package com.sofn.agpjyz.util;

import com.sofn.agpjyz.constants.Constants;
import com.sofn.agpjyz.enums.ProcessEnum;
import com.sofn.agpjyz.sysapi.SysFileApi;
import com.sofn.agpjyz.vo.OrganizationInfo;
import com.sofn.common.exception.SofnException;
import com.sofn.common.utils.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * redis用户工具
 *
 * @Author yumao
 * @Date 2020/2/26 15:26
 **/
public class RedisUserUtil {

    private static SysFileApi sysApi = SpringContextHolder.getBean(SysFileApi.class);

    private static final String str = "当前用户所属机构";

    /**
     * 验证用户机构配置是否满足要求
     */
    public static void validLoginUser(String expertReport, String province, String city, String county) {
        OrganizationInfo orgInfo = getOrgInfo();
        if (BoolUtils.N.equals(expertReport)) {
            if (StringUtils.isBlank(orgInfo.getThirdOrg())) {
                throw new SofnException(str + "未设置是否行政机构标识!");
            }
            if (StringUtils.isBlank(orgInfo.getOrganizationLevel())) {
                throw new SofnException(str + "未设置机构级别!");
            }
            String regionLastCode = orgInfo.getRegionLastCode();
            if (StringUtils.isBlank(regionLastCode)) {
                throw new SofnException(str + "未设置末级区域码!");
            }
            String[] regionName = getRegionNames(regionLastCode.substring(0, 2) + "0000",
                    regionLastCode.substring(0, 4) + "00", regionLastCode);
            if (!county.startsWith(province.substring(0, 2))) {
                throw new SofnException("不能跨省提交,区域只能选择" + regionName[0] + "的下面的县级");
            }
        }
        if (StringUtils.isBlank(orgInfo.getOrganizationName())) {
            throw new SofnException(str + "未设置机构名称!");
        }
    }

    /**
     * 获取区域中文名称
     */
    public static String[] getRegionNames(String province, String city, String county) {
        String[] res = new String[3];
        String[] regionNames = null;
        if (!StringUtils.isBlank(county)) {
            regionNames = ApiUtil.getResultStrArr(sysApi.getRegionNamesByCodes(province + "," + city + "," + county));
            res[0] = regionNames[0];
            res[1] = regionNames[1];
            res[2] = regionNames[2];
        } else if (!StringUtils.isBlank(city)) {
            regionNames = ApiUtil.getResultStrArr(sysApi.getRegionNamesByCodes(province + "," + city));
            res[0] = regionNames[0];
            res[1] = regionNames[1];
        } else if (!StringUtils.isBlank(province)) {
            regionNames = ApiUtil.getResultStrArr(sysApi.getRegionNamesByCodes(province));
            res[0] = regionNames[0];
        }
        return res;
    }

    /**
     * 完善查询参数
     */
    public static void perfectParams(Map<String, Object> params) {
        perfectParams(params, null);
    }

    /**
     * 完善查询参数
     */
    public static void perfectParams2(Map<String, Object> params) {
        perfectParams2(params, null);
    }

    /**
     * 完善查询参数
     */
    public static void perfectParams(Map<String, Object> params, String export) {
        List<String> loginUserRoleCodeList = UserUtil.getLoginUserRoleCodeList();
        if (!CollectionUtils.isEmpty(loginUserRoleCodeList)
                && loginUserRoleCodeList.contains(Constants.EXPERT_ROLE_CODE)) {
            params.put("createUserId", UserUtil.getLoginUserId());
        } else {
            OrganizationInfo orgInfo = getOrgInfo();
            String organizationLevel = orgInfo.getOrganizationLevel();
            String regionLastCode = orgInfo.getRegionLastCode();
            params.put("organizationLevel", organizationLevel);
            int length = regionLastCode.length();
            //当前机构用户级别是区县,查询参数需要增加省 市 区
            if (Constants.REGION_TYPE_COUNTY.equals(organizationLevel) && length >= 6) {
//                params.put("province", regionLastCode.substring(0, 2) + "0000");
                params.put("createUserId", UserUtil.getLoginUserId());
            }
            //当前机构用户级别是市,查询参数需要增加省 市
            else if (Constants.REGION_TYPE_CITY.equals(organizationLevel) && length >= 4) {
                params.put("province", regionLastCode.substring(0, 2) + "0000");
                params.put("city", regionLastCode.substring(0, 4) + "00");
            }
            //机构用户级别是省,查询参数需要增加省
            else if (Constants.REGION_TYPE_PROVINCE.equals(organizationLevel) && length >= 6) {
                params.put("province", regionLastCode.substring(0, 2) + "0000");
            }
            //机构用户级别是部,不需要自动增加区域参数
            else if (Constants.REGION_TYPE_MINISTRY.equals(organizationLevel)) {
            }
            //验证参数范围是否为一年一县
            if (StringUtils.isNotBlank(export)) {
                validParamsRange(params);
            }
        }
    }

    /**
     * 完善查询参数
     */
    public static void perfectParams2(Map<String, Object> params, String export) {
        List<String> loginUserRoleCodeList = UserUtil.getLoginUserRoleCodeList();
        if (!CollectionUtils.isEmpty(loginUserRoleCodeList)
                && loginUserRoleCodeList.contains(Constants.EXPERT_ROLE_CODE)) {
            params.put("createUserId", UserUtil.getLoginUserId());
        } else {
            OrganizationInfo orgInfo = getOrgInfo();
            String organizationLevel = orgInfo.getOrganizationLevel();
            String regionLastCode = orgInfo.getRegionLastCode();
            params.put("organizationLevel", organizationLevel);
            int length = regionLastCode.length();
            //当前机构用户级别是区县,查询参数需要增加省 市 区
            if (Constants.REGION_TYPE_COUNTY.equals(organizationLevel) && length >= 6) {
                params.put("createUserId", UserUtil.getLoginUserId());
            }
            //当前机构用户级别是市,查询参数需要增加省 市
            else if (Constants.REGION_TYPE_CITY.equals(organizationLevel) && length >= 4) {
                params.put("province2", regionLastCode.substring(0, 2) + "0000");
                params.put("city2", regionLastCode.substring(0, 4) + "00");
                params.put("createUserId2", UserUtil.getLoginUserId());
            }
            //机构用户级别是省,查询参数需要增加省
            else if (Constants.REGION_TYPE_PROVINCE.equals(organizationLevel) && length >= 6) {
                params.put("province2", regionLastCode.substring(0, 2) + "0000");
                params.put("createUserId2", UserUtil.getLoginUserId());
            }
            //机构用户级别是部,不需要自动增加区域参数
            else if (Constants.REGION_TYPE_MINISTRY.equals(organizationLevel)) {
            }
            //验证参数范围是否为一年一县
            if (StringUtils.isNotBlank(export)) {

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
     * 获取当前用户机构信息
     */
    public static OrganizationInfo getOrgInfo() {
        String orgInfoJosn = UserUtil.getLoginUserOrganizationInfo();
        if (org.springframework.util.StringUtils.isEmpty(orgInfoJosn)) {
            throw new SofnException("未获取到登录用户所属机构信息!");
        }
        return JsonUtils.json2obj(orgInfoJosn, OrganizationInfo.class);
    }

    public static Boolean[] getHandlePower(String status, String expertReport) {
        //审核 撤回 修改操作权限
        Boolean handlePower[] = {true, true, true};
        List<String> loginUserRoleCodeList = UserUtil.getLoginUserRoleCodeList();
        if (!CollectionUtils.isEmpty(loginUserRoleCodeList)) {
            //专家
            if (loginUserRoleCodeList.contains(Constants.EXPERT_ROLE_CODE)) {
                handlePower[0] = false;
                if (!ProcessEnum.REPORT.getKey().equals(status)) {
                    handlePower[1] = false;
                }
                if (ProcessEnum.REPORT.getKey().equals(status) ||
                        ProcessEnum.CITY_AUDIT.getKey().equals(status) ||
                        ProcessEnum.PROVINCE_AUDIT.getKey().equals(status) ||
                        ProcessEnum.FINAL_AUDIT.getKey().equals(status)) {
                    handlePower[2] = false;
                }
            } else {
                OrganizationInfo orgInfo = getOrgInfo();
                String organizationLevel = orgInfo.getOrganizationLevel();
                if (Constants.REGION_TYPE_COUNTY.equals(organizationLevel)) {
                    handlePower[0] = false;
                    if (!ProcessEnum.REPORT.getKey().equals(status)) {
                        handlePower[1] = false;
                    }
                    if (ProcessEnum.REPORT.getKey().equals(status) ||
                            ProcessEnum.CITY_AUDIT.getKey().equals(status) ||
                            ProcessEnum.PROVINCE_AUDIT.getKey().equals(status) ||
                            ProcessEnum.FINAL_AUDIT.getKey().equals(status)) {
                        handlePower[2] = false;
                    }
                } else {
                    handlePower[1] = false;
                    handlePower[2] = false;
                    if (Constants.REGION_TYPE_CITY.equals(organizationLevel)
                            && !ProcessEnum.REPORT.getKey().equals(status)) {
                        handlePower[0] = false;
                    } else if (Constants.REGION_TYPE_PROVINCE.equals(organizationLevel)
                            && !ProcessEnum.CITY_AUDIT.getKey().equals(status)) {
                        handlePower[0] = false;
                    } else if (Constants.REGION_TYPE_MINISTRY.equals(organizationLevel)) {
                        if (ProcessEnum.FINAL_AUDIT_RETURN.getKey().equals(status)) {
                            handlePower[0] = false;
                        }
                    }
                }
            }
        }
        return handlePower;
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
