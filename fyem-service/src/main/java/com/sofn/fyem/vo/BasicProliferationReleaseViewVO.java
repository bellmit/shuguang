package com.sofn.fyem.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.common.model.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @Description: 水生生物增殖放流基础数据查看列表VO
 * @Author: DJH
 * @Date: 2020.4.26 11.10
 */
@ApiModel(value = "BasicProliferationReleaseViewVO", description = "水生生物增殖放流基础数据查看列表VO")
@TableName(value = "BASIC_PROLIFERATION_RELEASE")
@Data
@EqualsAndHashCode(callSuper = false)
public class BasicProliferationReleaseViewVO extends BaseVo<BasicProliferationReleaseViewVO> {

    @ApiModelProperty(name = "belongYear", value = "所属年度")
    private String belongYear;

    @ApiModelProperty(name = "releaseArea", value = "放流区域")
    private String releaseArea;

    @ApiModelProperty(name = "provinceId", value = "省级id")
    private String provinceId;

    @ApiModelProperty(name = "cityId", value = "市级id")
    private String cityId;

    @ApiModelProperty(name = "countyId", value = "区县id")
    private String countyId;

    @ApiModelProperty(name = "releaseSite", value = "放流地点")
    private String releaseSite;

    @JSONField(format = "yyyy-MM-dd")
    @ApiModelProperty(name = "releaseTime", value = "放流时间")
    private Date releaseTime;

    @ApiModelProperty(name = "organizationName", value = "组织单位")
    private String organizationName;

    @ApiModelProperty(name = "releaseLevel", value = "放流活动级别(0:其他,1:市县级,2:省级,3:国家级)")
    private String releaseLevel;

    @ApiModelProperty(name = "releaseVarieties", value = "放流品种")
    private String releaseVarieties;

    @ApiModelProperty(name = "releaseNumber", value = "流放数量(万尾)")
    private Double releaseNumber;

    @ApiModelProperty(name = "releaseSpecification", value = "流放规格")
    private Double releaseSpecification;

    @ApiModelProperty(name = "nationInvestment", value = "中央投资")
    private Double nationInvestment;

    @ApiModelProperty(name = "provinceInvestment", value = "省级投资")
    private Double provinceInvestment;

    @ApiModelProperty(name = "cityInvestment", value = "市县投资")
    private Double cityInvestment;

    @ApiModelProperty(name = "societyInvestment", value = "社会投资")
    private Double societyInvestment;

    @ApiModelProperty(name = "provideOrganizationName", value = "供苗单位名称")
    private String provideOrganizationName;

    @ApiModelProperty(name = "accessory", value = "附件（附件文件存储id）")
    private String accessory;

    @ApiModelProperty(name = "accessoryName", value = "附件名")
    private String accessoryName;
}
