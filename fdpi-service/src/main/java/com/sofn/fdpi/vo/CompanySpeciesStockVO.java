/**
 * @Author 文俊云
 * @Date 2020/1/2 14:41
 * @Version 1.0
 */
package com.sofn.fdpi.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author 文俊云
 * @Date 2020/1/2 14:41
 * @Version 1.0
 */

@Data
public class CompanySpeciesStockVO {
    @ApiModelProperty("ID")
    private String id;

    @ApiModelProperty("变更日期")
    private Date changeTime;

    @ApiModelProperty("公司ID")
    private String companyId;

    @ApiModelProperty("公司名称")
    private String companyName;

    @ApiModelProperty("物种ID")
    private String speciesId;

    @ApiModelProperty("物种名称")
    private String speciesName;

    @ApiModelProperty("变更类型")
    private String billType;

    @ApiModelProperty("变更原因（变更操作）")
    private String changeReason;

    @ApiModelProperty("出入库标识")
    private String importMark;

    @ApiModelProperty("变更前数目")
    private String beforeNum;

    @ApiModelProperty("本次变更数目")
    private String changeNum;

    @ApiModelProperty("本次变更后数目")
    private String afterNum;

    @ApiModelProperty("变更人Id")
    private String lastChangeUserId;

    @ApiModelProperty("对方单位")
    private String otherComName;

}
