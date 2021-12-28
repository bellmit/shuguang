package com.sofn.agpjyz.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 基础设施维护情况备案登记
 *
 * @Author yumao
 * @Date 2020/3/12 9:12
 **/
@Data
@TableName("FACILITY_MAINTENANCE")
public class FacilityMaintenance {

    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "保护点ID")
    private String protectId;
    @ApiModelProperty(value = "保护点名称")
    private String protectValue;
    @TableField(exist = false)
    private String facilities;
    @ApiModelProperty(value = "基础设施名称ID,来自本系统(基础设施备案登记接口-下拉接口)")
    private String facilitiesId;
    @ApiModelProperty(value = "使用日期")
    private Date useDate;
    @ApiModelProperty(value = "故障原因")
    private String fault;
    @ApiModelProperty(value = "解决方案")
    private String solve;
    @ApiModelProperty(value = "维修人")
    private String repairMan;
    @ApiModelProperty(value = "维修时间")
    private Date repairTime;
}
