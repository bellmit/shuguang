package com.sofn.fyem.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description:
 * @Author: DJH
 * @Date: 2020/8/3 15:12
 */
@ApiModel(value = "ProliferationReleaseDistributionPointVo", description = "放流点位分布Vo")
@Data
@EqualsAndHashCode(callSuper = false)
public class ProliferationReleaseDistributionPointVo {

    @ApiModelProperty(value = "区域")
    private String region;

    @ApiModelProperty(value = "区域名")
    private String regionName;

    @ApiModelProperty(name = "longitude", value = "经度")
    private String longitude;

    @ApiModelProperty(name = "latitude", value = "纬度")
    private String latitude;

    @ApiModelProperty(name = "releaseSite", value = "流放地点")
    private String releaseSite;

    @ApiModelProperty(name = "releaseInfo", value = "放流情况")
    private String releaseInfo;
}
