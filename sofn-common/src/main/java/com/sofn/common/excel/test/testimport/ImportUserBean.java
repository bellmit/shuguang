package com.sofn.common.excel.test.testimport;

import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.annotation.ExcelFields;
import com.sofn.common.excel.annotation.ExcelImportCheck;
import com.sofn.common.excel.annotation.ExcelSheetInfo;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 导入Excel测试类
 *
 * 注意： 1.如果类中属性个数和EXCEL中的个数不匹配时，需要设置column 属性   从0开始
 * Created by heyongjie on 2019/6/12 15:21
 */
@Data
@ExcelSheetInfo(sheetName = "用户Sheet")
public class ImportUserBean {

    @ExcelField(title = "用户名")
    @ExcelImportCheck(isNull =  false,max = 20,min = 1,errMsg = "用户名在1到20之间")
    private String username;

    @ExcelField(title = "密码",type = ExcelField.Type.IMPORT)
    @ExcelImportCheck(isNull =  false)
    private String password;

    @ExcelField(title = "性别",dictType = "sex" )
    private String sex;

    @ExcelField(title = "年龄",dataFormat = "0")
    @ExcelImportCheck(max = 150,min = 0,errMsg = "年龄最大150岁")
    private Integer age;

    @ExcelField(title = "出生日期",dataFormat = "yyyy-MM")
    @ExcelImportCheck(min = 1700,max = 9999,errMsg = "日期不合法,合法日期为1700-9999年")
    private Date birthDay;

    @ExcelField(title = "余额",dataFormat = "0.00")
    @ExcelImportCheck(max = 999999999,errMsg = "余额最大值999999999")
    private Double balance;

    @ExcelField(title = "总价",dataFormat = "0.000")
    @ExcelImportCheck(max = 999999999,errMsg = "总价最大值999999999")
    private BigDecimal totalPrice;

    @ExcelFields(clazz = ImportUserAreaBan.class)
    private ImportUserAreaBan importUserAreaBan;



}
