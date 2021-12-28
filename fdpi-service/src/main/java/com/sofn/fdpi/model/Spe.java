package com.sofn.fdpi.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.annotation.ExcelSheetInfo;
import com.sofn.common.model.BaseModel;
import com.sofn.fdpi.vo.FileManageForm;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Auther:
 * @Date: 2019/11/28 13:57
 * @Description:
 */

@ExcelSheetInfo(title = "重点保护物种种类管理信息",sheetName = "表一")
@TableName("TB_SPECIES")
@Data
public class Spe extends BaseModel<Spe> {

    @TableId(type = IdType.UUID )
    @ApiModelProperty(name = "id", value = "主键")
    private String id;

    @ApiModelProperty(value = "物种名")
    private String speName;

    @ApiModelProperty(value = "拉丁名")
    private String latinName;
    @ExcelField(title = "俗名")
    @ApiModelProperty(value = "俗名")
    private String localName;

    @ApiModelProperty(value = "商品名")
    private String tradeName;

    @ApiModelProperty(value = "简介")
    private String intro;

    @ApiModelProperty(value = "生境及习性")
    private String habit;

    @ApiModelProperty(value = "地理分布")
    private String distribution;
    @ApiModelProperty(value = "保护现状")
    private String conStatus;
    @ApiModelProperty(value = "特殊管理要求")
    private String requirements;

    @ApiModelProperty(name = "identify", value = "是否使用标识写死，0:不使用,1 :全程使用,2:销售使用")
    private String identify;

    @ApiModelProperty(name = "pedigree", value = "是否进行谱系写死，否：0是：1")
    private String pedigree;

    @ApiModelProperty(name = "proLevel", value = "中国保护水平写死 一级:1 ，2级：2 ，特殊管理要求：3")
    private String proLevel;

    @ApiModelProperty(name = "cites", value = "CITES级别，1级：1,2级：2,3级：3")
    private String cites;

    @TableField(exist = false)
    @ApiModelProperty(value = "文件表单列表")
    private List<FileManage> files;

    @ApiModelProperty(value = "物种编号")
    private String speCode;

    @ApiModelProperty(value = "物种类型")
    private String speType;







}
