package com.sofn.agsjdm.vo.exportBean;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.annotation.ExcelSheetInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ExcelSheetInfo(title = "水质监测信息采集", sheetName = "水质监测信息采集")
public class ExportWqBean {

    @ExcelField(title = "湿地区名称")
    private String wetlandName;
    @ExcelField(title = "水源补给状况")
    private String waterSupply;
    @ExcelField(title = "流出状况")
    private String flowOut;
    @ExcelField(title = "积水状况")
    private String accWater;
    @ExcelField(title = "枯水位(m)")
    private Double lowWater;
    @ExcelField(title = "平水位(m)")
    private Double flatWater;
    @ExcelField(title = "丰水位(m)")
    private Double abundantWater;
    @ExcelField(title = "最大水深(m)")
    private Double maxDepth;
    @ExcelField(title = "平均水深(m)")
    private Double avgDepth;
    @ExcelField(title = "蓄水量(万m³)")
    private Double waterDemand;
    @ExcelField(title = "监测人")
    private String operator;
    @ExcelField(title = "监测时间")
    @JSONField(format = "yyyy-MM-dd")
    private Date operatorTime;


}
