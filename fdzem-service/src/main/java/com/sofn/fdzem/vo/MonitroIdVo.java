package com.sofn.fdzem.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.checkerframework.checker.units.qual.A;

import java.util.List;

@Data
public class MonitroIdVo {
    @ApiModelProperty("监测中心id")
    private String id;
    @ApiModelProperty("监测中心名称")
    private String name;
    @ApiModelProperty("要分配的监测站id集合")
    private List<Long> monitroIds;
}
