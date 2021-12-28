package com.sofn.ducss.util;


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sofn.ducss.exception.SofnException;
import com.sofn.ducss.model.basemodel.Result;
import com.sofn.ducss.enums.AdministrativeLevelEnum;
import com.sofn.ducss.enums.Constants;
import com.sofn.ducss.enums.RegionLevel;
import com.sofn.ducss.enums.UserRoleType;
import com.sofn.ducss.sysapi.SysApi;
import com.sofn.ducss.vo.SysRegionTreeVo;
import com.sofn.ducss.vo.AreaRegionCode;
import com.sofn.ducss.vo.OrganizationInfo;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
     * 根据区划 id 查询名称
     *
     * @param code
     */
    public static String getRegionNameByRegionCode(String code) {
        if (code != null && !code.isEmpty()) {
            String result = sysApi.getSysRegionName(code);
            if (null != result) {
                return result;
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
    public static String getReginNameByRegionCodes(String codes) {
        String regionName = "";
        List<String> regionCodeList = new ArrayList<>(IdUtil.getIdsByStr(codes));
        for (String codeId : regionCodeList) {
            String name = getRegionNameByRegionCode(codeId);
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
    public static AreaRegionCode getRegionCodeByLastCode(String lastCode) {
        List<SysRegionTreeVo> treeVos = sysApi.getParentNode(lastCode);


        AreaRegionCode region = new AreaRegionCode();
        String level = null;
        String lastName = null;
        if (!CollectionUtils.isEmpty(treeVos)) {
            region.setProvinceId("100000");   //默认是省级
            region.setProvinceName("全国");
            level = RegionLevel.PROVINCE.getCode();
            lastName = region.getProvinceName();

            if (treeVos.size() >= 1) {  //市
                region.setCityId(treeVos.get(0).getRegionCode());
                region.setCityName(treeVos.get(0).getRegionName());
                level = RegionLevel.CITY.getCode();
                lastName = region.getCityName();
            }

            if (treeVos.size() >= 2) {  //区县
                region.setCountyId(treeVos.get(1).getRegionCode());
                region.setCountyName(treeVos.get(1).getRegionName());
                level = RegionLevel.COUNTY.getCode();
                lastName = region.getCountyName();
            }
        }

        if (lastCode.equals(Constants.ZHONGGUO_AREA_ID)) {
            lastName = Constants.ZHONGGUO_AREA_NAME;
            level = RegionLevel.MINISTRY.getCode();
        } else if (CollectionUtils.isEmpty(treeVos)) { //未查到区域信息
            com.sofn.ducss.util.ServiceHelpUtil.throwException("区域代码" + lastCode + "未找到行政区划信息");
        }

        region.setLastId(lastCode);
        region.setLastName(lastName);
        region.setRegionLevel(level);


        return region;
    }

    public static AreaRegionCode getRegionCodeByLastCode2(String lastCode) {
        List<SysRegionTreeVo> treeVos = sysApi.getParentNode(lastCode);


        AreaRegionCode region = new AreaRegionCode();
        String level = null;
        String lastName = null;
        if (!CollectionUtils.isEmpty(treeVos)) {
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
        } else if (CollectionUtils.isEmpty(treeVos)) { //未查到区域信息
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
    public static String getFullRegionNameByLastCode(String lastCode) {
        List<SysRegionTreeVo> treeList = sysApi.getParentNode(lastCode);
        StringBuilder regionName = new StringBuilder();
        for (SysRegionTreeVo vo : treeList) {
            regionName.append(vo.getRegionName());
        }
        return regionName.toString();
    }

    /**
     * 获取当前用户的完整区划名
     *
     * @return
     */
    public static String getLoginFullRegionName() {
        return getFullRegionNameByLastCode(getRegLastCodeStr());
    }


    /**
     * 获取当前用户区域代码对象
     *
     * @return
     */
    public static AreaRegionCode getRegionCode() {
        return getRegionCodeByLastCode(getRegLastCodeStr());
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
        List<SysRegionTreeVo> childrenList = getChildrenRegionList(areaId);

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

    public static List<String> getChildrenRegionIdList2(String areaId,String year){
        List<SysRegionTreeVo> listByParentId = sysApi.getListByParentId(areaId, Constants.APPID, null, year);
        List<String> idList = getChildrenRegionIdList(listByParentId);
        return idList;
    }

    public static List<String> getChildrenRegionIdList(String areaId) {
        List<SysRegionTreeVo> childrenList = sysApi.getListByParentId(areaId, Constants.APPID, null);
        return getChildrenRegionIdList(childrenList);
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
            SysRegionTreeVo result = sysApi.getSysRegionTree(null, areaCode, null, null, null, Constants.APPID, null);
            if (result != null) {
                if (result.getChildren().size() > 0) {
                    result.getChildren().forEach(sysRegionTreeVo -> {
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

    public static List<SysRegionTreeVo> getChildrenRegionList(String areaId) {
        List<SysRegionTreeVo> list = sysApi.getListByParentId(areaId, Constants.APPID, null);
        return list;
    }

    /**
     * 获取区划名称，返回map
     *
     * @param areaIds
     * @return Map<areaId, areaName>
     */
    public static Map<String, String> getRegionNameMapByCodes(List<String> areaIds) {
        Map<String, String> map = new HashMap();
        String result = sysApi.getRegionNamesByCodes(areaIds.stream().collect(Collectors.joining(",")));
        if (result != null) {
            String[] names = result.split(",");
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
    public static Map<String, AreaRegionCode> getRegionMapByCodes(List<String> areaIds) {
        Map<String, AreaRegionCode> map = new HashMap();

        for (String areaId : areaIds) {
            map.put(areaId, getRegionCodeByLastCode(areaId));
        }

        return map;
    }

    /**
     * 获取下级区域信息
     *
     * @param parengId
     * @return
     */
    public static List<AreaRegionCode> getChildrenRegionCodeList(String parengId) {
        List<AreaRegionCode> resList = new ArrayList();
        List<SysRegionTreeVo> result = sysApi.getListByParentId(parengId, Constants.APPID, null);
        if (!CollectionUtils.isEmpty(result)) {
            resList = result.stream().map(v -> getRegionCodeByLastCode(v.getRegionCode())).collect(Collectors.toList());
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
        Map<String, String> regionNameMapsByCodes = sysApi.getRegionNameMapsByCodes(codes, Integer.parseInt(year));
        return regionNameMapsByCodes;
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
        if (CollectionUtils.isEmpty(areaCodeNameMaps)) {
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
     * 获取所有县级id
     *
     * @return
     */
    public static List<AreaRegionCode> getAllCountyIds() {
        List<AreaRegionCode> allCountyIds = Lists.newArrayList();
        SysRegionTreeVo root = sysApi.getSysRegionTree(null, null, null, null, null, Constants.APPID, null);
        if (root != null) {
            for (SysRegionTreeVo cityTree : root.getChildren()) {
                for (SysRegionTreeVo county : cityTree.getChildren()) {
                    AreaRegionCode lastCode = new AreaRegionCode();
                    lastCode.setProvinceId(root.getRegionCode());
                    lastCode.setCityId(cityTree.getRegionCode());
                    lastCode.setCountyId(county.getRegionCode());
                    allCountyIds.add(lastCode);
                }
            }
        }
        return allCountyIds;
    }

}
