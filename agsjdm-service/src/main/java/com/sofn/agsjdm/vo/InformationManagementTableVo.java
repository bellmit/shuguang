package com.sofn.agsjdm.vo;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Description
 * @Author wg
 * @Date 2021/7/14 15:46
 **/
@Data
@ApiModel("列表vo")
public class InformationManagementTableVo {
    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "湿地区名称")
    private String wetAreasName;
    @ApiModelProperty(value = "湿地区编码")
    private String wetAreasCode;
    @ApiModelProperty(value = "所属二级流域")
    private String theSecondaryBasin;
    @ApiModelProperty(value = "河流级别")
    private String theRiverLevel;
    @ApiModelProperty("调查时间")
    @JSONField(format = "yyyy-MM-dd")
    private Date createTime;
    @ApiModelProperty("调查人")
    private String operator;
}
