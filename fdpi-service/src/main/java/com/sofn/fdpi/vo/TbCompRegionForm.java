package com.sofn.fdpi.vo;

import com.sofn.fdpi.model.TbComp;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel("企业行政区划表单")
public class TbCompRegionForm {
    //企业id（支撑平台组织机构id）
    @NotBlank(message = "企业id不能为空")
    @Length(max = 32,message = "企业id长度已超长！")
    @ApiModelProperty("企业id（支撑平台组织机构id）")
    private String id;
    //省
    @NotBlank(message = "省不能为空")
    @Length(max = 32,message = "省长度已超长！")
    @ApiModelProperty("省")
    private String compProvince;
    //市
    @NotBlank(message = "市不能为空")
    @Length(max = 32,message = "市长度已超长！")
    @ApiModelProperty("市")
    private String compCity;
    //区
    @NotBlank(message = "区不能为空")
    @Length(max = 32,message = "区长度已超长！")
    @ApiModelProperty("区")
    private String compDistrict;
    //行政区划中文
    @NotBlank(message = "行政区划中文不能为空")
    @Length(max = 100,message = "行政区划中文已超长！")
    @ApiModelProperty("行政区划中文（省-市-区中文拼接）")
    private String regionInCh;

    public TbComp getTbComp(TbCompRegionForm tbCompRegionForm){
        TbComp tbComp=new TbComp();
        BeanUtils.copyProperties(tbCompRegionForm,tbComp);
        return tbComp;
    }
}
