package com.sofn.fdpi.vo;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Description:  年审记录对象
 * @Author: wuXy
 * @Date: 2020-1-3 16:41:37
 */
@Data
@ApiModel("年审历史对象")
public class PapersYearInspectHistoryVo {
    @ApiModelProperty("年度")
    private int year;
    @ApiModelProperty("企业名称")
    private String compName;
    @ApiModelProperty("申请日期")
    @JSONField(format = "yyyy-MM-dd")
    private Date createTime;
    @ApiModelProperty("批准日期")
    @JSONField(format = "yyyy-MM-dd")
    private Date approveTime;
}
