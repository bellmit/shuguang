package com.sofn.infroflow.util;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author heyongjie
 * @date 2020/5/12 17:15
 */
public class IdUtil {


    /**
     * 将ID 拼接为'ID'
     *
     * @param ids 需要拼接的ID集合
     * @return List<String>
     */
    public static List<String> getId(Set<String> ids) {
        if (ids == null || ids.size() <= 0) {
            return new ArrayList<>();
        }

        List<String> ids2 = new ArrayList<>();
        for (String id : ids) {
            if (!StringUtils.isBlank(id)) {
                ids2.add("'" + id + "'");
            }
        }
        return ids2;
    }

    /**
     * 将ID 使用逗号分隔
     *
     * @param collect 集合
     * @return String
     */
    public static String getStrId(List<String> collect) {
        StringBuilder stringBuffer = new StringBuilder();

        if (collect != null && collect.size() > 0) {
            for (int i = 0; i < collect.size(); i++) {
                stringBuffer.append(collect.get(i));
                if (i != collect.size() - 1) {
                    stringBuffer.append(",");
                }
            }

            return stringBuffer.toString();
        }
        return "";
    }

    /**
     * 获取in后面的信息
     *
     * @param ids id,id
     * @return 'id','id'
     */
    public static String getSqlId(Set<String> ids) {
        if (ids == null || ids.size() <= 0) {
            return "";
        }

        List<String> id = getId(ids);
        return getStrId(id);
    }

    public static void main(String[] args) {
        Set<String> strings = new HashSet<>();
        strings.add("1");
        strings.add("2");
        System.out.println(IdUtil.getSqlId(strings));

    }

}
