package com.sofn.agpjpm.vo;

import com.sofn.agpjpm.model.TargetSpec;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Objects;

@Data
@ApiModel(value = "监测（物种）VO 对象")
public class SpeciesMonitorVo {

    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "物种id")
    private String specId;
    @ApiModelProperty(value = "物种名称")
    private String specName;
    @ApiModelProperty(value = "拉丁文种名")
    private String latinName;
    @ApiModelProperty(value = "属名")
    private String attrName;
    @ApiModelProperty(value = "科名")
    private String familyName;
    @ApiModelProperty(value = "生长状况")
    private String growth;
    @ApiModelProperty(value = "分布密度")
    private String density;
    @ApiModelProperty(value = "丰富度")
    private String richness;
    @ApiModelProperty(value = "分布面积（亩）")
    private String area;

    @ApiModelProperty(value = "植株照片(目标物种)")
    private List<PictureAttVo> plantPic;
    @ApiModelProperty(value = "群落景观、盖度照片(目标物种)")
    private List<PictureAttVo> communityPic;

    /**
     * 实体类转VO类
     */
    public static SpeciesMonitorVo entity2Vo(TargetSpec entity) {
        SpeciesMonitorVo vo = new SpeciesMonitorVo();
        if (!Objects.isNull(entity)) {
            BeanUtils.copyProperties(entity, vo);
            vo.setArea(Objects.nonNull(entity.getArea()) ? String.valueOf(entity.getArea()) : "");
        }
        return vo;
    }
}
