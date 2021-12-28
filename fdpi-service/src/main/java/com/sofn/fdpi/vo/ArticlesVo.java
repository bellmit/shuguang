package com.sofn.fdpi.vo;

import com.sofn.common.model.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Auther: Bay
 * @Date: 2020/1/8 13:45
 */
@Data
@ApiModel(value = "文章查询对象Vo")
public class ArticlesVo extends BaseVo<ArticlesVo> {
    @ApiModelProperty(name = "pageNo", value = "索引")
    private Integer pageNo;
    @ApiModelProperty(name = "pageSize", value = "每页条数")
    private Integer pageSize;
    /**
     * 栏目
     */
    @ApiModelProperty(value = "栏目")
    private String cName;
    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    private String essaytitle;
    /**
     * 发布时间
     */
    @ApiModelProperty(value = "开始时间")
    private Date startTime;
    @ApiModelProperty(value = "结束时间")
    private Date endTime;
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private String essaystatus;


}
