package com.sofn.agpjpm.util;

import com.sofn.agpjpm.constants.Constants;
import com.sofn.agpjpm.sysapi.SysRegionApi;
import com.sofn.common.exception.SofnException;
import com.sofn.common.utils.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class RedisUserUtil {

    private static SysRegionApi sysRegionApi = SpringContextHolder.getBean(SysRegionApi.class);

    /**
     * 获取区域中文名称
     */
    public static String[] getRegionNames(String province, String city, String county) {
        String[] res = new String[3];
        String[] regionNames = null;
        if (!StringUtils.isBlank(county)) {
            regionNames = ApiUtil.getResultStrArr(sysRegionApi.getRegionNamesByCodes(province + "," + city + "," + county));
            res[0] = regionNames[0];
            res[1] = regionNames[1];
            res[2] = regionNames[2];
        } else if (!StringUtils.isBlank(city)) {
            regionNames = ApiUtil.getResultStrArr(sysRegionApi.getRegionNamesByCodes(province + "," + city));
            res[0] = regionNames[0];
            res[1] = regionNames[1];
        } else if (!StringUtils.isBlank(province)) {
            regionNames = ApiUtil.getResultStrArr(sysRegionApi.getRegionNamesByCodes(province));
            res[0] = regionNames[0];
        }
        return res;
    }

    /**
     * 完善查询参数
     */
    public static void perfectParams(Map<String, Object> params) {
        List<String> loginUserRoleCodeList = UserUtil.getLoginUserRoleCodeList();
        if (!CollectionUtils.isEmpty(loginUserRoleCodeList)
                && (loginUserRoleCodeList.contains(Constants.EXPERT_ROLE_CODE)
                || loginUserRoleCodeList.contains(Constants.EXPERT_ROLE_AGPJYZ))) {
            params.put("createUserId", UserUtil.getLoginUserId());
        } else {
            //验证参数范围是否为一年一县
            if (Objects.nonNull(params.get("type"))) {
                validParamsRange(params);
            }
        }
    }

    /**
     * 导出数据验证参数范围是否为一年一县
     */
    private static void validParamsRange(Map<String, Object> params) {
        if (Objects.isNull(params.get("county"))) {
            throw new SofnException("请选择需要导出的县级区域");
        }
        Object startTime = params.get("startTime");
        Object endTime = params.get("endTime");
        if (Objects.isNull(startTime) || Objects.isNull(endTime)) {
            throw new SofnException("请选择开始日期和结束日期");
        }
        Date startDate = DateUtils.stringToDate(String.valueOf(startTime), DateUtils.DATE_PATTERN);
        Date maxEndDate = DateUtils.addDateMonths(startDate, 12);
        Date endDate = DateUtils.stringToDate(String.valueOf(endTime), DateUtils.DATE_TIME_PATTERN);
        if (maxEndDate.before(endDate)) {
            throw new SofnException("日期范围不能超过一年");
        }
    }

    /**
     * 验证重复提交
     */
    public static void validReSubmit(String type) {
        Long defaultTime = 5L;
        validReSubmit(type, defaultTime);
    }

    /**
     * 验证重复提交
     */
    public static void validReSubmit(String type, Long time) {
        String token = UserUtil.getLoginToken();
        String redisKey = token + "_" + type;
        RedisHelper redisHelper = (RedisHelper) SpringContextHolder.getBean(RedisHelper.class);
        Object redisToken = redisHelper.get(redisKey);
        if (Objects.nonNull(redisToken)) {
            throw new SofnException("请勿重复提交!");
        } else {
            redisHelper.set(redisKey, token, time);
        }
    }

}
