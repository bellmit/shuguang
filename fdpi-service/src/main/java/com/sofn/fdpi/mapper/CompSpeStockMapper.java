package com.sofn.fdpi.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.fdpi.model.CompSpeStock;
import com.sofn.fdpi.model.CompSpeStockFlow;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CompSpeStockMapper extends BaseMapper<CompSpeStock> {
    int batchInsert(@Param("list") List<CompSpeStock> list);
    int batchInsertForStockFlow(List<CompSpeStockFlow> list);
}
