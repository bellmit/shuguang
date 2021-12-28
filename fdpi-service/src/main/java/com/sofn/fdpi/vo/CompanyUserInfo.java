package com.sofn.fdpi.vo;


import lombok.Data;

/**
 * @Description: 用于直属查看企业信息
 * @Auther: xiaobo
 * @Date: 2020-01-06 15:29
 */
@Data
public class CompanyUserInfo {
//     用户id
    private  String id;
//    公司地址
    private  String local;
//    公司名字
    private  String compName;
//    账户
    private  String account;
//    密码
    private String  passWord;
//    用户状态 0停用1启用
    private  String userStatus;
//    邮件
    private  String  email;
//    联系人
    private  String contacts;
//    电话
    private  String  phone;


}
