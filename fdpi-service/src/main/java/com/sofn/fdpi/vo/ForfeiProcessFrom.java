package com.sofn.fdpi.vo;

import com.sofn.common.model.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description:
 * @Auther: Bay
 * @Date: 2020/1/3 11:04
 */
@Data
@ApiModel("罚没流程vo对象")
public class ForfeiProcessFrom extends BaseVo<ForfeiProcessFrom> {

    @ApiModelProperty(value = "罚没ID")
    private String ffId;
    /**
     * 审核意见
     */
    @ApiModelProperty(value = "审核意见")
    private String advice;

    /**
     * 审核状态
     */

    @ApiModelProperty(value = "状态 1未上报6上报")
    private int status;
}
