package com.sofn.ducss.util;

import com.sofn.ducss.enums.RegionEnum;
import org.apache.commons.lang.enums.EnumUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.List;


/**
 * @ClassName EnumUtil
 * @Description 枚举类帮助类，判断某个值是否在枚举中存在
 * @Author Administrator
 * @Date2020/11/30 15:21
 * Version1.0
 **/
public class EnumUtil {

    private static final Logger logger = LoggerFactory.getLogger(EnumUtil.class);

    /**
     * 判断某个枚举是否包某个code值
     * @param enumClass 需要判断是否存在那个枚举类中
     * @param code 需要判断的值
     * @return 包含返回true，否则返回false
     */
    public static boolean isInclude(Class enumClass, String code){
        List enumList = EnumUtils.getEnumList(enumClass);
        for (int i = 0;i<enumList.size(); i++){
            Object en = enumList.get(i);
            Class<?> enClass = en.getClass();
            try {
                Method method = enClass.getMethod("getCode"); // 需要与枚举类方法对应
                Object invoke = method.invoke(en, null);
                if(code.equals(invoke.toString())) {
                    return true;
                }
            }catch (Exception e){
                logger.error("枚举执行getCode方法失败...");
            }
        }
        return false;
    }

    public static void main(String[] args) {
        boolean include1 = isInclude(RegionEnum.class, "110000");
        System.out.println(include1); // true

        boolean include2 = isInclude(RegionEnum.class, "120000");
        System.out.println(include2); // false
    }
}

