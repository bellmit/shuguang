package com.sofn.fdpi.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * @Deacription
 * @Author yumao
 * @Date 2019/12/26 17:23
 **/
@Data
@ApiModel(value = "标识表单对象")
public class SignboardForm {

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "标识编码")
    private String code;

    @ApiModelProperty(value = "物种ID")
    private String speId;

    @ApiModelProperty(value = "企业ID")
    private String compId;


    @ApiModelProperty(value = "物种来源/种苗来源")
    private String speSource;

    //    @NotBlank(message = "昵称不能为空")
    @ApiModelProperty(value = "昵称", example = "昵称")
    private String nickname;

    //    @Size(max = 1, message = "有无杂交值过长 1 有 0 无")
//    @NotBlank(message = "有无杂交不能为空")
    @ApiModelProperty(value = "有无杂交", example = "1")
    private String hybridize;

    //    @Past(message = "出生日期不能大于当前日期")
    @ApiModelProperty(value = "出生日期")
    private Date brTime;

    //    @NotBlank(message = "出生类型不能为空")
    @ApiModelProperty(value = "出生类型")
    private String brType;

    //    @NotBlank(message = "出生地不能为空")
//    @Size(max = 30, message = "出生地不能超过30")
    @ApiModelProperty(value = "出生地", example = "四川成都")
    private String brLocal;

    //    @NotBlank(message = "父亲编码不能为空")
    @ApiModelProperty(value = "父亲编码")
    private String parCode;

    //    @NotBlank(message = "母亲编码不能为空")
    @ApiModelProperty(value = "母亲编码")
    private String mumCode;

    //    @NotBlank(message = "来源地不能为空")
//    @Size(max = 30, message = "来源地不能超过30")
    @ApiModelProperty(value = "来源地", example = "四川成都")
    private String originLocal;

    //    @Size(max = 1, message = "性别值过长 1 雄 2 雌 0 其它")
//    @NotBlank(message = "性别不能为空")
    @ApiModelProperty(value = "性别(1 雄 2 雌 0 其它)", example = "1")
    private String sex;

    //    @NotBlank(message = "育幼类型不能为空")
    @ApiModelProperty(value = "育幼类型")
    private String rearType;

    //    @Digits(integer = 6, fraction = 2, message = "体长" + "{javax.validation.constraints.Digits.message}")
    @ApiModelProperty(value = "体长", example = "12")
    private Double height;

    //    @Digits(integer = 6, fraction = 2, message = "体重" + "{javax.validation.constraints.Digits.message}")
    @ApiModelProperty(value = "体重", example = "25")
    private Double weight;

    //    @Digits(integer = 3, fraction = 1, message = "年龄" + "{javax.validation.constraints.Digits.message}")
    @ApiModelProperty(value = "年龄", example = "3")
    private Double age;

    //    @Past(message = "获得时间不能大于当前时间")
    @ApiModelProperty(value = "获得日期")
    private Date gainDate;

    //    @NotBlank(message = "获得方式不能为空")
//    @Size(max = 10, message = "获得方式不能超过10")
    @ApiModelProperty(value = "获得方式", example = "获得方式")
    private String gainType;

    //    @NotBlank(message = "获得目的不能为空")
//    @Size(max = 10, message = "获得目的不能超过10")
    @ApiModelProperty(value = "获得目的", example = "获得目的")
    private String aim;

    //@Digits(integer = 3, fraction = 0, message = "妊娠次数" + "{javax.validation.constraints.Digits.message}")
    @ApiModelProperty(value = "妊娠次数", example = "1")
    private Integer frPre;

    //    @NotBlank(message = "腋下围不能为空")
    @ApiModelProperty(value = "腋下围", example = "腋下围")
    private String armCir;

    //    @Size(max = 1, message = "是否建有谱系值过长 1 是 0 否")
//    @NotBlank(message = "是否建有谱系不能为空")
//    @ApiModelProperty(value = "是否建有谱系(1 是 0 否)", example = "1")
//    private String esGen;

    //    @Size(max = 1, message = "是否有电子标识值过长 1 是 0 否")
//    @NotBlank(message = "是否有电子标识不能为空")
    @ApiModelProperty(value = "是否有电子标识(1 是 0 否)", example = "1")
    private String elIdent;

    //    @Size(max = 1, message = "物种利用类型值过长 1 驯养展演 2 人工繁育")
//    @NotBlank(message = "物种利用类型不能为空")
    @ApiModelProperty(value = "物种利用类型(1 驯养展演 2 人工繁育)", example = "1")
    private String speUtilizeType;

    //    @NotBlank(message = "标识位置不能为空")
//    @Size(max = 30, message = "标识位置不能超过30")
    @ApiModelProperty(value = "标识位置", example = "标识位置")
    private String identLocal;

    //    @NotBlank(message = "治疗记录不能为空")
    @Size(max = 100, message = "治疗记录不能超过100")
    @ApiModelProperty(value = "治疗记录", example = "无")
    private String cureRec;

    @ApiModelProperty(value = "文件表单列表")
    private List<FileManageForm> files;

    @ApiModelProperty(value = "拟销售省份")
    private String saleProvince;

    @ApiModelProperty(value = "标识类型")
    private String signboardType;

//    @ApiModelProperty(value = "引进方式")
//    private String importType;

    @ApiModelProperty(value = "引进时间")
    private Date importTime;

//    @ApiModelProperty(value = "CITES级别")
//    private String citesLevel;
//
//    @ApiModelProperty(value = "中国保护等级")
//    private String protectLevel;
//
    @ApiModelProperty(value = "物种/产品介绍")
    private String introduce;

//    @ApiModelProperty(value = "生境及习性")
//    private String habit;

    @ApiModelProperty(value = "背甲长")
    private Double dorsalLength;

    @ApiModelProperty(value = "背甲宽")
    private Double dorsalWide;

    @ApiModelProperty(value = "标识产品")
    private String signProduct;

    @ApiModelProperty(value = "产品名")
    private String product;

    @ApiModelProperty(value = "制品大鲵含量")
    private String andriasContent;

    @ApiModelProperty(value = "标识申请数量")
    private Integer applyNum;

    @ApiModelProperty(value = "生产批号")
    private String batchNumber;

    @ApiModelProperty(value = "标识人")
    private String idenPerson;

    @ApiModelProperty(value = "标识时间")
    private Date idenTime;

    @ApiModelProperty(value = "有效期")
    private Date effective;

    @ApiModelProperty(value = "国外芯片")
    private String chipAbroad;

    @ApiModelProperty(value = "国内芯片")
    private String chipDomestic;

    @ApiModelProperty(value = "国外芯片(体外)")
    private String chipAbroadOut;

    @ApiModelProperty(value = "国内芯片(体外)")
    private String chipDomesticOut;

    @ApiModelProperty(value = "国外芯片部位")
    private String chipAbroadPosition;

    @ApiModelProperty(value = "国内芯片部位")
    private String chipDomesticPosition;

    @ApiModelProperty(value = "旧标识编码")
    private String oldCode;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "产品类型")
    private String productType;
}
