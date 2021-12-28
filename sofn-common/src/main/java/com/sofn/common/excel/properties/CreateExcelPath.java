package com.sofn.common.excel.properties;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;


/**
 * 程序初始化的时候创建Excel文件路径
 *
 * @author heyongjie
 * @date 2019/8/2 16:21
 */
@Component
@Slf4j
public class CreateExcelPath implements CommandLineRunner {

    @Autowired
    private ExcelProperties excelPathProperties;

    @Override
    public void run(String... args) throws Exception {
        String path = excelPathProperties.getCreateExcelPath();
        File file = new File(path);
        if(!file.exists()){
            log.info("Excel配置的文件路径【{}】不存在，开始创建",path);
            file.mkdirs();
        }
    }
}
