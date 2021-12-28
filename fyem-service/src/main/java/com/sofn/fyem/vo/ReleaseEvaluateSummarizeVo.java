package com.sofn.fyem.vo;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * @Description: 放流评价-汇总
 * @Author: mcc
 */
@ApiModel
@Data
@EqualsAndHashCode(callSuper = false)
public class ReleaseEvaluateSummarizeVo {

    @ApiModelProperty(name = "basicId", value = "水生物放流信息id")
    private String basicId;

    @ApiModelProperty(name = "belongYear", value = "所属年度")

    private String belongYear;
    @ApiModelProperty(name = "releaseSite", value = "流放地点")
    private String releaseSite;

    @ApiModelProperty(name = "longitude", value = "经度")
    private String longitude;

    @ApiModelProperty(name = "latitude", value = "纬度")
    private String latitude;

    @JSONField(format = "yyyy-MM-dd")
    @ApiModelProperty(name = "releaseTime", value = "流放时间")
    private Date releaseTime;

    @ApiModelProperty(name = "firstEvaluateIndicatorList", value = "评价指标综合")
    private List<FirstEvaluateIndicatorVo> firstEvaluateIndicatorList;

}
