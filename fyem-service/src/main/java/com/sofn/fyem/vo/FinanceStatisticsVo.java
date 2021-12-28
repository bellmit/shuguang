package com.sofn.fyem.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author Administrator
 */
@ApiModel
@Data
@EqualsAndHashCode(callSuper = false)
public class FinanceStatisticsVo {

    @ApiModelProperty(name = "countList", value = "统计数据list")
    private List<ReleaseStatisticsCountVo> countList;
}
