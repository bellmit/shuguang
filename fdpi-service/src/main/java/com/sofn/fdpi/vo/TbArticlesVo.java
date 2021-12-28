package com.sofn.fdpi.vo;

import com.sofn.common.model.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Auther: Bay
 * @Date: 2020/1/8 09:23
 */
@ApiModel("文章Vo")
@Data
public class TbArticlesVo extends BaseVo<TbArticlesVo> {
    /**
     * 文章ID
     */
    @ApiModelProperty(value = "文章ID")
    private String id;

    /**
     * 栏目
     */
    @ApiModelProperty(value = "栏目")
    private String cName;

    /**
     * 标题
     */
    @Size(max = 50,message = "标题内容不能超过100")
    @ApiModelProperty(value = "标题")
    private String essaytitle;

    /**
     * 作者
     */
    @Size(max = 10,message = "作者长度不能超过30")
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
    @ApiModelProperty(value = "状态")
    private String essaystatus;

    @ApiModelProperty(value = "文件表单列表")
    private List<FileManageForm> files;

}
