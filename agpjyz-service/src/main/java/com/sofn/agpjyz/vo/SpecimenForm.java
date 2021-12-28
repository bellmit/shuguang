package com.sofn.agpjyz.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-03-11 15:03
 */
@Data
@ApiModel(value = "标本采集表单对象")
public class SpecimenForm {
    @ApiModelProperty(value = "主键")
    private String id;
    //    采集号

    @ApiModelProperty(value = "采集号")
    private String   collectionNumber;
    //            采集日期
    @ApiModelProperty(value = "采集日期")
    private Date collectionDate;
    //            采集单位ID

    @ApiModelProperty(value = "采集单位ID")
    private String collectionId;
    //            采集单位名称

    @ApiModelProperty(value = "采集单位名称")
    private String collectionValue;
    //            采集人

    @ApiModelProperty(value = "采集人")
    private String collectioner;
    //            中文名ID
    @ApiModelProperty(value = "中文名ID")
    private String chineseId;
    //            中文名名称
    @ApiModelProperty(value = "中文名名称")
    private String chineseValue;
    //    拉丁学名
    @ApiModelProperty(value = "拉丁学名")
    private String latinName;
    //科名
    @ApiModelProperty(value = "科名")
    private String  scientificName;
    //属名
    @ApiModelProperty(value = "属名")
    private String   genericName;
    //俗名
    @Size(max=64,message = "俗名长度不能超过64")
    @ApiModelProperty(value = "俗名")
    private String  commonName;
    //省
    @ApiModelProperty(value = "省")
    private String  province;
    //            省名
    @ApiModelProperty(value = "省名")
    private String  provinceName;
    //市
    @ApiModelProperty(value = "市")
    private String   city;
    //            市名
    @ApiModelProperty(value = "市名")
    private String  cityName;
    //区
    @ApiModelProperty(value = "区")
    private String   county;
    //            县名
    @ApiModelProperty(value = "县名")
    private String   countyName;
    //海拔
    @ApiModelProperty(value = "海拔")
    private String   altitude;
    //植株高度
    @ApiModelProperty(value = "植株高度")
    private String   plantHeight;
    //胸径
    @ApiModelProperty(value = "胸径")
    private String   diameter;
    //审核状态 状态 0已保存 1已撤回 2已上报 3市级退回 4市级通过 5省级退回 6省级通过 7总站退回 8总站通过
    @ApiModelProperty(value = "审核状态 状态 0已保存 1已撤回 2已上报 3市级退回 4市级通过 5省级退回 6省级通过 7总站退回 8总站通过")
    private String   status;

//    @ApiModelProperty(value = "乔木")
////    private String   rootDescribe;
//    @ApiModelProperty(value = "年份")
//    private String   treeYear;
//    @ApiModelProperty(value = "宜立")
//    private String   yiLi;
    @Valid
    @ApiModelProperty(value = "性状类型表单对象(乔木)")
    private List<CharacterForm> treeValue;
//    @ApiModelProperty(value = "性状类型表单对象(宜立)")
//    private List<CharacterForm> yiLi;
    @ApiModelProperty(value = "图片附件表单对象(根)")
    private List<PictureAccessoriesForm> root;
    @ApiModelProperty(value = "图片附件表单对象(茎)")
    private List<PictureAccessoriesForm> stem;
    @ApiModelProperty(value = "图片附件表单对象(叶)")
    private List<PictureAccessoriesForm> leaf;
    @ApiModelProperty(value = "图片附件表单对象(花)")
    private List<PictureAccessoriesForm> flower;
    @ApiModelProperty(value = "图片附件表单对象(果)")
    private List<PictureAccessoriesForm> fruit;
    @ApiModelProperty(value = "图片附件表单对象(种子)")
    private List<PictureAccessoriesForm> seed;
}
