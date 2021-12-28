package com.sofn.agpjyz.vo;

import com.sofn.agpjyz.model.PlantUtilization;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
@ApiModel(value = "植物利用VO对象")
public class PlantUtilizationVo {

    @ApiModelProperty(value = "主键")
    private String id;
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
    @ApiModelProperty(value = "物种名称ID")
    private String specId;
    @ApiModelProperty(value = "物种名称")
    private String specValue;
    @ApiModelProperty(value = "拉丁学名")
    private String latin;
    @ApiModelProperty(value = "产业利用ID")
    private String industrialId;
    @ApiModelProperty(value = "产业利用名称")
    private String industrialValue;
    @ApiModelProperty(value = "利用单位名称")
    private String utilizationUnit;
    @ApiModelProperty(value = "植物利用用途")
    private String purpose;
    @ApiModelProperty(value = "植物利用用途名称")
    private String purposeName;
    @ApiModelProperty(value = "具体内容")
    private String concreteContent;
    @ApiModelProperty(value = "具体内容名称")
    private String concreteContentName;
    @ApiModelProperty(value = "价值")
    private String worth;
    @ApiModelProperty(value = "价值名称")
    private String worthName;
    @ApiModelProperty(value = "产值(万元)")
    private Double outputValue;
    @ApiModelProperty(value = "市场需求")
    private String marketDemand;
    @ApiModelProperty(value = "市场需求名称")
    private String marketDemandName;
    @ApiModelProperty(value = "人工栽培技术 1过关2不过关3其它")
    private String artificial;
    @ApiModelProperty(value = "人工栽培技术名称")
    private String artificialName;
    @ApiModelProperty(value = "人工栽培技术(其它)")
    private String artificialOther;
    @ApiModelProperty(value = "其他")
    private String other;
    @ApiModelProperty(value = "图片附件VO对象(其它)")
    private List<PictureAccessoriesVo> picOther;
    @ApiModelProperty(value = "上报人")
    private String reportPerson;
    @ApiModelProperty(value = "上报时间")
    private Date reportTime;

    /**
     * 实体类转VO类
     */
    public static PlantUtilizationVo entity2Vo(PlantUtilization entity) {
        PlantUtilizationVo vo = null;
        if (!Objects.isNull(entity)) {
            vo = new PlantUtilizationVo();
            BeanUtils.copyProperties(entity, vo);
            vo.setMarketDemandName("1".equals(entity.getMarketDemand()) ? "大" : "不大");
            String artificial = entity.getArtificial();
            vo.setArtificialName("1".equals(artificial) ? "过关" : ("2".equals(artificial) ? "不过关" : "其它"));
            Double outputValue = entity.getOutputValue();
            if (outputValue != null)
                vo.setOutputValue(Double.valueOf(new DecimalFormat("#.00").format(outputValue)));
        }
        return vo;
    }
}
