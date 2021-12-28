/**
 * @Author 文俊云
 * @Date 2020/7/30 17:04
 * @Version 1.0
 */
package com.sofn.fdpi.vo;

import com.sofn.common.excel.annotation.ExcelField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author 文俊云
 * @Date 2020/7/30 17:04
 * @Version 1.0
 */

@Data
public class CompCountVO {
    @ApiModelProperty("区域名称")
    @ExcelField(title = "区域名称")
    private String regionName;
    @ApiModelProperty("企业ID")
    @ExcelField(title = "企业ID",isShow = false)
    private String compId;
    @ApiModelProperty("企业名称")
    @ExcelField(title = "企业名称")
    private String compName;
    @ApiModelProperty("物种名称")
    @ExcelField(title = "物种名称")
    private String speName;
    @ApiModelProperty("物种数量")
    @ExcelField(title = "物种数量")
    private double speNumber;
    @ApiModelProperty("标识总数")
    @ExcelField(title = "标识总数")
    private double allsigncount;
    @ApiModelProperty("激活标识总数")
    @ExcelField(title = "激活标识总数")
    private double linesigncount;
}
