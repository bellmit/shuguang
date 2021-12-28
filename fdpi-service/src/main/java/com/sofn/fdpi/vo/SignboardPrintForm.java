package com.sofn.fdpi.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;


@Data
@ApiModel(value = "标识打印表单对象")
public class SignboardPrintForm {

    private String id;

    @ApiModelProperty(value = "物种类型")
    private String type;

    @ApiModelProperty(value = "物种编码")
    private String speCode;

    @ApiModelProperty(value = "省代码")
    private String provinceCode;

    @ApiModelProperty(value = "企业代码")
    private String compCode;

    @ApiModelProperty(value = "开始代码")
    private String codeStart;

    @ApiModelProperty(value = "委托数量")
    private Integer num;


    @NotBlank(message = "打印企业ID不能为空")
    @ApiModelProperty(value = "打印企业ID")
    private String printCompId;

    @NotBlank(message = "企业ID不能为空")
    @ApiModelProperty(value = "企业ID")
    private String compId;

    @ApiModelProperty(value = "标识申请ID")
    private String applyId;

    @ApiModelProperty(value = "申请类型1标识申请2国内鱼子酱申请")
    private String applyType;

    @ApiModelProperty(value = "标识打印列表表单对象")
    private List<SignboardPrintListForm> printList;
}
