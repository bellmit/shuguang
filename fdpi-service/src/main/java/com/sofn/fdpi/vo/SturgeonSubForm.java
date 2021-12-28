package com.sofn.fdpi.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@ApiModel(value = "鲟鱼子酱子表表单对象")
public class SturgeonSubForm {

    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "鲟鱼子酱ID")
    private String sturgeonId;
    @ApiModelProperty(value = "客户", example = "美国Awers")
    private String customer;
    @ApiModelProperty(value = "品种", example = "SCH*DAU")
    private String variety;
    @ApiModelProperty(value = "标签纸规格", example = "A")
    private String label;
    @ApiModelProperty(value = "箱号", example = "1")
    private Integer caseNum;
    @ApiModelProperty(value = "起始号", example = "00315")
    private String startNum;
    @ApiModelProperty(value = "终止号", example = "00320")
    private String endNum;
    @ApiModelProperty(value = "规格(/克)", example = "1200")
    private Integer specs;
    @ApiModelProperty(value = "听数", example = "100")
    private Integer sum;


}
