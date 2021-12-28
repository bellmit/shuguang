package com.sofn.agpjpm.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-06-10 9:11
 */
@TableName("soil_type")
@Data
@ApiModel(value = "土壤对象")
public class SoilType {
    @ApiModelProperty(value = "主键，前端不传")
    private String id;
    // 植物调查id
    @ApiModelProperty(value = "调查id，前端不传")
    private String surveyId;
    @ApiModelProperty(value = "土壤id")
    private String soilId;
}
