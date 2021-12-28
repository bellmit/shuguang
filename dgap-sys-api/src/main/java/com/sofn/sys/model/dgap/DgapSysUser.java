package com.sofn.sys.model.dgap;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 共享系统的用户
 */
@Data
public class DgapSysUser implements Serializable {
    private static final long serialVersionUID = 2559494226792129666L;

    public  DgapSysUser(){
        this.reservedField1="0";
        this.delFlag="N";
    }
    private String id;
    private String remark;
    private String createBy;
    private Date createTime;
    private String updateBy;
    private Date updateTime;
    private String delFlag;
    private String account;
    @JSONField(serialize = false)
    private String password;

    private String userName;

    private String deptId;
    private String organizationId;
    private String userType;

    private String roleId;

    private String postId;
    private String phone;

    private String email;

    private String status;

    private String reservedField1;

    private String reservedField2;
    private Date passwordValidatePeriod;
}
