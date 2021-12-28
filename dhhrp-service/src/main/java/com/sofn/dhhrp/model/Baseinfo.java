package com.sofn.dhhrp.model;

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
     * 监测年份
     */
    private String year;
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
     * 温度（℃）
     */
    private Double temperature;
    /**
     * 湿度（克/立方米）
     */
    private Double humidity;
    /**
     * 光照（Lux）
     */
    private Double illumination;
    /**
     * 年降雨量（mm）
     */
    private Double rainfall;
    /**
     * 引进品种名称
     */
    private String variety;
    /**
     * 群体数量(个)
     */
    private Integer amount;
    /**
     * 公母比例
     */
    private String proportion;
    /**
     * 养殖户数量(个)
     */
    private Integer breeder;
    /**
     * 空气质量
     */
    private String air;
    /**
     * 饲草料种植面积（m2）
     */
    private Double plant;
    /**
     * 饲草料产量（t）
     */
    private Double yield;
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

}
