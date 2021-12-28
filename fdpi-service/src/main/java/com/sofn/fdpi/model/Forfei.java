package com.sofn.fdpi.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.common.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@TableName("FORFEI")
@ApiModel(value = "罚没model")
@Data
public class Forfei extends BaseModel<Forfei> {
    /**
     * 罚没ID
     */
    @ApiModelProperty(value = "罚没ID")
    private String id;

    /**
     * 企业ID
     */
    @ApiModelProperty(value = "企业ID")
    private String compId;

    @ApiModelProperty(value = "标识编码")
    private String code;

    /**
     * 罚没类型
     */
    @ApiModelProperty(value = "罚没类型")
    private String ffType;

    /**
     * 时间
     */
    @ApiModelProperty(value = "时间")
    private Date ffDate;

    /**
     * 地点
     */
    @ApiModelProperty(value = "地点")
    private String ffLocal;

    /**
     * 物种名
     */
    @ApiModelProperty(value = "物种名")
    private String speName;

    /**
     * 性别
     */
    @ApiModelProperty(value = "性别")
    private String sex;

    /**
     * 数量
     */
    @ApiModelProperty(value = "数量")
    private Long speNum;

    /**
     * 体况/规格
     */
    @ApiModelProperty(value = "体况/规格")
    private String speCon;

    /**
     * 救助场所
     */
    @ApiModelProperty(value = "救助场所")
    private String resSites;



    /**
     * 处置意见
     */
    @ApiModelProperty(value = "处置意见")
    private String ffIdea;

    /**
     * 处置方案
     */
    @ApiModelProperty(value = "处置方案")
    private String ffMeth;

    /**
     * 处置单位
     */
    @ApiModelProperty(value = "处置单位")
    private String ffUnit;

    /**
     * 处置时间
     */
    @ApiModelProperty(value = "处置时间")
    private Date disDate;

    /**
     * 制品型号
     */
    @ApiModelProperty(value = "制品型号")
    private String proModel;

    /**
     * 包装方式
     */
    @ApiModelProperty(value = "包装方式")
    private String packType;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private int status;

    /**
     * 省
     */
    @ApiModelProperty(value = "省")
    private String province;

    /**
     * 市
     */
    @ApiModelProperty(value = "市")
    private String city;

    /**
     * 区
     */
    @ApiModelProperty(value = "区")
    private String area;
    @ApiModelProperty(value = "当前组织机构id")
    private String orgId;
    @TableField(exist = false)
    @ApiModelProperty(value = "文件表单列表")
    private List<FileManage> files;

}

