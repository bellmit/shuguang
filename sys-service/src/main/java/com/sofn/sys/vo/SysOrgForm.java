package com.sofn.sys.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 *@Description
 *@Author quzhijie
 *@Date 2020/3/19 17:21
 *@Version 1.0
 **/
@Data
@ApiModel
public class SysOrgForm {

  @ApiModelProperty(value = "ID")
  private String id;

  @ApiModelProperty(value = "组织机构名称")
  @NotBlank(message = "组织机构名称不能为空")
  private String organizationName;
  /**
   * 父编号
   */
  @NotBlank(message = "父编号不能为空")
  @ApiModelProperty(value = "父编号")
  private String parentId;

  /**
   * 系统归属
   */
  @NotEmpty(message = "系统归属APPID不能为空")
  @ApiModelProperty(value = "系统归属AppID")
  private List<String> appIds;

  /**
   * 区域码
   */
  @ApiModelProperty(value = "区域码")
  private String regionLastCode;

  /**
   * 区域码
   */
  @ApiModelProperty(value = "所在地址区域码")
  @NotBlank(message = "所在地址区域码不能为空")
  private String addressLastCode;

  /**
   * 级别
   */
  @ApiModelProperty(value = "级别")
  private String organizationLevel;

  /**
   * 是否第三方机构
   */
  @ApiModelProperty(value = "是否非行政机构（Y:行政机构/N:非行政机构）")
//  @NotBlank(message = "是否非行政机构不能为空")
  private String thirdOrg;

  /**
   * 机构所在地址详细地址
   */
  @ApiModelProperty(value = "所在地址详细地址")
  private String addressDetail;

  /**
   * 负责人
   */
  @ApiModelProperty(value = "负责人")
  private String principal;

  /**
   * 负责人电话
   */
  @ApiModelProperty(value = "负责人电话")
  private String phone;

  /**
   * 经度
   */
  @ApiModelProperty(value = "经度")
  private String longitude;

  /**
   * 纬度
   */
  @ApiModelProperty(value = "纬度")
  private String latitude;
}
