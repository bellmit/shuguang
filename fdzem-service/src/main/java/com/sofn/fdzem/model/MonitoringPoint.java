package com.sofn.fdzem.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_monitoring_point")
@ApiModel("监测点实体类")
public class MonitoringPoint {

  @TableId(value = "id", type = IdType.INPUT)
  @ApiModelProperty("ID")
  private String id;
  @ApiModelProperty("监测点名称")
  private String name;
  @ApiModelProperty("所属批次id")
  private String batchManagementId;
  @ApiModelProperty("经度")
  private String longitude;
  @ApiModelProperty("纬度")
  private String latitude;
  @ApiModelProperty("数据类型:1-水质监测指标2-沉积物监测指标3-浮游生物监测指标4-生物残留监测指标")
  private String type;

  @ApiModelProperty("水质监测指标")
  @TableField(exist = false)
  private WaterQualityMonitoring waterQualityMonitoring;
  @ApiModelProperty("沉淀物监测指标")
  @TableField(exist = false)
  private SedimentMonitoring sedimentMonitoring;
  @ApiModelProperty("浮游生物监测指标")
  @TableField(exist = false)
  private PlanktonMonitoring planktonMonitoring;
  @ApiModelProperty("生物残留监测指标")
  @TableField(exist = false)
  private BiologicalResidueMonitoring biologicalResidueMonitoring;


}
