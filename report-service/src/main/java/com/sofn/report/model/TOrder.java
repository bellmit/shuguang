package com.sofn.report.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;
import java.util.List;


@TableName("t_order")
@Data
public class TOrder {

    @TableId(value = "order_id", type = IdType.AUTO)
    private long orderId;
    private long userId;
    private String orderStatus;
    private String orderExpress;
    private Date orderPlaceDatetime;
    private String orderPayWay;
    private Date orderPayDatetime;
    @TableField(exist = false)
    private List<TOrderItem> orderItemList;
}
