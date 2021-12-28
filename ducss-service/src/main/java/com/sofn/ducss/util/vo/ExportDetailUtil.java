/**
 * @Author 文俊云
 * @Date 2020/3/5 10:41
 * @Version 1.0
 */
package com.sofn.ducss.util.vo;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * @Author 文俊云
 * @Date 2020/3/5 10:41
 * @Version 1.0
 */


public class ExportDetailUtil {

        private static final Logger LOGGER = LoggerFactory.getLogger(ExportDetailUtil.class);

        private Workbook workBook;
        private Sheet sheet;

        public ExportDetailUtil(String fileFullPath) {
            this(fileFullPath, null);
        }

        /**单条数据的初始化
         * @author 文俊云
         * @date 2020-03-05 14:12
         * @param
         * @return
         * @throws
         * @since
         */
        public ExportDetailUtil(String fileFullPath, String sheetName) {
            // 解决版本问题，HSSFWorkbook是97-03版本的xls版本，XSSFWorkbook是07版本的xlsx
            try {
                workBook = new XSSFWorkbook(new FileInputStream(fileFullPath));
            } catch (Exception e) {
                try {
                    workBook = new HSSFWorkbook(new FileInputStream(fileFullPath));
                } catch (Exception e1) {
                    LOGGER.error("Excel格式不正确", e1);
                    throw new RuntimeException(e1);
                }
            }

            // 进行模板的克隆(接下来的操作都是针对克隆后的sheet)
            sheet = workBook.cloneSheet(0);
            // 移除workbook中的模板sheet
            workBook.removeSheetAt(0);
            // 重命名克隆后的sheet
            workBook.setSheetName(0, sheetName != null ? sheetName : "sheet1");
        }

        /**多条数据的初始化
         * @author 文俊云
         * @date 2020-03-05 14:12
         * @param
         * @return
         * @throws
         * @since
         */
        public ExportDetailUtil(String fileFullPath, int size) {
            // 解决版本问题，HSSFWorkbook是97-03版本的xls版本，XSSFWorkbook是07版本的xlsx
            try {
                workBook = new XSSFWorkbook(new FileInputStream(fileFullPath));
            } catch (Exception e) {
                try {
                    workBook = new HSSFWorkbook(new FileInputStream(fileFullPath));
                } catch (Exception e1) {
                    LOGGER.error("Excel格式不正确", e1);
                    throw new RuntimeException(e1);
                }
            }

            for (int i=0;i<size;i++){
                // 进行模板的克隆(接下来的操作都是针对克隆后的sheet)
                sheet = workBook.cloneSheet(0);
                // 重命名克隆后的sheet
                workBook.setSheetName(i+1, "sheet+"+i+1);
            }
            // 移除workbook中的模板sheet
            workBook.removeSheetAt(0);

        }

    /**多条数据的初始化
     * @author 文俊云
     * @date 2020-03-05 14:12
     * @param
     * @return
     * @throws
     * @since
     */
    public ExportDetailUtil(InputStream  is, int size) {
        // 解决版本问题，HSSFWorkbook是97-03版本的xls版本，XSSFWorkbook是07版本的xlsx
        try {
            workBook = new XSSFWorkbook(is);
        } catch (Exception e) {
            try {
                workBook = new HSSFWorkbook(is);
            } catch (Exception e1) {
                LOGGER.error("Excel格式不正确", e1);
                throw new RuntimeException(e1);
            }
        }

        for (int i=0;i<size;i++){
            // 进行模板的克隆(接下来的操作都是针对克隆后的sheet)
            sheet = workBook.cloneSheet(i);
            // 重命名克隆后的sheet
            workBook.setSheetName(i+1, "sheet+"+i+1);
        }
        // 移除workbook中的模板sheet
        workBook.removeSheetAt(0);

    }

    public ExportDetailUtil(InputStream  is) {
        // 解决版本问题，HSSFWorkbook是97-03版本的xls版本，XSSFWorkbook是07版本的xlsx
        try {
            workBook = new XSSFWorkbook(is);
        } catch (Exception e) {
            try {
                workBook = new HSSFWorkbook(is);
            } catch (Exception e1) {
                LOGGER.error("Excel格式不正确", e1);
                throw new RuntimeException(e1);
            }
        }
    }

        public String getCellValue(int rowNum, int colNum) {
            return getCellValue(rowNum, colNum, "");
        }

        /**
         * 根据行号列号获取值
         *
         * @param rowNum
         *            行号
         * @param colNum
         *            列号
         * @param defaultValue
         *            默认值
         * @return
         */
        public String getCellValue(int rowNum, int colNum, String defaultValue) {
            Row row = sheet.getRow(rowNum);
            if (row == null) {
                return defaultValue;
            }

            Cell cell = row.getCell(colNum);
            if (cell == null) {
                return defaultValue;
            }

            return getCellValue(cell, defaultValue);
        }

        public String getCellValue(Cell cell) {
            return getCellValue(cell, "");
        }

        /**
         * 读取cell的值
         *
         * @param cell
         *            需要读取的cell
         * @param defaultValue
         *            默认值
         * @return
         */
        public String getCellValue(Cell cell, String defaultValue) {
            if (cell != null) {
                //cell.setCellType(cell.CELL_TYPE_STRING);
                cell.setCellType(CellType.STRING);
                return cell.getStringCellValue();
            }

            return defaultValue;
        }

        /**
         * 替换单元格的内容，单元格的获取位置是合并单元格之前的位置，也就是下标都是合并之前的下表
         *
         * @param cell
         *            单元格
         * @param value
         *            需要设置的值
         */
        public void replaceCellValue(Cell cell, Object value) {
            String val = value != null ? String.valueOf(value) : "";
            cell.setCellValue(val);
        }

        /**
         * 根据行号，列号进行替换
         *
         * @param rowNum
         *            行号
         * @param colNum
         *            列号
         * @param value
         *            值
         */
        public void replaceCellValue(int rowNum, int colNum, Object value) {
            Row row = sheet.getRow(rowNum);
            if (row == null) {
                return;
            }

            Cell cell = row.getCell(colNum);
            if (cell == null) {
                return;
            }

            replaceCellValue(cell, value);
        }

        /**
         * 向sheet中添加行，后面的行会向后自动移动
         *
         * @param startRowIndex
         *            起始行
         * @param datas
         *            数据
         * @param keys
         *            数据中Map对应的key
         */
        public void appendRows(int startRowIndex, List<Map<String, Object>> datas, String[] keys) {
            // 插入行
            sheet.shiftRows(startRowIndex, sheet.getLastRowNum(), datas.size(), true, false);// 第1个参数是指要开始插入的行，第2个参数是结尾行数,第三个参数表示动态添加的行数
            // 向插入的行中动态的填充数据
            for (int i = 0; i < datas.size(); i++) {
                Map<String, Object> data = datas.get(i);
                // 创建行
                Row row = sheet.createRow(startRowIndex + i);
                row.setHeight(sheet.getRow(0).getHeight());

                // 添加单元格
                Cell cell = null;
                for (int j = 0, length_2 = keys.length; j < length_2; j++) {
                    String key = keys[j];
                    String value = MapUtils.getString(data, key, "");
                    cell = row.createCell(j);
                    //cell.setCellType(Cell.CELL_TYPE_STRING);
                    cell.setCellType(CellType.STRING);
                    cell.setCellValue(value);
                }
            }

            // 调整列宽
            autoResizeColumn(keys.length);
        }

        public void exportExcel(File file) {
            exportExcel(file.getAbsolutePath());
        }

        public void exportExcel(String fileFullPath) {
            OutputStream outputStream = null;
            try {
                outputStream = new FileOutputStream(fileFullPath);
                workBook.write(outputStream);
            } catch (IOException e) {
                LOGGER.error(" exportExcel error", e);
            } finally {
                IOUtils.closeQuietly(outputStream);
            }
        }

        /**
         * 输出数据流
         *
         * @param os 输出数据流
         */
        public void write(OutputStream os) {
            try {
                workBook.write(os);
                os.flush();
            } catch (IOException ex) {
                LOGGER.error(ex.getMessage(), ex);
            }finally {
                IOUtils.closeQuietly(os);
            }
        }

        private void autoResizeColumn(int colNumber) {
            // 如果是SXSSFSheet，需要调用trackAllColumnsForAutoSizing方法一次
            if (sheet instanceof SXSSFSheet) {
                SXSSFSheet tmpSheet = (SXSSFSheet) sheet;
                tmpSheet.trackAllColumnsForAutoSizing();
            }

            for (int i = 0; i < colNumber; i++) {
                sheet.autoSizeColumn(i, true);
            }
        }

        public Sheet getSheet() {
            return sheet;
        }
        public void setSheet(Sheet sheet) {
        this.sheet=sheet;
    }
        public Workbook getWorkbook() {
        return workBook;
    }
}
