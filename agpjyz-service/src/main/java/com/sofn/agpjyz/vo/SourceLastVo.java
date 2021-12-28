package com.sofn.agpjyz.vo;

import com.sofn.agpjyz.model.Source;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
@ApiModel(value = "资源调查最后一次VO对象")
public class SourceLastVo {

    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "调查日期")
    private Date survey;
    @ApiModelProperty(value = "调查人")
    private String investigator;
    @ApiModelProperty(value = "联系电话")
    private String phone;
    @ApiModelProperty(value = "物种名称ID")
    private String specId;
    @ApiModelProperty(value = "物种名称")
    private String specValue;
    @ApiModelProperty(value = "拉丁学名")
    private String latin;
    @ApiModelProperty(value = "俗名")
    private String commonName;
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
    @ApiModelProperty(value = "海拔")
    private String altitude;
    @ApiModelProperty(value = "分布面积")
    private String distribution;
    @ApiModelProperty(value = "种群数量")
    private String amount;
    @ApiModelProperty(value = "形态特征")
    private String features;
    @ApiModelProperty(value = "生物学特性")
    private String characteristic;
    @ApiModelProperty(value = "濒危状况ID")
    private String endangeredId;
    @ApiModelProperty(value = "濒危状况名称")
    private String endangeredValue;
    @ApiModelProperty(value = "威胁因素")
    private String threaten;

    @ApiModelProperty(value = "植被类型ID")
    private String vegetationId;
    @ApiModelProperty(value = "植被类型名称")
    private String vegetationValue;
    @ApiModelProperty(value = "植被覆盖率")
    private String vegetationCoverage;
    @ApiModelProperty(value = "土壤类型ID")
    private String soilId;
    @ApiModelProperty(value = "土壤类型名称")
    private String soilValue;
    @ApiModelProperty(value = "土壤肥力")
    private String soilFertility;
    @ApiModelProperty(value = "保护与利用状况")
    private String protectionUtilization;
    @ApiModelProperty(value = "评价和建议")
    private String suggest;
    @ApiModelProperty(value = "生境类型VO对象")
    private List<HabitatTypeVo> habitatTypeVos;
    @ApiModelProperty(value = "生境类型ids")
    private List<String> habitatTypeIds;

    /**
     * 实体类转VO类
     */
    public static SourceLastVo entity2Vo(Source entity) {
        SourceLastVo vo = null;
        if (!Objects.isNull(entity)) {
            vo = new SourceLastVo();
            BeanUtils.copyProperties(entity, vo);
        }
        return vo;
    }
}
