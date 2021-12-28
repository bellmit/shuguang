package com.sofn.agsjdm.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-04-16 17:31
 */
@Data
@ApiModel("分布地区")
public class AddrVo {
    @ApiModelProperty(value = "分布-省",example = "440000")
    private String provinceCode;
    //分布-市
    @ApiModelProperty(value = "分布-市",example = "440300")
    private String cityCode;
    //分布-区
    @ApiModelProperty(value = "分布-区",example = "440303")
    private String areaCode;
    @ApiModelProperty(value="分布-区域中文",example = "广东省-深圳市-罗湖区")
    private String regionInCh;
}
