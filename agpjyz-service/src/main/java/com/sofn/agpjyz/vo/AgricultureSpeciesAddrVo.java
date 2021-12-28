package com.sofn.agpjyz.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 农业植物物种分布地址
 * @Author: WXY
 * @Date: 2020-2-28 13:55:00
 */
@Data
@ApiModel("农业植物物种分布地址")
public class AgricultureSpeciesAddrVo implements Serializable {
    @ApiModelProperty(value="主键")
    private String id;
    //省CODE
    @ApiModelProperty(value="省CODE")
    private String provinceCode;
    //市CODE
    @ApiModelProperty(value="市CODE")
    private String cityCode;
    //区CODE
    @ApiModelProperty(value="区CODE")
    private String areaCode;
    //省-市-区中文
    @ApiModelProperty(value="省-市-区中文")
    private String regionInCh;
}
