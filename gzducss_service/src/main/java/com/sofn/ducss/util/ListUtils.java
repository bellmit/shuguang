package com.sofn.ducss.util;

import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.util.*;

/***
 * @desc 集合工具类
 * @author xl
 * @date 2021/04/13 14:55
 */
public class ListUtils{

    /**
     * 集合拆分
     *
     * @param list     原集合
     * @param pageSize 子集合长度
     * @param <T>
     * @return
     */
    public static <T> List<List<T>> groupList(List<T> list, int pageSize) {
        List<List<T>> listGroup = new ArrayList<List<T>>();
        int listSize = list.size();
        for (int i = 0; i < listSize; i += pageSize) {
            if (i + pageSize > listSize) {
                pageSize = listSize - i;
            }
            List<T> newList = list.subList(i, i + pageSize);
            listGroup.add(newList);
        }
        return listGroup;
    }

    public static List<String> springStringToList(String str){
        if (StringUtils.isEmpty(str)){
            return null;
        }
        return Arrays.asList(str.split(","));
    }

    public static Map<String,Object> Obj2Map(Object obj){
        Map<String,Object> map=new HashMap<String, Object>();
        Field[] fields = obj.getClass().getDeclaredFields();
        for(Field field:fields){
            field.setAccessible(true);
           try {
               map.put(field.getName(), field.get(obj));
           }catch (Exception e){
               e.printStackTrace();
           }
        }
        return map;
    }

    public static List<String> splitToList(String str){
        if (StringUtils.isEmpty(str)){
            return null;
        }
        String[] array =str.split(",");
        return Arrays.asList(array);
    }

    public static String listToString(List<String> list){
        StringBuffer str = new StringBuffer();
        for (String item:list) {
            str.append(item).append(",");
        }
        return str.substring(0, str.length() -1);
    }
    public static boolean isEmpty(List list){
        if (list != null || list.size() >0 ){
            return false;
        }
        return true;
    }

    public static String splitToArrayString(String string, String strs){
        String[] str = string.split("/");
        StringBuffer buffer = new StringBuffer();
        if (string.contains("/行政区划/") || string.contains("/行政区划")){
            if (string.contains("/行政区划/")){
                buffer.append(string.replaceAll("/行政区划/",""));
            }else if (string.contains("/行政区划")){
                buffer.append(string.replaceAll("/行政区划",""));
            }
            if (buffer.toString().equals("")){
                buffer.append(strs);
            }else {
                buffer.append("/").append(strs);
            }
        }
        if (string.contains("中国/")){
            buffer.append(string.replaceAll("中国/",""));
        }
        return buffer.toString();
    }

    public static void main(String[] args) {
       List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");
        list.add("9");
        List<List<String>> lists = groupList(list, 5);

    }

}
