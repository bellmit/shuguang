package com.sofn.common.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.util.ParameterizedTypeImpl;
import com.google.common.collect.Lists;
import com.sofn.common.annotation.RedisCache;
import com.sofn.common.exception.SofnException;
import com.sofn.common.utils.JsonUtils;
import com.sofn.common.utils.RedisHelper;
import com.sofn.common.utils.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

/**
 * Redis 缓存切面
 *
 * @author heyongjie
 * @date 2019/12/30 13:51
 */
@Aspect
@Component
@Slf4j
public class RedisCacheAspect {

    @Autowired
    private RedisHelper redisHelper;

    /**
     * KEY  之间的分隔符
     */
    private final static String KEY_SPLIT_STR = "_";

    @Pointcut("@annotation(com.sofn.common.annotation.RedisCache)")
    private void includeService() {
    }

    @Around("includeService()")
    public Object cache(ProceedingJoinPoint proceedingJoinPoint) {
        Object result = null;
        MethodSignature sign = (MethodSignature) proceedingJoinPoint.getSignature();
        Object[] args = proceedingJoinPoint.getArgs();
        Method method = sign.getMethod();
        //获取方法上的注解
        RedisCache redisCache = method.getAnnotation(RedisCache.class);
        String key;
        String loginUserId = "";
        if(redisCache.isCarryLoginUserId()){
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


        if (!redisCache.isMap()) {
            key = getKey(args, redisCache, redisCache.key(),loginUserId);
        } else {
            // item和isCarryParams必须有一个
            if(StringUtils.isBlank(redisCache.item()) && !redisCache.isCarryParams()){
                log.error("方法:{}需要存储Map，Item为空并且没有携带参数，无法存储Map",method.getName());
                throw new SofnException("方法需要存储Map，Item为空并且没有携带参数，无法存储Map");
            }
            if(StringUtils.isBlank(redisCache.item()) && redisCache.isCarryParams()){
                if(args.length == 0){
                    // 空字符串转json时json返回null并不会是""
                    log.error("方法:{}需要存储Map，Item为空并且没有携带参数，无法存储Map",method.getName());
                    throw new SofnException("方法需要存储Map，Item为空并且参数为空，无法存储Map");
                }
            }
            key = getKey(args, redisCache, redisCache.item(),loginUserId);
        }
        // 如果缓存中有值就直接从缓存中获取
        Object valueByRedis = getValueByRedis(key, redisCache, method);
        if (valueByRedis != null) {
            if(!redisCache.isMap()){
                log.info("方法{}从redis中获取值,KEY为：{}", method.getName(), key);
            }else{
                log.info("方法{}从redis中获取值,KEY为：{},ITEM为：{}", method.getName(),redisCache.key(), key);
            }
            return valueByRedis;
        }
        try {
            result = proceedingJoinPoint.proceed();
            setValue(redisCache, result, key);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            log.error("方法执行失败");
        }
        return result;
    }


    /**
     * 从Redis 中获取值
     * 如果、
     *
     * @param key        KEY
     * @param redisCache 方法上的RedisCache 注解
     * @return Object
     */
    private Object getValueByRedis(String key, RedisCache redisCache, Method method) {
        if (StringUtils.isBlank(key)) {
            log.warn("KEY值为空，不走缓存");
            return null;
        }
        // 1. 取出值
        Object redisValueObj;
        if (!redisCache.isMap()) {
            redisValueObj = redisHelper.get(key);
        } else {
            redisValueObj = redisHelper.hget(redisCache.key(), key);
        }
        String redisValue = (String) redisValueObj;
        // 获取值类型
        Class<?> returnType = method.getReturnType();
        // 2. 如果有值那么将值根据类型转换
        try {
            // 获取泛型
            List<Type> types = Lists.newArrayList();
            types.add(returnType);
            Type genericReturnType = method.getGenericReturnType();
            if (genericReturnType != null) {
                try {
                    Type[] actualTypeArguments = ((ParameterizedType) genericReturnType).getActualTypeArguments();
                    // 这些是具体的实现类
                    if (actualTypeArguments != null) {
                        types.addAll(Arrays.asList(actualTypeArguments));
                    }
                    return JSON.parseObject(redisValue, buildType(types));
                } catch (Exception e) {
                    log.warn(e.getMessage());
                    return JSON.parseObject(redisValue, returnType);
                }
            }
            return JSON.parseObject(redisValue, returnType);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("将REDIS 中的值转为具体类型失败");
        }
        return null;
    }

    /**
     * 将查询出的结果缓存
     *
     * @param redisCache 方法上的RedisCache
     * @param key        最终的KEY
     * @param result     结果
     */
    private void setValue(RedisCache redisCache, Object result, String key) {
        if (result != null) {
            String redisValue = JsonUtils.obj2json(result);
            // 设置值
            if (!redisCache.isMap()) {
                redisHelper.set(key, redisValue, redisCache.timeout());
            } else {
                redisHelper.hset(redisCache.key(),key, redisValue);
            }
        }
    }

    /**
     * 用于获取所有泛型，便于JSON.parseObject方法使用
     * 参考：https://www.cnblogs.com/liqipeng/p/9148545.html
     *
     * @param types 所有泛型
     * @return Type
     */
    private static Type buildType(List<Type> types) {
        ParameterizedTypeImpl beforeType = null;
        if (!CollectionUtils.isEmpty(types)) {
            for (int i = types.size() - 1; i > 0; i--) {
                beforeType = new ParameterizedTypeImpl(new Type[]{beforeType == null ? types.get(i) : beforeType}, null, types.get(i - 1));
            }
        }
        return beforeType;
    }


    /**
     * 获取Redis缓存值得KEY
     * 如果携带参数，那么最终生成的KEY:RedisCache中key_将参数转为json后的字符串
     * 如果携带当前登录用户，那么最终生成的KEY：RedisCache中key_当前登录用户ID
     * 如果既需要携带参数也需要携带登录用户，那么最终生成的key：RedisCache中key_将参数转为json后的字符串_当前登录用户ID
     *
     * @param params     请求参数
     * @param redisCache 方法上的RedisCache注解
     * @param key        需要加参数和登陆用户的KEY
     * @return 根据配置生成的KEY
     */
    private String getKey(Object[] params, RedisCache redisCache, String key,String loginUserId) {
        if (redisCache == null) {
            throw new SofnException("RedisCache为空");
        }
        if (redisCache.isCarryParams()) {
            String paramsKey = JsonUtils.obj2json(params);
            key += KEY_SPLIT_STR + paramsKey;
        }
        if (redisCache.isCarryLoginUserId()) {

            key += KEY_SPLIT_STR + loginUserId;
        }
        return key;
    }

}
