package com.sofn.fdpi.vo;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
/**
 * @Description:  年审记录对象
 * @Author: wuXy
 * @Date: 2020-1-3 16:41:37
 */
@Data
@ApiModel("年审记录对象")
public class PapersYearInspectProcessVo implements Serializable {
    @ApiModelProperty("证书编号")
    private String papersNumber;
    @ApiModelProperty("申请日期")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date applyTime;
    @ApiModelProperty("批准日期")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date approveTime;
    @ApiModelProperty("核定物种")
    private String speciesName;
    @ApiModelProperty("核定数量")
    private Integer speciesNumber;
    @ApiModelProperty("核发单位")
    private String approveOrg;
}
