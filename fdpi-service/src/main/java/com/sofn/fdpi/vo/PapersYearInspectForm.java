package com.sofn.fdpi.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Data
@ApiModel("证书年审新增/编辑表单对象")
public class PapersYearInspectForm implements Serializable {
    @ApiModelProperty("证书年审id：新增时为空字符串，修改时需要赋值")
    private String inspectId;
    @ApiModelProperty("年度")
    private int year;
    @ApiModelProperty("企业id")
    @NotBlank(message = "企业id不能为空")
    @Length(max = 32,message = "企业id长度为32")
    private String compId;
    @ApiModelProperty("物种信息列表,新增时，需要上传；编辑时，不需要上传")
    List<PapersYearInspectRetailForm> speciesList;
    @ApiModelProperty("证书列表,新增时，需要上传；编辑时，不需要上传")
    List<PapersYearInspectPRetailForm> papersList;
}
