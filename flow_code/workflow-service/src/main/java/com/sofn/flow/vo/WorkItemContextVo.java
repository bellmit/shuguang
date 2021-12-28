package com.sofn.flow.vo;

import lombok.Data;

@Data
public class WorkItemContextVo {
    private long id;

    private String dataFieldId;

    private String value;

    private long procInstId;

    private long parentId;
}
