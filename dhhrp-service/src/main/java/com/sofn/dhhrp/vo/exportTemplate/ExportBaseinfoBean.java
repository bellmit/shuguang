package com.sofn.dhhrp.vo.exportTemplate;

import com.alibaba.fastjson.annotation.JSONField;
import com.sofn.dhhrp.model.Baseinfo;
import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.annotation.ExcelSheetInfo;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.Objects;

@Data
@ExcelSheetInfo(title = "地方畜禽遗传资源原生境基础信息", sheetName = "地方畜禽遗传资源原生境基础信息")
public class ExportBaseinfoBean {

    @ExcelField(title = "监测点名称")
    private String pointName;
    @ExcelField(title = "监测年份")
    private String year;
    @ExcelField(title = "温度（℃）")
    private Double temperature;
    @ExcelField(title = "物种名称")
    private String varietyName;
    @ExcelField(title = "群体数量（个）")
    private Integer amount;
    @ExcelField(title = "养殖户数量（个）")
    private Integer breeder;
    @ExcelField(title = "饲草料种植面积（㎡）")
    private Double plant;
    @ExcelField(title = "监测人")
    private String monitor;
//    @ExcelField(title = "监测时间")
//    @JSONField(format = "yyyy-MM-dd")
//    private Date monitoringTime;


    /**
     * 实体类转VO类
     */
    public static ExportBaseinfoBean entity2Export(Baseinfo entity) {
        ExportBaseinfoBean vo = null;
        if (!Objects.isNull(entity)) {
            vo = new ExportBaseinfoBean();
            BeanUtils.copyProperties(entity, vo);
        }
        return vo;
    }
}
