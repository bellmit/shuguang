package com.sofn.flow.util;

import org.apache.commons.lang.StringUtils;

/**
 * @author heyongjie
 * @date 2020/5/20 18:02
 */
public class BooleanUtil {

    public final static String TRUE = "Y";

    public final static String FALSE = "N";

    /**
     * 是否是TRUE
     * @param state   状态
     * @return  true false
     */
    public static boolean isTrue(String state){
        if(StringUtils.isBlank(state)){
            return false;
        }
        if(TRUE.equals(state)){
            return true;
        }
        return false;
    }
}
