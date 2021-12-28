package com.sofn.fdpi.vo;

import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.annotation.ExcelImportCheck;
import com.sofn.common.excel.annotation.ExcelSheetInfo;
import com.sofn.fdpi.enums.SpeCategoryEnum;
import com.sofn.fdpi.model.Spe;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-03-04 15:51
 */
@Data
@ExcelSheetInfo(sheetName = "重点保护物种种类管理信息导入模板")
public class SpeExcel {

    @ExcelField(title = "序号")
//    @ExcelImportCheck(isNull =  false)
    private String id;
    @ExcelField(title = "物种编号")
    private String speCode;
    @ExcelField(title = "类别")
    private String speType;
    @ExcelField(title = "物种学名")
    @ExcelImportCheck(isNull = false, max = 20, errMsg = "物种学名不能为空且长度不超过20")
    private String speName;
    @ExcelImportCheck(max = 40, errMsg = "拉丁名长度不超过40")
    @ExcelField(title = "拉丁名")
    private String latinName;
    @ExcelImportCheck(max = 10, errMsg = "俗名长度不超过10")
    @ExcelField(title = "俗名")
    private String localName;
    @ExcelImportCheck(max = 10, errMsg = "商品名长度不超过10")
    @ExcelField(title = "商品名")
    private String tradeName;
    @ExcelImportCheck(max = 500, errMsg = "物种简介长度不超过10")
    @ExcelField(title = "物种简介")
    private String intro;
    @ExcelImportCheck(max = 500, errMsg = "生境及习性长度不超过100")
    @ExcelField(title = "生境及习性")
    private String habit;
    @ExcelImportCheck(max = 500, errMsg = "地理分布不超过100")
    @ExcelField(title = "地理分布")
    private String distribution;
    @ExcelImportCheck(isNull = false, errMsg = "是否使用标识必选")
    @ExcelField(title = "是否使用标识", dictType = "identify")
    private String identify;
    @ExcelImportCheck(isNull = false, errMsg = "是否进行谱系管理必选")
    @ExcelField(title = "是否进行谱系管理", dictType = "pedigree")
    private String pedigree;
    @ExcelImportCheck(isNull = false, errMsg = "中国保护级别必选")
    @ExcelField(title = "中国保护级别", dictType = "proLevel")
    private String proLevel;
    @ExcelImportCheck(isNull = false, errMsg = "CITES级别必选")
    @ExcelField(title = "CITES级别", dictType = "cites")
    private String cites;

    /**
     * 实体类转VO类
     */
    public static SpeExcel entity2Excel(Spe entity) {
        SpeExcel excel = new SpeExcel();
        BeanUtils.copyProperties(entity, excel);
        excel.setSpeType(SpeCategoryEnum.getVal(entity.getSpeType()));
        return excel;
    }
}
