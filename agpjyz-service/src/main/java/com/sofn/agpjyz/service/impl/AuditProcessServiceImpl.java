package com.sofn.agpjyz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sofn.agpjyz.mapper.AuditProcessMapper;
import com.sofn.agpjyz.model.AuditProcess;
import com.sofn.agpjyz.service.AuditProcessService;
import com.sofn.agpjyz.vo.AuditProcessVo;
import com.sofn.common.utils.IdUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 审核进程记录服务类
 **/
@Service(value = "auditProcessService")
public class AuditProcessServiceImpl implements AuditProcessService {

    @Resource
    private AuditProcessMapper apMapper;

    @Override
    public AuditProcess save(AuditProcess entity) {
        entity.setId(IdUtil.getUUId());
        Integer charLength = 1000;
        String auditOpinion = entity.getAuditOpinion();
        boolean flag = StringUtils.hasText(auditOpinion) && auditOpinion.length() > charLength;
        if (flag) {
            entity.setAuditOpinion(auditOpinion.substring(0, charLength));
        }
        apMapper.insert(entity);
        if (flag) {
            apMapper.updateAuditOpinion(entity.getId(), auditOpinion.substring(charLength));
        }
        return entity;
    }

    @Override
    public List<AuditProcessVo> listBySourceId(String sourceId) {
        QueryWrapper<AuditProcess> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("SOURCE_ID", sourceId).orderByDesc("AUDIT_TIME");
        List<AuditProcess> list = apMapper.selectList(queryWrapper);
        if (!CollectionUtils.isEmpty(list)) {
            List<AuditProcessVo> listVo = new ArrayList<>(list.size());
            for (AuditProcess ap : list) {
                listVo.add(AuditProcessVo.entity2Vo(ap));
            }
            return listVo;
        }
        return null;
    }
}
