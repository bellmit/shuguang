package com.sofn.fdpi.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-03-06 9:22
 */
@ApiModel(value = "文章列表对象")
@Data
public class TbArticlesListVo {

    @ApiModelProperty(value = "文章ID")
    private String id;

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
    @ApiModelProperty(value = "状态")
    private String essaystatus;
    @ApiModelProperty(value = "栏目名")
    private String cName;
}
