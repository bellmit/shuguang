package com.sofn.fdpi.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
/**
 * @Description:  审核/证书年审明细中物种对象
 * @Author: wuXy
 * @Date: 2020-1-3 16:41:37
 */
@Data
@ApiModel("证书年审明细中物种对象")
public class SpecieVoInPapersYear implements Serializable {
//    @ApiModelProperty("证书ID")
//    private String papersId;
//    @ApiModelProperty("证书类型")
//    private String papersType;
//    @ApiModelProperty("证书类型名称")
//    private String papersTypeName;
//    //证书编号
//    @ApiModelProperty("证书编号")
//    private String papersNumber;
//    @ApiModelProperty("有效期至")
//    private String dataClos;
//    @ApiModelProperty("企业名称")
//    private String compName;

    @ApiModelProperty("物种信息-证书年审明细表中主键")
    private String inspectRetailId;
    @ApiModelProperty("物种信息-物种ID")
    private String speciesId;
    @ApiModelProperty("物种名称")
    private String speciesName;
    @ApiModelProperty("物种信息-物种数量")
    private Integer speciesNumber;
    @ApiModelProperty("物种信息-标识数量")
    private Integer signboardNumber;
}
