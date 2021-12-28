package com.sofn.ahhdp.util;

import com.sofn.common.exception.SofnException;
import org.springframework.util.StringUtils;

import java.util.regex.Pattern;

/**
 *
 **/
public class ValidatorUtil {

    /**
     * 验证整数（正整数和负整数）
     */
    public static final String REGEX_DIGIT = "\\d{4}";


    public static final String REGEX_CHINESE = "^[\u4E00-\u9FA5]+$";


    public static boolean checkDigit(String digit) {
        return Pattern.matches(REGEX_DIGIT, digit);
    }

    public static boolean checkChinese(String chinese) {
        return Pattern.matches(REGEX_CHINESE, chinese);
    }

    public static void validStrLength(String str, String strName, Integer length) {
        if (StringUtils.hasText(str)) {
            if (str.length() > length) {
                throw new SofnException(strName + "长度不能超" + length);
            }
        }
    }

}