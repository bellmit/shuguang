package com.sofn.fdpi.vo;

import com.sofn.common.model.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Auther: Bay
 * @Date: 2020/1/9 11:54
 */
@ApiModel("问题Vo对象")
@Data
public class QuestionFrom extends BaseVo<QuestionFrom> {

    /**
     * 解答
     */
    @ApiModelProperty(value = "解答")
    private String answer;

    /**
     * 解答人
     */
    @ApiModelProperty(value = "解答人")
    private String anPerson;

    /**
     * 解答时间
     */
    @ApiModelProperty(value = "解答时间")
    private Date anDate;

    /**
     * 解答附件
     */
    @ApiModelProperty(value = "解答附件")
    private String anAdjunct;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private int queStatus;



}
