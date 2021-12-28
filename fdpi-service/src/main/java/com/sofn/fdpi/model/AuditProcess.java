package com.sofn.fdpi.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.common.model.BaseModel;
import lombok.Data;

import java.util.Date;


@Data
@TableName("AUDIT_PROCESS")
public class AuditProcess extends BaseModel<AuditProcess> {
    @TableId(value = "ID", type = IdType.UUID)
    private String id;
    //证书编号
    private String papersId;
    //意见
    private String advice;
    //状态
    private String status;
    //操作人id
    private String person;
    //人名或者机构名称
    private String personName;
    //操作时间
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date conTime;
    //申请单号
    private String applyNum;
}
