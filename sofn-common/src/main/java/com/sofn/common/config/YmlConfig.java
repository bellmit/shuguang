package com.sofn.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *@Description 配置文件属性
 *@Author quzhijie
 *@Date 2020/4/20 19:02
 *@Version 1.0
 **/
@Component
public class YmlConfig {
  @Value("${appid:}")
  public String appId;

  @Value("${spring.application.name:}")
  public String appName;

  @Value("${sofn.token.secert-key:}")
  public String secertKey;

  @Value("${sofn.token.token-expire-time:0}")
  public long tokenExpireTime;

  @Value("${sofn.token.issuer:}")
  public String issuer;

  @Value("${sofn.token.remember-expire-time:0}")
  public long rememberExpireTime;

  @Value("${sofn.captcha.expire-time:0}")
  public long captchaExpireTime;

  @Value("${jasypt.encryptor.password}")
  public String jasyptEncryptorPassword;
}
