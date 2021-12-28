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
@TableName("tb_biological_residue_monitoring")
@ApiModel("生物残留监测指标实体类")
public class BiologicalResidueMonitoring {

  @TableId(value = "id", type = IdType.INPUT)
  @ApiModelProperty("ID")
  private String id;
  @ApiModelProperty("所属监测点id")
  private String monitoringPointId;
  @ApiModelProperty("大肠菌群/个/100g")
  private String coliform;
  @ApiModelProperty("铜/mg/kg")
  private String cu;
  @ApiModelProperty("铅/mg/kg")
  private String pb;
  @ApiModelProperty("镉/mg/kg")
  private String cd;
  @ApiModelProperty("总汞/mg/kg")
  private String tHg;
  @ApiModelProperty("无机砷/mg/kg")
  private String iAs;
  @ApiModelProperty("铬/mg/kg")
  private String cr;
  @ApiModelProperty("甲基汞/mg/k")
  private String meHg;

}
