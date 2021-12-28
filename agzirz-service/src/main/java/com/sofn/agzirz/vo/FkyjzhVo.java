package com.sofn.agzirz.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.sofn.agzirz.model.FKYJZH;
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
public class FkyjzhVo extends Model<FkyjzhVo> {

    @ApiModelProperty(name = "fkyjzhNo", value = "主键")
    private String fkyjzhNo;

    @JSONField(format = "yyyy-MM-dd")
    @ApiModelProperty(name = "eventTime", value = "发生时间")
    private Date eventTime;

    @ApiModelProperty(name = "eventLocation", value = "发生地点")
    private String eventLocation;

    @ApiModelProperty(name = "eventCause", value = "事件原因")
    private String eventCause;

    @ApiModelProperty(name = "eventContent", value = "事件内容")
    private String eventContent;

    @ApiModelProperty(name = "eventImgs", value = "图片")
    private String eventImgs;

    @ApiModelProperty(name = "eventAffect", value = "事件影响")
    private String eventAffect;

    @ApiModelProperty(name = "importance", value = "重要程度")
    private String importance;

    @ApiModelProperty(name = "emerOrgani", value = "应急机构")
    private String emerOrgani;

    @ApiModelProperty(name = "emerOrgani", value = "应急机构名称")
    private String emerOrganiName;

    @ApiModelProperty(name = "reportUser", value = "上报人")
    private String reportUser;

    @ApiModelProperty(name = "reportUserName", value = "上报人名字")
    private String reportUserName;

    @JSONField(format = "yyyy-MM-dd")
    @ApiModelProperty(name = "reportTime", value = "上报时间")
    private Date reportTime;

    @ApiModelProperty(value = "有效状态，用于逻辑删除")
    private String enableStatus;

    @ApiModelProperty(name = "imgsUrl", value = "图片地址map")
    private List<Map<String, Object>> imgsUrl;

    @ApiModelProperty(name = "eventLocationName", value = "发生地点名称")
    private String eventLocationName;

    public FkyjzhVo(){}

    /**
     * 将vo转换为po对象
     */
    public static FKYJZH getFKYJZH(FkyjzhVo fkyjzhVo){
        FKYJZH fkyjzh = new FKYJZH();
        BeanUtils.copyProperties(fkyjzhVo,fkyjzh);
        return fkyjzh;
    }


    /**
     * 将po转换为vo对象
     */
    public static FkyjzhVo getFkyjzhVo(FKYJZH fkyjzh){
        FkyjzhVo fkyjzhVo = new FkyjzhVo();
        BeanUtils.copyProperties(fkyjzh,fkyjzhVo);
        return fkyjzhVo;

    }

}
