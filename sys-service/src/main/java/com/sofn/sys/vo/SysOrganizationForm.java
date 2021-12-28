package com.sofn.sys.vo;


import com.sofn.sys.model.SysOrg;
import com.sofn.sys.model.SysOrganization;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

/**
 * Created by sofn
 */

@Data
@ApiModel(value="组织管理对象，修改的时候必须传递完整信息，如果为空，会删除已有的凭证")
public class SysOrganizationForm  {

    @ApiModelProperty(value = "组织机构管理ID")
    private String id;

    /**
     * 组织机构名称
     */
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
     * 是否删除
     */
    @ApiModelProperty(value = "是否删除")
    private String delFlag;

    /**
     * 叶子节点
     */
    @ApiModelProperty(value = "叶子节点")
    private String leaf;

    /**
     * 机构类型（行政机构/非行政机构）
     */
    @ApiModelProperty(value = "机构类型（行政机构/非行政机构）")
    private String thirdOrg;

    /**
     *  级别
     */
    @ApiModelProperty(value = "级别")
    private String organizationLevel;

    /**
     *  级别名称
     */
    @ApiModelProperty(value = "级别名称")
    private String organizationLevelName;

    /**
     * 区域码
     */
    @ApiModelProperty(value = "区域码")
    private String regioncode;

    /**
     * 区域码名称
     */
    @ApiModelProperty(value = "区域码名称")
    private String regioncodeName;

    @ApiModelProperty(value = "区域码")
    @NotBlank(message = "区域码不能为空")
    private String regionLastCode;

    /**
     * 所在地址
     */
    @ApiModelProperty(value = "所在地址")
    private String address;

    /**
     * 所在地址名称
     */
    @ApiModelProperty(value = "所在地址名称")
    private String addressName;

    @ApiModelProperty(value = "所在地址区域码")
    @NotBlank(message = "所在地址区域码不能为空")
    private String addressLastCode;

    /**
     * 机构所在地址详细地址
     */
    @ApiModelProperty(value = "所在地址详细地址")
    private String addressDetail;


  /**
     * 机构职能
     */
    @ApiModelProperty(value = "机构职能")
    private String orgFunction;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private String priority;

    /**
     * 资质证书信息
     */
    @ApiModelProperty(value = "资质证书信息，为一个MAP，一个资质证书可能有多个文件;key=资质证书类型" +
            "，从字典中选择;values为上传资质证书后的文件ID，格式为:{\"123\":[\"a\",\"b\"]}")
    private Map<String, List<String>> calificacionTypeInfo;

    @ApiModelProperty(value = "资质证书信息，KEY为证书类型ID，value为证书所对应的文件信息，为List，查询时会返回")
    private Map<String,List<SysFileManageVo>> cTypeAndFileInfo;

    /**
     * 负责人
     */
    @ApiModelProperty(value = "负责人")
    @Length(max = 25,message = "负责人超长")
    private String principal;

    /**
     * 负责人电话
     */
    @ApiModelProperty(value = "负责人电话")
    @Length(max = 12,message = "负责人电话超长")
    private String phone;

    @ApiModelProperty(value = "排序，正在使用")
    private Integer sort;

    @ApiModelProperty(value = "行政区划详情")
    private SysRegionInfoVo regionInfoVo;

    @ApiModelProperty(value = "所在地区划详情")
    private SysRegionInfoVo addressRegionInfoVo;

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

    public String makeSelfAsParentIds() {
        return getParentIds() + getId() + "/";
    }

    public String getId() {
        return id;
    }

    public String getParentIds() {
        return parentIds;
    }

    public static SysOrganization getSysOrganization(SysOrganizationForm sysOrganizationForm){
        SysOrganization sysOrganization = new SysOrganization();
        BeanUtils.copyProperties(sysOrganizationForm,sysOrganization);
        return sysOrganization;
    }

    public static SysOrganizationForm getSysOrganizationForm(SysOrganization sysOrganization){
        SysOrganizationForm sysOrganizationForm = new SysOrganizationForm();
        BeanUtils.copyProperties(sysOrganization,sysOrganizationForm);
        return sysOrganizationForm;
    }

    public static SysOrganizationForm getSysOrganizationForm(SysOrg sysOrg){
        SysOrganizationForm sysOrganizationForm = new SysOrganizationForm();
        BeanUtils.copyProperties(sysOrg,sysOrganizationForm);
        return sysOrganizationForm;
    }
}
