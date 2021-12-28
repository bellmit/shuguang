//package com.sofn.service.dgap;
//
//import com.sofn.core.base.BaseService;
//import com.sofn.core.constant.DictType;
//import com.sofn.core.constant.SysDictData;
//import com.sofn.core.constant.SysUser;
//import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
////import com.sofn.model.generator.SysDictData;
////import com.sofn.model.generator.SysUser;
////import com.sofn.provider.sys.SysDictProvider;
//import com.sofn.provider.sys.SysUserProvider;
//import java.util.Date;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
///** Created by wangt on 2017/5/9. */
//@Service
//public class TDgapUserAcctService extends BaseService<SysUserProvider, SysUser> {
//  @DubboReference
//  public void setProvider(SysUserProvider provider) {
//    this.provider = provider;
//  }
//
//  @Autowired
//  private OuterApiService outerApiService;
//
//  @DubboReference SysDictProvider sysDictProvider;
//
//  /**
//   * 获取用户信息
//   *
//   * @param id
//   * @return
//   */
//  public Object getUserInfo(String id) {
//    return provider.queryById(id);
//  }
//
//  /**
//   * 获取用户职位信息
//   *
//   * @param dictType
//   * @return
//   */
//  public List<SysDictData> getPostType(DictType dictType) {
//    return sysDictProvider.getDictByType(dictType);
//  }
//
//  /**
//   * 修改用户信息
//   *
//   * @param sysUser
//   * @return
//   */
//  public SysUser changeInfo(SysUser sysUser) {
//    SysUser oldInfo = provider.queryById(sysUser.getId());
//    if (oldInfo != null) {
//      oldInfo.setPostId(sysUser.getPostId());
//      oldInfo.setPhone(sysUser.getPhone());
//      oldInfo.setEmail(sysUser.getEmail());
//      oldInfo.setUpdateBy(sysUser.getId());
//      oldInfo.setUpdateTime(new Date());
//      return this.provider.update(oldInfo);
//    } else return null;
//  }
//
//  public Boolean changePwd(String account, String oldPwd, String newPwd, String token) {
//    return provider.changePwd(account, oldPwd, newPwd, token);
//  }
//}
