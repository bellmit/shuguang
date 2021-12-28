package com.sofn.fdpi.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.common.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@TableName("ESSAYCOLUMN")
@ApiModel(value = "栏目model")
@Data
public class Essaycolumn extends BaseModel<Essaycolumn> {
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
    @ApiModelProperty(value = "是否公开状态")
    private String status;

}

