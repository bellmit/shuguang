package com.sofn.fdpi.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.common.model.BaseModel;
import com.sofn.fdpi.vo.FileManageForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@TableName("TB_ARTICLES")
@ApiModel(value = "文章model")
@Data
public class TbArticles extends BaseModel<TbArticles> {
    /**
     * 文章ID
     */
    @ApiModelProperty(value = "文章ID")
    private String id;

    /**
     * 栏目ID
     */
    @ApiModelProperty(value = "栏目名")
    private String cName;

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    private String essaytitle;

    /**
     * 作者
     */
    @ApiModelProperty(value = "作者")
    private String essayauthor;

    /**
     * 内容
     */

    @ApiModelProperty(value = "内容")
    private String essaycontent;

    /**
     * 发布时间
     */
    @ApiModelProperty(value = "发布时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date essaydatetime;

    /**
     * 浏览次数
     */
    @ApiModelProperty(value = "浏览次数")
    private int essaycount;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private String essaystatus;
    @TableField(exist = false)
    @ApiModelProperty(value = "文件表单列表")
    private List<FileManage> files;
}

