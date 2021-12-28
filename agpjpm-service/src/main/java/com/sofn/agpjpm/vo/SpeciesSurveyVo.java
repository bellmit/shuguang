package com.sofn.agpjpm.vo;

import com.sofn.agpjpm.model.TargetSpec;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-06-10 16:37
 */
@Data
public class SpeciesSurveyVo {
    @ApiModelProperty(value = "主键，前端不管不用传")
    private String id;

    @ApiModelProperty(value = "物种id")
    private String specId;

    @ApiModelProperty(value = "资源调查id，前端不管不用传")
    private String sourceId;

    @ApiModelProperty(value = "物种名称")
    private String specName;

    @ApiModelProperty(value = "属名")
    private String attrName;
    @ApiModelProperty(value = "拉丁名")
    private String latinName;

    @ApiModelProperty(value = "科名")
    private String familyName;

    @ApiModelProperty(value = "分布面积（亩）")
    private String area;

    @ApiModelProperty(value = "返青期开始")
    private Date greenS;

    @ApiModelProperty(value = "返青期结束")
    private Date greenE;

    @ApiModelProperty(value = "繁殖期开始")
    private Date breedS;

    @ApiModelProperty(value = "繁殖期结束")
    private Date breedE;

    @ApiModelProperty(value = "枯萎期开始")
    private Date witheredS;

    @ApiModelProperty(value = "枯萎期结束")
    private Date witheredE;

    @ApiModelProperty(value = "分布数量")
    private String amount;
    @ApiModelProperty(value = "前段判断：是否可以修改属名和科名")
    private Boolean isDisabled;

    @ApiModelProperty(value = "省内是否新发现")
    private String discovery;
    @ApiModelProperty(value = "是否为国家新发物种 0否 1是")
    private String newSpecies;
    @ApiModelProperty(value = "植株照片")
    private List<PictureAttVo> plant;
    @ApiModelProperty(value = "群落照片")
    private List<PictureAttVo>  community;
    @ApiModelProperty(value = "生境照片")
    private List<PictureAttVo>  habitat;
    /**
     * 实体类转VO类
     */
    public static SpeciesSurveyVo entity2Vo(TargetSpec entity) {
        SpeciesSurveyVo vo = new SpeciesSurveyVo();
        if (!Objects.isNull(entity)) {
            BeanUtils.copyProperties(entity, vo);

        }
        return vo;
    }
}
