package com.sofn.report.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;


@TableName("t_order_item")
@Data
public class TOrderItem {

    @TableId(value = "order_item_id", type = IdType.AUTO)
    private long orderItemId;
    private long orderId;
    private long userId;
    private String goodName;
}
