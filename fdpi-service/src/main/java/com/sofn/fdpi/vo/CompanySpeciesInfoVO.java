/**
 * @Author 文俊云
 * @Date 2020/1/2 14:41
 * @Version 1.0
 */
package com.sofn.fdpi.vo;

import com.sofn.common.utils.PageUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author 文俊云
 * @Date 2020/1/2 14:41
 * @Version 1.0
 */

@Data
public class CompanySpeciesInfoVO {
    @ApiModelProperty("ID")
    private String id;
    @ApiModelProperty("物种名称")
    private String speName;
    @ApiModelProperty("拉丁文名称")
    private String latinName;
    @ApiModelProperty("商品名称")
    private String tradeName;
    @ApiModelProperty("俗名")
    private String localName;
    @ApiModelProperty("保护级别")
    private String proLevel;
    @ApiModelProperty("cites级别")
    private String cites;
    @ApiModelProperty("是否标识")
    private String identify;
    @ApiModelProperty("是否谱系")
    private String pedigree;
    @ApiModelProperty("物种简介")
    private String intro;
    @ApiModelProperty("生境及习性")
    private String habit;
    @ApiModelProperty("地理分布")
    private String distribution;
    @ApiModelProperty("保护现状")
    private String conStatus;
    @ApiModelProperty("驯养类型")
    private String tamType;
    @ApiModelProperty("物种状态")
    private String speStatus;
    @ApiModelProperty("物种来源")
    private String speOrigin;
    @ApiModelProperty("照片ID")
    private String photo;
    @ApiModelProperty("该企业现存数目")
    private String num;
    @ApiModelProperty("该企业该物种变更流水记录分页")
    private PageUtils<CompanySpeciesStockVO> pageUtils;
}
