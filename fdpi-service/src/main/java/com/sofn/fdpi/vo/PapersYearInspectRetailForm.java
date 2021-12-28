package com.sofn.fdpi.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@ApiModel("证书年审新增中物种信息")
public class PapersYearInspectRetailForm implements Serializable {

    @ApiModelProperty("证书年审明细表id：新增时为空字符串，修改时需要赋值")
    private String inspectRetailId;

    @ApiModelProperty("证书ID")
    @NotBlank(message = "证书id不能为空")
    @Length(max = 32,message = "证书id长度为32")
    private String papersId;

    @ApiModelProperty("物种ID")
    @NotBlank(message = "物种id不能为空")
    @Length(max = 32,message = "物种id长度为32")
    private String speciesId;

    @ApiModelProperty("物种数量")
    private Integer speciesNumber;

    @ApiModelProperty("标识数量")
    private Integer signboardNumber;
}
