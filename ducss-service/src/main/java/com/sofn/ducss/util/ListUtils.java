package com.sofn.ducss.util;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/***
 * @desc 集合工具类
 * @author xl
 * @date 2021/04/13 14:55
 */
public class ListUtils {

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

    public static List<String> splitToList(String str){
        if (StringUtils.isEmpty(str)){
            return null;
        }
        String[] array =str.split(",");
        return Arrays.asList(array);
    }


    public static List<String> springStringToList(String str){
        if (StringUtils.isEmpty(str)){
            return null;
        }
        return Arrays.asList(str.split(","));
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
        if (string.contains("/中国/") || string.contains("中国/")){
            String regionNames ="";
            if (string.contains("/中国/")){
                 regionNames = string.replaceAll("/中国/","");
            }else if (string.contains("中国/")){
                regionNames = string.replaceAll("中国/","");
            }
            if (regionNames.contains(strs)){
                buffer.append(regionNames);
            }else {
                buffer.append(regionNames).append("/").append(strs);
            }
        }
        return buffer.toString();
    }

    public static void main(String[] args) {
       // System.out.println(splitToArrayString("/行政区划/北京市"));
    }

}
