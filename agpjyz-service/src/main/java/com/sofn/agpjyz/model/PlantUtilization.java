package com.sofn.agpjyz.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.common.model.BaseModel;
import lombok.Data;

import java.util.Date;


/**
 * 植物利用类
 **/
@Data
@TableName("PLANT_UTILIZATION")
public class PlantUtilization extends BaseModel<PlantUtilization> {

    /**
     * 省
     */
    private String province;
    /**
     * 省名
     */
    private String provinceName;
    /**
     * 市
     */
    private String city;
    /**
     * 市名
     */
    private String cityName;
    /**
     * 县
     */
    private String county;
    /**
     * 县名
     */
    private String countyName;
    /**
     * 物种名称ID
     */
    private String specId;
    /**
     * 物种名称
     */
    private String specValue;
    /**
     * 拉丁学名
     */
    private String latin;
    /**
     * 产业利用ID
     */
    private String industrialId;
    /**
     * 产业利用名称
     */
    private String industrialValue;
    /**
     * 利用单位名称
     */
    private String utilizationUnit;
    /**
     * 用途
     */
    private String purpose;
    /**
     * 具体内容
     */
    private String concreteContent;
    /**
     * 价值
     */
    private String worth;
    /**
     * 产值(万元)
     */
    private Double outputValue;
    /**
     * 市场需求
     */
    private String marketDemand;
    /**
     * 人工栽培技术
     */
    private String artificial;
    /**
     * 人工栽培技术(其它)
     */
    private String artificialOther;
    /**
     * 其他
     */
    private String other;
    /**
     * 上报人
     */
    private String reportPerson;
    /**
     * 上报时间
     */
    private Date reportTime;


}
