package com.sofn.core.shiro;

import java.util.Map;
import org.apache.shiro.authc.AuthenticationToken;

/** Created by Code.Ai on 16/8/10. Description: */
public class StatelessToken implements AuthenticationToken {
  private static final long serialVersionUID = 7725129693827821256L;
  private String uniqueToken;
  private Map<String, String[]> params;

  public StatelessToken(String uniqueToken, Map<String, String[]> params) {
    this.uniqueToken = uniqueToken;
    this.params = params;
  }

  public StatelessToken(String uniqueToken) {
    this.uniqueToken = uniqueToken;
  }

  public String getUniqueToken() {
    return uniqueToken;
  }

  public void setUniqueToken(String uniqueToken) {
    this.uniqueToken = uniqueToken;
  }

  public Map<String, String[]> getParams() {
    return params;
  }

  public void setParams(Map<String, String[]> params) {
    this.params = params;
  }

  @Override
  public Object getPrincipal() {
    return uniqueToken;
  }

  @Override
  public Object getCredentials() {
    return uniqueToken;
  }
}
