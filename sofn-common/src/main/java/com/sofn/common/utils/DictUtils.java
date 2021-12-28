package com.sofn.common.utils;

import com.sofn.common.dao.DictDao;
import com.sofn.common.enums.CommonEnum;
import com.sofn.common.model.Dict;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * 字典查询工具类
 */
public class DictUtils {

    private static DictDao dictDao = SpringContextHolder.getBean(DictDao.class);
    private static RedisHelper redisHelper = SpringContextHolder.getBean(RedisHelper.class);

    /**
     * 根据type和key获取字典
     * @param type 字典类型
     * @param key 字典标签
     * @return 字典
     */
    public static Dict getByTypeAndKey(String type, String key) {
        List<Dict> list =  getByType(type);
        if (list != null && list.size()>0){
            for (Dict dict : list) {
                if (dict == null){
                  continue;
                }

                if (key.equals(dict.getDictCode())) {
                    return dict;
                }
            }
        }

        return null;
    }

    /**
     * 根据type和value获取字典
     * @param type 字典类型
     * @param value 字典值
     * @return 字典
     */
    public static Dict getByTypeAndValue(String type, String value) {
        List<Dict> list =  getByType(type);
        if (list != null && list.size()>0){
            for (Dict dict : list) {
                if (dict == null){
                  continue;
                }

                if (value.equals(dict.getDictName())) {
                    return dict;
                }
            }
        }

        return null;
    }

    /**
     * 根据type获取字典
     * @param type 字典类型
     * @return 字典列表
     */
    public static List<Dict> getByType(String type) {
        if (StringUtils.isBlank(type)){
            return null;
        }

        List<Dict> list = getCacheByType(type);
        if (list == null || list.size()<=0) {
            list = dictDao.getByType(type);
            if (list != null && list.size()>0){
                cacheByType(type, list);
            }
        }

        return list;
    }

    /**
     * 根据字典类型获取缓存
     * @param type 字典类型
     * @return 字典列表
     */
    private static List<Dict> getCacheByType(String type){
        if (!StringUtils.isBlank(type)){
            Object object = redisHelper.hget(CommonEnum.SYS_DICT_CACHE_KEY.getCode(), type);
            if (object != null) {
                return JsonUtils.json2List(object.toString(), Dict.class);
            }
        }
        return null;
    }

    /**
     * 按类型缓存字典
     */
    private static void cacheByType(String type, List<Dict> dictList) {
        if (dictList != null && dictList.size()>0) {
            String listToStr = JsonUtils.obj2json(dictList);
            redisHelper.hset(CommonEnum.SYS_DICT_CACHE_KEY.getCode(), type, listToStr);
        }
    }

    /**
     * 根据type删除字典缓存
     * @param type 字典类型
     */
    public static void deleteCacheByType(String type){
        if (!StringUtils.isBlank(type)){
            redisHelper.hdel(CommonEnum.SYS_DICT_CACHE_KEY.getCode(), type);
        }
    }

    /**
     * 清空全部字典缓存
     */
    public static void clearAllCache(){
        redisHelper.del(CommonEnum.SYS_DICT_CACHE_KEY.getCode());
    }

}
