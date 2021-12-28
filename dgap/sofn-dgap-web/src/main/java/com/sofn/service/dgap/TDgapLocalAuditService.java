package com.sofn.service.dgap;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.constant.CurrentUser;
import com.sofn.core.constant.Organization;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.core.util.WebUtil;
import com.sofn.model.dgap.Constants;
import com.sofn.model.generator.TDgapLocalPlatFormAppli;
import com.sofn.model.generator.TDgapResourceApplication;
import com.sofn.provider.dgap.TDgapLocalAuditProvider;
import com.sofn.provider.dgap.TDgapResourceApplicationProvider;
//import com.sofn.provider.sys.SysUserProvider;

import java.util.*;

import jodd.util.StringUtil;
import org.springframework.stereotype.Service;

@Service
public class TDgapLocalAuditService
    extends BaseService<TDgapLocalAuditProvider, TDgapLocalPlatFormAppli> {
  @DubboReference
  public void setProvider(TDgapLocalAuditProvider provider) {
    this.provider = provider;
  }

//  private SysUserProvider sysUserProvider;
//
//  @DubboReference
//  public void setProvider(SysUserProvider sysUserProvider) {
//    this.sysUserProvider = sysUserProvider;
//  }

  private TDgapResourceApplicationProvider reprovider;

  @DubboReference
  public void setProvider(TDgapResourceApplicationProvider reprovider) {
    this.reprovider = reprovider;
  }

  /**
   * 分页查询
   *
   * @param
   * @param pageNum
   * @param pageSize
   * @return
   */
  public PageInfo<Map<String, Object>> list(String platFormName, String platFormLevel, String platFormAreaId, String enableStatus, String status,
      String userName, String dateBegin, String dateEnd, int pageNum, int pageSize) {
    Map<String, Object> params = new HashMap<>();
    // page
    params.put(Constants.PAGE_NUM, pageNum);
    params.put(Constants.PAGE_SIZE, pageSize);
    // query
    params.put("status", StringUtil.isNotBlank(status) ? status : null);
    params.put("dateBegin", StringUtil.isNotBlank(dateBegin) ? dateBegin : null);
    params.put("dateEnd", StringUtil.isNotBlank(dateEnd) ? dateEnd : null);
    params.put("platFormName", StringUtil.isNotBlank(platFormName) ? platFormName : null);
    params.put("platFormLevel", StringUtil.isNotBlank(platFormLevel) ? platFormLevel : null);
    params.put("platFormAreaId", StringUtil.isNotBlank(platFormAreaId) ? platFormAreaId : null);
    params.put("enableStatus", StringUtil.isNotBlank(enableStatus) ? enableStatus : null);
    return provider.list(params);
  }


  public int formAudit(TDgapLocalPlatFormAppli localPlatFormAppli, String token) {
    localPlatFormAppli.setAuditTime(new Date());
    localPlatFormAppli.setAuditUserId(WebUtil.getCurrentUserName(token));
    localPlatFormAppli.setUpdateTime(new Date());
    localPlatFormAppli.setUpdateBy(WebUtil.getCurrentUserName(token));

    String userId = WebUtil.getCurrentUserId(token);
    List<TDgapResourceApplication> resourceApplications = reprovider.getbyParams(localPlatFormAppli.getId());//根据地方平台申请id获取资源申请
    for(TDgapResourceApplication resourceApplication:resourceApplications ){
      resourceApplication.setUpdateTime(new Date());
      resourceApplication.setAuditTime(new Date());
      resourceApplication.setAuditUserId(userId);
      resourceApplication.setApproval(localPlatFormAppli.getAuditComments());
      String callerToken = createCallerToken(10);
      resourceApplication.setCallerToken(callerToken);
      if (localPlatFormAppli.getStatus().equals("APPROVED")) {
        resourceApplication.setPassOrNot("Y");
        resourceApplication.setStatus("APPR");
      } else {
        resourceApplication.setPassOrNot("N");
        resourceApplication.setStatus("DENY");
      }
      reprovider.update(resourceApplication);
    }
    return provider.updateLocalPlatFormAppli(localPlatFormAppli);
  }

  /**
   * 生成授权码
   *
   * @param length
   * @return
   */
  public static String createCallerToken(int length) {
    String val = "";
    Random random = new Random();
    for (int i = 0; i < length; i++) {
      // 输出字母还是数字
      String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
      // 字符串
      if ("char".equalsIgnoreCase(charOrNum)) {
        // 取得大写字母还是小写字母
        int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;
        val += (char) (choice + random.nextInt(26));
      } else if ("num".equalsIgnoreCase(charOrNum)) {
        val += String.valueOf(random.nextInt(10));
      }
    }
    return val;
  }
}
