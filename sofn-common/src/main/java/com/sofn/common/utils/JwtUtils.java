package com.sofn.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.sofn.common.config.YmlConfig;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Calendar;
import java.util.Date;

/**
 * JWT工具类
 */
@Component
public class JwtUtils {

    // token允许的时间差
    private static final int TOKEN_DIFF = 1000;
    private static YmlConfig YMLCONFIG;

    @Autowired
    private YmlConfig ymlConfig;

    @PostConstruct
    public void init(){
      YMLCONFIG = ymlConfig;
    }

    /**
     * 生成签名
     */
    public static String generateToken(String username){
        Calendar nowCal = Calendar.getInstance();
        nowCal.setTimeInMillis(Calendar.getInstance().getTimeInMillis()-TOKEN_DIFF);
        //算法
        Algorithm algorithm = Algorithm.HMAC256(YMLCONFIG.secertKey);

        String token = JWT.create()
                //签发人
                .withIssuer(YMLCONFIG.issuer)
                //签发时间
                .withIssuedAt(nowCal.getTime())
                //过期时间
                .withExpiresAt(new Date(nowCal.getTimeInMillis() + YMLCONFIG.tokenExpireTime))
                //保存身份标识
                .withClaim("username", username)
                .sign(algorithm);
        return token;
    }

    /**
     * 生成签名
     */
    public static String generateToken(String username, String signType){
      Calendar nowCal = Calendar.getInstance();
      nowCal.setTimeInMillis(Calendar.getInstance().getTimeInMillis()-TOKEN_DIFF);
      //算法
      Algorithm algorithm = Algorithm.HMAC256(YMLCONFIG.secertKey);

      String token = JWT.create()
        //签发人
        .withIssuer(YMLCONFIG.issuer)
        //签发时间
        .withIssuedAt(nowCal.getTime())
        //过期时间
        .withExpiresAt(new Date(nowCal.getTimeInMillis() + YMLCONFIG.tokenExpireTime))
        //保存身份标识
        .withClaim("username", username)
        .withClaim("type", StringUtils.isBlank(signType)?"":signType)
        .sign(algorithm);
      return token;
    }

  /**
   * 生成签名
   */
  public static String generateToken(String username, String signType, long tokenExpireTime){
    Calendar nowCal = Calendar.getInstance();
    nowCal.setTimeInMillis(Calendar.getInstance().getTimeInMillis()-TOKEN_DIFF);
    //算法
    Algorithm algorithm = Algorithm.HMAC256(YMLCONFIG.secertKey);

    String token = JWT.create()
      //签发人
      .withIssuer(YMLCONFIG.issuer)
      //签发时间
      .withIssuedAt(nowCal.getTime())
      //过期时间
      .withExpiresAt(new Date(nowCal.getTimeInMillis() + tokenExpireTime))
      //保存身份标识
      .withClaim("username", username)
      .withClaim("type", StringUtils.isBlank(signType)?"":signType)
      .sign(algorithm);
    return token;
  }

    /**
     * 验证token
     */
    public static boolean verify(String token){
        try {
            //算法
            Algorithm algorithm = Algorithm.HMAC256(YMLCONFIG.secertKey);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(YMLCONFIG.issuer)
                    .build();
            verifier.verify(token);
            return true;
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

    /**
     * 从token获取username
     */
    public static String getUsername(String token){
        try{
            return JWT.decode(token).getClaim("username").asString();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return "";
    }

    /**
     * 从token获取Type
     */
    public static String getSignType(String token){
      try{
        return JWT.decode(token).getClaim("type").asString();
      }catch(Exception ex){
        ex.printStackTrace();
      }
      return "";
    }

  /**
   * 从token获取exp
   */
  public static String getExp(String token){
    try{
      return JWT.decode(token).getClaim("exp").asString();
    }catch(Exception ex){
      ex.printStackTrace();
    }
    return "";
  }
}
