package com.sofn.agzirz.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;

@Data
@TableName("SJSJ")
public class SjsjVo {

    @ApiModelProperty(name="sjsjNo",value = "主键")
    private String sjsjNo;

    @ApiModelProperty(name="eventAbtFiles", value = "事件相关文件")
    private String eventAbtFiles;

    @ApiModelProperty(name="disposzalOrgani", value = "处置机构")
    private String disposalOrgani;

    @JSONField(format = "yyyy-MM-dd")
    @ApiModelProperty(name="eventTime", value = "发生时间")
    private Date eventTime;

    @ApiModelProperty(name="eventLocation", value = "发生地点")
    private String eventLocation;

    @ApiModelProperty(name="eventContent", value = "事件内容")
    @Size(max = 500,message = "事件内容字数不能超过500")
    private String eventContent;

    @ApiModelProperty(name="treatmentMeasure", value = "处理措施")
    @Size(max= 300,message = "处理措施字数不能超过300")
    private String treatmentMeasure;

    @ApiModelProperty(name="eventImgs", value = "事件图片")
    private String eventImgs;

    @ApiModelProperty(name="eventVedios", value = "事件视频")
    private String eventVedios;

    @ApiModelProperty(name="eventAffect", value = "事件影响")
    @Size(max= 200,message = "事件影响字数不能超过200")
    private String eventAffect;

    @ApiModelProperty(name="processReport", value = "处理报告")
    private String processReport;

}
