package com.sofn.fdpi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.Result;
import com.sofn.common.service.BaseService;
import com.sofn.common.utils.*;
import com.sofn.fdpi.constants.Constants;
import com.sofn.fdpi.enums.*;
import com.sofn.fdpi.mapper.*;
import com.sofn.fdpi.model.ChangeRecordProcess;
import com.sofn.fdpi.model.*;
import com.sofn.fdpi.service.TbCompService;
import com.sofn.fdpi.service.TbDepartmentService;
import com.sofn.fdpi.service.TransferService;
import com.sofn.fdpi.sysapi.SysRegionApi;
import com.sofn.fdpi.sysapi.WorkApi;
import com.sofn.fdpi.sysapi.bean.*;
import com.sofn.fdpi.util.*;
import com.sofn.fdpi.vo.*;
import com.xiaoleilu.hutool.collection.CollectionUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Auther: wenjunyun
 * @Date: 2019/12/17 14:44
 */
@Slf4j
@Service
public class TransferServiceImpl extends BaseService<ChangeRecordMapper, ChangeRecord> implements TransferService {


    @Autowired(required = false)
    ChangeRecordMapper changeRecordMapper;
    @Autowired(required = false)
    ChangeRecordProcessMapper changeRecordProcessMapper;
    @Autowired(required = false)
    TransferMapper transferMapper;
    @Autowired(required = false)
    private SignboardChangeRecordMapper scrMapper;

    @Autowired(required = false)
    SignboardMapper signboardMapper;
    @Resource
    private TbDepartmentService tbDepartmentService;
    @Autowired(required = false)
    private SysRegionApi sysRegionApi;

    @Autowired(required = false)
    private WorkApi workApi;

    @Resource
    private TbCompService tbCompService;

    private static final String DEF_ID = "fdpichange:fdpi_tran_copy";
    private static final String ID_ATTR_NAME = "dataId";
    private static final String TARGET_ACT_DEFID = "fdpi_add_report";

    @Override
    public ChangeRecordCompanyVO getCompanyByIdOrName(Map map) {
        List<FileManageVo> list = transferMapper.listPapersFilesInfo(map);
        ChangeRecordCompanyVO crvo = changeRecordMapper.getCompanyByIdOrName(map).get(0);
        crvo.setListPapersFiles(list);
        return crvo;
    }

    @Override
    public List<ChangeRecordCompanyVO> listCompanyByIdOrName(Map map) {
        return changeRecordMapper.listCompanyByIdOrName(map);
    }

    @Override
    public List<CompanySignVO> listSignByCompanyId(String companyId) {
        Map map = new HashMap();
        map.put("companyId", companyId);
        return transferMapper.listSignByCompanyId(map);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveTransfer(TransferVO transferVO, ChangeRecordProcess changeRecordProcess) {
        RedisUserUtil.validReSubmit("fdpi_transfer_save");
        if (StringUtils.isEmpty(transferVO.getReduceCompanyVO().getCompanyId())) {
            throw new SofnException("减少方企业不能为空");
        }
        transferMapper.saveTransfer(transferVO);
        Map map = new HashMap();
        map.put("transferId", transferVO.getId());
        transferMapper.deleteTransferSpecies(map);
        for (SpeciesSelect speciesSelect : transferVO.getSpeciesSelectList()) {
            map.put("id", IdUtil.getUUId());
            map.put("speName", speciesSelect.getSpeciesName());
            map.put("speNum", speciesSelect.getSpeciesNum());
            map.put("speId", speciesSelect.getSpeciesId());
            transferMapper.saveTransferSpecies(map);
        }
        if (transferVO.getSignId() != null && !transferVO.getSignId().equals("")) {
            String[] signes = transferVO.getSignId().split(",");
            map.put("companyId", transferVO.getReduceCompanyVO().getCompanyId());
            for (int i = 0, j = signes.length; i < j; i++) {
                map.put("code", signes[i]);
                map.put("completeCode", signes[i]);
                map.put("signboardStatus", transferVO.getId());
                transferMapper.updateSign(map);
                map.put("transferId", transferVO.getId());
                map.put("signCode", signes[i]);
                transferMapper.saveTransferSign(map);
            }
        }

        SysFileManageForm sf = new SysFileManageForm();
        sf.setFileName(transferVO.getAddContractName());
        sf.setIds(transferVO.getAddContractId());
        sf.setSystemId("fdpi");
        sf.setRemark("物种转移合同-增加方");
        sf.setFileState("Y");
        sysRegionApi.activationFile(sf);

        if (TranferStatusEnum.REPORT.getKey().equals(transferVO.getTransferStatus())) {
//           如果是上报状态，那么检查物种转移流程中流程是否 有该物种的申请正在执行中
            checkOnProcess(transferVO);
            transferMapper.saveTransferProcess(changeRecordProcess);
            if (Constants.WORKFLOW.equals(BoolUtils.Y)) {
                Map mapforaudit = new HashMap();
                mapforaudit.put("transferId", transferVO.getId());
                TransferVO transferId = this.getTransferVO(mapforaudit);
                String[] keys = {"status", "person"};
                Object[] vals = {transferId.getTransferStatus(), UserUtil.getLoginUser().getNickname(),};
                Result<String> stringResult = workApi.startChainProcess(new SubmitInstanceVo(DEF_ID, ID_ATTR_NAME,
                        transferVO.getId(), MapUtil.getParams(keys, vals)));
                if (!Result.CODE.equals(stringResult.getCode())) {
                    throw new SofnException("加入流程失败");
                }
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteTransfer(TransferVO transferVO, ChangeRecordProcess changeRecordProcess) {
        Map map = new HashMap();
        map.put("transferId", transferVO.getId());
        List<TransferSign> signList = transferMapper.listTransferSign(map);
        TransferVO temp_old = transferMapper.getTransferVO(map);
        if (signList != null && signList.size() > 0) {
            map.put("companyId", temp_old.getReduceCompanyVO().getCompanyId());
            for (int i = 0, j = signList.size(); i < j; i++) {
                map.put("code", signList.get(i).getSignCode());
                map.put("completeCode", signList.get(i).getSignCode());
                map.put("signboardStatus", "N");
                transferMapper.updateSign(map);
            }
            transferMapper.deleteTransferSign(map);
        }
        transferMapper.updateTransfer(transferVO);
        transferMapper.saveTransferProcess(changeRecordProcess);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateTransfer(TransferVO transferVO, ChangeRecordProcess changeRecordProcess) {
        String oldId = transferVO.getId();
        RedisUserUtil.validReSubmit("fdpi_transfer_update", oldId);
        Map map = new HashMap();

        //编辑或者更新状态
        if (StringUtils.isNotBlank(oldId)) {
            Map mapforaudit = new HashMap();
            mapforaudit.put("transferId", oldId);

            //更新转移表
            if (!(transferVO.getTransferStatus().equals("2") || transferVO.getTransferStatus().equals("4")
                    || transferVO.getTransferStatus().equals("6"))) {
                transferVO.setOpnion("");
            }
            if (transferVO.getTransferStatus().equals("5")) {
                transferVO.setReduceUserId(UserUtil.getLoginUserId());
            }

            map.put("transferId", oldId);

            //如果是还是在保存状态则还要更新转移物种表，此处跟减少物种企业上传文件的时候更新区别开，需要判断更新时候的状态
            if (transferVO.getTransferStatus().equals(TranferStatusEnum.KEEP.getKey()) ||
                    (TranferStatusEnum.REPORT.getKey().equals(transferVO.getTransferStatus())
                            && transferVO.getSpeciesSelectList() != null)) {
                transferMapper.deleteTransferSpecies(map);
                for (SpeciesSelect speciesSelect : transferVO.getSpeciesSelectList()) {
                    map.put("id", IdUtil.getUUId());
                    map.put("speName", speciesSelect.getSpeciesName());
                    map.put("speNum", speciesSelect.getSpeciesNum());
                    map.put("speId", speciesSelect.getSpeciesId());
                    transferMapper.saveTransferSpecies(map);
                }
            }
            TransferVO temp_old = transferMapper.getTransferVO(map);
            if (!CollectionUtil.isEmpty(transferVO.getSpeciesSelectList())) {
                //清空旧标识锁定，增加新的标识锁定
                if (transferVO.getTransferStatus().equals(TranferStatusEnum.KEEP.getKey()) ||
                        transferVO.getTransferStatus().equals(TranferStatusEnum.REPORT.getKey())) {
                    if (temp_old.getAddContractId() != null && transferVO.getAddContractId() != null
                            && !temp_old.getAddContractId().equals(transferVO.getAddContractId())) {
                        SysFileManageForm sf = new SysFileManageForm();
                        sf.setFileName(transferVO.getAddContractName());
                        sf.setIds(transferVO.getAddContractId());
                        sf.setSystemId("fdpi");
                        sf.setRemark("物种转移合同-增加方");
                        sf.setFileState("Y");
                        sysRegionApi.activationFile(sf);
                        sysRegionApi.delFile(temp_old.getAddContractId());
                    }

                    List<TransferSign> signList = transferMapper.listTransferSign(map);
                    if (signList != null && signList.size() > 0) {
                        map.put("companyId", temp_old.getReduceCompanyVO().getCompanyId());
                        for (int i = 0, j = signList.size(); i < j; i++) {
                            map.put("code", signList.get(i).getSignCode());
                            map.put("completeCode", signList.get(i).getSignCode());
                            map.put("signboardStatus", "N");
                            transferMapper.updateSign(map);
                        }
                        transferMapper.deleteTransferSign(map);
                    }
                    if (transferVO.getSignId() != null && !transferVO.getSignId().equals("")) {
                        String[] signes = transferVO.getSignId().split(",");
                        map.put("companyId", transferVO.getReduceCompanyVO().getCompanyId());
                        for (int i = 0, j = signes.length; i < j; i++) {
                            map.put("code", signes[i]);
                            map.put("completeCode", signes[i]);
                            map.put("signboardStatus", oldId);
                            transferMapper.updateSign(map);
                            map.put("signCode", signes[i]);
                            transferMapper.saveTransferSign(map);
                        }
                    }
                }

            }
            if (TranferStatusEnum.REPORT.getKey().equals(transferVO.getTransferStatus())) {
                transferVO.setIsReport("Y");
                if (StringUtils.isBlank(temp_old.getApplyCode())) {
                    String provinceCode = CodeUtil.getProvinceCode(tbCompService.getCombById(UserUtil.getLoginUserOrganizationId()).getRegionInCh().split("-")[0]);
                    transferVO.setApplyCode(CodeUtil.getApplyCode(provinceCode,
                            CodeTypeEnum.SPE_TRANSFER.getKey(), this.getSequenceNum(provinceCode)));
                }
            }

            transferMapper.updateTransfer(transferVO);

//            校验物种审核流程
            checkOnProcess(transferVO);

            //物种转移记录流水，根据不同的状态，记录不同的操作
            changeRecordProcess.setStatus(transferVO.getTransferStatus());
            changeRecordProcess.setChangeRecordId(transferVO.getId());
            changeRecordProcess.setAdvice(transferVO.getOpnion());
            TransferVO transferVO1 = this.getTransferVO(mapforaudit);
            String transferId = transferVO1.getId();
            String transferStatus = transferVO1.getTransferStatus();
            if (transferVO.getTransferStatus().equals(TranferStatusEnum.KEEP.getKey())) {
                changeRecordProcess.setContent("保存");
            } else if (transferVO.getTransferStatus().equals(TranferStatusEnum.REPORT.getKey())) {
                changeRecordProcess.setContent("上报");
            } else if (transferVO.getTransferStatus().equals(TranferStatusEnum.FIRST_RETURN.getKey())) {
                changeRecordProcess.setContent("增加企业直属退回");
            } else if (transferVO.getTransferStatus().equals(TranferStatusEnum.FIRST_PASS.getKey())) {
                changeRecordProcess.setContent("增加企业直属通过");
            } else if (transferVO.getTransferStatus().equals(TranferStatusEnum.SECOND_RETURN.getKey())) {
                changeRecordProcess.setContent("减少企业审核退回");
            } else if (transferVO.getTransferStatus().equals(TranferStatusEnum.SECOND_PASS.getKey())) {
                changeRecordProcess.setContent("减少企业审核通过");
            } else if (transferVO.getTransferStatus().equals(TranferStatusEnum.THIRD_RETURN.getKey())) {
                changeRecordProcess.setContent("减少企业直属审核退回");
            } else if (transferVO.getTransferStatus().equals(TranferStatusEnum.THIRD_PASS.getKey())) {
                changeRecordProcess.setContent("减少企业直属审核通过");
            } else if (transferVO.getTransferStatus().equals("8")) {
                changeRecordProcess.setContent("结束");
            }
            changeRecordProcess.setConTime(new Date());
            transferMapper.saveTransferProcess(changeRecordProcess);
            TransferVO temp = transferMapper.getTransferVO(map);
            //如果更新状态是11的话，还要涉及到修改增加企业\减少企业的物种数目，以及记录流水
            if (transferVO.getTransferStatus().equals(TranferStatusEnum.THIRD_PASS.getKey())) {
                List<SpeciesSelect> list = temp.getSpeciesSelectList();
                for (SpeciesSelect ss : list) {
                    //装载增加企业库存流水map
                    map.clear();
                    map.put("id", IdUtil.getUUId());
                    map.put("companyId", temp.getAddCompanyVO().getCompanyId());
                    map.put("speciesId", ss.getSpeciesId());

                    //查询增加企业该物种以前的库存
                    SpeciesSelect speciesSelect = transferMapper.getSpeciesSelect(map);

                    map.put("speNum", transferVO.getId());
                    map.put("billType", ComeStockFlowEnum.INCREASE.getCode());
                    map.put("importMark", "入库");
                    map.put("otherComName", temp.getReduceCompanyVO().getCompName());

                    map.put("changeNum", ss.getSpeciesNum());
                    map.put("changeDate", new Date());
                    map.put("lastChangeUserId", temp.getCreateUserId());


                    if (speciesSelect != null) {
                        //装载增加企业库存更新map
                        map.put("createUserId", temp.getCreateUserId());
                        changeRecordProcessMapper.changeCompanySpecies(map);
                        map.put("beforeNum", speciesSelect.getSpeciesNum());
                        map.put("afterNum",
                                Integer.parseInt(speciesSelect.getSpeciesNum()) + Integer.parseInt(ss.getSpeciesNum()));
                        changeRecordProcessMapper.saveCompanySpeciesProcess(map);
                    } else if (temp.getIsHavePaper().equals("1")) {
                        map.put("uuid", IdUtil.getUUId());
                        changeRecordProcessMapper.insertCompanySpecies(map);
                        map.put("beforeNum", 0);
                        map.put("afterNum", 0 + Integer.parseInt(ss.getSpeciesNum()));
                        changeRecordProcessMapper.saveCompanySpeciesProcess(map);
                    } else {
                        throw new SofnException("该笔交易不是无证书转移，增加企业必须具备该交易下物种："
                                + ss.getSpeciesName() + "的证书!");
                    }


                    /**
                     * 减少企业的处理
                     */

                    //查询减少企业该物种以前的库存
                    map.put("companyId", temp.getReduceCompanyVO().getCompanyId());
                    map.put("speciesId", ss.getSpeciesId());
                    speciesSelect = transferMapper.getSpeciesSelect(map);

                    //装载减少企业库存流水map
                    map.clear();
                    map.put("id", IdUtil.getUUId());
                    map.put("speNum", transferVO.getId());
                    map.put("billType", ComeStockFlowEnum.REDUCE.getCode());
                    map.put("otherComName", temp.getAddCompanyVO().getCompName());
                    map.put("importMark", "出库");
                    map.put("beforeNum", speciesSelect.getSpeciesNum());
                    map.put("afterNum", Integer.parseInt(speciesSelect.getSpeciesNum())
                            - Integer.parseInt(ss.getSpeciesNum()));
                    map.put("changeDate", new Date());
                    map.put("lastChangeUserId", temp.getReduceUserId());
                    map.put("changeNum", "-" + ss.getSpeciesNum());
                    map.put("companyId", temp.getReduceCompanyVO().getCompanyId());
                    map.put("speciesId", ss.getSpeciesId());
                    changeRecordProcessMapper.saveCompanySpeciesProcess(map);


                    if (Integer.parseInt(speciesSelect.getSpeciesNum()) - Integer.parseInt(ss.getSpeciesNum()) < 0) {
                        throw new SofnException("减少企业的库存不足！");
                    }

                    //装载减少企业库存更新map
                    map.put("createUserId", temp.getReduceUserId());
                    map.put("changeNum", Integer.parseInt("-" + ss.getSpeciesNum()));
                    changeRecordProcessMapper.changeCompanySpecies(map);
                }
                map.put("transferId", transferVO.getId());
                List<TransferSign> listSign = transferMapper.listTransferSign(map);
                if (listSign != null && listSign.size() > 0) {
                    map.put("companyId", temp.getAddCompanyVO().getCompanyId());
                    for (int i = 0, j = listSign.size(); i < j; i++) {
                        map.put("code", listSign.get(i).getSignCode());
                        map.put("completeCode", listSign.get(i).getSignCode());
                        map.put("signboardStatus", "N");
                        map.put("delFlag", BoolUtils.N);
                        //根据标识完整编码查询标识表，数据用于装载标识流水表的对象填充-减少企业
                        List<Signboard> listSignbord = signboardMapper.listByParams(map);
                        Signboard signboard = listSignbord.get(0);
                        SignboardChangeRecord entity_reduce = new SignboardChangeRecord();
                        entity_reduce.setSignboardId(signboard.getId());
                        entity_reduce.setSpeId(signboard.getSpeId());
                        entity_reduce.setSpeName(signboard.getSpeName());
                        entity_reduce.setCompId(temp.getReduceCompanyVO().getCompanyId());
                        entity_reduce.setCompName(temp.getReduceCompanyVO().getCompName());
                        entity_reduce.setCode(signboard.getCode());
                        entity_reduce.setStatus("3");
                        entity_reduce.setChangeTime(new Date());
                        entity_reduce.setRemark("物种转移终审通过后标识归属减少企业");
                        entity_reduce.setId(IdUtil.getUUId());
                        scrMapper.insert(entity_reduce);
                        //根据标识完整编码查询标识表，数据用于装载标识流水表的对象填充-增加企业
                        SignboardChangeRecord entity_add = new SignboardChangeRecord();
                        entity_add.setSignboardId(signboard.getId());
                        entity_add.setSpeId(signboard.getSpeId());
                        entity_add.setSpeName(signboard.getSpeName());
                        entity_add.setCompId(temp.getAddCompanyVO().getCompanyId());
                        entity_add.setCompName(temp.getAddCompanyVO().getCompName());
                        entity_add.setCode(signboard.getCode());
                        entity_add.setStatus("4");
                        entity_add.setChangeTime(new Date());
                        entity_add.setRemark("物种转移终审通过后标识归属增加企业修改");
                        entity_add.setId(IdUtil.getUUId());
                        scrMapper.insert(entity_add);
                        //修改标识归属的企业ID
                        transferMapper.updateSign(map);
                    }
                }
            }
            if (Constants.WORKFLOW.equals(BoolUtils.Y)) {
                String status = transferVO.getTransferStatus();
                if (TranferStatusEnum.REPORT.getKey().equals(status)) {
                    String[] keys = {"status", "person"};
                    Object[] vals = {transferStatus, UserUtil.getLoginUser().getNickname()};
                    Result<String> stringResult = workApi.startChainProcess(new SubmitInstanceVo(DEF_ID, ID_ATTR_NAME,
                            transferVO.getId(), MapUtil.getParams(keys, vals)));
                    if (!Result.CODE.equals(stringResult.getCode())) {
                        throw new SofnException("加入流程失败");
                    }
                } else if (TranferStatusEnum.FIRST_RETURN.getKey().equals(status) ||
                        TranferStatusEnum.SECOND_RETURN.getKey().equals(status) ||
                        TranferStatusEnum.THIRD_RETURN.getKey().equals(status)) {
                    String[] keys = {"status", "person", "opinion"};
                    Object[] vals = {transferStatus, SysOwnOrgUtil.getOrganizationName(),
                            transferVO.getOpnion()};
                    Result<String> stringResult = workApi.backWorkItem(BackWorkItemForm.getInstanceForm(DEF_ID, ID_ATTR_NAME, transferId,
                            TARGET_ACT_DEFID, MapUtil.getParams(keys, vals)));
                    if (!Result.CODE.equals(stringResult.getCode())) {
                        throw new SofnException("加入流程失败");
                    }
                } else if (TranferStatusEnum.FIRST_PASS.getKey().equals(status) ||
                        TranferStatusEnum.SECOND_PASS.getKey().equals(status) ||
                        TranferStatusEnum.THIRD_PASS.getKey().equals(status)) {
                    String[] keys = {"status", "person"};
                    Object[] vals = {transferStatus, SysOwnOrgUtil.getOrganizationName()};
                    Result<String> stringResult = workApi.completeWorkItem(new SubmitInstanceVo(DEF_ID,
                            ID_ATTR_NAME, transferVO.getId(), MapUtil.getParams(keys, vals)));
                    if (!Result.CODE.equals(stringResult.getCode())) {
                        throw new SofnException("加入流程失败");
                    }
                }
            }
        } else {
            //没有保存过。直接点击上报
            transferVO.setTransferStatus("1");
            if (transferVO.getIsHavePaper() == null || transferVO.getIsHavePaper().equals("")) {
                throw new SofnException("有无证书标识参数未传递！");
            }
            String id = IdUtil.getUUId();
            transferVO.setId(id);
            transferVO.setCreateTime(new Date());
            transferVO.setCreateUserId(UserUtil.getLoginUserId());
            transferVO.setDelFlag("N");
            transferVO.setOpnion("");
            if (StringUtils.isEmpty(transferVO.getReduceCompanyVO().getCompanyId())) {
                throw new SofnException("减少方企业不能为空");
            }
            if (TranferStatusEnum.REPORT.getKey().equals(transferVO.getTransferStatus())) {
                transferVO.setIsReport("Y");
                if (StringUtils.isBlank(transferVO.getApplyCode())) {
                    String provinceCode = CodeUtil.getProvinceCode(tbCompService.getCombById(UserUtil.getLoginUserOrganizationId()).getRegionInCh().split("-")[0]);
                    transferVO.setApplyCode(CodeUtil.getApplyCode(provinceCode,
                            CodeTypeEnum.SPE_TRANSFER.getKey(), this.getSequenceNum(provinceCode)));
                }
            }
            transferMapper.saveTransfer(transferVO);

            changeRecordProcess.setChangeRecordId(id);
            changeRecordProcess.setStatus("1");
            changeRecordProcess.setAdvice("");
            changeRecordProcess.setContent("上报");
            transferMapper.saveTransferProcess(changeRecordProcess);

            map.clear();
            map.put("transferId", transferVO.getId());
            transferMapper.deleteTransferSpecies(map);
            for (SpeciesSelect speciesSelect : transferVO.getSpeciesSelectList()) {
                map.put("id", IdUtil.getUUId());
                map.put("speName", speciesSelect.getSpeciesName());
                map.put("speNum", speciesSelect.getSpeciesNum());
                map.put("speId", speciesSelect.getSpeciesId());
                transferMapper.saveTransferSpecies(map);
            }

            if (transferVO.getSignId() != null && !transferVO.getSignId().equals("")) {
                String[] signes = transferVO.getSignId().split(",");
                map.put("companyId", transferVO.getReduceCompanyVO().getCompanyId());
                for (int i = 0, j = signes.length; i < j; i++) {
                    map.put("code", signes[i]);
                    map.put("completeCode", signes[i]);
                    map.put("signboardStatus", transferVO.getId());
                    transferMapper.updateSign(map);
                    map.put("signCode", signes[i]);
                    transferMapper.saveTransferSign(map);
                }
            }

            SysFileManageForm sf = new SysFileManageForm();
            sf.setFileName(transferVO.getAddContractName());
            sf.setIds(transferVO.getAddContractId());
            sf.setSystemId("fdpi");
            sf.setRemark("物种转移合同-增加方");
            sf.setFileState("Y");
            sysRegionApi.activationFile(sf);

            //校验是否转移物种在流程中
            checkOnProcess(transferVO);
            if (Constants.WORKFLOW.equals(BoolUtils.Y)) {
                Map mapforaudit = new HashMap();
                mapforaudit.put("transferId", transferVO.getId());
                TransferVO transferId = this.getTransferVO(mapforaudit);
                String[] keys = {"status", "person"};
                Object[] vals = {transferId.getTransferStatus(), UserUtil.getLoginUser().getNickname()};
                Result<String> stringResult = workApi.startChainProcess(new SubmitInstanceVo(DEF_ID, ID_ATTR_NAME,
                        transferVO.getId(), MapUtil.getParams(keys, vals)));
                if (!Result.CODE.equals(stringResult.getCode())) {
                    throw new SofnException("加入流程失败");
                }
            }
        }
    }

    private String getSequenceNum(String provinceCode) {
        String maxApplyNum = transferMapper.getTodayMaxApplyNum(
                DateUtils.format(new Date(), "yyyyMMdd") + "0" + provinceCode);
        return org.springframework.util.StringUtils.hasText(maxApplyNum) ?
                String.format("%06d", (Integer.valueOf(maxApplyNum.substring(13)) + 1)) : null;
    }

    @Override
    public PageUtils<TransferVO> listTransferVO(Map map, Integer pageNo, Integer pageSize) {
//        当前直属部门id
        PageHelper.offsetPage(pageNo, pageSize);
        List<TransferVO> listTransferVOes = transferMapper.listTransferVO(map);
        if ("3".equals(map.get("queryType"))) {
            String organizationId = UserUtil.getLoginUser().getOrganizationId();
            listTransferVOes.forEach(o -> {
//            ---如果状态是增加企业的直属审核
                if (organizationId.equals(o.getAddCompanyVO().getDireclyId())
                        && !organizationId.equals(o.getReduceCompanyVO().getDireclyId())) {
                    o.setCompName(o.getAddCompanyVO().getCompName());
                    o.setTransferType("转移增加");
                } else if (!organizationId.equals(o.getAddCompanyVO().getDireclyId())
                        && organizationId.equals(o.getReduceCompanyVO().getDireclyId())) {
                    o.setCompName(o.getReduceCompanyVO().getCompName());
                    o.setTransferType("转移减少");
                } else if (organizationId.equals(o.getAddCompanyVO().getDireclyId())
                        && organizationId.equals(o.getReduceCompanyVO().getDireclyId())) {
                    if (Integer.parseInt(o.getTransferStatus()) <= 2) {
                        o.setCompName(o.getAddCompanyVO().getCompName());
                        o.setTransferType("转移增加");
                    } else {
                        o.setCompName(o.getReduceCompanyVO().getCompName());
                        o.setTransferType("转移减少");
                    }
                }
                if ("1".equals(o.getTransferStatus()) && organizationId.equals(o.getAddCompanyVO().getDireclyId())) {
                    o.setCanAudit(true);
                } else if ("5".equals(o.getTransferStatus()) && organizationId.equals(o.getReduceCompanyVO().getDireclyId())) {
                    o.setCanAudit(true);
                } else {
                    o.setCanAudit(false);
                }
            });
        }
        //如果当前是企业查询，则根据条件决定是否显示撤回按钮
        if ("1".equals(map.get("queryType"))) {
            listTransferVOes.forEach(vo -> {
                if (vo.getTransferStatus().equals(TranferStatusEnum.REPORT.getKey())) {
                    vo.setIsShowCancel(true);
                }
            });
        }
        PageInfo<TransferVO> listTransferVOesPageInfo = new PageInfo<TransferVO>(listTransferVOes);
        listTransferVOesPageInfo.setList(listTransferVOes);
        return PageUtils.getPageUtils(listTransferVOesPageInfo);
    }

    /**
     * 完善权限查询参数
     */
    private void perfectParams(Map<String, Object> params) {
        OrganizationInfo orgInfo = SysOwnOrgUtil.getOrgInfo();
        //验证用户机构配置是否满足要求
        RedisUserUtil.validLoginUser(orgInfo);
        String id = orgInfo.getId();
        String thirdOrg = orgInfo.getThirdOrg();
        String regionLastCode = orgInfo.getRegionLastCode();
        // 第三方机构
        if (BoolUtils.N.equals(thirdOrg)) {
            params.put("compId", id);
        } else {
            String organizationLevel = orgInfo.getOrganizationLevel();
            params.put("organizationLevel", organizationLevel);
            params.put("regionLastCode", regionLastCode);
        }
    }

    @Override
    public PageUtils<TransferVO> listTransferVO2(Map map, Integer pageNo, Integer pageSize) {
        this.perfectParams(map);
        PageHelper.offsetPage(pageNo, pageSize);
        List<TransferVO> listTransferVOes = transferMapper.listTransferVO2(map);
        if ("3".equals(map.get("queryType"))) {
            this.handleDetail(listTransferVOes, map.get("regionLastCode").toString());
        }
        //如果当前是企业查询，则根据条件决定是否显示撤回按钮
        else if ("1".equals(map.get("queryType"))) {
            listTransferVOes.forEach(vo -> {
                if (vo.getTransferStatus().equals(TranferStatusEnum.REPORT.getKey())) {
                    vo.setIsShowCancel(true);
                }
            });
        }
        PageInfo<TransferVO> listTransferVOesPageInfo = new PageInfo<TransferVO>(listTransferVOes);
        listTransferVOesPageInfo.setList(listTransferVOes);
        return PageUtils.getPageUtils(listTransferVOesPageInfo);

    }

    private void handleDetail(List<TransferVO> listTransferVOes, String regionLastCode) {
        Set<String> auths = tbDepartmentService.listAllAuth(DepartmentTypeEnum.SIGNBOARD.getKey());
        if (!CollectionUtils.isEmpty(listTransferVOes)) {
            for (TransferVO vo : listTransferVOes) {
                //获取增加、减少企业直属
                String[] directlys = this.setCompNameAndType(auths, vo, regionLastCode);
                vo.setCanAudit(this.getCanAudit(auths, vo, regionLastCode, directlys));
            }
        }

    }

    private String[] setCompNameAndType(Set<String> auths, TransferVO vo, String regionLastCode) {
        //增加企业信息
        ChangeRecordCompanyVO addComp = vo.getAddCompanyVO();
        String addProvince = addComp.getCompProvince();
        String addCity = addComp.getCompCity();
        String addDistrict = addComp.getCompDistrict();
        //减少企业信息
        ChangeRecordCompanyVO reduceComp = vo.getReduceCompanyVO();
        String reduceProvince = reduceComp.getCompProvince();
        String reduceCity = reduceComp.getCompCity();
        String reduceDistrict = reduceComp.getCompDistrict();
        //获取增加企业直属
        String addDirectly = auths.contains(addDistrict) ? addDistrict :
                auths.contains(addCity) ? addCity : auths.contains(addProvince) ? addProvince : "";
        //获取减少企业直属
        String reduceDirectly = auths.contains(reduceDistrict) ? reduceDistrict :
                auths.contains(reduceCity) ? reduceCity : auths.contains(reduceProvince) ? reduceProvince : "";
        if (regionLastCode.equals(addDirectly)) {
            vo.setCompName(addComp.getCompName());
            vo.setTransferType("转移增加");
        } else if (regionLastCode.equals(reduceDirectly)) {
            vo.setCompName(reduceComp.getCompName());
            vo.setTransferType("转移减少");
        }
        return new String[]{addDirectly, reduceDirectly};

    }

    private Boolean getCanAudit(Set<String> auths, TransferVO vo, String regionLastCode, String[] directlys) {
        String transferStatus = vo.getTransferStatus();
        //只有状态在上报或者减少企业确认通过状态情况才能审核
        if (!TranferStatusEnum.REPORT.getKey().equals(transferStatus) &&
                !TranferStatusEnum.SECOND_PASS.getKey().equals(transferStatus)) {
            return false;
        }
        //企业帐号没有权限，直接返回false
        if (CollectionUtils.isEmpty(auths)) {
            return false;
        }
        //企业上报状态，判断能否审核增加企业提交
        if (TranferStatusEnum.REPORT.getKey().equals(transferStatus)) {
            if (directlys[0].equals(regionLastCode)) {
                return true;
            }
        }
        //减少企业确认通过状态,判断能否审核减少企业确认
        else if (TranferStatusEnum.SECOND_PASS.getKey().equals(transferStatus)) {
            if (directlys[1].equals(regionLastCode)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public TransferVO getTransferVO(Map map) {
        TransferVO tv = transferMapper.getTransferVO(map);
        List<TransferSign> signList = transferMapper.listTransferSign(map);
        if (signList != null && signList.size() > 0) {
            String signString = "";
            for (TransferSign sign : signList) {
                signString += sign.getSignCode() + ",";
            }
            signString = signString.substring(0, signString.length() - 1);
            tv.setSignId(signString);
        }
        map.put("companyId", tv.getAddCompanyVO().getCompanyId());
        List<FileManageVo> listAdd = transferMapper.listPapersFilesInfo(map);
        tv.getAddCompanyVO().setListPapersFiles(listAdd);
        map.put("companyId", tv.getReduceCompanyVO().getCompanyId());
        List<FileManageVo> listReduce = transferMapper.listPapersFilesInfo(map);
        tv.getReduceCompanyVO().setListPapersFiles(listReduce);
        return tv;
    }

    @Override
    public PageUtils<TransferProcessVO> listTransferProcessVO(Map<String, Object> map, Integer pageNo, Integer pageSize) {
        PageHelper.offsetPage(pageNo, pageSize);
        List<TransferProcessVO> list = transferMapper.listTransferProcessVO(map);
        PageInfo<TransferProcessVO> listPageInfo = new PageInfo<TransferProcessVO>(list);
        listPageInfo.setList(list);
        return PageUtils.getPageUtils(listPageInfo);
    }

    @Override
    public PageUtils<TransferProcessVO> listTransferProcessVOByassembly(Map<String, Object> map, Integer pageNo, Integer pageSize) {
        List<TransferVO> list = transferMapper.getAssemblyId(map);
        Map<String, TransferVO> maps = list.stream().collect(Collectors.toMap(TransferVO::getId, a -> a, (k1, k2) -> k1));
        List<String> dataIds = list.stream().map(TransferVO::getId).collect(Collectors.toList());
        ActivityDataParamsVo activityDataParamsVo = ActivityDataParamsVo.getInstance(DEF_ID, ID_ATTR_NAME,
                dataIds, map, pageNo, pageSize);
        PageUtils<ActivityDataVo> activityDataVoPageUtils = WorkUtil.getPageUtilsByParams(activityDataParamsVo);
        List<ActivityDataVo> activityDataVos = activityDataVoPageUtils.getList();
        List<TransferProcessVO> transferProcessVOS = this.activityDataVos2TransferProcessVO(activityDataVos, maps);
        PageInfo<TransferProcessVO> transferProcessVOSPageInfo = new PageInfo<>(transferProcessVOS);
        transferProcessVOSPageInfo.setTotal(activityDataVoPageUtils.getTotalCount());
        transferProcessVOSPageInfo.setPageSize(activityDataVoPageUtils.getPageSize());
        transferProcessVOSPageInfo.setPageNum(activityDataVoPageUtils.getCurrPage());
        return PageUtils.getPageUtils(transferProcessVOSPageInfo);
    }

    /**
     * 转换成自己封装的流程类
     */
    private List<TransferProcessVO> activityDataVos2TransferProcessVO(
            List<ActivityDataVo> activityDataVos, Map<String, TransferVO> map) {
        List<TransferProcessVO> transferProcessVOs = Collections.EMPTY_LIST;
        if (!CollectionUtils.isEmpty(activityDataVos)) {
            transferProcessVOs = Lists.newArrayListWithCapacity(activityDataVos.size());
            for (ActivityDataVo activityDataVo : activityDataVos) {
                TransferProcessVO transferprocessvo = new TransferProcessVO();
                TransferVO transfervo = map.get(activityDataVo.getUnitValue());
                transferprocessvo.setSpeName(transfervo.getSpeName());
                transferprocessvo.setAddCompanyName(transfervo.getAddCompanyVO().getCompName());
                transferprocessvo.setReduceCompanyName(transfervo.getReduceCompanyVO().getCompName());
                transferprocessvo.setSpeNum(transfervo.getSpeNum());
                transferprocessvo.setApplyCode(transfervo.getApplyCode());
                transferprocessvo.setCreateTime(transfervo.getCreateTime());
                List<ActContextVo> actContextVos = activityDataVo.getActContextVos();
                for (ActContextVo actContextVo : actContextVos) {
                    String dataFieldId = actContextVo.getDataFieldId();
                    String value = actContextVo.getValue();
                    if (org.springframework.util.StringUtils.hasText(value)) {
                        if ("status".equals(dataFieldId)) {
                            transferprocessvo.setOpContent(TranferStatusEnum.getVal(value));
                        } else if ("person".equals(dataFieldId)) {
                            transferprocessvo.setOpUserName(value);
                        } else if ("opinion".equals(dataFieldId)) {
                            transferprocessvo.setAdvice(value);
                        }
                    }
                }
                transferprocessvo.setOpTime(
                        DateUtils.stringToDate(activityDataVo.getActivityCompleteTime(), DateUtils.DATE_TIME_PATTERN));
                transferProcessVOs.add(transferprocessvo);

            }
        }
        return transferProcessVOs;
    }


    @Override
    public List<TransferProcessVO> getTransferProcessVO(Map<String, Object> map) {
        List<TransferProcessVO> list = transferMapper.getTransferProcessVO(map);
        return list;
    }

    @Override
    public List<TransferProcessVO> getTransferProcessVOByassembly(Map<String, Object> map) {

        TransferVO tb = transferMapper.getInAssemblyId((String) map.get("transferId"));
        List<TransferProcessVO> list = new ArrayList<>();
        if (tb != null) {
            List<Map<String, Object>> transferId = WorkUtil.getProcesslist(DEF_ID, ID_ATTR_NAME, (String) map.get("transferId"));

            transferId.forEach(o -> {
                TransferProcessVO tp = new TransferProcessVO();
                tp.setOpUserName((String) o.get("person"));
                tp.setOpTime((Date) o.get("createTime"));
                tp.setOpContent(TranferStatusEnum.FIRST_RETURN.getKey().equals(o.get("status"))
                        || TranferStatusEnum.SECOND_RETURN.getKey().equals(o.get("status"))
                        || TranferStatusEnum.THIRD_RETURN.getKey().equals(o.get("status"))
                        ?
                        (String) o.get("opinion") : TranferStatusEnum.getVal((String) o.get("status")));
                list.add(tp);
            });
        }


        return list;
    }

    @Override
    public List<Signboard> listSignboardApplyListVo(Map map) {
        return transferMapper.listSignboardApplyListVo(map);
    }

    @Override
    public AreaVO listSpeciesCountVOProvince(Map<String, Object> map, Integer pageNo, Integer pageSize) {
        PageHelper.offsetPage(pageNo, pageSize);
        List<SpeciesCountVO> list = transferMapper.listSpeciesCountVOProvince(map);
        PageInfo<SpeciesCountVO> listPageInfo = new PageInfo<SpeciesCountVO>(list);
        listPageInfo.setList(list);
        AreaVO areaVO = new AreaVO();
        areaVO.setPageUtils(PageUtils.getPageUtils(listPageInfo));


        SpeciesCountVO speciesCountVO = transferMapper.sumSpeciesCountVOProvince(map);
        if (speciesCountVO == null) {
            speciesCountVO = new SpeciesCountVO();
            speciesCountVO.setAddNumber(0);
            speciesCountVO.setCurNumber(0);
            speciesCountVO.setReduceNumber(0);
        }
        areaVO.setSpeciesCountVO(speciesCountVO);
        return areaVO;
    }

    @Override
    public AreaVO listSpeciesCountVOCity(Map<String, Object> map, Integer pageNo, Integer pageSize) {
        PageHelper.offsetPage(pageNo, pageSize);
        List<SpeciesCountVO> list = transferMapper.listSpeciesCountVOCity(map);
        PageInfo<SpeciesCountVO> listPageInfo = new PageInfo<SpeciesCountVO>(list);
        listPageInfo.setList(list);
        AreaVO areaVO = new AreaVO();
        areaVO.setPageUtils(PageUtils.getPageUtils(listPageInfo));

        SpeciesCountVO speciesCountVO = transferMapper.sumSpeciesCountVOCity(map);
        if (speciesCountVO == null) {
            speciesCountVO = new SpeciesCountVO();
            speciesCountVO.setAddNumber(0);
            speciesCountVO.setCurNumber(0);
            speciesCountVO.setReduceNumber(0);
        }
        areaVO.setSpeciesCountVO(speciesCountVO);
        return areaVO;
    }

    @Override
    public AreaVO listSpeciesCountVOCountry(Map<String, Object> map, Integer pageNo, Integer pageSize) {
        PageHelper.offsetPage(pageNo, pageSize);
        List<SpeciesCountVO> list = transferMapper.listSpeciesCountVOCountry(map);
        PageInfo<SpeciesCountVO> listPageInfo = new PageInfo<SpeciesCountVO>(list);
        listPageInfo.setList(list);
        AreaVO areaVO = new AreaVO();
        areaVO.setPageUtils(PageUtils.getPageUtils(listPageInfo));

        SpeciesCountVO speciesCountVO = transferMapper.sumSpeciesCountVOCountry(map);
        if (speciesCountVO == null) {
            speciesCountVO = new SpeciesCountVO();
            speciesCountVO.setAddNumber(0);
            speciesCountVO.setCurNumber(0);
            speciesCountVO.setReduceNumber(0);
        }
        areaVO.setSpeciesCountVO(speciesCountVO);
        return areaVO;
    }

    @Override
    public AreaVO listSpeciesCountVORegiones(Map<String, Object> map, Integer pageNo, Integer pageSize) {
        PageHelper.offsetPage(pageNo, pageSize);
        List<SpeciesCountVO> list = transferMapper.listSpeciesCountVORegiones(map);
        PageInfo<SpeciesCountVO> listPageInfo = new PageInfo<SpeciesCountVO>(list);
        listPageInfo.setList(list);
        AreaVO areaVO = new AreaVO();
        areaVO.setPageUtils(PageUtils.getPageUtils(listPageInfo));

        SpeciesCountVO speciesCountVO = transferMapper.sumSpeciesCountVORegiones(map);
        if (speciesCountVO == null) {
            speciesCountVO = new SpeciesCountVO();
            speciesCountVO.setAddNumber(0);
            speciesCountVO.setCurNumber(0);
            speciesCountVO.setReduceNumber(0);
        }
        areaVO.setSpeciesCountVO(speciesCountVO);
        return areaVO;

    }

    @Override
    public List<SpeciesCountVO> listSpeciesCountVOProvince(Map<String, Object> map) {
        List<SpeciesCountVO> list = transferMapper.listSpeciesCountVOProvince(map);
        SpeciesCountVO speciesCountVO = transferMapper.sumSpeciesCountVOProvince(map);
        if (speciesCountVO == null) {
            speciesCountVO = new SpeciesCountVO();
            speciesCountVO.setAddNumber(0);
            speciesCountVO.setCurNumber(0);
            speciesCountVO.setReduceNumber(0);
        }
        speciesCountVO.setRegionName("合计");
        list.add(speciesCountVO);
        return list;
    }

    @Override
    public List<SpeciesCountVO> listSpeciesCountVOCity(Map<String, Object> map) {
        List<SpeciesCountVO> list = transferMapper.listSpeciesCountVOCity(map);
        SpeciesCountVO speciesCountVO = transferMapper.sumSpeciesCountVOCity(map);
        if (speciesCountVO == null) {
            speciesCountVO = new SpeciesCountVO();
            speciesCountVO.setAddNumber(0);
            speciesCountVO.setCurNumber(0);
            speciesCountVO.setReduceNumber(0);
        }
        speciesCountVO.setRegionName("合计");
        list.add(speciesCountVO);
        return list;
    }

    @Override
    public List<SpeciesCountVO> listSpeciesCountVOCountry(Map<String, Object> map) {
        List<SpeciesCountVO> list = transferMapper.listSpeciesCountVOCountry(map);
        SpeciesCountVO speciesCountVO = transferMapper.sumSpeciesCountVOCountry(map);
        if (speciesCountVO == null) {
            speciesCountVO = new SpeciesCountVO();
            speciesCountVO.setAddNumber(0);
            speciesCountVO.setCurNumber(0);
            speciesCountVO.setReduceNumber(0);
        }
        speciesCountVO.setRegionName("合计");
        list.add(speciesCountVO);
        return list;
    }

    @Override
    public List<SpeciesCountVO> listSpeciesCountVORegiones(Map<String, Object> map) {
        List<SpeciesCountVO> list = transferMapper.listSpeciesCountVORegiones(map);
        SpeciesCountVO speciesCountVO = transferMapper.sumSpeciesCountVORegiones(map);
        if (speciesCountVO == null) {
            speciesCountVO = new SpeciesCountVO();
            speciesCountVO.setAddNumber(0);
            speciesCountVO.setCurNumber(0);
            speciesCountVO.setReduceNumber(0);
        }
        speciesCountVO.setRegionName("合计");
        list.add(speciesCountVO);
        return list;
    }

    @Override
    public CompVO listCompanyCount(Map<String, Object> map, Integer pageNo, Integer pageSize) {
        PageHelper.offsetPage(pageNo, pageSize);
        List<CompCountVO> list = transferMapper.listCompanyCount(map);
        PageInfo<CompCountVO> listPageInfo = new PageInfo<CompCountVO>(list);
        listPageInfo.setList(list);

        CompVO compVo = new CompVO();
        compVo.setPageUtils(PageUtils.getPageUtils(listPageInfo));

        CompCountVO compCountVO = transferMapper.sumCompanyCount(map);
        if (compCountVO == null) {
            compCountVO = new CompCountVO();
            compCountVO.setAllsigncount(0);
            compCountVO.setLinesigncount(0);
            compCountVO.setSpeNumber(0);
        }
        compCountVO.setRegionName("合计");
        compVo.setCompCountVO(compCountVO);
        return compVo;
    }

    /**
     * @param id
     * @return void
     * @author wg
     * @description 物种转移撤回
     * @date 2021/4/9 16:32
     */
    @Override
    public void cancel(String id) {
        //根据主键查询详情
        QueryWrapper<Transfer> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id).eq("del_flag", BoolUtils.N);
        wrapper.select("id", "transfer_status");
        Transfer transfer = transferMapper.selectOne(wrapper);
        //非空校验
        if (transfer == null) {
            throw new SofnException("没有找到相关数据");
        }
        //如果当前是已上报才可以撤回
        if (transfer.getTransferStatus() != null && !transfer.getTransferStatus().equals(TranferStatusEnum.REPORT.getKey())) {
            throw new SofnException("只有已上报状态才可以撤回");
        }
        //设置当前操作人、操作时间
        transfer.preUpdate();
        //设置当前状态
        transfer.setTransferStatus(TranferStatusEnum.CANCEL.getKey());
        //执行更新
        int i = transferMapper.updateById(transfer);
        if (i != 1) {
            throw new SofnException("撤回失败");
        }
        ChangeRecordProcess changeRecordProcess = new ChangeRecordProcess();
        changeRecordProcess.setId(IdUtil.getUUId());
        changeRecordProcess.setStatus(TranferStatusEnum.CANCEL.getKey());
        changeRecordProcess.setChangeRecordId(id);
        changeRecordProcess.setOpUserId(UserUtil.getLoginUser().getNickname());
        changeRecordProcess.setContent("撤回");
        changeRecordProcess.setAdvice("");
        changeRecordProcess.setConTime(new Date());
        transferMapper.saveTransferProcess(changeRecordProcess);

        if (Constants.WORKFLOW.equals(BoolUtils.Y)) {
            //操作工作流
            String[] keys = {"status", "person"};
            Object[] vals = {TranferStatusEnum.CANCEL.getKey(), SysOwnOrgUtil.getOrganizationName()};
            workApi.backWorkItem(BackWorkItemForm.getInstanceForm(
                    DEF_ID, ID_ATTR_NAME, id, TARGET_ACT_DEFID, MapUtil.getParams(keys, vals)));
        }
    }

    @Override
    public AreaVO listSpeciesCountVORegionesNew(Map<String, Object> params, Integer pageNo, Integer pageSize) {
        if ("4".equals(params.get("queryType")) && Objects.nonNull(params.get("regiones"))) {
            params.put("regionCodes", Arrays.asList(params.get("regiones").toString().split(",")));
        }
        params.put("regionType", params.get("queryType"));
        //数据权限过滤
        this.handleRegionCode(params);
        List<SpeciesCountVO> list = transferMapper.listSpeciesCountVORegionesNew(params);
        SpeciesCountVO totalCount = new SpeciesCountVO();
        double stock = 0;
        double change = 0;
        double applyNum = 0;
        double useNum = 0;
        double bizNum = 0;
        double breedNum = 0;
        for (int i = 0; i < list.size(); i++) {
            SpeciesCountVO scv = list.get(i);
            stock += scv.getStock();
            change += scv.getChange();
            applyNum += scv.getApplyNum();
            useNum += scv.getUseNum();
            bizNum += scv.getBizNum();
            breedNum += scv.getBreedNum();
        }

        totalCount.setStock(stock);
        totalCount.setChange(change);
        totalCount.setApplyNum(applyNum);
        totalCount.setBizNum(bizNum);
        totalCount.setUseNum(useNum);
        totalCount.setBreedNum(breedNum);
        Object order = params.get("order");
        Object orderSign = params.get("orderSign");
        if (Objects.nonNull(order) && Objects.nonNull(orderSign)) {
            if ("desc".equals(orderSign.toString().toLowerCase())) {
                if ("stock".equals(order)) {
                    list.sort(Comparator.comparing(SpeciesCountVO::getStock).reversed());
                } else if ("change".equals(order)) {
                    list.sort(Comparator.comparing(SpeciesCountVO::getChange).reversed());
                } else if ("applyNum".equals(order)) {
                    list.sort(Comparator.comparing(SpeciesCountVO::getApplyNum).reversed());
                } else if ("useNum".equals(order)) {
                    list.sort(Comparator.comparing(SpeciesCountVO::getUseNum).reversed());
                } else if ("bizNum".equals(order)) {
                    list.sort(Comparator.comparing(SpeciesCountVO::getBizNum).reversed());
                } else if ("breedNum".equals(order)) {
                    list.sort(Comparator.comparing(SpeciesCountVO::getBreedNum).reversed());
                }
            } else {
                if ("stock".equals(order)) {
                    list.sort(Comparator.comparing(SpeciesCountVO::getStock));
                } else if ("change".equals(order)) {
                    list.sort(Comparator.comparing(SpeciesCountVO::getChange));
                } else if ("applyNum".equals(order)) {
                    list.sort(Comparator.comparing(SpeciesCountVO::getApplyNum));
                } else if ("useNum".equals(order)) {
                    list.sort(Comparator.comparing(SpeciesCountVO::getUseNum));
                } else if ("bizNum".equals(order)) {
                    list.sort(Comparator.comparing(SpeciesCountVO::getBizNum));
                } else if ("breedNum".equals(order)) {
                    list.sort(Comparator.comparing(SpeciesCountVO::getBreedNum));
                }
            }
        }
        List<SpeciesCountVO> pageList = new ArrayList<>(pageSize);
        int j = 0;
        if (list.size() < pageSize) {
            j = pageNo + j;
        } else {
            j = pageNo + 10;
        }
        if (j > list.size()) {
            j = list.size();
        } else if (list.size() < pageSize) {
            j = list.size();
        }
        for (int i = pageNo; i < j; i++) {
            pageList.add(list.get(i));
        }
        PageInfo<SpeciesCountVO> scvPageInfo = new PageInfo<>(pageList);
        scvPageInfo.setTotal(list.size());
        scvPageInfo.setPageSize(pageSize);
        scvPageInfo.setPageNum((pageNo / pageSize) + 1);
        AreaVO areaVO = new AreaVO();
        areaVO.setPageUtils(PageUtils.getPageUtils(scvPageInfo));
        areaVO.setSpeciesCountVO(totalCount);
        return areaVO;
    }

    private void handleRegionCode(Map<String, Object> params) {
        OrganizationInfo oif = SysOwnOrgUtil.getOrgInfo();
        String level = oif.getOrganizationLevel();
        String queryType = params.get("queryType").toString();
        if ("province".equals(level)) {
            params.put("regionCode_", oif.getRegionLastCode().substring(0, 2) + "____");
        } else if ("city".equals(level)) {
            if ("1".equals(queryType)) {
                params.put("regionCode_", oif.getRegionLastCode().substring(0, 2) + "____");
            } else if ("2".equals(queryType) || "3".equals(queryType)) {
                params.put("regionCode_", oif.getRegionLastCode().substring(0, 4) + "__");
            }
        } else if ("county".equals(level)) {
            if ("1".equals(queryType)) {
                params.put("regionCode_", oif.getRegionLastCode().substring(0, 2) + "____");
            } else if ("2".equals(queryType)) {
                params.put("regionCode_", oif.getRegionLastCode().substring(0, 4) + "__");
            } else if ("3".equals(queryType)) {
                params.put("regionCode_", oif.getRegionLastCode());
            }
        }
    }

    @Override
    public List<CompCountVO> listCompanyCount(Map<String, Object> map) {
        List<CompCountVO> list = transferMapper.listCompanyCount(map);
        CompCountVO compCountVO = transferMapper.sumCompanyCount(map);
        if (compCountVO == null) {
            compCountVO = new CompCountVO();
            compCountVO.setAllsigncount(0);
            compCountVO.setLinesigncount(0);
            compCountVO.setSpeNumber(0);
        }
        compCountVO.setRegionName("合计");
        list.add(compCountVO);
        return list;
    }
//    用于变化转移物种的可操作物种数目

    //    private  void updateOperableNum(List<SpeciesSelect> speciesSelectList ,String status,String addcompId,String reducecompId){
////         减少 -减少企业的物种可操作数量
//         Boolean a="1".equals(status);
////         增加 -减少企业的物种可操作数量
//         Boolean b="2".equals(status)||"4".equals(status)||"6".equals(status);
////         增加  增加企业的物种可操作数量
//        Boolean c="7".equals(status);
//        if (!CollectionUtil.isEmpty(speciesSelectList)){
//            speciesSelectList.forEach(o->{
//            if(a){
//                Map<String,Object> map_num = Maps.newHashMap();
//                map_num.put("companyId",reducecompId);
//                map_num.put("speciesId",o.getSpeciesId());
//                map_num.put("changeNum",Integer.parseInt("-"+o.getSpeciesNum()));
//                transferMapper.updateOperableNum(map_num);
//            }else if(b){
//                Map<String,Object> map_num = Maps.newHashMap();
//                map_num.put("companyId",reducecompId);
//                map_num.put("speciesId",o.getSpeciesId());
//                map_num.put("changeNum",o.getSpeciesNum());
//                transferMapper.updateOperableNum(map_num);
//            }else if(c){
//                Map<String,Object> map_num = Maps.newHashMap();
//                map_num.put("companyId",addcompId);
//                map_num.put("speciesId",o.getSpeciesId());
//                map_num.put("changeNum",o.getSpeciesNum());
//                transferMapper.updateOperableNum(map_num);
//            }
//
//            });
//        }
//    }
//    ----   校验是否转移物种在流程中
    private void checkOnProcess(TransferVO transferVO) {
        Map map_get = new HashMap();
        map_get.put("transferId", transferVO.getId());
        TransferVO one = this.getTransferVO(map_get);
        if (one.getTransferStatus().equals("1")) {
            one.getSpeciesSelectList().forEach(o -> {
                List<CheckVo> checkVos = transferMapper.checkOnProcess(one.getReduceCompanyVO().getCompanyId(), o.getSpeciesId());
                if (!CollectionUtil.isEmpty(checkVos)) {
                    throw new SofnException("减少企业转移物种 " + o.getSpeciesName() + "已在物种变更审核流程中，等待审核完成后再进行申请");
                }
            });
        }
    }
}
