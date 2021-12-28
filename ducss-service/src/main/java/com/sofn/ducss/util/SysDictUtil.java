package com.sofn.ducss.util;

import com.sofn.common.utils.SpringContextHolder;
import com.sofn.ducss.enums.Constants;
import com.sofn.ducss.sysapi.SysApi;
import com.sofn.ducss.sysapi.bean.SysDict;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 支撑系统字典相关工具类
 */
public class SysDictUtil {

    private static SysApi sysApi = SpringContextHolder.getBean(SysApi.class);

    /**
     * 根据字典类型，获取字典列表
     * @param dictType
     * @return
     */
    public static List<SysDict> getDictList(String dictType) {
        List<SysDict> dictList = sysApi.getDictListByType(dictType).getData();
        if(dictList==null)
            dictList = new ArrayList();

        return dictList;
    }

    /**
     * 根据字典类型，获取字典链表
     * @param dictType
     * @return
     */
    public static Map<String,SysDict> getDictMap(String dictType){
        List<SysDict> dictList = getDictList(dictType);
        Map<String, SysDict> dictMap = new HashMap();
        if(dictList.size()>0)
            dictMap = dictList.stream().collect(Collectors.toMap(d -> d.getDictcode(),d->d));
        return dictMap;
    }
}
