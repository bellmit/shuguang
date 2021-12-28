package com.sofn.fdpi.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class CompAndUserVo implements Serializable {
    private String compName;
    private String userName;
}
