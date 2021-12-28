package com.sofn.sys.vo;

import com.sofn.common.tree.TreeNode;
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
public class SysOrgVo implements TreeNode<SysOrgVo> {

  @ApiModelProperty(value = "ID")
  private String id;

  @ApiModelProperty(value = "组织机构名称")
  private String organizationName;
  /**
   * 父编号
   */
  @ApiModelProperty(value = "父编号")
  private String parentId;

  /**
   * 系统归属
   */
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
  @ApiModelProperty(value = "区域码串")
  private String regioncode;

  @ApiModelProperty(value = "完整区域码")
  private List<String> regionFullCode;

  @ApiModelProperty(value = "完整区域名称")
  private List<String> regionFullName;

  /**
   * 区域码
   */
  @ApiModelProperty(value = "所在地址区域码")
  private String addressLastCode;

  /**
   * 区域码
   */
  @ApiModelProperty(value = "地址区域码串")
  private String address;

  @ApiModelProperty(value = "完整所在地址区域码")
  private List<String> addressFullCode;

  @ApiModelProperty(value = "完整所在地址名称")
  private List<String> addressFullName;

  /**
   * 级别
   */
  @ApiModelProperty(value = "级别")
  @NotBlank(message = "机构级别不能为空")
  private String organizationLevel;

  @ApiModelProperty(value = "级别名称")
  private String organizationLevelName;

  /**
   * 是否第三方机构
   */
  @ApiModelProperty(value = "是否非行政机构（Y:行政机构/N:非行政机构）")
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

  /**
   * 机构appids
   */
  @ApiModelProperty(value = "机构所属系统的appid集合")
  private List<String> appids;

  /**
   * 子机构集合
   */
  @ApiModelProperty(value = "子机构集合")
  List<SysOrgVo> children;

  @Override
  public String getName() {
    return organizationName;
  }

  @Override
  public void setChildren(List<SysOrgVo> children) {
    this.children = children;
  }
}
