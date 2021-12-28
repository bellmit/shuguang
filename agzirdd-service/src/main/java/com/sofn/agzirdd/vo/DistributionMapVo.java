package com.sofn.agzirdd.vo;

import com.sofn.agzirdd.model.DistributionMap;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName DistributionMapVo
 * @Description TODO
 * @Author Chlf
 * @Date2020/4/3 10:50
 * Version1.0
 **/
@Data
public class DistributionMapVo extends DistributionMap {
    @ApiModelProperty(name = "combinName", value = "同一物种分布地区合并一处显示，前端直接取值显示即可")
    private String combinName;

    @ApiModelProperty(name = "adLevel", value = "地图所在级别")
    private String adLevel;

    @ApiModelProperty(name = "adCode", value = "地图所在区划编码")
    private String adCode;

    @ApiModelProperty(name = "speciesImgUrl", value = "新发物种图片URL")
    private String speciesImgUrl;

}
