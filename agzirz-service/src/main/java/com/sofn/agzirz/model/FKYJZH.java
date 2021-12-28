package com.sofn.agzirz.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.sofn.common.excel.annotation.ExcelField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Size;
import java.util.Date;

@Data
@TableName("FKYJZH")
public class FKYJZH extends Model<FKYJZH> {

    @ExcelField(title = "主键")
    @ApiModelProperty(name = "fkyjzhNo", value = "主键")
    @TableId("FKYJZH_NO")
    private String fkyjzhNo;

    @ApiModelProperty(value = "省级ID")
    @TableField("PROVINCE_ID")
    private String provinceId;

    @ApiModelProperty(value = "市级ID")
    @TableField("CITY_ID")
    private String cityId;

    @ApiModelProperty(value = "县级ID")
    @TableField("COUNTY_ID")
    private String countyId;

    @ExcelField(title = "发生时间")
    @JSONField(format = "yyyy-MM-dd")
    @ApiModelProperty(name = "eventTime", value = "发生时间")
    private Date eventTime;

    @ExcelField(title = "发生地点")
    @ApiModelProperty(name = "eventLocation", value = "发生地点")
    private String eventLocation;

    @ExcelField(title = "事件原因")
    @Size(max= 300,message = "事件原因字数不能超过300")
    @ApiModelProperty(name = "eventCause", value = "事件原因")
    private String eventCause;

    @ExcelField(title = "事件内容")
    @Size(max= 300,message = "事件内容字数不能超过300")
    @ApiModelProperty(name = "eventContent", value = "事件内容")
    private String eventContent;

    @ExcelField(title = "图片")
    @ApiModelProperty(name = "eventImgs", value = "图片")
    private String eventImgs;

    @ExcelField(title = "事件影响")
    @Size(max= 300,message = "事件影响字数不能超过300")
    @ApiModelProperty(name = "eventAffect", value = "事件影响")
    private String eventAffect;

    @ExcelField(title = "重要程度")
    @ApiModelProperty(name = "importance", value = "重要程度")
    private String importance;

    @ExcelField(title = "应急机构")
    @ApiModelProperty(name = "emerOrgani", value = "应急机构")
    private String emerOrgani;

    @ExcelField(title = "应急机构")
    @ApiModelProperty(name = "emerOrgani", value = "应急机构名称")
    private String emerOrganiName;

    @ExcelField(title = "上报人")
    @ApiModelProperty(name = "reportUser", value = "上报人")
    private String reportUser;

    @ExcelField(title = "上报时间")
    @JSONField(format = "yyyy-MM-dd")
    @ApiModelProperty(name = "reportTime", value = "上报时间")
    private Date reportTime;

    @ApiModelProperty(value = "有效状态，用于逻辑删除")
    @ExcelField(title="有效状态，用于逻辑删除",isShow = false)
    private String enableStatus;

}
