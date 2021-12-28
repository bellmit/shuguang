package com.sofn.fdpi.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.shiro.spring.aop.SpringAnnotationResolver;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-07-17 11:30
 */
@Data
@ApiModel(value = "反馈意见对象")
public class BackAdvice {
    @ApiModelProperty(value = "反馈id")
    private  String id;
    @ApiModelProperty(value = "反馈意见")
    private  String advice;
    @ApiModelProperty("备案：0不备案，1备案上报")
    private String record;
}
