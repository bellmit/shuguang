package com.sofn.fdpi.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.common.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 标识类
 *
 * @Author yumao
 * @Date 2019/12/26 17:29
 **/
@Data
@TableName("SIGNBOARD")
public class Signboard extends BaseModel<Signboard> {

    /**
     * 主键
     */
    private String id;
    /**
     * 标识编码
     */
    private String code;
    /**
     * 标识状态 1未激活2在养3已注销
     */
    @ApiModelProperty("1未激活2在养3已注销4销售")
    private String status;
    /**
     * 物种ID
     */
    private String speId;
    /**
     * 物种名称
     */
    @TableField(exist = false)
    private String speName;
    /**
     * 拉丁名称
     */
    @TableField(exist = false)
    private String latinName;
    /**
     * CITES级别
     */
    @TableField(exist = false)
    private String cites;
    /**
     * 中国保护级别
     */
    @TableField(exist = false)
    private String proLevel;
    /**
     * 企业ID
     */
    private String compId;
    /**
     * 企业名称
     */
    @TableField(exist = false)
    private String compName;
    /**
     * 企业类型
     */
    @TableField(exist = false)
    private String compType;
    /**
     * 区域名称
     */
    @TableField(exist = false)
    private String areaName;
    /**
     * 申请单号
     */
    @TableField(exist = false)
    private String applyCode;
    /**
     * 养殖地点
     */
    @TableField(exist = false)
    private String contactAddress;
    /**
     * 物种来源/种苗来源
     */
    private String speSource;
    /**
     * 物种利用类型
     */
    private String speUtilizeType;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 有无杂交
     */
    private String hybridize;
    /**
     * 出生日期
     */
    private Date brTime;
    /**
     * 出生类型
     */
    private String brType;
    /**
     * 出生地
     */
    private String brLocal;
    /**
     * 父亲编码
     */
    private String parCode;
    /**
     * 母亲编码
     */
    private String mumCode;
    /**
     * 来源地
     */
    private String originLocal;
    /**
     * 性别
     */
    private String sex;
    /**
     * 育幼类型
     */
    private String rearType;
    /**
     * 体长
     */
    private Double height;
    /**
     * 体重
     */
    private Double weight;
    /**
     * 年龄
     */
    private Double age;
    /**
     * 获得日期
     */
    private Date gainDate;
    /**
     * 获得方式
     */
    private String gainType;
    /**
     * 获得目的
     */
    private String aim;
    /**
     * 妊娠次数
     */
    private Integer frPre;
    /**
     * 腋下围
     */
    private String armCir;
    /**
     * 是否建有谱系
     */
    private String esGen;
    /**
     * 是否进行谱系写死，否：0是：1
     */
    @TableField(exist = false)
    private String pedigree;
    /**
     * 是否有电子标识
     */
    private String elIdent;
    /**
     * 标识位置
     */
    private String identLocal;
    /**
     * 治疗记录
     */
    private String cureRec;
    /**
     * 谱系信息是否完善  1 是 0 否
     */
    private String isPedigree;
    /**
     * 打印状态 1 已打印 0 未打印
     */
    private String printStatus;
    /**
     * 转移状态  Y 转移中 N 转移完成
     */
    private String transferStatus;
    /**
     * 打印id
     */
    private String printId;
    /**
     * 拟销售省份
     */
    private String saleProvince;
    /**
     * 引进方式
     */
    private String importType;
    /**
     * 引进时间
     */
    private Date importTime;
    /**
     * CITES级别
     */
    private String citesLevel;
    /**
     * 中国保护等级
     */
    private String protectLevel;
    /**
     * 物种/产品介绍
     */
    private String introduce;
    /**
     * 物种介绍
     */
    @TableField(exist = false)
    private String intro;
    /**
     * 生境及习性
     */
    @TableField(exist = false)
    private String habit;
    /**
     * 背甲长
     */
    private Double dorsalLength;
    /**
     * 背甲宽
     */
    private Double dorsalWide;
    /**
     * 标识产品
     */
    private String signProduct;
    /**
     * 产品名
     */
    private String product;
    /***
     * 制品大鲵含量
     */
    private String andriasContent;
    /**
     * 标识申请数量
     */
    private Integer applyNum;
    /**
     * 生产批号
     */
    private String batchNumber;
    /**
     * 标识人
     */
    private String idenPerson;
    /**
     * 标识时间
     */
    private Date idenTime;
    /**
     * 标识类型
     */
    private String signboardType;
    /**
     * 有效期
     */
    private Date effective;
    /**
     * 创建时间戳
     */
    private Long crtTime;
    /**
     * 国外芯片
     */
    private String chipAbroad;
    /**
     * 国内芯片
     */
    private String chipDomestic;
    /**
     * 国外芯片(体外)
     */
    private String chipAbroadOut;
    /**
     * 国内芯片(体外)
     */
    private String chipDomesticOut;
    /**
     * 国外芯片部位
     */
    private String chipAbroadPosition;
    /**
     * 国内芯片部位
     */
    private String chipDomesticPosition;
    /**
     * 旧标识编码
     */
    private String oldCode;
    /**
     * 标识申请ID
     */
    private String applyId;
    /**
     * 备注
     */
    private String remark;
    /**
     * 产品类型
     */
    private String productType;



}
