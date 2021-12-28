package com.sofn.common.utils;

import org.apache.poi.ss.formula.functions.T;

import java.util.ArrayList;
import java.util.List;

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
