package com.sofn.ducss.util;

import com.sofn.ducss.enums.AnalyIndexEnum;
import com.sofn.ducss.vo.DataAnalysis.DataKing;
import com.sofn.ducss.vo.DataKingDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

@Slf4j
public class ExcelDataAnalyUtil {

    public static HSSFWorkbook createWorkbook(List<String> headerList,List<String> contextHeaderList, List<String> strawNameList,List<String> contextStrawNameList, List<DataKingDto> dataList, List<String> year) throws Exception {
        HSSFWorkbook workbook = new HSSFWorkbook();//创建Excel文件(Workbook)
        HSSFSheet sheet = workbook.createSheet("Test");// 创建工作表(Sheet)
       // setWidthHeight(sheet, strawNameList,year,5000);
        createExcellTitle(headerList, sheet,year,workbook,strawNameList);
        createExcelContent(dataList, contextStrawNameList,contextHeaderList,year,sheet);
        return workbook;
    }

    /***
     * 创建表头
     * @param yearList
     * @param sheet
     */
    public static void createExcellTitle(List<String> headerList,HSSFSheet sheet,List<String> yearList,HSSFWorkbook workbook, List<String> strawNameList ){
        HSSFRow row = sheet.createRow(0);// 第一行
        HSSFRow row1 = sheet.createRow(1);// 第二行
        HSSFRow row2 = sheet.createRow(2);// 第三行
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("区域");// 设置单元格内容
        createStyle(cell,workbook);
        CellRangeAddress regions=new CellRangeAddress(0, 2, 0, 0);
        sheet.addMergedRegion(regions); // 合并单元格
        int yearCell;
        int headerCell;
        for (int i = 0; i < yearList.size() ; i++) {
            yearCell = headerList.size()*strawNameList.size()*i + 1;
            HSSFCell cell1 = row.createCell(headerList.size()*strawNameList.size()*i + 1);
            createStyle(cell1, workbook);
            cell1.setCellValue(yearList.get(i));
            for (int j = 0; j < headerList.size() ; j++) {
                headerCell = yearCell + (strawNameList.size()-1) *j + j;
                HSSFCell cell2 = row1.createCell(yearCell + (strawNameList.size()-1) *j + j);
                createStyle(cell2,workbook);
                cell2.setCellValue(headerList.get(j));
                for (int k = 0; k < strawNameList.size() ; k++) {// 作物
                    HSSFCell cell3 = row2.createCell(j*strawNameList.size()+yearCell+k);
                    createStyle(cell3, workbook);
                    cell3.setCellValue(strawNameList.get(k));
                }
                // 合并指标表头
                if (headerCell + strawNameList.size()- 1 > headerCell) {
                    CellRangeAddress region11 = new CellRangeAddress(1, 1, headerCell, headerCell + strawNameList.size() - 1);
                    sheet.addMergedRegion(region11); // 合并单元格
                }
            }
            // 合并指标表头
            //System.out.println(headerList.size()*strawNameList.size()*i + 1 + "==========");
            //System.out.println(yearCell);
            if (headerList.size()*strawNameList.size()*(i+1) > yearCell) {
                CellRangeAddress region1 = new CellRangeAddress(0, 0, yearCell, headerList.size() * strawNameList.size() * (i + 1));
                sheet.addMergedRegion(region1); // 合并单元格
            }
        }
    }


    /***
     *
     * @param dataKingList 数据
     * @param strawNameList 作物列表
     */
    public static void createExcelContent(List<DataKingDto> dataKingList,List<String> strawNameList,List<String> headerList,List<String> yearList,HSSFSheet sheet){
        int j =3;
        int yearCell;
        for (int i = 0; i < dataKingList.size() ; i++) { // 遍历区域
            HSSFRow rows = sheet.createRow(i+j);
            HSSFCell cell1 = rows.createCell(0);
            cell1.setCellValue(dataKingList.get(i).getAreaName());
            for (int k = 0; k < yearList.size() ; k++) {
                yearCell = headerList.size()*strawNameList.size()*k + 1;
                Map<String,Object> map = dataKingList.get(i).getIndicatorArrays().get(yearList.get(k));
                for (int l = 0; l < headerList.size() ; l++) {
                    Map<String,Object> objects = (Map<String, Object>) map.get(headerList.get(l));
                    for (int m = 0; m < strawNameList.size() ; m++) {
                        Object number = objects.get(strawNameList.get(m));
                        int ms = l*strawNameList.size()+yearCell+m;
                        //System.out.println(ms);
                        HSSFCell cellss = rows.createCell(ms);
                        cellss.setCellValue(number.toString());
                    }
                }
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
/*        String filePath="F:\\sample.xls";//文件路径
        List<String> yearList = new ArrayList<>();
        yearList.add("2019");
        yearList.add("2020");
        yearList.add("2021");
        List<String> headList = new ArrayList<>();
        headList.add("产生量");
        headList.add("还田量");
        headList.add("离田量");
        List<String> straList = new ArrayList<>();
        straList.add("全部作物");
        straList.add("早稻");
        straList.add("双季晚稻");
        straList.add("木薯");
        straList.add("其他");
        List<DataKingDto> dataKingList = new ArrayList<>();
        DataKingDto dataKingDto = new DataKingDto();
        dataKingDto.setId("1");
        dataKingDto.setRegionCode("1");
        dataKingDto.setAreaName("北京市");
        Map<String, Map<String,Object>> indicatorArrays = new LinkedHashMap<>();
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("all_type", "0.00");
        map.put("early_rice","0.00");
        Map<String,Object> s = new LinkedHashMap<>();
        s.put("grassValleyRatio",map );
        indicatorArrays.put("2019", s);
        dataKingDto.setIndicatorArrays(indicatorArrays);
        dataKingList.add(dataKingDto);
        HSSFWorkbook workbook =  createWorkbook(headList,straList,dataKingList,yearList);
        FileOutputStream out = new FileOutputStream(filePath);
        workbook.write(out);//保存Excel文件
        out.close();//关闭文件流
        System.out.println("OK!");*/
    }
}
