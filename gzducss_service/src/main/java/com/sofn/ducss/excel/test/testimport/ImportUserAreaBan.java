package com.sofn.ducss.excel.test.testimport;

import com.sofn.ducss.excel.annotation.ExcelField;
import com.sofn.ducss.excel.annotation.ExcelFields;

import java.util.List;

/**
 * Created by heyongjie on 2019/6/13 17:48
 */
public class ImportUserAreaBan {

    @ExcelField(title = "区域名称")
    private String area;

    private List test;

    @ExcelFields(clazz = ImportAreaYBean.class)
    private ImportAreaYBean importAreaYBean;
}
