package com.sofn.fyem.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Description: 上报管理表
 * @Author: mcc
 */
@TableName("REPOR_MANAGEMENT")
@Data
public class ReporManagement {

    @ApiModelProperty(name = "id", value = "id")
    private String id;

    @ApiModelProperty(name = "belongYear", value = "所属年度")
    private String belongYear;

    @ApiModelProperty(name = "provinceId",value = "省id")
    private String provinceId;

    @ApiModelProperty(name = "cityId",value = "市id")
    private String cityId;

    @ApiModelProperty(name = "countyId",value = "区县id")
    private String countyId;

    @ApiModelProperty(name = "hasBasicProliferationRelease", value = "是否填写增值放流基础数据")
    private String hasBasicProliferationRelease;

    @ApiModelProperty(name = "hasProliferationReleaseStatistics", value = "是否填写增值放流情况")
    private String hasProliferationReleaseStatistics;

    @ApiModelProperty(name = "organizationName", value = "填写单位名称")
    private String organizationName;

    @ApiModelProperty(name = "organizationId", value = "填写单位id")
    private String organizationId;

    @ApiModelProperty(name = "status", value = "状态")
    private String status;

    @ApiModelProperty(name = "roleCode", value = "角色code")
    private String roleCode;

    @ApiModelProperty(name = "remark", value = "意见")
    private String remark;

    @ApiModelProperty(name = "createUserId", value = "填写人id")
    private String createUserId;

    @ApiModelProperty(name = "createUserName", value = "填写人名称")
    private String createUserName;

    @ApiModelProperty(name = "createTime", value = "填写时间")
    private Date createTime;

}