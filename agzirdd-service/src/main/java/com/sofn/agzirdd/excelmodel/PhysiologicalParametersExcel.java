package com.sofn.agzirdd.excelmodel;

import com.alibaba.fastjson.annotation.JSONField;
import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.annotation.ExcelSheetInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author Administrator
 */
@Data
@ApiModel(value = "PhysiologicalParametersXlsx", description = "外来入侵生物植被生理参数信息")
@ExcelSheetInfo(title = "外来入侵生物植被生理参数信息列表", sheetName = "表一")
public class PhysiologicalParametersExcel {

    @ExcelField(title = "监测点名称")
    @ApiModelProperty(name = "monitorName", value = "监测点名称")
    private String monitorName;

    @ExcelField(title = "植物吸收性光合有效辐射分量")
    @ApiModelProperty(name = "leafAreaIndex", value = "植物吸收性光合有效辐射分量")
    private String leafAreaIndex;

    @ExcelField(title = "叶面积指数")
    @ApiModelProperty(name = "effectiveRadiationComponent", value = "叶面积指数")
    private String effectiveRadiationComponent;

    @ExcelField(title = "表观光合量子效率")
    @ApiModelProperty(name = "photosyntheticQuantum", value = "表观光合量子效率")
    private String photosyntheticQuantum;

    @ExcelField(title = "状态")
    @ApiModelProperty(name = "status", value = "状态")
    private String status;

    @ExcelField(title = "操作人")
    @ApiModelProperty(name = "createUserName", value = "创建者姓名")
    private String createUserName;

    @ExcelField(title = "操作时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(name = "createTime", value = "创建时间")
    private Date createTime;
}
