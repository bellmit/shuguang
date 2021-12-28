/**
 * @Author 文俊云
 * @Date 2020/7/30 9:39
 * @Version 1.0
 */
package com.sofn.fdpi.vo;

import com.sofn.common.excel.annotation.ExcelField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author 文俊云
 * @Date 2020/7/30 9:39
 * @Version 1.0
 */

@Data
public class SpeciesCountVO {

    @ExcelField(title = "区域编码",isShow = false)
    @ApiModelProperty("区域编码")
    private String id;
    @ExcelField(title = "区域名称")
    @ApiModelProperty("区域名称")
    private String regionName;
    @ExcelField(title = "物种名称")
    @ApiModelProperty("物种名称")
    private String speciesName;
    @ExcelField(title = "增加量")
    @ApiModelProperty("增加量")
    private double addNumber;
    @ExcelField(title = "减少量")
    @ApiModelProperty("减少量")
    private double reduceNumber;
    @ExcelField(title = "现有量")
    @ApiModelProperty("现有量")
    private double curNumber;

    @ApiModelProperty("存量")
    private double stock;
    @ApiModelProperty("变化量")
    private double change;
    @ApiModelProperty("申请数量")
    private double applyNum;
    @ApiModelProperty("使用数量")
    private double useNum;
    @ApiModelProperty("经营证数量")
    private double bizNum;
    @ApiModelProperty("繁育证数量")
    private double breedNum;
}
