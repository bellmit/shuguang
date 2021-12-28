package com.sofn.sys.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author heyongjie
 * @date 2020/3/3 16:04
 */
@ApiModel(value = "保存系统和数据字典数据字典值的关系VO")
@Data
public class SaveSubSystemAndBaseDataRelationshipVo {

    @ApiModelProperty("数据字典ID（TYPE_ID）和字典值ID(VALUE_TYPEID_VALUEID)用英文逗号隔开")
    private String ids;

    @ApiModelProperty("系统APPID")
    @NotBlank(message = "系统APPID不能为空")
    private String appId;

}
