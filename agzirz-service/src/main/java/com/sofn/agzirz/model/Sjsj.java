package com.sofn.agzirz.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.annotation.ExcelSheetInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 事件收集模块
 * </p>
 *
 * @author simon
 * @since 2020-03-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("SJSJ")
@ApiModel(value="Sjsj对象", description="事件收集模块")
@ExcelSheetInfo(title="事件收集模块")
public class Sjsj extends Model<Sjsj> {

    @ApiModelProperty(value = "主键")
    @TableId("SJSJ_NO")
    @ExcelField(title="主键",isShow = false)
    private String sjsjNo;

    @ExcelField(title = "",isShow = false)
    @ApiModelProperty(value = "省级ID")
    @TableField("PROVINCE_ID")
    private String provinceId;

    @ExcelField(title = "",isShow = false)
    @ApiModelProperty(value = "省级ID")
    @TableField("CITY_ID")
    private String cityId;

    @ExcelField(title = "",isShow = false)
    @ApiModelProperty(value = "省级ID")
    @TableField("COUNTY_ID")
    private String countyId;

    @ApiModelProperty(value = "上报人")
    @TableField("REPORT_USER")
    @ExcelField(title="上报人",isShow = false )
    private String reportUser;

    @ApiModelProperty(value = "上报时间")
    @JSONField(format = "yyyy-MM-dd")
    @TableField("REPORT_TIME")
    @ExcelField(title="上报时间",dataFormat = "yyyy-MM-dd",sort = 1)
    private Date reportTime;

    @ApiModelProperty(value = "事件相关文件")
    @TableField("EVENT_ABT_FILES")
    @ExcelField(title="事件相关文件",isShow = false)
    private String eventAbtFiles;

    @ApiModelProperty(value = "处置机构")
    @TableField("DISPOSAL_ORGANI")
    @ExcelField(title="处置机构",sort = 7)
    private String disposalOrgani;

    @ApiModelProperty(value = "发生时间")
    @JSONField(format = "yyyy-MM-dd")
    @TableField("EVENT_TIME")
    @ExcelField(title="发生时间",dataFormat = "yyyy-MM-dd",sort = 2)
    private Date eventTime;

    @ApiModelProperty(value = "发生地点")
    @TableField("EVENT_LOCATION")
    @ExcelField(title="发生地点",isShow = false)
    private String eventLocation;

    @ApiModelProperty(value = "事件内容")
    @TableField("EVENT_CONTENT")
    @ExcelField(title="事件内容",sort = 4)
    private String eventContent;

    @ApiModelProperty(value = "处理措施")
    @TableField("TREATMENT_MEASURE")
    @ExcelField(title="处理措施",sort = 8)
    private String treatmentMeasure;

    @ApiModelProperty(value = "事件图片")
    @TableField("EVENT_IMGS")
    @ExcelField(title="事件图片",isShow = false)
    private String eventImgs;

    @ApiModelProperty(value = "事件视频")
    @TableField("EVENT_VEDIOS")
    @ExcelField(title="事件视频",isShow = false)
    private String eventVedios;

    @ApiModelProperty(value = "事件影响")
    @TableField("EVENT_AFFECT")
    @ExcelField(title="事件影响",sort = 5)
    private String eventAffect;

    @ApiModelProperty(value = "处理报告")
    @TableField("PROCESS_REPORT")
    @ExcelField(title="处理报告",isShow = false)
    private String processReport;

    @ApiModelProperty(value = "有效状态，用于逻辑删除")
    @TableField("ENABLE_STATUS")
    @ExcelField(title="有效状态，用于逻辑删除",isShow = false)
    private String enableStatus;


    /**############ 非数据库字段 ##############*/

    @TableField(exist = false)
    @ExcelField(title="",isShow = false)
    private List<Map<String, Object>> eventAbtFilesList;
    @TableField(exist = false)
    @ExcelField(title="",isShow = false)
    private List<Map<String, Object>> eventImgsList;
    @TableField(exist = false)
    @ExcelField(title="",isShow = false)
    private List<Map<String, Object>> eventVediosList;
    @TableField(exist = false)
    @ExcelField(title="",isShow = false)
    private List<Map<String, Object>> processReportList;
    @TableField(exist = false)
    @ExcelField(title="发生地点",isShow = true,sort = 3)
    private String eventLocationName;
    @TableField(exist = false)
    @ExcelField(title="上报人",isShow = true,sort = 0)
    private String reportUserName;
}
