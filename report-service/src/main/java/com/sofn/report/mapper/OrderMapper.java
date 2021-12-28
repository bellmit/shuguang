package com.sofn.report.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.report.model.TOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper extends BaseMapper<TOrder> {

    List<TOrder> selectOrderItemList(@Param("orderId") Long orderId);

}
