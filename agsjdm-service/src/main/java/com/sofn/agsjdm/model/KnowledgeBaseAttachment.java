package com.sofn.agsjdm.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-04-16 11:16
 */
@ApiModel(value = "知识库附件对象")
@TableName("KNOWLEDGE_BASE_ATTACHMENT")
@Data
public class KnowledgeBaseAttachment {
    @ApiModelProperty(value = "主键id 新增不用传")
    private String  id;
    @ApiModelProperty(value = "文件id")
    private String  fileId;
    @ApiModelProperty(value = "知识库id")
    private String  knowledgeId;
    @ApiModelProperty(value = "文件名")
    private String fileName;
    @TableField(exist=false)
    @ApiModelProperty(value = "下载次数")
    private Integer     downloads;
}
