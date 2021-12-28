package com.sofn.fyrpa.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@ApiModel("保护区数量以及市级的集合vo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CityAreasVo {

    @ApiModelProperty(name = "amount",value = "保护区数量")
    private Integer amount;

    @ApiModelProperty(name = "regionCode",value = "区域码")
    private String  regionCode;

    @ApiModelProperty(name = "regionName",value = "区域名称")
    private String  regionName;

    private List<CityVo> cityVoList;
}
