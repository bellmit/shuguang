package com.sofn.common.excel.test.regionexport;

import com.sofn.common.excel.ExcelExportUtil;
import com.sofn.common.excel.core.ExcelExport;
import com.sofn.common.excel.test.regionexport.dto.SysRegionExportDto;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.List;

/**
 * @author heyongjie
 * @date 2019/10/17 16:01
 */
@Slf4j
public class SysRegionExport {

    public static void main(String[] args) throws Exception {
        String filePath = "C:\\Users\\heyongjie\\Desktop\\region_data.xlsx";
        List<SysRegionExportBean> regionExport = SysRegionExportDto.getRegionExport();
        try {
            log.info("开始时间{}",new Date());
            ExcelExportUtil.createExcel(filePath, SysRegionExportBean.class,regionExport);
            log.info("结束时间{}",new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
