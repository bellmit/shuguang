package com.sofn.fdpi.sysapi.bean;

import lombok.Data;

@Data
public class ActContextVo {
    private long id;

    private String dataFieldId;

    private String value;

    private long procInstId;

    private long parentId;
}
