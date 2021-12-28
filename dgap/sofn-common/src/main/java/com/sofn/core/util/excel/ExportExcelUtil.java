package com.sofn.core.util.excel;

import org.apache.poi.hssf.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.poi.hssf.util.Region;
/**
 *
 * @author
 * @version 1.0
 * @date
 */
public class ExportExcelUtil {
    /**
     * 创建Excel表格
     *
     * @param object
     * @param outStream
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    public static void exportExcel(ExcelEntity object, OutputStream outStream) throws Exception {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet(object.getSheetName());
        HSSFRow row = sheet.createRow(0);// 创建第一行
        HSSFCell cell = row.createCell(0);// 创建第一行的第一个单元格
        cell.setCellValue("序号");
        String[] colNames = object.getColumnNames();
        String[] propertys = object.getPropertyNames();
        for (int i = 0; i < colNames.length; i++) { // 添加列名，从第一行的第二个单元格开始添加
            row.createCell(i + 1).setCellValue(colNames[i]);
        }
        Iterator it = object.getResultList().iterator();
        int rowNum = 1; // 从第二行开始添加数据
        while (it.hasNext()) {
            Map map = (Map) it.next();
            HSSFRow rw = sheet.createRow(rowNum);
            rw.createCell(0).setCellValue(rowNum); // 添加序号
            rowNum++;
            for (int x = 0; x < propertys.length; x++) {
                String property = propertys[x];
                if (map.containsKey(property)) {
                    Object value = map.get(propertys[x]); // 根据属性名称得到属性值
                    if (value == null || "null".equalsIgnoreCase(value.toString())) {
                        value = "";
                    }
                    rw.createCell(x + 1).setCellValue(value + "");
                } else {
                    rw.createCell(x + 1).setCellValue("");
                }
            }
        }
        try {
            wb.write(outStream);
            outStream.flush();
            outStream.close();
        } catch (IOException e) {
            if (outStream != null) {
                outStream.close();
            }
            e.printStackTrace();
        }
    }

    /**
     * 导出Excel
     *
     * @param response
     * @param list
     * @param columns
     * @param propertyNames
     * @throws IOException
     * @throws UnsupportedEncodingException
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    public static void exportView(HttpServletResponse response, List<Map> list, String[] columns, String[] propertyNames) throws IOException, UnsupportedEncodingException, Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        ExcelEntity entity = new ExcelEntity();
        entity.setColumnNames(columns);
        entity.setPropertyNames(propertyNames);
        entity.setResultList(list);
        entity.setSheetName(sdf.format(new Date()));

        OutputStream outStream = response.getOutputStream();
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        String fileName = sdf.format(new Date()) + ".xls";
        response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("GBK"), "iso8859-1"));
        exportExcel(entity, outStream);
    }

    /**
     * 创建通用EXCEL头部
     *
     * @param headString
     *            头部显示的字符
     * @param colSum
     *            该报表的列数
     */
    @SuppressWarnings("deprecation")
    public void createNormalHead(String headString, int colSum, String sheetName) {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet(sheetName);
        HSSFRow row = sheet.createRow(0);

        // 设置第一行
        HSSFCell cell = row.createCell(0);
        row.setHeight((short) 400);

        // 定义单元格为字符串类型
        cell.setCellType(HSSFCell.ENCODING_UTF_16);
        cell.setCellValue(new HSSFRichTextString(headString));

        // 指定合并区域
        sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) colSum));

        HSSFCellStyle cellStyle = wb.createCellStyle();

        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 指定单元格居中对齐
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 指定单元格垂直居中对齐
        cellStyle.setWrapText(true);// 指定单元格自动换行

        // 设置单元格字体
        HSSFFont font = wb.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setFontName("宋体");
        font.setFontHeight((short) 300);
        cellStyle.setFont(font);

        cell.setCellStyle(cellStyle);
    }
}
