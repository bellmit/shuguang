package com.sofn.ducss.sysapi;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.sofn.ducss.enums.SysManageEnum;
import com.sofn.ducss.model.SysRegion;
import com.sofn.ducss.vo.SysUserForm;
import com.sofn.ducss.model.basemodel.Result;
import com.sofn.ducss.service.SysDictService;
import com.sofn.ducss.service.SysRegionService;
import com.sofn.ducss.service.SysRegionToTreeService;
import com.sofn.ducss.service.SysUserService;
import com.sofn.ducss.model.SysDict;
import com.sofn.ducss.vo.SysRegionTreeVo;
import com.sofn.ducss.util.BoolUtils;
import com.sofn.ducss.util.IdUtil;
import com.sofn.ducss.util.PageUtils;
import com.sofn.ducss.vo.SysRegionForm;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 使用Feign 调用sys服务
 *
 * @author simon
 */
/*@FeignClient(
        value = "sys-service",
        configuration = FeignConfiguration.class
)*/
@Component
public class SysApi {

    @Autowired
    private SysRegionToTreeService sysRegionToTreeService;

    @Autowired
    private SysRegionService sysRegionService;

    @Autowired
    private SysDictService sysDictService;

    @Autowired
    private SysUserService userService;

    /**
     * 根据areaId获取
     *
     * @return Result
     */
    public List<SysRegionTreeVo> getParentNode(String regionCode) {
        List<SysRegionTreeVo> parentNode = sysRegionToTreeService.getParentNode(regionCode, null);
        return parentNode;
    }

    /**
     * 根据区划id获取省市区名称
     *
     * @return Result<String>
     */
    public String getSysRegionName(String id) {
        if (StringUtils.isBlank(id)) {
            return null;
        }

        Map<String, Object> params = new HashMap<>();
        params.put("regionCode", id);
        params.put("versionCode", sysRegionService.getMaxVersionCodeByYear(null));

        List<SysRegion> regionList = sysRegionService.getSysRegionByContion(params);
        if (!CollectionUtils.isEmpty(regionList)) {
            SysRegion sysRegion = regionList.get(0);
            if (sysRegion != null) {
                return sysRegion.getRegionName();
            }
        }

        return null;
    }


    /**
     * 获取行政区划树结构
     *
     * @param keyword    行政区划名称
     * @param parentId   父节点ID
     * @param regionCode 行政区划代码
     * @param isAuth     是否鉴权：Y鉴权（和当前的登录用户相关，例如是成都市的用户，就返回四川省-成都市-List<区>），使用鉴权后筛选无效
     * @param level      行政区划级别(province省city市, 例如鉴权情况下双流县用户如果选择行政区域为province,则能查出整个双流上级到省,整个四川省的区域)
     * @param appId      系统APPID
     * @param token
     * @return
     */
    public SysRegionTreeVo getSysRegionTree(String keyword, String parentId, String regionCode, String isAuth, String level, String appId, String token) {
        SysRegionTreeVo treeVo;
        Integer versionYear = Calendar.getInstance().get(Calendar.YEAR);
        if (BoolUtils.Y.equals(isAuth)) {
            return sysRegionToTreeService.getSysRegionTreeByLoginUser(token, level, null, appId, versionYear);
        }

        if (StringUtils.isNotBlank(keyword) || StringUtils.isNotBlank(parentId) || StringUtils.isNotBlank(regionCode)) {
            treeVo = sysRegionToTreeService.getSysRegionTree(appId, keyword, parentId, regionCode, versionYear);
        } else {
            treeVo = sysRegionToTreeService.getSysRegionTreeByCache(appId, versionYear);
        }
        return treeVo;
    }

    /**
     * 根据区划代码获取代码对应的区划名称
     *
     * @param codes
     * @return
     */
    public String getRegionNamesByCodes(String codes) {
        return sysRegionService.getRegionNames(codes, null);
    }

    /**
     * 查询当前类型下的所有字典
     *
     * @param typevalue
     * @return
     */
    public List<SysDict> getDictListByType(String typevalue) {
        List<SysDict> sysDictList = sysDictService.getDictListByType(typevalue);
        // 追加其他作物细项,并排除其他类型
        List<SysDict> other = sysDictService.getDictListByType("other");
        sysDictList = sysDictList.stream().filter(e -> !"other".equals(e.getDictcode())).collect(Collectors.toList());
        sysDictList.addAll(other);
        return sysDictList;
    }


    /**
     * 根据父ID，查询所有下一级子区划列表
     *
     * @param parentId 父节点ID,如果不传入，默认为100000
     * @param appId    系统APPID
     * @param isAuth   是否鉴权（和当前的登录用户相关，如果是省级用户 那么根据100000查询的时候只会显示当前登录用户的省级节点
     * @return
     */
    public List<SysRegionTreeVo> getListByParentId(String parentId, String appId, String isAuth) {
        if (StringUtils.isBlank(parentId)) {
            parentId = "100000";
        }
        if (StringUtils.isBlank(isAuth)) {
            isAuth = "N";
        }
        List<SysRegionForm> sysRegionForms = sysRegionService.getListByRegionCode(appId, parentId, isAuth, null);
        List<SysRegionTreeVo> sysRegionTreeVos = Lists.newArrayList();
        for (SysRegionForm sysRegionForm : sysRegionForms) {
            SysRegionTreeVo treeVo = new SysRegionTreeVo();
            BeanUtils.copyProperties(sysRegionForm, treeVo);
            sysRegionTreeVos.add(treeVo);
        }
        return sysRegionTreeVos;
    }

    /**
     * @description
     * @date 2021/12/27 10:56
     * @param [parentId, appId, isAuth, year]
     * @return java.util.List<com.sofn.ducss.vo.SysRegionTreeVo>
     */
    public List<SysRegionTreeVo> getListByParentId(String parentId, String appId, String isAuth,String year) {
        if (StringUtils.isBlank(parentId)) {
            parentId = "100000";
        }
        if (StringUtils.isBlank(isAuth)) {
            isAuth = "N";
        }
        List<SysRegionForm> sysRegionForms = sysRegionService.getListByRegionCode(appId, parentId, isAuth, Integer.valueOf(year));
        List<SysRegionTreeVo> sysRegionTreeVos = Lists.newArrayList();
        for (SysRegionForm sysRegionForm : sysRegionForms) {
            SysRegionTreeVo treeVo = new SysRegionTreeVo();
            BeanUtils.copyProperties(sysRegionForm, treeVo);
            sysRegionTreeVos.add(treeVo);
        }
        return sysRegionTreeVos;
    }

    /**
     * 根据区划代码获取代码对应的区划名称，返回Map<String,String>  key为code value为code对应的值
     *
     * @param codes       区划代码，多个用逗号分隔
     * @param versionYear 版本年份
     * @return
     */
    public Map<String, String> getRegionNameMapsByCodes(String codes, Integer versionYear) {
        List<String> idsByStr = IdUtil.getIdsByStr(codes);
        if (CollectionUtils.isEmpty(idsByStr)) {
            return Maps.newHashMap();
        }
        Map<String, String> regionNameMaps = sysRegionService.getRegionNameMaps(Sets.newHashSet(idsByStr), versionYear);
        return regionNameMaps;
    }


    /**
     * 根据父ID，查询所有下一级子区划列表
     *
     * @param parentId 父ID
     * @param appId    系统APPID
     * @return
     */
    public List<SysRegionTreeVo> getSysRegionTreeById(String parentId, String appId) {
        if (StringUtils.isBlank(parentId)) {
            parentId = SysManageEnum.ROOT_LEVEL.getCode();
        }

        List<SysRegionTreeVo> sysRegionTreeVos = sysRegionToTreeService.getSysRegionTreeByIdAndCache(appId, parentId, null);
        return sysRegionTreeVos;
    }


    /**
     * 根据父ID获取下面的子区划，只返回一层，如果带了条件后台不进行分页并且不止返回一层，任何一个分页参数未传入就不分页
     *
     * @param keyword     行政区划名称
     * @param parentId    父节点ID，当没有条件时必传
     * @param regionCode  行政区划代码
     * @param versionYear 版本年份
     * @param pageNo      从哪条记录开始
     * @param pageSize    每页显示多少条
     * @return
     */
    public PageUtils<SysRegionTreeVo> getSysRegionFormByParentId(String keyword, String parentId, String regionCode, Integer versionYear, Integer pageNo, Integer pageSize) {
        Map<String, String> params = Maps.newHashMap();
        params.put("parentId", parentId);
        if (StringUtils.isNotBlank(keyword)) {
            params.put("regionName", keyword);
        }
        if (StringUtils.isNotBlank(regionCode)) {
            params.put("regionCode", regionCode);
        }
        PageUtils<SysRegionTreeVo> sysRegionFormByParentId = sysRegionToTreeService.getSysRegionFormByParentId(params, versionYear, pageNo, pageSize);
        return sysRegionFormByParentId;
    }


    /**
     * 根据行政区划代码获取当前区划的所有上级区划信息
     *
     * @param regionCode 行政区划代码
     * @return
     */
    public List<SysRegionTreeVo> getParentNodeByRegionCode(String regionCode) {
        List<SysRegionTreeVo> parentNode = sysRegionToTreeService.getParentNode(regionCode, null);
        return parentNode;
    }


    /**
     * 根据行政等级,系统id,区域id获取指定用户信息
     *
     * @param regionCodes 区域list
     * @param orgLevel    机构等级
     * @param appId       系统id
     * @return
     */
    public List<SysUserForm> getUserByOrgInfoAndAppId(List<String> regionCodes, String orgLevel, String appId) {
        List<SysUserForm> userFormList = userService.getUserByOrgInfoAndAppId(regionCodes, appId, orgLevel, false);
        return userFormList;
    }

    /**
     * 根据年度获取行政区划树结构
     *
     * @param keyword     行政区划名称
     * @param parentId    父节点ID
     * @param regionCode  行政区划代码
     * @param isAuth      是否鉴权：Y鉴权（和当前的登录用户相关，例如是成都市的用户，就返回四川省-成都市-List<区>），使用鉴权后筛选无效
     * @param level       行政区划级别(province省city市, 例如鉴权情况下双流县用户如果选择行政区域为province,则能查出整个双流上级到省,整个四川省的区域)
     * @param appId       系统APPID
     * @param versionYear 版本年份
     * @param token
     * @return
     */
    public SysRegionTreeVo getSysRegionTreeByYear(String keyword, String parentId, String regionCode, String isAuth, String level,
                                                  String appId, Integer versionYear, String token) {
        SysRegionTreeVo treeVo;
        if (null == versionYear) {
            versionYear = Calendar.getInstance().get(Calendar.YEAR);
        } else {
            //如果传递的年份在数据库中没有数据，则查询最近的一年
            List<Integer> versionYearList = sysRegionService.getVersionYearList();
            if (!versionYearList.contains(versionYear)) {
                versionYear = Calendar.getInstance().get(Calendar.YEAR);
            }
        }
        if (BoolUtils.Y.equals(isAuth)) {
            return sysRegionToTreeService.getSysRegionTreeByLoginUser(token, level, null, appId, versionYear);
        }

        if (StringUtils.isNotBlank(keyword) || StringUtils.isNotBlank(parentId) || StringUtils.isNotBlank(regionCode)) {
            treeVo = sysRegionToTreeService.getSysRegionTree(appId, keyword, parentId, regionCode, versionYear);
        } else {
            treeVo = sysRegionToTreeService.getSysRegionTreeByCache(appId, versionYear);
        }
        return treeVo;
    }
}
