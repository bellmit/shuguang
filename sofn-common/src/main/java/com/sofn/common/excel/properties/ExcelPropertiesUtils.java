package com.sofn.common.excel.properties;

import com.sofn.common.utils.SpringContextHolder;

/**
 * 获取Excel配置信息工具
 * @author heyongjie
 * @date 2019/8/2 17:00
 */
public class ExcelPropertiesUtils {

    /**
     * 获取配置的Excel生成路径
     *
     * @return
     */
    public static String getExcelFilePath() {
        ExcelProperties excelProperties = SpringContextHolder.getBean(ExcelProperties.class);
        return excelProperties.getCreateExcelPath();
    }


}
