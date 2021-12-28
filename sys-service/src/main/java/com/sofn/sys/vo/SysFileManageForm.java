package com.sofn.sys.vo;

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
@ApiModel(value = "激活文件对象，当修改时，不为空才更新")
@Data
public class SysFileManageForm {

    @ApiModelProperty(value = "文件名称")
    @Length(max = 60,message = "文件名称超长")
    private String fileName;

    @ApiModelProperty(value = "系统ID，修改时无效，但是也请将系统ID传入")
    @NotEmpty(message = "系统ID不能为空")
    @Length(max = 64,message = "系统ID超长")
    private String systemId;

    @ApiModelProperty(value = "备注")
    @Length(max = 100,message = "备注超长，最多100个字符")
    private String remark;

    @ApiModelProperty(value = "需要激活的ID，用英文逗号隔开，修改时不支持批量")
    @NotEmpty(message = "文件ID不能为空")
    private String  ids;

    @ApiModelProperty(value = "接口号，系统的哪个接口上传的文件，修改时无效")
    @Length(max=50,message = "接口号超长，最多50个字符")
    private String interfaceNum;

    @ApiModelProperty(value = "文件编号")
    @Length(max=30,message = "文件编号超长，最多30个字符")
    private String fileNumber;

    @ApiModelProperty(value = "文件状态，Y启用，N停用")
    @Length(max=2,message = "文件状态超长，最多1个字符")
    private String fileState;

    @ApiModelProperty(value = "业务文件类型，一般是数据字典值,32位ID")
    @Length(max=32,message = "业务文件类型超长，最多32个字符")
    private String businessFileType;


    public SysFileManageForm(){}

    /**
     *
     * @param fileName   文件名
     * @param systemId  系统ID或者AppId
     * @param remark    备注
     * @param interfaceNum  接口号
     * @param ids   待激活文件ID
     */
    public SysFileManageForm(String fileName,String systemId,String remark,String interfaceNum,String ids){
        this.fileName = fileName;
        this.systemId = systemId;
        this.remark = remark;
        this.interfaceNum = interfaceNum;
        this.ids = ids;
    }
}
