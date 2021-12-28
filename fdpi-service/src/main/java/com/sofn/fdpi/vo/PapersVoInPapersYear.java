package com.sofn.fdpi.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("证书年审新增/修改/详情中相关证件信息")
public class PapersVoInPapersYear implements Serializable {
    @ApiModelProperty("证书ID")
    private String papersId;
    @ApiModelProperty("证书类型")
    private String papersType;
    @ApiModelProperty("证书类型名称")
    private String papersTypeName;
    //证书编号
    @ApiModelProperty("证书编号")
    private String papersNumber;
    @ApiModelProperty("企业名称")
    private String compName;
    @ApiModelProperty("物种学名,以顿号相隔")
    private String speciesNames;
    @ApiModelProperty("有效期至")
    private String dataClos;
}
