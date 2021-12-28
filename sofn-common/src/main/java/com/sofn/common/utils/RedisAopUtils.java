package com.sofn.common.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sofn.common.exception.SofnException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * Redis切面工具
 *
 * @author heyongjie
 * @date 2020/1/9 16:07
 */
@Slf4j
public class RedisAopUtils {

    /**
     * 数组表达式开始
     */
    private static final String ARR_OPEN = "[";

    /**
     * 数组表达式结束
     */
    private static final String ARR_CLOSE = "]";

    /**
     * 获取集合中的所有
     */
    private static final String ARR_ALL = "*";

    /**
     * 获取由参数名称构建的Map
     *
     * @param paramNames  参数名称
     * @param paramValues 参数值
     * @return    Map<String, String>
     */
    public static Map<String, String> getParamMap(String[] paramNames, Object[] paramValues) {
        Map<String, String> maps = Maps.newHashMap();
        if (paramNames != null && paramNames.length != 0 && paramValues != null && paramValues.length != 0) {
            if (paramNames.length != paramValues.length) {
                log.error("paramNams={}", JsonUtils.obj2json(paramNames));
                log.error("paramValues={}", JsonUtils.obj2json(paramValues));
                throw new SofnException("获取参数Map异常");
            }
            for (int i = 0; i < paramValues.length; i++) {
                maps.put(paramNames[i], JsonUtils.obj2json(paramValues[i]));
            }
        }
        return maps;
    }


    public static void main(String[] args) {
        // 测试情况1   参数为List<基本类型> 如List<String>    通过测试
        Map<String, String> values = Maps.newHashMap();
        /*values.put("aaa", JsonUtils.obj2json(Lists.newArrayList("1", 2, "3", "4")));
        List<String> aaa = getValue(values, "aaa[*]");
        System.out.println(aaa);*/

        // 测试情况2  参数为基本类型 如String    通过测试
       /*values.put("aaa",JsonUtils.obj2json("aaa"));
        values.put("bbb",JsonUtils.obj2json(null));
        values.put("ccc",JsonUtils.obj2json(""));
        List<String> aaa = getValue(values, "aaa");
        System.out.println(aaa);*/


        // 测试情况3(注意如果为对象key一定要是aaa.aaa格式)  参数为对象  测试通过，如果键不存在返回""
        /*SysRegion sysRegion = new SysRegion();
        sysRegion.setId("id");
        sysRegion.setRemark("remark");
        values.put("aaa",JsonUtils.obj2json(sysRegion));
        List<String> aaa = getValue(values, "aaa.id");
        System.out.println(aaa);*/

        // 测试情况4  参数为List<对象>
      /* List<SysRegion> sysRegionList = Lists.newArrayList();
        SysRegion sysRegion = new SysRegion();
        sysRegion.setId("id");
        sysRegion.setRemark("remark");
        SysRegion sysRegion2 = new SysRegion();
        sysRegion2.setId("id2");
        sysRegion2.setRemark("remark2");
        sysRegionList.add(sysRegion);
        sysRegionList.add(sysRegion2);
        values.put("aaa", JsonUtils.obj2json(sysRegionList));
        List<String> aaa = getValue(values, "aaa[*].id");
        System.out.println(aaa);*/


    }

    /**
     * 从Map中获取值    Map的值不支持集合中还包集合   如List<List<String>>不支持 List<Object>  Object中还有List也不支持
     *
     * @param values  Map<String,String>   值为使用JsonUtils.obj2json(sysRegionList)转换后的
     * @param keyword keywords
     * @return 符合keyword的值
     */
    public static List<String> getValue(Map<String, String> values, String keyword) {
        if (!CollectionUtils.isEmpty(values) && !StringUtils.isBlank(keyword)) {
            String[] keywords = getAttr(keyword);
            if (keywords != null && keywords.length > 0) {
                if (keywords.length == 1) {
                    // 1. 直接返回Map的Value
                    String key = getRealKey(keywords[0]);
                    Object obj = JsonUtils.json2obj(values.get(key));
                    if (obj == null) {
                        log.warn("key：{}对应的值为空", key);
                        return Lists.newArrayList("");
                    }
                    // 如果含有[]  表示是数组
                    if (keywords[0].contains(ARR_OPEN) && keywords[0].contains(ARR_CLOSE)) {
                        if (obj instanceof JSONArray) {
                            String expressionByKey = getExpressionByKey(keywords[0]);
                            if (ARR_ALL.equals(expressionByKey)) {
                                return ((JSONArray) obj).toJavaList(String.class);
                            } else {
                                int num = Integer.parseInt(expressionByKey);
                                Object result = ((JSONArray) obj).get(num);
                                return result == null ? Lists.newArrayList("") : Lists.newArrayList(result.toString());
                            }

                        }
                    } else {
                        return Lists.newArrayList(obj.toString());
                    }
                } else {
                    // 需要循环Map的Value，取出Value中的值
                    String key = getRealKey(keywords[0]);
                    Object obj = JsonUtils.json2obj(values.get(key));
                    // 只支持keywords中有一个集合
                    for (int i = 1; i < keywords.length; i++) {
                        String realKey = getRealKey(keywords[i]);
                        if (obj != null) {
                            if (obj instanceof JSONObject) {
                                obj = ((JSONObject) obj).get(realKey);
                            } else if (obj instanceof JSONArray) {
                                //  遍历所有的对象
                                List<String> jsonObjects = Lists.newArrayList();
                                // 获取前一个keyword里面的表达式
                                String expressionByKey = getExpressionByKey(keywords[i-1]);
                                // 从这里面取对象
                                if (ARR_ALL.equals(expressionByKey)) {
                                    ((JSONArray) obj).forEach((jsonArr) -> {
                                        if (jsonArr instanceof JSONObject) {
                                            jsonObjects.add(((JSONObject) jsonArr).get(realKey) == null ? "" : ((JSONObject) jsonArr).get(realKey).toString());
                                        }
                                    });
                                } else {
                                    int num = Integer.parseInt(expressionByKey);
                                    Object result = ((JSONArray) obj).get(num);
                                    return result == null ? Lists.newArrayList("") : Lists.newArrayList(result.toString());
                                }
                                return jsonObjects;
                            }
                        }
                    }
                    return obj == null ? Lists.newArrayList("") : Lists.newArrayList(obj.toString());
                }
            }
        }
        return null;
    }


    /**
     * 获取KEY里面的表达式  如KEY为  aaa[*]  那么返回*
     *
     * @param key KEY
     * @return key包含的表达式
     */
    private static String getExpressionByKey(String key) {
        if (StringUtils.isBlank(key)) {
            throw new SofnException("KEY 无效");
        }
        if (key.contains(ARR_OPEN) && key.contains(ARR_CLOSE)) {
            return key.substring(key.indexOf(ARR_OPEN) + 1, key.indexOf(ARR_CLOSE));
        }
        return "";
    }

    /**
     * 表达式
     * 获取真实的KEY 如KEY为aaa[1] || aaa[*]   返回aaa
     *
     * @param key 携带[]的KEY
     * @return 去掉[]后的KEY
     */
    private static String getRealKey(String key) {
        if (StringUtils.isBlank(key)) {
            throw new SofnException("key为空");
        }
        if (key.contains(ARR_OPEN) && key.contains(ARR_CLOSE)) {
            return key.split("\\[")[0];
        }
        return key;
    }

    /**
     * 根据关键字获取属性
     *
     * @param keyword 关键字
     * @return String[]
     */
    private static String[] getAttr(String keyword) {
        if (!StringUtils.isBlank(keyword)) {
            String[] split = keyword.split("\\.");
            if (split.length > 0) {
                return split;
            }
        }
        return null;
    }

}
