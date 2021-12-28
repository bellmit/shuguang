//package com.sofn.fdpi.util;
//
//import com.sofn.common.utils.RedisHelper;
//import com.sofn.common.utils.SpringContextHolder;
//
//import java.util.Date;
//import java.util.Objects;
//
//public class RedisCompUtil {
//
//    public final static String registerKey="FDPI_COMP_REGISTER";
//    private final static String registerUserKey="FDPI_USER_REGISTER";
//
//    private static RedisHelper redisHelper;
//    static {
//        redisHelper = SpringContextHolder.getBean(RedisHelper.class);
//    }
//
//    /**
//     * 验证企业是否重复注册
//     * @param compName 企业名称
//     * @return 重复：true;其它：false
//     */
//    public static boolean validCompIsHasRegisterAndSaveInCache(String compName){
//        return validIsExistAndIsNotExistToSaveInCache(registerKey,compName);
//    }
//
//    public static void addRegisterCompInCacheForHash(String compName){
//        addInCacheForHash(registerKey,compName);
//    }
//
//    /**
//     * 清除企业注册 企业缓存
//     * @param compName 企业名称
//     */
//    public static void deleteCompInCacheForHash(String compName){
//        deleteInCacheForHash(registerKey,compName);
//    }
//
//    /**
//     * 验证账号是否重复注册
//     * @param userName 用户账号
//     * @return 重复：true;其它：false
//     */
//    public static boolean validUserNameIsHasRegisterAndSaveInCache(String userName){
//        return validIsExistAndIsNotExistToSaveInCache(registerUserKey,userName);
//    }
//
//    public static void addRegisterUserNameInCacheForHash(String userName){
//        addInCacheForHash(registerUserKey,userName);
//    }
//
//    /**
//     * 清除账号注册 账号缓存
//     * @param userName 用户账号
//     */
//    public static void deleteUserNameInCacheForHash(String userName){
//        deleteInCacheForHash(registerUserKey,userName);
//    }
//
//
//
//    /**
//     * 公共方法，判断hash中key 是否存在，存在返回true，不存在则添加返回false
//     * @param redisKey key
//     * @param name value  item=value
//     * @return true/false
//     */
//    private static boolean validIsExistAndIsNotExistToSaveInCache(String redisKey,String name){
//        Object objInfo = redisHelper.hget(redisKey,name);
//        if(Objects.isNull(objInfo)){
//            //不存在则保存到redis中
//            synchronized (RedisCompUtil.class){
//                Object objInfoTwo = redisHelper.hget(redisKey, name);
//                if(Objects.isNull(objInfoTwo)){
//                    //保存
//                    addInCacheForHash(redisKey,name);
//                    return false;
//                }else{
//                    return true;
//                }
//            }
//        }else{
//            return true;
//        }
//    }
//
//    /**
//     * 保存到redis缓存中，数据类型:hash
//     * @param redisKey key
//     * @param name item=value
//     */
//    private static void addInCacheForHash(String redisKey,String name){
//        redisHelper.hset(redisKey,name,name+"#"+new Date());
//    }
//
//    /**
//     * 删除redis的缓存，数据类型:hash
//     * @param redisKey key
//     * @param name item=value
//     */
//    private static void deleteInCacheForHash(String redisKey,String name){
//        redisHelper.hdel(redisKey,name);
//    }
//
//
//}
