package com.sofn.agsjsi.vo;

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

    @ApiModelProperty(value = "行政区划下的行政区划")
    private List<SysRegionTreeVo> children;


}
