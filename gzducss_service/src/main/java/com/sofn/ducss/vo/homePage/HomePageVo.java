package com.sofn.ducss.vo.homePage;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 *
 * 首页返回vo
 * @author jiangtao
 * @date 2020/10/29
 */
@ApiModel(value = "首页返回vo")
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class HomePageVo {

    /**
     * 数据范围Vo
     */
    @ApiModelProperty(value = "数据范围")
    private DataArea dataArea;


    /**
     * 数据范围Vo
     */
    @ApiModelProperty(value = "数据范围")
    private List<ReportProgressVo> list;

}