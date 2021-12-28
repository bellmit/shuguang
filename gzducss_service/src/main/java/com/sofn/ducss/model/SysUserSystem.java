package com.sofn.ducss.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/*
 * 描述: <br>
 用户常用系统表
 * @Author: Luoyinghui
 * @Date: 2021-01-04 15:29
 */
@TableName("SYS_USER_SYSTEM")
@Data
public class SysUserSystem implements Serializable {
    private String id;

    private String userId;

    private String systemId;

    private String appId;

    private String viewUrl;

    @ApiModelProperty(name = "更新时间", value = "UpdateTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date UpdateTime;

    private String systemName;

    public SysUserSystem() {
    }
}
