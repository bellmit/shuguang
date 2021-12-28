package com.sofn.fdpi.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 证书物种信息
 * @Author: wuXy
 * @Date: 2020-6-23 14:54:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("证书物种信息")
public class PapersSpecVo {
    @ApiModelProperty(value = "证书id")
    private String papersId;
    @ApiModelProperty(value = "物种id")
    private String specId;
    @ApiModelProperty(value = "物种名称")
    private String specName;
    @ApiModelProperty(value = "拉丁名称")
    private String latinName;
    @ApiModelProperty(value = "来源")
    private String source;
    @ApiModelProperty(value = "数量")
    private Integer amount;
    @ApiModelProperty(name = "proLevel", value = "中国保护水平写死 一级:1 ，2级：2 ，特殊管理要求：3")
    private String proLevel;
    @ApiModelProperty(name = "cites", value = "CITES级别，1级：1,2级：2,3级：3")
    private String cites;
    @ApiModelProperty(value = "方式")
    private String mode;
    @ApiModelProperty(value = "物种类型")
    private String speType;
    @ApiModelProperty(value = "单位")
    private String unit;
    @ApiModelProperty(value = "单位")
    private String unitName;


    public PapersSpecVo(String papersId, String specId, String specName, String source,
                        Integer amount, String proLevel, String cites, String mode) {
        this.papersId = papersId;
        this.specId = specId;
        this.specName = specName;
        this.source = source;
        this.amount = amount;
        this.proLevel = proLevel;
        this.cites = cites;
        this.mode = mode;
    }

}
