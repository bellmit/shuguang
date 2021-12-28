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
public class MonitorCount {

    @ApiModelProperty("监测点ID")
    private String monitorId;

    @ApiModelProperty("监测点名称")
    private String monitorName;
}
