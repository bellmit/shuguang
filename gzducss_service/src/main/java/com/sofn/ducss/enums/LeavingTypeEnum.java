package com.sofn.ducss.enums;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

@AllArgsConstructor
@Getter
public enum LeavingTypeEnum {

    /**
     * 离田运输方式
     */
    FARMER("FARMER", "农民自运"),
    GOVERNMENT("GOVERNMENT", "政府统筹");
    private String code;
    private String description;

    public static String getTypes(List<String> types) {
        List<String> resultList = Lists.newArrayList();
        LeavingTypeEnum[] values = LeavingTypeEnum.values();
        for (LeavingTypeEnum value : values) {
            if (types.contains(value.getCode())) {
                resultList.add(value.getDescription());
            }
        }
        return StringUtils.join(resultList, ",");
    }
}
