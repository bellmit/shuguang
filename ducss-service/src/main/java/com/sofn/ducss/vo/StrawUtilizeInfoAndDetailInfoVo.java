package com.sofn.ducss.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 市场主体规模化利用信息和主体信息
 */
@Data
@ApiModel("市场主体规模化利用信息和主体信息")
public class StrawUtilizeInfoAndDetailInfoVo {

    @ApiModelProperty("市场主体名称")
    private String mainName;

    @ApiModelProperty("年份")
    private String year;

    @ApiModelProperty("法人名称")
    private String corporationName;

    @ApiModelProperty("法人电话")
    private String mobilePhone;

    @ApiModelProperty("详细地址")
    private String address;

    @ApiModelProperty("填报信息")
    private List<StrawTypeVo> strawTypeVoList;

}
