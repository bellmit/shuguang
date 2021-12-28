package com.sofn.ducss.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

/***
 * excel 打包导出
 */
@Data
@ApiModel(value = "excel 打包导出")
public class ZipDataVo<E> {
    private String title;
    private String[] header;
    private List<E> dataList;
    private Class aClass;
}
