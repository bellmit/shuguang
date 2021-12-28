package com.sofn.fyem.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.sofn.fyem.model.BasicProliferationRelease;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@ApiModel(value = "BasicProliferationReleaseForm", description = "水生生物增殖放流基础数据表单")
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class BasicProliferationReleaseForm {

    @ApiModelProperty(name = "id", value = "主键ID")
    private String id;

    @NotBlank(message = "上报年度不能为空")
    @ApiModelProperty(name = "belongYear", value = "所属年度")
    private String belongYear;

    @ApiModelProperty(name = "releaseArea", value = "放流区域")
    private String releaseArea;

    @NotBlank(message = "流放地点不能为空")
    @ApiModelProperty(name = "releaseSite", value = "流放地点")
    private String releaseSite;

    @NotBlank(message = "省级id不能为空")
    @ApiModelProperty(name = "provinceId", value = "省级id")
    private String provinceId;

    @NotBlank(message = "市级id不能为空")
    @ApiModelProperty(name = "cityId", value = "市级id")
    private String cityId;

    @NotBlank(message = "区县id不能为空")
    @ApiModelProperty(name = "id", value = "区县id")
    private String countyId;

    @NotBlank(message = "经度不能为空")
    @Digits(integer = 100,fraction = 6, message = "经度只允许在100位整数和6位小数范围内")
    @ApiModelProperty(name = "longitude", value = "经度")
    private String longitude;

    @NotBlank(message = "纬度不能为空")
    @Digits(integer = 100,fraction = 6, message = "纬度只允许在100位整数和6位小数范围内")
    @ApiModelProperty(name = "latitude", value = "纬度")
    private String latitude;

    @NotNull(message = "流放时间不能为空")
    @ApiModelProperty(name = "releaseTime", value = "流放时间")
    @JSONField(format = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date releaseTime;

    @NotNull(message = "流放资金不能为空")
    @Digits(integer = 100,fraction = 2, message = "流放资金只允许在100位整数和2位小数范围内")
    @ApiModelProperty(name = "releaseMoney", value = "流放资金(万元)")
    private Double releaseMoney;

    @NotBlank(message = "组织名称不能为空")
    @ApiModelProperty(name = "organizationName", value = "组织名称")
    private String organizationName;

    @NotBlank(message = "流放级别不能为空")
    @ApiModelProperty(name = "releaseLevel", value = "放流活动级别(0:其他,1:市县级,2:省级,3:国家级)")
    private String releaseLevel;

    @NotBlank(message = "流放品种不能为空")
    @ApiModelProperty(name = "releaseVarieties", value = "流放品种")
    private String releaseVarieties;

    @NotNull(message = "流放数量不能为空")
    @Digits(integer = 100,fraction = 4, message = "流放数量只允许在100位整数和4位小数范围内")
    @ApiModelProperty(name = "releaseNumber", value = "流放数量(万尾)")
    private Double releaseNumber;

    @NotNull(message = "流放规格不能为空")
    @Digits(integer = 100,fraction = 2, message = "流放规格只允许在100位整数和2位小数范围内")
    @ApiModelProperty(name = "releaseSpecification", value = "流放规格")
    private Double releaseSpecification;

    @NotNull(message = "中央投资不能为空")
    @Digits(integer = 100,fraction = 2, message = "中央投资只允许在100位整数和2位小数范围内")
    @ApiModelProperty(name = "nationInvestment", value = "中央投资")
    private Double nationInvestment;

    @NotNull(message = "省级投资不能为空")
    @Digits(integer = 100,fraction = 2, message = "省级投资只允许在100位整数和2位小数范围内")
    @ApiModelProperty(name = "provinceInvestment", value = "省级投资")
    private Double provinceInvestment;

    @NotNull(message = "市县投资不能为空")
    @Digits(integer = 100,fraction = 2, message = "市县投资只允许在100位整数和2位小数范围内")
    @ApiModelProperty(name = "cityInvestment", value = "市县投资")
    private Double cityInvestment;

    @NotNull(message = "社会投资不能为空")
    @Digits(integer = 100,fraction = 2, message = "社会投资只允许在100位整数和2位小数范围内")
    @ApiModelProperty(name = "societyInvestment", value = "社会投资")
    private Double societyInvestment;

    @NotBlank(message = "供苗单位名称不能为空")
    @ApiModelProperty(name = "provideOrganizationName", value = "供苗单位名称")
    private String provideOrganizationName;

    @ApiModelProperty(name = "remark", value = "备注")
    private String remark;

    @ApiModelProperty(name = "accessory", value = "附件（附件文件存储id）")
    private String accessory;

    @ApiModelProperty(name = "accessoryName", value = "附件名")
    private String accessoryName;

    public BasicProliferationReleaseForm() {
    }

    /**
     * 将vo转换为po对象
     * @param form
     * @param bpr
     */
    public static void getBasicProliferationRelease(BasicProliferationReleaseForm form,BasicProliferationRelease bpr){
        BeanUtils.copyProperties(form, bpr);
    }

    /**
     * 将po转换为vo对象
     * @param bpr
     */
    public static BasicProliferationReleaseForm getBasicProliferationReleaseForm(BasicProliferationRelease bpr){
        BasicProliferationReleaseForm form = new BasicProliferationReleaseForm();
        BeanUtils.copyProperties(bpr, form);
        return form;
    }
}
