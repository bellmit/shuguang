package com.sofn.fdpi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sofn.fdpi.mapper.CitesForm1Mapper;
import com.sofn.fdpi.model.CitesForm1;
import com.sofn.fdpi.service.CitesForm1Service;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("citesForm1Service")
public class CitesForm1ServiceImpl implements CitesForm1Service {

    @Resource
    private CitesForm1Mapper citesForm1Mapper;

    @Override
    public List<CitesForm1> listAll() {
        QueryWrapper<CitesForm1> queryWrapper = new QueryWrapper<>();
        return citesForm1Mapper.selectList(queryWrapper);
    }
}
