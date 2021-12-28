package com.sofn.agsjdm.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-03-09 9:55
 */
@Data
@ApiModel(value = "知识库表单对象")
public class KnowledgeVo {

    @ApiModelProperty(value = "主键id,新增不用传")
    private String id;

    @NotBlank(message = "知识库名称不可为空")
    @Length(max = 64)
    @ApiModelProperty(value = "知识库名称")
    private String knowledge;

    @NotBlank(message = "文档类型不可为空")
    @ApiModelProperty(value = "文档类型ID 文档类型 1:知识常识2.工作手册3.样本集素材采集方法，4相关项目的政策、法规")
    private String documentId;
    @ApiModelProperty(value = "文档类型VALUE（无用字段）")
    private String documentValue;

    @NotBlank(message = "文件id不可为空")
    @ApiModelProperty(value = "文件id")
    private String fileId;

    @NotBlank(message = "文件名不可为空")
    @ApiModelProperty(value = "文件name")
    private String fileName;
}
