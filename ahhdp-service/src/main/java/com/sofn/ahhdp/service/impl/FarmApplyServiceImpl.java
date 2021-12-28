package com.sofn.ahhdp.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sofn.ahhdp.enums.AuditStatusEnum;
import com.sofn.ahhdp.mapper.FarmApplyMapper;
import com.sofn.ahhdp.model.Farm;
import com.sofn.ahhdp.model.FarmApply;
import com.sofn.ahhdp.model.FarmRecord;
import com.sofn.ahhdp.service.FarmApplyService;
import com.sofn.ahhdp.service.FarmRecordService;
import com.sofn.ahhdp.service.FarmService;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.User;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-04-21 15:33
 */
@Service(value = "farmApplyService")
public class FarmApplyServiceImpl implements FarmApplyService {

    @Autowired
    private FarmApplyMapper farmApplyMapper;

    @Autowired
    @Lazy
    private FarmService farmService;

    @Autowired
    private FarmRecordService farmRecordService;

    @Override
    public void save(FarmApply entity) {
        entity.setId(entity.getCode());
        farmApplyMapper.insert(entity);
    }

    @Override
    public FarmApply get(String id) {
        return farmApplyMapper.selectById(id);
    }

    @Override
    public void apply(String id, String newName, String newCompany) {
        if (StringUtils.isEmpty(newName) && StringUtils.isEmpty(newCompany)) {
            throw new SofnException("保护场名称,建设单位至少填写一项!");
        }
        if(newName.length()>64){
            throw new SofnException("保护场名称长度不能超过64");
        }
        if(newCompany.length()>64){
            throw new SofnException("建设单位长度不能超过64");
        }
        FarmApply farmApply = farmApplyMapper.selectById(id);
        farmApply.setId(id);
        String auditStatus = farmApply.getAuditStatus();
        String oldName = farmApply.getNewName();
        // 原始数据存在新(更改)名称并且审核状态为审核通过
        if (StringUtils.hasText(oldName) && AuditStatusEnum.PASS.getKey().equals(auditStatus)) {
            farmApply.setOldName(oldName);
        }
        farmApply.setNewName(newName);
        String oldCompany = farmApply.getNewCompany();
        if (StringUtils.hasText(oldCompany) && AuditStatusEnum.PASS.getKey().equals(auditStatus)) {
            farmApply.setOldCompany(oldCompany);
        }
        farmApply.setNewCompany(newCompany);
        User user = UserUtil.getLoginUser();
        String operator = "变更人";
        if (Objects.nonNull(user)) {
            operator = user.getNickname();
        }
        farmApply.setChangeTime(new Date());
        farmApply.setOperator(operator);
        farmApply.setAuditStatus(AuditStatusEnum.APPLY.getKey());

        farmApplyMapper.updateById(farmApply);
    }

    @Override
    @Transactional
    public void auditPass(String id, String opinion) {
        int max=100;
        if (opinion.length()>max){
            throw new SofnException("审核意见不能大于"+max+"字");
        }
        this.audit(id, AuditStatusEnum.PASS.getKey(), StringUtils.hasText(opinion) ? opinion : "");
    }

    /**
     * 审核
     */
    private void audit(String id, String auditStatus, String opinion) {
        FarmApply FarmApply = farmApplyMapper.selectById(id);
        if (Objects.isNull(FarmApply)) {
            throw new SofnException("待审核数据不存在!");
        }
        if (StringUtils.hasText(FarmApply.getAuditStatus()) &&
                !AuditStatusEnum.APPLY.getKey().equals(FarmApply.getAuditStatus())) {
            throw new SofnException("当前数据不需要审核!");
        }
        User user = UserUtil.getLoginUser();
        String auditor = "审核人";
        if (Objects.nonNull(user)) {
            auditor = user.getNickname();
        }
        Date now = new Date();
        FarmApply.setAuditor(auditor);
        FarmApply.setAuditTime(now);
        FarmApply.setAuditStatus(auditStatus);
        FarmApply.setOpinion(opinion);
        farmApplyMapper.updateById(FarmApply);

        if (AuditStatusEnum.PASS.getKey().equals(auditStatus)) {
            FarmRecord FarmRecord = new FarmRecord();
            BeanUtils.copyProperties(FarmApply, FarmRecord);
            farmRecordService.save(FarmRecord);

            Farm farm = new Farm();
            farm.setId(FarmApply.getId());
            farm.setChangeTime(FarmApply.getChangeTime());
            String operator = "操作人";
            if (Objects.nonNull(user)) {
                operator = user.getNickname();
            }
            farm.setOperator(operator);
            String newName = FarmApply.getNewName();
            if (StringUtils.hasText(newName)) {
                farm.setOldName(newName);
            }
            String newCompany = FarmApply.getNewCompany();
            if (StringUtils.hasText(newCompany)) {
                farm.setOldCompany(newCompany);
            }
            farmService.update(farm);
        }
    }

    @Override
    public void auditUnpass(String id, String opinion) {
        int max=100;
        if (opinion.length()>max){
            throw new SofnException("退回意见不能大于"+max+"字");
        }
        this.audit(id, AuditStatusEnum.UNPASS.getKey(), StringUtils.hasText(opinion) ? opinion : "");
    }

    @Override
    public PageUtils<Farm> listByParams(Map<String, Object> params, Integer pageNo, Integer pageSize) {
        PageHelper.offsetPage(pageNo, pageSize);
        if (AuditStatusEnum.UNDO.getKey().equals(params.get("auditStatus"))) {
            params.put("auditStatus", "");
            params.put("auditStatus2", AuditStatusEnum.UNDO.getKey());
        }
        List<FarmApply> farmApplies = farmApplyMapper.listByParams(params);
        setAuditStatusName(farmApplies);
        return PageUtils.getPageUtils(new PageInfo(farmApplies));
    }

    @Override
    public FarmRecord getForManage(String id) {
        FarmRecord farmRecord = farmRecordService.getLastChangeByCode(id);
        if (Objects.nonNull(farmRecord)) {
            return farmRecord;
        } else {
            farmRecord = new FarmRecord();
            Farm farm = farmService.get(id);
            if (Objects.nonNull(farm)) {
                BeanUtils.copyProperties(farm, farmRecord, "operator");
            }
            return farmRecord;
        }
    }

    private void setAuditStatusName(List<FarmApply> FarmApplies) {
        if (!CollectionUtils.isEmpty(FarmApplies)) {
            for (FarmApply farmApply : FarmApplies) {
                farmApply.setAuditStatusName(AuditStatusEnum.getVal(farmApply.getAuditStatus()));
                String auditSatuts = farmApply.getAuditStatus();
                farmApply.setCanAudit("2".equals(auditSatuts));
                farmApply.setCanEdit(!"2".equals(auditSatuts));
            }
        }
    }
}
