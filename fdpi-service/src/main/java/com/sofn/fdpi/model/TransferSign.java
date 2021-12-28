/**
 * @Author 文俊云
 * @Date 2020/8/27 10:26
 * @Version 1.0
 */
package com.sofn.fdpi.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author 文俊云
 * @Date 2020/8/27 10:26
 * @Version 1.0
 */

@Data
public class TransferSign {
    @ApiModelProperty("物种转移ID")
    private String transferId;
    @ApiModelProperty("标识编码")
    private String signCode;
}
