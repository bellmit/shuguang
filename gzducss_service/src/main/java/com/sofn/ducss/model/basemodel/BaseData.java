package com.sofn.ducss.model.basemodel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BaseData {
    private String id;
    private String baseDataTypeId;
    private String baseDataName;
    private String baseDataCode;
}
