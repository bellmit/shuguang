package com.sofn.ouman.vo.apivo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

/**
 * 文件管理表单对象
 * @author heyongjie
 * @date 2019/11/20 15:13
 */
@ApiModel(value = "文件管理Form对象")
@Data
public class SysFileManageForm {

    @ApiModelProperty(value = "文件名称")
    @Length(max = 60,message = "文件名称超长")
    private String fileName;

    @ApiModelProperty(value = "系统ID")
    @NotEmpty(message = "系统ID不能为空")
    @Length(max = 64,message = "系统ID超长")
    private String systemId;

    @ApiModelProperty(value = "备注")
    @Length(max = 250,message = "备注超长")
    private String remark;

    @ApiModelProperty(value = "需要激活的ID，用,隔开")
    @NotEmpty(message = "待激活文件ID不能为空")
    private String  ids;

    @ApiModelProperty(value = "接口号，hidden开头的不会在文件管理中显示，默认为hidden，所以文件管理时必须改为其他值")
    private String interfaceNum = "hidden";



}
