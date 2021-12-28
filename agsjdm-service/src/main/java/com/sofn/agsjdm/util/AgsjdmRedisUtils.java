package com.sofn.agsjdm.util;

import com.sofn.common.exception.SofnException;
import com.sofn.common.utils.RedisHelper;
import com.sofn.common.utils.SpringContextHolder;
import com.sofn.common.utils.UserUtil;

import java.util.Objects;

/**
 * @Description 专门用户防止重复提交封装的工具类, 代码基本从fdpi搬过来的
 * @Author wg
 * @Date 2021/4/21 16:25
 **/
public class AgsjdmRedisUtils {
    public static void checkReSubmit(String redisKey, long time) {
        RedisHelper redisHelper = (RedisHelper) SpringContextHolder.getBean(RedisHelper.class);
        String token = UserUtil.getLoginToken();
        Object redisToken = redisHelper.get(redisKey);
        if (Objects.nonNull(redisToken)) {
            throw new SofnException("请勿重复提交!");
        } else {
            redisHelper.set(redisKey, token, time);
        }
    }
}
