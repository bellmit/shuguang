package com.sofn.sys.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.common.model.BaseModel;
import com.sofn.common.tree.TreeNode;
import com.sofn.common.utils.BoolUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang.StringUtils;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
  * @Description: 组织机构对象
  * @Author: quzhijie
  * @Date: 2020/3/19 14:22
  **/
@ApiModel(value="组织机构对象")
@TableName("SYS_ORG")
@Data
public class SysOrg extends BaseModel<SysOrg> implements TreeNode<SysOrg> {


    @ApiModelProperty(value = "组织机构名称")
    private String organizationName;
    /**
     * 父编号
     */
    @ApiModelProperty(value = "父编号")
    private String parentId;
    /**
     * 父编号列表，如1/2/
     */
    @ApiModelProperty(value = "父编号列表")
    private String parentIds;

    /**
     * 区域码
     */
    @ApiModelProperty(value = "区域码")
    @NotBlank(message = "区域码不能为空")
    private String regionLastCode;

    /**
     * 区域码
     */
    @ApiModelProperty(value = "区域码串")
    private String regioncode;

     /**
      * 区域码
      */
     @ApiModelProperty(value = "所在地址区域码")
     @NotBlank(message = "所在地址区域码不能为空")
     private String addressLastCode;

    /**
     * 区域码
     */
    @ApiModelProperty(value = "地址区域码串")
    private String address;

    /**
     * 级别
     */
    @ApiModelProperty(value = "级别")
    private String organizationLevel;

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
     * 排序
     */
    private Integer sort;

    @TableField(exist=false)
    private List<SysOrg> children;

   @Override
   public String getName() {
     return organizationName;
   }

   public boolean isThirdOrganization(){
     return StringUtils.endsWithIgnoreCase(thirdOrg,BoolUtils.N);
   }

}
