package com.sofn.ahhrm.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;


import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@ApiModel(value = "基础信息表单对象")
public class BaseinfoForm {

    @ApiModelProperty(value = "主键")
    private String id;
    @Length(min=1, max=64,message = "监测点名称字符数应介于1-64之间")
    @ApiModelProperty(value = "监测点名称")
    private String pointName;

    @ApiModelProperty(value = "省", example = "510000")
    @NotBlank(message = "省级代码不能为空")
    private String province;

    @ApiModelProperty(value = "市", example = "510100")
    @NotBlank(message = "市级代码不能为空")
    private String city;

    @NotBlank(message = "县级代码不能为空")
    @ApiModelProperty(value = "县", example = "510107")
    private String county;

    @NotBlank
//    @Pattern(regexp = "^(\\+|-)?(?:180(?:(?:\\.0{1,6})?)|(?:[0-9]|[1-9][0-9]|1[0-7][0-9])(?:(?:\\.[0-9]{1,6})?))$",message = "经度范围应当介于0-180之间")
    @ApiModelProperty(value = "经度")
    private String longitude;

    @NotBlank
//    @Pattern(regexp = "^(\\+|-)?(?:90(?:(?:\\.0{1,6})?)|(?:[0-9]|[1-8][0-9])(?:(?:\\.[0-9]{1,6})?))$",message = "纬度范围应当介于0-90之间")
    @ApiModelProperty(value = "纬度")
    private String latitude;

    @NotBlank(message = "所属类型不能为空")
    @ApiModelProperty(value = "所属类型")
    private String type;

    @ApiModelProperty(value = "固定电话/手机")
    @NotBlank(message = "电话号码不能为空")
    @Pattern(regexp = "(^(1[3-9])\\d{9}$)|(0\\d{2,3}-\\d{7,8})|(\\d{7,8})",message = "无效号码。格式如:1XXXXXXXXXX或XXXXXXX或XXX-XXXXXXX")
    private String tel;

    @Email(message = "请填写正确邮箱格式")
    @NotBlank
    @Pattern(regexp = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$",message = "邮箱格式不正确")
    @ApiModelProperty(value = "电子邮箱", example = "xxx@qq.com")
    private String email;

    @ApiModelProperty(value = "状态 0-保存  2-上报")
    private String status;

    @ApiModelProperty(value = "基础信息子表表单对象")
    @Valid
    private List<BaseinfoSubForm> baseinfoSubForms;
}
