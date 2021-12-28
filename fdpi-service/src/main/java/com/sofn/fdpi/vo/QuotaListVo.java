package com.sofn.fdpi.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Description
 * @Author wg
 * @Date 2021/6/7 16:50
 **/
@Data
@ApiModel("欧鳗企业配额管理列表数据")
public class QuotaListVo {
    @ApiModelProperty("数据的主键")
    private String id;
    @ApiModelProperty("企业主键")
    private String compId;
    @ApiModelProperty("企业名称")
    private String compName;
    @ApiModelProperty("允许进出口说明书")
    private String credential;
    @ApiModelProperty("交易日期")
    private Date dealDate;
    @ApiModelProperty("欧鳗规格")
    private Integer importSize;
    @ApiModelProperty("进口数量")
    private Double importNum;
    @ApiModelProperty("折算比例")
    private Double obversion;
    @ApiModelProperty("剩余数量")
    private Double residueNum;
    @ApiModelProperty("剩余折算")
    private Double residueConvert;
    @ApiModelProperty("该企业的允许驯养繁殖情况(吨)")
    private Double tameAllowTon;
    @ApiModelProperty("数据所属的类型,1为进口企业，2为养殖企业，3为加工企业，查询详情的时候带上这个值")
    private String dateType;
}
