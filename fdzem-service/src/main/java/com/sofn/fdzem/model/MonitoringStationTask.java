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

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_monitoring_station_task")
@ApiModel("监测站信息实体类")
public class MonitoringStationTask {

  @TableId(value = "id", type = IdType.INPUT)
  private String id;
  @ApiModelProperty("所属年度")
  private String year;
  @ApiModelProperty("监测站名称")
  private String name;
  @ApiModelProperty("监测批次数")
  private Integer monitoringStationNumber;
  @ApiModelProperty("所属区域")
  private String area;
  @ApiModelProperty("所属海区域流域")
  private String seaArea;
  @ApiModelProperty("状态:0-未上报,1-待审核/已上报,2-已通过,3-已驳回")
  private Integer status;
  @ApiModelProperty("创建时间")
  private Date createTime;
  @ApiModelProperty("省")
  private String provice;
  @ApiModelProperty("市")
  private String city;
  @ApiModelProperty("县")
  private String county;
  @ApiModelProperty("组织机构id")
  private String organizationId;
  @ApiModelProperty("组织机构父级id")
  private String organizationParentId;

  @ApiModelProperty("监测站地址")
  private String address;

  /**
   * 經度
   */
  @ApiModelProperty("經度")
  private String longitude;

  /**
   * 緯度
   */
  @ApiModelProperty("緯度")
  private String latitude;

  /**
   * 水域类型
   */
  @ApiModelProperty("水域类型")
  private String watersType;

  /**
   * 水域名称
   */
  @ApiModelProperty("水域名称")
  private String watersName;

}
