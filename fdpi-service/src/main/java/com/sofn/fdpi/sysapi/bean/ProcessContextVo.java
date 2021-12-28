package com.sofn.fdpi.sysapi.bean;

import lombok.Data;

@Data
public class ProcessContextVo {
    private long id;

    private String dataFieldId;

    private String value;

    private long parentId;
}
