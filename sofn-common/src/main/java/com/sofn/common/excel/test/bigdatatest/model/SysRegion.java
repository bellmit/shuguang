package com.sofn.common.excel.test.bigdatatest.model;

import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.annotation.ExcelSheetInfo;
import lombok.Data;

/**
 * Created by heyongjie on 2019/6/19 13:38
 */
@Data
@ExcelSheetInfo(title = "行政区划信息")
public class SysRegion {

    @ExcelField(title = "ID")
    private String id;

    @ExcelField(title = "parentId")
    private String parentId;

    @ExcelField(title = "行政区划名称")
    private String regionName;

    @ExcelField(title = "行政区划代码")
    private String regionCode;

    @ExcelField(title = "排序")
    private Integer sortid ;

    @ExcelField(title = "备注")
    private String remark = "";

    @ExcelField(title = "创建人")
    private String createUserId;

    @ExcelField(title = "test1")
    private String test1 = "123456";

    @ExcelField(title = "test2")
    private String test2 = "123456";

    @ExcelField(title = "test3")
    private String test3 = "123456";

    @ExcelField(title = "test4")
    private String test4 = "123456";

    @ExcelField(title = "test5")
    private String test5 = "123456";


    @ExcelField(title = "test6")
    private String test6 = "123456";

    @ExcelField(title = "test7")
    private String test7 = "123456";

    @ExcelField(title = "test8")
    private String test8 = "123456";

    @ExcelField(title = "test9")
    private String test9 = "123456";

    @ExcelField(title = "test10")
    private String test10 = "123456";

    @ExcelField(title = "test11")
    private String test11 = "123456";

    @ExcelField(title = "test12")
    private String test12 = "123456";

    @ExcelField(title = "test13")
    private String test13 = "123456";

    @ExcelField(title = "test14")
    private String test14 = "123456";

    @ExcelField(title = "test15")
    private String test15 = "123456";

    @ExcelField(title = "test16")
    private String test16 = "123456";

    @ExcelField(title = "test17")
    private String test17 = "123456";

    @ExcelField(title = "test18")
    private String test18 = "123456";

    @ExcelField(title = "test19")
    private String test19 = "123456";

    @ExcelField(title = "test22")
    private String test22 = "123456";

}
