package com.sofn.fyem.excelmodel;

import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.annotation.ExcelSheetInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description:
 * @Author: DJH
 * @Date: 2020/5/12 9:29
 */
@ApiModel(value = "ProliferationReleaseXlsx", description = "各地区增殖放流关键数据")
@EqualsAndHashCode(callSuper = false)
@Data
public class ProliferationReleaseExcel {

    @ApiModelProperty(name = "region",value = "区域")
    private String region;

    @ApiModelProperty(name = "releaseNumber",value = "放流数量")
    private String releaseNumber;

    @ApiModelProperty(name = "nationInvestment",value = "中央投资")
    private String nationInvestment;

    @ApiModelProperty(name = "provinceInvestment",value = "省级投资")
    private String provinceInvestment;

    @ApiModelProperty(name = "cityInvestment",value = "市县投资")
    private String cityInvestment;

    @ApiModelProperty(name = "societyInvestment",value = "社会投资")
    private String societyInvestment;

    @ApiModelProperty(name = "investmentTotal",value = "投资合计")
    private String investmentTotal;

    @ApiModelProperty(name = "nationReleaseCount",value = "国家放流次数")
    private String nationReleaseCount;

    @ApiModelProperty(name = "provinceReleaseCount",value = "省级放流次数")
    private String provinceReleaseCount;

    @ApiModelProperty(name = "cityReleaseCount",value = "市县放流次数")
    private String cityReleaseCount;

    @ApiModelProperty(name = "otherReleaseCount",value = "其他放流次数")
    private String otherReleaseCount;

    @ApiModelProperty(name = "releaseCountTotal",value = "放流次数合计")
    private String releaseCountTotal;

}
