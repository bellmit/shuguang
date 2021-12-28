package com.sofn.common.excel.util;

import com.google.common.collect.Lists;
import com.sofn.common.dao.DictDao;
import com.sofn.common.model.Dict;
import com.sofn.common.utils.DictUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 字典工具类
 *
 * @author heyongjie
 * @date 2019/6/14 10:45
 */
public class ExcelDictUtil {

    /**
     * 获取字典中文注解
     *
     * @param dictType 字典类型
     * @param dictVal  字典值
     */
    public static String getDescription(String dictType, String dictVal) {
        Dict dict = DictUtils.getByTypeAndKey(dictType, dictVal);
        if (dict == null) {
            return dictVal;
        }
        return dict.getDictName();
    }

    /**
     * 获取某个类别的中文注解列表
     *
     * @param type 类别
     * @return 中文注解列表
     */
    public static List<String> getDescriptions(String type) {
        List<Dict> dicts = DictUtils.getByType(type);
        if (dicts == null) {
            return Lists.newArrayList();
        }
        return  dicts.stream().map(Dict::getDictName).collect(Collectors.toList());
    }

    /**
     * 根据Sql和参数获取值
     *
     * @param sql    Sql
     * @param params Sql参数
     * @return List<String>
     */
    public static List<String> getDesBySql(String sql, String[] params) {
        DictDao dictDao = new DictDao();
        return dictDao.getDictBySql(sql, params);
    }

    /**
     * 根据类型和中文注解获得字典值
     *
     * @param dictType    字典类型
     * @param description 中文说明
     * @return 字典值
     */
    public static String getValue(String dictType, String description) {
        Dict byTypeAndValue = DictUtils.getByTypeAndValue(dictType, description);
        if(byTypeAndValue != null){
            return byTypeAndValue.getDictCode();
        }else{
            return description;
        }
    }

}
