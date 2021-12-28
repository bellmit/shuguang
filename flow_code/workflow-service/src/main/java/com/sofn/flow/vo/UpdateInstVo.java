package com.sofn.flow.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Map;

/**
 * 修改流程数据
 * @author quzhijie
 * @date 2020-12-14
 */
@Data
@ApiModel(value="修改流程实体")
public class UpdateInstVo {

    @NotBlank(message = "流程定义ID不能为空")
    @ApiModelProperty(value = "流程定义ID", required = true)
    private String defId;

    @NotBlank(message = "业务主键属性名称不能为空")
    @ApiModelProperty(value = "业务主键属性名称", required = true)
    private String idAttrName;

    @NotBlank(message = "业务主键属性值不能为空")
    @ApiModelProperty(value = "业务主键属性值", required = true)
    private String idAttrValue;

}
