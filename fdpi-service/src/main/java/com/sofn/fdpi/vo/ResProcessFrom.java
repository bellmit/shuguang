package com.sofn.fdpi.vo;

import com.sofn.common.model.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description:
 * @Auther: Bay
 * @Date: 2020/1/3 16:33
 */
@Data
@ApiModel("收容流程vo对象，需要传入上报的收容id和上报的状态6")
public class ResProcessFrom extends BaseVo<ResProcessFrom> {

    /**
     * 收容ID
     */
    @ApiModelProperty(value = "收容ID")
    private String resId;





    /**
     * 审核状态
     */

    @ApiModelProperty(value = "状态 1未上报6上报")
    private int status;

    /**
     * 审核意见
     */
    @ApiModelProperty(value = "审核意见")
    private String advice;



}
