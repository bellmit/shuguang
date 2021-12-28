package com.sofn.ducss.util;

import com.sofn.ducss.model.excelmodel.StrawUtilizeExcel;
import com.sofn.ducss.model.excelmodel.YieldAndReturnExportExcel;
import com.sofn.ducss.vo.DataAnalysis.DataKing;
import com.xiaoleilu.hutool.poi.excel.ExcelUtil;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * Zip数据工具类导出
 */
public class ExcelDataUtil {


    public static<E> HSSFWorkbook createWorkbook(HSSFWorkbook workbook,List<E> list,Class classes,String[] header){
            HSSFSheet sheet = workbook.createSheet("Test");// 创建工作表(Sheet)
            if (header == null || header.length ==0){ // 市场主体表格表头
                createExcellTitle(sheet,workbook);
                setWidthHeight(sheet,list,6000);
            }else {
                createBasicExcelHeader(sheet,workbook,header);
                setWidthHeight(sheet,list,5000);
            }
            createExcelContent(list,sheet,classes,header);
            return workbook;
        }

    /***
     * 创建表头
     * @param sheet
     */
    public static<E> void createExcellTitle(HSSFSheet sheet, HSSFWorkbook workbook) {
        //String[] header = {"秸秆类型","粮食产量（吨）","草谷比", "可收集系数","播种面积（亩）","还田面积（亩）","秸秆调出量（吨）"};
        // String[] header = {"市场主体名称","法人名称","法人电话", "地址","市场主体规模化秸秆利用量（吨）","还田面积（亩）","秸秆调出量（吨）"};
        createMarketPlayers(sheet,workbook);
    }

    /***
     * 创建内容表格
     */
    public static <E> void createExcelContent(List<E> data, HSSFSheet sheet, Class classes,String[] header){
        int a = 1;
        if (header == null){
            a = 2;
        }
        Field[] fields = classes.getDeclaredFields();
        for (int i = 0; i < data.size(); i++) {
            HSSFRow row = sheet.createRow(i+a);
            Map<String,Object> map = ListUtils.Obj2Map(data.get(i));
            for (int j = 0; j < fields.length ; j++) {
                System.out.println(fields[j].getName());
                String key = fields[j].getName();
                System.out.println(map.get(key));
                String value = "";
                if (map.get(key) == null){
                    value = "0.00";
                }else {
                    if (map.get(key) instanceof BigDecimal){
                        value =  ((BigDecimal) map.get(key)).setScale(2,BigDecimal.ROUND_DOWN).toEngineeringString();
                    }else {
                        value = map.get(key).toString();
                    }
                    if (value.equals("0E-10")){
                        value ="0.00";
                    }
                }
                HSSFCell cell =  row.createCell(j);
                cell.setCellValue(value);
            }
        }
    }

    public static void createStyle(HSSFCell cell, HSSFWorkbook workbook){
        CellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        //自定义字体颜色, 同单元格样式
        HSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short) 9);
        //字体设置为Arial
        font.setFontName(HSSFFont.FONT_ARIAL);
        style.setFont(font);
        cell.setCellStyle(style);
    }

    public static void   setWidthHeight( HSSFSheet sheet, List list, int width){
        for (int i = 0; i <list.size(); i++) {
            sheet.setColumnWidth(i, width);
        }
    }

    /***
     *  创建基础表头
     * @param sheet
     * @param header
     */
    private static void createBasicExcelHeader(HSSFSheet sheet,HSSFWorkbook wb, String[] header){
        HSSFRow row = sheet.createRow(0);// 表头
        for (int i = 0; i < header.length ; i++) {
            HSSFCell cell =  row.createCell(i);
            createStyle(cell,wb);
            cell.setCellValue(header[i]);
        }
    }

    /***
     * 创建市场主体表头
     * @param sheet
     */
    private static void createMarketPlayers(HSSFSheet sheet, HSSFWorkbook workbook){
        String[] header = {"主体编码","市场主体名称","法人名称", "法人电话","地址","农作物类型"};
        // 市场主体表头
        String[] headerList ={"饲料化(吨)","饲料化(吨)","燃料化(吨)","基料化(吨)","原料化(吨)","外县来源(吨)","本县来源(吨)"};
        HSSFRow row = sheet.createRow(0);// 表头
        HSSFRow row1 = sheet.createRow(1);// 表头
        for (int i = 0; i < 6 ; i++) {
            HSSFCell cell1 = row.createCell(i);
            createStyle(cell1,workbook);
            cell1.setCellValue(header[i]);
            CellRangeAddress region1=new CellRangeAddress(0, 1, i, i);
            sheet.addMergedRegion(region1);
        }
        HSSFCell cells = row.createCell(6);
        createStyle(cells,workbook);
        cells.setCellValue("市场主体规模化秸秆利用量（吨）");
        HSSFCell cells1 = row.createCell(11);
        createStyle(cells1,workbook);
        cells1.setCellValue("年总利用量(吨)");
        for (int i = 0; i < headerList.length  ; i++) {
            HSSFCell cell1 = row1.createCell(6+i);
            createStyle(cell1,workbook);
            cell1.setCellValue(headerList[i]);
        }
        // 合并单元格
        CellRangeAddress regions1=new CellRangeAddress(0, 0, 6, 10);
        sheet.addMergedRegion(regions1);
        CellRangeAddress regions2=new CellRangeAddress(0, 0, 11, 12);
        sheet.addMergedRegion(regions2);
    }

    public static void main(String[] args) throws Exception {
        String filePath="F:\\sample.xls";//文件路径
/*        List<YieldAndReturnExportExcel> list = new ArrayList<>();
        YieldAndReturnExportExcel yieldAndReturnExportExcel = new YieldAndReturnExportExcel();
        yieldAndReturnExportExcel.setStrawName("a");
        yieldAndReturnExportExcel.setCountyName("1");
        yieldAndReturnExportExcel.setReturnArea(new BigDecimal(0));
        yieldAndReturnExportExcel.setCollectionRatio(new BigDecimal(0));
        yieldAndReturnExportExcel.setGrainYield(new BigDecimal(1));
        yieldAndReturnExportExcel.setGrassValleyRatio(new BigDecimal("1"));
        list.add(yieldAndReturnExportExcel);*/
        List<StrawUtilizeExcel> list = new ArrayList<>();
        StrawUtilizeExcel strawUtilizeExcel = new StrawUtilizeExcel();
        strawUtilizeExcel.setMainNo("1");
        strawUtilizeExcel.setOwnCountry(new BigDecimal("1"));
        strawUtilizeExcel.setAddress("1123123");
        strawUtilizeExcel.setCorporationName("1231231");
        strawUtilizeExcel.setBase(new BigDecimal("0"));
        strawUtilizeExcel.setFertilising(new BigDecimal("0.11"));
        strawUtilizeExcel.setFuel(new BigDecimal("0.00"));
        strawUtilizeExcel.setStrawName("12321");
        list.add(strawUtilizeExcel);
        HSSFWorkbook workbook = new HSSFWorkbook();
        Workbook workbook1 = createWorkbook(workbook,list,StrawUtilizeExcel.class,null);
        FileOutputStream out = new FileOutputStream(filePath);
        workbook1.write(out);//保存Excel文件
        out.close();//关闭文件流
        System.out.println("OK!");
    }
}
