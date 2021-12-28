package com.sofn.fyem.util;

import com.sofn.common.exception.SofnException;
import com.sofn.common.model.Result;
import com.sofn.common.utils.JsonUtils;
import com.sofn.common.utils.SpringContextHolder;
import com.sofn.common.utils.UserUtil;
import com.sofn.fyem.sysapi.SysRegionApi;
import com.sofn.fyem.sysapi.bean.SysOrgVo;
import com.sofn.fyem.sysapi.bean.SysOrganization;
import com.sofn.fyem.sysapi.bean.SysOrganizationForm;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.util.CollectionUtils;

import java.util.*;

public interface OrganizationUtil {
    /**
     * 获取当前登录用户的组织相关信息
     * @return
     */
    static SysOrgVo getLoginUserSysOrgVo() {
        return JsonUtils.json2obj(UserUtil.getLoginUserOrganizationInfo(), SysOrgVo.class);
    }

    /**
     * 获取当前登录用户的组织相关信息
     * @return
     */
    static SysOrganization getLoginUserSysOrganization() {
        return JsonUtils.json2obj(UserUtil.getLoginUserOrganizationInfo(), SysOrganization.class);
    }

    /**
     * 获取登录用户的机构及其下属机构列表
     * @return
     */
    static List<SysOrgVo> getLogUserOrgAndChildren() {
        SysOrgVo vo = getLoginUserSysOrgVo();

        SysRegionApi sysRegionApi = SpringContextHolder.getBean(SysRegionApi.class);
        List<SysOrgVo> list = new ArrayList<>();
        list.add(vo);
        Deque<SysOrgVo> deque = new LinkedList<>();
        deque.offer(vo);
        while (!deque.isEmpty()) {
            SysOrgVo org = deque.pop();
            Result<List<SysOrgVo>> result = sysRegionApi.listByParentId(org.getId(), "fyem");
            if (Objects.equals(result.getCode(), Result.CODE) && !CollectionUtils.isEmpty(result.getData()) && list.addAll(result.getData())) {
                result.getData().forEach(deque::offer);
            }
        }

        return list;
    }

    /**
     * 获取当前用户从生态总站到用户父级组织路线图
     * @return 组织机构id列表
     */
    static List<String> getLoginUserOrgParentIds() {
        final SysOrganization org = getLoginUserSysOrganization();
        String[] parentIds = org.getParentIds().split("\\W+");
        parentIds = ArrayUtils.subarray(parentIds, 2, Integer.MAX_VALUE);

        return Arrays.asList(parentIds);
    }

    /**
     * 获取指定组织机构对应级别的组织机构id
     * @param curOrgId
     * @param level
     * @return
     */
    static String resolveLevelOrganization(String curOrgId, String level) {
        SysOrganizationForm organization = getOrgInfoById(curOrgId);
        if (Objects.equals(organization.getOrganizationLevel(), level)) {
            return curOrgId;
        }
        String[] parentIds = ArrayUtils.subarray(organization.getParentIds().split("\\W+"), 2, Integer.MAX_VALUE);
        int index = resolveIndex(level);
        return index >= parentIds.length ? curOrgId : parentIds[resolveIndex(level)];
    }

    static int resolveIndex(String level) {
        switch (level) {
            case "ministry":
                return 0;
            case "province":
                return 1;
            case "city":
                return 2;
            case "county":
                return 3;
            default:
                throw new IllegalArgumentException("不合法的等级 " + level);
        }
    }

    /**
     * 根据id获取组织机构信息
     * @param id
     * @return
     */
    static SysOrganizationForm getOrgInfoById(String id) {
        SysRegionApi sysRegionApi = SpringContextHolder.getBean(SysRegionApi.class);

        Result<SysOrganizationForm> result = sysRegionApi.getOrgInfoById(id);
        if (!Objects.equals(result.getCode(), Result.CODE)) {
            throw new SofnException("访问系统服务失败 " + result.getMsg());
        }

        return result.getData();
    }
}
