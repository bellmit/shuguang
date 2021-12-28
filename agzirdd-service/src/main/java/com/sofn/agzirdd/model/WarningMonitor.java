package com.sofn.agzirdd.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
/**
* @Author Chlf
* @Description //外来入侵生物监测预警实体类
* @Date 14:09 2020/3/27
* @Param
* @Return
**/
@TableName("WARNING_MONITOR")
@Data
public class WarningMonitor extends Model<WarningMonitor> {
    @TableId(type = IdType.UUID)
    @ApiModelProperty(name = "id", value = "id", hidden = true)
    private String id;
    @ApiModelProperty(name = "belongYear", value = "所属年度")
    private String belongYear;
    @ApiModelProperty(name = "speciesName", value = "物种名")
    private String speciesName;
    @ApiModelProperty(name = "longitude", value = "经度")
    private String longitude;
    @ApiModelProperty(name = "latitude", value = "纬度")
    private String latitude;
    @ApiModelProperty(name = "classificationId", value = "指标分类id")
    private String classificationId;
    @ApiModelProperty(name = "classificationName", value = "指标分类名")
    private String classificationName;
    @ApiModelProperty(name = "amount", value = "数值")
    private String amount;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(name = "createTime", value = "创建时间(不用传递)", hidden = true)
    private Date createTime;
    @ApiModelProperty(name = "speciesId", value = "物种id")
    private String speciesId;
    @ApiModelProperty(name = "provinceId", value = "省级Id")
    private String provinceId;
    private String provinceName;
    @ApiModelProperty(name = "cityId", value = "市级Id")
    private String cityId;
    private String cityName;
    @ApiModelProperty(name = "countyId", value = "区县Id")
    private String countyId;
    private String countyName;
    @ApiModelProperty(name = "company", value = "监测单位")
    private String company;
    @ApiModelProperty(name = "monitor", value = "监测人")
    private String monitor;
    @ApiModelProperty(name = "latinName", value = "拉丁学名")
    private String latinName;
    @ApiModelProperty(name = "num", value = "数量:株或个")
    private String num;
    @ApiModelProperty(name = "coverRatio", value = "覆盖度")
    private String coverRatio;
    @ApiModelProperty(name = "workImg", value = "工作照片")
    private String workImg;
    @ApiModelProperty(name = "speciesImg", value = "物种照片")
    private String speciesImg;
    @ApiModelProperty(name = "summary", value = "结果概述")
    private String summary;
    @ApiModelProperty(name = "speciesMonitorId", value = "监测信息ID")
    private String speciesMonitorId;
}