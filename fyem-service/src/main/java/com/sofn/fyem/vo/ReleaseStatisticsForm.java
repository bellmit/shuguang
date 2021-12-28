package com.sofn.fyem.vo;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @Description: 增值放流-表单展示
 * @Author: mcc
 */
@ApiModel
@Data
@EqualsAndHashCode(callSuper = false)
public class ReleaseStatisticsForm {

    @ApiModelProperty(name = "belongYear", value = "上报年度")
    private String belongYear;

    @JSONField(format = "yyyy-MM-dd")
    @ApiModelProperty(name = "createTime", value = "填报时间")
    private Date createTime;
}
