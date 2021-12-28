package com.sofn.sys.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "GisMap查询返回对象")
public class SysGisMapVo {
  /**
   * 经度
   */
  @ApiModelProperty("经度")
  private String longitude;
  /**
   * 纬度
   */
  @ApiModelProperty("纬度")
  private String latitude;


  /**
   * 行政区域名称
   */
  @ApiModelProperty("行政区域名称")
  private String regionName;

  /**
   * 行政区域代码
   */
  @ApiModelProperty("行政区域代码")
  private String regionCode;

  /**
   * 备注
   */
  @ApiModelProperty("备注")
  private String remark;
}
