package com.sofn.ahhrm.vo.exportTemplate;

import com.alibaba.fastjson.annotation.JSONField;
import com.sofn.ahhrm.enums.ProcessEnum;
import com.sofn.ahhrm.model.Baseinfo;
import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.annotation.ExcelSheetInfo;
import com.sofn.common.utils.DateUtils;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.Objects;

@Data
@ExcelSheetInfo(title = "地方畜禽遗传资源基础信息", sheetName = "地方畜禽遗传资源基础信息")
public class ExportBaseinfoBean {

    @ExcelField(title = "监测点名称")
    private String pointName;
    @ExcelField(title = "监测点位置")
    private String addr;
    @ExcelField(title = "所属类型")
    private String type;
    @ExcelField(title = "固定电话、手机")
    private String tel;
    @ExcelField(title = "电子邮箱")
    private String email;
    @ExcelField(title = "监测人")
    private String monitor;
    @ExcelField(title = "监测时间")
    private String monitoringTime;


    /**
     * 实体类转VO类
     */
    public static ExportBaseinfoBean entity2Export(Baseinfo entity) {
        ExportBaseinfoBean vo = null;
        if (!Objects.isNull(entity)) {
            vo = new ExportBaseinfoBean();
            BeanUtils.copyProperties(entity, vo);
            vo.setAddr(entity.getProvinceName()+entity.getCityName()+entity.getCountyName());
            vo.setMonitoringTime(DateUtils.format(entity.getMonitoringTime(), "yyyy-MM-dd HH:mm"));
        }
        return vo;
    }
}
