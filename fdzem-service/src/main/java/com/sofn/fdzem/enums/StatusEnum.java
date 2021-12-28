package com.sofn.fdzem.enums;

import lombok.Getter;


@Getter
public enum StatusEnum {

    STATUS_ZERO(0,"站点填报者未上报"),
    STATUS_ONE(1,"站点填报者已上报/站点审核者待审核"),
    STATUS_TWO(2,"站点填报者已通过/站点审核者未上报(未向区域上报)"),
    STATUS_THREE(3,"站点审核者已驳回/站点填报者被驳回"),
    STATUS_FOUR(4,"站点审核者已上报/区域待审核"),
    STATUS_FIVE(5,"区域已通过/区域未上报(未向总站上报)"),
    STATUS_SIX(6,"区域已驳回/站点审核者被驳回"),
    ZERO(123,"0"),
    N(321,"N"),
    Y(222,"Y"),
    INDEX(9,"二级指标");

    private Integer code;
    private String description;

    StatusEnum(Integer code, String description){
        this.code = code;
        this.description = description;
    }
}
