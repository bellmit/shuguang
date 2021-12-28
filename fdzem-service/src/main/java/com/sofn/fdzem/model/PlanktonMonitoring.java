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
@TableName("tb_plankton_monitoring")
@ApiModel("浮游生物监测指标实体类")
public class PlanktonMonitoring {

  @TableId(value = "id", type = IdType.INPUT)
  @ApiModelProperty("ID")
  private String id;
  @ApiModelProperty("所属监测点id")
  private String monitoringPointId;
  @ApiModelProperty("叶绿素 a/ug/L")
  private String chlorophyll;
  @ApiModelProperty("浮游植物密度/1000个/m3")
  private String phytoplanktonDensity;
  @ApiModelProperty("浮游植物种类数/个")
  private String phytoplanktonSpeciesNumber;
  @ApiModelProperty("浮游植物多样性指数/H'")
  private String phytoplanktonDiversityIndex;
  @ApiModelProperty("浮游动物密度/1000个/m3")
  private String zooplanktonDensity;
  @ApiModelProperty("浮游动物生物量/mg/m3")
  private String zooplanktonBiomass;
  @ApiModelProperty("浮游动物种类数/个")
  private String zooplanktonSpeciesNumber;
  @ApiModelProperty("浮游动物多样性指数/H")
  private String zooplanktonDiversityIndex;



}
