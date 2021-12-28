package com.sofn.core.constant;

/**
 * Created by: dong4j.
 * Date: 2016-11-09.
 * Time: 13:28.
 * Description: 字典枚举定义
 */
public enum DictType {
    // 公共字典
    ORGTYPE("机构类别", "OrgType"),
    ORGLEVEL("机构级别", "OrgLevel"),
    INDUSTRYTYPE("行业分类", "IndustryType"),
    POSTTYPE("职务", "PostType"),
    POSTLEVEL("职级", "PostLevel"),
    YEAR("年份","year"),
    TEMPTYPE("临时主体类型","linshizhutileixing"),//新增公共临时主体类型常量(2018年2月26日14:38:56)
    DOCTYPE("文档类别", "docType"),
    PLATFORMTYPE("平台类别", "platform_type"),
    FILECATEGORY("文件分类", "filecategory"),

    // 追溯字典(tts)
    ORGMODE("组织形式", "OrgMode"),
    SUBJTYPE("主体类型", "SubjType"),
    SUBJPROPERTY("主体属性", "SubjProperty"),
    UNITTYPE("数量单位", "UnitType"),
    QTMODE("质检情况", "QtMode"),
    PRODUCTFROM("产品来源", "ProductFrom"),
    ISSUETYPE("问题类型","IssueType"),
    PLANTINGBASEUNIT("种植业基地单位", "PlantingBaseUnit"),
    STOCKBREEDINGBASEUNIT("畜牧业基地单位", "StockbreedingBaseUnit"),
    FISHERYBASEUNIT("渔业基地单位", "FisheryBaseUnit"),
    PLANTINGAMOUNTUNIT("种植业数量单位", "PlantingAmountUnit"),
    STOCKBREEDINGAMOUNTUNIT("畜牧业数量单位", "StockbreedingAmountUnit"),
    FISHERYAMOUNTUNIT("渔业数量单位", "FisheryAmountUnit"),


    // 监管字典(asms)
    MONITORMODEL("监测模型","MonitorModel"),
    PATROLSCOPE("巡查范围","PatrolScope"),
    PATROLSUBJTYPE("巡查主体类型","PatrolSubjType"),
    PATROLRESULT("巡查结果","PatrolResult"),
    PATROLPERIOD("巡查季度","PatrolPeriod"),
    ORGNATURE("机构性质","OrgNature"),
    JUDGESTANDARD("判定标准","JudgeStandard"),
    COMMONOPINION("常用意见","CommonOpinion"),


    // 检测字典
    QUALIFICATIONCERTIFICATE("资质证书","QualificationCertificate"),
    MONITORTYPE("监测类型","MonitorType"),
    DETECITEM("检测项目","DetecItem"),
    SAMPLETACHE("抽样环节","SampleTache"),
    LEADUNIT("牵头单位","LeadUnit"),
    DETECSTANDARD("检测标准","DetecStandard"),
    SAMPLEMETHOD("抽样方法","SampleMethod"),

    // 执法字典(ales)
    PENALTYSOURCE("行政处罚来源","PenaltySource"),

    // 监测字典
    MONITORBATCH("监测批次","MonitorBatch"),
    MONITORKIND("监测种类","MonitorKind");

    private String name;
    private String code;

    DictType(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
