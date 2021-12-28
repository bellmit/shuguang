package com.sofn.flow.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public final class RedisHelper {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 指定缓存失效时间
     *
     * @param key  键
     * @param time 时间(秒)
     * @return true 成功
     */
    public boolean expire(String key, long time) {
        try {
            if (StringUtils.isEmpty(key)) {
                log.info("key is null，不进行下一步处理");
                return false;
            }
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据key 获取过期时间
     *
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效
     */
    public long getExpire(String key) {
        if (StringUtils.isEmpty(key)) {
            log.info("key is null，不进行下一步处理");
            return 0;
        }
        try {
            return redisTemplate.getExpire(key, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 判断key是否存在
     *
     * @param key 键
     * @return true 存在 false不存在
     */
    public boolean hasKey(String key) {
        try {
            if (StringUtils.isEmpty(key)) {
                log.info("key is null，不进行下一步处理");
                return false;
            }
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除缓存
     *
     * @param key 可以传一个值 或多个
     */
    @SuppressWarnings("unchecked")
    public void del(String... key) {
        if (key != null && key.length > 0) {
            try {
                if (key.length == 1) {
                    redisTemplate.delete(key[0]);
                } else {
                    redisTemplate.delete(CollectionUtils.arrayToList(key));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 删除模糊查询key中的缓存
     *
     * @param key 一个值
     */
    public void delLikeKey(String key) {
        if (key != null && key.length() > 0) {
            try {
                key = key + "*";
                Set<String> keys = redisTemplate.keys(key);
                redisTemplate.delete(keys);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // ============================String=============================

    /**
     * 普通缓存获取
     *
     * @param key 键
     * @return 值
     */
    public Object get(String key) {
        try {
            return StringUtils.isEmpty(key) ? null : redisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 普通缓存放入
     *
     * @param key   键
     * @param value 值
     * @return true成功 false失败
     */
    public boolean set(String key, Object value) {
        try {
            if (StringUtils.isEmpty(key)) {
                log.info("key is null，不进行下一步处理");
                return false;
            }
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 普通缓存放入并设置时间
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     */
    public boolean set(String key, Object value, long time) {
        try {

            if (StringUtils.isEmpty(key)) {
                log.info("key is null，不进行下一步处理");
                return false;
            }
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

  /**
   * 模糊搜索key
   * @param patternKey
   */
  public List<String> scan(String patternKey) {
      //需要匹配的key
      ScanOptions options = ScanOptions.scanOptions()
        //这里指定每次扫描key的数量
        .count(1000)
        .match(patternKey).build();

      RedisSerializer<String> redisSerializer = (RedisSerializer<String>) redisTemplate.getKeySerializer();
      Cursor cursor = (Cursor) redisTemplate.executeWithStickyConnection(redisConnection -> new ConvertingCursor<>(redisConnection.scan(options), redisSerializer::deserialize));
      List<String> result = new ArrayList<>();
      while(true){
        assert cursor != null;
        if (!cursor.hasNext()) {
          break;
        }

        result.add(Objects.requireNonNull(cursor.next()).toString());
      }

      try{
        //切记这里一定要关闭，否则会耗尽连接数。报Cannot get Jedis connection; nested exception is redis.clients.jedis.exceptions.JedisException: Could not get a
        cursor.close();
      }catch (Exception e) {
        e.printStackTrace();
      }

      return result;
    }

  /**
   * 根据key模糊删除
   * @param patternKey
   */
  public void delByPattern(String patternKey) {
      List<String> list = scan(patternKey);
      if (!CollectionUtils.isEmpty(list)) {
        redisTemplate.delete(list);
      }
    }


    /**
     * 递增
     *
     * @param key   键
     * @param delta 要增加几(大于0)
     * @return
     */
    public long incr(String key, long delta) {
        if (StringUtils.isEmpty(key)) {
            log.info("key is null，不进行下一步处理");
            return 0;
        }

        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        try {
            return redisTemplate.opsForValue().increment(key, delta);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

    }

    /**
     * 递减
     *
     * @param key   键
     * @param delta 要减少几(小于0)
     * @return
     */
    public long decr(String key, long delta) {
        if (StringUtils.isEmpty(key)) {
            log.info("key is null，不进行下一步处理");
            return 0;
        }

        if (delta < 0) {
            throw new RuntimeException("递减因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, -delta);
    }

    // ================================Map=================================

    /**
     * HashGet
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return 值
     */
    public Object hget(String key, String item) {
        if (StringUtils.isEmpty(key)) {
            log.info("key is null，不进行下一步处理");
            return null;
        }

        Object obj = null;
        try {
            obj = redisTemplate.opsForHash().get(key, item);
        } catch (Exception e) {
            log.error(ExceptionUtils.getFullStackTrace(e));
        }
        return obj;
    }

    /**
     * HashGet
     *
     * @param key  键 不能为null
     * @return 值
     */
    public Map<Object, Object> hgetall(String key) {
        if (StringUtils.isEmpty(key)) {
            log.info("key is null，不进行下一步处理");
            return null;
        }

        Map<Object, Object> map = null;
        try {
            map = redisTemplate.opsForHash().entries(key);
        } catch (Exception e) {
            log.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 获取hashKey对应的所有键值
     *
     * @param key 键
     * @return 对应的多个键值
     */
    public Map<Object, Object> hmget(String key) {
        if (StringUtils.isEmpty(key)) {
            log.info("key is null，不进行下一步处理");
            return null;
        }


        Map<Object, Object> maps = null;
        try {
            maps = redisTemplate.opsForHash().entries(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maps;
    }

    /**
     * HashSet
     *
     * @param key 键
     * @param map 对应多个键值
     * @return true 成功 false 失败
     */
    public boolean hmset(String key, Map<String, Object> map) {
        try {
            if (StringUtils.isEmpty(key)) {
                log.info("key is null，不进行下一步处理");
                return false;
            }
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * HashSet 并设置时间
     *
     * @param key  键
     * @param map  对应多个键值
     * @param time 时间(秒)
     * @return true成功 false失败
     */
    public boolean hmset(String key, Map<String, Object> map, long time) {
        try {
            if (StringUtils.isEmpty(key)) {
                log.info("key is null，不进行下一步处理");
                return false;
            }

            redisTemplate.opsForHash().putAll(key, map);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @return true 成功 false失败
     */
    public boolean hset(String key, String item, Object value) {
        try {
            if (StringUtils.isEmpty(key)) {
                log.info("key is null，不进行下一步处理");
                return false;
            }

            redisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @param time  时间(秒) 注意:如果已存在的hash表有时间,这里将会替换原有的时间
     * @return true 成功 false失败
     */
    public boolean hset(String key, String item, Object value, long time) {
        try {
            if (StringUtils.isEmpty(key)) {
                log.info("key is null，不进行下一步处理");
                return false;
            }
            redisTemplate.opsForHash().put(key, item, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除hash表中的值
     *
     * @param key  键 不能为null
     * @param item 项 可以使多个 不能为null
     */
    public void hdel(String key, Object... item) {
        if (StringUtils.isEmpty(key)) {
            log.info("key is null，不进行下一步处理");
        }
        redisTemplate.opsForHash().delete(key, item);
    }

    /**
     * 判断hash表中是否有该项的值
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return true 存在 false不存在
     */
    public boolean hHasKey(String key, String item) {
        if (StringUtils.isEmpty(key)) {
            log.info("key is null，不进行下一步处理");
            return false;
        }
        return redisTemplate.opsForHash().hasKey(key, item);
    }

    /**
     * hash递增 如果不存在,就会创建一个 并把新增后的值返回
     *
     * @param key  键
     * @param item 项
     * @param by   要增加几(大于0)
     * @return
     */
    public double hincr(String key, String item, double by) {
        if (StringUtils.isEmpty(key)) {
            log.info("key is null，不进行下一步处理");
            return 0;
        }
        return redisTemplate.opsForHash().increment(key, item, by);
    }

    /**
     * hash递减
     *
     * @param key  键
     * @param item 项
     * @param by   要减少记(小于0)
     * @return
     */
    public double hdecr(String key, String item, double by) {
        if (StringUtils.isEmpty(key)) {
            log.info("key is null，不进行下一步处理");
            return 0;
        }
        return redisTemplate.opsForHash().increment(key, item, -by);
    }

    /**
     * 通过正则表达式扫描hash中的key
     *
     * @param key
     * @param pattern
     * @return
     */
    public Map<Object, Object> hScan(String key, String pattern) {
      try {
        Cursor<Map.Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan(key, ScanOptions.scanOptions().match(pattern).build());
        Map<Object, Object> map = new HashMap<>();
        while (cursor.hasNext()) {
          Map.Entry<Object, Object> entry = cursor.next();
          if (entry != null) {
            map.put(entry.getKey(), entry.getValue());
          }
        }

        //关闭cursor
        cursor.close();

        return map;
      }catch (IOException e){
        e.printStackTrace();
      }

      return null;
    }

    /**
     * 获取hash表中元素的行数
     * @param key key
     * @return long
     */
    public long hLength(String key){
        if(StringUtils.isEmpty(key)){
            return 0;
        }
        return redisTemplate.opsForHash().size(key);
    }

    /**
     * 通过正则表达式删除hash中的元素
     *
     * @param key     key
     * @param pattern 正则表达式
     */
    public void hdelByPattern(String key, String pattern) {
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(pattern)) {
            log.info("key or pattern is null，not continue");
            return;
        }
        Map<Object, Object> keys = hScan(key, pattern);
        if (!CollectionUtils.isEmpty(keys)) {
            Object[] array = keys.keySet().toArray();
            hdel(key, array);
        }
    }

    // ============================set=============================

    /**
     * 根据key获取Set中的所有值
     *
     * @param key 键
     * @return
     */
    public Set<Object> sGet(String key) {
        try {
            if (StringUtils.isEmpty(key)) {
                log.info("key is null，不进行下一步处理");
                return null;
            }

            return redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

  /**
   * 根据key随机获取Set中的一个值
   *
   * @param key 键
   * @return
   */
  public Object sRandomGet(String key) {
    try {
      if (StringUtils.isEmpty(key)) {
        log.info("key is null，不进行下一步处理");
        return null;
      }

      return redisTemplate.opsForSet().randomMember(key);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

    /**
     * 根据value从一个set中查询,是否存在
     *
     * @param key   键
     * @param value 值
     * @return true 存在 false不存在
     */
    public boolean sHasKey(String key, Object value) {
        try {
            if (StringUtils.isEmpty(key)) {
                log.info("key is null，不进行下一步处理");
                return false;
            }

            return redisTemplate.opsForSet().isMember(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将数据放入set缓存
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public long sSet(String key, Object... values) {
        try {
            if (StringUtils.isEmpty(key)) {
                log.info("key is null，不进行下一步处理");
                return 0;
            }

            return redisTemplate.opsForSet().add(key, values);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 将set数据放入缓存
     *
     * @param key    键
     * @param time   时间(秒)
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public long sSetAndTime(String key, long time, Object... values) {
        try {
            if (StringUtils.isEmpty(key)) {
                log.info("key is null，不进行下一步处理");
                return 0;
            }

            Long count = redisTemplate.opsForSet().add(key, values);
            if (time > 0) {
                expire(key, time);
            }
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 获取set缓存的长度
     *
     * @param key 键
     * @return
     */
    public long sGetSetSize(String key) {
        try {
            if (StringUtils.isEmpty(key)) {
                log.info("key is null，不进行下一步处理");
                return 0;
            }

            return redisTemplate.opsForSet().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 移除值为value的
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 移除的个数
     */
    public long setRemove(String key, Object... values) {
        try {
            if (StringUtils.isEmpty(key)) {
                log.info("key is null，不进行下一步处理");
                return 0;
            }

            Long count = redisTemplate.opsForSet().remove(key, values);
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 随机弹出set集合中的一个value
     *
     * @param key    键
     */
    public Object setPop(String key) {
      try {
        if (StringUtils.isEmpty(key)) {
          log.info("key is null，不进行下一步处理");
          return 0;
        }

        return redisTemplate.opsForSet().pop(key);
      } catch (Exception e) {
        e.printStackTrace();
        return null;
      }
    }

    // ===============================list=================================

    /**
     * 获取list缓存的内容
     *
     * @param key   键
     * @param start 开始
     * @param end   结束 0 到 -1代表所有值
     * @return
     */
    public List<Object> lGet(String key, long start, long end) {
        try {
            if (StringUtils.isEmpty(key)) {
                log.info("key is null，不进行下一步处理");
                return null;
            }

            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取list缓存的长度
     *
     * @param key 键
     * @return
     */
    public long lGetListSize(String key) {
        try {
            if (StringUtils.isEmpty(key)) {
                log.info("key is null，不进行下一步处理");
                return 0;
            }

            return redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 通过索引 获取list中的值
     *
     * @param key   键
     * @param index 索引 index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
     * @return
     */
    public Object lGetIndex(String key, long index) {
        try {
            if (StringUtils.isEmpty(key)) {
                log.info("key is null，不进行下一步处理");
                return 0;
            }
            return redisTemplate.opsForList().index(key, index);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将list放入缓存
     */
    public boolean lSet(String key, Object value) {
        try {
            if (StringUtils.isEmpty(key)) {
                log.info("key is null，不进行下一步处理");
                return false;
            }

            redisTemplate.opsForList().rightPush(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return
     */
    public boolean lSet(String key, Object value, long time) {
        try {
            if (StringUtils.isEmpty(key)) {
                log.info("key is null，不进行下一步处理");
                return false;
            }
            redisTemplate.opsForList().rightPush(key, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @return
     */
    public boolean lSet(String key, List<Object> value) {
        try {
            if (StringUtils.isEmpty(key)) {
                log.info("key is null，不进行下一步处理");
                return false;
            }

            redisTemplate.opsForList().rightPushAll(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return
     */
    public boolean lSet(String key, List<Object> value, long time) {
        try {
            if (StringUtils.isEmpty(key)) {
                log.info("key is null，不进行下一步处理");
                return false;
            }

            redisTemplate.opsForList().rightPushAll(key, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据索引修改list中的某条数据
     *
     * @param key   键
     * @param index 索引
     * @param value 值
     * @return
     */
    public boolean lUpdateIndex(String key, long index, Object value) {
        if (StringUtils.isEmpty(key)) {
            log.info("key is null，不进行下一步处理");
            return false;
        }

        try {
            redisTemplate.opsForList().set(key, index, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 移除N个值为value
     *
     * @param key   键
     * @param count 移除多少个
     * @param value 值
     * @return 移除的个数
     */
    public long lRemove(String key, long count, Object value) {
        if (StringUtils.isEmpty(key)) {
            log.info("key is null，不进行下一步处理");
            return 0;
        }
        try {
            Long remove = redisTemplate.opsForList().remove(key, count, value);
            return remove;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 使用示例
     *    SessionCallback<Object> callback = new SessionCallback<Object>() {
     *         @Override
     *         public Object execute(RedisOperations operations) throws DataAccessException {
     *             operations.multi();
     *             operations.opsForValue().set("name", "qinyi");
     *             operations.opsForValue().set("gender", "male");
     *             operations.opsForValue().set("age", "19");
     *             return operations.exec();
     *         }
     *     };
     * @param callback
     * @return
     */
    public Object execute(SessionCallback<Object> callback){
        return redisTemplate.execute(callback);
    }

}
