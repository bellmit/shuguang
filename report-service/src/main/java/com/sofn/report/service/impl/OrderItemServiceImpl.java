package com.sofn.report.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sofn.report.mapper.OrderItemMapper;
import com.sofn.report.model.TOrderItem;
import com.sofn.report.service.OrderItemService;
import org.springframework.stereotype.Service;

@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, TOrderItem> implements OrderItemService {
    
}
