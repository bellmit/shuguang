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
@TableName("tb_sediment_monitoring")
@ApiModel("沉淀物监测指标实体类")
public class SedimentMonitoring {

  @TableId(value = "id", type = IdType.INPUT)
  @ApiModelProperty("ID")
  private String id;
  @ApiModelProperty("所属监测点id")
  private String monitoringPointId;
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



}
