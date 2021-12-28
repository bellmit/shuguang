package com.sofn.dhhrp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sofn.dhhrp.mapper.AuditProcessMapper;
import com.sofn.dhhrp.model.AuditProcess;
import com.sofn.dhhrp.service.AuditProcessService;
import com.sofn.dhhrp.vo.AuditProcessVo;
import com.sofn.common.utils.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 审核进程记录服务类
 **/
@Service(value = "auditProcessService")
public class AuditProcessServiceImpl implements AuditProcessService {

    @Autowired
    private AuditProcessMapper apMapper;

    @Override
    public AuditProcess save(AuditProcess entity) {
        entity.setId(IdUtil.getUUId());
        apMapper.insert(entity);
        return entity;
    }

    @Override
    public List<AuditProcessVo> listByBaseId(String baseId) {
        QueryWrapper<AuditProcess> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("base_id", baseId).orderByDesc("audit_time");
        List<AuditProcess> list = apMapper.selectList(queryWrapper);
        if (!CollectionUtils.isEmpty(list)) {
            List<AuditProcessVo> listVo = new ArrayList<>(list.size());
            list.forEach(ap -> {
                listVo.add(AuditProcessVo.entity2Vo(ap));
            });
            return listVo;
        }
        return null;
    }
}
