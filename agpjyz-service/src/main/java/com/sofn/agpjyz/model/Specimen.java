package com.sofn.agpjyz.model;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.common.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-03-11 13:53
 */
@Data
@TableName("SPECIMEN")
public class Specimen extends BaseModel<Specimen> {


    //            采集号
    private String   collectionNumber;
    //            采集日期
    private Date collectionDate;
    //            采集单位ID
    private String collectionId;
    //            采集单位名称
    private String collectionValue;
    //            采集人
    private String collectioner;
    //            中文名ID
    private String chineseId;
    //            中文名名称
    private String chineseValue;
    //    拉丁学名
    private String latinName;
    //科名
    private String  scientificName;
    //属名
    private String   genericName;
    //俗名
    private String  commonName;
    //省
    private String  province;
    //            省名
    private String  provinceName;
    //市
    private String   city;
    //            市名
    private String  cityName;
    //区
    @TableField(strategy = FieldStrategy.IGNORED)
    private String   county;
    //            县名
    @TableField(strategy = FieldStrategy.IGNORED)
    private String   countyName;
    //海拔
    private String   altitude;
    //植株高度
    private String   plantHeight;
    //胸径
    private String   diameter;
////    @ApiModelProperty(value = "乔木")
//    private String   treeValue;
//    @ApiModelProperty(value = "年份")
    private String   treeYear;
////    @ApiModelProperty(value = "宜立")
//    private String   yiLi;
    //审核状态 状态 0已保存 1已撤回 2已上报 3市级退回 4市级通过 5省级退回 6省级通过 7总站退回 8总站通过
    private String   status;
    /**
     * 是否已审核
     */
    private String  auditFlag;
    /**
     * 是否专家填报
     */
    private String expertReport;
    /**
     * 性状
     */
    @TableField(exist=false)
    private String stringAgg;

//    {
//        ID,COLLECTION_NUMBER,COLLECTION_DATE,COLLECTION_ID,
//        COLLECTION_VALUE,COLLECTIONER,CHINESE_ID,CHINESE_VALUE,
//        LATIN_NAME,SCIENTIFIC_NAME,GENERIC_NAME,COMMON_NAME,PROVINCE,
//        CITY,COUNTY, ALTITUDE,PLANT_HEIGHT, DIAMETER,STATUS,
//        PROVINCE_NAME,CITY_NAME,COUNTY_NAME
//    }

}
