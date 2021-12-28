package com.sofn.ducss.vo.homePage;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 上报审核进度
 *
 * @author liuqiang
 * @date 2020/10/29
 */
@ApiModel(value = "上报审核进度")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportProgressVo {

    /**
     * 区域等级
     */
    @ApiModelProperty(value = "区域等级")
    private String AreaLevel;

    /**
     * 已审核县数量
     */
    @ApiModelProperty(value = "已审核县数量")
    private String auditCounty;

    /**
     * 已上报县数量
     */
    @ApiModelProperty(value = "已上报县数量")
    private String reportCounty;


    /**
     * 未上报县数量
     */
    @ApiModelProperty(value = "未上报县数量")
    private String noReportCounty;

}