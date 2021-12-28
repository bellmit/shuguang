package com.sofn.fdpi.vo;

import com.sofn.common.model.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @Description:
 * @Auther: Bay
 * @Date: 2020/1/9 14:54
 */
@ApiModel("问题增加Vo对象")
@Data
public class QuestionVoOne extends BaseVo<QuestionVoOne> {

    /**
     * 问题ID
     */
    @ApiModelProperty(value = "问题ID 主键 新增不用传")
    private String id;

    /**
     * 提问人
     */
    @Size(max = 15,message = "提问人长度不能超过15")
    @ApiModelProperty(value = "提问人")
    private String quePerson;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String queEmail;

    /**
     * 提问时间
     */
    @ApiModelProperty(value = "提问时间")
    private Date queDate;

    /**
     * 问题描述
     */
    @Size(max = 400,message = "问题描述不能超过400")
    @ApiModelProperty(value = "问题描述")
    private String queDesc;

    /**
     * 附件
     */
    @ApiModelProperty(value = "附件id 传支撑平台返回的文件id")
    private String queAdjunct;
    @ApiModelProperty(value = "附件名称")
    private String queFileName;
}
