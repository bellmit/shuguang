package com.sofn.fyrpa.enums;

import lombok.Getter;

@Getter
public enum DeleteEnum {
    DEL_FLAG_Y("Y","已删除"),
    DEL_FLAG_N("N","未删除");



    private String key;
    private String val;

    private DeleteEnum(String key,String val){
        this.key = key;
        this.val = val;
    }
}
