/**
 * @Author 文俊云
 * @Date 2020/6/8 15:48
 * @Version 1.0
 */
package com.sofn.agpjyz.vo.ApiVO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author 文俊云
 * @Date 2020/6/8 15:48
 * @Version 1.0
 */

@Data
public class MonitorApi {

    @ApiModelProperty("年份")
    private String yearString;

    @ApiModelProperty("分布面积")
    private double distribution;

    @ApiModelProperty("受损面积")
    private double damage;

}
