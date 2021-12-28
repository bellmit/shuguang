package com.sofn.fyem.excelmodel;

import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.annotation.ExcelSheetInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description: 分布图示-导出模板
 * @Author: DJH
 * @Date: 2020/5/13 18:23
 */
@ApiModel(value = "ProliferationReleaseXlsx", description = "分布图示")
@ExcelSheetInfo(title = "分布图示-导出模板", sheetName = "表一")
@Data
public class ProliferationReleaseDistributionExcel {

    @ExcelField(title = "区域名")
    @ApiModelProperty(value = "区域名")
    private String regionName;

    @ExcelField(title = "经度")
    @ApiModelProperty(value = "经度")
    private String longitude;

    @ExcelField(title = "纬度")
    @ApiModelProperty(value = "纬度")
    private String latitude;

    @ExcelField(title = "放流总次数")
    @ApiModelProperty(value = "放流总次数")
    private String releaseCountTotal;
}
