package com.sofn.fyem.util;

import com.google.common.collect.Maps;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.Result;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.JsonUtils;
import com.sofn.common.utils.SpringContextHolder;
import com.sofn.common.utils.UserUtil;
import com.sofn.fyem.sysapi.SysRegionApi;
import com.sofn.fyem.sysapi.bean.SysOrgVo;
import com.sofn.fyem.sysapi.bean.SysOrganization;
import com.sofn.fyem.sysapi.bean.SysRegionTreeVo;
import com.sofn.fyem.vo.FyemArea;
import com.xiaoleilu.hutool.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * @Description: 与区划和机构相关的工具类
 * @Author: DJH
 * @Date: 2020/5/14 11:42
 */
@Slf4j
public class FyemAreaUtil {

    /**
     * 获取当前登录用户所属省市县id
     * @param sysRegionApi
     * @return
     */
    public static List<FyemArea> getUserAreaIdByLogUser(SysRegionApi sysRegionApi) {

        List<FyemArea> fyemAreaList = new ArrayList<>();
        //获取当前登录用户组织详情
        String userAreaByLogUser = UserUtil.getLoginUserOrganizationInfo();

        SysOrganization orgData = JsonUtils.json2obj(userAreaByLogUser, SysOrganization.class);
        if(orgData == null || orgData.getThirdOrg() == null || orgData.getThirdOrg() == ""){
            throw new SofnException("当前登录用户所在机构异常无法判断当前机构类型!");
        }

        //判断当前用户所在机构的类型(Y-行政机构,N-代理机构)
        if(orgData.getThirdOrg().equals("Y")){
            FyemArea fyemArea = new FyemArea();
            String address = orgData.getAddress();
            List<String> areaList = JsonUtils.json2List(address, String.class);
            String provinceId = areaList.size() >= 1 ? areaList.get(0) : "";
            String cityId = areaList.size() >= 2 ? areaList.get(1) : "";
            String countyId = areaList.size() >= 3 ? areaList.get(2) : "";
            //放入省id
            fyemArea.setProvinceId(provinceId);
            //放入市id
            fyemArea.setCityId(cityId);
            //放入区县id
            fyemArea.setCountyId(countyId);
            fyemArea.setLevel(orgData.getOrganizationLevel());
            fyemArea.setId(IdUtil.getUUId());
            fyemAreaList.add(fyemArea);
        }else{
            //代理机构
            //增殖放流系統id
            String fyemSysId = "fyem";
            //获取当前用户代理机构下的行政机构信息
            Result<List<SysOrganization>> orgListByAgentOrgId = sysRegionApi.getOrgListByAgentOrgId(
                    fyemSysId, orgData.getId(), "");

            List<SysOrganization> data = orgListByAgentOrgId.getData();
            for(Iterator<SysOrganization> it = data.iterator(); it.hasNext();) {
                SysOrganization next = it.next();
                //获取当前机构级别(ministry:部级，province：省级，city：市级，county：县级，country：乡级)
                String organizationLevel = next.getOrganizationLevel();
                if(!organizationLevel.equals("country")){

                    FyemArea fyemArea = new FyemArea();
                    List<String> addressList = JsonUtils.json2List(next.getAddress(), String.class);
                    String provinceId = addressList.size() >= 1 ? addressList.get(0) : "";
                    String cityId = addressList.size() >= 2 ? addressList.get(1) : "";
                    String countyId = addressList.size() >= 3 ? addressList.get(2) : "";
                    //放入省id
                    fyemArea.setProvinceId(provinceId);
                    //放入市id
                    fyemArea.setCityId(cityId);
                    //放入区县id
                    fyemArea.setCountyId(countyId);
                    fyemArea.setLevel(organizationLevel);
                    fyemArea.setId(IdUtil.getUUId());
                    fyemAreaList.add(fyemArea);
                }
            }
        }
        return fyemAreaList;
    }

    /**
     * 获取登录用户组织id下的直属机构、代理机构、子机构数量
     * @param sysRegionApi
     * @return
     */
    public static int getListCountByParentId(SysRegionApi sysRegionApi){
        //获取当前登录用户组织详情
        String userAreaByLogUser = UserUtil.getLoginUserOrganizationInfo();

        SysOrganization orgData = JsonUtils.json2obj(userAreaByLogUser, SysOrganization.class);
        if(orgData.getThirdOrg() == null || orgData.getThirdOrg() == ""){
            throw new SofnException("当前登录用户所在机构异常无法判断当前机构类型!");
        }

        List<FyemArea> fyemAreaList = getUserAreaIdByLogUser(sysRegionApi);
        FyemArea area = fyemAreaList.size() != 0 ? fyemAreaList.get(0): new FyemArea() ;
        String provinceId = area.getProvinceId();
        String cityId = area.getCityId();
        String countyId = area.getCountyId();
        String level = area.getLevel();
        String regionCode = "";
        switch (level){
            case "province": regionCode = provinceId;break;
            case "city": regionCode = cityId;break;
            case "county": regionCode = countyId;break;
        }

        // 获取登录用户组织id下的子机构
        Result<List<SysOrgVo>> result = sysRegionApi.listTree("fyem", "", regionCode, "", "", "Y", "");
        List<SysOrgVo> data = result != null ? result.getData() : null;
        List<SysOrgVo> children = data.isEmpty() ? new ArrayList<>() : data.get(0).getChildren();
        // 过滤出当前区划id下的子机构
        List<SysOrgVo> childrenOrg = new ArrayList<>();
        for (SysOrgVo orgVo: children) {
            if (orgVo.getRegionFullCode().contains(regionCode)){
                childrenOrg.add(orgVo);
            }
        }
        int count = (childrenOrg != null && childrenOrg.size() > 0) ? childrenOrg.size() : 0;

        // 获取本级机构
        int currentOrg = 1;

        // 获取直属机构
        Result<List<SysOrganization>> directOrgList = sysRegionApi.getOrgAgentByOrgId("fyem", orgData.getId(), "");
        List<SysOrganization> directOrgData = directOrgList != null ? directOrgList.getData() : null;
        int directOrg = (directOrgData != null && directOrgData.size() > 0) ? directOrgData.size() : 0;
        return count+currentOrg+directOrg;
    }

    /**
     * 填写常用参数
     * @param params
     * @return
     */
    public static Map<String, Object> fillRegionCode(final Map<String, Object> params) {
        String belongYear = (String) params.get("belongYear");
        String provinceId = (String) params.get("provinceId");
        String cityId = (String) params.get("cityId");
        String countyId = (String) params.get("countyId");
        belongYear = Objects.equals(belongYear, "0") ? StringUtils.EMPTY : belongYear;
        provinceId = Objects.equals(provinceId, "0") ? StringUtils.EMPTY : provinceId;
        cityId = Objects.equals(cityId, "0") ? StringUtils.EMPTY : cityId;
        countyId = Objects.equals(countyId, "0") ? StringUtils.EMPTY : countyId;

        if (StringUtils.isBlank(belongYear)) {
            belongYear = String.valueOf(DateUtil.year(new Date()));
        }

        if (StringUtils.isAnyBlank(provinceId, cityId)) {
            SysRegionApi sysRegionApi = SpringContextHolder.getBean(SysRegionApi.class);
            if (StringUtils.isNotBlank(countyId)) {
                Result<List<SysRegionTreeVo>> result = sysRegionApi.getParentNode(countyId);
                if (Objects.equals(result.getCode(), Result.CODE)) {
                    List<SysRegionTreeVo> data = result.getData();
                    provinceId = data.get(0).getRegionCode();
                    cityId = data.get(1).getRegionCode();
                }
            } else if (StringUtils.isNotBlank(cityId)) {
                Result<List<SysRegionTreeVo>> result = sysRegionApi.getParentNode(cityId);
                if (Objects.equals(result.getCode(), Result.CODE)) {
                    List<SysRegionTreeVo> data = result.getData();
                    provinceId = data.get(0).getRegionCode();
                }
            }
            Map<String, Object> result = Maps.newHashMap();
            result.put("belongYear", belongYear);
            result.put("provinceId", provinceId);
            result.put("cityId", cityId);
            result.put("countyId", countyId);
            return result;
        } else {
            return params;
        }
    }
}
