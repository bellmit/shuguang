package com.sofn.fdpi.util;

import com.sofn.common.exception.SofnException;
import com.sofn.common.utils.BoolUtils;
import com.sofn.common.utils.RedisHelper;
import com.sofn.common.utils.SpringContextHolder;
import com.sofn.common.utils.UserUtil;
import com.sofn.fdpi.vo.OrganizationInfo;
import org.apache.commons.lang.StringUtils;

import java.util.Objects;

/**
 * redis用户工具
 *
 * @Author yumao
 * @Date 2020/2/26 15:26
 **/
public class RedisUserUtil {


    private static final String str = "当前用户所属机构";


    public static void validLoginUser(OrganizationInfo orgInfo) {
        if (StringUtils.isBlank(orgInfo.getThirdOrg())) {
            throw new SofnException(str + "未设置是否行政机构标识!");
        }
        if (BoolUtils.Y.equals(orgInfo.getThirdOrg()) && StringUtils.isBlank(orgInfo.getOrganizationLevel())) {
            throw new SofnException(str + "未设置机构级别!");
        }
        if (BoolUtils.Y.equals(orgInfo.getThirdOrg()) && StringUtils.isBlank(orgInfo.getRegionLastCode())) {
            throw new SofnException(str + "未设置末级区域码!");
        }
        if (StringUtils.isBlank(orgInfo.getOrganizationName())) {
            throw new SofnException(str + "未设置机构名称!");
        }
    }

    /**
     * 验证重复提交
     */
    public static String validReSubmit(String type) {
        Long defaultTime = 5L;
        return validReSubmit(type, defaultTime);
    }

    /**
     * 验证重复提交
     */
    public static String validReSubmit(String type, String id) {
        Long defaultTime = 5L;
        return validReSubmit(type, defaultTime, id);
    }

    /**
     * 验证重复提交
     */
    public static String validReSubmit(String type, Long time) {
        return validReSubmit(type, time, "");
    }

    /**
     * 验证重复提交
     */
    public static String validReSubmit(String type, Long time, String id) {
        String token = UserUtil.getLoginToken();
        String redisKey = token + "_" + type + "_" + id;
        RedisHelper redisHelper = (RedisHelper) SpringContextHolder.getBean(RedisHelper.class);
        Object redisToken = redisHelper.get(redisKey);
        if (Objects.nonNull(redisToken)) {
            throw new SofnException("请勿重复提交!");
        } else {
            redisHelper.set(redisKey, token, time);
        }
        return redisKey;
    }

    public static String validApplyCode(String applyCode) {
        Long defaultTime = 15L;
        return validApplyCode(applyCode, defaultTime);
    }

    public static String validApplyCode(String applyCode, Long time) {
        RedisHelper redisHelper = (RedisHelper) SpringContextHolder.getBean(RedisHelper.class);
        Object redisApplyCode = redisHelper.get(applyCode);
        if (Objects.nonNull(redisApplyCode)) {
            throw new SofnException("申请单号（" + applyCode + "）出现重复，请稍后操作!");
        } else {
            redisHelper.set(applyCode, applyCode, time);
        }
        return applyCode;

    }


    public static void delRedisKey(String redisKey) {
        RedisHelper redisHelper = (RedisHelper) SpringContextHolder.getBean(RedisHelper.class);
        redisHelper.del(redisKey);
    }

    static String key = "FDPI_INSERT_SIGNBOARD";

    public static void hdel(String key, String item) {
        RedisHelper redisHelper = (RedisHelper) SpringContextHolder.getBean(RedisHelper.class);
        redisHelper.hdel(key, item);
    }

    public static Object hget(String key, String item) {
        RedisHelper redisHelper = (RedisHelper) SpringContextHolder.getBean(RedisHelper.class);
        return redisHelper.hget(key, item);
    }

    public static boolean hset(String key, String item, String val) {
        Long defaultTime = 1800L;
        RedisHelper redisHelper = (RedisHelper) SpringContextHolder.getBean(RedisHelper.class);
        return redisHelper.hset(key, item, val, defaultTime);
    }

    public static boolean hset(String key, String item, Object val, Long time) {
        RedisHelper redisHelper = (RedisHelper) SpringContextHolder.getBean(RedisHelper.class);
        return redisHelper.hset(key, item, val, time);
    }
}
