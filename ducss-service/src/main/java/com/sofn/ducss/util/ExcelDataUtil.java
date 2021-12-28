package com.sofn.ducss.util;

import com.sofn.ducss.vo.DataAnalysis.DataKing;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

/***
 * 分析数据工具类导出 (废弃)
 */
@Deprecated
public class ExcelDataUtil {


    public static HSSFWorkbook createWorkbook(List<String> headerList, List<String> strawNameList, List<DataKing> dataList, String year) throws Exception {
            HSSFWorkbook workbook = new HSSFWorkbook();//创建Excel文件(Workbook)
            HSSFSheet sheet = workbook.createSheet("Test");// 创建工作表(Sheet)
            setWidthHeight(sheet, strawNameList,year,5000);
            createExcellTitle(headerList, sheet,year,workbook);
            createExcelContent(dataList, strawNameList,year,sheet);
            return workbook;
        }

    /***
     * 创建表头
     * @param list
     * @param sheet
     */
    public static void createExcellTitle(List<String> list,HSSFSheet sheet,String year,HSSFWorkbook workbook ){
        HSSFRow row = sheet.createRow(0);// 创建行,从0开始
        HSSFRow row1 = sheet.createRow(1);// 创建行,从0开始
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("所属区域");// 设置单元格内容
        createStyle(cell, workbook);
        HSSFCell cell2 = row.createCell(2);
        cell2.setCellValue(year);// 设置单元格内容
        createStyle(cell2,workbook);
        HSSFCell cell3 = row.createCell(1);
        cell3.setCellValue("作物类型");// 设置单元格内容
        createStyle(cell3,workbook);
        List<String> yearList = null;
        if (year.contains(",")) {
            yearList = ListUtils.splitToList(year);
        }else {
            yearList = new ArrayList<>();
            yearList.add(year);
        }
        CellRangeAddress region1=new CellRangeAddress(0, 1, 0, 0);
        CellRangeAddress region2=new CellRangeAddress(0, 1, 1, 1);
        sheet.addMergedRegion(region1);
        sheet.addMergedRegion(region2);
        for (int i = 0; i < yearList.size() ; i++) {
            for (int j = 0; j < list.size() ; j++) {
                HSSFCell cells1 = row.createCell(i*list.size()+2);
                cells1.setCellValue(yearList.get(i));
                createStyle(cells1, workbook);
                HSSFCell cells2 = row1.createCell(i*list.size()+2+j);
                cells2.setCellValue(list.get(j));
                createStyle(cells2, workbook);
            }
            if (list.size() > 1){
/*                System.out.println(i*list.size()+2 + "=======================");
                System.out.println((i*list.size()+2)+list.size() +"=========================");*/
                CellRangeAddress regions=new CellRangeAddress(0, 0, i*list.size()+2, (i*list.size()+2)+list.size()-1);
                sheet.addMergedRegion(regions);
            }
        }
    }

    /***
     *
     * @param dataKingList 数据
     * @param strawNameList 作物列表
     */
    public static void createExcelContent(List<DataKing> dataKingList,List<String> strawNameList,String year,HSSFSheet sheet){
        int j = 2;
        List<String> yearList = null;
        DecimalFormat df1 = new DecimalFormat("0.00");
        if (year.contains(",")) {
            yearList = ListUtils.splitToList(year);
        }else {
            yearList = new ArrayList<>();
            yearList.add(year);
        }
        for (int i = 0; i <dataKingList.size() ; i++) {
            HSSFRow row = sheet.createRow(j);
            row.createCell(0).setCellValue(dataKingList.get(i).getArea_Name());
            row.createCell(1).setCellValue(dataKingList.get(i).getStrawName());
            for (int s = 0; s < yearList.size() ; s++) {
                for (int k = 0; k < strawNameList.size() ; k++) {
                    row.createCell((s*strawNameList.size()+k)+2).setCellType(CellType.STRING);
                    Object context  = null;
                    if (dataKingList.get(i).getIndicatorArrays().get(yearList.get(s))!= null){
                       context = dataKingList.get(i).getIndicatorArrays().get(yearList.get(s)).get(strawNameList.get(k));
                    }
                    if (context == null || context.equals("OE-10")){
                        context = new BigDecimal("0.00");
                    }
                    if (context instanceof BigDecimal){
                        String number = df1.format(((BigDecimal)context).stripTrailingZeros());
                        row.getCell((s*strawNameList.size()+k)+2).setCellValue(number);
                    }else {
                        String text = (String) context;
                        row.getCell((s*strawNameList.size()+k)+2).setCellValue(text);
                    }
                }
            }
            j++;
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

    public static void   setWidthHeight( HSSFSheet sheet, List list,String year, int width){

        List<String> yearList = null;
        if (year.contains(",")) {
            yearList = ListUtils.splitToList(year);
        }else {
            yearList = new ArrayList<>();
            yearList.add(year);
        }
        for (int i = 0; i <list.size()*yearList.size() +2  ; i++) {
            sheet.setColumnWidth(i, width);
        }
    }

    public static void main(String[] args) throws Exception {
        String filePath="F:\\sample.xls";//文件路径
        List<DataKing> dataKingList = new ArrayList<>();
        DataKing dataKing = new DataKing();
        dataKing.setId("440000");
        dataKing.setArea_Name("/广东省");
        dataKing.setStrawName("全部作物");
        HashMap<String, Map<String, Object>> indicatorArrays = new HashMap<>();
        Map<String, Object> map = new HashMap<>();
        map.put("grainYield","29432070.84");
        indicatorArrays.put("2020", map);

        System.out.println(indicatorArrays.get("2021"));
/*        dataKing.setIndicatorArrays(indicatorArrays);
        dataKingList.add(dataKing);
        List<String> list = new ArrayList<>();
        list.add("粮食产量（吨）");
        list.add("粮食产量（吨）");
        list.add("粮食产量（吨）");
        list.add("粮食产量（吨）");

        List<String> lists = new ArrayList<>();
        lists.add("grainYield");
        HSSFWorkbook workbook =  createWorkbook(list,lists,dataKingList,"2018,2019");

        FileOutputStream out = new FileOutputStream(filePath);
        workbook.write(out);//保存Excel文件
        out.close();//关闭文件流
        System.out.println("OK!");*/
    }
}
