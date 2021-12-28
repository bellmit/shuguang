package com.sofn.ducss.util;


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.Result;
import com.sofn.common.utils.*;
import com.sofn.ducss.enums.AdministrativeLevelEnum;
import com.sofn.ducss.enums.Constants;
import com.sofn.ducss.enums.RegionLevel;
import com.sofn.ducss.enums.UserRoleType;
import com.sofn.ducss.sysapi.SysApi;
import com.sofn.ducss.sysapi.bean.SysRegionTreeVo;
import com.sofn.ducss.sysapi.bean.SysUserForm;
import com.sofn.ducss.util.vo.AreaRegionCode;
import com.sofn.ducss.util.vo.OrganizationInfo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * SysRegionApi 类的工具类
 */
public class SysRegionUtil {
    private static SysApi sysApi = SpringContextHolder.getBean(SysApi.class);

    /**
     * 转换区域jsoncode为list
     *
     * @param regionJsoncode
     * @return
     */
    public static List<String> getRegionCodeListByStr(String regionJsoncode) {
        if (StringUtils.hasText(regionJsoncode)) {
            return JsonUtils.json2List(regionJsoncode, String.class);
        }
        return null;
    }

    /**
     * 根据用户ID，获取用户信息
     *
     * @param userId
     * @return
     */
    public static SysUserForm getUserOneByUserId(String userId) {
        Result<SysUserForm> result = sysApi.getUserOne(userId);
        if (result != null && result.getData() != null) {
            return result.getData();
        }
        return null;
    }

    /**
     * 根据用户ID，获取用户昵称
     *
     * @param userId
     * @return
     */
    public static String getUserNickNameByUserId(String userId) {
        SysUserForm user = getUserOneByUserId(userId);
        if (user != null)
            return user.getNickname();
        return null;
    }

    /**
     * 自动组装区划名称到属性
     *
     * @param t
     * @param <T>
     * @return
     */
/*    public static <T> T assignmentRegionNameAll(T t) {
        assignmentRegionNameProvince(t);
        assignmentRegionNameCity(t);
        assignmentRegionNameCounty(t);
        return t;
    }*/

    /**
     * 组装省名称
     *
     * @param t
     * @param <T>
     * @return
     */
/*    public static <T> T assignmentRegionNameProvince(T t) {
        t = assignmentRegionName(t, "getProvinceId", "setProvinceName");
        return t;
    }*/

    /**
     * 组装市名称
     *
     * @param t
     * @param <T>
     * @return
     */
/*    public static <T> T assignmentRegionNameCity(T t) {
        t = assignmentRegionName(t, "getCityId", "setCityName");
        return t;
    }*/

    /**
     * 组装区县名称
     *
     * @param t
     * @param <T>
     * @return
     */
/*    public static <T> T assignmentRegionNameCounty(T t) {
        t = assignmentRegionName(t, "getCountyId", "setCountyName");
        return t;
    }*/

    /**
     * 通用组装名称方法
     *
     * @param t
     * @param <T>
     * @return
     */
/*    public static <T> T assignmentRegionName(T t, String getMethodName, String setMethodName) {
        if (t != null) {
            Class<?> tClass = t.getClass();
            try {
                Method getMethod = tClass.getMethod(getMethodName);
                Method setMethod = tClass.getMethod(setMethodName, String.class);
                Object val = getMethod.invoke(t);
                if (val != null) {
                    setMethod.invoke(t, getRegionNameByRegionCode(val.toString()));
                }
            } catch (NoSuchMethodException e) {
                System.out.println("获取方法失败");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return t;
    }*/

    /**
     * 根据区划 code 查询名称
     *
     * @param code
     */
    public static String getRegionNameByRegionCode(String code, String year) {
        if (code != null && !code.isEmpty()) {
            Result<String> result = sysApi.getSysRegionName(code, year != null ? Integer.valueOf(year) : null);
            if (null != result) {
                return result.getData();
            }
        }
        return "";
    }

    /**
     * 根据人区划ids查询名称,省市区县
     *
     * @param codes
     * @return
     */
    public static String getReginNameByRegionCodes(String codes, String year) {
        String regionName = "";
        List<String> regionCodeList = new ArrayList<>(IdUtil.getIdsByStr(codes));
        for (String codeId : regionCodeList) {
            String name = getRegionNameByRegionCode(codeId, year);
            if (StringUtils.hasText(name))
                regionName = regionName + name;
        }
        return regionName;
    }

    /**
     * 转换json字符串为组织机构实体
     *
     * @param orgInfoJsonStr
     * @return
     */
    public static OrganizationInfo getOrgInfoByJsonStr(String orgInfoJsonStr) {
        OrganizationInfo organizationInfo = JsonUtils.json2obj(orgInfoJsonStr, OrganizationInfo.class);
        return organizationInfo;
    }

    /**
     * 检查用户的组织级别是否正确
     *
     * @param level
     * @return OrganizationInfo 组织信息
     */
    public static OrganizationInfo checkUserLevel(RegionLevel level) {
        OrganizationInfo orgInfo = getOrgInfoByJsonStr(UserUtil.getLoginUserOrganizationInfo());
        if (orgInfo == null || !level.getCode().equals(orgInfo.getOrganizationLevel())) {
            throw new SofnException("只有" + level.getDescription() + "才有权限操作");
        }
        return orgInfo;
    }


    /**
     * 获取用户的角色类型
     *
     * @return
     */
    public static UserRoleType getUserRoleType() {
        List<String> roleCodeList = UserUtil.getLoginUserRoleCodeList();

        if (roleCodeList.contains(UserRoleType.EXPERT.getCode()))
            return UserRoleType.EXPERT;
        else if (roleCodeList.contains(UserRoleType.CENTER.getCode()))
            return UserRoleType.CENTER;
        else if (roleCodeList.contains(UserRoleType.PROVINCE.getCode()))
            return UserRoleType.PROVINCE;
        else if (roleCodeList.contains(UserRoleType.CITY.getCode()))
            return UserRoleType.CITY;
        else
            return UserRoleType.COUNTY;
    }

    /**
     * 查上级所有的RegionCode
     *
     * @param lastCode 行政代码
     */
    public static AreaRegionCode getRegionCodeByLastCode(String lastCode, String year) {
        Result<List<SysRegionTreeVo>> result = sysApi.getParentNode(lastCode, year == null ? null : Integer.valueOf(year));


        AreaRegionCode region = new AreaRegionCode();
        String level = null;
        String lastName = null;
        if (result != null && result.getData() != null && result.getData().size() > 0) {
            List<SysRegionTreeVo> treeVos = result.getData();
            region.setProvinceId(treeVos.get(0).getRegionCode());   //默认是省级
            region.setProvinceName(treeVos.get(0).getRegionName());
            level = RegionLevel.PROVINCE.getCode();
            lastName = region.getProvinceName();

            if (treeVos.size() >= 2) {  //市
                region.setCityId(treeVos.get(1).getRegionCode());
                region.setCityName(treeVos.get(1).getRegionName());
                level = RegionLevel.CITY.getCode();
                lastName = region.getCityName();
            }

            if (treeVos.size() >= 3) {  //区县
                region.setCountyId(treeVos.get(2).getRegionCode());
                region.setCountyName(treeVos.get(2).getRegionName());
                level = RegionLevel.COUNTY.getCode();
                lastName = region.getCountyName();
            }
        }

        if (lastCode.equals(Constants.ZHONGGUO_AREA_ID)) {
            lastName = Constants.ZHONGGUO_AREA_NAME;
            level = RegionLevel.MINISTRY.getCode();
        } else if (result == null || result.getData() == null || result.getData().size() == 0) { //未查到区域信息
            ServiceHelpUtil.throwException("区域代码" + lastCode + "未找到行政区划信息");
        }

        region.setLastId(lastCode);
        region.setLastName(lastName);
        region.setRegionLevel(level);


        return region;
    }

    public static AreaRegionCode getRegionCodeByLastCode2(String lastCode, String year) {
        Result<List<SysRegionTreeVo>> result = sysApi.getParentNode(lastCode, year == null ? null : Integer.valueOf(year));


        AreaRegionCode region = new AreaRegionCode();
        String level = null;
        String lastName = null;
        if (result != null && result.getData() != null && result.getData().size() > 0) {
            List<SysRegionTreeVo> treeVos = result.getData();
            region.setProvinceId(treeVos.get(0).getRegionCode());   //默认是省级
            region.setProvinceName(treeVos.get(0).getRegionName());
            level = RegionLevel.PROVINCE.getCode();
            lastName = region.getProvinceName();

            if (treeVos.size() >= 2) {  //市
                region.setCityId(treeVos.get(1).getRegionCode());
                region.setCityName(treeVos.get(1).getRegionName());
                level = RegionLevel.CITY.getCode();
                lastName = region.getCityName();
            }

            if (treeVos.size() >= 3) {  //区县
                region.setCountyId(treeVos.get(2).getRegionCode());
                region.setCountyName(treeVos.get(2).getRegionName());
                level = RegionLevel.COUNTY.getCode();
                lastName = region.getCountyName();
            }
        }

        if (lastCode.equals(Constants.ZHONGGUO_AREA_ID)) {
            lastName = Constants.ZHONGGUO_AREA_NAME;
            level = RegionLevel.MINISTRY.getCode();
        } else if (result == null || result.getData() == null || result.getData().size() == 0) { //未查到区域信息
            return null;
//            ServiceHelpUtil.throwException("区域代码" + lastCode + "未找到行政区划信息");
        }

        region.setLastId(lastCode);
        region.setLastName(lastName);
        region.setRegionLevel(level);


        return region;
    }

    /**
     * 根据lastRegionCode，获取完整省市区名称
     *
     * @param lastCode
     * @return
     */
    public static String getFullRegionNameByLastCode(String lastCode, String year) {
        Result<List<SysRegionTreeVo>> parentTree = sysApi.getParentNode(lastCode, year == null ? null : Integer.valueOf(year));
        StringBuilder regionName = new StringBuilder();
        if (parentTree != null) {
            List<SysRegionTreeVo> treeList = parentTree.getData();
            for (SysRegionTreeVo vo : treeList) {
                regionName.append(vo.getRegionName());
            }
        }
        return regionName.toString();
    }

    /**
     * 获取当前用户的完整区划名
     *
     * @return
     */
    public static String getLoginFullRegionName() {
        return getFullRegionNameByLastCode(getRegLastCodeStr(), null);
    }


    /**
     * 获取当前用户区域代码对象
     *
     * @return
     */
    public static AreaRegionCode getRegionCode() {
        return getRegionCodeByLastCode(getRegLastCodeStr(), null);
    }

    /**
     * 区划对象
     *
     * @return
     */
    public static OrganizationInfo getOrgInfo() {
        String orgInfoJsonStr = UserUtil.getLoginUserOrganizationInfo();
        OrganizationInfo organizationInfo = JsonUtils.json2obj(orgInfoJsonStr, OrganizationInfo.class);
        return organizationInfo;
    }


    /**
     * 获取行政区划代码
     *
     * @return
     */
    public static String getRegLastCodeStr() {
        return getOrgInfo().getRegionLastCode();
    }

    /**
     * 获取子区划的id，并拼接成字符串
     *
     * @param areaId
     * @return
     */
    public static String getChildrenRegionIds(String areaId) {
        List<SysRegionTreeVo> childrenList = getChildrenRegionList(areaId, null);

        return getChildrenRegionIds(childrenList);
    }

    public static String getChildrenRegionIds(List<SysRegionTreeVo> childrenList) {
        List<String> idList = new ArrayList();
        if (childrenList.size() > 0) {
            for (SysRegionTreeVo vo : childrenList) {
                idList.add(vo.getRegionCode());
            }
        }
        return IdUtil.getStrIdsByList(idList);
    }

    public static List<String> getChildrenRegionIdList(String areaId) {
        List<SysRegionTreeVo> childrenList = getChildrenRegionList(areaId, null);

        return getChildrenRegionIdList(childrenList);
    }

    public static List<String> getChildrenRegionIdByYearList(String areaId, String year) {
        List<SysRegionTreeVo> childrenList = getChildrenRegionList(areaId, StringUtils.isEmpty(year) ? null : Integer.valueOf(year));

        return getChildrenRegionIdList(childrenList);
    }

    public static String getChildrenRegionIdStrByYearList(String areaId, String year) {
        List<SysRegionTreeVo> childrenList = getChildrenRegionList(areaId, StringUtils.isEmpty(year) ? null : Integer.valueOf(year));
        return IdUtil.getStrIdsByList(getChildrenRegionIdList(childrenList));
    }

    public static List<String> getChildrenRegionIdList(List<SysRegionTreeVo> childrenList) {
        List<String> idList = new ArrayList();
        if (childrenList.size() > 0) {
            for (SysRegionTreeVo vo : childrenList) {
                idList.add(vo.getRegionCode());
            }
        }
        return idList;
    }

    public static List<SysRegionTreeVo> getSpecialAreaIds(String administrativeLevel, String areaCode) {
        List<SysRegionTreeVo> list = new ArrayList<>();
        if (!AdministrativeLevelEnum.CITY.getCode().equals(administrativeLevel)) {
            return list;
        }
        ArrayList<String> containsList = Lists.newArrayList("110000", "120000", "310000", "500000");
        //特殊处理直辖市
        if (containsList.contains(areaCode)) {
            Result<SysRegionTreeVo> result = sysApi.getSysRegionTree(null, areaCode, null, null, null, Constants.APPID, null);
            if (result.getData() != null) {
                if (result.getData().getChildren().size() > 0) {
                    result.getData().getChildren().forEach(sysRegionTreeVo -> {
                        List<SysRegionTreeVo> children = sysRegionTreeVo.getChildren();
                        children.forEach(sysRegionCode -> {
                            if (sysRegionCode.getChildren().size() > 0) {
                                for (SysRegionTreeVo child : sysRegionCode.getChildren()) {
                                    list.add(child);
                                }
                            }
                        });
                    });
                }
            }
            return list;
        }
        return list;
    }

    public static List<SysRegionTreeVo> getChildrenRegionList(String areaId, Integer year) {
        List<SysRegionTreeVo> list = null;

        Result<List<SysRegionTreeVo>> res = sysApi.getListByParentId(areaId, Constants.APPID, null, year);
        if (res.getData() != null) {
            list = res.getData();
        } else
            list = new ArrayList<>();
        return list;
    }

    /**
     * 获取区划名称，返回map
     *
     * @param areaIds
     * @return Map<areaId, areaName>
     */
    public static Map<String, String> getRegionNameMapByCodes(List<String> areaIds, String year) {
        Integer y = year == null ? null : Integer.valueOf(year);
        Map<String, String> map = new HashMap<>();
        while (areaIds.size() > 500) {
            List<String> areaIdsBuffer = areaIds.subList(0, 500);
            Result<String> result = sysApi.getRegionNamesByCodes(String.join(",", areaIdsBuffer), y);
            if (result.getData() != null) {
                String[] names = result.getData().split(",");
                for (int i = 0; i < areaIdsBuffer.size(); i++) {
                    map.put(areaIdsBuffer.get(i), names[i]);
                }
            }
            areaIds.removeAll(areaIdsBuffer);
        }
        Result<String> result = sysApi.getRegionNamesByCodes(String.join(",", areaIds), y);
        if (result.getData() != null) {
            String[] names = result.getData().split(",");
            for (int i = 0; i < areaIds.size(); i++) {
                map.put(areaIds.get(i), names[i]);
            }
        }

        return map;
    }

    /**
     * 获取区划名称，返回map
     *
     * @param areaIds
     * @return Map<areaId, areaName>
     */
/*    public static Map<String, AreaRegionCode> getRegionMapByCodes(List<String> areaIds) {
        Map<String, AreaRegionCode> map = new HashMap();

        for (String areaId : areaIds) {
            map.put(areaId, getRegionCodeByLastCode(areaId));
        }

        return map;
    }*/

    /**
     * 获取下级区域信息
     *
     * @param parengId
     * @return
     */
    public static List<AreaRegionCode> getChildrenRegionCodeList(String parengId, String year) {
        List<AreaRegionCode> resList = new ArrayList();
        Result<List<SysRegionTreeVo>> result = sysApi.getListByParentId(parengId, Constants.APPID, null, null);
        if (result != null && result.getData() != null) {
            resList = result.getData().stream().map(v -> getRegionCodeByLastCode(v.getRegionCode(), year)).collect(Collectors.toList());
        }
        return resList;
    }

    /**
     * 根据区域列表信息，获取区域Id拼接字符串
     *
     * @param regions
     * @return
     */
    public static String getAreaIdsByRegionList(List<AreaRegionCode> regions) {
        return regions.stream().map(r -> r.getLastId()).collect(Collectors.joining(","));
    }

    /**
     * 根据行政区划代码查询名字返回Map结构
     *
     * @param codes 行政区划代码
     * @param year  年度
     * @return 代码和名字的Map
     */
    public static Map<String, String> getRegionNameMapsByCodes(String codes, String year) {
        Result<Map<String, String>> regionNameMapsByCodes = sysApi.getRegionNameMapsByCodes(codes, year == null ? null : Integer.parseInt(year));
        if (Result.CODE.equals(regionNameMapsByCodes.getCode())) {
            return regionNameMapsByCodes.getData();
        }
        return Maps.newHashMap();
    }

    /**
     * 查询数据多的情况
     * 根据行政区划代码查询名字返回Map结构
     *
     * @param codes 行政区划代码
     * @param year  年度
     * @return 代码和名字的Map
     */
    public static Map<String, String> getRegionNameMapsByCodes(List<String> codes, String year) {
        Map<String, String> resultMap = Maps.newHashMap();
        while (codes.size() > 500) {
            List<String> codesBuffer = codes.subList(0, 500);
            Result<Map<String, String>> regionNameMapsByCodes = sysApi.getRegionNameMapsByCodes(IdUtil.getStrIdsByList(codesBuffer), year == null ? null : Integer.parseInt(year));
            if (Result.CODE.equals(regionNameMapsByCodes.getCode())) {
                resultMap.putAll(regionNameMapsByCodes.getData());
            }
            codes.removeAll(codesBuffer);
        }
        Result<Map<String, String>> regionNameMapsByCodes = sysApi.getRegionNameMapsByCodes(IdUtil.getStrIdsByList(codes), year == null ? null : Integer.parseInt(year));
        if (Result.CODE.equals(regionNameMapsByCodes.getCode())) {
            resultMap.putAll(regionNameMapsByCodes.getData());
        }
        return resultMap;
    }

    /**
     * 根据ID   获取名字
     *
     * @param areaCodeNameMaps 区划代码和名字的Map对应
     * @param areaCodes        需要翻译的代码
     * @return 根据传入的code顺序返回名字，如果Map中没有就该Code就返回""
     */
    public static String getAreaName(Map<String, String> areaCodeNameMaps, String... areaCodes) {
        if (areaCodes == null || areaCodes.length == 0) {
            return "";
        }
        if (MapUtils.isEmpty(areaCodeNameMaps)) {
            areaCodeNameMaps = Maps.newHashMap();
        }
        StringBuilder areaName = new StringBuilder("");
        for (String areaCode : areaCodes) {
            if (!StringUtils.isEmpty(areaCode)) {
                String name = areaCodeNameMaps.get(areaCode);
                areaName.append(org.apache.commons.lang.StringUtils.isBlank(name) ? "" : name);
            }
        }
        return areaName.toString();

    }

    /**
     * 通过父级id获取下级id
     *
     * @param parentIds
     * @param level     parentIds 的所属级别
     * @return
     */
    public static List<String> listChildrenIdsByParentIdsAndLevel(List<String> parentIds, String level) {
        Result<SysRegionTreeVo> treeVoResult = sysApi.getSysRegionTreeByYear(null, null, null, null, null, Constants.APPID, null, null);
        return listChildrenIdsByParentIdsAndLevel(treeVoResult, parentIds, level);
    }


    public static List<String> listChildrenIdsByParentIdsAndLevel(Result<SysRegionTreeVo> treeVoResult, List<String> parentIds, String level) {
        List<String> result = Lists.newArrayList();
        // 整棵树
        if (treeVoResult != null && treeVoResult.getData() != null) {
            SysRegionTreeVo root = treeVoResult.getData();
            if (RegionLevel.MINISTRY.getCode().equals(level)) {
                if (parentIds.contains(root.getRegionCode())) {
                    List<String> provinceIds = root.getChildren().stream().map(SysRegionTreeVo::getRegionCode).collect(Collectors.toList());
                    result.addAll(provinceIds);
                }
            }
            if (RegionLevel.PROVINCE.getCode().equals(level)) {
                for (SysRegionTreeVo province : root.getChildren()) {
                    if (parentIds.contains(province.getRegionCode())) {
                        List<String> cityIds = province.getChildren().stream().map(SysRegionTreeVo::getRegionCode).collect(Collectors.toList());
                        result.addAll(cityIds);
                    }
                }
            }
            if (RegionLevel.CITY.getCode().equals(level)) {
                for (SysRegionTreeVo province : root.getChildren()) {
                    for (SysRegionTreeVo city : province.getChildren()) {
                        if (parentIds.contains(city.getRegionCode())) {
                            List<String> areaIds = city.getChildren().stream().map(SysRegionTreeVo::getRegionCode).collect(Collectors.toList());
                            result.addAll(areaIds);
                        }
                    }
                }
            }
        } else {
            throw new SofnException("获取行政区划失败！");
        }
        return result;
    }

    /**
     * 查询传入年度是否有行政区划数据，如果没有返回有区划的最大年度
     */
    public static String getYearByYear(String year) {
        if (org.apache.commons.lang.StringUtils.isBlank(year)) {
            year = DateUtils.format(new Date(), "yyyy");
        }
        // 获取所有版本年份
        Result<List<Integer>> result = sysApi.getVersionYearList();
        if (result != null && CollectionUtils.isNotEmpty(result.getData())) {
            List<Integer> yearList = result.getData();
            if (yearList.contains(Integer.valueOf(year))) {
                return year;
            } else {
                return Collections.max(yearList).toString();
            }
        }
        return year;
    }
}
