package com.sofn.sys.vo.Excel;

import com.sofn.common.excel.annotation.ExcelField;
/*import com.sofn.common.excel.annotation.ExcelFields;*/
import com.sofn.common.excel.annotation.ExcelImportCheck;
import com.sofn.common.excel.annotation.ExcelSheetInfo;
import lombok.Data;

@Data
@ExcelSheetInfo(title = "资源菜单title",sheetName = "资源菜单sheet")
public class ImportSysResourceVo {

        @ExcelField(title = "菜单名称")
        @ExcelImportCheck(isNull =  false,max = 25,min = 1,errMsg = "用户名在1到25之间")
        private String resourceName;

        @ExcelField(title = "类型")
        @ExcelImportCheck(isNull =  false)
        private String type;

        @ExcelField(title = "权限" )
        @ExcelImportCheck(isNull =  false)
        private String permission;

        @ExcelField(title = "父节点")
        @ExcelImportCheck(isNull =  false)
        private String parentId;

        @ExcelField(title = "父节点集合")
        @ExcelImportCheck(isNull =  false)
        private String parentIds;

        @ExcelField(title = "appId")
        @ExcelImportCheck(isNull =  false)
        private String appId;

        @ExcelField(title = "资源URL")
        private String resourceUrl;

        /*@ExcelFields(clazz = ImportUserAreaBan.class)
        private ImportUserAreaBan importUserAreaBan;*/

}
