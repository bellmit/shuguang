package com.sofn.fdpi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.common.model.Result;
import com.sofn.common.utils.PageUtils;
import com.sofn.fdpi.model.TbUsers;
import com.sofn.fdpi.sysapi.bean.SysOrganizationForm;
import com.sofn.fdpi.sysapi.bean.SysUser;
import com.sofn.fdpi.vo.CompanyUserInfo;
import com.sofn.fdpi.vo.SysRegionInfoVo;
import com.sofn.fdpi.vo.UpdatePasswordVo;
import com.sofn.fdpi.vo.UserCompanyInfoVo;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface TbUsersService extends IService<TbUsers> {
    /**
     * 修改企业员工状态
     * @param map 修改参数map对象
     * @return int
     */
    String updateStatusById(Map<String, Object> map);

    /**
     * 修改当前用户的密码
     * @param vo
     * @return
     */
    String changePassWordByUserId(UpdatePasswordVo vo);

    /**
     *
     * @return
     */
    Result<SysUser> getLoginUserInfo();

    TbUsers getUserIdAndPassword(String compId);
    /**
     * 获取当前组织机构下的公司信息
     * @return
     */
    PageUtils<CompanyUserInfo> getCompanyUserInfo(Map<String, Object> map, int pageNo, int pageSize);

    /**
     * 根据id查看用户的详细信息
     * @param id
     * @return
     */
    UserCompanyInfoVo getUserCompanyInfo(String id);

    SysRegionInfoVo get();

    Result<Map<String, Set<String>>> getUserHasPermission(String appId);

    boolean validUserNameIsHasRegisterAndSave(String account);

    List<TbUsers> getUserByCompId(String compId);

    int deleteByIds(List<String> userIds);
}
