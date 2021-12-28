package com.sofn.common.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.*;

/**
 * 递归去除请求参数前后空格
 * @author heyongjie
 * @date 2020/3/10 15:45
 */
@Slf4j
public class StringJsonUtils {


    /**
     * JsonString 转换成map
     *
     * @param jsonString JSON 字符串
     * @return Map<String, Object>
     */
    public static Map<String, Object> jsonStringToMap(String jsonString) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = Maps.newHashMap();
        try {
            map = mapper.readValue(jsonString,
                    new TypeReference<HashMap<String, Object>>() {
                    });
            Set<String> set = map.keySet();
            for (String key : set) {
                Object o = map.get(key);
                Object values;
                if (o instanceof String) {
                    // 如果是字符串直接取出空格后返回
                    values = map.get(key).toString().trim();
                } else {
                    // 这后面是一个对象
                    values = removeSpace(o);
                }
                map.put(key, values);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;
    }


    /**
     * 递归去除空格
     *
     * @return Object
     */
    private static Object removeSpace(Object oldValue) {
        if (oldValue != null) {
            Object value;
            ObjectMapper mapper = new ObjectMapper();
            if (oldValue instanceof Collection) {
                // 处理集合
                Collection collection = (Collection) oldValue;
                // 遍历集合
                if(CollectionUtils.isEmpty(collection)){
                    value = oldValue;
                }else{
                    List<Object> lists= Lists.newArrayList();
                    for (Object o : collection) {
                        String jsonString = JsonUtils.obj2json(o);
                        if(StringUtils.isBlank(jsonString)){
                            lists.add("");
                        }else{
                            try{
                                // 可以转换成Map说明是普通对象或者Map对象
                                mapper.readValue(jsonString,
                                        new TypeReference<HashMap<String, Object>>() {
                                        });

                                lists.add(removeSpace(o));
                            }catch (Exception e){
                                // 无法转就直接去空格后返回
                                lists.add(o.toString().trim());
                            }
                        }
                    }
                    value = lists;
                }
                return value;
            }
            // 普通对象或者Map
            String jsonString = JsonUtils.obj2json(oldValue);
            try {
                // 所有属性
                Map<String, Object> map = mapper.readValue(jsonString,
                        new TypeReference<HashMap<String, Object>>() {
                        });
                Set<String> set = map.keySet();
                // 遍历每个属性分别处理
                for (String key : set) {
                    Object o = map.get(key);
                    Object values;
                    if (o instanceof String) {
                        // 如果是字符串直接取出空格后返回
                        values = map.get(key).toString().trim();
                    } else {
                        // 这后面是一个对象
                        values = removeSpace(map.get(key));
                    }
                    map.put(key, values);
                }
                return map;
            } catch (IOException e) {
                // 如果转换异常直接返回原始值不去除空格
                return oldValue;
            }
        }
        return oldValue;
    }


}
