package com.sofn.ducss.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "δΈζθε")
public class SysDictAllVo {
    private String id;
    private String title;
    private String value;
}
