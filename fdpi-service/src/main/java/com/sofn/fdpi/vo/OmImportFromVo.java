package com.sofn.fdpi.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.annotation.ExcelSheetInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description 进口比例折算列表对象
 * @Author wg
 * @Date 2021/5/14 11:08
 **/
@Data
@ApiModel("进口企业比例折算列表对象")
public class OmImportFromVo implements Serializable {
    //主键
    @ApiModelProperty("主键")
    private String id;

    //进口企业
    @ApiModelProperty("进口企业")
    private String importMan;

    //《允许进出口证明书》号
    @ApiModelProperty("允许进出口证明书")
    private String credential;

    //海关报送单号
    @ApiModelProperty("海关报送单号")
    private String customsList;

    //进口日期
    @ApiModelProperty("进口日期")
    private Date importDate;

    //规格
    @ApiModelProperty("规格")
    private Integer size;

    //进口国
    @ApiModelProperty("进口国")
    private String importCountry;

    //进口数量(吨)
    @ApiModelProperty("进口数量")
    private Double quantity;

    //折算比例(黑*600，白*900)
    @ApiModelProperty("折算比例")
    private Double obversion;

    //剩余数量
    @ApiModelProperty("剩余数量")
    private Double remainingQty;

    //剩余数量的折算比例
    @ApiModelProperty("剩余数量的折算比例")
    private Double remainingQtyConvert;

    //操作人（企业名称）
    @ApiModelProperty("操作人")
    private String operator;

    //创建时间
    @ApiModelProperty("创建时间")
    private Date createTime;

}
