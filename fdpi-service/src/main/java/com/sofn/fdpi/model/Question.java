package com.sofn.fdpi.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.common.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@TableName("QUESTION")
@ApiModel(value = "问题Model")
@Data
public class Question extends BaseModel<Question> {
    /**
     * 问题ID
     */
    @ApiModelProperty(value = "问题ID")
    private String id;

    /**
     * 提问人
     */
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
    @JSONField(format = "yyyy-MM-dd hh:mm:ss")
    @ApiModelProperty(value = "提问时间")
    private Date queDate;

    /**
     * 问题描述
     */
    @ApiModelProperty(value = "问题描述")
    private String queDesc;

    /**
     * 附件
     */
    @ApiModelProperty(value = "问题附件id")
    private String queAdjunct;
    @ApiModelProperty(value = "问题附件文件名")
    private String queFileName;

    /**
     * 解答
     */
    @ApiModelProperty(value = "解答")
    private String answer;

    /**
     * 解答人
     */
    @ApiModelProperty(value = "解答人")
    private String anPerson;

    /**
     * 解答时间
     */
    @JSONField(format = "yyyy-MM-dd hh:mm:ss")
    @ApiModelProperty(value = "解答时间")
    private Date anDate;

    /**
     * 解答附件
     */
    @ApiModelProperty(value = "解答附件id")
    private String anAdjunct;
    @ApiModelProperty(value = "问题附件文件名")
    private String anFileName;


    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private int queStatus;


    /**
     * 来源
     */
    @ApiModelProperty(value = "来源")
    //0  公众  1   企业
    private String qFrom;
    // 直属部门
    private String direclyId;
    // 省级部门
    private String provinceId;
    //  公司id
    private String compId;
}

