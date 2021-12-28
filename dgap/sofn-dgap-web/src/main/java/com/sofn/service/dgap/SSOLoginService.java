//package com.sofn.service.dgap;
//
//import com.sofn.core.base.BaseService;
//import com.sofn.core.constant.CurrentUser;
//import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
//import com.sofn.provider.sso.SSOLoginProvider;
//import java.util.Map;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.CachePut;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.stereotype.Service;
//
///** Created by dong4j on 16/9/13. Description: */
//@Service
//public class SSOLoginService extends BaseService<SSOLoginProvider, CurrentUser> {
//  /**
//   * Sets provider.
//   *
//   * @param provider the provider
//   */
//  @DubboReference
//  public void setProvider(SSOLoginProvider provider) {
//    this.provider = provider;
//  }
//
//  /**
//   * Login map.
//   *
//   * @param account the account
//   * @param password the password
//   * @return the map
//   */
//  public Map<String, Object> login(String account, String password) {
//    return provider.login(account, password);
//  }
//
//  /**
//   * Logout.
//   *
//   * @param token the token
//   * @param type the type
//   */
//  public void logout(String token, String type) {
//    provider.logout(token, type);
//  }
//
//  /**
//   * Gets sys user. Cacheable 缓存测试 如果缓存中有数据 则不会走 db 然后将数据放入缓存中
//   *
//   * @param id the id
//   * @return the sys user
//   */
//  @Cacheable(value = "default", key = "'queryById_id_'+#id")
//  public CurrentUser getSysUser(String id) {
//    logger.info("real query SysUser. {}" + id);
//    return provider.queryById(id);
//  }
//
//  /**
//   * Insert selective sys user. CachePut 缓存测试 如果参数与缓存不一致,则更新数据库,并且更新缓存
//   *
//   * @param user the user
//   * @return the sys user
//   */
//  @CachePut(value = "default", key = "#user.getUserName()")
//  public CurrentUser insertSelective(CurrentUser user) {
//    logger.info("real insertSelective SysUser. {}" + user);
//    return provider.update(user);
//  }
//
//  /**
//   * Delete by primary key sys user. 缓存测试
//   *
//   * @param id the id
//   * @param type the type
//   * @return the sys user
//   */
//  @CacheEvict(value = "default", key = "'delete_id_'+#id")
//  public String deleteByPrimaryKey(String id, String type) {
//    provider.delete(id, "admin");
//    return "return cache";
//  }
//
//  /** 事务测试 */
//  public Integer register(CurrentUser userModel) {
//    return provider.register(userModel);
//  }
//}
