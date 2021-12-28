package com.sofn.common.utils.shiro;

import org.apache.shiro.authc.AuthenticationToken;

public class JWTToken implements AuthenticationToken  {
  public static final String TOKEN = "Authorization";
  public static final String SIGN_TYPE_SHARE = "share";

	// 秘钥
	private String token;

	public JWTToken(String token) {
		this.token = token;
	}

	@Override
	public Object getPrincipal() {
		return token;
	}

	@Override
	public Object getCredentials() {
		return token;
	}
}
