package com.sofn.agpjyz.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 基础设施备案登记
 * @Author yumao
 * @Date 2020/3/10 8:55
 **/
@Data
@TableName("FACILITIES_FILING")
public class FacilitiesFiling {

    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "保护点ID")
    private String protectId;
    @ApiModelProperty(value = "保护点名称")
    private String protectValue;
    @ApiModelProperty(value = "基础设施名称")
    private String facilities;
    @ApiModelProperty(value = "使用日期")
    private Date useDate;
    @ApiModelProperty(value = "操作人")
    private String inputer;
    @ApiModelProperty(value = "操作时间")
    private Date inputerTime;

}
