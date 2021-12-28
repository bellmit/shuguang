package com.sofn.agzirdd.sysapi.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 *
 * 行政区划代码树结构
 * @author Chenlf
 */
@Data
@ApiModel
public class SysRegionTreeVo implements Serializable {

    @ApiModelProperty(value = "行政区划ID")
    private String id;

    @ApiModelProperty(value = "行政区划名称")
    private String regionName;

    @ApiModelProperty(value = "行政区划代码")
    private String regionCode;

    @ApiModelProperty(value = "行政区划排序")
    private Integer sort ;

    @ApiModelProperty(value = "父行政区划ID")
    private String parentId;

    @ApiModelProperty(value = "备注")
    private String remark = "";

    @ApiModelProperty(value = "区域ID")
    private String areaId;

    @ApiModelProperty(value = "区域名称")
    private String areaName;

    @ApiModelProperty(name = "recoveryOperations", value = "是否启动回收行动：0-否；1-是")
    private String recoveryOperations;

    @ApiModelProperty(name = "startover", value = "是否开启确认：0-否；1-是；recoveryOperations=1时候有效")
    private String startover;

    @ApiModelProperty(value = "行政区划下的行政区划")
    private List<SysRegionTreeVo> children;

    @ApiModelProperty(value = "回收行动县列表Id")
    private String recycleCountyId;


}
