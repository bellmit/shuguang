/**
 * @Author 文俊云
 * @Date 2020/8/6 16:20
 * @Version 1.0
 */
package com.sofn.fdpi.vo;

import com.sofn.common.utils.PageUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.poi.ss.formula.functions.T;

/**
 * @Author 文俊云
 * @Date 2020/8/6 16:20
 * @Version 1.0
 */

@Data
public class CompVO {

    @ApiModelProperty("分页数据")
    private PageUtils<T> pageUtils;

    @ApiModelProperty("合计数据")
    private CompCountVO compCountVO;
}
