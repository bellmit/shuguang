package com.sofn.ducss.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sofn.common.exception.SofnException;
import com.sofn.common.utils.JsonUtils;
import com.sofn.common.utils.PageUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ServiceHelpUtil {

    /**
     * 把List中的元素统一转换为另一种对象
     * @param tarClass
     * @param srcList
     * @param <T>
     * @return
     */
    public static<T> List<T> convertList(Class<T> tarClass, List srcList) {
        List<T> list = new ArrayList<>();
        try {

            for (Object o : srcList) {
                T t = tarClass.newInstance();
                BeanUtils.copyProperties(o,t);
                list.add(t);
            }
            return list;
        } catch (InstantiationException e) {
            e.printStackTrace();
            throw new SofnException(e.getMessage());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new SofnException(e.getMessage());
        }
    }

    /**
     * 检查flag ,如果为false,则抛出异常信息
     * @param flag
     * @param errMsg
     */
    public static void checkFlag(boolean flag, String errMsg) {
        if (!flag) {
            throwException(errMsg);
        }
    }

    /**
     * 抛出sofn异常
     * @param errMsg
     */
    public static void throwException(String errMsg) {
        throw new SofnException(errMsg);
    }


    /**
     * 组装返回分页数据
     * @param page
     * @param <T>
     * @return
     */
    public static<T> PageUtils<T> assemblySofnPage(IPage<T> page){
        PageUtils<T> res = new PageUtils();
        res.setCurrPage(Long.valueOf(page.getCurrent()).intValue());
        res.setList(page.getRecords());
        res.setPageSize(Long.valueOf(page.getSize()).intValue());
        res.setTotalCount(Long.valueOf(page.getTotal()).intValue());
        res.setTotalPage(Long.valueOf(page.getPages()).intValue());

        return res;
    }


    /**
     * 判断是否不为空，如果为空，或者没有值，则抛出异常
     * @param zzyjbqkNo
     * @param errMsg
     */
    public static void checkNotNull(String zzyjbqkNo, String errMsg) {
        if(zzyjbqkNo==null || StringUtils.isEmpty(zzyjbqkNo))
            throwException(errMsg);
    }

    /**
     * 从类中获取值
     * @param obj
     * @param getMethodName
     * @param t
     * @param <T>
     * @return
     */
    public static <T> T getValByObj(Object obj,String getMethodName,Class<T>  t) {
        try {
            Method getMethod = obj.getClass().getMethod(getMethodName);
            Object val  = getMethod.invoke(obj);
            if(val!=null){
                return t.cast(val);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 过滤字符串中的数字为上报年度
     */
    public static String getSbndFromStr(String str) {
        if (StringUtils.hasText(str)) {
            char[] chars = str.toCharArray();
            StringBuilder sb = new StringBuilder();
            for (char c : chars) {
                if(sb.length()==4)
                    break;
                if (Character.isDigit(c)) {
                    sb.append(c);
                }
            }
            return sb.toString();
        }
        return "";
    }

}



