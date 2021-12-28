package com.sofn.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.Dict;
import com.sofn.common.utils.DictUtils;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.UserUtil;
import com.sofn.sys.enums.SysManageEnum;
import com.sofn.sys.mapper.SysAptitudeMapper;
import com.sofn.sys.model.SysAptitude;
import com.sofn.sys.service.SysAptitudeService;
import com.sofn.sys.service.SysFileManageService;
import com.sofn.sys.vo.SysFileManageForm;
import com.sofn.sys.vo.SysFileManageVo;
import com.sofn.sys.vo.SysFileVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 资质文件管理服务
 *
 * @author heyongjie
 * @date 2019/11/22 15:53
 */
@Service
@Slf4j
public class SysAptitudeServiceImpl implements SysAptitudeService {

    @Autowired
    private SysAptitudeMapper sysAptitudeMapper;

    @Autowired
    private SysFileManageService sysFileManageService;

    /**
     * 字典中保存了一些资质证书，只能是字典中存在的
     */
    private final static String ZZKEY = "zzzs";

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchSave(Map<String, List<String>> infos, String orgId) {
        if (!CollectionUtils.isEmpty(infos)) {
            // 1. 将Map转为 List<SysAptitude>对象
            List<SysAptitude> sysAptitudes = getSysAptitudes(infos, orgId);
            // 2. 激活资质文件
            List<String> fileIds = sysAptitudes.stream().map(SysAptitude::getFileId).collect(Collectors.toList());
            activationFile(Sets.newHashSet(fileIds));
            // 3. 文件激活后保存文件信息
            if (!CollectionUtils.isEmpty(sysAptitudes)) {
                sysAptitudeMapper.batchInsert(sysAptitudes);
            }

        }
    }

    /**
     * 根据文件信息获取SysAptitude
     *
     * @param infos 文件信息
     * @param orgId 机构ID
     * @return List<SysAptitude>
     */
    private List<SysAptitude> getSysAptitudes(Map<String, List<String>> infos, String orgId) {
        List<SysAptitude> sysAptitudes = Lists.newArrayList();
        infos.forEach((type, fileIds) -> {
            if (!CollectionUtils.isEmpty(fileIds)) {
                fileIds.forEach(fileId -> {
                    SysAptitude sysAptitude = new SysAptitude();
                    sysAptitude.preInsert();
                    sysAptitude.setCalificacionType(type);
                    sysAptitude.setFileId(fileId);
                    sysAptitude.setOrgId(orgId);
                    checkInfo(sysAptitude);
                    sysAptitudes.add(sysAptitude);
                });
            }
        });
        return sysAptitudes;
    }

    /**
     * 根据机构ID获取当前机构的所有资质证书信息
     *
     * @param orgId 机构ID
     * @return 资质证书信息  List<SysAptitude>
     */
    private List<SysAptitude> get(String orgId) {
        return sysAptitudeMapper.selectList(new QueryWrapper<SysAptitude>().eq("ORG_ID", orgId)
                .eq("DEL_FLAG", SysManageEnum.DEL_FLAG_N.getCode()));
    }

    @Override
    public Map<String, List<SysFileManageVo>> getInfo(String orgId) {
        Map<String, List<SysFileManageVo>> returnInfos = Maps.newHashMap();
        // 1. 查询资质证书信息
        List<SysAptitude> sysAptitudes = get(orgId);
        if (!CollectionUtils.isEmpty(sysAptitudes)) {
            // 2. 查询文件信息
            List<String> fileIds = sysAptitudes.stream().map(SysAptitude::getFileId).collect(Collectors.toList());
            List<SysFileManageVo> fileInfos = sysFileManageService.getFileInfoByIds(fileIds);
            if (!CollectionUtils.isEmpty(fileInfos)) {
                // 3. 根据类型分组
                Map<String, List<String>> typeHaveFiles = getTypeHaveFiles(sysAptitudes);
                // 4. 根据分组信息找出完整的文件信息
                if (!CollectionUtils.isEmpty(typeHaveFiles)) {
                    typeHaveFiles.forEach((k, v) -> {
                        List<SysFileManageVo> returnLists = Lists.newArrayList();
                        if (!CollectionUtils.isEmpty(v)) {
                            v.forEach((e) -> {
                                Optional<SysFileManageVo> first = fileInfos.stream().filter(f -> f.getId().equals(e)).findFirst();
                                if (first.isPresent()) {
                                    SysFileManageVo sysFileManageVo = first.get();
                                    returnLists.add(sysFileManageVo);
                                }
                            });
                        }
                        returnInfos.put(k, returnLists);
                    });
                }
            }
        }
        return returnInfos;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByOrgId(String orgId) {
        // 获取当前机构的所有凭证信息
        List<SysAptitude> sysAptitudes = get(orgId);
        if (!CollectionUtils.isEmpty(sysAptitudes)) {
            // 删除文件
            List<String> fileIds = sysAptitudes.stream().map(SysAptitude::getFileId).collect(Collectors.toList());
            sysFileManageService.batchDelete(fileIds,UserUtil.getLoginToken());
            // 删除数据
            sysAptitudeMapper.deleteByOrgId(orgId, UserUtil.getLoginUserId());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Map<String, List<String>> infos, String orgId) {
        List<SysAptitude> sysAptitudes = get(orgId);
        if (CollectionUtils.isEmpty(infos)) {
            // 如果传入的内容为空，判断当前机构之前是否有凭证文件，如果有，就直接全部删除
            if (!CollectionUtils.isEmpty(sysAptitudes)) {
                deleteByOrgId(orgId);
            }
        } else {
            // 如果传入的信息不为空并且查询的结果也不为空
            if (!CollectionUtils.isEmpty(sysAptitudes)) {
                Map<String, List<String>> typeHaveFiles = getTypeHaveFiles(sysAptitudes);
                // 比较内容
                boolean change = isChange(infos, typeHaveFiles);
                if (change) {
                    // 发生了变化
                    // 需要激活的文件
                    Set<String> addFileInfo = getChangeFilePath(typeHaveFiles, infos);
                    activationFile(addFileInfo);
                    // 需要删除的文件
                    Set<String> removeFileInfo = getChangeFilePath(infos, typeHaveFiles);
                    if (!CollectionUtils.isEmpty(removeFileInfo)) {
                        log.info("更新资质证书，需要删除的文件id={}", removeFileInfo);
                        sysFileManageService.batchDelete(Lists.newArrayList(removeFileInfo), UserUtil.getLoginToken());
                    }
                    // 删除全部对应关系 然后批量新增
                    sysAptitudeMapper.deleteByOrgId(orgId, UserUtil.getLoginUserId());
                    List<SysAptitude> sysAptitudes1 = getSysAptitudes(infos, orgId);
                    if (!CollectionUtils.isEmpty(sysAptitudes1)) {
                        sysAptitudeMapper.batchInsert(sysAptitudes1);
                    }
                }
            } else {
                // 直接保存
                batchSave(infos, orgId);
            }
        }
    }

    /**
     * 激活资质文件
     *
     * @param fileIds 需要激活的文件
     */
    private void activationFile(Set<String> fileIds) {
        if (!CollectionUtils.isEmpty(fileIds)) {
            log.info("更新资质证书，需要激活的文件id={}", fileIds);
            List<SysFileVo> sysFileVos = sysFileManageService.activationFile(new SysFileManageForm("", SysManageEnum.SYS_SYSTEM_ID.getCode(),
                    "企业资质证书", "hidden", IdUtil.getStrIdsByList(Lists.newArrayList(fileIds))),UserUtil.getLoginToken());
            if (!CollectionUtils.isEmpty(sysFileVos)) {
                if (fileIds.size() != sysFileVos.size()) {
                    log.error("需要激活的文件addFileInfo={},激活成功的文件sysFileVos={}", fileIds, sysFileVos);
                    throw new SofnException("有资质文件激活失败");
                }
            } else {
                throw new SofnException("没有资质文件被激活");
            }
        }
    }

    /**
     * 找出文件2相对于文件1来说多出的部分
     *
     * @param fileInfo1 文件信息1
     * @param fileInfo2 文件信息2
     * @return Set<String>
     */
    private Set<String> getChangeFilePath(Map<String, List<String>> fileInfo1, Map<String, List<String>> fileInfo2) {
        Set<String> returnInfos = Sets.newHashSet();
        // 如果文件1为空就直接返回文件信息2中有的所有文件
        if (CollectionUtils.isEmpty(fileInfo1)) {
            if (!CollectionUtils.isEmpty(fileInfo2)) {
                fileInfo2.forEach((k, v) -> returnInfos.addAll(v));
            }
        } else {
            if (!CollectionUtils.isEmpty(fileInfo2)) {
                // 1 2 都不为空
                Set<String> f1 = Sets.newHashSet();
                fileInfo1.forEach((k, v) -> f1.addAll(v));
                Set<String> f2 = Sets.newHashSet();
                fileInfo2.forEach((k, v) -> f2.addAll(v));
                // 2的内容减去1的内容就是多出的部分
                f2.removeAll(f1);
                returnInfos.addAll(f2);
            }
        }
        return returnInfos;
    }

    /**
     * 判断内容是否发生变化
     *
     * @param oldInfo 旧的内容
     * @param newInfo 新的内容
     * @return true 发生变化  false 未发生变化
     */
    private boolean isChange(Map<String, List<String>> oldInfo, Map<String, List<String>> newInfo) {
        // 二个都为空 肯定没有发生变化
        if (CollectionUtils.isEmpty(oldInfo) && CollectionUtils.isEmpty(newInfo)) {
            return false;
        }
        // 一个为空 一个不为空  肯定发生变化了
        boolean flag = (CollectionUtils.isEmpty(oldInfo) && !CollectionUtils.isEmpty(newInfo)) ||
                !(CollectionUtils.isEmpty(oldInfo) && CollectionUtils.isEmpty(newInfo));
        if (flag) {
            return true;
        }
        // 二个都不为空
        if (!CollectionUtils.isEmpty(newInfo) && !(CollectionUtils.isEmpty(oldInfo))) {
            // 判断KEY是否相同
            boolean keyIsEquals = ListUtils.isEqualList(newInfo.keySet(), oldInfo.keySet());
            if (keyIsEquals) {
                // KEY相同判断内容是否发生了变化
                for (String key : oldInfo.keySet()) {
                    boolean contentEqual = ListUtils.isEqualList(oldInfo.get(key), newInfo.get(key));
                    // 如果内容比较不相等就直接返回true 代表已经发生了变化
                    if (!contentEqual) {
                        return true;
                    }
                }
                return false;
            } else {
                return true;
            }

        }

        return false;
    }


    /**
     * 根据凭证类型将文件分组
     *
     * @param sysAptitudes 凭证信息 List<SysAptitude>
     * @return Map<String, List < String>>  KEY 为凭证类型 value为有的文件
     */
    private Map<String, List<String>> getTypeHaveFiles(List<SysAptitude> sysAptitudes) {
        Map<String, List<String>> returnInfos = Maps.newHashMap();
        if (!CollectionUtils.isEmpty(sysAptitudes)) {
            sysAptitudes.forEach((sysAptitude) -> {
                if (sysAptitude != null) {
                    String key = sysAptitude.getCalificacionType();
                    List<String> values = null;
                    if (returnInfos.containsKey(key)) {
                        values = returnInfos.get(key);
                    }
                    if (CollectionUtils.isEmpty(values)) {
                        values = Lists.newArrayList();
                    }
                    values.add(sysAptitude.getFileId());
                    returnInfos.put(key, values);
                }
            });
        }
        return returnInfos;

    }


    /**
     * 检查资质信息是否填写完整
     *
     * @param sysAptitude 资质信息
     */
    private void checkInfo(SysAptitude sysAptitude) {
        if (sysAptitude == null) {
            throw new SofnException("资质信息为空");
        }
        if (StringUtils.isBlank(sysAptitude.getOrgId())) {
            throw new SofnException("机构信息为空");
        }
        if (StringUtils.isBlank(sysAptitude.getFileId())) {
            throw new SofnException("文件信息为空");
        }
        if (StringUtils.isBlank(sysAptitude.getCalificacionType())) {
            throw new SofnException("资质类别为空");
        }
        Dict byTypeAndKey = DictUtils.getByTypeAndKey(ZZKEY, sysAptitude.getCalificacionType());
        if (byTypeAndKey == null) {
            throw new SofnException("资质证书不存在，请核对");
        }
    }
}
