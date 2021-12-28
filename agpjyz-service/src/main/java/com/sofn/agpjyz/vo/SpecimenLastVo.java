package com.sofn.agpjyz.vo;

import com.sofn.agpjyz.enums.ProcessEnum;
import com.sofn.agpjyz.model.Specimen;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @Description:
 * @author: xiaobo
 * @Date: 2020-12-16 15:59
 */
@Data
@ApiModel(value = "标本采集上次填报信息VO对象")
public class SpecimenLastVo {
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
    @ApiModelProperty(value = "性状类型表单对象(乔木)")
    private List<CharacterVo> treeValue;
    @ApiModelProperty(value = "性状")
    private String stringAgg;
    @ApiModelProperty(value = "乔木数组")
    private List<String> tv;
    @ApiModelProperty(value = "宜立数组")
    private List<String> yl;

    public static SpecimenLastVo entity2Vo(Specimen entity){
        SpecimenLastVo vo = new SpecimenLastVo();
        if (!Objects.isNull(entity)) {
            BeanUtils.copyProperties(entity, vo);
        }
        return vo;
    }

}
