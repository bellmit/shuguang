package com.sofn.agzirz.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.sofn.agzirz.model.FKYJZH;
import com.sofn.common.excel.annotation.ExcelField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;


@ApiModel
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class FkyjzhExcelVo {

    @ExcelField(title = "发生时间")
    @JSONField(format = "yyyy-MM-dd")
    @ApiModelProperty(name = "eventTime", value = "发生时间")
    private Date eventTime;

    @ExcelField(title = "发生地点")
    @ApiModelProperty(name = "eventLocation", value = "发生地点")
    private String eventLocation;

    @ExcelField(title = "事件原因")
    @ApiModelProperty(name = "eventCause", value = "事件原因")
    private String eventCause;

    @ExcelField(title = "事件内容")
    @ApiModelProperty(name = "eventContent", value = "事件内容")
    private String eventContent;

    @ExcelField(title = "事件影响")
    @ApiModelProperty(name = "eventAffect", value = "事件影响")
    private String eventAffect;

    @ExcelField(title = "重要程度")
    @ApiModelProperty(name = "importance", value = "重要程度")
    private String importance;

    @ExcelField(title = "应急机构")
    @ApiModelProperty(name = "emerOrgani", value = "应急机构名称")
    private String emerOrganiName;

    @ExcelField(title = "上报人")
    @ApiModelProperty(name = "reportUser", value = "上报人")
    private String reportUser;

    @ExcelField(title = "上报时间")
    @JSONField(format = "yyyy-MM-dd")
    @ApiModelProperty(name = "reportTime", value = "上报时间")
    private Date reportTime;

    public FkyjzhExcelVo(){}

    public static FkyjzhExcelVo getFkyjzhVo(FKYJZH fkyjzh){
        FkyjzhExcelVo fkyjzhExcelVo = new FkyjzhExcelVo();
        BeanUtils.copyProperties(fkyjzh,fkyjzhExcelVo);
        return fkyjzhExcelVo;

    }

}
