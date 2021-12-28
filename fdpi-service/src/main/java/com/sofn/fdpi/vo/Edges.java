package com.sofn.fdpi.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Description 欧鳗溯源组织关系描述
 * @Author wg
 * @Date 2021/6/4 10:36
 **/
@Data
@ApiModel("欧鳗溯源组织关系描述")
public class Edges {
    //来源企业id
    @ApiModelProperty("来源企业id")
    private String source;
    //目标企业id
    @ApiModelProperty("目标企业id")
    private String target;
    //交易数量
    @ApiModelProperty("交易数量")
    private String label;
    //交易数量
    @ApiModelProperty("数据类型")
    private String dataType;
    //交易数量
    @ApiModelProperty("交易日期")
    private String subLabel;
}
