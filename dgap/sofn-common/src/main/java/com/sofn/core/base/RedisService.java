package com.sofn.core.base;

import com.sofn.core.constant.ApiConstants;
import com.sofn.core.constant.OperateLog;
import com.sofn.core.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * token处理类
 * @author dong4j
 * 2016年09月22日  下午 5:31:59
 * 修改 redis 操作类为RedisUtil
 */
@Service
public class RedisService {
    private static Logger logger = LoggerFactory.getLogger(RedisService.class);

    /**
     * 登录成功后，生成accesstoken，并放验证码到redis服务器
     */
    public String putAccessToken(String userNameAndId, String token) {
        logger.info("putAccessToken:" + token);
        RedisUtil.set(userNameAndId + "_" + ApiConstants.PCTOKEN, token, ApiConstants.APP_TOKEN_TIMEOUT);
        return token;
    }

    /**
     * 验证时，根据用户名，获取redis里面的accesstoken
     * @return
     */
    public String getAccessToken(String userNameAndId) {
        return (String)RedisUtil.get(userNameAndId + "_" + ApiConstants.PCTOKEN);
    }

    /**
     * 登录成功后，生成token，并放验证码到redis服务器
     */
    public String putPcToken(String userNameAndId, String token) {
        RedisUtil.set(userNameAndId + "_" + ApiConstants.PCTOKEN, token, ApiConstants.APP_TOKEN_TIMEOUT);
        return token;
    }

    /**
     * 验证时，根据用户名，获取redis里面的token
     * @return
     */
    public String getPcToken(String userNameAndId) {
        return (String)RedisUtil.get(userNameAndId + "_" + ApiConstants.PCTOKEN);
    }

    /**
     * 生成验证码，并将验证码存入redis
     *
     * @param phoneNum
     * @return
     */
    public String putIdentifyingCode(String phoneNum, String code) {
        RedisUtil.set(phoneNum + "_" + ApiConstants.CODE, code, ApiConstants.PC_TOKEN_TIMEOUT);
        return code;
    }

    ///**
    // * 从redis获取验证码
    // *
    // * @param phoneNum
    // * @return
    // */
    //public String getIdentifyingCode(String phoneNum) {
    //    return (String)RedisUtil.get(phoneNum + "_" + ApiConstants.CODE);
    //}
    //
    ///**
    // * 从redis获取列表
    // *
    // * @param <T>
    // * @param key
    // * @return
    // */
    //public <T> List<T> getList(String key) {
    //    return (List<T>) JedisUtils.getObject(key);
    //}
    //
    ///**
    // * 把list存入redis
    // *
    // * @param <T>
    // * @param key
    // * @param
    // */
    //public <T> void setList(String key, List<T> list) {
    //    JedisUtils.setObject(key, list, 0);
    //}

    public void deletePcToken(String userNameAndId) {
        RedisUtil.del(userNameAndId + "_" + ApiConstants.PCTOKEN);
    }

    public void deleteAppToken(String userNameAndId) {
        RedisUtil.del(userNameAndId + "_" + ApiConstants.APPTOKEN);
    }

    public List<OperateLog> queryOperateLog(){
        List<OperateLog> logs = RedisUtil.keys();
//        logger.debug(logs.toString());
        return logs;
    }

    public List<OperateLog> queryDataOperateLog(){
        List<OperateLog> logs = RedisUtil.newkeys();
//        logger.debug(logs.toString());
        return logs;
    }


    public List<OperateLog> queryOperateLog(String operateUsername){
        List<OperateLog> logs = RedisUtil.getOperateLogsByCondition(operateUsername);
//        logger.debug(logs.toString());
        return logs;
    }
}
