package com.sofn.agpjyz.enums;

import com.sofn.agpjyz.constants.Constants;
import com.sofn.agpjyz.util.RedisUserUtil;
import com.sofn.agpjyz.vo.OrganizationInfo;
import com.sofn.agpjyz.vo.SelectVo;
import com.sofn.common.utils.UserUtil;
import lombok.Getter;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 已保存/已撤回/已上报/市级退回/市级通过/省级退回/省级通过/总站退回/总站通过/
 *
 * @Author yumao
 * @Date 2020/3/2 11:00
 **/
@Getter
public enum ProcessEnum {

    KEEP("0", "已保存"),
    WITHDRAW("1", "已撤回"),
    REPORT("2", "已上报"),
    CITY_AUDIT_RETURN("3", "市级退回"),
    CITY_AUDIT("4", "市级通过"),
    PROVINCE_AUDIT_RETURN("5", "省级退回"),
    PROVINCE_AUDIT("6", "省级通过"),
    FINAL_AUDIT_RETURN("7", "总站退回"),
    FINAL_AUDIT("8", "总站通过");

    private String key;
    private String val;

    ProcessEnum(String key, String val) {
        this.key = key;
        this.val = val;
    }

    public static List<SelectVo> getSelect() {
        ProcessEnum[] arr = ProcessEnum.values();
        List<SelectVo> list = new ArrayList<>(arr.length);
        for (ProcessEnum obj : arr) {
            SelectVo sv = new SelectVo();
            sv.setKey(obj.getKey());
            sv.setVal(obj.getVal());
            list.add(sv);
        }
        return list;
    }
    public static String getVal(String key) {
        ProcessEnum[] arr = values();
        for (ProcessEnum obj : arr) {
            if (obj.key.equals(key)) {
                return obj.val;
            }
        }
        return null;
    }

    /**
     * 根据当前用户机构分别返回状态列表
     */
    public static List<SelectVo> getStatus() {
        List<SelectVo> selectVoList = getSelect();
        List<SelectVo> res = null;
        List<String> loginUserRoleCodeList = UserUtil.getLoginUserRoleCodeList();
        if (!CollectionUtils.isEmpty(loginUserRoleCodeList)) {
            if (loginUserRoleCodeList.contains(Constants.EXPERT_ROLE_CODE)) {
                res = new ArrayList<>(5);
                res.add(selectVoList.get(0));
                res.add(selectVoList.get(1));
                res.add(selectVoList.get(2));
                res.add(selectVoList.get(7));
                res.add(selectVoList.get(8));
            } else {
                OrganizationInfo orgInfo = RedisUserUtil.getOrgInfo();
                String organizationLevel = orgInfo.getOrganizationLevel();
                if (Constants.REGION_TYPE_MINISTRY.equals(organizationLevel)) {
                    res = new ArrayList<>(4);
                    res.add(selectVoList.get(2));
                } else if (Constants.REGION_TYPE_PROVINCE.equals(organizationLevel)) {
                    res = new ArrayList<>(5);
                    res.add(selectVoList.get(4));
                    res.add(selectVoList.get(5));
                } else if (Constants.REGION_TYPE_CITY.equals(organizationLevel)) {
                    res = new ArrayList<>(7);
                    res.add(selectVoList.get(2));
                    res.add(selectVoList.get(3));
                    res.add(selectVoList.get(4));
                    res.add(selectVoList.get(5));
                } else {
                    res = new ArrayList<>(9);
                    res.add(selectVoList.get(0));
                    res.add(selectVoList.get(1));
                    res.add(selectVoList.get(2));
                    res.add(selectVoList.get(3));
                    res.add(selectVoList.get(4));
                    res.add(selectVoList.get(5));
                }
                res.add(selectVoList.get(6));
                res.add(selectVoList.get(7));
                res.add(selectVoList.get(8));
            }
        }
        return res;
    }
}
