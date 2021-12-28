package com.sofn.fdpi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.Result;
import com.sofn.common.utils.BoolUtils;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.fdpi.constants.Constants;
import com.sofn.fdpi.enums.OMCompProcessEnum;
import com.sofn.fdpi.enums.OrganizationLevelEnum;
import com.sofn.fdpi.mapper.TbUsersMapper;
import com.sofn.fdpi.model.TbUsers;
import com.sofn.fdpi.service.OmCompService;
import com.sofn.fdpi.service.TbUsersService;
import com.sofn.fdpi.sysapi.SysRegionApi;
import com.sofn.fdpi.sysapi.bean.SysUser;
import com.sofn.fdpi.util.SysOwnOrgUtil;
import com.sofn.fdpi.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;


@Service("tbUsersService")
public class TbUsersServiceImpl extends ServiceImpl<TbUsersMapper, TbUsers> implements TbUsersService {
    @Autowired
    private TbUsersMapper tbUsersMapper;
    @Autowired
    private SysRegionApi sysRegionApi;
    @Resource
    private OmCompService omCompService;


    @Override
    public String updateStatusById(Map<String, Object> map) {
        int result = tbUsersMapper.updateStatusById(map);
        if (result == 0) {
            return "修改用户状态失败！";
        }
        return "1";
    }

    @Override
    public String changePassWordByUserId(UpdatePasswordVo vo) {
        Result<String> stringResult = sysRegionApi.changePassword(vo);
        return stringResult.getMsg();
    }

    @Override
    public Result<SysUser> getLoginUserInfo() {

        return sysRegionApi.getLoginUser();

    }

    @Override
    public TbUsers getUserIdAndPassword(String compId) {
        return tbUsersMapper.getUserIdAndPassword(compId);
    }

    @Override
    public PageUtils<CompanyUserInfo> getCompanyUserInfo(Map<String, Object> map, int pageNo, int pageSize) {
        PageHelper.offsetPage(pageNo, pageSize);
        Result<SysOrgAndRegionVo> sysOrgInfoForApproveAndQuery = SysOwnOrgUtil.getSysOrgInfoForApproveAndQuery();
        String sysOrgLevelId = sysOrgInfoForApproveAndQuery.getData().getSysOrgLevelId();
        Boolean chose = OrganizationLevelEnum.DIRECT_AND_CITY_ORG_LEVEL.getId().equals(sysOrgLevelId) || OrganizationLevelEnum.DIRECT_AND_PROVINCE_ORG_LEVEL.getId().equals(sysOrgLevelId) || OrganizationLevelEnum.DIRECT_AND_MINISTRY_ORG_LEVEL.getId().equals(sysOrgLevelId) || OrganizationLevelEnum.DIRECT_ORG_LEVEL.getId().equals(sysOrgLevelId);
        if (chose) {
            String orgId = UserUtil.getLoginUserOrganizationId();
            map.put("orgId", orgId);
            List<CompanyUserInfo> compUserInfo = tbUsersMapper.getCompUserInfo(map);
            StringBuilder ids1 = new StringBuilder();
            for (CompanyUserInfo es :
                    compUserInfo) {
                String id = es.getId();
                ids1.append(id);
                ids1.append(",");
            }
            String ids = ids1.substring(0, ids1.length() - 1);
            Result<List<SysUserForm>> userInfoByIds = sysRegionApi.getUserInfoByIds(ids);
            List<SysUserForm> data = userInfoByIds.getData();
            for (CompanyUserInfo es : compUserInfo) {
                String id = es.getId();
                for (SysUserForm sy : data) {
                    if (id.equals(sy.getId())) {
                        es.setAccount(sy.getUsername());
                        es.setEmail(sy.getEmail());
                        es.setPassWord(sy.getInitPassword());
                        es.setPhone(sy.getMobile());
                        es.setContacts(sy.getNickname());
                        es.setUserStatus(sy.getStatus());
                    }
                }
            }
            Iterator<CompanyUserInfo> it = compUserInfo.iterator();
            if (map.get("userStatus") != null && !map.get("userStatus").equals("")) {
                while (it.hasNext()) {
                    CompanyUserInfo x = it.next();
                    if (!x.getUserStatus().equals(map.get("userStatus"))) {
                        it.remove();
                    }
                }
            }

            PageInfo<CompanyUserInfo> pageInfo = new PageInfo<>(compUserInfo);
            return PageUtils.getPageUtils(pageInfo);
        } else {
            throw new SofnException("当前机构不是直属机构");
        }

    }

    /**
     * 根据id查看用户的详细信息
     *
     * @param id
     * @return
     */
    @Override
    public UserCompanyInfoVo getUserCompanyInfo(String id) {
        UserCompanyInfoVo companyUserInfo = tbUsersMapper.getCompanyUserInfo(id);
        Result<SysUserForm> one = sysRegionApi.getOne(id);
        companyUserInfo.setAccount(one.getData().getUsername());
        companyUserInfo.setPassWord(one.getData().getInitPassword());
        companyUserInfo.setEmail(one.getData().getEmail());
        companyUserInfo.setContacts(one.getData().getNickname());
        companyUserInfo.setPhone(one.getData().getMobile());
        companyUserInfo.setUserStatus(one.getData().getStatus());
        return companyUserInfo;
    }

    @Override
    public SysRegionInfoVo get() {

        Result<SysRegionInfoVo> sysRegionInfoByOrgId = sysRegionApi.getSysRegionInfoByOrgId(UserUtil.getLoginUserOrganizationId());
        return sysRegionInfoByOrgId.getData();
    }

    @Override
    public Result<Map<String, Set<String>>> getUserHasPermission(String appId) {
        Result<Map<String, Set<String>>> result =
                sysRegionApi.getUserHavePermission(StringUtils.hasText(appId) ? appId : Constants.SYSTEM_ID);
        List<String> roles = UserUtil.getLoginUserRoleCodeList();
        if (roles.contains("ouman_import") || roles.contains("ouman_breed") || roles.contains("ouman_process")) {
            OmCompVO omCompVO = omCompService.getOmComp();
            if (!OMCompProcessEnum.PASS.getKey().equals(omCompVO.getStatus())) {
                Map<String, Set<String>> map = result.getData();
                Set<String> set = map.get("menus");
                set.remove("ouman:importInfo:index"); //去掉鳗苗进口管理菜单
                set.remove("ouman:transaction:index"); //去掉交易流向管理菜单
                set.remove("ouman:source:index"); //去掉溯源管理分析菜单
                set.remove("ouman:exportInfo:index"); //去掉产品再出口管理菜单
            }
        }
        return result;
    }

    @Override
    public boolean validUserNameIsHasRegisterAndSave(String account) {
        QueryWrapper<TbUsers> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del_flag", BoolUtils.N).eq("account", account);
        return !CollectionUtils.isEmpty(tbUsersMapper.selectList(queryWrapper));
    }

    @Override
    public List<TbUsers> getUserByCompId(String compId) {
        QueryWrapper<TbUsers> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("comp_id", compId);
        return tbUsersMapper.selectList(queryWrapper);
    }

    @Override
    public int deleteByIds(List<String> userIds) {
        if (!CollectionUtils.isEmpty(userIds)) {
            tbUsersMapper.deleteBatchIds(userIds);
        }
        return 0;
    }
}
