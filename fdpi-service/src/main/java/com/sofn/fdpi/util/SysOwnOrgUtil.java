package com.sofn.fdpi.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Maps;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.Result;
import com.sofn.common.utils.JsonUtils;
import com.sofn.common.utils.RedisHelper;
import com.sofn.common.utils.UserUtil;
import com.sofn.fdpi.constants.Constants;
import com.sofn.fdpi.enums.ApproveLevelEnum;
import com.sofn.fdpi.enums.DepartmentRedisKeyEnum;
import com.sofn.fdpi.enums.OrganizationLevelEnum;
import com.sofn.fdpi.model.TbDepartment;
import com.sofn.fdpi.service.TbDepartmentService;
import com.sofn.fdpi.sysapi.SysRegionApi;
import com.sofn.fdpi.sysapi.bean.SysOrganizationForm;
import com.sofn.fdpi.sysapi.bean.SysRegionInfoVo;
import com.sofn.fdpi.vo.OrganizationInfo;
import com.sofn.fdpi.vo.SysOrgAndRegionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Component
public class SysOwnOrgUtil {
    @Autowired
    private SysRegionApi sysRegionApi;

    @Autowired
    private TbDepartmentService tbDepartmentService;

    @Autowired
    private RedisHelper redisHelper;

    public static SysOwnOrgUtil sysOwnOrgUtil;


    @PostConstruct
    public void init() {
        sysOwnOrgUtil = this;
        sysOwnOrgUtil.sysRegionApi = this.sysRegionApi;
        sysOwnOrgUtil.tbDepartmentService = this.tbDepartmentService;
        sysOwnOrgUtil.redisHelper = this.redisHelper;
    }

    /**
     * 获取当前机构和查询机构区划（用于判断审核级别和通过区划过滤数据）
     * wuXY
     * 2020-1-2 15:10:34
     *
     * @return Result<SysOrgAndRegionVo>对象
     */
    public static Result<SysOrgAndRegionVo> getSysOrgInfoForApproveAndQuery() {
        //当前用户的组织id
        String loginUserOrganizationId = UserUtil.getLoginUserOrganizationId();

        Result<SysOrganizationForm> result = sysOwnOrgUtil.sysRegionApi.getOrgInfoById(loginUserOrganizationId);
        if (!Result.CODE.equals(result.getCode())) {
            return Result.error("根据机构id获取机构信息失败！");
        }
        SysOrganizationForm sysOrganizationForm = result.getData();
        if (sysOrganizationForm != null) {
            SysOrgAndRegionVo sysOrgAndRegionVo = new SysOrgAndRegionVo();
            if ("N".equals(sysOrganizationForm.getThirdOrg())) {
                //第三方机构
                sysOrgAndRegionVo = ConvertSysOrgAndRegionVo(sysOrgAndRegionVo, loginUserOrganizationId, sysOrganizationForm.getOrganizationName(), OrganizationLevelEnum.COMPANY_ORG_LEVEL.getId(), "", "", "");
                return Result.ok(sysOrgAndRegionVo);
            } else {
                //判断当前是否是直属机构
                //从redis中获取直属机构信息
                TbDepartment tbDepartment = getTbDepartment(DepartmentRedisKeyEnum.DEPARTMENT_DIRECT_TYPE_VALUE.getKey(), loginUserOrganizationId);

                //获取机构的省市区对象
                SysRegionInfoVo regionInfoVo = sysOrganizationForm.getRegionInfoVo();
                if (tbDepartment == null) {
                    //非直属机构
                    switch (sysOrganizationForm.getOrganizationLevel()) {
                        case "ministry":
                            //部级
                            sysOrgAndRegionVo = ConvertSysOrgAndRegionVo(sysOrgAndRegionVo, loginUserOrganizationId, sysOrganizationForm.getOrganizationName(), OrganizationLevelEnum.MINISTRY_ORG_LEVEL.getId(), "", "", "");
                            break;
                        case "province":
                            //省级
                            sysOrgAndRegionVo = ConvertSysOrgAndRegionVo(sysOrgAndRegionVo, loginUserOrganizationId, sysOrganizationForm.getOrganizationName(), OrganizationLevelEnum.PROVINCE_ORG_LEVEL.getId(), regionInfoVo.getProvince(), "", "");
                            break;
                        case "city":
                            //市级
                            sysOrgAndRegionVo = ConvertSysOrgAndRegionVo(sysOrgAndRegionVo, loginUserOrganizationId, sysOrganizationForm.getOrganizationName(), OrganizationLevelEnum.CITY_ORG_LEVEL.getId(), regionInfoVo.getProvince(), regionInfoVo.getCity(), "");
                            break;
                        case "county":
                            //区级
                            sysOrgAndRegionVo = ConvertSysOrgAndRegionVo(sysOrgAndRegionVo, loginUserOrganizationId, sysOrganizationForm.getOrganizationName(), OrganizationLevelEnum.DISTRICT_ORG_LEVEL.getId(), regionInfoVo.getProvince(), regionInfoVo.getCity(), regionInfoVo.getArea());
                            break;
                        default:
                            sysOrgAndRegionVo = ConvertSysOrgAndRegionVo(sysOrgAndRegionVo, loginUserOrganizationId, sysOrganizationForm.getOrganizationName(), OrganizationLevelEnum.OTHER_ORG_LEVEL.getId(), "", "", "");

                    }
                } else {
                    //直属机构
                    switch (sysOrganizationForm.getOrganizationLevel()) {
                        case "ministry":
                            //直属机构且是部级机构
                            sysOrgAndRegionVo = ConvertSysOrgAndRegionVo(sysOrgAndRegionVo, loginUserOrganizationId, sysOrganizationForm.getOrganizationName(), OrganizationLevelEnum.DIRECT_AND_MINISTRY_ORG_LEVEL.getId(), "", "", "");
                            break;
                        case "province":
                            //直属机构且是省级
                            sysOrgAndRegionVo = ConvertSysOrgAndRegionVo(sysOrgAndRegionVo, loginUserOrganizationId, sysOrganizationForm.getOrganizationName(), OrganizationLevelEnum.DIRECT_AND_PROVINCE_ORG_LEVEL.getId(), regionInfoVo.getProvince(), "", "");
                            break;
                        case "city":
                            //市级
                            sysOrgAndRegionVo = ConvertSysOrgAndRegionVo(sysOrgAndRegionVo, loginUserOrganizationId, sysOrganizationForm.getOrganizationName(), OrganizationLevelEnum.DIRECT_AND_CITY_ORG_LEVEL.getId(), regionInfoVo.getProvince(), regionInfoVo.getCity(), "");
                            break;
                        default:
                            //区级
                            sysOrgAndRegionVo = ConvertSysOrgAndRegionVo(sysOrgAndRegionVo, loginUserOrganizationId, sysOrganizationForm.getOrganizationName(), OrganizationLevelEnum.DIRECT_ORG_LEVEL.getId(), regionInfoVo.getProvince(), regionInfoVo.getCity(), regionInfoVo.getArea());
                    }

                }
                return Result.ok(sysOrgAndRegionVo);
            }

        } else {
            return Result.error("请完善当前登录账号的机构信息！");
        }
    }

    /**
     * 根据当前的用户，判断当前列表数据过滤的Map条件
     * <p>
     * wuXY
     * 2020-1-15 09:35:37
     *
     * @param isApproveList 是否是审核列表：true：审核列表；false：否
     * @return Result<Map < String, Object>>
     */
    public static Result<Map<String, Object>> getDataWhereMapForDifferenceLevel(boolean isApproveList) {
        Map<String, Object> whereMap = Maps.newHashMap();
        //获取当前组织结构信息
//        String loginUserOrganizationInfo = UserUtil.getLoginUserOrganizationInfo();
        OrganizationInfo organizationInfo = JsonUtils.json2obj(UserUtil.getLoginUserOrganizationInfo(), OrganizationInfo.class);

        if (organizationInfo == null) {
            return Result.error("获取当前用户组织结构失败！");
        }
        if ("N".equals(organizationInfo.getThirdOrg())) {
            //第三方机构
            whereMap.put("compId", organizationInfo.getId());
            return Result.ok(whereMap);
        } else {
            //获取机构的省市区对象
            //获取机构的行政区划
            List<String> sysRegionList = getRegionCodeList(organizationInfo.getRegioncode());
            String sysOrgProvince = "";
            String sysOrgCity = "";
            String sysOrgDistrict = "";
            if (sysRegionList != null) {
                for (int i = 0; i < sysRegionList.size(); i++) {
                    switch (i) {
                        case 0:
                            sysOrgProvince = sysRegionList.get(i);
                            break;
                        case 1:
                            sysOrgCity = sysRegionList.get(i);
                            break;
                        case 2:
                            sysOrgDistrict = sysRegionList.get(i);
                            break;
                    }
                }
            }

            //判断当前是否是直属机构
            //从redis中获取直属机构信息
            TbDepartment tbDepartment = getTbDepartment(DepartmentRedisKeyEnum.DEPARTMENT_DIRECT_TYPE_VALUE.getKey(), organizationInfo.getId());
            //直属机构
            if (isApproveList && tbDepartment != null) {
                //审核列表中的直属部门，则只能看直属机构下面的列表
                if ("province".equals(organizationInfo.getOrganizationLevel())) {
                    whereMap.put("sysDirectAndProvinceOrgId", organizationInfo.getId());
                    whereMap.put("sysOrgProvince", sysOrgProvince);
                } else {
                    whereMap.put("sysDirectOrgId", organizationInfo.getId());
                    whereMap.put("sysStatus", "2");
                }
                return Result.ok(whereMap);
            }
            if (isApproveList) {
                //审核过程中只有直属部门能够看到数据，其它不能看数据
                whereMap.put("sysDirectOrgId", "XSX00001");
                return Result.ok(whereMap);
            }
            //判断是否是执法机构
//            TbDepartment tbDepartmentForLaw = getTbDepartment(DepartmentRedisKeyEnum.DEPARTMENT_LAW_TYPE_VALUE.getKey(), organizationInfo.getId());
//            if (tbDepartmentForLaw != null) {
            if (Constants.ORG_ZHIFA_FUNC_CODE.equals(organizationInfo.getOrgFunction())) {
                //看省级内的数据
                whereMap.put("sysOrgProvince", sysOrgProvince);
                return Result.ok(whereMap);
            }
            switch (organizationInfo.getOrganizationLevel()) {
                case "ministry":
                    //部级都可以看
                    whereMap.put("sysStatus", "4");
                    break;
                case "province":
                    //省级
                    whereMap.put("sysOrgProvince", sysOrgProvince);
                    whereMap.put("sysStatus", tbDepartment == null ? "4" : "2");
                    break;
                case "city":
                    //市级
                    whereMap.put("sysOrgProvince", sysOrgProvince);
                    whereMap.put("sysOrgCity", sysOrgCity);
                    whereMap.put("sysStatus", "2");
                    break;
                case "county":
                    //区级
                    whereMap.put("sysOrgProvince", sysOrgProvince);
                    whereMap.put("sysOrgCity", sysOrgCity);
                    whereMap.put("sysOrgDistrict", sysOrgDistrict);
                    whereMap.put("sysStatus", "2");
                    break;
                default:
                    //其他的看不了数据
                    whereMap.put("sysOrgProvince", "XSX00001");
            }
            return Result.ok(whereMap);
        }
    }

    /**
     * 根据当前的用户，判断当前列表数据过滤的Map条件,证书年审列表
     * <p>
     * wuXY
     * 2020-1-15 09:35:37
     *
     * @return Result<Map < String, Object>>
     */
    public static Result<Map<String, Object>> getDataWhereMapForPapersYearApprove() {
        Map<String, Object> whereMap = Maps.newHashMap();
        //获取当前组织结构信息
        OrganizationInfo organizationInfo = JsonUtils.json2obj(UserUtil.getLoginUserOrganizationInfo(), OrganizationInfo.class);

        if (organizationInfo == null) {
            return Result.error("获取当前用户组织结构失败！");
        }
        if ("N".equals(organizationInfo.getThirdOrg())) {
            //第三方机构
            whereMap.put("compId", organizationInfo.getId());
            return Result.ok(whereMap);
        } else {
            //获取机构的省市区对象
            //获取机构的行政区划
            List<String> sysRegionList = getRegionCodeList(organizationInfo.getRegioncode());
            String sysOrgProvince = "";
            String sysOrgCity = "";
            String sysOrgDistrict = "";
            if (sysRegionList != null) {
                for (int i = 0; i < sysRegionList.size(); i++) {
                    switch (i) {
                        case 0:
                            sysOrgProvince = sysRegionList.get(i);
                            break;
                        case 1:
                            sysOrgCity = sysRegionList.get(i);
                            break;
                        case 2:
                            sysOrgDistrict = sysRegionList.get(i);
                            break;
                    }
                }
            }

            //判断是否是执法机构
            if (Constants.ORG_ZHIFA_FUNC_CODE.equals(organizationInfo.getOrgFunction())) {
                //看省级内的数据审核通过的数据
                whereMap.put("sysOrgProvince", sysOrgProvince);
                whereMap.put("sysStatus", "4");
                return Result.ok(whereMap);
            }
            TbDepartment department = null;
            switch (organizationInfo.getOrganizationLevel()) {
                case "ministry":
                    //部级可以看审核通过的数据
                    whereMap.put("sysStatus", "4");
                    break;
                case "province":
                    //省级
                    department = getTbDepartmentForPapersYearInspect(organizationInfo.getId(), sysOrgProvince, "", "");
                    if (department != null) {
                        //直属，如果是省级，则看排除其它市的直属机构的数据
//                        Set<String> excludeCityCodeList = getCityOrAreaCodeListInYearDirectDept(true, sysOrgProvince, "");
//                        whereMap.put("excludeCityCodeList", excludeCityCodeList);
                        whereMap.put("excludeCityCodeList", department.getCityCodeSet());
                        whereMap.put("excludeAreaCodeList", department.getAreaCodeSet());
                        whereMap.put("sysOrgProvince", sysOrgProvince);
                        whereMap.put("sysStatus", "2");

                    }
                    break;
                case "city":
                    //市级
                    department = getTbDepartmentForPapersYearInspect(organizationInfo.getId(), sysOrgProvince, sysOrgCity, "");
                    if (department != null) {
                        //直属，查找市内数据排除其它区级直属部门已上报的数据
                        //查看证书年审区级直属有哪些
//                        Set<String> excludeAreaCodeList = getCityOrAreaCodeListInYearDirectDept(false, sysOrgProvince, sysOrgCity);
//                        whereMap.put("excludeAreaCodeList", excludeAreaCodeList);
                        whereMap.put("excludeAreaCodeList", department.getAreaCodeSet());
                        whereMap.put("sysOrgProvince", sysOrgProvince);
                        whereMap.put("sysOrgCity", sysOrgCity);
                        whereMap.put("sysStatus", "2");
//                        whereMap.put("sysOutAreaCodeList",department.getAreaCodeSet());
                    }

                    break;
                case "county":
                    //区级
                    department = getTbDepartmentForPapersYearInspect(organizationInfo.getId(), sysOrgProvince, sysOrgCity, sysOrgDistrict);
                    if (department != null) {
                        //看区级已上报的企业数据
                        whereMap.put("sysOrgProvince", sysOrgProvince);
                        whereMap.put("sysOrgCity", sysOrgCity);
                        whereMap.put("sysOrgDistrict", sysOrgDistrict);
                        whereMap.put("sysStatus", "2");
                    }
                    break;
                default:
                    //其他的看不了数据
                    whereMap.put("sysOrgProvince", "XSX00001");
            }
            if (department == null) {
                //非直属部门，则不能看数据
                whereMap.put("sysOrgProvince", "XSX00001");
            }

            return Result.ok(whereMap);
        }
    }


    /**
     * 获取当前机构和查询机构区划（用于判断审核级别和通过区划过滤数据）
     * wuXY
     * 2020-1-2 15:10:34
     *
     * @return Result<SysOrgAndRegionVo>对象,只需要判断对象中approveLevel的值
     */
    public static Result<SysOrgAndRegionVo> getSysApproveLevelForApprove() {
        //获取当前组织结构信息
        SysOrgAndRegionVo sysOrgAndRegionVo = new SysOrgAndRegionVo();
        System.out.println("当前组织结构：" + UserUtil.getLoginUserOrganizationInfo());
        OrganizationInfo organizationInfo = JsonUtils.json2obj(UserUtil.getLoginUserOrganizationInfo(), OrganizationInfo.class);

        if (organizationInfo == null) {
            return Result.error("获取当前用户组织结构失败！");
        }
        sysOrgAndRegionVo.setOrgId(organizationInfo.getId());
        sysOrgAndRegionVo.setOrgName(organizationInfo.getOrganizationName());
        if ("N".equals(organizationInfo.getThirdOrg())) {
            //第三方机构
            sysOrgAndRegionVo.setApproveLevel(ApproveLevelEnum.APPROVE_ZERO_LEVEL.getLevel());
            return Result.ok(sysOrgAndRegionVo);
        } else {
            if ("ministry".equals(organizationInfo.getOrganizationLevel())) {
                //部级
                sysOrgAndRegionVo.setApproveLevel(ApproveLevelEnum.APPROVE_THREE_LEVEL.getLevel());
            } else {
                //判断当前是否是直属机构
                //从redis中获取直属机构信息
                TbDepartment tbDepartment = getTbDepartment(DepartmentRedisKeyEnum.DEPARTMENT_DIRECT_TYPE_VALUE.getKey(), organizationInfo.getId());
                if ("province".equals(organizationInfo.getOrganizationLevel())) {
                    //省级
                    if (tbDepartment != null) {
                        sysOrgAndRegionVo.setApproveLevel(ApproveLevelEnum.APPROVE_SECOND_LEVEL.getLevel());
                    } else {
                        sysOrgAndRegionVo.setApproveLevel(ApproveLevelEnum.APPROVE_SECOND_NO_LEVEL.getLevel());
                    }

                } else if (tbDepartment != null) {
                    sysOrgAndRegionVo.setApproveLevel(ApproveLevelEnum.APPROVE_FIRST_LEVEL.getLevel());
                } else {
                    //非直属
                    sysOrgAndRegionVo.setApproveLevel(ApproveLevelEnum.APPROVE_ZERO_LEVEL.getLevel());
                }

            }
            return Result.ok(sysOrgAndRegionVo);
        }
    }

    /**
     * 获取当前机构和查询机构区划（用于判断审核级别和通过区划过滤数据）
     * wuXY
     * 2020-1-2 15:10:34
     *
     * @param levelType:"1":一级审核；"2"：二级审核
     * @return Result<SysOrgAndRegionVo>对象,只需要判断对象中approveLevel的值
     */
    public static Result<SysOrgAndRegionVo> getSysApproveLevelForApprove(String levelType) {
        //获取当前组织结构信息
        SysOrgAndRegionVo sysOrgAndRegionVo = new SysOrgAndRegionVo();
        System.out.println("当前组织结构：" + UserUtil.getLoginUserOrganizationInfo());
        OrganizationInfo organizationInfo = JsonUtils.json2obj(UserUtil.getLoginUserOrganizationInfo(), OrganizationInfo.class);

        if (organizationInfo == null) {
            return Result.error("获取当前用户组织结构失败！");
        }
        sysOrgAndRegionVo.setOrgId(organizationInfo.getId());
        sysOrgAndRegionVo.setOrgName(organizationInfo.getOrganizationName());
        if ("N".equals(organizationInfo.getThirdOrg())) {
            //第三方机构
            sysOrgAndRegionVo.setApproveLevel(ApproveLevelEnum.APPROVE_ZERO_LEVEL.getLevel());
            return Result.ok(sysOrgAndRegionVo);
        } else {
            if ("ministry".equals(organizationInfo.getOrganizationLevel())) {
                //部级
                sysOrgAndRegionVo.setApproveLevel("2".equals(levelType) ? ApproveLevelEnum.APPROVE_SECOND_LEVEL.getLevel() : ApproveLevelEnum.APPROVE_ZERO_LEVEL.getLevel());
            }
//            else if ("province".equals(organizationInfo.getOrganizationLevel())) {
//                //省级
//                sysOrgAndRegionVo.setApproveLevel(ApproveLevelEnum.APPROVE_SECOND_LEVEL.getLevel());
//            }
            else {
                //判断当前是否是直属机构
                //从redis中获取直属机构信息
                TbDepartment tbDepartment = getTbDepartment(DepartmentRedisKeyEnum.DEPARTMENT_DIRECT_TYPE_VALUE.getKey(), organizationInfo.getId());
                if (tbDepartment != null) {
                    //直属
                    sysOrgAndRegionVo.setApproveLevel(ApproveLevelEnum.APPROVE_FIRST_LEVEL.getLevel());
                } else {
                    //非直属
                    sysOrgAndRegionVo.setApproveLevel(ApproveLevelEnum.APPROVE_ZERO_LEVEL.getLevel());
                }

            }
            return Result.ok(sysOrgAndRegionVo);
        }
    }

    /**
     * 获取当前机构在证书年审中的审核级别（用于判断审核级别和通过区划过滤数据）
     * wuXY
     * 2020-1-2 15:10:34
     *
     * @return Result<SysOrgAndRegionVo>对象,只需要判断对象中approveLevel的值
     */
    public static Result<SysOrgAndRegionVo> getSysApproveLevelForPapersYearApprove() {
        //获取当前组织结构信息
        SysOrgAndRegionVo sysOrgAndRegionVo = new SysOrgAndRegionVo();
        System.out.println("当前组织结构：" + UserUtil.getLoginUserOrganizationInfo());
        OrganizationInfo organizationInfo = JsonUtils.json2obj(UserUtil.getLoginUserOrganizationInfo(), OrganizationInfo.class);

        if (organizationInfo == null) {
            return Result.error("获取当前用户组织结构失败！");
        }
        sysOrgAndRegionVo.setOrgId(organizationInfo.getId());
        sysOrgAndRegionVo.setOrgName(organizationInfo.getOrganizationName());
        if ("N".equals(organizationInfo.getThirdOrg())) {
            //第三方机构
            sysOrgAndRegionVo.setApproveLevel(ApproveLevelEnum.APPROVE_ZERO_LEVEL.getLevel());
            return Result.ok(sysOrgAndRegionVo);
        } else {
            boolean isTwoLevel = false;
            if ("ministry".equals(organizationInfo.getOrganizationLevel())) {
                //部级,如果是2：二级审核（企业上报-直属部门-部级审核），则有审核级别，否则审核级别为0；
                sysOrgAndRegionVo.setApproveLevel(ApproveLevelEnum.APPROVE_ZERO_LEVEL.getLevel());
//            } else if ("province".equals(organizationInfo.getOrganizationLevel())) {
//                //省级
//                sysOrgAndRegionVo.setApproveLevel(ApproveLevelEnum.APPROVE_SECOND_LEVEL.getLevel());
            } else {
                //判断当前是否是直属机构
                List<String> sysRegionList = getRegionCodeList(organizationInfo.getRegioncode());
                String provinceCode = "";
                String cityCode = "";
                String areaCode = "";
                if ("province".equals(organizationInfo.getOrganizationLevel())) {
                    provinceCode = sysRegionList.get(0);
                } else if ("city".equals(organizationInfo.getOrganizationLevel())) {
                    provinceCode = sysRegionList.get(0);
                    cityCode = sysRegionList.get(1);
                } else if ("county".equals(organizationInfo.getOrganizationLevel())) {
                    provinceCode = sysRegionList.get(0);
                    cityCode = sysRegionList.get(1);
                    areaCode = sysRegionList.get(2);
                }
                TbDepartment tbDepartment = getTbDepartmentForPapersYearInspect(organizationInfo.getId(), provinceCode, cityCode, areaCode);
                if (tbDepartment != null) {
                    //直属
                    sysOrgAndRegionVo.setApproveLevel(ApproveLevelEnum.APPROVE_FIRST_LEVEL.getLevel());
                } else {
                    //非直属
                    sysOrgAndRegionVo.setApproveLevel(ApproveLevelEnum.APPROVE_ZERO_LEVEL.getLevel());
                }
            }
            return Result.ok(sysOrgAndRegionVo);
        }
    }

    /**
     * 组装数据：SysOrgAndRegionVo
     *
     * @param sysOrgAndRegionVo 对象
     * @return sysOrgAndRegionVo
     */
    private static SysOrgAndRegionVo ConvertSysOrgAndRegionVo(SysOrgAndRegionVo sysOrgAndRegionVo
            , String orgId
            , String orgName
            , String organLevelEumId
            , String provinceCode
            , String cityCode
            , String districtCode) {

        sysOrgAndRegionVo.setOrgId(orgId);
        sysOrgAndRegionVo.setOrgName(orgName);
        sysOrgAndRegionVo.setSysOrgLevelId(organLevelEumId);
        Map<String, Object> sysOrgRegionMap = Maps.newHashMap();
        sysOrgRegionMap.put("sysOrgProvince", provinceCode);
        sysOrgRegionMap.put("sysOrgCity", cityCode);
        sysOrgRegionMap.put("sysOrgDistrict", districtCode);

        sysOrgAndRegionVo.setSysOrgRegionMap(sysOrgRegionMap);
        return sysOrgAndRegionVo;
    }

    /**
     * @param type:类型：1：直属机构(非证书年审直属机构)；2：证书年审直属机构
     * @param orgId:机构id
     * @description:获取当前机构信息
     * @author: wuXY
     * @date: 2020/2/12 12:28
     * @return: TbDepartment
     */
    public static TbDepartment getTbDepartment(String type, String orgId) {
        //从redis中获取直属机构信息
        Object jsonObj = sysOwnOrgUtil.redisHelper.hget(DepartmentRedisKeyEnum.DEPARTMENT_KEY.getKey(), type + "_" + orgId);
        TbDepartment tbDepartment = null;
        if (jsonObj != null) {
            tbDepartment = JsonUtils.json2obj(jsonObj.toString(), TbDepartment.class);
        } else {
            synchronized (SysOwnOrgUtil.class) {
                Object jsonObj2 = sysOwnOrgUtil.redisHelper.hget(DepartmentRedisKeyEnum.DEPARTMENT_KEY.getKey(), type + "_" + orgId);
                if (jsonObj2 != null) {
                    tbDepartment = JsonUtils.json2obj(jsonObj2.toString(), TbDepartment.class);
                } else {
                    QueryWrapper<TbDepartment> queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq("ORG_ID", orgId);
                    queryWrapper.eq("TYPE", type);
                    queryWrapper.eq("DEL_FLAG", "N");
                    TbDepartment department = sysOwnOrgUtil.tbDepartmentService.getOne(queryWrapper);
//                    if(department==null){
//                        sysOwnOrgUtil.redisHelper.hset(DepartmentRedisKeyEnum.DEPARTMENT_KEY.getKey(), type + "_" + orgId, null,10000);
//                    }else {
                    sysOwnOrgUtil.redisHelper.hset(DepartmentRedisKeyEnum.DEPARTMENT_KEY.getKey(), type + "_" + orgId, JsonUtils.obj2json(department));
//                    }
                    tbDepartment = department;
                }
            }
        }
        return tbDepartment;
    }

    /**
     * @param provinceCode:省code
     * @param cityCode:市code
     * @param areaCode:区code
     * @description:获取当前账号的证书年审直属部门
     * @author: wuXY
     * @date: 2020/2/12 12:28
     * @return: TbDepartment
     */
    public static TbDepartment getTbDepartmentForPapersYearInspect(String orgId, String provinceCode, String cityCode, String areaCode) {
        //从redis中获取直属机构信息
        QueryWrapper<TbDepartment> departmentQuery = new QueryWrapper<>();
        departmentQuery.eq("TYPE", "2");
        departmentQuery.eq("DEL_FLAG", "N");
        departmentQuery.eq("DEPT_PRO", provinceCode);
        if (!StringUtils.isEmpty(cityCode)) {
            departmentQuery.eq("DEPT_CITY", cityCode);
        }
        if (!StringUtils.isEmpty(areaCode)) {
            departmentQuery.eq("DEPT_AREA", areaCode);
        }
        List<TbDepartment> list = sysOwnOrgUtil.tbDepartmentService.list(departmentQuery);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        } else {
            //当前机构在证书直属中要存在，不存在，则不是直属部门；
            List<TbDepartment> collect = list.stream().filter(n -> n.getOrgId().equals(orgId)).collect(Collectors.toList());
            if (collect.size() == 0) {
                return null;
            }
        }
        if (list.size() == 1) {
            return list.get(0);
        } else {
            //如果当前区域有多条数据，则当前机构是省级、市级，遍历列表，找到直属机构以及下级/下下级区域
            AtomicReference<TbDepartment> tbDepartment = new AtomicReference<>(new TbDepartment());
            Set<String> cityCodeSet = new HashSet<>();
            Set<String> areaCodeSet = new HashSet<>();

            list.forEach(n -> {
                if (StringUtils.isEmpty(cityCode)) {
                    if (StringUtils.isEmpty(n.getDeptCity())) {
                        //当前是省级机构，则市级编码为null的数据，则是直属机构
                        if (n.getOrgId().equals(orgId)) {
                            tbDepartment.set(n);
                        }
                    } else {
                        //收集不存在区直属的市级机构的市级区划编码
                        if (StringUtils.isEmpty(n.getDeptArea()) && !cityCodeSet.contains(n.getDeptCity())) {
                            cityCodeSet.add(n.getDeptCity());
                        } else if (!StringUtils.isEmpty(n.getDeptArea())) {
                            areaCodeSet.add(n.getDeptArea());
                        }
                    }
                } else if (StringUtils.isEmpty(areaCode)) {
                    if (StringUtils.isEmpty(n.getDeptArea())) {
                        //当前是市机构，则区级编码为null的数据，则是直属机构
                        if (n.getOrgId().equals(orgId)) {
                            tbDepartment.set(n);
                        }
                    } else {
                        //收集不存在区直属的区级机构的区级区划编码
                        if (!StringUtils.isEmpty(n.getDeptArea())) {
                            areaCodeSet.add(n.getDeptArea());
                        }
                    }
                }
            });
            TbDepartment result = tbDepartment.get();
            if (!ObjectUtils.isEmpty(result)) {
                result.setCityCodeSet(cityCodeSet.size() == 0 ? null : cityCodeSet);
                result.setAreaCodeSet(areaCodeSet.size() == 0 ? null : areaCodeSet);
                return result;
            }
            return null;
        }
    }

    /**
     * 通过当前账号，找到下级的直属部门code
     *
     * @param isProvince   是否省级
     * @param provinceCode 省级code
     * @param cityCode     市级code
     * @return Set<String>
     */
    public static Set<String> getCityOrAreaCodeListInYearDirectDept(boolean isProvince, String provinceCode, String cityCode) {
        QueryWrapper<TbDepartment> departmentQuery = new QueryWrapper<>();
        departmentQuery.eq("TYPE", "2");
        departmentQuery.eq("DEL_FLAG", "N");
        departmentQuery.eq("DEPT_PRO", provinceCode);
        if (isProvince) {
            //省级下面的市
            departmentQuery.isNotNull("DEPT_CITY");
        } else {
            //查找市级下面的区
            departmentQuery.eq("DEPT_CITY", cityCode);
            departmentQuery.isNotNull("DEPT_AREA");
        }
        List<TbDepartment> list = sysOwnOrgUtil.tbDepartmentService.list(departmentQuery);
        Set<String> codeList = null;
        if (!CollectionUtils.isEmpty(list)) {
            if (isProvince) {
                codeList = list.stream().map(TbDepartment::getDeptCity).collect(Collectors.toSet());
            } else {
                codeList = list.stream().map(TbDepartment::getDeptArea).collect(Collectors.toSet());
            }

        }
        return codeList;
    }

    /**
     * 行政区划字符串转换成list
     *
     * @param regionCodeStr 行政区划编码字符串
     * @return 列表
     */
    private static List<String> getRegionCodeList(String regionCodeStr) {
        return JsonUtils.json2List(regionCodeStr, String.class);
    }


    /**
     * 获取当前机构名称
     */
    public static String getOrganizationName() {
        return getOrgInfo().getOrganizationName();
    }

    /**
     * 获取当前机构级级别
     */
    public static String getOrganizationLevel() {
        return getOrgInfo().getOrganizationLevel();
    }

    /**
     * 获取当前用户所属组织机构
     */
    public static OrganizationInfo getOrgInfo() {
        String orgInfoJosn = UserUtil.getLoginUserOrganizationInfo();
        if (StringUtils.isEmpty(orgInfoJosn)) {
            throw new SofnException("未获取到登录用户所属机构信息!");
        }
        return JsonUtils.json2obj(orgInfoJosn, OrganizationInfo.class);
    }
}
