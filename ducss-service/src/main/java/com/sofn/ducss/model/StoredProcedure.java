package com.sofn.ducss.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("农户分散利用量填报基础表")
public class StoredProcedure implements Serializable {
    private static final long serialVersionUID = -2553061913902720882L;
    private String id;

    private String strawTypeData;

    private String yearData;

    private String areaIdData;

    private String isCall;
}