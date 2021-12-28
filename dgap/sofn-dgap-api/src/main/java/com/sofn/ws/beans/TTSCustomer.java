package com.sofn.ws.beans;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@SuppressWarnings("serial")
public class TTSCustomer {

    @Size(max = 255)
    private String userIdCode; //主体用户码

    @Size(max = 255)
    private String customerEntityIdCode; //客户主体身份码

    @Size(max = 255)
    private String customerUserIdCode; //客户主体用户码

    @Size(max = 64)
    @Pattern(regexp = "\\d+.\\d+.\\d+.\\d+")
    private String ipAddress; //IP地址

    @Size(max = 255)
    private String name; //企业名称

    @Size(max = 255)
    private String type; //客户类型

    @Size(max = 255)
    private String organizationType; //组织形式

    @Size(max = 255)
    private String legalRepresentative; //法人代表

    @Size(max = 64)
    private String userName; //用户名字

    @Size(max = 64)
    private String idCardNo; //身份证号码

    @Size(max = 255)
    private String phone; //电话

    @Size(max = 255)
    private String email; //邮箱

    @Size(max = 500)
    private String address; //地址

    @Size(max = 38)
    private String status; //状态

    @Size(max = 255)
    private String entityIdCode; //主体身份码

    public TTSCustomer(){

    }

    public String getUserIdCode() {
        return userIdCode;
    }

    public void setUserIdCode(String userIdCode) {
        this.userIdCode = userIdCode;
    }

    public String getCustomerEntityIdCode() {
        return customerEntityIdCode;
    }

    public void setCustomerEntityIdCode(String customerEntityIdCode) {
        this.customerEntityIdCode = customerEntityIdCode;
    }

    public String getCustomerUserIdCode() {
        return customerUserIdCode;
    }

    public void setCustomerUserIdCode(String customerUserIdCode) {
        this.customerUserIdCode = customerUserIdCode;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOrganizationType() {
        return organizationType;
    }

    public void setOrganizationType(String organizationType) {
        this.organizationType = organizationType;
    }

    public String getLegalRepresentative() {
        return legalRepresentative;
    }

    public void setLegalRepresentative(String legalRepresentative) {
        this.legalRepresentative = legalRepresentative;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEntityIdCode() {
        return entityIdCode;
    }

    public void setEntityIdCode(String entityIdCode) {
        this.entityIdCode = entityIdCode;
    }

}