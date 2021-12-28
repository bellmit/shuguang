package com.sofn.agsjsi.vo.excelBean;

import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.annotation.ExcelSheetInfo;
import lombok.Data;
import java.io.Serializable;

@Data
@ExcelSheetInfo(title="农业湿地示范点收集",sheetName = "农业湿地示范点收集")
public class WetExamplePointCollectExcel implements Serializable {
    //采集时间
    @ExcelField(title = "湿地区名称")
    private String wetName;
    //采集地点
    @ExcelField(title="湿地区编码")
    private String wetCode;
    @ExcelField(title="湿地总面积")
    private String wetTotalArea;
    @ExcelField(title="湿地斑块数量")
    private String wetPlaqueCount;
    @ExcelField(title="湿地类")
    private String wetType1;
    @ExcelField(title="类面积")
    private String wetTypeArea1;
    //植株高度
    @ExcelField(title="主要湿地型")
    private String wetModel1;
    //胸径
    @ExcelField(title="行面积1")
    private String wetModelArea1;
    @ExcelField(title="湿地类")
    private String wetType2;
    @ExcelField(title="类面积")
    private String wetTypeArea2;
    //植株高度
    @ExcelField(title="主要湿地型")
    private String wetModel2;
    //胸径
    @ExcelField(title="行面积")
    private String wetModelArea2;
    @ExcelField(title="县行政区")
    private String regionInCh;
    @ExcelField(title="北纬")
    private String northLatitude;
    @ExcelField(title="东经")
    private String eastLongitude;
    @ExcelField(title="所属二级流域")
    private String secondBasin;
    @ExcelField(title="河流级别")
    private String riverLevel;
    @ExcelField(title="平均海拔")
    private String avgAltitude;
    @ExcelField(title="水源补给情况")
    private String waterSupply;
    @ExcelField(title="近海域海岸湿地")
    private String wetNearSea;
    @ExcelField(title="盐度")
    private String salinity;
    @ExcelField(title="水温")
    private String waterTemp;
    @ExcelField(title="土地所有权")
    private String landOwnership;
    @ExcelField(title="植被类型")
    private String plantType;
    @ExcelField(title="植被面积")
    private String plantArea;
    @ExcelField(title="中文学名")
    private String nameInCh;
    @ExcelField(title="拉丁学名")
    private String latinName;
    @ExcelField(title="科名")
    private String familyName;
}
