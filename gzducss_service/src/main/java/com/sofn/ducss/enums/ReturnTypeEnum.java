package com.sofn.ducss.enums;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

@AllArgsConstructor
@Getter
public enum ReturnTypeEnum {

    /**
     * 还田方式
     */
    DEEP_TURN("DEEP_TURN", "深翻还田"),
    NO_TILLAGE("NO_TILLAGE", "免耕还田"),
    HEAP_RETTING("HEAP_RETTING", "堆沤还田");
    private String code;
    private String description;

    public static String getTypes(List<String> types) {
        List<String> resultList = Lists.newArrayList();
        ReturnTypeEnum[] values = ReturnTypeEnum.values();
        for (ReturnTypeEnum value : values) {
            if (types.contains(value.getCode())) {
                resultList.add(value.getDescription());
            }
        }
        return StringUtils.join(resultList, ",");
    }
}
