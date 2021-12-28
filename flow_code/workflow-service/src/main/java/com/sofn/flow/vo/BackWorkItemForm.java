package com.sofn.flow.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Data
@ApiModel(value="回退参数")
public class BackWorkItemForm {

    @ApiModelProperty(value = "流程定义ID", required = true)
    private String defId;

    @ApiModelProperty(value = "业务ID属性名称", required = true)
    private String idAttrName;

    @ApiModelProperty(value = "业务ID属性值", required = true)
    private String idAttrValue;

    @ApiModelProperty(value = "回退目标活动标识[为空：返回上一节点，不为空：返回到指定节点]")
    private String targetActDefId;

    @ApiModelProperty(value = "是否回退给提交人[是:Y,否: N, 默认: N]")
    private String backToCommit = "N";

    @ApiModelProperty(value = "是否接收后回退[是:Y,否: N, 默认: N]")
    private String acceptBack = "N";

    @ApiModelProperty(value = "流程实例参数")
    private Map<String, Object> instParams;

    @ApiModelProperty(value = "流程活动参数")
    private Map<String, Object> params;

    @ApiModelProperty(value = "流程下一活动参数")
    private Map<String, Object> nextParams;
}
