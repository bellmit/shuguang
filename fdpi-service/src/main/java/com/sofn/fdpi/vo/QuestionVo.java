package com.sofn.fdpi.vo;

import com.sofn.common.model.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description:
 * @Auther: Bay
 * @Date: 2020/1/9 11:54
 */
@ApiModel("问题Vo对象")
@Data
public class QuestionVo extends BaseVo<QuestionVo> {
    @ApiModelProperty(name = "pageNo", value = "索引")
    private Integer pageNo;

    @ApiModelProperty(name = "pageSize", value = "每页条数")
    private Integer pageSize;
    @ApiModelProperty(name = "startTime", value = "违法时间开始")
    private String startTime;
    @ApiModelProperty(name = "endTime", value = "违法时间end")
    private String endTime;
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private int queStatus;


}
