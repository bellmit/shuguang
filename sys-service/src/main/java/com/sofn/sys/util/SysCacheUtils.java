package com.sofn.sys.util;

import com.google.common.collect.Lists;
import com.sofn.common.config.YmlConfig;
import com.sofn.common.constants.Constants;
import com.sofn.common.enums.CommonEnum;
import com.sofn.common.exception.ExceptionType;
import com.sofn.common.exception.SofnException;
import com.sofn.common.utils.*;
import com.sofn.sys.enums.SysCacheKeysEnum;
import com.sofn.sys.enums.SysConstant;
import com.sofn.sys.enums.SysManageEnum;
import com.sofn.sys.model.*;
import com.sofn.sys.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.redisson.api.RBucket;
import org.redisson.api.RMap;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * 系统管理缓存工具类
 */
@Slf4j
public class SysCacheUtils {

    private static RedisHelper redisHelper = SpringContextHolder.getBean(RedisHelper.class);
    private static RedisUtils redisUtils = SpringContextHolder.getBean(RedisUtils.class);
    private static YmlConfig ymlConfig = SpringContextHolder.getBean(YmlConfig.class);

    private static final String VERIFYCOUNT_PREFIX = "fail.count.";

    // =============================common===================================

    /**
     * 用户缓存版本号递增
     * @param userId 用户ID
     */
    public static double increaseUserCacheVersion(String userId) {
        if (StringUtils.isBlank(userId)) {
            throw new SofnException("用户缓存版本号递增未指定用户ID");
        }

        return redisHelper.hincr(SysCacheKeysEnum.SYS_KEY_USER_VERSION.getCode(), userId, 1);
    }

    /**
     * 获取用户缓存版本号
     * @param userId 用户ID
     */
    public static int getUserCacheVersion(String userId) {
        if (StringUtils.isBlank(userId)) {
            throw new SofnException("获取用户缓存版本号未指定用户ID");
        }

        Object versionObj = redisHelper.hget(SysCacheKeysEnum.SYS_KEY_USER_VERSION.getCode(), userId);
        if (versionObj == null) {
            return new Double(increaseUserCacheVersion(userId)).intValue();
        }

        return Integer.parseInt(versionObj.toString());
    }

    /**
     * 删除用户缓存版本号
     * @param userId 用户ID
     */
    public void delUserCacheVersion(String userId) {
        if (StringUtils.isBlank(userId)) {
            throw new SofnException("删除用户缓存版本号未指定用户ID");
        }

        redisHelper.hdel(SysCacheKeysEnum.SYS_KEY_USER_VERSION.getCode(), userId);
    }

    // =============================登录相关缓存===================================

    /**
     * 根据验证码ID缓存验证码
     *
     * @param captchaId 验证码ID
     * @param captcha   验证码
     */
    public static void cacheCaptchaById(String captchaId, String captcha) {
        // 验证码存储在redis中，有效时间
        redisHelper.set(captchaId, captcha, ymlConfig.captchaExpireTime);
    }

    /**
     * 根据验证码ID获取缓存的验证码
     *
     * @param captchaId 验证码ID
     * @return 验证码
     */
    public static String getCaptchaById(String captchaId) {
        return (String) redisHelper.get(captchaId);
    }

    /**
     * 从验证码仓库中随机获取一个验证码
     * @return
     */
    public static String getCaptchaFromRepo() {
      Object value = redisHelper.sRandomGet(SysCacheKeysEnum.SYS_KEY_CAPTCHA_REPO.getCode());
      return value==null?"":String.valueOf(value);
    }

    /**
     * 获取验证码仓库size
     * @return
     */
    public static long getCaptchaRepoSize() {
      return redisHelper.sGetSetSize(SysCacheKeysEnum.SYS_KEY_CAPTCHA_REPO.getCode());
    }

    /**
     * 添加一个验证码到仓库
     * @return
     */
    public static long addCaptchaToRepo(String value) {
      if (StringUtils.isNotBlank(value)){
        return redisHelper.sSet(SysCacheKeysEnum.SYS_KEY_CAPTCHA_REPO.getCode(), value);
      }

      return 0;
    }

    /**
     * 从验证码仓库随机移除一个验证码
     * @return
     */
    public static Object popCaptchaFromRepo() {
      return redisHelper.setPop(SysCacheKeysEnum.SYS_KEY_CAPTCHA_REPO.getCode());
    }

    /**
     * 缓存用户登录信息
     *
     * @param token    token
     * @param user     登录用户
     * @param roleList 用户角色名称列表
     */
    public static void cacheUserInfo(String token, SysUserForm user, List<SysRole> roleList, LoginVo loginVo, SysOrganization org) {
        String lastToken = (String) redisHelper.hget(Constants.UserSession.userNameToken, user.getUsername());
        if (StringUtils.isNotBlank(lastToken)) {
            redisHelper.del(lastToken);
        }

        // 缓存登录信息
        if (!redisHelper.hset(Constants.UserSession.userNameToken, user.getUsername(), token) ||
                !redisHelper.hset(token, Constants.UserSession.userId, user.getId()) ||
                !redisHelper.hset(token, Constants.UserSession.user, JsonUtils.obj2json(user)) ||
                !redisHelper.hset(token, Constants.UserSession.userName, user.getUsername()) ||
                !redisHelper.hset(token, Constants.UserSession.userOrganizationId, user.getOrganizationId()) ||
                !redisHelper.hset(token, Constants.UserSession.userOrgInfo, JsonUtils.obj2json(org)) ||
                !redisHelper.hset(token, Constants.UserSession.userRoles, JsonUtils.obj2json(roleList)) ||
                !redisHelper.hset(token, Constants.UserSession.isSysUser, BoolUtils.Y)) {
            redisHelper.del(token);
            throw new SofnException(ExceptionType.CACHE_ERROR);
        }

        // 设置过期时间
        if (BoolUtils.isTrue(loginVo.getRememberMe())) {
            redisHelper.hset(token, Constants.UserSession.rememberMe, loginVo.getRememberMe());
            redisHelper.expire(token, ymlConfig.rememberExpireTime / 1000);
        } else {
            redisHelper.expire(token, ymlConfig.tokenExpireTime / 1000);
        }
    }

    /**
     * 缓存用户能够查看的机构信息
     * @param token   token
     * @param systemIds   系统ID
     * @param appIds  系统代码
     * @param username   用户名称
     */
    public static void cacheUserCanShowSystem(String token,Set<String> systemIds,Set<String> appIds,String username){

        if(!CollectionUtils.isEmpty(systemIds)){
            redisHelper.hset(token,Constants.UserSession.USER_HAVA_SYSTEM_IDS,IdUtil.getStrIdsByList(Lists.newArrayList(systemIds)));
        }

        if(!CollectionUtils.isEmpty(appIds)){
            redisHelper.hset(token,Constants.UserSession.USER_HAVA_SYSTEM_APPIDS,IdUtil.getStrIdsByList(Lists.newArrayList(appIds)));
        }

    }

    /**
     * 清除用户登录相关缓存
     *
     * @param token token
     * @return 是否成功
     */
    public static boolean clearUserCache(String token) {
        if (redisHelper.hasKey(token)) {
            Object userNameObj = redisHelper.hget(token, Constants.UserSession.userName);
            String userName = userNameObj == null ? null : userNameObj.toString();
            if (StringUtils.isNotBlank(userName)) {
                redisHelper.hdel(Constants.UserSession.userNameToken, userName);
                redisHelper.del(token);
                return true;
            }
        } else {
            // token过期直接返回成功
            return true;
        }

        return false;
    }


    // =================================================================子系统缓存相关

    /**
     * 缓存子系统树
     *
     * @param subsystemFormList 子系统树
     */
    public static void cacheSubsystemTree(List<SysSubsystemForm> subsystemFormList) {
        if (!checkRedisHelper()) {
            return;
        }
        if (CollectionUtils.isEmpty(subsystemFormList)) {
            return;
        }

        String subsystemFormListJson = JsonUtils.obj2json(subsystemFormList);
        redisHelper.set(SysManageEnum.SYS_SUBSYSTEM_CACHE_TREE_KEY.getCode(), subsystemFormListJson);
        log.info(String.format("Cache subsystemFormList:%s", subsystemFormList));
    }

    /**
     * 获取子系统树
     */
    public static List<SysSubsystemForm> getSubsystemFormList() {
        if (!checkRedisHelper()) {
            return null;
        }

        if (redisHelper.hasKey(SysManageEnum.SYS_SUBSYSTEM_CACHE_TREE_KEY.getCode())) {
            String subsystemFormListJson = (String) redisHelper.get(SysManageEnum.SYS_SUBSYSTEM_CACHE_TREE_KEY.getCode());
            if (StringUtils.isNotBlank(subsystemFormListJson)) {
                return JsonUtils.json2List(subsystemFormListJson, SysSubsystemForm.class);
            }
            return new ArrayList<>();
        }
        return null;
    }

    public static void delSubsystemTreeCache() {
        if (redisHelper.hasKey(SysManageEnum.SYS_SUBSYSTEM_CACHE_TREE_KEY.getCode())) {
            redisHelper.del(SysManageEnum.SYS_SUBSYSTEM_CACHE_TREE_KEY.getCode());
        }
    }

    // =========================================================角色资源缓存相关

    /**
     * 根据角色缓存资源
     * @param roleId   角色ID
     * @param resourceList  资源集合
     */
    public static void cacheResourceByRole(String roleId, Set<SysResourceForm> resourceList) {
        if (!checkRedisHelper()) {
            return;
        }
        if (StringUtils.isBlank(roleId) || CollectionUtils.isEmpty(resourceList)) {
            return;
        }

        String resourceJson = JsonUtils.obj2json(resourceList);
        redisHelper.hset(CommonEnum.SYS_ROLE_RESOURCE_CACHE_KEY.getCode(), roleId, resourceJson);
        log.info(String.format("缓存角色权限,角色ID:%s,资源ID:%s", roleId, resourceJson));
    }

    /**
     * 获取角色权限
     *
     * @param roleId 角色名称
     * @return 角色对应的权限列表
     */
    public static List<SysResourceForm> getResourceByRole(String roleId) {
        if (!checkRedisHelper()) {
            return null;
        }

        if (StringUtils.isBlank(roleId)) {
            return null;
        }

        if (redisHelper.hHasKey(CommonEnum.SYS_ROLE_RESOURCE_CACHE_KEY.getCode(), roleId)) {
            String resourceJson = (String) redisHelper.hget(CommonEnum.SYS_ROLE_RESOURCE_CACHE_KEY.getCode(), roleId);
            log.info(String.format("get cache resources: %s", resourceJson));
            if (StringUtils.isNotBlank(resourceJson)) {
                return JsonUtils.json2List(resourceJson, SysResourceForm.class);
            }

            return new ArrayList<>();
        }

        return null;
    }

    /**
     * 删除角色权限缓存
     *
     * @param roleId 角色名称
     */
    public static void delRoleResourceCache(String roleId) {
        if (redisHelper.hHasKey(CommonEnum.SYS_ROLE_RESOURCE_CACHE_KEY.getCode(), roleId)) {
            redisHelper.hdel(CommonEnum.SYS_ROLE_RESOURCE_CACHE_KEY.getCode(), roleId);
        }
    }

    // ==================================================================缓存每个子系统有的角色

    /**
     * 缓存子系统下角色列表
     *
     * @param roleFormList 角色列表
     */
    public static void cacheRoleFormList(List<SysRoleForm> roleFormList, String subsystemId) {
        if (!checkRedisHelper()) {
            return;
        }
        if (CollectionUtils.isEmpty(roleFormList) || StringUtils.isBlank(subsystemId)) {
            return;
        }

        String roleFormListJson = JsonUtils.obj2json(roleFormList);
        redisHelper.hset(SysManageEnum.SYS_SUBSYSTEM_ROLE_LIST_CACHE_KEY.getCode(), subsystemId, roleFormListJson);
        log.info(String.format("Cache subsystem roleList:subsystemId:%s,roleFormList:%s", subsystemId, roleFormListJson));
    }

    /**
     * 获取子系统下角色列表
     */
    public static List<SysRoleForm> getRoleFormList(String subsystemId) {
        if (!checkRedisHelper()) {
            return null;
        }
        if (StringUtils.isBlank(subsystemId)) {
            return null;
        }

        if (redisHelper.hHasKey(SysManageEnum.SYS_SUBSYSTEM_ROLE_LIST_CACHE_KEY.getCode(), subsystemId)) {
            String roleFormListJson = (String) redisHelper.hget(SysManageEnum.SYS_SUBSYSTEM_ROLE_LIST_CACHE_KEY.getCode(), subsystemId);
            log.info(String.format("get  subsystem roleList: %s", roleFormListJson));
            if (StringUtils.isNotBlank(roleFormListJson)) {
                return JsonUtils.json2List(roleFormListJson, SysRoleForm.class);
            }
            return new ArrayList<>();
        }
        return null;

    }

    public static void delRoleFormList(String subsystemId) {
        if (redisHelper.hHasKey(SysManageEnum.SYS_SUBSYSTEM_ROLE_LIST_CACHE_KEY.getCode(), subsystemId)) {
            redisHelper.del(SysManageEnum.SYS_SUBSYSTEM_ROLE_LIST_CACHE_KEY.getCode(), subsystemId);
        }
    }

    // ===================================================================行政区划缓存相关

    /**
     * 获取整棵树结构
     *
     * @return SysRegionTreeVo
     */
    public static SysRegionTreeVo getSysRegionTreeByCache(String appId, Integer versionYear) {
        if (redisUtils == null) {
            return null;
        }
        SysRegionTreeVo sysRegionTreeVo = redisUtils.get(SysManageEnum.SYS_REGION_CACHE_KEY.getCode()+"_"+appId+"_"+versionYear, SysRegionTreeVo.class);
        //  缓存中没有值
        return sysRegionTreeVo;
    }

    /**
     * 将行政区划树缓存到redis中
     */
    public static void setSysRegionTreeByCache(String appId, Integer versionYear, SysRegionTreeVo sysRegionTreeVo) {
        if (redisUtils == null) {
            return;
        }
        if (sysRegionTreeVo != null) {
            redisUtils.set(SysManageEnum.SYS_REGION_CACHE_KEY.getCode()+"_"+appId+"_"+versionYear, sysRegionTreeVo, Long.parseLong(
                    SysManageEnum.SYS_MANAGE_CACHE_TIMEOUT.getCode()));
        }
    }


    /**
     * 根据父ID获取树结构
     *
     * @param parentId
     * @return
     */
    public static List<SysRegionTreeVo> getSysRegionTreeByIdAndCache(String appId, String parentId, Integer versionYear) {
        if (redisUtils == null) {
            return null;
        }
        if (StringUtils.isBlank(parentId)) {
            return null;
        }
        String key = SysManageEnum.SYS_REGION_CACHE_KEY.getCode() + "_" + appId + "_"+versionYear+"_" + parentId;
        List<SysRegionTreeVo> sysRegionTreeVos = redisUtils.get(key, List.class);
        return sysRegionTreeVos;
    }

    /**
     * 将ParentId 所对应的树缓存到redis中
     *
     * @param parentId
     * @return
     */
    public static void setSysRegionTreeByIdAndCache(String appId, String parentId, Integer versionYear, List<SysRegionTreeVo> sysRegionTreeVos) {
        if (redisUtils == null) {
            return;
        }

        if (StringUtils.isBlank(appId)) {
          return;
        }

        if (StringUtils.isBlank(parentId)) {
            return;
        }

        if (CollectionUtils.isEmpty(sysRegionTreeVos)) {
            return;
        }

        String key = SysManageEnum.SYS_REGION_CACHE_KEY.getCode() + "_" + appId + "_"+versionYear+"_" + parentId;

        redisUtils.set(key, sysRegionTreeVos, Long.parseLong(
                SysManageEnum.SYS_MANAGE_CACHE_TIMEOUT.getCode()));
    }


    /**
     * 根据行政区划ParentId获取列表
     *
     * @param parentId 行政区划ParentId
     * @return List<SysRegionForm>
     */
    public static List<SysRegionForm> getListByRegionCode(String parentId) {
        if (redisUtils == null) {
            return null;
        }

        if (StringUtils.isBlank(parentId)) {
            return null;
        }
        String key = SysManageEnum.SYS_REGION_CACHE_LIST_KEY.getCode() + "_" + parentId;
        List<SysRegionForm> sysRegionForms = redisUtils.get(key, List.class);
        return sysRegionForms;
    }

    /**
     * 根据parentId将sysRegionForms 存入缓存中
     *
     * @param parentId       parentId
     * @param sysRegionForms sysRegionForms
     */
    public static void setListByRegionCode(String parentId, List<SysRegionForm> sysRegionForms) {
        if (redisUtils == null) {
            return;
        }

        if (StringUtils.isBlank(parentId)) {
            return;
        }
        if (CollectionUtils.isEmpty(sysRegionForms)) {
            return;
        }

        redisUtils.set(SysManageEnum.SYS_REGION_CACHE_LIST_KEY.getCode() + "_" + parentId, sysRegionForms, Long.parseLong(
                SysManageEnum.SYS_MANAGE_CACHE_TIMEOUT.getCode()));

    }

    /**
     * 设置行政区划代码缓存
     *
     * @param codeMappingCache 行政区划代码映射
     */
    public static void setRegionCodeMappingCache(Map<String, String> codeMappingCache, Integer versionYear) {
        if (!CollectionUtils.isEmpty(codeMappingCache)) {
            redisUtils.set(SysManageEnum.SYS_REGION_CACHE_CODE_MAPPING.getCode()+"_"+versionYear, codeMappingCache, Long.parseLong(
                    SysManageEnum.SYS_MANAGE_CACHE_TIMEOUT.getCode()));
        }
    }

    /**
     * 获取行政区划代码映射值
     *
     * @return
     */
    public static Map<String, String> getRegionCodeMapping(Integer versionYear) {
        return redisUtils.get(SysManageEnum.SYS_REGION_CACHE_CODE_MAPPING.getCode()+"_"+versionYear, Map.class);
    }


    /**
     * 删除行政区划整棵树缓存
     */
    public static void removeRegionTreeKey() {
        if (redisUtils == null) {
            return;
        }

        // SysManageEnum.SYS_REGION_CACHE_KEY.getCode()
        redisHelper.delByPattern(SysManageEnum.SYS_REGION_CACHE_KEY.getCode() + "*");
        redisHelper.delByPattern(SysManageEnum.SYS_REGION_CACHE_CODE_MAPPING.getCode()   + "*");

        redisUtils.delete(SysManageEnum.SYS_REGION_CACHE_KEY.getCode());
        redisUtils.delete(SysManageEnum.SYS_REGION_CACHE_CODE_MAPPING.getCode() );
    }

    /**
     * 根据ParentId删除行政区划树和列表缓存
     *
     * @param parentId parentId
     */
    public static void removeRegionTreeKeyByParentId(List<String> appIds, String parentId) {
        if (redisUtils == null) {
            return;
        }

        if (StringUtils.isBlank(parentId)) {
            return;
        }

        if (!CollectionUtils.isEmpty(appIds)){
          appIds.forEach(appId -> redisUtils.delete(SysManageEnum.SYS_REGION_CACHE_KEY.getCode() + "_" + appId + "_" + parentId));
        }
        redisUtils.delete(SysManageEnum.SYS_REGION_CACHE_LIST_KEY.getCode() + "_" + parentId);
    }

    /**
     * 移除[区划名称-区划代码] and [区划代码-区划名称]的缓存
     */
    public static void removeRegionNameCodeCache() {
        redisHelper.del(CommonEnum.SYS_REGION_NAME_CODE_CACHE_KEY.getCode(),
                CommonEnum.SYS_REGION_CODE_NAME_CACHE_KEY.getCode());
    }

    // =================================机构相关缓存========================

    /**
     * 根据机构id缓存机构信息
     * @param sysOrg 机构对象
     * @return 是否成功
     */
    public static boolean cacheOrgById(SysOrg sysOrg){
        if (sysOrg != null) {
            return redisHelper.hset(SysCacheKeysEnum.SYS_CACHE_KEY_ORG_ID.getCode(), sysOrg.getId(),JsonUtils.obj2json(sysOrg));
        }

        return false;
    }

    /**
     * 根据机构id获取机构信息
     * @param orgId 机构id
     * @return 机构对象
     */
    public static SysOrg getCacheOrgById(String orgId){
        if (StringUtils.isNotBlank(orgId)) {
            Object obj = redisHelper.hget(SysCacheKeysEnum.SYS_CACHE_KEY_ORG_ID.getCode(), orgId);
            return obj == null?null: JsonUtils.json2obj(String.valueOf(obj),SysOrg.class);
        }

        return null;
    }

    /**
     * 根据机构id删除缓存
     * @param orgIds 机构id
     */
    public static void delCacheOrgByIds(Object... orgIds){
        if (orgIds != null && orgIds.length>0) {
            redisHelper.hdel(SysCacheKeysEnum.SYS_CACHE_KEY_ORG_ID.getCode(), orgIds);
        }
    }

    // =================================角色相关缓存========================

    /**
     * 组装用户角色缓存key
     * @param userId 用户ID
     */
    private static String assembleUserRoleCacheKey(String userId) {
        return String.format("%s:%s:%d",
                SysCacheKeysEnum.SYS_CACHE_KEY_ROLE_USERID_PREFIX.getCode(),
                userId,
                getUserCacheVersion(userId));
    }

    //组装用户常用系统缓存键
    private static String assembleUserSystemCacheKey(String userId) {
        return String.format("%s:%s:%d",
                SysCacheKeysEnum.SYS_CACHE_KEY_SYSTEM_USERID_PREFIX.getCode(),
                userId,
                getUserCacheVersion(userId));
    }

    /**
     * 根据id缓存用户角色列表
     * @param userId 用户id
     * @param roleList 角色列表
     */
    public static void cacheRoleListByUserId(String userId, List<SysRole> roleList) {
        if (StringUtils.isNotBlank(userId) && roleList != null) {
            redisHelper.getRedissonClient()
                .getBucket(assembleUserRoleCacheKey(userId))
                .set(roleList,SysConstant.WEEK_SECOND, TimeUnit.SECONDS);
        }
    }

    /**
     * 根据用户id获取用户角色列表缓存
     * @param userId
     * @return
     */
    public static List<SysRole> getRoleListByUserId(String userId) {
        if (StringUtils.isNotBlank(userId)) {
            RBucket<List<SysRole>> rBucket = redisHelper.getRedissonClient()
                .getBucket(assembleUserRoleCacheKey(userId));
            return rBucket.get();
        }

        return null;
    }

    /**
     * 根据用户id删除缓存
     * @param userIds 用户id
     */
    public static void delCacheRoleListByIds(List<String> userIds) {
        if (userIds != null && userIds.size()>0) {
            userIds.forEach(new Consumer<String>() {
                @Override
                public void accept(String userId) {
                    redisHelper.getRedissonClient()
                        .getBucket(assembleUserRoleCacheKey(userId))
                        .delete();
                }
            });
        }
    }

    // =================================业务系统相关缓存========================

    /**
     * 根据系统id缓存业务系统
     * @param subsystemList 业务系统列表
     */
    public static void cacheSubsystemListById(List<SysSubsystem> subsystemList) {
        if (!CollectionUtils.isEmpty(subsystemList)) {
            RMap<String,SysSubsystem> rMap = redisHelper.getRedissonClient()
                .getMap(SysCacheKeysEnum.SYS_CACHE_KEY_SYSTEM_ID.getCode());

            subsystemList.forEach(sysSubsystem -> {
                if (sysSubsystem != null && StringUtils.isNotBlank(sysSubsystem.getId())){
                    rMap.put(sysSubsystem.getId(), sysSubsystem);
                }
            });

            rMap.expire(SysConstant.WEEK_SECOND, TimeUnit.SECONDS);
        }
    }

    /**
     * 获取全部业务系统缓存
     * @return Map<String,SysSubsystem>； key：业务系统id
     */
    public static Map<String,SysSubsystem> getSubsystemList(){
        RMap<String,SysSubsystem> rMap = redisHelper.getRedissonClient()
                .getMap(SysCacheKeysEnum.SYS_CACHE_KEY_SYSTEM_ID.getCode());

        return rMap.readAllMap();
    }

    /**
     * 根据id集合获取业务系统信息
     * @param ids id集合
     */
    public static Map<String,SysSubsystem> getSubSubsystemList(Set<String> ids){
        RMap<String,SysSubsystem> rMap = redisHelper.getRedissonClient()
                .getMap(SysCacheKeysEnum.SYS_CACHE_KEY_SYSTEM_ID.getCode());

        return rMap.getAll(ids);
    }

    /**
     * 删除业务系统缓存
     */
    public static boolean delSubsystemList(){
        return redisHelper.getRedissonClient()
            .getMap(SysCacheKeysEnum.SYS_CACHE_KEY_SYSTEM_ID.getCode())
            .delete();
    }


    /**
     * 缓存用户常用的子系统信息
     * @param userSystemList  常用系统list
     * @param userId   用户Id
     */
    public static void cacheUserUseSystem(String userId, List<SysUserSystem> userSystemList){

        if (StringUtils.isNotBlank(userId) && userSystemList != null) {
            redisHelper.getRedissonClient()
                    .getBucket(assembleUserSystemCacheKey(userId))
                    .set(userSystemList,SysConstant.WEEK_SECOND, TimeUnit.SECONDS);
        }

    }

    private static boolean checkRedisHelper() {
        return redisHelper != null;
    }

    /**
     * 获取用了登录失败次数
     * @param userName 用户名
     * @return 是否可以正常登录
     * @author du.yang
     * @create 2021-03-26 10:00:00
     */
    public static Boolean getFailCountByUserName(String userName) {
        Boolean isCanLogin = true;
        Object failCount = redisHelper.get(VERIFYCOUNT_PREFIX + userName);
        if (failCount != null && Integer.valueOf(failCount.toString()) >= 5) {
            isCanLogin = false;
        }
        return isCanLogin;
    }

    /**
     * 获取用户登录失败次数
     * @param userName 用户名
     * @return 登录失败次数
     * @author du.yang
     * @create 2021-03-26 12:00:00
     */
    public static Integer getUserFailCountByCache(String userName){
        if (StringUtils.isNotBlank(userName)) {
            Object count = redisHelper.get(VERIFYCOUNT_PREFIX + userName);
            return count == null ? 0 : Integer.valueOf(count.toString());
        }
        return null;
    }

    /**
     * 更新登录失败次数放于redis缓存中
     * @param failCount 失败次数
     * @return null
     * @author du.yang
     * @create 2021-03-26 16:00:00
     */
    public static void setFailCountToCache(Integer failCount,String userName) {
        if (redisUtils == null && failCount == null) {
            return;
        }
        if (failCount != null) {
            redisUtils.set(VERIFYCOUNT_PREFIX + userName, failCount + 1, 60 * 60 * 2);
        }
    }

    /**
     * 登录成功后清除缓存中的登录失败次数释放资源
     * @param userName 用户名
     * @return null
     * @author du.yang
     * @create 2021-03-26 17:00:00
     */
    public static void delFailCountInCache(String userName) {
        if (redisUtils == null && userName == null) {
            return;
        }
        if (userName != null) {
            redisUtils.delete(VERIFYCOUNT_PREFIX + userName);
        }
    }

}
