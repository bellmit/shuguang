package com.sofn.sys.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.kafka.common.protocol.types.Field;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Map;

/**
 * @author heyongjie
 * @date 2020/3/5 15:49
 */
@Data
@ApiModel(value = "机构职能对象")
public class SysFunctionForm {

    @ApiModelProperty("机构ID")
    @NotBlank(message = "机构ID不能为空")
    private String orgId;

    @ApiModelProperty("机构系统职能")
    private List<FunctionOfSystemForm> functionOfSystemForm;

  @Data
  @ApiModel(value = "机构系统职能对象")
  public static class FunctionOfSystemForm {
    @ApiModelProperty("系统appid")
    private String appId;

    @ApiModelProperty("职能代码列表")
    private List<String> functionCodes;
  }
}
