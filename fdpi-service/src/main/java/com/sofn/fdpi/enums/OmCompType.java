package com.sofn.fdpi.enums;

import com.sofn.common.exception.SofnException;
import lombok.Getter;

/**
 * @Description 欧鳗企业类型
 * @Author wg
 * @Date 2021/5/14 13:34
 **/
@Getter
public enum OmCompType {
    IMPORT("欧鳗进口企业", 1),
    BREED("欧鳗养殖企业", 2),
    PROC("欧鳗加工企业", 3),
    ;
    private String compType; //公司的类型
    private Integer code;

    OmCompType(String compType, Integer code) {
        this.compType = compType;
        this.code = code;
    }

    public String getCompType() {
        return compType;
    }

    public Integer getCode() {
        return code;
    }

    //code转compType
    public static String getCompTypeByCode(Integer code) {
        OmCompType[] values = OmCompType.values();
        for (OmCompType value : values) {
            if (value.getCode().equals(code)) {
                return value.getCompType();
            }
        }
        throw new SofnException("转换失败");
    }
}
