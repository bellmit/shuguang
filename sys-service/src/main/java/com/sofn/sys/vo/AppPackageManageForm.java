package com.sofn.sys.vo;

import com.sofn.sys.model.AppPackageManage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;

/**
 * @author heyongjie
 * @date 2019/11/18 13:57
 */
@Data
@ApiModel(value="上传APP管理，更新时只能更改更新描述和状态")
public class AppPackageManageForm {

    @ApiModelProperty(value = "产品ID")
    @Length(max = 50)
    private String id;

    @ApiModelProperty(value = "产品名称",required = true)
    @NotBlank(message = "产品名称不能为空")
    @Length(max = 50)
    private String productName;

    @ApiModelProperty(value = "平台,添加的时候必传,android,ios",required = true)
    @NotBlank(message = "平台不能为空")
    @Length(max = 10)
    private String platform;

    @ApiModelProperty(value = "版本号",required = true)
    @NotBlank(message = "版本号不能为空")
    @Length(max = 10)
    private String version;

    @ApiModelProperty(value = "状态,1 最新版本,2 提示升级,3 强制升级,4 不提示升级",required = true)
    @NotBlank(message = "状态不能为空")
    @Length(max = 2)
    private String state;

    @ApiModelProperty(value = "更新描述")
    @Length(max = 100)
    private String updateDescription;

    @ApiModelProperty(value = "App包上传成功后获取的文件ID")
    @NotBlank(message = "请输入App包对应的文件ID")
    @Length(max = 32,message = "文件ID超长")
    private String sysFileManageId;

    @ApiModelProperty(value = "整数版本号，适用于android平台，后台自动维护")
    private int intVersion;

    /**
     * 获取App管理持久层对象
     * @param appPackageManageForm   App管理表单对象
     * @return AppPackageManage  App管理持久层对象
     */
    public static AppPackageManage getAppPackageManage(AppPackageManageForm appPackageManageForm){
        AppPackageManage appPackageManage = new AppPackageManage();
        BeanUtils.copyProperties(appPackageManageForm,appPackageManage);
        return appPackageManage;
    }

}
