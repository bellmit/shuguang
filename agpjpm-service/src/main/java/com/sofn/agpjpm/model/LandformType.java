package com.sofn.agpjpm.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-06-10 9:10
 */
@TableName("landform_type")
@Data
@ApiModel(value = "地形对象")
public class LandformType {
    @ApiModelProperty(value = "主键，前端不传")
    private String id;
    // 植物调查id
    @ApiModelProperty(value = "调查id，前端不传")
    private String surveyId;
    @ApiModelProperty(value = "地形id")
    private String  landformId;
}
