package com.sofn.sys.vo;

import com.sofn.sys.model.SysRegion;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * 前端传入的内容BEAN
 * 校验类
 * Created by heyongjie on 2019/5/29 13:34
 */
@Data
@ApiModel(value="行政区划VO对象")
public class SysRegionForm {

    @ApiModelProperty(value = "行政区划ID，修改时必传")
    private String id;

    @Length(max = 64,message = "父节点内容超长")
    @ApiModelProperty(value = "行政区划父ID")
    private String parentId;

    @NotBlank(message = "行政区划名称不能为空")
    @Length(max = 50,message = "行政区划名称超长")
    @ApiModelProperty(value = "行政区划名称")
    private String regionName;

    @NotBlank(message = "行政区划代码不能为空")
    @Length(max = 64,message = "行政区划代码超长")
    @ApiModelProperty(value = "行政区划代码")
    private String regionCode;

    @Min(value = 0)
    @ApiModelProperty(value = "行政区划顺序，默认等于0")
    private Integer sortid = 0 ;

    @Length(max=400,message = "备注超长")
    @ApiModelProperty(value = "行政区划备注")
    private String remark = "";

    @NotBlank(message = "请选择区域")
    @Length(max=32,message = "区域超长")
    @ApiModelProperty(value = "区域ID，添加的时候必传，获取区域列表时约定区域的字典为areaType")
    private String areaId;

    @ApiModelProperty(value = "区域名称，查询的时候如果有会返回，添加修改时无效")
    private String areaName;

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
     * 纬度
     */
    @Min(value = 2010)
    @ApiModelProperty(value = "版本年份")
    private Integer versionYear;

    /**
     * 将表单对象转为持久层对象
     * @param sysRegionForm  表单对象
     * @return 持久层对象
     */
    public static SysRegion getSysRegion(SysRegionForm sysRegionForm){
        SysRegion sysRegion = new SysRegion();
        BeanUtils.copyProperties(sysRegionForm,sysRegion);
        return sysRegion;
    }

    /**
     * 将持久层对象转为VO 方便返回前端要的格式
     * @param sysRegion
     * @return
     */
    public static SysRegionForm getSysRegionForm(SysRegion sysRegion){
        SysRegionForm sysRegionForm = new SysRegionForm();
        BeanUtils.copyProperties(sysRegion,sysRegionForm);
        if(StringUtils.isBlank(sysRegionForm.getRemark())){
            sysRegionForm.setRemark("");
        }
        if(StringUtils.isBlank(sysRegionForm.getAreaId())){
            sysRegionForm.setAreaId("");
        }
        return sysRegionForm;

    }

}
