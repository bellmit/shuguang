package com.sofn.agpjpm.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.common.model.BaseModel;
import lombok.Data;

import java.util.Date;

@Data
@TableName("monitor")
public class Monitor extends BaseModel<Monitor> {

    /**
     * 保护点id
     */
    private String protectId;
    /**
     * 调查日期
     */
    private Date surveyDate;
    /**
     * 调查单位
     */
    private String surveyDept;
    /**
     * 调查人
     */
    private String surveyor;
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
     * 详细地址
     */
    private String addr;
    /**
     * 联系电话
     */
    private String tel;
    /**
     * 目标物种
     */
    private String targetSpec;
    /**
     * 受损面积(亩)
     */
    private Double damage;
    /**
     * (目标物种)其它
     */
    private String other;
    /**
     * 围栏设施
     */
    private String fence;
    /**
     * 看护房
     */
    private String nurse;
    /**
     * 警示牌
     */
    private String warning;
    /**
     * 巡护路
     */
    private String patrol;
    /**
     * 瞭望塔
     */
    private String tower;
    /**
     * 其他设施
     */
    private String otherFacilities;
    /**
     * 年均温
     */
    private Double avgTemp;
    /**
     * 年均降雨量
     */
    private Double avgRainfall;
    /**
     * 种数(优势伴生物种）
     */
    private Integer superSpecies;
    /**
     * 总株、苗数(优势伴生物种）
     */
    private Integer superSeedling;
    /**
     * 科数(优势伴生物种）
     */
    private Integer superFamily;
    /**
     * 属数(优势伴生物种）
     */
    private Integer superGenera;
    /**
     * 生长状况(优势伴生物种）
     */
    private String superGrowth;
    /**
     * 群落覆盖度(%)(优势伴生物种）
     */
    private Double superCover;
    /**
     * 分布面积（亩）(优势伴生物种）
     */
    private Double superArea;
    /**
     * 返青期(开始时间)
     */
    private Date trunGreenStart;
    /**
     * 返青期(结束时间)
     */
    private Date trunGreenEnd;
    /**
     * 枯萎/落叶期(开始时间)
     */
    private Date witherStart;
    /**
     * 枯萎/落叶期(结束时间)
     */
    private Date witherEnd;
    /**
     * 种类数(入侵物种)
     */
    private Integer invadeSpecies;
    /**
     * 物种名称(入侵物种)
     */
    private String invadeSpecName;
    /**
     * 分布面积（亩）(入侵物种）
     */
    private Double invadeArea;
    /**
     * 危害程度(入侵物种）
     */
    private String invadeHazard;
    /**
     * 铲除情况(入侵物种）
     */
    private String invadeEradicate;
}
