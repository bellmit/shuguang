package com.sofn.fdpi.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-01-07 14:22
 */
@ApiModel
@Data
public class UserCompanyInfoVo {
    //     
    @ApiModelProperty("用户id")
    private  String id;
    //    公司地址
    @ApiModelProperty("公司地址")
    private  String local;
    //    公司名字
    @ApiModelProperty("公司名字")
    private  String compName;
    //    账户
    @ApiModelProperty("账号")
    private  String account;
    //    密码
    @ApiModelProperty("密码")
    private String  passWord;
    //    用户状态 0停用1启用
    @ApiModelProperty("用户状态 0停用1启用")
    private  String userStatus;
    //    邮件
    @ApiModelProperty("邮件")
    private  String  email;
    //    联系人
    @ApiModelProperty("联系人")
    private  String contacts;
    //    电话
    @ApiModelProperty("电话")
    private  String  phone;
    //   法人代表
    @ApiModelProperty("法人代表")
    private String legal;
//    通讯地址
    @ApiModelProperty("通讯地址")
    private String contactAddress;
//  邮政编码
    @ApiModelProperty("邮政编码")
    private String postAddress;
}
