package com.sofn.ducss.model.basemodel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Dict {
    private String id;
    private String dictTypeId;
    private String dictName;
    private String dictCode;
}
