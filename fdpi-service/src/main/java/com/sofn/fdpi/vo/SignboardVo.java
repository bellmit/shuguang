package com.sofn.fdpi.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.sofn.fdpi.enums.*;
import com.sofn.fdpi.model.Signboard;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @Deacription
 * @Author yumao
 * @Date 2019/12/26 17:23
 **/
@Data
@ApiModel(value = "标识VO对象")
public class SignboardVo {

    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "标识编码")
    private String code;
    @ApiModelProperty(value = "标识状态 1 未激活 2 在养 3 已注销")
    private String status;
    @ApiModelProperty(value = "标识状态名称")
    private String statusName;
    @ApiModelProperty(value = "物种ID")
    private String speId;
    @ApiModelProperty(value = "物种名称")
    private String speName;
    @ApiModelProperty(value = "拉丁名称")
    private String latinName;
    @ApiModelProperty(value = "企业ID")
    private String compId;
    @ApiModelProperty(value = "企业名称")
    private String compName;
    @ApiModelProperty(value = "区域名称")
    private String areaName;
    @ApiModelProperty(value = "养殖地点")
    private String contactAddress;
    @ApiModelProperty(value = "物种来源")
    private String speSource;
    @ApiModelProperty(value = "物种来源名称")
    private String speSourceName;
    @ApiModelProperty(value = "物种利用类型")
    private String speUtilizeType;
    @ApiModelProperty(value = "物种利用类型名称")
    private String speUtilizeTypeName;

    @ApiModelProperty(value = "昵称")
    private String nickname;
    @ApiModelProperty(value = "有无杂交")
    private String hybridize;
    @ApiModelProperty(value = "有无杂交名称")
    private String hybridizeName;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "出生日期")
    private Date brTime;
    @ApiModelProperty(value = "出生类型")
    private String brType;
    @ApiModelProperty(value = "出生地")
    private String brLocal;
    @ApiModelProperty(value = "父亲编码")
    private String parCode;
    @ApiModelProperty(value = "母亲编码")
    private String mumCode;
    @ApiModelProperty(value = "来源地")
    private String originLocal;
    @ApiModelProperty(value = "性别")
    private String sex;
    @ApiModelProperty(value = "性别名称")
    private String sexName;
    @ApiModelProperty(value = "育幼类型")
    private String rearType;
    @ApiModelProperty(value = "体长")
    private Double height;
    @ApiModelProperty(value = "体重")
    private Double weight;
    @ApiModelProperty(value = "年龄")
    private Double age;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "获得日期")
    private Date gainDate;
    @ApiModelProperty(value = "获得方式")
    private String gainType;
    @ApiModelProperty(value = "获得目的")
    private String aim;
    @ApiModelProperty(value = "妊娠次数")
    private Integer frPre;
    @ApiModelProperty(value = "腋下围")
    private String armCir;
    @ApiModelProperty(value = "是否进行谱系")
    private String pedigree;
    @ApiModelProperty(value = "是否进行谱系名称")
    private String pedigreeName;
    @ApiModelProperty(value = "是否建有谱系")
    private String esGen;
    @ApiModelProperty(value = "是否建有谱系名称")
    private String esGenName;
    @ApiModelProperty(value = "是否有电子标识")
    private String elIdent;
    @ApiModelProperty(value = "是否有电子标识名称")
    private String elIdentName;
    @ApiModelProperty(value = "标识位置")
    private String identLocal;
    @ApiModelProperty(value = "治疗记录")
    private String cureRec;
    @ApiModelProperty(value = "谱系信息是否完善  1 是 0 否")
    private String isPedigree;
    @ApiModelProperty(value = "谱系信息是否完善名称")
    private String isPedigreeName;
    @ApiModelProperty(value = "打印状态 1已打印 0未打印")
    private String printStatus;
    @ApiModelProperty(value = "打印状态名称")
    private String printStatusName;
    @ApiModelProperty(value = "文件VO对象列表")
    private List<FileManageVo> files;
    @ApiModelProperty(value = "标识类型")
    private String signboardType;
    @ApiModelProperty(value = "拟销售省份")
    private String saleProvince;
    @ApiModelProperty(value = "引进方式")
    private String importType;
    @ApiModelProperty(value = "引进时间")
    private Date importTime;
    @ApiModelProperty(value = "CITES级别")
    private String citesLevel;
    @ApiModelProperty(value = "中国保护等级")
    private String protectLevel;
    @ApiModelProperty(value = "物种/产品介绍")
    private String introduce;
    @ApiModelProperty(value = "物种介绍")
    private String intro;
    @ApiModelProperty(value = "生境及习性")
    private String habit;
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
    @ApiModelProperty(value = "企业类型")
    private String compType;
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
    @ApiModelProperty(value = "申请单号")
    private String applyCode;
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "产品类型")
    private String productType;


    /**
     * 实体类转VO类
     */
    public static SignboardVo Signboard2Vo(Signboard signboard) {
        SignboardVo vo = new SignboardVo();
        BeanUtils.copyProperties(signboard, vo);
        vo.setEsGenName(IsEnum.getVal(vo.getEsGen()));
        vo.setElIdentName(IsEnum.getVal(vo.getElIdent()));
        vo.setHybridizeName(HasEnum.getVal(vo.getHybridize()));
        vo.setSexName(SexTypeEnum.getVal(vo.getSex()));
        vo.setIsPedigreeName(IsEnum.getVal(vo.getIsPedigree()));
        vo.setPrintStatusName(PrintStatusEnum.getVal(vo.getPrintStatus()));
        vo.setSpeSourceName(ChangeReasonEnum.getVal(vo.getSpeSource()));
        vo.setStatusName(SignboardStatusEnum.getVal(vo.getStatus()));
        vo.setPedigreeName(IsPedigreeEnum.getVal(vo.getPedigree()));
        vo.setSpeUtilizeTypeName(SpeciesUtilizeTypeEnum.getVal(vo.getSpeUtilizeType()));
        vo.setCitesLevel(CitesLevelEnum.getVal(signboard.getCites()));
        vo.setProtectLevel(SpeProLevelEnum.getVal(signboard.getProLevel()));
        vo.setWeight(Objects.nonNull(signboard.getWeight()) ?
                Double.parseDouble(String.format("%.2f", signboard.getWeight())) : 0);
        vo.setHeight(Objects.nonNull(signboard.getHeight()) ?
                Double.parseDouble(String.format("%.2f", signboard.getHeight())) : 0);
        vo.setDorsalLength(Objects.nonNull(signboard.getDorsalLength()) ?
                Double.parseDouble(String.format("%.2f", signboard.getDorsalLength())) : 0);
        vo.setDorsalWide(Objects.nonNull(signboard.getDorsalWide()) ?
                Double.parseDouble(String.format("%.2f", signboard.getDorsalWide())) : 0);
        return vo;
    }


}
