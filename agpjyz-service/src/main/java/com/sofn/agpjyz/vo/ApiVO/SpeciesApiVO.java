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
public class SpeciesApiVO {
    @ApiModelProperty("年份")
    private String yearString;
    @ApiModelProperty("数目")
    private String speciesNumber;
}
