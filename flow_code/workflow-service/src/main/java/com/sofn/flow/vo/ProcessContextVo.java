package com.sofn.flow.vo;

import lombok.Data;

@Data
public class ProcessContextVo {
    private long id;

    private String dataFieldId;

    private String value;

    private long parentId;
}
