package com.sofn.fdpi.util;

import com.google.common.collect.Lists;
import com.sofn.common.model.Result;
import com.sofn.fdpi.constants.Constants;
import com.sofn.fdpi.enums.SysTipsEnum;
import com.sofn.fdpi.sysapi.SysRegionApi;
import com.sofn.fdpi.sysapi.bean.SysFileManageForm;
import com.sofn.fdpi.vo.FileManageVo;
import com.sofn.fdpi.vo.SysFileVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description:调用支撑平台文件处理的工具类
 * @author:wuxy
 * @date:2019-12-6 14:09:33
 */
@Slf4j
@Component
public class SysFileManageUtil {
    @Autowired
    private SysRegionApi sysRegionApi;

    public static SysFileManageUtil sysFileManageUtil;

    @PostConstruct
    public void init() {
        sysFileManageUtil = this;
        sysFileManageUtil.sysRegionApi = this.sysRegionApi;
    }

    /**
     * @param sysFileIds:需要激活的ID，用,隔开
     * @return true or false
     * @description:激活文件（前端调用上传文件在上传表单，接口需要对文件进行激活，否则文件会被定时删除）
     * @author：wuxy
     * @date：2019-12-6 14:01:41
     */
    public static boolean activationFile(String sysFileIds) {
        try {
            SysFileManageForm sfmf = new SysFileManageForm();
            sfmf.setIds(sysFileIds);
            sfmf.setInterfaceNum("hidden");
            sfmf.setSystemId(Constants.SYSTEM_ID);
            Result<List<SysFileVo>> result = sysFileManageUtil.sysRegionApi.activationFile(sfmf);

            if (!Result.CODE.equals(result.getCode())) {
                writeErrorLog(1, "", sysFileIds);
                return false;
            }
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error(sysFileIds + "激活失败:" + ex.getMessage());
        }

        return false;
    }

    /**
     * @param :oldSysFileId:要替换的文件ID;
     * @param :remark:备注
     * @param :newSysFileId:新文件Id
     * @return true or false
     * @description:替换（前端调用上传文件在上传表单，接口需要对文件进行激活，否则文件会被定时删除）
     * @author：wuxy
     * @date：2019-12-6 14:01:41
     */
    public static boolean replaceFile(String oldSysFileId, String remark, String newSysFileId) {
        try {
            Result result = sysFileManageUtil.sysRegionApi.replaceFile(oldSysFileId, remark, newSysFileId);

            if (!Result.CODE.equals(result.getCode())) {
                writeErrorLog(2, oldSysFileId, newSysFileId);
                return false;
            }
            return true;
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return false;
    }

    /**
     * @param sysFileId：支撑平台的文件id
     * @return
     * @description：删除文件
     * @author：wuxy
     * @date：2019-12-6 15:09:20
     */
    public static boolean delFile(String sysFileId) {
        try {
            Result result = sysFileManageUtil.sysRegionApi.delFile(sysFileId);

            if (!Result.CODE.equals(result.getCode())) {
                writeErrorLog(3, "", sysFileId);
                return false;
            }
            return true;
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return false;
    }

    /**
     * @param sysFileIds：支撑平台的文件ids，多个使用,相隔
     * @return
     * @description：批量删除文件
     * @author：wuxy
     * @date：2019-12-6 15:09:20
     */
    public static boolean batchDeleteFile(String sysFileIds) {
        try {
            Result result = sysFileManageUtil.sysRegionApi.batchDeleteFile(sysFileIds);

            if (!Result.CODE.equals(result.getCode())) {
                writeErrorLog(3, "", sysFileIds);
                return false;
            }
            return true;
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return false;
    }

    /**
     * 含有单个文件上传的表单，对文件的统一处理
     *
     * @param sysFileIdInForm:表单中上传上来的文件id
     * @param sysFileIdInDB：当前表单数据库中的文件id
     * @return Result对象，code为200，获取msg的值，msg值是返回的sysFileId，存入到数据库中使用
     */
    public static Result dealFileInUploadForm(String sysFileIdInForm, String sysFileIdInDB) {
        //处理文件的过程
        if (StringUtils.isBlank(sysFileIdInForm) && StringUtils.isNotBlank(sysFileIdInDB)) {
            //调用删除文件，删除原文件
            boolean delSuccess = delFile(sysFileIdInDB);
            if (!delSuccess) {
                return Result.error(SysTipsEnum.FILE_DELETE_ERROR.getMsg());
            }
            return Result.ok();
        } else if (StringUtils.isNotBlank(sysFileIdInForm)) {
            if (StringUtils.isBlank(sysFileIdInDB)) {
                //激活文件
                boolean actSuccess = activationFile(sysFileIdInForm);
                if (!actSuccess) {
                    return Result.error(SysTipsEnum.FILE_ACTIVE_ERROR.getMsg());
                }
                return Result.ok(sysFileIdInForm);
            } else {
                if (!sysFileIdInForm.equals(sysFileIdInDB)) {
                    //替换文件，替换文件，文件id是原文件的id
                    boolean replaceSuccess = replaceFile(sysFileIdInDB, "", sysFileIdInForm);
                    if (!replaceSuccess) {
                        return Result.error(SysTipsEnum.FILE_REPLACE_ERROR.getMsg());
                    }
                    return Result.ok(sysFileIdInDB);
                }
            }
        }
        return Result.ok(sysFileIdInForm);
    }

    /**
     * 有批量上传文件表单，对文件统一处理
     *
     * @param fileListInForm 表单中的文件
     * @param fileListInDb   表单中数据库存在的文件
     * @return true/false
     */
    public static boolean dealFileListForUpdate(List<FileManageVo> fileListInForm, List<FileManageVo> fileListInDb) {
        if (CollectionUtils.isEmpty(fileListInForm) && !CollectionUtils.isEmpty(fileListInDb)) {
            //删除数据库中的文件
            List<String> idList = fileListInDb.stream().map(FileManageVo::getId).collect(Collectors.toList());
            String ids = String.join(",", idList);
            return batchDeleteFile(ids);
        }
        if (!CollectionUtils.isEmpty(fileListInForm) && CollectionUtils.isEmpty(fileListInDb)) {
            //全部激活
            List<String> idList = fileListInForm.stream().map(FileManageVo::getId).collect(Collectors.toList());
            String ids = String.join(",", idList);
            return activationFile(ids);
        }

        if (!CollectionUtils.isEmpty(fileListInForm) && !CollectionUtils.isEmpty(fileListInDb)) {
            //表单和数据库都有数据，则需要区分哪些进行激活和删除,都存在的则不处理
            List<String> activeFileIdList = Lists.newArrayList();
            List<String> existFileIdList = Lists.newArrayList();
            List<String> deleteFileIdList = Lists.newArrayList();
            boolean isExist = false;
            int doNotExistCount = 0;
            for (FileManageVo manageVo : fileListInForm) {
                doNotExistCount = 0;
                isExist = false;
                for (FileManageVo fileManageVo : fileListInDb) {
                    if (manageVo.getId().contains(fileManageVo.getId())) {
                        isExist = true;
                        break;
                    } else {
                        doNotExistCount++;
                    }
                }
                if (isExist) {
                    //都存在
                    existFileIdList.add(manageVo.getId());
                }
                if (doNotExistCount == fileListInDb.size()) {
                    activeFileIdList.add(manageVo.getId());
                }
            }
            if (CollectionUtils.isEmpty(existFileIdList)) {
                //不存在相同的，则把数据库中的文件，全部删除
                deleteFileIdList = fileListInDb.stream().map(FileManageVo::getId).collect(Collectors.toList());
            } else {
                deleteFileIdList = fileListInDb.stream().filter(fileManageVo -> !existFileIdList.contains(fileManageVo.getId()))
                        .map(FileManageVo::getId).collect(Collectors.toList());
            }
            if(!CollectionUtils.isEmpty(activeFileIdList)){
                String ids = String.join(",", activeFileIdList);
                activationFile(ids);
            }
            if(!CollectionUtils.isEmpty(deleteFileIdList)){
                String ids = String.join(",", deleteFileIdList);
                batchDeleteFile(ids);
            }
        }
        return true;
    }

    /**
     * 写异常日志
     *
     * @param type         1：激活；2：替换；3：删除
     * @param oldSysFileId
     * @param newSysFileId
     */
    private static void writeErrorLog(int type, String oldSysFileId, String newSysFileId) {
        StringBuilder sb = new StringBuilder();
        switch (type) {
            case 1:
                sb.append(SysTipsEnum.FILE_ACTIVE_ERROR.getMsg())
                        .append(":")
                        .append(newSysFileId);
                log.error(sb.toString());
                break;
            case 2:
                sb.append(SysTipsEnum.FILE_REPLACE_ERROR.getMsg())
                        .append(":")
                        .append(oldSysFileId)
                        .append("_")
                        .append(newSysFileId);
                log.error(sb.toString());
                break;
            case 3:
                sb.append(SysTipsEnum.FILE_DELETE_ERROR.getMsg())
                        .append(":")
                        .append(newSysFileId);
                log.error(sb.toString());
                break;
            default:
                log.error(newSysFileId);
        }
    }
}
