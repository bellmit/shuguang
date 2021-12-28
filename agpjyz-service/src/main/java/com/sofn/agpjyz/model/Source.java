package com.sofn.agpjyz.model;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.common.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 资源调查类
 *
 * @Author yumao
 * @Date 2020/3/9 16:13
 **/
@Data
@TableName("SOURCE")
public class Source extends BaseModel<Source> {

    /**
     * 调查日期
     */
    private Date survey;
    /**
     * 调查人
     */
    private String investigator;
    /**
     * 联系电话
     */
    private String phone;
    /**
     * 物种名称ID
     */
    private String specId;
    /**
     * 物种名称
     */
    private String specValue;
    /**
     * 拉丁学名
     */
    private String latin;
    /**
     * 俗名
     */
    private String commonName;
    /**
     * 省
     */
    private String province;
    /**
     * 省名
     */
    private String provinceName;
    /**
     * 市
     */
    private String city;
    /**
     * 市名
     */
    private String cityName;
    /**
     * 县
     */
//    @TableField(strategy = FieldStrategy.IGNORED)
    private String county;
    /**
     * 县名
     */
//    @TableField(strategy = FieldStrategy.IGNORED)
    private String countyName;
    /**
     * 海拔
     */
    private String altitude;
    /**
     * 分布面积
     */
    private String distribution;
    /**
     * 种群数量
     */
    private String amount;
    /**
     * 形态特征
     */
    private String features;
    /**
     * 生物学特性
     */
    private String characteristic;
    /**
     * 濒危状况ID
     */
    private String endangeredId;
    /**
     * 濒危状况名称
     */
    private String endangeredValue;
    /**
     * 威胁因素
     */
    private String threaten;
    /**
     * 年平均气温
     */
    private String temperature;
    /**
     * 大于等于10℃年积温(℃)
     */
    private String greater;
    /**
     * 年平均降水量
     */
    private String precipitation;
    /**
     * 年平均日照时数
     */
    private String sunshine;
    /**
     * 年蒸发量
     */
    private String evaporation;
    /**
     * 植被类型ID
     */
    private String vegetationId;
    /**
     * 植被类型名称
     */
    private String vegetationValue;
    /**
     * 植被覆盖率
     */
    private String vegetationCoverage;
    /**
     * 土壤类型ID
     */
    private String soilId;
    /**
     * 土壤类型名称
     */
    private String soilValue;
    /**
     * 土壤肥力
     */
    private String soilFertility;
    /**
     * 保护与利用状况
     */
    private String protectionUtilization;
    /**
     * 评价和建议
     */
    private String suggest;
    /**
     * 状态 0已保存 1已撤回 2已上报 3市级退回 4市级通过 5省级退回 6省级通过 7总站退回 8总站通过
     */
    private String status;
    /**
     * 是否已审核
     */
    private String auditFlag;
    /**
     * 是否专家填报
     */
    private String expertReport;
}
