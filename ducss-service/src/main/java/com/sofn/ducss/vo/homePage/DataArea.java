package com.sofn.ducss.vo.homePage;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 数据范围Vo
 * @author jiangtao
 *
 * @date 2020/10/29
 */
@ApiModel(value = "数据范围Vo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataArea {


    /**
     * 填报县数
     */
    @ApiModelProperty(value = "填报县数")
    private String reportCounty;

    /**
     * 市场规模化主体数
     */
    @ApiModelProperty(value = "市场规模化主体数")
    private String strawUtilize;

    /**
     * 抽样分散户数
     */
    @ApiModelProperty(value = "抽样分散户数")
    private String disperseUtilize;
}