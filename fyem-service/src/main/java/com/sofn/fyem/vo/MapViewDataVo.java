package com.sofn.fyem.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 * @Description:
 * @Author: DJH
 * @Date: 2020/7/30 14:58
 */
@ApiModel(value = "MapViewDataVo", description = "分布地图参数Vo")
@Data
@EqualsAndHashCode(callSuper = false)
public class MapViewDataVo {
    @ApiModelProperty(name = "index", value = "指标")
    private String index;

    @ApiModelProperty(name = "adLevel", value = "行政级别")
    private String adLevel;

    @ApiModelProperty(name = "adCode", value = "行政区域代码或行政区域名称")
    private String adCode;

    @ApiModelProperty(name = "conditions", value = "条件")
    private Map<String, String> conditions;
}
