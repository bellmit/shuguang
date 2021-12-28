package com.sofn.core.support.cache.jedis;

/**
 * Created by wangzg on 2016/12/27.
 */
public interface JedisClient {
    public String set(String key, String value);

    public String get(String key);

    public Long hset(String key, String item, String value);

    public String hget(String key, String item);

    public Long hdel(String key, String item);

    public Long incr(String key);

    public Long decr(String key);


    /**
     * @param key
     * @param second
     * @return
     * @Description: 设置存存活时间
     * @author leechenxiang
     * @date 2016年4月27日 下午4:34:35
     */
    public Long expire(String key, int second);

    /**
     * @param key
     * @return 秒
     * <p>
     * >= 0     剩余秒数
     * <p>
     * = -1    永久存活
     * <p>
     * = -2    已经消除
     * @Description: 判断key多久过期
     * @author leechenxiang
     * @date 2016年4月27日 下午4:34:22
     */
    public Long ttl(String key);

}
