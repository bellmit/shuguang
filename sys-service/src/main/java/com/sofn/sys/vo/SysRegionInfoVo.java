package com.sofn.sys.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 区域省市县VO
 * @author heyongjie
 * @date 2019/12/17 18:15
 */
@Data
@ApiModel(value="区域信息VO对象")
public class SysRegionInfoVo {

    @ApiModelProperty(value = "省代码")
    private String province;

    @ApiModelProperty(value = "市代码")
    private String city;

    @ApiModelProperty(value = "区代码")
    private String area;

    @ApiModelProperty(value = "省名称")
    private String provinceName;

    @ApiModelProperty(value = "市名称")
    private String cityName;

    @ApiModelProperty(value = "区名称")
    private String areaName;

}
