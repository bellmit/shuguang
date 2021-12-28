/**
 * @Author 文俊云
 * @Date 2019/12/27 10:19
 * @Version 1.0
 */
package com.sofn.fdpi.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author 文俊云
 * @Date 2019/12/27 10:19
 * @Version 1.0
 */

@Data
public class SpeciesSelect {

    @ApiModelProperty("物种Id")
    private String speciesId;
    @ApiModelProperty("物种名称")
    private String speciesName;
    @ApiModelProperty("物种数目")
    private String speciesNum;
    @ApiModelProperty("是否使用标识写死，0:不使用,1 :全程使用,2:销售使用")
    private String speciesSign;

    @ApiModelProperty("可操作的物种数目")
    private String operableNum;
}
