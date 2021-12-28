package com.sofn.agsjdm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sofn.agsjdm.api.JsiApi;
import com.sofn.agsjdm.mapper.TargetClassifyMapper;
import com.sofn.agsjdm.model.TargetClassify;
import com.sofn.agsjdm.service.TargetClassifyService;
import com.sofn.common.exception.SofnException;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 指标分类服务类
 */
@Service(value = "targetClassifyService")
public class TargetClassifyServiceImpl implements TargetClassifyService {

    @Autowired
    private TargetClassifyMapper tsMapper;

    @Override
    public TargetClassify save(TargetClassify entity) {
        entity.setId(IdUtil.getUUId());
        tsMapper.insert(entity);
        return entity;
    }

    @Override
    public void delete(String id) {
        tsMapper.deleteById(id);
    }

    @Override
    public void update(TargetClassify entity) {
        if (Objects.isNull(tsMapper.selectById(entity.getId()))) {
            throw new SofnException("待修改数据不存在");
        }
        tsMapper.updateById(entity);
    }

    @Override
    public TargetClassify get(String id) {
        return tsMapper.selectById(id);
    }

    @Override
    public List<TargetClassify> list(String targetVal) {
        QueryWrapper<TargetClassify> queryWrapper = new QueryWrapper<>();
        if (StringUtils.hasText(targetVal)) {
            queryWrapper.like("TARGET_VAL", targetVal);
        }
        queryWrapper.orderByDesc("TARGET_VAL");
        return tsMapper.selectList(queryWrapper);
    }

    @Override
    public PageUtils<TargetClassify> listPage(String targetVal, Integer pageNo, Integer pageSize) {
        PageHelper.offsetPage(pageNo, pageSize);
        return PageUtils.getPageUtils(new PageInfo<>(this.list(targetVal)));
    }
}
