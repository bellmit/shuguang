package com.sofn.core.support.login;

import com.sofn.core.config.Resources;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import com.sofn.core.exception.LoginException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sofn
 * @version 2016年5月20日 下午3:44:45
 */
public final class LoginHelper {
	private LoginHelper() {
	}

	/** 用户登录 */
	public static Boolean login(String account, String password) {
		// shiro的类, 根据用户名和加密后的密码生成token
		UsernamePasswordToken token = new UsernamePasswordToken(account, password);
		token.setRememberMe(true);
		// 得到当前执行的用户
		Subject subject = SecurityUtils.getSubject();
		try {
			// 为当前用户设置 token
			subject.login(token);
			// 标识为已认证
			return subject.isAuthenticated();
		} catch (LockedAccountException e) {
			throw new LoginException(Resources.getMessage("ACCOUNT_LOCKED", token.getPrincipal()));
		} catch (DisabledAccountException e) {
			throw new LoginException(Resources.getMessage("ACCOUNT_DISABLED", token.getPrincipal()));
		} catch (ExpiredCredentialsException e) {
			throw new LoginException(Resources.getMessage("ACCOUNT_EXPIRED", token.getPrincipal()));
		} catch (Exception e) {
			throw new LoginException(Resources.getMessage("LOGIN_FAIL"), e);
		}
	}

	public static Map<String,Object> login(String account, String password, String type){
		Map<String, Object> result = new HashMap<>();
		// shiro的类, 根据用户名和加密后的密码生成token
		UsernamePasswordToken token = new UsernamePasswordToken(account, password);
		token.setRememberMe(true);
		// 得到当前执行的用户
		Subject subject = SecurityUtils.getSubject();
		try {
			// 为当前用户设置 token,然后交由 shiro 认证
			subject.login(token);
			// 标识为已认证
			result.put("isAuthenticated",subject.isAuthenticated());
			result.put("token",token);
			result.put("session",subject.getSession());
			return result;
		} catch (LockedAccountException e) {
			throw new LoginException(Resources.getMessage("ACCOUNT_LOCKED", token.getPrincipal()));
		} catch (DisabledAccountException e) {
			throw new LoginException(Resources.getMessage("ACCOUNT_DISABLED", token.getPrincipal()));
		} catch (ExpiredCredentialsException e) {
			throw new LoginException(Resources.getMessage("ACCOUNT_EXPIRED", token.getPrincipal()));
		} catch (Exception e) {
			throw new LoginException(Resources.getMessage("LOGIN_FAIL"), e);
		}
	}
}
