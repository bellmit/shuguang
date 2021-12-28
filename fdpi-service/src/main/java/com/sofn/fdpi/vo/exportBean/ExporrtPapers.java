package com.sofn.fdpi.vo.exportBean;

import lombok.Data;

import java.util.Date;

@Data
public class ExporrtPapers {

    //证书类型
    private String papersType;
    //证书编号
    private String papersNumber;
    //企业名称
    private String compName;
    //企业地址
    private String compAddress;
    //法人
    private String legal;
    //经营方式
    private String modeOperation;
    //销售去向
    private String salesDestination;
    //技术负责人
    private String technicalDirector;
    //人工繁育目的
    private String purpose;
    //有效期结束时间
    private Date dataClos;
    //发证机关
    private String issueUnit;
    //发证日期
    private Date issueDate;
    //物种名
    private String speName;
    //来源地
    private String source;
    //方式
    private String mode;
    //数量
    private Integer amount;
    //物种类型
    private String speType;
    //单位
    private String unit;



















}
