package com.sofn.web.dgap;

import com.alibaba.fastjson.JSONObject;
import com.sofn.core.base.BaseController;
import com.sofn.core.base.RedisService;
import com.sofn.core.config.Resources;
import com.sofn.core.constant.ApiConstants;
import com.sofn.core.constant.CurrentUser;
import com.sofn.core.constant.HttpCode;
import com.sofn.core.exception.LoginException;
import com.sofn.core.support.Assert;
import com.sofn.core.util.MathUtil;
import com.sofn.core.util.RedisUtil;
import com.sofn.core.util.SecurityUtil;
import com.sofn.core.util.ValidateCodeUtil;
import com.sofn.core.util.encrypt.PublicKeyMap;
import com.sofn.core.util.encrypt.RSAUtils;
//import com.sofn.service.dgap.SSOLoginService;
import com.sofn.service.dgap.OuterApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.SecurityUtils;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

/** Created by: dong4j Date: 2016-09-18 Time: 10:12 Description: */
@RestController
@Api(value = "登录接口", description = "登录接口")
public class LoginController extends BaseController {
//  private final SSOLoginService ssoLoginService;
  private final OuterApiService outerApiService;
  private final RedisService redisService;
  // 使用构造注入
  @Autowired(required = false)
  public LoginController(OuterApiService outerApiService, RedisService redisService) {
    this.outerApiService = outerApiService;
    this.redisService = redisService;
  }

  /**
   * 记住Cookie
   *
   * @param userName
   * @param password
   * @param response
   */
  private void rememberMe(String userName, String password, HttpServletResponse response) {
    Cookie user = new Cookie("user", userName + "-" + password);
    user.setMaxAge(1 * 60 * 60 * 24 * 7);
    response.addCookie(user);
  }

  @ApiOperation(value = "保存Cookie")
  @RequestMapping(value = "/saveCookie")
  protected Object saveCookie(
      ModelMap modelMap,
      HttpServletRequest request,
      HttpServletResponse response,
      @ApiParam(required = false, value = "记住密码") @RequestParam(name = "remember", required = false)
          String remember,
      @ApiParam(required = false, value = "用户名") @RequestParam(name = "password", required = false)
          String password,
      @ApiParam(required = false, value = "密码") @RequestParam(name = "userName", required = false)
          String userName) {

    response.setHeader("Access-Control-Allow-Origin", "*");

    if ("1".equals(remember)) {
      rememberMe(userName, password, response);
    }

    return setSuccessModelMap(modelMap, HttpCode.OK);
  }

  /**
   * Gets uuid. 前端刷新登录页面或者点击验证码图片时调用此接口,用于生成uuid
   *
   * @param modelMap the model map
   * @param response the response
   * @param prevUUID the prev uuid 如果没有则不传
   * @return the uuid
   */
  @ApiOperation(value = "加载login.html时向后台获取UUID")
  @RequestMapping(value = "/getUUID", method = RequestMethod.GET)
  public Object generatorCode(
      ModelMap modelMap,
      HttpServletRequest request,
      HttpServletResponse response,
      @ApiParam(required = false, value = "前一次的uuid") @RequestParam(name = "prev", required = false)
          String prevUUID) {
    // 解决垮域请求
    response.setHeader("Access-Control-Allow-Origin", "*");
    String uuid = ValidateCodeUtil.getUUID(prevUUID, ApiConstants.VALIDATE_CODE_LEN);
    modelMap.addAttribute("uuid", uuid);
    String code = (String) RedisUtil.get(uuid);
    modelMap.addAttribute("code", code);
    // 解析是否有Cookie
    if (request.getAttribute("user") == null) {
      String userName = null;
      String password = null;

      Cookie[] cookies = request.getCookies();
      for (int i = 0; cookies != null && i < cookies.length; i++) {
        if (cookies[i].getName().equals("user")) {
          userName = cookies[i].getValue().split("-")[0];
          password = cookies[i].getValue().split("-")[1];
        }
      }

      if (userName == null) {
        userName = "";
      }

      if (password == null) {
        password = "";
      }
      CurrentUser user = new CurrentUser();
      user.setUserName(userName);
      user.setPassword(password);
      modelMap.addAttribute("user", user);
    }

    return setSuccessModelMap(modelMap);
  }

  /**
   * Generator image. 前端先调用 generatorCode 获取uuid,然后将uuid传入到此接口获取验证码图片反给前端
   *
   * @param request the request
   * @param response the response
   * @param currUUID the curr uuid
   * @throws Exception the exception
   */
  @ApiOperation(value = "生成验证码")
  @RequestMapping(value = "/generatorImage", method = RequestMethod.GET)
  public void generatorImage(
      HttpServletRequest request,
      HttpServletResponse response,
      @ApiParam(required = true, value = "刷新后UUID") @RequestParam(name = "curr", required = false)
          String currUUID)
      throws Exception {
    Assert.isNotBlank(currUUID, "UUID");
    response.setHeader("Access-Control-Allow-Origin", "*");
    ValidateCodeUtil.getRandcode(request, response, currUUID);
  }

  /**
   * Login object.
   *
   * @param modelMap the model map
   * @param account the account
   * @param password the password
   * @return the object
   */
  // <editor-fold desc="不再使用">
  @ApiOperation(value = "用户登录")
  @PostMapping(
      "/login_unable") /*等效于 @RequestMapping(value = "/login",method = RequestMethod.POST) */
  public Object login_unable(
      ModelMap modelMap,
      @ApiParam(required = true, value = "登录帐号") @RequestParam(value = "account", required = false)
          String account,
      @ApiParam(required = true, value = "登录密码") @RequestParam(value = "password", required = false)
          String password,
      @ApiParam(required = true, value = "刷新后UUID") @RequestParam(name = "uuid", required = false)
          String uuid,
      @ApiParam(required = true, value = "验证码") @RequestParam(name = "code", required = false)
          String code) {
    // 参数检查
    Assert.isNotBlank(account, "ACCOUNT");
    Assert.isNotBlank(password, "PASSWORD");
    Assert.isNotBlank(uuid, "UUID");

    if (ValidateCodeUtil.checkCode(uuid, code)) {
      // if(true){
      // 登录逻辑
      Map<String, Object> map = outerApiService.login(account, password);
      if (map == null) {
        throw new LoginException(Resources.getMessage("LOGIN_FAIL"));
      }
      CurrentUser userModel = (CurrentUser) map.get("user");
      // 查询机构信息并缓存
      CurrentUser user = (CurrentUser) map.get("user");
      //// 如果是超级管理员等无机构的则不查询
      // if(userModel.getOrganizationId()!=null&&!userModel.getOrganizationId().equals("")) {
      //    SysOrganization org = sysOrgService.queryById(userModel.getOrganizationId());
      //    user.setOrganization(org);
      // }
      // 根据业务需要将返回数据加密后返回
      modelMap.addAttribute("token", map.get("token"));
      return setSuccessModelMap(modelMap);
    }
    throw new LoginException(Resources.getMessage("CODE_ERROR"));
  }

  // </editor-fold>

  /**
   * Login sso object.
   *
   * @param modelMap the model map
   * @param ciphertext des加密后的密文
   * @param desKey 用rsa加密后的desKey
   * @return the object
   */
  //////////////////////////////////////////////////////////////////////////
  // todo 将 pc 或 app 参数存入 token 中 用于区分用户用什么登录系统的
  @ApiOperation(value = "用户登录")
  @PostMapping("/login")
  //    public Object login(ModelMap modelMap,
  //                            @ApiParam(required = true, value = "登录帐号")
  //                            @RequestParam(value = "account", required = false) String account,
  //                            @ApiParam(required = true, value = "登录密码")
  //                            @RequestParam(value = "password", required = false) String password,
  //                            @ApiParam(required = true, value = "刷新后UUID")
  //                            @RequestParam(name = "uuid", required = false) String uuid,
  //                            @ApiParam(required = true, value = "验证码")
  //                            @RequestParam(name = "code", required = false) String code) {
  public Object login(
      ModelMap modelMap,
      @ApiParam(required = true, value = "密文") @RequestParam(value = "ciphertext", required = false)
          String ciphertext,
      @ApiParam(required = true, value = "RSA加密后的key")
          @RequestParam(value = "desKey", required = false)
          String desKey) {

    // 将原有参数定义为变量
    String account = null;
    String password = null;
    String uuid = null;
    String code = null;
    // 解密-----------------------------------------------------------------------------
    try {
      // 将字符串转换为JSON对象
      JSONObject json = RSAUtils.decrypt(ciphertext, RSAUtils.Module.SYS, desKey);
      // 将解密后的json值赋值给变量
      if (json.get("account") != null) account = json.get("account").toString();
      if (json.get("password") != null) password = json.get("password").toString();
      if (json.get("uuid") != null) uuid = json.get("uuid").toString();
      if (json.get("code") != null) code = json.get("code").toString();
    } catch (Exception ex) {
      return setModelMap(modelMap, HttpCode.INTERNAL_SERVER_ERROR);
    }
    // end 解密-----------------------------------------------------------------------------
    // 参数检查
    Assert.isNotBlank(account, "ACCOUNT");
    Assert.isNotBlank(password, "PASSWORD");
    Assert.isNotBlank(uuid, "UUID");

    if (ValidateCodeUtil.checkCode(uuid, code)) {
      // if(true){
      // 登录逻辑
      Map<String, Object> map = outerApiService.login(account, password);
      if (map == null) {
        throw new LoginException(Resources.getMessage("LOGIN_FAIL"));
      }
      CurrentUser userModel = (CurrentUser) map.get("user");
      // 查询机构信息并缓存
      CurrentUser user = (CurrentUser) map.get("user");
      //// 如果是超级管理员等无机构的则不查询
      // if(userModel.getOrganizationId()!=null&&!userModel.getOrganizationId().equals("")) {
      //    SysOrganization org = sysOrgService.queryById(userModel.getOrganizationId());
      //    user.setOrganization(org);
      // }
      // 根据业务需要将返回数据加密后返回
      try {
        String token = RSAUtils.encode(map.get("token").toString(), RSAUtils.Module.SYS, desKey);
        // 添加返回加密后的token
        modelMap.addAttribute("token", token);
        modelMap.addAttribute("userType", user.getUserType());
      } catch (UnsupportedEncodingException ex) {
        return setModelMap(modelMap, HttpCode.INTERNAL_SERVER_ERROR);
      }

      return setSuccessModelMap(modelMap);
    }
    throw new LoginException(Resources.getMessage("CODE_ERROR"));
  }

  /**
   * Logout object.
   *
   * @param modelMap the model map
   * @param token the token
   * @param type the type
   * @return the object
   */
  @ApiOperation(value = "用户登出")
  @PostMapping("/logout")
  public Object logout(
      ModelMap modelMap,
      @ApiParam(required = true, value = "token") @RequestParam(value = "token", defaultValue = "")
          String token,
      @ApiParam(required = true, value = "pc or app")
          @RequestParam(value = "type", defaultValue = "")
          String type) {
    outerApiService.logout(token, type);
    return setSuccessModelMap(modelMap, HttpCode.OK);
  }

  /**
   * Unauthorized object. 没有登录
   *
   * @param modelMap the model map
   * @return the object
   */
  @ApiOperation(value = "没有登录")
  @GetMapping("/unauthorized")
  public Object unauthorized(ModelMap modelMap) {
    SecurityUtils.getSubject().logout();
    return setModelMap(modelMap, HttpCode.UNAUTHORIZED);
  }

  /**
   * Forbidden object. 没有权限
   *
   * @param modelMap the model map
   * @return the object
   */
  @ApiOperation(value = "没有权限")
  @GetMapping("/forbidden")
  public Object forbidden(ModelMap modelMap) {
    return setModelMap(modelMap, HttpCode.FORBIDDEN);
  }

  /**
   * Add user info object. todo 添加用户信息测试
   *
   * @param image the image
   * @param username the username
   * @param password the password
   * @return the object
   */
  @PostMapping("/addUserInfo")
  public Object addUserInfo(
      @RequestParam("file") CommonsMultipartFile image,
      @RequestParam(value = "username", defaultValue = "") String username,
      @RequestParam(value = "password", defaultValue = "") String password) {

    return null;
  }

  /**
   * Upload object. 上传文件
   *
   * @param file the file
   * @return the object
   * @throws IOException the io exception
   * @throws MyException the my exception
   */
  @PostMapping("/upload")
  public Object upload(MultipartFile file) throws IOException, MyException {
    System.out.println(file.getSize());
    // return null;
    ClientGlobal.init("/Users/codeai/Downloads/SpringBoot/SpringBoot-Learning/client.conf");
    System.out.println("network_timeout=" + ClientGlobal.g_network_timeout + "ms");
    System.out.println("charset=" + ClientGlobal.g_charset);
    TrackerGroup tg =
        new TrackerGroup(new InetSocketAddress[] {new InetSocketAddress("172.16.7.157", 22122)});
    TrackerClient tc = new TrackerClient(tg);
    TrackerServer ts = tc.getConnection();
    if (ts == null) {
      System.out.println("getConnection return null");
      return null;
    }
    StorageServer ss = tc.getStoreStorage(ts);
    if (ss == null) {
      System.out.println("getStoreStorage return null");
    }
    StorageClient1 sc1 = new StorageClient1(ts, ss);
    NameValuePair[] meta_list = null;
    String item = "c:/windows/system32/notepad.exe";
    String fileid = sc1.upload_file1(file.getBytes(), "jpg", meta_list);
    System.out.println("Upload local file " + item + " ok, fileid=" + fileid);
    return null;
  }

  /**
   * Get sys user object. todo 缓存测试
   *
   * @param modelMap the model map
   * @return the object
   */
  @PostMapping("/cache_test")
  public Object getSysUser(ModelMap modelMap) {
    Map<String, Object> resultMap = new HashMap<>();
    resultMap.put("getSysUser", outerApiService.getSysUser("1"));
    //        CurrentUser sysUser   = new CurrentUser();
    double randomDub = MathUtil.getRandom(1, 4).doubleValue();
    String randomStr = com.xiaoleilu.hutool.util.MathUtil.roundStr(randomDub, 3);
    logger.info("randomStr {}", randomStr.length());

    resultMap.put("delete", outerApiService.deleteByPrimaryKey("201609211150483422", "test"));
    return setSuccessModelMap(modelMap, resultMap);
  }

  @PostMapping("/register")
  public Object register(CurrentUser userModel) {
    userModel.setPassword(
        SecurityUtil.encryptMd5(SecurityUtil.encryptSHA(userModel.getPassword())));
    outerApiService.register(userModel);
    return setSuccessModelMap(new ModelMap());
  }

  @ApiOperation(value = "获取公钥")
  @PostMapping("/getPublicKey")
  public Object getPublicKey(
      ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
    try {
      PublicKeyMap publicKeyMap = RSAUtils.getPublicKeyMap(RSAUtils.Module.SYS);
      modelMap.put("publicKey", publicKeyMap);
      return setSuccessModelMap(modelMap);
    } catch (Exception e) {
      e.printStackTrace();
      return setModelMap(modelMap, HttpCode.INTERNAL_SERVER_ERROR);
    }
  }
}
