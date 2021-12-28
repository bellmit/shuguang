package com.sofn.common.excel.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 获取配置
 * @author heyongjie
 * @date 2019/8/2 15:58
 */
@Data
@ConfigurationProperties(prefix = "sofn.excel")
@Component
public class ExcelProperties {

    /**
     * 创建Excel时的文件路径
     */
    private String createExcelPath = "/home/sofn/excel";

}


