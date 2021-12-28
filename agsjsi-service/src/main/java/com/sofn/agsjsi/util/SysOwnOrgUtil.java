package com.sofn.agsjsi.util;

import com.google.common.collect.Maps;
import com.sofn.agsjsi.constants.Constants;
import com.sofn.agsjsi.enums.ApproveLevelEnum;
import com.sofn.agsjsi.enums.ProcessStatusEnum;
import com.sofn.agsjsi.vo.OrganizationInfo;
import com.sofn.agsjsi.vo.SysOrgAndRegionVo;
import com.sofn.common.model.Result;
import com.sofn.common.model.User;
import com.sofn.common.utils.JsonUtils;
import com.sofn.common.utils.UserUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Component
public class SysOwnOrgUtil {
    public static SysOwnOrgUtil sysOwnOrgUtil;
    @PostConstruct
    public void init() {
        sysOwnOrgUtil = this;
    }

    /**
     * 根据当前的用户，判断当前列表数据过滤的Map条件
     * <p>
     * wuXY
     * 2020-1-15 09:35:37
     *
     * @return Result<Map < String, Object>>
     */
    public static Result<Map<String, Object>> getDataWhereMapForDifferenceLevel() {
        Map<String, Object> whereMap = Maps.newHashMap();

        List<String> loginUserRoleCodeList = UserUtil.getLoginUserRoleCodeList();
        boolean isExpert=false;
        if(!CollectionUtils.isEmpty(loginUserRoleCodeList)){
            if(loginUserRoleCodeList.contains(Constants.EXPERT_ROLE_CODE)){
                //是专家
                isExpert=true;
            }
        }
        if(isExpert){
            //是专家，可以看到总站审核通过的数据
//            whereMap.put("sysStatus",ProcessStatusEnum.STATUS_APPROVE_THREE.getStatus());
            whereMap.put("sysUserLevel","4");
            return Result.ok(whereMap);
        }
        //后面处理非专家账号的过滤条件
        //获取当前组织结构信息
        OrganizationInfo organizationInfo = JsonUtils.json2obj(UserUtil.getLoginUserOrganizationInfo(), OrganizationInfo.class);

        if (organizationInfo == null) {
            return Result.error("获取当前用户组织结构失败！");
        }
        //获取机构的省市区对象
        //获取机构的行政区划
        List<String> sysRegionList = JsonUtils.json2List(organizationInfo.getRegioncode(), String.class);
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

        switch (organizationInfo.getOrganizationLevel()) {
            case "ministry":
                //部级都可以看省级审核通过后的数据
//                whereMap.put("sysStatus", ProcessStatusEnum.STATUS_APPROVE_SECOND.getStatus());
                whereMap.put("sysUserLevel","3");
                break;
            case "province":
                //省级
                whereMap.put("sysOrgProvince", sysOrgProvince);
//                whereMap.put("sysStatus", ProcessStatusEnum.STATUS_APPROVE_FIRST.getStatus());
                whereMap.put("sysUserLevel","2");
                break;
            case "city":
                //市级
                whereMap.put("sysOrgProvince", sysOrgProvince);
                whereMap.put("sysOrgCity", sysOrgCity);
//                whereMap.put("sysStatus", ProcessStatusEnum.STATUS_REPORT.getStatus());
                whereMap.put("sysUserLevel","1");
                break;
            case "county":
                //区级
                whereMap.put("sysOrgProvince", sysOrgProvince);
                whereMap.put("sysOrgCity", sysOrgCity);
                whereMap.put("sysOrgDistrict", sysOrgDistrict);
                break;
            default:
                //其他的看不了数据
                whereMap.put("sysOrgProvince", "XSX00001");
        }
        return Result.ok(whereMap);
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
//        System.out.println("当前组织结构：" + UserUtil.getLoginUserOrganizationInfo());
        OrganizationInfo organizationInfo = JsonUtils.json2obj(UserUtil.getLoginUserOrganizationInfo(), OrganizationInfo.class);

        if (organizationInfo == null) {
            return Result.error("获取当前用户组织结构失败！");
        }
        sysOrgAndRegionVo.setOrgId(organizationInfo.getId());
        sysOrgAndRegionVo.setOrgName(organizationInfo.getOrganizationName());

        //需要判断是否是专家，是专家基本返回4；
        List<String> loginUserRoleCodeList = UserUtil.getLoginUserRoleCodeList();
        if(!CollectionUtils.isEmpty(loginUserRoleCodeList)){
            if(loginUserRoleCodeList.contains(Constants.EXPERT_ROLE_CODE)){
                sysOrgAndRegionVo.setApproveLevel(ApproveLevelEnum.APPROVE_FOUR_LEVEL.getLevel());
                return Result.ok(sysOrgAndRegionVo);
            }
        }

        if ("ministry".equals(organizationInfo.getOrganizationLevel())) {
            //部级
            sysOrgAndRegionVo.setApproveLevel(ApproveLevelEnum.APPROVE_THREE_LEVEL.getLevel());
        } else if ("province".equals(organizationInfo.getOrganizationLevel())) {
            //省级
            sysOrgAndRegionVo.setApproveLevel(ApproveLevelEnum.APPROVE_SECOND_LEVEL.getLevel());
        } else if ("city".equals(organizationInfo.getOrganizationLevel())) {
            //市里一级审核
            sysOrgAndRegionVo.setApproveLevel(ApproveLevelEnum.APPROVE_FIRST_LEVEL.getLevel());
        }else {
            //其余，不能审核
            sysOrgAndRegionVo.setApproveLevel(ApproveLevelEnum.APPROVE_ZERO_LEVEL.getLevel());
        }
        return Result.ok(sysOrgAndRegionVo);
    }

    /**
     * 判断当前用户是否是专家
     * @return true：专家；false：非专家
     */
    public static boolean checkIsExpert(){
        boolean isExpert=false;
        List<String> loginUserRoleCodeList = UserUtil.getLoginUserRoleCodeList();
        if(!CollectionUtils.isEmpty(loginUserRoleCodeList)){
            if(loginUserRoleCodeList.contains(Constants.EXPERT_ROLE_CODE)){
                //是专家
                isExpert=true;
            }
        }
        return isExpert;
    }

    /**
     * 获取当前用户所属机构的新增区划数组
     * @return 省市区数组
     */
    public static List<String> getCurrentUserOrgRegion(){
        //获取当前组织结构信息
        OrganizationInfo organizationInfo = JsonUtils.json2obj(UserUtil.getLoginUserOrganizationInfo(), OrganizationInfo.class);
        if (organizationInfo == null) {
            return null;
        }
        //获取机构的省市区对象
        //获取机构的行政区划
        List<String> sysRegionList = JsonUtils.json2List(organizationInfo.getRegioncode(), String.class);
        return sysRegionList;
    }
    /**
     * 获取当前用户名称
     * @return 用户名称
     */
    public static String getUserNickName() {
        User loginUser = UserUtil.getLoginUser();
        return loginUser == null ? "" : loginUser.getNickname();
    }

    /**
     * 获取当前用户信息
     * @return 用户信息
     */
    public static User getUserInfo(){
        User lgoinUser=new User();
        lgoinUser=UserUtil.getLoginUser();
        return lgoinUser;
    }
}
