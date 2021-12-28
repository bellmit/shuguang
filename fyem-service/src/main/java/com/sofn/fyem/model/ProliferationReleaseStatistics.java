package com.sofn.fyem.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;


/**
 * @Description: 增殖放流情况统计表
 * @Author: mcc
 */
@TableName("PROLIFERATION_RELEASE_STATISTICS")
@Data
public class ProliferationReleaseStatistics extends Model<ProliferationReleaseStatistics> {

    @ApiModelProperty(name = "id", value = "id")
    private String id;

    @ApiModelProperty(name = "belongYear", value = "所属年度")
    private String belongYear;

    @ApiModelProperty(name = "speciesType", value = "物种类型")
    private String speciesType;

    @ApiModelProperty(name = "reporManagementId", value = "上报信息id")
    private String reporManagementId;

    @ApiModelProperty(value = "省级id")
    private String provinceId;

    @ApiModelProperty(value = "市级id")
    private String cityId;

    @ApiModelProperty(value = "区县id")
    private String countyId;

    @ApiModelProperty(name = "planReleaseCapital", value = "计划放流资金")
    private Double planReleaseCapital;

    @ApiModelProperty(name = "planReleaseAmount", value = "计划放流数量")
    private Double planReleaseAmount;

    @ApiModelProperty(name = "practicalReleaseCapital", value = "实际放流资金")
    private Double practicalReleaseCapital;

    @ApiModelProperty(name = "practicalReleaseAmount", value = "实际放流数量")
    private Double practicalReleaseAmount;

    @ApiModelProperty(name = "yearReleaseCapital", value = "上一年度放流资金")
    private Double yearReleaseCapital;

    @ApiModelProperty(name = "yearReleaseAmount", value = "上一年度放流数量")
    private Double yearReleaseAmount;

    @ApiModelProperty(name = "roleCode", value = "角色code")
    private String roleCode;

    @ApiModelProperty(name = "status", value = "状态")
    private String status;

    @ApiModelProperty(name = "createUserId", value = "填报人id")
    private String createUserId;

    @ApiModelProperty(name = "organizationName", value = "填报单位名称")
    private String organizationName;

    @ApiModelProperty(name = "organizationId", value = "填报单位id")
    private String organizationId;

    @JSONField(format = "yyyy-MM-dd")
    @ApiModelProperty(name = "createTime", value = "填报时间")
    private Date createTime;

}