package com.sofn.service.dgap;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.constant.CurrentUser;
import com.sofn.core.constant.Organization;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.core.util.StringUtils;
import com.sofn.core.util.WebUtil;
import com.sofn.model.dgap.Constants;
import com.sofn.model.generator.TDgapLocalPlatFormAppli;
import com.sofn.provider.dgap.TDgapLocalPlatFormAppliProvider;
//import com.sofn.provider.sys.SysUserProvider;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import jodd.util.StringUtil;
import org.springframework.stereotype.Service;

@Service
public class TDgapLocalPlatFormAppliService
    extends BaseService<TDgapLocalPlatFormAppliProvider, TDgapLocalPlatFormAppli> {
  @DubboReference
  public void setProvider(TDgapLocalPlatFormAppliProvider provider) {
    this.provider = provider;
  }

//  private SysUserProvider sysUserProvider;
//
//  @DubboReference
//  public void setProvider(SysUserProvider sysUserProvider) {
//    this.sysUserProvider = sysUserProvider;
//  }

  /**
   * 分页查询
   *
   * @param
   * @param pageNum
   * @param pageSize
   * @return
   */
  public PageInfo<Map<String, Object>> list(
      String status,String enableStatus, String userName, String dateBegin, String dateEnd, int pageNum, int pageSize) {
    Map<String, Object> params = new HashMap<>();
    // page
    params.put(Constants.PAGE_NUM, pageNum);
    params.put(Constants.PAGE_SIZE, pageSize);
    // query
    params.put("status", StringUtil.isNotBlank(status) ? status : null);
    params.put("userName", StringUtil.isNotBlank(userName) ? userName : null);
    params.put("dateBegin", StringUtil.isNotBlank(dateBegin) ? dateBegin : null);
    params.put("dateEnd", StringUtil.isNotBlank(dateEnd) ? dateEnd : null);
    params.put("enableStatus", StringUtil.isNotBlank(enableStatus) ? enableStatus : null);
    return provider.list(params);
  }

  /**
   * 根据用户token获取organization
   * TODO eep
   */
  public Organization getOrganizationByToken(String token) {
//    return sysUserProvider.findSysUserOrganization(token);
    CurrentUser u = WebUtil.getCurrentUser(token);
    if (u == null || u.getOrganizationId() == null)
      return null;
    return u.getOrganization();
  }

  public int addLocalPlatFormAppli(TDgapLocalPlatFormAppli localPlatFormAppli, String token) {
    if (StringUtils.isNullEmpty(localPlatFormAppli.getId())) {
      localPlatFormAppli.setId(StringUtils.getUUIDString().toString().replace("-", ""));
      localPlatFormAppli.setDelFlag("N");
      localPlatFormAppli.setCreateBy(WebUtil.getCurrentUserName(token));
      localPlatFormAppli.setAppliCant(WebUtil.getCurrentUserName(token));

      Organization organization = getOrganizationByToken(token);
      localPlatFormAppli.setAppliCationDepartment(organization.getOrgName());
      localPlatFormAppli.setCreateTime(new Date());
      localPlatFormAppli.setStatus("NEW");
      return provider.addLocalPlatFormAppli(localPlatFormAppli);
    } else {
      TDgapLocalPlatFormAppli appli=provider.queryById(localPlatFormAppli.getId());
      appli.setIndustry(localPlatFormAppli.getIndustry());
      appli.setNote(localPlatFormAppli.getNote());
      appli.setDataNotifierUrl(localPlatFormAppli.getDataNotifierUrl());
      appli.setPlatFormAreaId(localPlatFormAppli.getPlatFormAreaId());
      appli.setPlatFormLevel(localPlatFormAppli.getPlatFormLevel());
      appli.setPlatFormName(localPlatFormAppli.getPlatFormName());
      appli.setUrl(localPlatFormAppli.getUrl());
      appli.setUpdateTime(new Date());
      appli.setUpdateBy(WebUtil.getCurrentUserName(token));
      return provider.updateLocalPlatFormAppli(appli);
    }
  }
}
