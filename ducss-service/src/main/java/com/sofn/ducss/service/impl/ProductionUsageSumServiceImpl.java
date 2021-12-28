package com.sofn.ducss.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sofn.ducss.mapper.ProductionUsageSumMapper;
import com.sofn.ducss.model.ProductionUsageSum;
import com.sofn.ducss.service.ProductionUsageSumService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductionUsageSumServiceImpl extends ServiceImpl<ProductionUsageSumMapper, ProductionUsageSum> implements ProductionUsageSumService {

    @Override
    public List<ProductionUsageSum> selectProductionUsageSum(List<String> areaIds, String year, List<String> statusList) {
        return baseMapper.selectProductionUsageSum(areaIds, year, statusList);
    }
}
