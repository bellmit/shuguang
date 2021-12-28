package com.sofn.web.dgap;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.CurrentUser;
import com.sofn.core.constant.DictType;
import com.sofn.core.constant.HttpCode;
import com.sofn.core.util.RedisUtil;
import com.sofn.core.util.WebUtil;
import com.sofn.core.util.json.JsonUtils;
import com.sofn.model.generator.SysOrganization;
import com.sofn.service.dgap.OuterApiService;
import com.sofn.sys.model.dgap.DgapSysUser;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

/** Created by wangt on 2017/5/9. */
@RestController
@RequestMapping("/userInfo")
public class TDgapUserAcctController extends BaseController {
//  @Autowired TDgapUserAcctService dgapUserAcctService;
//  @Autowired SSOLoginService ssoLoginService;
  //
  //    @Autowired
  //    TDgapUserPostTypeService dgapUserPostTypeService;

  @Autowired
  OuterApiService outerApiService;

  @ApiOperation(value = "用户信息")
  @RequestMapping("/getUserInfoByToken")
  @SystemControllerLog(description = "(共享系统)用户信息", operationType = "查询")
  public Object getUserInfo(String token) {
    try {
      CurrentUser currentUser = WebUtil.getCurrentUser(token);
//      SysUser userInfo = dgapUserAcctService.getUserInfo(currentUser.getId());
      Object userInfo = outerApiService.getUserInfo(currentUser.getId());
      return setSuccessModelMap(new ModelMap(), userInfo);
    } catch (Exception e) {
      return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
    }
  }

  @ApiOperation(value = "用户职位")
  @RequestMapping("/getPostType")
  @SystemControllerLog(description = "(共享系统)用户职位", operationType = "查询")
  public Object getPostType() {
    try {
      DictType posttype = DictType.POSTTYPE;
//      List<SysDictData> postType = dgapUserAcctService.getPostType(posttype);
      Object postType = outerApiService.getPostType(posttype);
      return setSuccessModelMap(new ModelMap(), postType);
    } catch (Exception e) {
      return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
    }
  }

  @ApiOperation(value = "修改个人信息")
  @RequestMapping("/changeInfo")
  @SystemControllerLog(description = "(共享系统)修改个人信息", operationType = "更新")
  public Object changetInfo(DgapSysUser sysUser, String token) {
    try {
        DgapSysUser newInfo = outerApiService.changeInfo(sysUser);
      if (newInfo != null) {
        // 更新缓存里的用户数据
        CurrentUser u = WebUtil.getCurrentUser(token);
        u.setPhone(newInfo.getPhone());
        u.setEmail(newInfo.getEmail());

          String loginKey = String.format("LOGIN:login_key#token=%s", token);

          //将转换后的user信息缓存到redis,使用生态项目的存储格式
          RedisUtil.set(loginKey, JSON.toJSONString(u,new SerializeConfig()), 30 * 60);
        return setSuccessModelMap(new ModelMap(), newInfo);
      } else {
        return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);
      }
    } catch (Exception e) {
      return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
    }
  }

  @ApiOperation("注销个人信息")
  @RequestMapping("/cancelInfo")
  @SystemControllerLog(description = "(共享系统)注销个人信息", operationType = "更新")
  public Object cancelInfo() {
    return null;
  }

  @ApiOperation("修改密码")
  @RequestMapping("/changePwd")
  @SystemControllerLog(description = "(共享系统)修改密码", operationType = "更新")
  public Object changePwd(String account, String oldPwd, String newPwd, String token) {
    try {
      Boolean pwd = outerApiService.changePwd(account, oldPwd, newPwd, token);
      if (pwd) {
        return setSuccessModelMap(new ModelMap());
      } else return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);
    } catch (Exception e) {
      return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
    }
  }
}
