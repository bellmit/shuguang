package com.sofn.ducss.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.ducss.model.ProductionUsageSum;

import java.util.List;

public interface ProductionUsageSumService extends IService<ProductionUsageSum> {

    List<ProductionUsageSum> selectProductionUsageSum(List<String> areaIds,String year,  List<String> statusList);

}
