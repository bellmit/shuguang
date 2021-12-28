package com.sofn.gatewayservice.utils;

import com.sofn.common.config.YmlConfig;
import com.sofn.common.utils.JwtUtils;
import com.sofn.common.utils.RedisHelper;
import com.sofn.common.utils.SpringContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

@Slf4j
public class GatewayCacheUtils {
    private static final String KEY_REPLACE_TOKEN = "GATEWAY_REPLACEMENT_TOKEN";

    private static RedisHelper redisHelper = SpringContextHolder.getBean(RedisHelper.class);

    private static YmlConfig ymlConfig = SpringContextHolder.getBean(YmlConfig.class);

    public static void putReplaceToken(String replaceToken){
      if (checkRedisHelper()){
        if (StringUtils.isNotBlank(replaceToken)){
            redisHelper.set(KEY_REPLACE_TOKEN, replaceToken, ymlConfig.tokenExpireTime);
        }
      }
    }

    public static String getReplaceToken(){
      String replaceToken = "";
      if (checkRedisHelper()){
        Object object = redisHelper.get(KEY_REPLACE_TOKEN);
        if (object == null) {
          return "";
        }

        Object valueObject = redisHelper.get(object.toString());
        if (valueObject == null) {
          return "";
        }

        replaceToken = object.toString();
        if (StringUtils.isBlank(replaceToken) || !JwtUtils.verify(replaceToken)){
          return "";
        }
      }

      return replaceToken;
    }

    private static boolean checkRedisHelper(){
        return redisHelper != null;
    }
}
