package com.sofn.fdpi.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sofn.common.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@TableName("MANAGE_RES")
@ApiModel(value = "收容model")
@Data
public class ManageRes extends BaseModel<ManageRes> {
    /**
     * 收容ID
     */
    @ApiModelProperty(value = "收容ID")
    private String id;

    /**
     * 收容时间
     */
    @ApiModelProperty(value = "收容时间")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(
            pattern = "yyyy-MM-dd",
            timezone = "GMT+8")
    private Date asylumDate;

    /**
     * 救护地点
     */
    @ApiModelProperty(value = "救护地点")
    private String rescueSite;
    @ApiModelProperty(value = "标识编码")
    private String code;
    /**
     * 处置单位
     */
    @ApiModelProperty(value = "处置单位")
    private String disposalUnit;

    /**
     * 处置时间
     */
    @ApiModelProperty(value = "处置时间")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(
            pattern = "yyyy-MM-dd",
            timezone = "GMT+8")
    private Date disposalTime;

    /**
     * 救护物种
     */
    @ApiModelProperty(value = "救护物种")
    private String rescueSpe;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private int status;

    /**
     * 操作
     */
    @ApiModelProperty(value = "操作")
    private String operation;

    /**
     * 救护时间
     */
    @ApiModelProperty(value = "救护时间")
    private Date resTime;

    /**
     * 救护过程
     */
    @ApiModelProperty(value = "救护过程")
    private String resProcess;

    /**
     * 救护结果
     */
    @ApiModelProperty(value = "救护结果")
    private String resFruit;

    /**
     * 处置意见
     */
    @ApiModelProperty(value = "处置意见")
    private String disView;

    /**
     * 处置方案
     */
    @ApiModelProperty(value = "处置方案")
    private String disPlan;

    /**
     * 省
     */
    @ApiModelProperty(value = "省")
    private String province;

    /**
     * 市
     */
    @ApiModelProperty(value = "市")
    private String city;

    /**
     * 区
     */
    @ApiModelProperty(value = "区")
    private String area;
    @ApiModelProperty(value = "当前组织机构id")
    private String orgId;

    @ApiModelProperty(value = "发现人员")
    private String discoverer;
}

