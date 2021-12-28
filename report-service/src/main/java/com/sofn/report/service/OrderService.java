package com.sofn.report.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.report.model.TOrder;

import java.util.List;

public interface OrderService extends IService<TOrder> {

    List<TOrder> getOrderItemList(Long orderId);
}
