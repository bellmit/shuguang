package com.sofn.ahhdp.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sofn.ahhdp.enums.AuditStatusEnum;
import com.sofn.ahhdp.enums.TablesEnum;
import com.sofn.ahhdp.mapper.DirectoriesApplyMapper;
import com.sofn.ahhdp.model.Directories;
import com.sofn.ahhdp.model.DirectoriesApply;
import com.sofn.ahhdp.model.DirectoriesRecord;
import com.sofn.ahhdp.service.DirectoriesApplyService;
import com.sofn.ahhdp.service.DirectoriesRecordService;
import com.sofn.ahhdp.service.DirectoriesService;
import com.sofn.ahhdp.sysapi.SysRegionApi;
import com.sofn.ahhdp.vo.SysRegionForm;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.Result;
import com.sofn.common.model.User;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.RedisHelper;
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
 * @Date: 2020-04-21 11:37
 */
@Service("directoriesApplyService")
public class DirectoriesApplyServiceImpl implements DirectoriesApplyService {

    @Autowired
    private DirectoriesApplyMapper directoriesApplyMapper;

    @Autowired
    @Lazy
    private DirectoriesService directoriesService;

    @Autowired
    private DirectoriesRecordService directoriesRecordService;
    @Autowired
    private SysRegionApi sysRegionApi;
    @Autowired
    private RedisHelper redisHelper;
    private final String redisKey = TablesEnum.SPE_KEY_ALL.getCode();
    private final String redisKeyByProvinceCode = TablesEnum.SPE_KEY_BY_PROVINCE_CODE.getCode();

    @Override
    public void save(DirectoriesApply entity) {
        entity.setId(entity.getCode());
        directoriesApplyMapper.insert(entity);
    }

    @Override
    public DirectoriesApply get(String id) {
        return directoriesApplyMapper.selectById(id);
    }

    @Override
    public void apply(String id, String newName, String newCompany) {
        if (StringUtils.isEmpty(newName) && StringUtils.isEmpty(newCompany)) {
            throw new SofnException("品种名称,所属地区至少填写一项!");
        }
        if (newName.length() > 64) {
            throw new SofnException("品种名称长度不能超过64");
        }
        if (newCompany.length() > 250) {
            throw new SofnException("所属地区长度不能超过250");
        }
        DirectoriesApply directoriesApply = directoriesApplyMapper.selectById(id);
        directoriesApply.setId(id);
        String auditStatus = directoriesApply.getAuditStatus();
        String oldName = directoriesApply.getNewName();
        // 原始数据存在新(更改)名称并且审核状态为审核通过
        if (StringUtils.hasText(oldName) && AuditStatusEnum.PASS.getKey().equals(auditStatus)) {
            directoriesApply.setOldName(oldName);
        }
        directoriesApply.setNewName(newName);

        String oldCompany = directoriesApply.getNewRegion();
        String oldRegionCode = directoriesApply.getNewRegionCode();
        if (StringUtils.hasText(oldCompany) && AuditStatusEnum.PASS.getKey().equals(auditStatus)) {
            directoriesApply.setOldRegion(oldCompany);
            directoriesApply.setOldRegionCode(oldRegionCode);
        }
        StringBuilder st = new StringBuilder();
        if (StringUtils.hasText(newCompany)) {
            if (newCompany.contains(",")) {
                String[] split = newCompany.split(",");
                for (int i = 0; i < split.length; i++) {
                    Result<SysRegionForm> sysRegionByName = sysRegionApi.getSysRegionByName(split[i]);
                    String regionCode = sysRegionByName.getData().getRegionCode();
                    st.append(regionCode + ",");
                }
                directoriesApply.setNewRegionCode(st.substring(0, st.length() - 1));
            } else {
                Result<SysRegionForm> sysRegionByName = sysRegionApi.getSysRegionByName(newCompany);
                String regionCode = sysRegionByName.getData().getRegionCode();
                directoriesApply.setNewRegionCode(regionCode);
            }
        }
        directoriesApply.setNewRegion(newCompany);

        User user = UserUtil.getLoginUser();
        String operator = "变更人";
        if (Objects.nonNull(user)) {
            operator = user.getNickname();
        }
        directoriesApply.setChangeTime(new Date());
        directoriesApply.setOperator(operator);
        directoriesApply.setAuditStatus(AuditStatusEnum.APPLY.getKey());

        directoriesApplyMapper.updateById(directoriesApply);
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
        DirectoriesApply directoriesApply = directoriesApplyMapper.selectById(id);
        if (Objects.isNull(directoriesApply)) {
            throw new SofnException("待审核数据不存在!");
        }
        if (StringUtils.hasText(directoriesApply.getAuditStatus()) &&
                !AuditStatusEnum.APPLY.getKey().equals(directoriesApply.getAuditStatus())) {
            throw new SofnException("当前数据不需要审核!");
        }
        User user = UserUtil.getLoginUser();
        String auditor = "审核人";
        if (Objects.nonNull(user)) {
            auditor = user.getNickname();
        }
        Date now = new Date();
        directoriesApply.setAuditor(auditor);
        directoriesApply.setAuditTime(now);
        directoriesApply.setAuditStatus(auditStatus);
        directoriesApply.setOpinion(opinion);
        directoriesApplyMapper.updateById(directoriesApply);

        if (AuditStatusEnum.PASS.getKey().equals(auditStatus)) {
            DirectoriesRecord directoriesRecord = new DirectoriesRecord();
            BeanUtils.copyProperties(directoriesApply, directoriesRecord);
            directoriesRecordService.save(directoriesRecord);

            Directories Directories = new Directories();
            Directories.setId(directoriesApply.getId());
            Directories.setChangeTime(directoriesApply.getChangeTime());
            String operator = "操作人";
            if (Objects.nonNull(user)) {
                operator = user.getNickname();
            }
            Directories.setOperator(operator);
            String newName = directoriesApply.getNewName();
            if (StringUtils.hasText(newName)) {
                Directories.setOldName(newName);
            }
            String newCompany = directoriesApply.getNewRegion();
            if (StringUtils.hasText(newCompany)) {
                Directories.setOldRegion(newCompany);
            }
            String newRegionCode = directoriesApply.getNewRegionCode();
            if (StringUtils.hasText(newRegionCode)) {
                Directories.setOldRegionCode(newRegionCode);
            }
            directoriesService.update(Directories);
        }
        redisHelper.del(redisKey);
        redisHelper.del(redisKeyByProvinceCode);

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
    public PageUtils<DirectoriesApply> listByParams(Map<String, Object> params, Integer pageNo, Integer pageSize) {
        PageHelper.offsetPage(pageNo, pageSize);
        if (AuditStatusEnum.UNDO.getKey().equals(params.get("auditStatus"))) {
            params.put("auditStatus", "");
            params.put("auditStatus2", AuditStatusEnum.UNDO.getKey());
        }
        List<DirectoriesApply> directoriesApplies = directoriesApplyMapper.listByParams(params);
        setAuditStatusName(directoriesApplies);
        return PageUtils.getPageUtils(new PageInfo(directoriesApplies));
    }

    @Override
    public DirectoriesRecord getForManage(String id) {
        DirectoriesRecord directoriesRecord = directoriesRecordService.getLastChangeByCode(id);
        if (Objects.nonNull(directoriesRecord)) {
            return directoriesRecord;
        } else {
            directoriesRecord = new DirectoriesRecord();
            Directories directories = directoriesService.get(id);
            if (Objects.nonNull(directories)) {
                BeanUtils.copyProperties(directories, directoriesRecord, "operator");
            }
            return directoriesRecord;
        }
    }

    private void setAuditStatusName(List<DirectoriesApply> directoriesApplies) {
        if (!CollectionUtils.isEmpty(directoriesApplies)) {
            for (DirectoriesApply directoriesApply : directoriesApplies) {
                directoriesApply.setAuditStatusName(AuditStatusEnum.getVal(directoriesApply.getAuditStatus()));
                String auditSatuts = directoriesApply.getAuditStatus();
                directoriesApply.setCanAudit("2".equals(auditSatuts));
                directoriesApply.setCanEdit(!"2".equals(auditSatuts));
            }
        }
    }
}
