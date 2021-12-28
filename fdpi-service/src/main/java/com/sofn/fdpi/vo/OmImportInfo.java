package com.sofn.fdpi.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Description
 * @Author wg
 * @Date 2021/5/29 19:00
 **/
@Data
@ApiModel("通过出让企业和允许进出口说明书号得到的进口信息")
public class OmImportInfo {
    @ApiModelProperty(value = "规格,目前0是白仔鳗，1是黑仔鳗")
    private Integer size;
    @ApiModelProperty(value = "剩余重量(吨)养殖企业调这个接口那么这个值就是进口企业的，加工来调这个值是养殖企业的的剩余吨数")
    private Double remainingQty;
    @ApiModelProperty(value = "来源国")
    private String importCountry;
    @ApiModelProperty(value = "进口日期")
    private Date importDate;
    @ApiModelProperty(value = "出口国CITES证书号")
    private String citesId;
    @ApiModelProperty(value = "海关报送单号")
    private String customsList;
    @ApiModelProperty(value = "农业部批准文件号")
    private String miniRatifyFileId;
}
