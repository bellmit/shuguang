package com.sofn.fdpi.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description
 * @Author wg
 * @Date 2021/5/24 11:11
 **/
@Data
public class Target implements Serializable {
    //来源企业id
    private int sourceId;
    //目标企业id
    private int targetId;
    //交易数量
    private Double dealNum;
    //数据类型
    private String dataType;
    //交易时间
    private Date subLabel;
}
