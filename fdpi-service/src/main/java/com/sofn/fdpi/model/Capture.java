package com.sofn.fdpi.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.common.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 特许捕抓证
 */
@ApiModel(value = "特许捕获证对象")
@TableName("Capture")
@Data
public class Capture extends BaseModel<Capture> {
    @ApiModelProperty(value = "主键")
    @TableId(value = "ID", type = IdType.UUID)
    private  String id;
//持证单位
    @ApiModelProperty(value = "持证单位")
    private String capUnit;
//证书类型
    @ApiModelProperty(value = "证书类型")
    private String papersType;
//证书编号
    @ApiModelProperty(value = "证书编号")
    private String papersNumber;
//批准文号
    @ApiModelProperty(value = "批准文号")
    private String appNum;
//有效日期开始
    @ApiModelProperty(value = "有效日期开始")
    @JSONField(format = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataStart;
//有效日期截至
    @ApiModelProperty(value = "有效日期截至")
    @JSONField(format = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataClos;
//捕猎地点
    @ApiModelProperty(value = "具体捕猎地点")
    private String capLocal;
//捕猎方式
    @ApiModelProperty(value = "捕猎方式")
    private String capWay;
//物种名
    @ApiModelProperty(value = "物种名")
    private String speName;
//保护级别
    @ApiModelProperty(value = "保护级别")
    private String proLevel;
//捕获数量
    @ApiModelProperty(value = "捕获数量")
    private Integer capNum;
//发证机关
    @ApiModelProperty(value = "发证机关")
    private String issueUnit;
//发证日期
    @ApiModelProperty(value = "发证日期")
    @JSONField(format = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date issueDate;
//状态
    @ApiModelProperty(value = "状态")
    private String capStatus;

//   捕获事由
    @ApiModelProperty(value = "捕获事由")
    private String cause;

    //省
    @ApiModelProperty(value = "省")
    private String province;
    //市
    @ApiModelProperty(value = "市")
    private String city;
    //区
    @ApiModelProperty(value = "区")
    private String area;
    @ApiModelProperty("许可证正本：是否打印，0否 1：是")
    private  String isPrint;
    @TableField(exist = false)
    @ApiModelProperty(value = "能否打印")
    private Boolean canPrint;
    @TableField(exist = false)
    @ApiModelProperty(value = "能否操作")
    private Boolean canHandle;
}

