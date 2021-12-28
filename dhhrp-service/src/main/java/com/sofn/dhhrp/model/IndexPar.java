package com.sofn.dhhrp.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description: 指标参数
 * @Auther: xiaobo
 * @Date: 2020-04-28 14:04
 */
@ApiModel(value = "指标参数")
@Data
@TableName("index_par")
public class IndexPar {
    @ApiModelProperty(value = "主键")
    private  String id;
    @ApiModelProperty(value = "类型")
    private String value;
}
