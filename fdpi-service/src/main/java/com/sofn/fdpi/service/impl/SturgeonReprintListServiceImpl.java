package com.sofn.fdpi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sofn.common.utils.IdUtil;
import com.sofn.fdpi.mapper.SturgeonReprintListMapper;
import com.sofn.fdpi.model.SturgeonReprintList;
import com.sofn.fdpi.service.SturgeonReprintListService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service("sturgeonReprintListService")
public class SturgeonReprintListServiceImpl implements SturgeonReprintListService {

    @Resource
    private SturgeonReprintListMapper sturgeonReprintListMapper;

    @Override
    @Transactional
    public void saveOrUpdate(String reprintId, List<String> signboardIds) {
        QueryWrapper<SturgeonReprintList> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("reprint_id", reprintId);
        sturgeonReprintListMapper.delete(queryWrapper);
        for (String signboardId : signboardIds) {
            SturgeonReprintList entity = new SturgeonReprintList();
            entity.setId(IdUtil.getUUId());
            entity.setReprintId(reprintId);
            entity.setSignboardId(signboardId);
            sturgeonReprintListMapper.insert(entity);
        }
    }

    @Override
    public List<String> listByReprintId(String reprintId) {
        QueryWrapper<SturgeonReprintList> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("reprint_id", reprintId);
        return sturgeonReprintListMapper.selectList(queryWrapper).
                stream().map(SturgeonReprintList::getSignboardId).collect(Collectors.toList());
    }
}
