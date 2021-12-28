package com.sofn.fdpi.service.impl;

import com.google.common.collect.Lists;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.Result;
import com.sofn.common.utils.BoolUtils;
import com.sofn.common.utils.DateUtils;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.RedisHelper;
import com.sofn.fdpi.constants.Constants;
import com.sofn.fdpi.enums.CodeTypeEnum;
import com.sofn.fdpi.enums.DefaultAdviceEnum;
import com.sofn.fdpi.mapper.FileManageMapper;
import com.sofn.fdpi.model.*;
import com.sofn.fdpi.service.*;
import com.sofn.fdpi.sysapi.SysRegionApi;
import com.sofn.fdpi.sysapi.WorkApi;
import com.sofn.fdpi.sysapi.bean.SubmitInstanceVo;
import com.sofn.fdpi.util.*;
import com.sofn.fdpi.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service("enterpriseService")
public class EnterpriseServiceImpl implements EnterpriseService {
    @Autowired
    @Lazy
    private TbCompService tbCompService;

    @Autowired
    private TbUsersService tbUsersService;

    @Autowired
    private PapersService papersService;

//    @Autowired
//    private SpeService speService;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Autowired
    private AuditProcessService auditProcessService;

    @Autowired
    private TbDepartmentService tbDepartmentService;

    @Autowired
    private SysRegionApi sysRegionApi;

    @Autowired
    private FileManageMapper fileManageMapper;

    @Autowired
    private RedisHelper redisHelper;

    @Autowired
    private WorkApi workApi;

    //流程id
    private final static String defId = "fdpi_paper_binding:fdpi_paper_binding";

    //业务数据名称
    private final static String idAttrName = "dataId";

    /**
     * 企业注册
     *
     * @param enterpriseForm
     * @return "1":成功；其它：提示异常信息
     */
    @Override
    @Transactional
    public String registerCompany(EnterpriseForm enterpriseForm) {
        enterpriseForm.setCompName(enterpriseForm.getCompName().trim());

        //校验
        String checkingResult = checkingRegisterForm(enterpriseForm);
        if (!"1".equals(checkingResult)) {
            return checkingResult;
        }

        //判断当前企业是否有直属部门
        DepartmentLevelVo deptLevelModel = tbDepartmentService.getRedirectTempId(enterpriseForm.getProvince(), enterpriseForm.getCity(), enterpriseForm.getDistrict());
        if (deptLevelModel == null || StringUtils.isEmpty(deptLevelModel.getSysDeptId())) {
//            RedisCompUtil.deleteCompInCacheForHash(enterpriseForm.getCompName());
//            RedisCompUtil.deleteUserNameInCacheForHash(enterpriseForm.getAccount());
            return "注册失败！该企业无直属部门，请先维护直属部门！";
        }


        String compId = IdUtil.getUUId();
        Date dateNow = new Date();
        //获取当前证书信息,企业中法人是证书中得法人
//        Papers papersInDB = papersService.getById(enterpriseForm.getPapersId());
        //1.保存企业的信息
        TbComp tbComp = assembleTbComp(compId, dateNow, deptLevelModel.getSysDeptId(), enterpriseForm, "");
        //tbComp.setDirectOrgLevel(deptLevelModel.getSysLevel());//直属部门级别

        //2.创建用户账号
        String userId = IdUtil.getUUId();
        TbUsers tbUsers = assembleTbUsers(userId, compId, dateNow, enterpriseForm);

        //3.修改证书中的信息
        List<PapersInRegisterForm> papersFormList = enterpriseForm.getPapersList();
        //组装后的证书列表
        List<Papers> papersList = Lists.newArrayList();
        //组装后的证书图片列表
        List<FileManage> fileManagesList = Lists.newArrayList();
        //证书绑定操作记录列表
        List<AuditProcess> auditProcessList = Lists.newArrayList();
        //流程组件记录列表
        List<SubmitInstanceVo> submitInstanceList = Lists.newArrayList();

        papersFormList.forEach(papersForm -> {
            //组装证书表
            Papers papers = assemblePapersForUpdate(papersForm.getPapersId(), enterpriseForm.getRegion(), compId, dateNow);
            papersList.add(papers);
            //组装证书中的文件
            fileManagesList.addAll(assembleFileManage(papers.getId(), papersForm.getFileList()));
            //组装证书绑定记录表数据
            auditProcessList.add(assembleAuditProcess(papers.getApplyNum(), papersForm.getPapersId(), tbUsers.getId(), enterpriseForm.getCompName(), dateNow));

            //组装流程记录列表
            //是否操作流程组件
            if (Constants.WORKFLOW.equals(BoolUtils.Y)) {
                submitInstanceList.add(submitInstanceVoProcess(papersForm.getPapersId(), enterpriseForm.getCompName()));
            }
        });
//        Papers papers = assemblePapersForUpdate(enterpriseForm.getPapersId(), compId, dateNow);

        //证书绑定记录表数据组装

        //如果后面注册时间比较慢，则进行异步优化。

//        TransactionStatus tStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            //保存企业信息
            tbCompService.save(tbComp);
            //激活营业证书文件
            if (!StringUtils.isEmpty(tbComp.getBusLicenseFileId())) {
                SysFileManageUtil.activationFile(tbComp.getBusLicenseFileId());
            }
            //保存用户信息
            tbUsersService.save(tbUsers);
            //修改证书信息
            papersService.updateBatchById(papersList);
            //papersService.updateById(papers);

            // 保存证书文件
            for (FileManage fileManage : fileManagesList) {
                fileManageMapper.insert(fileManage);
            }

            //激活证书文件
            List<String> idList = fileManagesList.stream().map(FileManage::getId).collect(Collectors.toList());
            SysFileManageUtil.activationFile(String.join(",", idList));

            //新增证书上报流程提交
            //是否操作流程组件
            if (Constants.WORKFLOW.equals(BoolUtils.Y)) {
                for (SubmitInstanceVo submitInstanceVo : submitInstanceList) {
                    Result<String> result = workApi.startChainProcess(submitInstanceVo);
                    if (!Result.CODE.equals(result.getCode())) {
                        throw new SofnException("加入流程失败");
                    }
                }
            } else {
                //新增证书绑定记录
                auditProcessService.saveBatch(auditProcessList);
            }
            //事务提交
//            transactionManager.commit(tStatus);
            return "1";
        } catch (Exception ex) {
            //删除企业注册缓存
//            RedisCompUtil.deleteCompInCacheForHash(enterpriseForm.getCompName());
//            RedisCompUtil.deleteUserNameInCacheForHash(enterpriseForm.getAccount());
            log.error("企业注册异常：" + ex.getMessage());
            //事务回滚
//            transactionManager.rollback(tStatus);
        }
        return "注册失败";
    }

    /**
     * 获取证书下拉列表
     *
     * @param map 证书类型
     * @return List<SelectVo>对象
     */
    @Override
    public List<SelectVo> listPapersForSelect(Map<String, Object> map) {
        return papersService.listPapersForSelect(map);
    }

    @Override
    public Papers getPapersById(String id) {
        return papersService.getPapersById(id);
    }

    /**
     * 通过企业名称获取最近一次证书中企业的信息
     *
     * @param compName 企业名称
     * @return 企业对象
     */
    @Override
    public CompInRegisterVo getCompByCompName(String compName) {
        return papersService.getCompByCompName(compName);
    }

    /**
     * 注册表单中对数据进行校验
     *
     * @param enterpriseForm 注册表单
     * @return "1":通过；其它：则返回提示
     */
    private String checkingRegisterForm(EnterpriseForm enterpriseForm) {

        //注册中证书必须要上传
        if (CollectionUtils.isEmpty(enterpriseForm.getPapersList())) {
            return "许可证信息必须上传！";
        }
        List<PapersInRegisterForm> papersList = enterpriseForm.getPapersList();
        //许可证中证书文件必须上传；
        for (PapersInRegisterForm papers : papersList) {
            if (StringUtils.isEmpty(papers.getPapersId())) {
                return "证书id必须上传！";
            }
            if (CollectionUtils.isEmpty(papers.getPapersSpecList())) {
                return "许可证信息中物种信息必须上传！";
            }
            if (CollectionUtils.isEmpty(papers.getFileList())) {
                return "许可证信息中证书文件必须上传!";
            }
        }

        //如果选择有两个证书，则需要验证两个证书中存在相同物种，来源和数量不相同，则提示“注册失败！”
        if (papersList.size() > 1) {
            //先验证两个证书的类型要不相同。
            PapersInRegisterForm papersOne = papersList.get(0);
            PapersInRegisterForm papersTwo = papersList.get(1);
            if ("1,2".contains(papersOne.getPapersType()) && "1,2".contains(papersTwo.getPapersType())) {
                return "注册时不能选择两个相同证书类型的许可证！";
            }
            if (papersOne.getPapersId().equals(papersTwo.getPapersId())) {
                return "许可证信息不能上传多个相同的证书！";
            }
            //两个证书中的法人和企业地址要相同
            if (!papersOne.getLegal().equals(papersTwo.getLegal()) || !papersOne.getCompAddress().equals(papersTwo.getCompAddress())) {
                return "请确认所选许可证信息是否一致，如果存在差异，请及时与直属渔业主管部门进行联系";
            }
            List<PapersSpecForm> papersOneSpeList = papersOne.getPapersSpecList();
            List<PapersSpecForm> papersTwoSpeList = papersTwo.getPapersSpecList();
            if (papersOneSpeList.size() >= papersTwoSpeList.size()) {
                for (PapersSpecForm oneSpec : papersOneSpeList) {
                    for (PapersSpecForm twoSpec : papersTwoSpeList) {
                        if (oneSpec.getSpecId().equals(twoSpec.getSpecId()) && (!oneSpec.getSource().equals(twoSpec.getSource()) || !oneSpec.getAmount().equals(twoSpec.getAmount()))) {
                            return "注册失败！两个证书中相同物种来源和数量不同！";
                        }
                    }
                }
            } else {
                for (PapersSpecForm twoSpec : papersTwoSpeList) {
                    for (PapersSpecForm oneSpec : papersOneSpeList) {
                        if (oneSpec.getSpecId().equals(twoSpec.getSpecId()) && (!oneSpec.getSource().equals(twoSpec.getSource()) || !oneSpec.getAmount().equals(twoSpec.getAmount()))) {
                            return "注册失败！两个证书中相同物种来源和数量不同！";
                        }
                    }
                }
            }

        }

//        //查看redis缓存,防止redis没有做持久化，数据消息的问题
//        long hLength = 0;
//        hLength = redisHelper.hLength(RedisCompUtil.registerKey);
//        if (hLength == 0) {
//            synchronized (this) {
//                hLength = redisHelper.hLength(RedisCompUtil.registerKey);
//                if (hLength == 0) {
//                    //从数据库中加载数据
//                    tbCompService.loadCompAndUserDataToCache();
//                }
//            }
//        }
//
//        //验证企业注册,本地是否已注册
//        boolean isCompExist = RedisCompUtil.validCompIsHasRegisterAndSaveInCache(enterpriseForm.getCompName());
        boolean isCompExist = tbCompService.validCompIsHasRegisterAndSave(enterpriseForm.getCompName());
        if (isCompExist) {
            return "该企业已注册，不能重复注册！";
        }
//
//        //验证企业账号,本地是否已存在
//        boolean isAccountExist = RedisCompUtil.validUserNameIsHasRegisterAndSaveInCache(enterpriseForm.getAccount());
        boolean isAccountExist = tbUsersService.validUserNameIsHasRegisterAndSave(enterpriseForm.getAccount());
        if (isAccountExist) {
            return "该账号已存在！";
        }
        //验证企业的证书ID是否存在

        Result<String> orgResult = sysRegionApi.checkOrgNameIsExits("", enterpriseForm.getCompName());
        if (!Result.CODE.equals(orgResult.getCode())) {
//            RedisCompUtil.deleteCompInCacheForHash(enterpriseForm.getCompName());
//            RedisCompUtil.deleteUserNameInCacheForHash(enterpriseForm.getAccount());
            return "连接支撑平台有问题！";
        }
        String isExistOrg = orgResult.getData();
        if ("Y".equals(isExistOrg)) {
            //企业已注册，但是账号不存在，则删除账号缓存。
//            RedisCompUtil.deleteUserNameInCacheForHash(enterpriseForm.getAccount());
            return "该企业已注册！";
        }

        //验证账号在支撑平台是否存在
        Result<String> sysUserResult = sysRegionApi.checkUserNameExist("", enterpriseForm.getAccount());
        if (!Result.CODE.equals(sysUserResult.getCode())) {
//            RedisCompUtil.deleteCompInCacheForHash(enterpriseForm.getCompName());
//            RedisCompUtil.deleteUserNameInCacheForHash(enterpriseForm.getAccount());
            return "连接支撑平台有问题！";
        }
        String isUserExist = sysUserResult.getData();
        if ("Y".equals(isUserExist)) {
//            RedisCompUtil.deleteCompInCacheForHash(enterpriseForm.getCompName());
            return "账号已存在！";
        }

        return "1";
    }

    /**
     * 组装企业信息，保存使用
     * wuXY
     * 2020-6-23 15:48:23
     *
     * @param compId         企业主键id
     * @param dateNow        当前时间
     * @param directDeptId   直属部门
     * @param enterpriseForm 注册表单
     * @return tbComp
     */
    private TbComp assembleTbComp(String compId, Date dateNow, String directDeptId, EnterpriseForm enterpriseForm, String legal) {
        TbComp tbComp = new TbComp();
        tbComp.setId(compId);
        tbComp.setCompName(enterpriseForm.getCompName());
        tbComp.setCompProvince(enterpriseForm.getProvince());
        tbComp.setCompCity(enterpriseForm.getCity());
        tbComp.setCompDistrict(enterpriseForm.getDistrict());
        tbComp.setRegionInCh(enterpriseForm.getRegion());
        tbComp.setContactAddress(enterpriseForm.getAddr());
        tbComp.setCompType(enterpriseForm.getCompType());
        tbComp.setPostAddress("");

        tbComp.setLegal(enterpriseForm.getLegal());
//        tbComp.setLegal(legal);
        tbComp.setLinkMan(enterpriseForm.getLinKMan());
        tbComp.setPhone(enterpriseForm.getPhone());
        tbComp.setEmail(enterpriseForm.getEmail());
        //0：注册；1：已审核；2：退回
        tbComp.setCompStatus("0");
        tbComp.setBusLicenseFileId(enterpriseForm.getBusLicenseFileId());
        tbComp.setBusLicenseFileName(enterpriseForm.getBusLicenseFileName());
        tbComp.setBusLicenseFilePath(enterpriseForm.getBusLicenseFilePath());
        tbComp.setCreateTime(dateNow);
        tbComp.setUpdateTime(tbComp.getCreateTime());
        tbComp.setDelFlag("N");
        tbComp.setDireclyId(directDeptId); //直属部门
        return tbComp;
    }

    private String getSequenceNum(String provinceCode) {
        String maxApplyNum = papersService.getTodayMaxApplyNum(
                DateUtils.format(new Date(), "yyyyMMdd") + "0" + provinceCode);
        return StringUtils.hasText(maxApplyNum) ?
                String.format("%06d", (Integer.valueOf(maxApplyNum.substring(13)) + 1)) : null;
    }

    /**
     * 组装用户基本信息
     *
     * @param userId  用户id，主键
     * @param compId  企业id，主键
     * @param dateNow 当前时间
     * @return TbUsers
     */
    private TbUsers assembleTbUsers(String userId, String compId, Date dateNow, EnterpriseForm enterpriseForm) {
        TbUsers tbUsers = new TbUsers();
        tbUsers.setId(userId);
        tbUsers.setAccount(enterpriseForm.getAccount());
        tbUsers.setPassword(enterpriseForm.getPassword());
        tbUsers.setCompId(compId);
        //0:停用;  1:启用
        tbUsers.setUserStatus("0");
        tbUsers.setCreateTime(dateNow);
        tbUsers.setUpdateTime(tbUsers.getCreateTime());
        tbUsers.setDelFlag("Y");
        return tbUsers;
    }

    /**
     * 组装证书信息，修改证书
     * wuXY
     *
     * @param papersId 证书id
     * @param compId   企业id
     * @param dateNow  当前时间
     * @return papers
     */
    private Papers assemblePapersForUpdate(String papersId, String region, String compId, Date dateNow) {
        Papers papers = new Papers();
        //修改证书中的信息
        papers.setId(papersId);
//        //物种id
//        papers.setSpeId(species.getId());
//        //物种数量
//        papers.setIssueNum(enterpriseForm.getIssueNum());
        //0：省级新建；1：绑定未上报；2：上报；3：初审通过；4;初审退回；5：复审通过；6：复审退回
        papers.setParStatus("2");
        papers.setCompId(compId);
        //0：注册绑定；1：证书绑定
        papers.setSource("0");
        String provinceCode = CodeUtil.getProvinceCode(region.split("-")[0]);
        papers.setApplyNum(CodeUtil.getApplyCode(provinceCode,
                CodeTypeEnum.PAPER_CHANGE.getKey(), this.getSequenceNum(provinceCode)));
        papers.setUpdateTime(dateNow);
        papers.setDelFlag("N");
        return papers;
    }

    /**
     * 组装文件列表，证书文件列表
     * wuXY
     *
     * @param papersId   证书编号
     * @param fileVoList 上传的文件列表
     * @return List<FileManage>
     */
    private List<FileManage> assembleFileManage(String papersId, List<FileManageVo> fileVoList) {
        List<FileManage> fileList = Lists.newArrayList();
        if (CollectionUtils.isEmpty(fileVoList)) {
            return fileList;
        } else {
            fileVoList.stream().forEach(fileManageVo -> {
                FileManage fileManage = fileManageVo.getFileManage(fileManageVo);
                fileManage.setFileSource("PAPERS");
                fileManage.setFileSourceId(papersId);
                fileManage.setFileStatus("1");
                fileList.add(fileManage);
            });
            return fileList;
        }
    }

    /**
     * 组装证书绑定操作记录日志
     * wuXY
     *
     * @param papersId    证书id
     * @param userId      用户id
     * @param userAccount 用户账号
     * @param dateNow     当前时间
     * @return AuditProcess
     */
    private AuditProcess assembleAuditProcess(String applyNum, String papersId, String userId,
                                              String userAccount, Date dateNow) {
        AuditProcess auditProcess = new AuditProcess();
        auditProcess.setId(IdUtil.getUUId());
        auditProcess.setPapersId(papersId);
        auditProcess.setApplyNum(applyNum);
        auditProcess.setAdvice((DefaultAdviceEnum.REPORT_DEFAULT_ADVICE.getMsg())); //上报
        auditProcess.setStatus((DefaultAdviceEnum.REPORT_DEFAULT_ADVICE.getCode()));
        auditProcess.setPerson(userId);
        auditProcess.setPersonName(userAccount);
        auditProcess.setConTime(dateNow);
        return auditProcess;
    }

    private SubmitInstanceVo submitInstanceVoProcess(String papersId, String personName) {
        SubmitInstanceVo submitInstanceVo = new SubmitInstanceVo();
        submitInstanceVo.setDefId(defId);
        submitInstanceVo.setIdAttrName(idAttrName);
        submitInstanceVo.setIdAttrValue(papersId);
        String[] keys = {"status", "advice", "personName"};
        String[] values = {DefaultAdviceEnum.REPORT_DEFAULT_ADVICE.getCode(), DefaultAdviceEnum.REPORT_DEFAULT_ADVICE.getMsg(), personName};
        Map<String, Object> params = MapUtil.getParams(keys, values);
        submitInstanceVo.setParams(params);
        return submitInstanceVo;
    }
}
