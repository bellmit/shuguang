package com.sofn.report.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sofn.report.mapper.OrderMapper;
import com.sofn.report.model.TOrder;
import com.sofn.report.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, TOrder> implements OrderService {

    @Autowired
    OrderMapper orderMapper;

    @Override
    public List<TOrder> getOrderItemList(Long orderId) {
        return orderMapper.selectOrderItemList(orderId);
    }
}
