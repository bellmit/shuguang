package com.sofn.ahhrm.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.common.model.BaseModel;
import lombok.Data;

import java.util.Date;

/**
 * 基础信息表
 **/
@Data
@TableName("baseinfo")
public class Baseinfo extends BaseModel<Baseinfo> {

    /**
     * 监测点名称
     */
    private String pointName;
    /**
     * 省
     */
    private String province;
    /**
     * 市
     */
    private String city;
    /**
     * 县
     */
    private String county;
    /**
     * 省名
     */
    private String provinceName;
    /**
     * 市名
     */
    private String cityName;
    /**
     * 县名
     */
    private String countyName;
    /**
     * 经度
     */
    private String longitude;
    /**
     * 纬度
     */
    private String latitude;
    /**
     * 所属类型
     */
    private String type;
    /**
     * 固定电话/手机
     */
    private String tel;
    /**
     * 电子邮箱
     */
    private String email;
    /**
     * 状态
     */
    private String status;
    /**
     * 监测人
     */
    private String monitor;
    /**
     * 监测时间
     */
    private Date monitoringTime;

    /**
     * 公母比例
     */
    @TableField(exist = false)
    private String proportion;
    /**
     * 有效群体含量
     */
    @TableField(exist = false)
    private Double effectiveGroup;

}
