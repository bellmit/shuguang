package com.sofn.ducss.vo.excelVo;

import com.sofn.common.excel.annotation.ExcelField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author huanghao
 * @version 1.0
 * @date 2020/8/31 11:18
 * @description 数据分析导出实体类
 **/
@Data
public class DataAnalysisExcelVo implements Serializable {
    private static final long serialVersionUID = -303356328670450346L;
    @ApiModelProperty(value = "区域")
    @ExcelField(title = "区域")
    private String area_Name;

    @ApiModelProperty(value = "类型")
    @ExcelField(title = "类型")
    private String strawName;

    @ApiModelProperty(value = "年份")
    @ExcelField(title = "年份")
    private String gYear;

    @ApiModelProperty(value = "指标数组")
    @ExcelField(title = "指标数组")
    private String indexs;

    @ApiModelProperty(value = "指标数组")
    @ExcelField(title = "指标数组")
    private String indicatorArrays;

    public DataAnalysisExcelVo(String area_Name, String strawName, String gYear, String indexs, String indicatorArrays) {
        this.area_Name = area_Name;
        this.strawName = strawName;
        this.gYear = gYear;
        this.indexs = indexs;
        this.indicatorArrays = indicatorArrays;
    }
}
