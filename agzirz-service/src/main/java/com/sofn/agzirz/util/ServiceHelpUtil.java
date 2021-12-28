package com.sofn.agzirz.util;

import com.sofn.agzirz.enums.RegionLevel;
import com.sofn.agzirz.enums.UserRoleType;
import com.sofn.agzirz.sysapi.SysFileApi;
import com.sofn.agzirz.sysapi.bean.SysRegionTreeVo;
import com.sofn.agzirz.util.beans.OrganizationInfo;
import com.sofn.common.model.Result;
import com.sofn.common.utils.JsonUtils;
import com.sofn.common.utils.SpringContextHolder;
import com.sofn.common.utils.UserUtil;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * 服务类帮助工具
 */
public class ServiceHelpUtil {

    private static SysFileApi sysRegionApi = SpringContextHolder.getBean(SysFileApi.class);

    /**
     * 获取用户的角色类型
     * @return
     */
    public static UserRoleType getUserRoleType() {
        List<String> roleCodeList = UserUtil.getLoginUserRoleCodeList();

        if(roleCodeList.contains(UserRoleType.EXPERT.getCode()))
            return UserRoleType.EXPERT;
        else if(roleCodeList.contains(UserRoleType.CENTER.getCode()))
            return UserRoleType.CENTER;
        else if(roleCodeList.contains(UserRoleType.PROVINCE.getCode()))
            return UserRoleType.PROVINCE;
        else if(roleCodeList.contains(UserRoleType.CITY.getCode()))
            return UserRoleType.CITY;
        else
            return UserRoleType.COUNTY;

    }

    /**
     * 获取区域代码对象
     * @return
     */
    public static AreaRegionCode getRegionCode(){
        OrganizationInfo orgInfo = getOrgInfo();
        return getRegionCodeByLastCode(orgInfo.getRegionLastCode());
    }

    public static OrganizationInfo getOrgInfo() {
        String orgInfoJsonStr =  UserUtil.getLoginUserOrganizationInfo();
        OrganizationInfo organizationInfo = JsonUtils.json2obj(orgInfoJsonStr, OrganizationInfo.class);
        return organizationInfo;
    }


    public static String getRegCode() {

        return getOrgInfo().getRegionLastCode();
    }

    /**
     * 查上级所有的RegionCode
     * @param lastCode  县级行政代码
     */
    public static AreaRegionCode getRegionCodeByLastCode(String lastCode){
        AreaRegionCode pcmskRegionCode = new AreaRegionCode();
        Result<List<SysRegionTreeVo>> result = sysRegionApi.getParentNode(lastCode);
        if (result != null && result.getData() != null && !result.getData().isEmpty()) {
            List<SysRegionTreeVo> treeVos=result.getData();
            pcmskRegionCode.setProvinceId(treeVos.get(0).getRegionCode());
            if(treeVos.size()>=2)
                pcmskRegionCode.setCityId(treeVos.get(1).getRegionCode());
            if(treeVos.size()>=3)
                pcmskRegionCode.setCountyId(treeVos.get(2).getRegionCode());
        }
        return pcmskRegionCode;
    }

    public static DaoQeuryParam getUserRoleQueryParam() {
        UserRoleType userRoleType = ServiceHelpUtil.getUserRoleType();
        String colName = null;
        String colVal = null;
        if (userRoleType != null) {
            if (userRoleType.equals(UserRoleType.EXPERT)) {
                //专家,只看自己的
                colName = "REPORT_USER";
                colVal = UserUtil.getLoginUserId();
            }else{
                OrganizationInfo orgInfo = ServiceHelpUtil.getOrgInfo();
                if(orgInfo.getOrganizationLevel().equals(RegionLevel.MINISTRY.getCode())){
                    //总站查看所有，不添加条件
                }else {
                    ServiceHelpUtil.AreaRegionCode regionCode = ServiceHelpUtil.getRegionCode();
                    if(orgInfo.getOrganizationLevel().equals(RegionLevel.PROVINCE.getCode())){
                        colName = "PROVINCE_ID";
                        colVal = regionCode.getProvinceId();
                    }else if(orgInfo.getOrganizationLevel().equals(RegionLevel.CITY.getCode())){
                        colName = "CITY_ID";
                        colVal = regionCode.getCityId();
                    }else if(orgInfo.getOrganizationLevel().equals(RegionLevel.COUNTY.getCode())){
                        colName = "COUNTY_ID";
                        colVal = regionCode.getCountyId();
                    }
                }
            }
        }
        DaoQeuryParam res = null;
        if(colName!=null)
            res = new DaoQeuryParam(colName,colVal);
        return res;
    }

    @Data
    public static class AreaRegionCode{
        private String provinceId;
        private String cityId;
        private String countyId;
    }

    @Data
    @AllArgsConstructor
    public static class DaoQeuryParam{
        private String colName;
        private String colVal;
    }

}
