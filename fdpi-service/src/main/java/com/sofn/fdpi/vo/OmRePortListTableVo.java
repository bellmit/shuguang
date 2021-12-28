package com.sofn.fdpi.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description 欧鳗再出口table对象
 * @Author wg
 * @Date 2021/5/27 16:27
 **/
@Data
@ApiModel("欧鳗再出口table对象")
public class OmRePortListTableVo implements Serializable {
    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "来源加工厂")
    private String sourceProc;
    @ApiModelProperty(value = "规格")
    private Integer importSize;
    @ApiModelProperty(value = "允许进出口说明书")
    private String credential;
    @ApiModelProperty(value = "发货口岸")
    private String portOfDispatch;
    @ApiModelProperty(value = "到达国家")
    private String reachCountry;
    @ApiModelProperty(value = "货物类型")
    private String goodsType;
    @ApiModelProperty(value = "交易数量")
    private Double exportVolume;
    @ApiModelProperty(value = "比例折算")
    private Double obversion;
    @ApiModelProperty(value = "操作人")
    private String operator;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "出口时间")
    private Date outOfDate;
}
