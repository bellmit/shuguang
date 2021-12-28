package com.sofn.common.excel.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Excel导入检查配置
 * Created by heyongjie on 2019/6/13 9:13
 */
@Target({ ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelImportCheck {

    /**
     * 是否可为空
     * @return
     */
    boolean isNull() default true;

    /**
     *
     * 字符串时为最大长度，数值时为最大值   为日期时满足格式：年月日，如：20190225;年月,如201902；年2019,其余都不满足
     * @return
     */
    long max() default -1;

    /**
     * 字符串时为最小长度，数值时为最小值   为日期时满足格式：年月日，如：20190225;年月,如201902；年2019,其余都不满足
     * @return
     */
    long min() default  -1;

    /**
     * 用于在值不符合规范的时候的提示消息
     * @return
     */
    String errMsg() default "输入内容不符合规范";





}
