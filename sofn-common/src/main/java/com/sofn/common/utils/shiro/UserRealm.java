package com.sofn.common.utils.shiro;

import com.sofn.common.constants.Constants;
import com.sofn.common.utils.*;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 认证
 */
@Component
public class UserRealm extends AuthorizingRealm {

    @Autowired
	  RedisHelper redisHelper;

    private boolean isSysUserAuth = false;

	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof JWTToken;
	}

    /**
     * 授权(验证权限时调用)
     */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String token = (String) principals.getPrimaryPrincipal();
		Set<String> permsSet = new HashSet<>();
		//获取用户角色列表
		Object object = redisHelper.hget(token,Constants.UserSession.userRoles);
		List<Map<String,Object>> roleList = object==null?null:JsonUtils.json2ListkeyMap(object.toString());
		if (roleList != null && roleList.size()>0) {
			// 获取单个角色下的权限列表
			for (Map<String,Object> roleMap : roleList){
				if (roleMap == null){
					continue;
				}
				// 根据角色获取权限
				String roleId = roleMap.get("id")==null?"":roleMap.get("id").toString();
				String permissions = (String) redisHelper.hget(Constants.ROLE_HAVA_PERMISSION_CACHE, roleId);
				if(!StringUtils.isBlank(permissions)){
					List<String> idsByStr = IdUtil.getIdsByStr(permissions);
					permsSet.addAll(idsByStr);
				}
			}
		}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setStringPermissions(permsSet);
		return info;
	}

	/**
	 * 认证(登录时调用)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
      String token = (String) authcToken.getCredentials();
      String username = JwtUtils.getUsername(token);

      if (StringUtils.isEmpty(username) || !JwtUtils.verify(token)) {
        throw new UnauthorizedException("token无效");
      }

      if (isSysUserAuth) {
        Object object = redisHelper.hget(token,Constants.UserSession.isSysUser);
        boolean sysUserAuth = object instanceof String && StringUtils.equals(String.valueOf(object),BoolUtils.Y);
        if (!sysUserAuth) {
          throw new UnauthorizedException("token无效");
        }
      }

      return new SimpleAuthenticationInfo(token, token, "sofn_realm");
	}

	@Override
	public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
		//HashedCredentialsMatcher shaCredentialsMatcher = new HashedCredentialsMatcher();
		//shaCredentialsMatcher.setHashAlgorithmName(ShiroUtils.hashAlgorithmName);
		//shaCredentialsMatcher.setHashIterations(ShiroUtils.hashIterations);
		super.setCredentialsMatcher(credentialsMatcher);
	}

	public void enableSysUserAuth(){
	  isSysUserAuth = true;
  }
}
