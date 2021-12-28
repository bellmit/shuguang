package com.sofn.fdpi.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author yumao
 * @Date 2019/12/27 9:26
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "前端下拉选择器VO类")
public class SelectVo implements Serializable {

    @ApiModelProperty(name = "key", value = "键")
    private String key;
    @ApiModelProperty(name = "val", value = "值")
    private String val;

}
