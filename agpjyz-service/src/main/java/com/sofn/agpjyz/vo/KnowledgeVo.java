package com.sofn.agpjyz.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-03-09 9:55
 */
@Data
@ApiModel(value = "知识库表单对象")
public class KnowledgeVo {

    @ApiModelProperty(value = "主键id,新增不用传")
    private String  id;
    @ApiModelProperty(value = "知识库名称")
    private String  knowledge;
    @ApiModelProperty(value = "文档类型ID 文档类型 1:知识常识2.工作手册3.样本集素材采集方法，4相关项目的政策、法规")
    private String  documentId;
    @ApiModelProperty(value = "文档类型VALUE（无用字段）")
    private String  documentValue;
    @ApiModelProperty(value = "文件id")
    private String  fileId;
    @ApiModelProperty(value = "文件name")
    private String  fileName;
}
