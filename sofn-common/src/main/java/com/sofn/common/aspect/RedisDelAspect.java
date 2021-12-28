package com.sofn.common.aspect;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sofn.common.annotation.RedisDel;
import com.sofn.common.exception.SofnException;
import com.sofn.common.utils.RedisAopUtils;
import com.sofn.common.utils.RedisHelper;
import com.sofn.common.utils.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 删除Redis缓存切面
 * 获取参数名称参考：
 * https://blog.csdn.net/revitalizing/article/details/71036970
 *
 * @author heyongjie
 * @date 2019/12/31 10:11
 */
@Aspect
@Component
@Slf4j
public class RedisDelAspect {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisHelper redisHelper;

    @Pointcut("@annotation(com.sofn.common.annotation.RedisDel)")
    public void includeService() {
    }

    @AfterReturning("includeService()")
    public void del(JoinPoint joinPoint) {
        MethodSignature sign = (MethodSignature) joinPoint.getSignature();
        Method method = sign.getMethod();
        RedisDel redisDel = method.getAnnotation(RedisDel.class);
        String[] keys = redisDel.key();
        if (keys.length > 0) {
            for (int i = 0; i < keys.length; i++) {
                // 是否是Map
                boolean isMap = getBolleanValueByArr(i, redisDel.isMap());
                // KEY是否携带登录用户ID
                boolean isCarryLoginUserId = getBolleanValueByArr(i, redisDel.isCarryLoginUserId());
                // KEY是否携带参数
                boolean isCarryParams = getBolleanValueByArr(i, redisDel.isCarryParams());
                // 参数中的关键字
                String keyword = getStringValueByArr(i, redisDel.keywords());
                // 如果是Map  Map的Key
                String item = getStringValueByArr(i, redisDel.items());

                String loginUserId = "";
                if(isCarryLoginUserId){
                    try {
                        loginUserId = UserUtil.getLoginUserId();
                    } catch (Exception e) {
                        log.error("Redis没有正确获取到登录用户ID");
                        e.printStackTrace();
                    }
                    if (StringUtils.isBlank(loginUserId)) {
                        throw new SofnException("用户没有登录");
                    }
                }

                String key = keys[i];
                Map<String, String> paramMap = Maps.newHashMap();
                if (isCarryParams) {
                    // 获取参数中的值
                    LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
                    paramMap = RedisAopUtils.getParamMap(u.getParameterNames(method), joinPoint.getArgs());
                }
                if (isMap) {
                    // 从Map中将值删除
                    List<String> delKeys = getDelKey(item, keyword, isCarryLoginUserId, paramMap, loginUserId);
                    if (!CollectionUtils.isEmpty(delKeys)) {
                        // 校验KEY是否符合要求
                        Map<Object, Object> objectValues = redisHelper.hmget(keys[i]);
                        Map<String, String> values = Maps.newHashMap();
                        if (!CollectionUtils.isEmpty(objectValues)) {
                            objectValues.forEach((k, v) -> {
                                if (k != null && v != null) {
                                    // 验证key是否符合条件
                                    boolean flag = checkKeyCanDel(k.toString(), delKeys, isCarryParams, isCarryLoginUserId, item);
                                    if (!flag) {
                                        values.put(k.toString(), v.toString());
                                    }else {
                                        log.info("KEY:{}下的ITEM:{}将被删除", key, k);
                                    }
                                }
                            });
                        }
                        redisHelper.del(key);
                        if(!CollectionUtils.isEmpty(values)){
                            values.forEach((k, v) -> redisHelper.hset(key, k, v));
                        }
                    }
                } else {
                    // 直接删除KEY
                    List<String> delKeys = getDelKey(key, keyword, isCarryLoginUserId, paramMap, loginUserId);
                    if (!CollectionUtils.isEmpty(delKeys)) {
                        delKeys.forEach((key1) -> {
                            log.info("redis即将删除的KEY={}", key1);
                            Set<String> keySets = redisTemplate.keys(key1);
                            redisTemplate.delete(keySets);
                        });
                    }
                }
            }
        }
    }


    /**
     * 检查Map中 Key是否能被删除
     * 1. 当KEY 和ITEM 相等时直接删除
     * 2. 当KEY_JSON
     *
     * @param key     待检查KEY,Redis中存储的Key格式： ITEM_参数JSON_登录用户ID  如： 02_["02"]_05 ,_["02"],02
     * @param delKeys 可以删除的KEY格式： ITEM*value*loginUserId*
     * @return true 可以删除  false不能删除
     */
    private boolean checkKeyCanDel(String key, List<String> delKeys, boolean isCarryParams, boolean isCarryLoginUserId, String item) {
        boolean flag = false;
        String oldKey = key;
        if (!CollectionUtils.isEmpty(delKeys)) {
            key = key.substring(key.indexOf(item + "_") + item.length() + 1);
            for (String delKey : delKeys) {
                delKey = delKey.substring(delKey.indexOf(item + "*") + item.length() + 1);
                // 只要KEY中有匹配的就匹配
                // 参数是否匹配
                if (isCarryParams) {
                    String delKeyParam = delKey.split("\\*")[0];
                    if(key.contains(delKeyParam)){
                        flag = true;
                        break;
                    }
                }
                // 登录用户是否匹配
                if (isCarryLoginUserId) {
                    String delKeyLoginUserId;
                    if (isCarryParams) {
                        delKeyLoginUserId = delKey.split("\\*")[1];
                    } else {
                        delKeyLoginUserId = delKey.split("\\*")[0];
                    }
                   if(key.contains(delKeyLoginUserId)){
                       flag = true;
                       break;
                   }
                }
            }
        }
        return flag;
    }


    /**
     * 获取要删除的实际KEY
     *
     * @param key                RedisDel里面的Key
     * @param keyword            参数中的值
     * @param isCarryLoginUserId 是否携带登录用户
     * @param paramMap           方法参数值
     * @param loginUserId        登录用户ID
     * @return List<String>  返回的String完整格式为  REDIS_KEY_*value*loginUserId*
     */
    private List<String> getDelKey(String key, String keyword, boolean isCarryLoginUserId, Map<String, String> paramMap, String loginUserId) {
        List<String> returnKeys = Lists.newArrayList();
        // keyword是否为空
        if (!StringUtils.isBlank(keyword)) {
            List<String> valueLists = RedisAopUtils.getValue(paramMap, keyword);
            if (CollectionUtils.isEmpty(valueLists)) {
                log.error("删除缓存失败，没有获取对应keyword={}的值", key);
                throw new SofnException("删除缓存失败，没有获取对应keyword的值");
            }
            for (String value : valueLists) {
                if (!StringUtils.isBlank(value)) {
                    returnKeys.add(key + "*" + value + "*");
                }
            }
        } else {
            returnKeys.add(key + "*");
        }
        if (isCarryLoginUserId) {
            returnKeys = returnKeys.stream().map((returnKey) -> returnKey + loginUserId + "*").collect(Collectors.toList());
        }
        return returnKeys;


    }


    /**
     * 从boolean数组中获取某一个值
     *
     * @param index 索引
     * @param objs  boolean 数组
     * @return 索引位置的值
     */
    private boolean getBolleanValueByArr(int index, boolean[] objs) {
        boolean value = false;
        if (objs.length > 0) {
            try {
                value = objs[index];
            } catch (Exception e) {
                e.printStackTrace();
                throw new SofnException("和KEY的数量不匹配");
            }
        }
        return value;
    }

    /**
     * 从String数组中获取某一个值
     *
     * @param index 索引
     * @param objs  String 数组
     * @return 索引位置的值
     */
    private String getStringValueByArr(int index, String[] objs) {
        String value = "";
        if (objs.length > 0) {
            try {
                value = objs[index];
            } catch (Exception e) {
                e.printStackTrace();
                throw new SofnException("和KEY的数量不匹配");
            }
        }
        return value;
    }


    //  redisHelper.hdel(redisDel.key()[i],redisDel.item()[i]);
    /**
     *   List<String> removeKeys = getDelKey(redisDel,method,joinPoint,keys[i]);
     *                     if(!CollectionUtils.isEmpty(removeKeys)){
     *                         removeKeys.forEach((key) -> {
     *                             log.info("redis即将删除的KEY={}",key);
     *                             Set<String> keySets = redisTemplate.keys(key + "*");
     *                             redisTemplate.delete(keySets);
     *                         });
     *                     }
     */


}
