package com.sofn.agpjpm.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-06-10 9:09
 */
@TableName("habitat_type")
@Data
@ApiModel(value = "生境对象")
public class HabitatType {
    @ApiModelProperty(value = "主键，前端不传")
    private String id;
    // 植物调查id
    @ApiModelProperty(value = "调查id，前端不传")
    private String surveyId;
    @ApiModelProperty(value = "生境id")
    private String habitatId;
}
