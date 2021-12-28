package com.sofn.agzirdd.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class WelcomeSpeciesVo {

    @ApiModelProperty(value = "物种类型")
    private String speciesType;

    @ApiModelProperty(value = "物种名称")
    private List<String> speciesNames;

    public WelcomeSpeciesVo(){
        this.speciesType = "";
        this.speciesNames = new ArrayList<>();
    }

    public WelcomeSpeciesVo(String speciesType) {
        this.speciesType = speciesType;
        this.speciesNames = new ArrayList<>();
    }

}
