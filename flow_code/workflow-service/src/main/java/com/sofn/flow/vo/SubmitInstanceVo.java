package com.sofn.flow.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Map;

/**
 * 提交流程数据
 * @author heyongjie
 * @date 2020/5/11 15:25
 */
@Data
@ApiModel(value="提交流程实体")
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

    @ApiModelProperty(value = "流程实例参数")
    private Map<String, Object> instParams;

    @ApiModelProperty(value = "流程活动参数")
    private Map<String, Object> params;

    @ApiModelProperty(value = "流程下一活动参数")
    private Map<String, Object> nextParams;

}
