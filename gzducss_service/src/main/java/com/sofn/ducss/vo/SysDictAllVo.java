package com.sofn.ducss.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "下拉菜单")
public class SysDictAllVo {
    private String id;
    private String title;
    private String value;
}
