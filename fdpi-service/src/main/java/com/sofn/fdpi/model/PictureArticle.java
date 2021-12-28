package com.sofn.fdpi.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.common.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-02-28 11:26
 */
@TableName("PICTURE_ARTICLE")
@ApiModel(value = "图片文章model")
@Data
public class PictureArticle extends BaseModel<PictureArticle> {
    /**
     * 文章ID
     */
    @ApiModelProperty(value = "文章ID")
    @TableId(value = "ID", type = IdType.UUID)
    private String id;

    /**
     * 栏目名
     */
    @ApiModelProperty(value = "栏目名")
    private String cName;

    /**
     * 标题
     */
    @Length(max = 100,message = "标题内容不能超过100")
    @ApiModelProperty(value = "标题")
    private String essaytitle;

    /**
     * 作者
     */
    @Length(max = 30,message = "作者长度不能超过30")
    @ApiModelProperty(value = "作者")
    private String essayauthor;

    /**
     * 内容
     */
    @Length(max = 8000,message = "文章内容不能超过8000")
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

    @ApiModelProperty(value = "文件id")
    private String fileId;
    @ApiModelProperty(value = "文件路径")
    private String filePath;
    @ApiModelProperty(value = "文件名称")
    private String fileName;
}
