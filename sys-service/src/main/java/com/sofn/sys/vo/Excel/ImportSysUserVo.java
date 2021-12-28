package com.sofn.sys.vo.Excel;

import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.annotation.ExcelImportCheck;
import com.sofn.common.excel.annotation.ExcelSheetInfo;
import lombok.Data;

/**
 * 导入Excel测试类
 *
 * @author heyongjie
 * @date 2020年1月19日14:10:35
 */
@Data
@ExcelSheetInfo(title = "用户信息", sheetName = "用户信息")
public class ImportSysUserVo {

    @ExcelField(title = "用户名")
    @ExcelImportCheck(isNull = false, max = 25, min = 1, errMsg = "用户名在1到25之间")
    private String userName;

    @ExcelField(title = "昵称")
    @ExcelImportCheck(isNull = false, max = 25, min = 1, errMsg = "用户名在1到25之间")
    private String nickname;

    @ExcelField(title = "性别", customDictValue = {"男", "女"})
    @ExcelImportCheck(isNull = false)
    private String sex;

    @ExcelField(title = "组织机构名称")
    private String organizationName;

    @ExcelField(title = "角色名称")
    private String roleNames;

    @ExcelField(title = "角色组名称")
    private String groupNames;

    @ExcelField(title = "密码")
    @ExcelImportCheck(isNull = false, max = 16, min = 1, errMsg = "密码1到16位")
    private String Password;

    @ExcelField(title = "手机号")
    @ExcelImportCheck(isNull = false, max = 11, min = 6, errMsg = "手机号6位11位")
    private String mobile;

    @ExcelField(title = "邮件")
    @ExcelImportCheck(isNull = false, max = 50, min = 5, errMsg = "邮箱5-50位")
    private String email;

    @ExcelField(title = "状态", customDictValue = {"正常", "禁用"})
    @ExcelImportCheck(isNull = false)
    private String status;

}
