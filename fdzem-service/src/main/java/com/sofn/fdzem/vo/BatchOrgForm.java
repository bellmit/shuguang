package com.sofn.fdzem.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class BatchOrgForm {

    private String appId;
    private List<String> ids;
}
