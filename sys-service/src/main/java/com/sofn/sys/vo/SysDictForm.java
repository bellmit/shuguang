package com.sofn.sys.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @author heyongjie
 * @date 2020/3/5 15:49
 */
@Data
@ApiModel(value = "保存数据字典对象")
public class SysDictForm {

    @ApiModelProperty("字典ID")
    @Length(max = 32,message = "字典ID超长，最多32个字符")
    private String id;

    @ApiModelProperty("字典类型ID")
    @NotBlank(message = "字典类型ID不能为空")
    @Length(max = 32,message = "字典类型ID超长，最多32个字符")
    private String dicttypeid;

    @ApiModelProperty("字典名称")
    @NotBlank(message = "字典名称不能为空")
    @Length(max = 50,message = "字典名称超长，最多50个字符")
    private String dictname;

    @ApiModelProperty("字典代码")
    @NotBlank(message = "字典代码不能为空")
    @Length(max = 25,message = "字典代码超长，最多25个字符")
    private String dictcode;

    @ApiModelProperty("是否启用")
    @NotBlank(message = "字典是否启用不能为空")
    private String enable;

    @ApiModelProperty("备注")
    @Length(max = 100,message = "字典备注超长，最多100个字符")
    private String remark;
}
