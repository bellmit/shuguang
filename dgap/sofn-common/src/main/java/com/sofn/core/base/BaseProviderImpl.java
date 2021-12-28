package com.sofn.core.base;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sofn.core.Constants;
import com.sofn.core.constant.ApiConstants;
import com.sofn.core.util.DataUtil;
import com.sofn.core.util.InstanceUtil;
import com.sofn.core.util.RedisUtil;
import com.sofn.core.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 业务逻辑层基类<br/>
 * 继承基类后必须配置CacheConfig(cacheNames="")
 * 此类中所有的缓存为事件记录缓存,系统自动保存
 * 与业务相关的缓存需要开发人员手动添加注解
 * @param <T> the type parameter
 * @author sofn
 * @version 2016年5月20日 下午3:19:19
 */
public abstract class BaseProviderImpl<T extends BaseModel> implements BaseProvider<T> {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * The Mapper.
     */
    @Autowired
    BaseMapper<T> mapper;

    /**
     * 启动分页查询  @param params the params
     *
     * @param params the params
     */
    protected void startPage(Map<String, Object> params) {
        if (DataUtil.isEmpty(params.get("pageNum"))) {
            params.put("pageNum", 1);
        }
        if (DataUtil.isEmpty(params.get("pageSize"))) {
            params.put("pageSize", ApiConstants.DEFUALUT_PAGESIZE);
        }
        if (DataUtil.isEmpty(params.get("orderBy"))) {
            params.put("orderBy", "id desc");
        }
        PageHelper.startPage(params);
    }

    /**
     * Gets provider.
     *
     * @return the provider
     */
    @SuppressWarnings("unchecked")
    private BaseProviderImpl<T> getProvider() {
        return InstanceUtil.getBean(getClass());
    }

    /** 生成主键策略 */
    //public String createId(String key) {
    //    String redisKey = "REDIS_TBL_" + key;
    //    String dateTime = DateUtil.getDateTime(DateUtil.DATE_PATTERN.YYYYMMDDHHMMSSSSS);
    //    return dateTime + RedisUtil.incr(redisKey);
    //}

    /**
     * 主键生成策略 用32位 uuid 替换时间戳
     *
     * @param key the key
     * @return string string
     */
    public String createId(String key) {
        String redisKey = "REDIS_TBL_" + key;
        //return uuid + RedisUtil.incr(redisKey);
        //return UUID.randomUUID().toString().replace("-","");
        return StringUtils.getUUIDString();
    }

    /**
     * 根据Id查询(默认类型T)  @param ids the ids
     *
     * @param ids the ids
     * @return the page
     */
    public PageInfo<T> getPage(Page<String> ids) {
        if (ids != null) {
            Page<T> page = new Page<T>(ids.getPageNum(), ids.getPageSize());
            page.setTotal(ids.getTotal());
            page.clear();
            BaseProviderImpl<T> provider = getProvider();
            page.addAll(ids.stream().map(provider::queryById).collect(Collectors.toList()));
            return new PageInfo<T>(page);
        }
        return new PageInfo<T>();
    }

    /** 根据Id查询(cls返回类型Class) */
    public <K> PageInfo<K> getPage(Page<String> ids, Class<K> cls) {
        if (ids != null) {
            Page<K> page = new Page<K>(ids.getPageNum(), ids.getPageSize());
            page.setTotal(ids.getTotal());
            page.clear();
            BaseProviderImpl<T> provider = getProvider();
            for (String id : ids) {
                T t = provider.queryById(id);
                K k = InstanceUtil.to(t, cls);
                page.add(k);
            }
            return new PageInfo<K>(page);
        }
        return new PageInfo<K>();
    }

    /**
     * 根据Id查询(默认类型T)  @param ids the ids
     *
     * @param ids the ids
     * @return the list
     */
    public List<T> getList(List<String> ids) {
        List<T> list = InstanceUtil.newArrayList();
        if (ids != null) {
            for (String id : ids) {
                list.add(getProvider().queryById(id));
            }
        }
        return list;
    }

    /** 根据Id查询(cls返回类型Class) */
    public <K> List<K> getList(List<String> ids, Class<K> cls) {
        List<K> list = InstanceUtil.newArrayList();
        if (ids != null) {
            for (String id : ids) {
                T t = getProvider().queryById(id);
                K k = InstanceUtil.to(t, cls);
                list.add(k);
            }
        }
        return list;
    }

    @Transactional
    public void delete(String id, String userId) {
        try {
            T record = queryById(id);
            record.setDelFlag("Y");
            record.setUpdateTime(new Date());
            record.setUpdateBy(userId);
            mapper.updateByPrimaryKey(record);
            // 删除缓存
            //RedisUtil.del(getCacheKey(id));
            //RedisUtil.set(getCacheKey(id), record);
            //RedisUtil.set(getCacheKey(id, "delete"), record);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Transactional
    public T update(T record) {
        try {
            record.setUpdateTime(new Date());
            logger.debug("{}", record.getDelFlag());
            logger.debug("{}", record.getClass().getSimpleName());
            if (StringUtils.isBlank(record.getId())) {
                String key = record.getClass().getSimpleName();
                record.setId(createId(key));
                record.setCreateTime(new Date());
                record.setDelFlag("N");
                mapper.insert(record);
            } else {
                mapper.updateByPrimaryKey(record);
            }
            //RedisUtil.set(getCacheKey(record.getId()), record);
            //RedisUtil.set(getCacheKey(record.getId(), "update"), record);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return record;
    }

    @Transactional
    @SuppressWarnings("unchecked")
    public T queryById(String id) {
        try {
            logger.info("queryById(id:"+id+")");
            String key = getCacheKey(id);
            //String key = getCacheKey(id, "queryById");
            //T record = (T)RedisUtil.get(key);
            T  record = mapper.selectByPrimaryKey(id);
            if(record != null) {
                logger.debug(record.toString());
            }
            //if (record == null) {
            //    //RedisUtil.set(key, record);
            //}
            logger.info("end queryById(id:"+id+")");
            return record;
        } catch (Exception e) {
            logger.error("",e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public void init() {
        List<T> list = mapper.selectAll();
        for (T record : list) {
            //RedisUtil.set(getCacheKey(record.getId(), "init"), record);
            RedisUtil.set(getCacheKey(record.getId()), record);
        }
    }

    /**
     * 获取缓存键值  @param id the id
     *
     * @param id         the id
     * @return the cache key
     */
    private String getCacheKey(Object id) {
        String cacheName = null;
        CacheConfig cacheConfig = getClass().getAnnotation(CacheConfig.class);
        if (cacheConfig == null || cacheConfig.cacheNames() == null || cacheConfig.cacheNames().length < 1) {
            cacheName = getClass().getName();
        } else {
            cacheName = cacheConfig.cacheNames()[0];
        }
        return Constants.CACHE_NAMESPACE + cacheName + ":" + id;
    }

    //private String getCacheKey(Object id, String methodName) {
    //    String cacheName = null;
    //    CacheConfig cacheConfig = getClass().getAnnotation(CacheConfig.class);
    //    if (cacheConfig == null || cacheConfig.cacheNames() == null || cacheConfig.cacheNames().length < 1) {
    //        cacheName = getClass().getName();
    //    } else {
    //        cacheName = cacheConfig.cacheNames()[0];
    //    }
    //    return Constants.CACHE_NAMESPACE + methodName + ":" + cacheName + ":" + id;
    //}

    public PageInfo<T> query(Map<String, Object> params) {
        return null;
    }

}
