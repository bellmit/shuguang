package com.sofn.agpjpm.vo;


import com.sofn.agpjpm.model.Monitor;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
@ApiModel(value = "监测表VO对象")
public class MonitorVo {

    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "保护点id")
    private String protectId;
    @ApiModelProperty(value = "保护点名称")
    private String protectName;
    @ApiModelProperty(value = "调查日期")
    private Date surveyDate;
    @ApiModelProperty(value = "调查单位")
    private String surveyDept;
    @ApiModelProperty(value = "调查人")
    private String surveyor;
    @ApiModelProperty(value = "省")
    private String province;
    @ApiModelProperty(value = "省名")
    private String provinceName;
    @ApiModelProperty(value = "市")
    private String city;
    @ApiModelProperty(value = "市名")
    private String cityName;
    @ApiModelProperty(value = "县")
    private String county;
    @ApiModelProperty(value = "县名")
    private String countyName;
    @ApiModelProperty(value = "详细地址")
    private String addr;
    @ApiModelProperty(value = "联系电话")
    private String tel;
    @ApiModelProperty(value = "目标物种")
    private String targetSpec;
    @ApiModelProperty(value = "受损面积(亩)")
    private String damage;
    @ApiModelProperty(value = "(目标物种)其它")
    private String other;
    @ApiModelProperty(value = "围栏设施")
    private String fence;
    @ApiModelProperty(value = "看护房")
    private String nurse;
    @ApiModelProperty(value = "警示牌")
    private String warning;
    @ApiModelProperty(value = "巡护路")
    private String patrol;
    @ApiModelProperty(value = "瞭望塔")
    private String tower;
    @ApiModelProperty(value = "其他设施")
    private String otherFacilities;
    @ApiModelProperty(value = "年均温")
    private String avgTemp;
    @ApiModelProperty(value = "年均降雨量")
    private String avgRainfall;
    @ApiModelProperty(value = "种数(优势伴生物种）")
    private String superSpecies;
    @ApiModelProperty(value = "总株、苗数(优势伴生物种）")
    private String superSeedling;
    @ApiModelProperty(value = "科数(优势伴生物种）")
    private String superFamily;
    @ApiModelProperty(value = "属数(优势伴生物种）")
    private String superGenera;
    @ApiModelProperty(value = "生长状况(优势伴生物种）")
    private String superGrowth;
    @ApiModelProperty(value = "群落覆盖度(%)(优势伴生物种）")
    private String superCover;
    @ApiModelProperty(value = "分布面积（亩）(优势伴生物种）")
    private String superArea;
    @ApiModelProperty(value = "返青期(开始时间)")
    private Date trunGreenStart;
    @ApiModelProperty(value = "返青期(结束时间)")
    private Date trunGreenEnd;
    @ApiModelProperty(value = "枯萎/落叶期(开始时间)")
    private Date witherStart;
    @ApiModelProperty(value = "枯萎/落叶期(结束时间)")
    private Date witherEnd;
    @ApiModelProperty(value = "种类数(入侵物种)")
    private String invadeSpecies;
    @ApiModelProperty(value = "物种名称(入侵物种)")
    private String invadeSpecName;
    @ApiModelProperty(value = "分布面积（亩）(入侵物种）")
    private String invadeArea;
    @ApiModelProperty(value = "危害程度(入侵物种）")
    private String invadeHazard;
    @ApiModelProperty(value = "铲除情况(入侵物种）")
    private String invadeEradicate;

    @ApiModelProperty(value = "监测（物种）VO 对象")
    private List<SpeciesMonitorVo> speciesMonitorVos;

    @ApiModelProperty(value = "保护点照片")
    private List<PictureAttVo> protectPic;

    /************************* 目标物种和基础设施保护情况 *************************/
    @ApiModelProperty(value = "目标物种照片")
    private List<PictureAttVo> specPic;
    @ApiModelProperty(value = "围栏设施照片")
    private List<PictureAttVo> fencePic;
    @ApiModelProperty(value = "看护房照片")
    private List<PictureAttVo> nursePic;
    @ApiModelProperty(value = "警示牌照片")
    private List<PictureAttVo> warningPic;
    @ApiModelProperty(value = "巡护路照片")
    private List<PictureAttVo> patrolPic;
    @ApiModelProperty(value = "瞭望塔照片")
    private List<PictureAttVo> towerPic;
    @ApiModelProperty(value = "其他设施照片")
    private List<PictureAttVo> otherFacilitiesPic;
    /*************************  优势伴生物种  *************************/
    @ApiModelProperty(value = "群落景观、盖度照片(优势伴生物种）")
    private List<PictureAttVo> superCommunityPic;
    @ApiModelProperty(value = "其它照片(优势伴生物种）")
    private List<PictureAttVo> otherPic;
    /************************* 入侵物种 **************************/
    @ApiModelProperty(value = "植株照片")
    private List<PictureAttVo> plantPic;
    @ApiModelProperty(value = "群落景观、盖度照片(入侵物种)")
    private List<PictureAttVo> invadeCommunityPic;
    @ApiModelProperty(value = "铲除前照片")
    private List<PictureAttVo> beforePic;
    @ApiModelProperty(value = "铲除后照片")
    private List<PictureAttVo> afterPic;

    /**
     * 实体类转VO类
     */
    public static MonitorVo entity2Vo(Monitor entity) {
        MonitorVo vo = null;
        if (!Objects.isNull(entity)) {
            vo = new MonitorVo();
            BeanUtils.copyProperties(entity, vo);
            vo.setDamage(Objects.nonNull(entity.getDamage()) ? String.valueOf(entity.getDamage()) : "");
            vo.setAvgTemp(Objects.nonNull(entity.getAvgTemp()) ? String.valueOf(entity.getAvgTemp()) : "");
            vo.setAvgRainfall(Objects.nonNull(entity.getAvgRainfall()) ? String.valueOf(entity.getAvgRainfall()) : "");
            vo.setSuperSpecies(Objects.nonNull(entity.getSuperSpecies()) ? String.valueOf(entity.getSuperSpecies()) : "");
            vo.setSuperSeedling(Objects.nonNull(entity.getSuperSeedling()) ? String.valueOf(entity.getSuperSeedling()) : "");
            vo.setSuperFamily(Objects.nonNull(entity.getSuperFamily()) ? String.valueOf(entity.getSuperFamily()) : "");
            vo.setSuperGenera(Objects.nonNull(entity.getSuperGenera()) ? String.valueOf(entity.getSuperGenera()) : "");
            vo.setSuperCover(Objects.nonNull(entity.getSuperCover()) ? String.valueOf(entity.getSuperCover()) : "");
            vo.setSuperArea(Objects.nonNull(entity.getSuperArea()) ? String.valueOf(entity.getSuperArea()) : "");
            vo.setInvadeSpecies(Objects.nonNull(entity.getInvadeSpecies()) ? String.valueOf(entity.getInvadeSpecies()) : "");
            vo.setInvadeArea(Objects.nonNull(entity.getInvadeArea()) ? String.valueOf(entity.getInvadeArea()) : "");
        }
        return vo;
    }
}
