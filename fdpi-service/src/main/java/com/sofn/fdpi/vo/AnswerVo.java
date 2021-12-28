package com.sofn.fdpi.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-03-06 10:48
 */
@Data
@ApiModel(value = "解答对象")
public class AnswerVo {
    @ApiModelProperty(value = "问题id，不能为空")
    private  String id;
    /**
     * 解答
     */
    @Size(max = 400,message = "解答内容不能超过400")
    @ApiModelProperty(value = "解答内容，不能为空")
    private String answer;

    /**
     * 解答人
     */
    @Size(max = 15,message = "解答人长度不能超过15")
    @ApiModelProperty(value = "解答人，不能为空")
    private String anPerson;

    /**
     * 解答时间
     */
    @ApiModelProperty(value = "解答时间，不能为空")
    private Date anDate;

    /**
     * 解答附件
     */
    @ApiModelProperty(value = "解答附件文件id，可以为空")
    private String fileId;
    @ApiModelProperty(value = "问题附件文件名")
    private String anFileName;
}
