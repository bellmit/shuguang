package com.sofn.core.shiro;

import com.sofn.core.base.RedisService;
import com.sofn.core.config.Global;
import com.sofn.core.config.Resources;
import com.sofn.core.exception.LoginException;
import com.sofn.core.util.JwtHelper;
import io.jsonwebtoken.Claims;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/** Created by Code.Ai on 16/8/10. Description: */
public class StatelessRealm extends AuthorizingRealm {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  @Autowired private RedisService redisService;

  @Override
  public boolean supports(AuthenticationToken token) {
    // 仅支持StatelessToken类型的Token
    return token instanceof StatelessToken;
  }

  // 授权
  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

    SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
    String userId = "1";

    info.addStringPermission("user");
    return info;
    // </editor-fold>
  }

  // 登录
  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
      throws AuthenticationException {
    //        if (uniqueToken.equals("app")) {
    //            return new SimpleAuthenticationInfo(uniqueToken, "app_token", getName());
    //        } else {
    //            return new SimpleAuthenticationInfo(uniqueToken, "pc_token", getName());
    //        }
    StatelessToken statelessToken = (StatelessToken) token;
    String uniqueToken = statelessToken.getUniqueToken();

    if (uniqueToken == null) {
      throw new LoginException(Resources.getMessage("LOGIN_FAIL"));
    }
    // 解析 token
    Claims claims = JwtHelper.parseJWT(uniqueToken, Global.getConfig("sofn.api.base64Secret"));
    if (claims != null) {

      String account = (String) claims.get("userName");
      String userId = (String) claims.get("userId");
      String role = (String) claims.get("role");
      String userName = (String) token.getPrincipal();

      String password = (String) token.getCredentials();

      return new SimpleAuthenticationInfo(
          uniqueToken, redisService.getAccessToken(account + userId), getName());
    }
    throw new LoginException(Resources.getMessage("LOGIN_FAIL"));
  }
}
