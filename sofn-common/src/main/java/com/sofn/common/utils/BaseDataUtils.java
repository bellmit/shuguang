package com.sofn.common.utils;

import com.sofn.common.dao.BaseDataDao;
import com.sofn.common.model.BaseData;
import com.sofn.common.model.BaseData;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 基础数据查询工具类
 */
public class BaseDataUtils {
    // 在redis缓存的hash key
    private static final String BASEDATA_CACHE_KEY = "basedata_cache_key";
    // 缓存时间
    private static final int BASEDATA_CACHE_EXPIRE = 24*60*60;

    private static BaseDataDao baseDataDao = SpringContextHolder.getBean(BaseDataDao.class);
    private static RedisHelper redisHelper = SpringContextHolder.getBean(RedisHelper.class);

    /**
     * 根据type和key获取基础数据
     * @param type 基础数据类型
     * @param key 基础数据标签
     * @return 基础数据
     */
    public static BaseData getByTypeAndKey(String appId, String type, String key) {
        List<BaseData> list =  getByType(appId,type);
        if (list != null && list.size()>0){
            for (BaseData baseData : list) {
                if (baseData == null) {
                    continue;
                }

                if (key.equals(baseData.getBaseDataCode())) {
                    return baseData;
                }
            }
        }

        return null;
    }

    /**
     * 根据type和value获取基础数据
     * @param type 基础数据类型
     * @param value 基础数据值
     * @return 基础数据
     */
    public static BaseData getByTypeAndValue(String appId, String type, String value) {
        List<BaseData> list =  getByType(appId, type);
        if (list != null && list.size()>0){
            for (BaseData baseData : list) {
                if (baseData == null) {
                    continue;
                }

                if (value.equals(baseData.getBaseDataName())) {
                    return baseData;
                }
            }
        }

        return null;
    }

    /**
     * 根据type获取基础数据
     * @param appId 系统appID
     * @param type 基础数据类型
     * @return 基础数据列表
     */
    public static List<BaseData> getByType(String appId, String type) {
        if (StringUtils.isBlank(type) || StringUtils.isBlank(appId)){
            return null;
        }

        List<BaseData> list = getCacheByType(appId, type);
        if (list == null || list.size()<=0) {
            list = baseDataDao.getByType(appId,type);
            if (list != null && list.size()>0){
                cacheByType(appId, type, list);
            }
        }

        return list;
    }

    /**
     * 根据type获取全部基础数据
     * @param type 基础数据类型
     * @return 基础数据列表
     */
    public static List<BaseData> getAllByType(String type) {
      if (StringUtils.isBlank(type)){
        return null;
      }

      List<BaseData> list = getCacheByType(null, type);
      if (list == null || list.size()<=0) {
        list = baseDataDao.getDataByType(type);
        if (list != null && list.size()>0){
          cacheByType(null, type, list);
        }
      }

      return list;
    }

    /**
     * 根据type获取未分配给子系统的基础数据
     * @param appId 系统appID
     * @param type 基础数据类型
     * @return 基础数据列表
     */
    public static List<BaseData> getNotInDataByType(String appId, String type) {
      return baseDataDao.getNotInDataByType(appId,type);
    }

    /**
     * 根据基础数据类型获取缓存
     * @param type 基础数据类型
     * @return 基础数据列表
     */
    private static List<BaseData> getCacheByType(String appId, String type){
        if (!StringUtils.isBlank(type)){
            String itemKey = null;
            if (StringUtils.isNotBlank(appId)){
              itemKey = String.format("basedata_%s_%s",appId,type);
            }else {
              itemKey = String.format("basedata_all_%s",type);
            }

            Object object = redisHelper.hget(BASEDATA_CACHE_KEY, itemKey);
            if (object != null) {
                return JsonUtils.json2List(object.toString(), BaseData.class);
            }
        }
        return null;
    }

    /**
     * 按类型缓存基础数据
     */
    public static void cacheByType(String appId, String type, List<BaseData> baseDataList) {
        if (StringUtils.isBlank(appId) || StringUtils.isBlank(type)){
            return;
        }

        if (!CollectionUtils.isEmpty(baseDataList)) {
            String itemKey = null;
            if (StringUtils.isNotBlank(appId)){
              itemKey = String.format("basedata_%s_%s",appId,type);
            }else {
              itemKey = String.format("basedata_all_%s",type);
            }

            String listToStr = JsonUtils.obj2json(baseDataList);
            redisHelper.hset(BASEDATA_CACHE_KEY, itemKey, listToStr, BASEDATA_CACHE_EXPIRE);
        }
    }

    /**
     * 根据type删除基础数据缓存
     * @param typeValue 基础数据类型
     */
    public static void deleteCacheByType(String typeValue){
        if (!StringUtils.isBlank(typeValue)) {
            String pattern = "*_"+typeValue;
            redisHelper.hdelByPattern(BASEDATA_CACHE_KEY, pattern);
        }
    }

    /**
     * 根据appid and type删除基础数据缓存
     * @param typeValue 基础数据类型
     */
    public static void deleteCacheByTypeAndAppId(String appId, String typeValue){
        if (!StringUtils.isBlank(typeValue)) {
            String itemKey = String.format("%s_%s",appId,typeValue);
            redisHelper.hdel(BASEDATA_CACHE_KEY, itemKey);
        }
    }

    /**
     * 清空全部基础数据缓存
     */
    public static void clearAllCache(){
        redisHelper.del(BASEDATA_CACHE_KEY);
    }
}
