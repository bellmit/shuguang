package com.sofn.common.excel.test;

import com.sofn.common.excel.ExcelExportUtil;
import com.sofn.common.excel.test.testimport.ImportUserBean;
import lombok.extern.slf4j.Slf4j;

/**
 * 导入Excel 测试
 *
 * Excel 注解类请查看 ImportUserBean
 * Created by heyongjie on 2019/6/14 15:26
 */
@Slf4j
public class ExcelUtilTest {

    public static void main(String[] args) {

        // 1. 导出模板
        String filePath = "C:\\Users\\heyongjie\\Desktop\\export2.xlsx";
        ExcelExportUtil.createTemplate(filePath, ImportUserBean.class);

//        // 2.导入
        // 读取第1个sheet
//        String readFilePath = "C:\\Users\\heyongjie\\Desktop\\export3.xlsx";
//        List<ImportUserBean> importUserBeans = ExcelImport.getDataByExcel(readFilePath, -1, ImportUserBean.class);
        // 读取指定的Sheet数据
//        List<ImportUserBean> importUserBeans = ExcelImport.getDataByExcelAndSheet(filePath, -1, ImportUserBean.class, ExcelField.Type.IMPORT,1);
//        System.out.println(importUserBeans);

        // 3. 导出
      /*  String exportFilePath = "C:\\Users\\heyongjie\\Desktop\\export5.xlsx";
//        ExcelExport.createExcel(exportFilePath,ImportUserBean.class,importUserBeans);


        // 4. 从数据源导出
        SysRegionDto sysRegionDto = new SysRegionDto();
        try {
            List<SysRegion> sysRegions = sysRegionDto.getData();
            System.out.println(sysRegions.size());
            log.info("开始时间{}",new Date());
            ExcelExportUtil.createExcel(exportFilePath, SysRegion.class,sysRegions);
            log.info("结束时间{}",new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
*/

    }

}
