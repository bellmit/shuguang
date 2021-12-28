package com.sofn.fdzem.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.sofn.fdzem.model.MonitorStation;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class DistributeVo {
    @JSONField(serializeUsing = ToStringSerializer.class)
    @ApiModelProperty("区域监测中心id")
    private String id;
    /**
     * 区域监测中心名字
     */
    @ApiModelProperty("区域监测中心名字")
    private String name;
    /**
     * 未分配监测站
     */
    @ApiModelProperty("未分配监测站")
    private List<MonitorStation> undistributed;
    /**
     * 已分配监测站
     */
    @ApiModelProperty("已分配监测站")
    private List<MonitorStation> allocated;
}
