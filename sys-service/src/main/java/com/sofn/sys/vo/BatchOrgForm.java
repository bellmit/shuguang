package com.sofn.sys.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.kafka.common.protocol.types.Field;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@ApiModel
public class BatchOrgForm {

    @ApiModelProperty(value = "系统APPID",required = true)
    @NotBlank(message = "系统APPID不能为空")
    private String appId;

    @ApiModelProperty(value = "机构id集合",required = true)
    @NotEmpty(message = "机构id集合不能为空")
    private List<String> ids;

}
