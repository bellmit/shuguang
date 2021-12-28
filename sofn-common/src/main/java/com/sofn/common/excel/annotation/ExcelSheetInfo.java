package com.sofn.common.excel.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Excel标题和Sheet信息
 * Created by heyongjie on 2019/6/13 13:46
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelSheetInfo {

    /**
     * Sheet名称
     * @return
     */
    String sheetName() default "sheet";

    /**
     * 表格标题
     * @return
     */
    String title() default "";

}
