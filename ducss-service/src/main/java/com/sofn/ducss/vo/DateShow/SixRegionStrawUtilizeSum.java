package com.sofn.ducss.vo.DateShow;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 六大区选定秸秆类型汇总数据Vo
 *
 * @author jiangtao
 * @date 2020/11/13
 */
@Data
@ApiModel(value = "六大区选定秸秆类型汇总数据Vo")
@NoArgsConstructor
@AllArgsConstructor
public class SixRegionStrawUtilizeSum<T> {

    @ApiModelProperty(value = "大区名称")
    private String regionName;

    @ApiModelProperty(value = "对应大区数据")
    private T  t;

    @ApiModelProperty(value = "对应大区区域id集合,逗号隔开")
    private String  regionCodes;
}