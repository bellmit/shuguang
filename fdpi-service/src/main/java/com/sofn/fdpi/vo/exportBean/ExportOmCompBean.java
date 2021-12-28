package com.sofn.fdpi.vo.exportBean;

import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.annotation.ExcelSheetInfo;
import lombok.Data;


@Data
@ExcelSheetInfo(title = "企业查询统计分析", sheetName = "企业查询统计分析")
public class ExportOmCompBean {

    @ExcelField(title = "用户名")
    private String username;
    @ExcelField(title = "单位名称")
    private String compName;
    @ExcelField(title = "企业类型")
    private String compType;
    @ExcelField(title = "所在区域")
    private String provinceName;
    @ExcelField(title = "法人代表")
    private String legalPerson;
    @ExcelField(title = "联系人")
    private String linkman;
    @ExcelField(title = "联系电话")
    private String phoneNumber;
    @ExcelField(title = "通讯地址")
    private String contactAddress;
    @ExcelField(title = "邮政编码")
    private String postal;
    @ExcelField(title = "电子邮箱")
    private String email;
    @ExcelField(title = "驯养繁育许可证编号")
    private String tameBreedClience;
    @ExcelField(title = "准许驯养繁殖情况鳗苗(吨）")
    private Double tameAllowTon;
    @ExcelField(title = "经营利用许可证编号")
    private String manageLicence;
    @ExcelField(title = "准许经营利用情况(吨)")
    private Double allowManageTon;


}
