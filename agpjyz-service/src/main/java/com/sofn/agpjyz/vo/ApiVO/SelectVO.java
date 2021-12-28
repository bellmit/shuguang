/**
 * @Author 文俊云
 * @Date 2020/8/24 17:00
 * @Version 1.0
 */
package com.sofn.agpjyz.vo.ApiVO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author 文俊云
 * @Date 2020/8/24 17:00
 * @Version 1.0
 */

@Data
public class SelectVO {
    @ApiModelProperty("KEY")
    private String keyString;
    @ApiModelProperty("VALUE")
    private String valueString;
}
