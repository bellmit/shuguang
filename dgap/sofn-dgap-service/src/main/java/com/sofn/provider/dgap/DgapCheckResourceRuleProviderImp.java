package com.sofn.provider.dgap;

import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.dgap.TDgapResourceApplicationExpandMapper;
import com.sofn.model.generator.TDgapResourceApplication;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * author tianjie
 *
 * @date 2016/10/17
 */
@DubboService(interfaceClass = TDgapCheckResourceRuleProvider.class)
public class DgapCheckResourceRuleProviderImp implements TDgapCheckResourceRuleProvider {

  /** 通过标识 */
  private static final String APPROVE_FLAG = "1";

  @Override
  public void checkResouce(String resource, String authCode, String userId) throws Exception {
    boolean checkFlag = false;
    List<TDgapResourceApplication> list =
        tDgapResourceApplicationExpandMapper.queryByResourceAndUser(resource, userId);
    if (list == null || list.size() <= 0) {
      throw new Exception("用户没有该资源的授权信息");
    }

    for (TDgapResourceApplication t : list) {
      if (t.getApproval().equals(APPROVE_FLAG) && t.getCallerToken().equals(authCode)) {
        checkFlag = true;
        break;
        // 校验用户信息完成，没有异常
      }
    }
    if (!checkFlag) {
      throw new Exception("授权信息已过期或authcode错误，请检查");
    }
  }

  @Override
  public Map<String, Object> getTDgapResourceApplication(String authCode) {
    return tDgapResourceApplicationExpandMapper.getTdgapResourceApplication(authCode);
  }

  @Autowired private TDgapResourceApplicationExpandMapper tDgapResourceApplicationExpandMapper;
}
