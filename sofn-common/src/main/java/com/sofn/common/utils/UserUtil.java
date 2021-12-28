package com.sofn.common.utils;

import com.google.common.collect.Lists;
import com.sofn.common.constants.Constants;
import com.sofn.common.dao.UserDao;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 用户相关工具类
 * Created by heyongjie on 2019/6/5 16:21
 */
@Slf4j
public class UserUtil {
    private static final String TAG = UserUtil.class.getName();

    private static RedisHelper redisHelper = SpringContextHolder.getBean(RedisHelper.class);

    /**
     * 获取当前登录用户token
     * @return token
     */
    public static String getLoginToken(){
        String token = (String) SecurityUtils.getSubject().getPrincipal();
        if(StringUtils.isBlank(token)){
            return  "";
        }

        return token;
    }

    /**
     * 获取当前登录用户ID
     * @return  String
     */
    public static String getLoginUserId(){
        String token = getLoginToken();
        if (StringUtils.isBlank(token)) {
          throw new SofnException("未获取到用户token");
        }

        Object obj = redisHelper.hget(token,Constants.UserSession.userId);
        if (obj == null){
            log.error("Redis未获取到登录用户信息，请检查，或者尝试重新登录");
            throw new SofnException("请尝试重新登录");
        }

        return obj.toString();
    }

    /**
     * 获取当前登录用户ID
     * @return  String
     */
    public static String getLoginUserId(String token){
        if(StringUtils.isBlank(token)){
            log.error(TAG, "token is blank");
            return  "";
        }
        Object obj = redisHelper.hget(token,Constants.UserSession.userId);
        if (obj == null){
            log.error(TAG, "userId is null");
            return null;
        }
        return obj.toString();
    }

    /**
     * 获取当前登录用户name
     * @return  String
     */
    public static String getLoginUserName(){
        Object obj = redisHelper.hget(getLoginToken(), Constants.UserSession.userName);
        if (obj == null){
            return null;
        }
        return obj.toString();
    }

    /**
     * 获取当前登录用户name
     * @return  String
     */
    public static String fetchLoginUserNameInToken(){
      return JwtUtils.getUsername(getLoginToken());
    }

    /**
     * 获取当前登录用户信息
     */
    public static User getLoginUser(){
        Object obj = redisHelper.hget(getLoginToken(),Constants.UserSession.user);
        if (obj == null){
            return null;
        }

        return JsonUtils.json2obj(obj.toString(), User.class);
    }

    /**
     * 获取当前登录用户信息JSON字符串
     */
    public static String getLoginUserJson(){
      Object obj = redisHelper.hget(getLoginToken(),Constants.UserSession.user);
      if (obj == null){
        return null;
      }

      return obj.toString();
    }

    /**
     * 获取当前登录用户组织id
     * @return  String
     */
    public static String getLoginUserOrganizationId(){
        Object obj = redisHelper.hget(getLoginToken(),Constants.UserSession.userOrganizationId);
        if (obj == null){
            return null;
        }
        return obj.toString();
    }

    /**
     * 获取当前登录用户组织详情
     * @return  组织机构JSON字符串
     */
    public static String getLoginUserOrganizationInfo(){
      Object obj = redisHelper.hget(getLoginToken(),Constants.UserSession.userOrgInfo);
      if (obj == null){
        return null;
      }
      return obj.toString();
    }

    /**
     * 获取当前登录用户角色列表JSON字符串
     * @return  String
     */
    public static String getLoginUserRoleList(){

      Object obj = redisHelper.hget(getLoginToken(),Constants.UserSession.userRoles);
      if (obj == null) {
        return null;
      }
      return obj.toString();
    }

    /**
     * 获取当前登录用户角色ID
     * @return  String
     */
    public static List<String> getLoginUserRoleIdList(){
        Object obj = redisHelper.hget(getLoginToken(),Constants.UserSession.userRoles);
        if (obj == null) {
            return null;
        }
        String roleJson = obj.toString();
        List<Map<String,Object>> roleList = JsonUtils.json2ListkeyMap(roleJson);
        if (roleList != null && roleList.size()>0){
            List<String> roleIdList = new ArrayList<>();
            for (Map<String,Object> map : roleList) {
                if (map == null) {
                    continue;
                }
                roleIdList.add(map.get("id")==null?"":map.get("id").toString());
            }
            return roleIdList;
        }

        return null;
    }


    /**
     * 获取当前登录用户角色code
     * @return  List<String>
     */
    public static List<String> getLoginUserRoleCodeList(){
        String token = getLoginToken();
        return getLoginUserRoleCodeList(token);
    }

    /**
     * 获取当前登录用户角色code
     * @return  List<String>
     */
    public static List<String> getLoginUserRoleCodeList(String token){
        Object obj = redisHelper.hget(token,Constants.UserSession.userRoles);
        if (obj == null) {
            return null;
        }
        String roleJson = obj.toString();
        List<Map<String,Object>> roleList = JsonUtils.json2ListkeyMap(roleJson);
        if (roleList != null && roleList.size()>0){
            List<String> roleCodeList = new ArrayList<>();
            for (Map<String,Object> map : roleList) {
                if (map == null) {
                    continue;
                }
                roleCodeList.add(map.get("roleCode")==null?"":map.get("roleCode").toString());
            }
            return roleCodeList;
        }

        return null;
    }
    /**
     * 根据ID获取用户名称
     * @param id  用户ID
     * @return  用户名称
     */
    public static String getUsernameById(String id){
        UserDao userDao = new UserDao();
        return userDao.getUsernameById(id);
    }

    /**
     * 获取用户有的系统代码
     * @return   List<String>
     */
    public static List<String> getLoginUserHaveSystemAppIds(){
        String token = getLoginToken();
        Object obj = redisHelper.hget(token,Constants.UserSession.USER_HAVA_SYSTEM_APPIDS);
        if(obj != null){
            return IdUtil.getIdsByStr(obj.toString());
        }
        return Lists.newArrayList();
    }

    /**
     * 获取用户有的系统ID
     * @return List<String>
     */
    public static List<String> getLoginUserHaveSystemIds(){
        String token = getLoginToken();
        Object obj = redisHelper.hget(token,Constants.UserSession.USER_HAVA_SYSTEM_IDS);
        if(obj != null){
            return IdUtil.getIdsByStr(obj.toString());
        }
        return Lists.newArrayList();
    }


    /**
     * 根据用户名称删除用户缓存
     * @param usernames  用户名称
     */
    public static void delUserCache(List<String> usernames){
        if(!CollectionUtils.isEmpty(usernames)){
            usernames.forEach(item->{
                if(!StringUtils.isBlank(item)){
                    String token = redisHelper.hget(Constants.UserSession.userNameToken, item) == null
                            ? "" : (String) redisHelper.hget(Constants.UserSession.userNameToken, item);
                    if (!token.equals("")){
                        redisHelper.del(token);
                        redisHelper.hdel(Constants.UserSession.userNameToken, item);
                    }
                }
            });
        }

    }
}
