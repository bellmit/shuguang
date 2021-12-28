package com.sofn.fdzem.model;

import com.baomidou.mybatisplus.annotation.IdType;
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
@TableName("tb_water_quality_monitoring")
@ApiModel("水质监测指标实体类")
public class WaterQualityMonitoring {

  @TableId(value = "id", type = IdType.INPUT)
  @ApiModelProperty("ID")
  private String id;
  @ApiModelProperty("PH")
  private String ph;
  @ApiModelProperty("水深/m")
  private String waterDepth;
  @ApiModelProperty("水温/℃")
  private String waterTemperature;
  @ApiModelProperty("透明度/cm")
  private String transparency;
  @ApiModelProperty("溶解氧/mg/L")
  private String dO;
  @ApiModelProperty("亚硝酸盐氮/mg/L")
  private String nitriteNitrogen;
  @ApiModelProperty("硝酸盐氮/mg/L")
  private String nitrateNitrogen;
  @ApiModelProperty("氨氮/mg/L")
  private String ammoniaNitrogen;
  @ApiModelProperty("无机氮/mg/L")
  private String mineralNitrogen;
  @ApiModelProperty("活性磷酸盐/mg/L")
  private String labilePhosphate;
  @ApiModelProperty("化学需氧量/mg/L")
  private String cOD;
  @ApiModelProperty("石油类/mg/L")
  private String petroleum;
  @ApiModelProperty("铜/ug/L")
  private String cu;
  @ApiModelProperty("锌/ug/L")
  private String zn;
  @ApiModelProperty("铅/ug/L")
  private String pb;
  @ApiModelProperty("镉/ug/L")
  private String cd;
  @ApiModelProperty("汞/ug/L")
  private String hg;
  @ApiModelProperty("砷/ug/L")
  private String asA;
  @ApiModelProperty("铬/ug/L")
  private String cr;
  @ApiModelProperty("总大肠菌群/个/L")
  private String coliforms;
  @ApiModelProperty("所属监测点id")
  private String monitoringPointId;



}
