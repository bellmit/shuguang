package com.sofn.fdpi.vo;

import com.sofn.common.model.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description:
 * @Auther: Bay
 * @Date: 2020/1/7 10:56
 */
@Data
@ApiModel("栏目Vo")

public class EssaycolumnVo extends BaseVo<EssaycolumnVo> {
    /**
     * 栏目名称
     */
    @ApiModelProperty(value = "栏目名称")
    private String columnName;

    /**
     * 栏目ID
     */
    @ApiModelProperty(value = "栏目ID")
    private String id;

    /**
     * 状态
     */
    @ApiModelProperty(value = "是否公开")
    private String status;
 /*   @ApiModelProperty(value = "删除标识")
    private String delFlag;*/
}
