package com.sofn.common.utils;

import org.apache.commons.lang.StringUtils;

public class BoolUtils {
    public static final String Y = "Y";
    public static final String N = "N";

    public static boolean isTrue(String value){
        return !StringUtils.isBlank(value) && Y.equals(value);
    }

    public static boolean isFalse(String value){
        return !StringUtils.isBlank(value) && N.equals(value);
    }

}
