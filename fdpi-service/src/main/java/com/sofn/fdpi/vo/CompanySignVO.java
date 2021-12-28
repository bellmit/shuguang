/**
 * @Author 文俊云
 * @Date 2019/12/31 10:46
 * @Version 1.0
 */
package com.sofn.fdpi.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author 文俊云
 * @Date 2019/12/31 10:46
 * @Version 1.0
 */

@Data
public class CompanySignVO {
    @ApiModelProperty("标识ID")
    private String signBoardId;
    @ApiModelProperty("标识编码")
    private String code;
    @ApiModelProperty("物种ID")
    private String speciesId;
    @ApiModelProperty("物种名称")
    private String speciesName;
}
