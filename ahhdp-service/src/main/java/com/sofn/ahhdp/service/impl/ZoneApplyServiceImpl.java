package com.sofn.ahhdp.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sofn.ahhdp.enums.AuditStatusEnum;
import com.sofn.ahhdp.mapper.ZoneApplyMapper;
import com.sofn.ahhdp.model.Zone;
import com.sofn.ahhdp.model.ZoneApply;
import com.sofn.ahhdp.model.ZoneRecord;
import com.sofn.ahhdp.service.ZoneApplyService;
import com.sofn.ahhdp.service.ZoneRecordService;
import com.sofn.ahhdp.service.ZoneService;
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

import java.util.*;


@Service(value = "zoneApplyService")
public class ZoneApplyServiceImpl implements ZoneApplyService {

    @Autowired
    private ZoneApplyMapper zoneApplyMapper;

    @Autowired
    @Lazy
    private ZoneService zoneService;

    @Autowired
    private ZoneRecordService zoneRecordService;

    @Override
    public void save(ZoneApply entity) {
        entity.setId(entity.getCode());
        zoneApplyMapper.insert(entity);
    }

    @Override
    public ZoneApply get(String id) {
        return zoneApplyMapper.selectById(id);
    }

    @Override
    public void apply(String id, String newName, String newCompany, String newRange) {
        if (StringUtils.isEmpty(newName) && StringUtils.isEmpty(newCompany) && StringUtils.isEmpty(newRange)) {
            throw new SofnException("保护区名称,建设单位,保护区范围至少填写一项!");
        }
        if (newName.length() > 64) {
            throw new SofnException("保护区名称长度不能超过64");
        }
        if (newCompany.length() > 64) {
            throw new SofnException("建设单位长度不能超过64");
        }
        if (newRange.length() > 64) {
            throw new SofnException("保护区范围长度不能超过64");
        }

        ZoneApply zoneApply = zoneApplyMapper.selectById(id);
        zoneApply.setId(id);
        String auditStatus = zoneApply.getAuditStatus();

        String oldName = zoneApply.getNewName();
        // 原始数据存在新(更改)名称并且审核状态为审核通过
        if (StringUtils.hasText(oldName) && AuditStatusEnum.PASS.getKey().equals(auditStatus)) {
            zoneApply.setAreaName(oldName);
        }
        zoneApply.setNewName(newName);
        String oldCompany = zoneApply.getNewCompany();
        if (StringUtils.hasText(oldCompany) && AuditStatusEnum.PASS.getKey().equals(auditStatus)) {
            zoneApply.setCompany(oldCompany);
        }
        zoneApply.setNewCompany(newCompany);
        String oldRange = zoneApply.getNewRange();
        if (StringUtils.hasText(oldRange) && AuditStatusEnum.PASS.getKey().equals(auditStatus)) {
            zoneApply.setAreaRange(oldRange);
        }
        zoneApply.setNewRange(newRange);
        User user = UserUtil.getLoginUser();
        String operator = "变更人";
        if (Objects.nonNull(user)) {
            operator = user.getNickname();
        }
        zoneApply.setChangeTime(new Date());
        zoneApply.setOperator(operator);
        zoneApply.setAuditStatus(AuditStatusEnum.APPLY.getKey());

        zoneApplyMapper.updateById(zoneApply);
    }

    @Override
    @Transactional
    public void auditPass(String id, String opinion) {
        int max = 100;
        if (opinion.length() > max) {
            throw new SofnException("审核意见不能大于" + max + "字");
        }
        this.audit(id, AuditStatusEnum.PASS.getKey(), StringUtils.hasText(opinion) ? opinion : "");
    }

    /**
     * 审核
     */
    private void audit(String id, String auditStatus, String opinion) {
        ZoneApply zoneApply = zoneApplyMapper.selectById(id);
        if (Objects.isNull(zoneApply)) {
            throw new SofnException("待审核数据不存在!");
        }
        if (StringUtils.hasText(zoneApply.getAuditStatus()) &&
                !AuditStatusEnum.APPLY.getKey().equals(zoneApply.getAuditStatus())) {
            throw new SofnException("当前数据不需要审核!");
        }
        User user = UserUtil.getLoginUser();
        String auditor = "审核人";
        if (Objects.nonNull(user)) {
            auditor = user.getNickname();
        }
        Date now = new Date();
        zoneApply.setAuditor(auditor);
        zoneApply.setAuditTime(now);
        zoneApply.setAuditStatus(auditStatus);
        zoneApply.setOpinion(opinion);
        zoneApplyMapper.updateById(zoneApply);

        if (AuditStatusEnum.PASS.getKey().equals(auditStatus)) {
            ZoneRecord zoneRecord = new ZoneRecord();
            BeanUtils.copyProperties(zoneApply, zoneRecord);
            zoneRecordService.save(zoneRecord);

            Zone zone = new Zone();
            zone.setId(zoneApply.getId());
            zone.setChangeTime(zoneApply.getChangeTime());
            String operator = "操作人";
            if (Objects.nonNull(user)) {
                operator = user.getNickname();
            }
            zone.setOperator(operator);
            String newName = zoneApply.getNewName();
            if (StringUtils.hasText(newName)) {
                zone.setAreaName(newName);
            }
            String newCompany = zoneApply.getNewCompany();
            if (StringUtils.hasText(newCompany)) {
                zone.setCompany(newCompany);
            }
            String newRange = zoneApply.getNewRange();
            if (StringUtils.hasText(newRange)) {
                zone.setAreaRange(newRange);
            }
            zoneService.update(zone);
        }
    }

    @Override
    public void auditUnpass(String id, String opinion) {
        int max = 100;
        if (opinion.length() > max) {
            throw new SofnException("退回意见不能大于" + max + "字");
        }
        this.audit(id, AuditStatusEnum.UNPASS.getKey(), StringUtils.hasText(opinion) ? opinion : "");
    }

    @Override
    public PageUtils<Zone> listByParams(Map<String, Object> params, Integer pageNo, Integer pageSize) {
        PageHelper.offsetPage(pageNo, pageSize);
        if (AuditStatusEnum.UNDO.getKey().equals(params.get("auditStatus"))) {
            params.put("auditStatus", "");
            params.put("auditStatus2", AuditStatusEnum.UNDO.getKey());
        }
        List<ZoneApply> zoneApplies = zoneApplyMapper.listByParams(params);
        setAuditStatusName(zoneApplies);
        return PageUtils.getPageUtils(new PageInfo(zoneApplies));
    }

    @Override
    public ZoneRecord getForManage(String id) {
        ZoneRecord zoneRecord = zoneRecordService.getLastChangeByCode(id);
        if (Objects.nonNull(zoneRecord)) {
            return zoneRecord;
        } else {
            zoneRecord = new ZoneRecord();
            Zone zone = zoneService.get(id);
            if (Objects.nonNull(zone)) {
                BeanUtils.copyProperties(zone, zoneRecord, "operator");
            }
            return zoneRecord;
        }
    }

    private void setAuditStatusName(List<ZoneApply> zoneApplies) {
        if (!CollectionUtils.isEmpty(zoneApplies)) {
            for (ZoneApply zoneApply : zoneApplies) {
                zoneApply.setAuditStatusName(AuditStatusEnum.getVal(zoneApply.getAuditStatus()));
                String auditSatuts = zoneApply.getAuditStatus();
                zoneApply.setCanAudit("2".equals(auditSatuts));
                zoneApply.setCanEdit(!"2".equals(auditSatuts));
            }
        }
    }

}
