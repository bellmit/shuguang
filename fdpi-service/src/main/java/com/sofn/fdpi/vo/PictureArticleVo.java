package com.sofn.fdpi.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-03-02 9:34
 */
@ApiModel("文章图片Vo")
@Data
public class PictureArticleVo {


    /**
     * 栏目
     */
    @ApiModelProperty(value = "栏目")
    private String cName;

    /**
     * 标题
     */
    @Size(max = 50,message = "标题不能超过50")
    @ApiModelProperty(value = "标题")
    private String essaytitle;

    /**
     * 作者
     */
    @Size(max = 10,message = "作者长度不能超过10")
    @ApiModelProperty(value = "作者")
    private String essayauthor;

    /**
     * 内容
     */
    @Size(max = 10000,message = "文章内容不能超过10000")
    @ApiModelProperty(value = "内容")
    private String essaycontent;

    /**
     * 发布时间
     */
    @ApiModelProperty(value = "发布时间")
    private Date essaydatetime;

    /**
     * 浏览次数
     */

    @ApiModelProperty(value = "浏览次数")
    private int essaycount;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态 status：1 公布 2：隐藏  3.未公布")
    private String essaystatus;

    @ApiModelProperty(value = "文件id")
    private String fileId;
    @ApiModelProperty(value = "文件路径")
    private String filePath;
    @ApiModelProperty(value = "文件名称")
    private String fileName;
}
