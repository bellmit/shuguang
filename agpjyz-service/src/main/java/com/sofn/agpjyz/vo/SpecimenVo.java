package com.sofn.agpjyz.vo;

import com.sofn.agpjyz.enums.ProcessEnum;
import com.sofn.agpjyz.model.Source;
import com.sofn.agpjyz.model.Specimen;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-03-11 15:47
 */
@Data
@ApiModel(value = "标本采集VO对象")
public class SpecimenVo {
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
//    @ApiModelProperty(value = "乔木")
//    private String   treeValue;
//    @ApiModelProperty(value = "年份")
//    private String   treeYear;
//    @ApiModelProperty(value = "宜立")
//    private String   yiLi;
    //审核状态 状态 0已保存 1已撤回 2已上报 3市级退回 4市级通过 5省级退回 6省级通过 7总站退回 8总站通过
    @ApiModelProperty(value = "审核状态 状态 0已保存 1已撤回 2已上报 3市级退回 4市级通过 5省级退回 6省级通过 7总站退回 8总站通过")
    private String   status;
    @ApiModelProperty(value = "状态 0已保存 1已撤回 2已上报 3市级退回 4市级通过 5省级退回 6省级通过 7总站退回 8总站通过")
    private String statusName;
    @ApiModelProperty(value = "性状类型表单对象(乔木)")
    private List<CharacterVo> treeValue;
    @ApiModelProperty(value = "性状")
    private String stringAgg;
    @ApiModelProperty(value = "图片附件VO对象(根)")
    private List<PictureAccessoriesVo> root;
    @ApiModelProperty(value = "图片附件VO对象(茎)")
    private List<PictureAccessoriesVo> stem;
    @ApiModelProperty(value = "图片附件VO对象(叶)")
    private List<PictureAccessoriesVo> leaf;
    @ApiModelProperty(value = "图片附件VO对象(花)")
    private List<PictureAccessoriesVo> flower;
    @ApiModelProperty(value = "图片附件VO对象(果)")
    private List<PictureAccessoriesVo> fruit;
    @ApiModelProperty(value = "图片附件VO对象(种子)")
    private List<PictureAccessoriesVo> seed;
    @ApiModelProperty(value = "审核进程记录VO对象")
    private List<AuditProcessVo> auditProcessVos;
    @ApiModelProperty(value = "乔木数组")
    private List<String> tv;
    @ApiModelProperty(value = "宜立数组")
    private List<String> yl;

    @ApiModelProperty(value = "能否审核")
    private Boolean canAudit;
    @ApiModelProperty(value = "能否撤回")
    private Boolean canCancel;
    @ApiModelProperty(value = "能否修改")
    private Boolean canEdit;
    /**
     * 实体类转VO类
     */
    public static SpecimenVo entity2Vo(Specimen entity) {
        SpecimenVo vo = new SpecimenVo();
        if (!Objects.isNull(entity)) {
            BeanUtils.copyProperties(entity, vo);
            vo.setStatusName(ProcessEnum.getVal(entity.getStatus()));
        }
        return vo;
    }
}
