package com.sofn.agpjyz.vo.ApiVO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MonitorStationApi {
    @ApiModelProperty("纬度")
    private String latitude;
    @ApiModelProperty("经度")
    private String longitude;
    @ApiModelProperty("区域代码")
    private String regionCode;
    @ApiModelProperty("区域名")
    private String regionName;
}
