package com.sofn.agpjyz.vo.exportBean;

import com.alibaba.fastjson.annotation.JSONField;
import com.sofn.agpjyz.model.PlantUtilization;
import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.annotation.ExcelSheetInfo;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.Objects;

@Data
@ExcelSheetInfo(title = "农业野生植物利用", sheetName = "农业野生植物利用")
public class ExportPuBean {

    @ExcelField(title = "所在地区")
    private String addr;
    @ExcelField(title = "物种名称")
    private String specValue;
    @ExcelField(title = "拉丁学名")
    private String latin;
    @ExcelField(title = "产业利用")
    private String industrialValue;
    @ExcelField(title = "利用单位")
    private String utilizationUnit;
    @ExcelField(title = "用途")
    private String purposeName;
    @ExcelField(title = "上报人")
    private String reportPerson;
    @ExcelField(title = "上报时间")
    @JSONField(format = "yyyy-MM-dd")
    private Date reportTime;


    /**
     * 实体类转BEAN
     */
    public static ExportPuBean entity2Bean(PlantUtilization entity) {
        ExportPuBean bean = null;
        if (!Objects.isNull(entity)) {
            bean = new ExportPuBean();
            BeanUtils.copyProperties(entity, bean);
            String provinceName = entity.getProvinceName();
            String cityName = entity.getCityName();
            String countyName = entity.getCountyName();
            StringBuilder sb = new StringBuilder();
            sb.append(provinceName == null ? "" : provinceName)
                    .append(cityName == null ? "" : cityName)
                    .append(countyName == null ? "" : countyName);
            bean.setAddr(sb.toString());
        }
        return bean;
    }
}
