package com.sofn.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sofn.common.exception.SofnException;
import com.sofn.common.fileutil.FastDFSClient;
import com.sofn.common.utils.BoolUtils;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.sys.enums.SysFileManageEnum;
import com.sofn.sys.enums.SysManageEnum;
import com.sofn.sys.mapper.SysFileManageMapper;
import com.sofn.sys.mapper.SysFileManageOperateLogMapper;
import com.sofn.sys.mapper.SysSubsystemMapper;
import com.sofn.sys.mapper.SysUserMapper;
import com.sofn.sys.model.SysFileManage;
import com.sofn.sys.model.SysFileManageOperateLog;
import com.sofn.sys.model.SysSubsystem;
import com.sofn.sys.model.SysUser;
import com.sofn.sys.service.SysFileManageService;
import com.sofn.sys.util.HandleFileUtil;
import com.sofn.sys.vo.SysFileManageForm;
import com.sofn.sys.vo.SysFileManageVo;
import com.sofn.sys.vo.SysFileVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 文件管理相关实现
 *
 * @author heyongjie
 * @date 2019年11月12日11:25:58
 */
@Service
@Slf4j
public class SysFileManageServiceImpl extends ServiceImpl<SysFileManageMapper, SysFileManage> implements SysFileManageService {


    @Autowired
    private SysFileManageMapper sysFileManageMapper;

    @Autowired
    private SysFileManageOperateLogMapper sysFileOperateLogMapper;

    @Autowired
    private SysSubsystemMapper sysSubsystemMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 当上传的文件没有后缀的时候使用other为后缀
     */
    private final static String OTHER_FILE_TYPE = "other";


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteFile(String id,String token) {
        // 1. 逻辑删除
        SysFileManage sysFile = sysFileManageMapper.getOne(id);
        if (sysFile == null) {
            throw new SofnException("待删除文件不存在");
        }
        sysFile.setUpdateTime(new Date());
        sysFile.setUpdateUserId(getLoginUserId(token));
        sysFile.setDelFlag(SysManageEnum.DEL_FLAG_Y.getCode());
        sysFileManageMapper.updateById(sysFile);
        // 3. 删除操作日志
        sysFileOperateLogMapper.deleteByFileIds(Lists.newArrayList(id), getLoginUserId(token));
        // 4. 删除文件   暂时不删除文件 当超过期限后再删除文件
//        FastDFSClient.delete_file(sysFile.getFilePath());
    }

    @Override
    public Object showRecords(String id) {
        // 根据文件获取操作日志
        List<SysFileManageOperateLog> list = sysFileOperateLogMapper.getSysFileOperateLogByFileId(id);
        if (!CollectionUtils.isEmpty(list)) {
            for (SysFileManageOperateLog log : list) {
                if (log != null) {
                    if (!StringUtils.isBlank(log.getSystem())) {
                        String systemName = "";
                        SysSubsystem sysSubsystemAppId = sysSubsystemMapper.getSysSubsystemAppId(log.getSystem());
                        if (sysSubsystemAppId != null) {
                            systemName = sysSubsystemAppId.getSubsystemName();
                        } else {
                            String[] sys = {log.getSystem()};
                            List<SysSubsystem> sysSubsystems = sysSubsystemMapper.selectSubsystemByIds(sys);
                            if (!CollectionUtils.isEmpty(sysSubsystems)) {
                                SysSubsystem sysSubsystem = sysSubsystems.get(0);
                                systemName = sysSubsystem.getSubsystemName();
                            }
                        }
                        log.setSystem(systemName);
                    } else {
                        log.setSystem("");
                    }
                }
                SysUser sysUser = sysUserMapper.selectByPrimaryKey(log.getOperator());
                log.setOperator(sysUser == null ? "-" : sysUser.getNickname());
            }
        }
        return list;
    }

    @Override
    public String getFileRealPath(String id) {
        SysFileManage sysFileManage = sysFileManageMapper.getOne(id);
        if (sysFileManage == null) {
            throw new SofnException("文件不存在");
        }
        return FastDFSClient.getRealPath(sysFileManage.getFilePath());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String batchDownloadFileByZip(String strIds,String token,String fileName) {
        // 1. 将ids转换为List
        List<String> ids = IdUtil.getIdsByStr(strIds);
        if (CollectionUtils.isEmpty(ids)) {
            throw new SofnException("请选择要下载的文件");
        }
        // 2. 找到有效的Path
        Map<String, String> effectivePaths = Maps.newHashMap();
        List<SysFileManage> sysFileManages = sysFileManageMapper.selectBatchIds(ids);
        if(!CollectionUtils.isEmpty(sysFileManages)){
            sysFileManages.forEach(sysFileManage->{
                if(sysFileManage != null){
                    // 如果文件名存在就加一个UUID在中间，否则是原来的名字
                    if(!StringUtils.isBlank(sysFileManage.getFilePath()) &&
                            BoolUtils.Y.equals(sysFileManage.getActivationState())
                            && BoolUtils.Y.equals(sysFileManage.getFileState())
                    ){
                        if (effectivePaths.containsKey(sysFileManage.getFileName())) {
                            effectivePaths.put(HandleFileUtil.getFileName(sysFileManage.getFileName()), sysFileManage.getFilePath());
                        } else {
                            effectivePaths.put(sysFileManage.getFileName(), sysFileManage.getFilePath());
                        }
                    }else{
                        log.info("ID{}对应的文件不存在、未启用或者未激活，不下载这条记录",sysFileManage.getId());
                    }
                }
            });
        }else{
            throw new SofnException("没有任何文件存在，不处理！");
        }
        if (CollectionUtils.isEmpty(effectivePaths)) {
            log.info("【批量下载文件】没有有效文件，不处理，参数strIds={}", strIds);
            throw new SofnException("文件都不存在，请检查");
        }
        // 3. 将需要下载的文件压缩


        String zipFilePath =  HandleFileUtil.zipFileByPaths(effectivePaths,fileName);
        // 4. 更新文件下载次数和保存操作日志
        sysFileManageMapper.addFileDownlowdNum(sysFileManages.stream().map(SysFileManage::getId).collect(Collectors.toList()));
        List<SysFileManageOperateLog> sysFileManageOperateLogList = sysFileManages.stream().map(item->getSysFileManageOperateLog(item,"下载文件",getLoginUserId(token))).collect(Collectors.toList());
        if(!CollectionUtils.isEmpty(sysFileManageOperateLogList)){
            sysFileOperateLogMapper.batchSaveLog(sysFileManageOperateLogList);
        }
        return zipFilePath;
    }

    @Override
    public SysFileManageVo getOne(String id) {
        SysFileManage sysFileManage = sysFileManageMapper.getOne(id);
        if (sysFileManage == null) {
            throw new SofnException("文件不存在");
        }
        // 翻译
        SysFileManageVo sysFileManageVo = SysFileManageVo.getSysFileManageVo(sysFileManage);
        SysSubsystem sysSubsystem = sysSubsystemMapper.selectById(sysFileManage.getSystem());
        sysFileManageVo.setSystemName(sysSubsystem == null ? "defaultSystem" : sysSubsystem.getSubsystemName());
        return sysFileManageVo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchDelete(List<String> fileIds,String token) {
        if (!CollectionUtils.isEmpty(fileIds)) {
            List<SysFileManage> sysFileBys = sysFileManageMapper.getSysFileByIdsIncludNotActivation(fileIds);
            if (!CollectionUtils.isEmpty(sysFileBys)) {
                // 删除文件
                List<String> filePaths = sysFileBys.stream().map(SysFileManage::getFilePath).collect(Collectors.toList());
                // 删除数据
                List<String> ids = sysFileBys.stream().map(SysFileManage::getId).collect(Collectors.toList());
                if (!CollectionUtils.isEmpty(ids)) {
                    String loginUserId = getLoginUserId(token);
                    sysFileManageMapper.batchDeleteFile(ids, loginUserId);
                    sysFileOperateLogMapper.deleteByFileIds(ids, loginUserId);
//                    // 可选择是否删除数据保留文件
//                    if(!SysManageEnum.DEL_FLAG_Y.getCode().equals(retainFile)){
//                        FastDFSClient.batchDeleteFile(filePaths);
//                    }

                }
            }
        }
    }

    @Override
    public List<SysFileManageVo> getFileInfoByIds(List<String> ids) {
        List<SysFileManageVo> sysFileManageVos = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(ids)) {
            List<SysFileManage> sysFiles = sysFileManageMapper.getSysFileByIdsIncludNotActivation(ids);
            // 查询激活的文件
            List<SysFileManage> activationFiles = sysFiles.stream().filter(e -> SysFileManageEnum.ACTIVATION_STATE_YES.getCode().equals(e.getActivationState())).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(activationFiles)) {
                activationFiles.forEach((sysFileManage) -> {
                    SysFileManageVo sysFileManageVo = SysFileManageVo.getSysFileManageVo(sysFileManage);
                    sysFileManageVo.setSystemName(getSystemName(sysFileManageVo.getSystemId()));
                    sysFileManageVos.add(sysFileManageVo);
                });
            }
        }
        return sysFileManageVos;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public long deleteNotActivationFile(Integer beforeDays) {
        // 1. 查询所有上传时间大于一天的未激活文件数量  这里使用分页查询方法删除，避免数据量过大，内存溢出
        long count = sysFileManageMapper.selectNotActivationCount(beforeDays);
        int pageSize = Integer.parseInt(SysFileManageEnum.BATCH_DELETE_NUM.getCode());
        if (count > pageSize) {
            PageHelper.startPage(0, pageSize);
            List<SysFileManage> sysFileManages = sysFileManageMapper.selectNotActivationList(beforeDays);
            PageInfo<SysFileManage> pageInfo = new PageInfo<>(sysFileManages);
            // 获取总共多少页
            int pages = pageInfo.getPages();
            // 分页删除
            for (int i = 1; i <= pages; i++) {
                // 因为数据删除后，后面的数据往前移，所以每次都从0开始
                PageHelper.startPage(0, pageSize);
                List<SysFileManage> tempData = sysFileManageMapper.selectNotActivationList(beforeDays);
                deleteFile(tempData);
            }
        } else {
            List<SysFileManage> sysFileManages = sysFileManageMapper.selectNotActivationList(beforeDays);
            deleteFile(sysFileManages);
        }
        return count;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateFileName(String id, String name,String token) {
        SysFileManage one = sysFileManageMapper.getOne(id);
        if (one == null) {
            throw new SofnException("待更新文件不存在");
        }
        one.setFileName(name);
        String loginUserId = getLoginUserId(token);
        one.setUpdateUserId(loginUserId);
        one.setUpdateTime(new Date());
        one.setOperateTime(new Date());
        one.setOperator(loginUserId);
        sysFileManageMapper.updateById(one);
    }

    @Override
    public List<SysFileManage> getDeleteFileByDate(int date) {
        // 1. 计算date之前的日期
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, -date);
        Date time = calendar.getTime();
        return sysFileManageMapper.selectList(new QueryWrapper<SysFileManage>()
                .eq("DEL_FLAG", SysManageEnum.DEL_FLAG_Y.getCode())
                .le("OPERATE_TIME", time)
        );
    }


    private String getRealFileName(String fileName){
        if(StringUtils.isNotBlank(fileName)){
            if(fileName.contains(".")){
                try {
                    return fileName.split("\\.")[0];
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return "";

    }

    @Override
    public void updateFileInfo(SysFileManageForm sysFileManageForm, String token) {
        if(sysFileManageForm == null) {
            log.info("【更新文件信息】未执行操作，更新信息为空");
            throw new SofnException("文件信息为空");
        }
        String id = sysFileManageForm.getIds();
        if(StringUtils.isBlank(id)){
            throw new SofnException("请传入待更新ID");
        }

        // 1.判断子系统是否存在

        SysFileManage sysFileManage = sysFileManageMapper.selectById(id);
        if(sysFileManage == null){
            throw new SofnException("文件不存在");
        }
        if(SysFileManageEnum.ACTIVATION_STATE_NO.getCode().equals(sysFileManage.getActivationState())){
            throw new SofnException("文件未激活");
        }
        if(SysManageEnum.DEL_FLAG_Y.getCode().equals(sysFileManage.getDelFlag())){
            throw new SofnException("文件已删除");
        }
        checkFileNameIsExists(sysFileManageForm.getSystemId(),sysFileManageForm.getFileName(),id );
        String loginUserId = UserUtil.getLoginUserId(token);
        sysFileManage.setUpdateUserId(loginUserId);
        sysFileManage.setUpdateTime(new Date());
        sysFileManage.setOperator(loginUserId);
        sysFileManage.setOperateTime(new Date());
        if(!StringUtils.isBlank(sysFileManageForm.getRemark()) ){
            sysFileManage.setRemark(sysFileManageForm.getRemark());
        }
        if(!StringUtils.isBlank(sysFileManageForm.getFileName()) ){
            sysFileManage.setFileName(sysFileManageForm.getFileName());
        }
        if(!StringUtils.isBlank(sysFileManageForm.getFileState()) ){
            sysFileManage.setFileState(sysFileManageForm.getFileState());
        }
        if(!StringUtils.isBlank(sysFileManageForm.getBusinessFileType()) ){
            sysFileManage.setBusinessFileType(sysFileManageForm.getBusinessFileType());
        }
        if(!StringUtils.isBlank(sysFileManageForm.getFileNumber()) ){
            sysFileManage.setFileNumber(sysFileManageForm.getFileNumber());
        }
        sysFileManageMapper.updateById(sysFileManage);

    }

    /**
     * 删除数据
     *
     * @param sysFileManages 数据
     */
    private void deleteFile(List<SysFileManage> sysFileManages) {
        if (!CollectionUtils.isEmpty(sysFileManages)) {
            // 删除数据
            List<String> fileIds = sysFileManages.stream().map(SysFileManage::getId).collect(Collectors.toList());
            sysFileManageMapper.batchDeleteFile(fileIds, "系统自动删除");
            // 删除操作记录
            sysFileOperateLogMapper.deleteByFileIds(fileIds, "系统自动删除");
            // 删除文件
            List<String> filePaths = sysFileManages.stream().map(SysFileManage::getFilePath).collect(Collectors.toList());
            FastDFSClient.batchDeleteFile(filePaths);

        }
    }

    @Override
    public Map<String, String> downloadFile(String id,String token) {
        Map<String, String> resultInfo = Maps.newHashMap();
        SysFileManage sysFileManage = sysFileManageMapper.getOne(id);
        if (sysFileManage == null) {
            throw new SofnException("文件不存在");
        }
        // 禁用状态不让下载
        if(!BoolUtils.Y.equals(sysFileManage.getFileState())){
            log.error("文件ID:{}停用，无法下载",id);
            throw new SofnException("文件停用，无法下载");
        }

        String path = sysFileManage.getFilePath();
        byte[] fileInfo = FastDFSClient.download_bytes(path);
        String strFileInfo = Base64.encodeBase64String(fileInfo);
        if (fileInfo == null) {
            log.info("下载的文件为空fileInfo=null，可能原因是文件已删除，但数据未删除");
            throw new SofnException("文件下载异常");
        }
        String fileName = sysFileManage.getFileName();
        if (fileName.contains(sysFileManage.getFileType())) {
            resultInfo.put("fileName", fileName);
        } else {
            resultInfo.put("fileName", sysFileManage.getFileName() + sysFileManage.getFileType());
        }
        resultInfo.put("fileInfo", strFileInfo);
        //修改下载次数
        sysFileManage.setDownloadTimes(sysFileManage.getDownloadTimes() + 1);
        sysFileManageMapper.updateById(sysFileManage);
        saveRecord(sysFileManage, "下载",token);
        return resultInfo;
    }

    /**
     * 下载文件
     * @param sysFileManage  文件信息
     * @param token    用户登录TOKEN
     * @return   文件内容
     */
    @Override
    public byte[] downloadFileNew(SysFileManage sysFileManage,String token ){
        if (sysFileManage == null) {
            throw new SofnException("文件不存在");
        }
        // 禁用状态不让下载
        if(!BoolUtils.Y.equals(sysFileManage.getFileState())){
            log.error("文件ID:{}停用，无法下载",sysFileManage.getId());
            throw new SofnException("文件停用，无法下载");
        }

        String path = sysFileManage.getFilePath();
        byte[] fileInfo = FastDFSClient.download_bytes(path);
        if (fileInfo == null) {
            log.info("下载的文件为空fileInfo=null，可能原因是文件已删除，但数据未删除");
            throw new SofnException("文件下载异常");
        }
        //修改下载次数
        sysFileManage.setDownloadTimes(sysFileManage.getDownloadTimes() + 1);
        sysFileManageMapper.updateById(sysFileManage);
        saveRecord(sysFileManage, "下载",token);
        return fileInfo;
    }

    @Override
    public SysFileManage getSysFileManageById(String id){
        return sysFileManageMapper.getOne(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void replaceFile(String id,String fileName, String remark, String newId,String token) {
        //  判断旧文件和新文件是否都在
        SysFileManage oldFile = sysFileManageMapper.getOne(id);
        if (oldFile == null) {
            throw new SofnException("旧文件不存在");
        }
        SysFileManage newFile = sysFileManageMapper.selectById(newId);
        if (newFile == null) {
            throw new SofnException("新文件不存在");
        }
        if (SysManageEnum.DEL_FLAG_Y.getCode().equals(newFile.getDelFlag())) {
            throw new SofnException("新文件不存在");
        }

        // 如果新文件和旧文件是同一个ID  那么就只更新备注！
        String oldFilePath = oldFile.getFilePath();
        String loginUserId = getLoginUserId(token);
        if (!id.equals(newId)) {
            // 替换信息
            // 如果之前的文件名称带了后缀那么就需要更改文件名后缀，没带后缀就不用
            oldFile.setFilePath(newFile.getFilePath());
            oldFile.setFileType(newFile.getFileType());
            oldFile.setUpdateTime(new Date());
            oldFile.setUpdateUserId(loginUserId);
            oldFile.setFileSize(newFile.getFileSize());
            oldFile.setOperateTime(new Date());
            oldFile.setOperator(loginUserId);
        }
        if(!StringUtils.isBlank(remark)){
            oldFile.setRemark(remark);
        }

        if(!StringUtils.isBlank(fileName)){
            oldFile.setFileName(fileName);
        }
        // 保存新的文件信息
        sysFileManageMapper.updateById(oldFile);
        saveRecord(oldFile, "替换",token);
        // 删除旧文件
        if (!id.equals(newId)) {
            // 删除新的文件信息，只删除数据，不删除文件,这里不使用逻辑删除
            sysFileManageMapper.deleteById(newId);
            FastDFSClient.delete_file(oldFilePath);
        }

    }

    @Override
    public PageUtils<SysFileManageVo> getSysFileByConditionAndPage(Map<String, Object> params, Integer pageNo, Integer pageSize) {
        PageHelper.offsetPage(pageNo, pageSize);
        List<SysFileManageVo> sysFileManages = sysFileManageMapper.getSysFileByCondition(params);
        PageInfo<SysFileManageVo> pageInfo = new PageInfo(sysFileManages);
        List<SysFileManageVo> list = pageInfo.getList();
        if(!CollectionUtils.isEmpty(list)){
            List<SysFileManageVo> list2 = list.stream().map(item -> {
                if(item!= null){
                    item.setFilePath(FastDFSClient.getRealPath(item.getFilePath()));
                    return item;
                }
                return null;
            }).collect(Collectors.toList());
            pageInfo.setList(list2);
        }

        return PageUtils.getPageUtils(pageInfo);
    }

    /**
     * 根据系统ID获取系统名称
     *
     * @param systemId 系统ID
     * @return 系统名称
     */
    private String getSystemName(String systemId) {
        SysSubsystem sysSubsystem = sysSubsystemMapper.selectById(systemId);
        return sysSubsystem == null ? "defaultSystem" : sysSubsystem.getSubsystemName();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SysFileVo uploadFile(MultipartFile multipartFile,String token) {
        if (multipartFile == null) {
            throw new SofnException("请选择文件");
        }
        // 1. 上传文件   因为不管文件名是否重复，上传之后文件服务器都会返回一个唯一的地址，所以不用判断文件名是否重复  直接上传文件
        String filePath = FastDFSClient.uploadFileForStream(multipartFile);
        if (StringUtils.isEmpty(filePath)) {
            throw new SofnException("上传文件失败");
        }
        // 2. 获取文件信息
        SysFileManage sysFileManage = new SysFileManage();
        String id = IdUtil.getUUId();
        sysFileManage.setId(id);
        sysFileManage.setFileName(multipartFile.getOriginalFilename());
        sysFileManage.setFilePath(filePath);
        sysFileManage.setFileSize(multipartFile.getSize());
        if (Objects.requireNonNull(multipartFile.getOriginalFilename()).contains(".")) {
            sysFileManage.setFileType(multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf('.')));
        } else {
            sysFileManage.setFileType(OTHER_FILE_TYPE);
        }
        sysFileManage.setDownloadTimes(0);
        sysFileManage.setOperateTime(new Date());
        // 用户可不登录上传文件
        String loginUserId=  getLoginUserId(token);
        sysFileManage.setOperator(loginUserId);
        sysFileManage.setCreateTime(new Date());
        sysFileManage.setCreateUserId(loginUserId);
        sysFileManage.setDelFlag(SysManageEnum.DEL_FLAG_N.getCode());
        sysFileManage.setActivationState(SysFileManageEnum.ACTIVATION_STATE_NO.getCode());
        try {
            sysFileManageMapper.insert(sysFileManage);
            //3.保存文件变更记录
            saveRecord(sysFileManage, "新增文件",token);
            return SysFileVo.getSysFileVo(sysFileManage);
        } catch (Exception e) {
            e.printStackTrace();
            // 出现异常删除文件
            log.error("上传文件出现错误，正在删除文件，filePath={}", filePath);
            FastDFSClient.delete_file(filePath);
            throw new SofnException("上传文件失败");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<SysFileVo> activationFile(SysFileManageForm sysFileManageForm, String token ) {

        if(sysFileManageForm == null){
            return Lists.newArrayList();
        }
        if(StringUtils.isBlank(sysFileManageForm.getFileState())){
            // 如果fileState没传入，默认为启用
            sysFileManageForm.setFileState(BoolUtils.Y);
        }

        List<String> ids = IdUtil.getIdsByStr(sysFileManageForm.getIds());
        if (!CollectionUtils.isEmpty(ids)) {
            if (StringUtils.isBlank(sysFileManageForm.getSystemId())) {
                throw new SofnException("子系统ID不能为空，可为子系统ID也可为子系统appId");
            }

            // 1.判断子系统是否存在
            checkFileNameIsExists(sysFileManageForm.getSystemId(),sysFileManageForm.getFileName(),null );

            // 2.判断文件启用状态是否合法
            if(!StringUtils.isBlank(sysFileManageForm.getFileState())){
                if(!(SysManageEnum.DEL_FLAG_Y.getCode().equals(sysFileManageForm.getFileState()) ||
                        SysManageEnum.DEL_FLAG_N.getCode().equals(sysFileManageForm.getFileState())
                )){
                    throw new SofnException("文件是否启用状态不合法！");
                }
            }
            String loginUserId = getLoginUserId(token);
            sysFileManageMapper.activationFile2(sysFileManageForm,ids, loginUserId);
            // 3. 存储更新后的值
            List<SysFileManage> sysFiles = sysFileManageMapper.getSysFileByIdsIncludNotActivation(ids);
            List<SysFileVo> sysFileVos = Lists.newArrayList();
            if (!CollectionUtils.isEmpty(sysFiles)) {
                // 4. 记录操作日志
                List<SysFileManageOperateLog> sysFileManageOperateLogList = Lists.newArrayList();
                sysFiles.forEach((e) -> {
                    sysFileVos.add(SysFileVo.getSysFileVo(e));
                    SysFileManageOperateLog sysFileManageOperateLog = getSysFileManageOperateLog(e, "激活文件",token);
                    sysFileManageOperateLogList.add(sysFileManageOperateLog);
                });
                if (!CollectionUtils.isEmpty(sysFileManageOperateLogList)) {
                    sysFileOperateLogMapper.batchSaveLog(sysFileManageOperateLogList);
                }
            }
            return sysFileVos;
        }

        return Lists.newArrayList();
    }


    /**
     * 检查文件名称是否重复和系统ID是否存在
     * @param systemAppIdOrId   系统ID或者AppId
     * @param fileName   文件名称
     * @param fileId   文件ID
     */
    private void checkFileNameIsExists(String systemAppIdOrId,String fileName, String fileId){
        // TODO  取消文件不可重复的校验
//        SysSubsystem sysSubsystemAppId = sysSubsystemMapper.getSysSubsystemAppId(systemAppIdOrId);
//        if (sysSubsystemAppId == null) {
//            SysSubsystem sysSubsystem = sysSubsystemMapper.selectById(systemAppIdOrId);
//            if (sysSubsystem == null) {
//                throw new SofnException(systemAppIdOrId + "系统不存在");
//            }else{
//                sysSubsystemAppId = sysSubsystem;
//            }
//        }
//
//        if(StringUtils.isNotBlank(fileName)){
//            long fileNameNum = sysFileManageMapper.getFileNameNum(fileName,
//                    sysSubsystemAppId.getAppId(), sysSubsystemAppId.getId(),fileId );
//            if(fileNameNum > 0 ){
//                throw new SofnException("文件名称重复");
//            }
//        }
    }

    /**
     * 根据文件获取操作日志
     *
     * @param sysFile     文件信息
     * @param operateType 操作记录
     * @param token   用户登录ID
     * @return 操作日志 SysFileManageOperateLog
     */
    private SysFileManageOperateLog getSysFileManageOperateLog(SysFileManage sysFile, String operateType,String token) {
        if(sysFile == null){
            return null;
        }
        SysFileManageOperateLog sysFileOperateLog = new SysFileManageOperateLog();
        sysFileOperateLog.setId(UUID.randomUUID().toString());
        sysFileOperateLog.setFileId(sysFile.getId());
        sysFileOperateLog.setFileType(sysFile.getFileType());
        sysFileOperateLog.setFileName(sysFile.getFileName());
        sysFileOperateLog.setFileSize(sysFile.getFileSize());
        sysFileOperateLog.setOperateType(operateType);
        sysFileOperateLog.setDelFlag(SysManageEnum.DEL_FLAG_N.getCode());
        sysFileOperateLog.setSystem(sysFile.getSystem());
        String loginUserId = getLoginUserId(token);
        sysFileOperateLog.setOperator(loginUserId);
        sysFileOperateLog.setOperateTime(new Date());
        return sysFileOperateLog;
    }

    /**
     * 保存操作日志
     *
     * @param sysFile     操作的文件
     * @param token     用户不登录操作
     * @param operateType 操作备注
     */
    private void saveRecord(SysFileManage sysFile, String operateType,String token) {
        SysFileManageOperateLog sysFileOperateLog = getSysFileManageOperateLog(sysFile, operateType,token);
        sysFileOperateLogMapper.insert(sysFileOperateLog);
    }

    /**
     * 根据TOKEN获取当前登录用户ID
     * @param token
     * @return
     */
    private String getLoginUserId(String token){
        if(StringUtils.isBlank(token)){
            return "用户未登录操作";
        }else{
            try{
                String loginUserId = UserUtil.getLoginUserId(token);
                if(StringUtils.isBlank(loginUserId)){
                    return "用户登录过期操作";
                }
                return loginUserId;
            }catch (Exception e){
                e.printStackTrace();
                return "用户登录过期操作";
            }
        }
    }

}
