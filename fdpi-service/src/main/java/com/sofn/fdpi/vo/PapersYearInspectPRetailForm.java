package com.sofn.fdpi.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@ApiModel("证书年审新增中证书信息")
public class PapersYearInspectPRetailForm implements Serializable {

    @ApiModelProperty("证书年审明细表id：新增时为空字符串，修改时需要赋值")
    private String inspectPRetailId;

    @ApiModelProperty("证书ID")
    @NotBlank(message = "证书id不能为空")
    @Length(max = 32,message = "证书id长度为32")
    private String papersId;
}
