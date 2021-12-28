package com.sofn.agpjyz.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-03-09 9:16
 */
@ApiModel(value = "知识库对象")
@TableName("KNOWLEDGE_BASE")
@Data
public class KnowledgeBase {
    @ApiModelProperty(value = "主键id ")
    private String  id;
    @ApiModelProperty(value = "知识库名称")
    private String  knowledge;
    @ApiModelProperty(value = "文档类型ID")
    private String  documentId;
    @ApiModelProperty(value = "文档类型VALUE")
    private String  documentValue;
    @TableField(exist = false)
    @ApiModelProperty(value = "知识库附件表单对象")
    private KnowledgeBaseAttachment k;

}
