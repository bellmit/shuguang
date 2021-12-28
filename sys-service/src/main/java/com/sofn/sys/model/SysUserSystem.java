package com.sofn.sys.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableName;
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
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date UpdateTime;

    private String systemName;

    public SysUserSystem() {
    }
}
