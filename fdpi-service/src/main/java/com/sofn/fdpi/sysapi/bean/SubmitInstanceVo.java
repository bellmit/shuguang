package com.sofn.fdpi.sysapi.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Map;

/**
 * 提交流程数据
 *
 * @author heyongjie
 * @date 2020/5/11 15:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "提交流程实体")
public class SubmitInstanceVo {

    @NotBlank(message = "流程定义ID不能为空")
    @ApiModelProperty(value = "流程定义ID", required = true)
    private String defId;

    @NotBlank(message = "业务主键属性名称不能为空")
    @ApiModelProperty(value = "业务主键属性名称", required = true)
    private String idAttrName;

    @NotBlank(message = "业务主键属性值不能为空")
    @ApiModelProperty(value = "业务主键属性值", required = true)
    private String idAttrValue;

    @ApiModelProperty(value = "流程参数")
    private Map<String, Object> params;

}
