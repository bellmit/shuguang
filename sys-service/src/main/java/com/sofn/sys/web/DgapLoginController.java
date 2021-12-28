//package com.sofn.sys.web;
//
//import com.google.code.kaptcha.Producer;
//import com.sofn.common.config.YmlConfig;
//import com.sofn.common.constants.Constants;
//import com.sofn.common.exception.ExceptionType;
//import com.sofn.common.exception.SofnException;
//import com.sofn.common.log.SofnLog;
//import com.sofn.common.utils.*;
//import com.sofn.sys.dgap.BeanFormatUtil;
//import com.sofn.sys.dgap.CurrentUser;
//import com.sofn.sys.dgap.DgapResourceForm;
//import com.sofn.sys.model.*;
//import com.sofn.sys.service.*;
//import com.sofn.common.utils.shiro.ShiroUtils;
//import com.sofn.sys.vo.SysResourceForm;
//import com.sofn.sys.vo.SysUserForm;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.http.ResponseEntity;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.*;
//
///**
// * Created by sofn
// */
//@SuppressWarnings("ALL")
//@Slf4j
//@Api(tags = "dagp前端登录相关接口")
//@RestController
//@RequestMapping("/sofn-sys-web")
//public class DgapLoginController {
//    @Autowired
//    private SysRegionService sysRegionService;
//    @Autowired
//    private SysOrganizationService sysOrganizationService;
//    @Autowired
//    private SysUserService sysUserService;
//    @Autowired
//    private SysRoleGroupService sysGroupService;
//    @Autowired
//    private SysRoleRoleGroupService sysRoleRoleGroupService;
//    @Autowired
//    private SysUserRoleService sysUserRoleService;
//    @Autowired
//    private SysUserGroupService sysUserGroupService;
//    @Autowired
//    private SysResourceService sysResourceService;
//    @Autowired
//    private RedisHelper redisHelper;
//    @Autowired
//    private Producer producer;
//    @Autowired
//    private YmlConfig ymlConfig;
//
//
//    /**
//     * Gets uuid. 前端刷新登录页面或者点击验证码图片时调用此接口,用于生成uuid
//     *
//     * @param modelMap the model map
//     * @param response the response
//     * @param prevUUID the prev uuid 如果没有则不传
//     * @return the uuid
//     */
//    @ApiOperation(value = "加载login.html时向后台获取UUID")
//    @RequestMapping(value = "/getUUID", method = RequestMethod.GET)
//    public Object generatorCode(
//            ModelMap modelMap,
//            HttpServletRequest request,
//            HttpServletResponse response,
//            @ApiParam(required = false, value = "前一次的uuid") @RequestParam(name = "prev", required = false)
//                    String prevUUID) {
//        // 解决垮域请求
//        response.setHeader("Access-Control-Allow-Origin", "*");
//
//        String uuid = UUID.randomUUID().toString();
//        String code = ValidateCodeUtil.generatorRandomString(4);
//        modelMap.addAttribute("uuid", uuid);
//        modelMap.addAttribute("code", code);
//
//        // 验证码存储在redis中，有效时间5分钟
//        redisHelper.set(uuid, code, 5 * 60);
//
//        // 解析是否有Cookie
//        if (request.getAttribute("user") == null) {
//            String userName = null;
//            String password = null;
//
//            Cookie[] cookies = request.getCookies();
//            for (int i = 0; cookies != null && i < cookies.length; i++) {
//                if (cookies[i].getName().equals("user")) {
//                    userName = cookies[i].getValue().split("-")[0];
//                    password = cookies[i].getValue().split("-")[1];
//                }
//            }
//
//            if (userName == null) {
//                userName = "";
//            }
//
//            if (password == null) {
//                password = "";
//            }
//            CurrentUser user = new CurrentUser();
//            user.setUserName(userName);
//            user.setPassword(password);
//            modelMap.addAttribute("user", user);
//        }
//
//        return ResponseEntity.ok(modelMap);
//    }
//
//    /**
//     * Generator image.
//     * 前端先调用 generatorCode 获取uuid,然后将uuid传入到此接口获取验证码图片反给前端
//     *
//     * @param request  the request
//     * @param response the response
//     * @param currUUID the curr uuid
//     * @throws Exception the exception
//     */
//    @ApiOperation(value = "生成验证码")
//    @RequestMapping(value = "/generatorImage", method = RequestMethod.GET)
//    public void generatorImage(HttpServletRequest request, HttpServletResponse response,
//                               @ApiParam(required = true, value = "刷新后UUID")
//                               @RequestParam(name = "curr", required = false) String currUUID) throws Exception {
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        String code = (String) redisHelper.get(currUUID);
//        ValidateCodeUtil.getRandcode(request, response, code);
//    }
//
//
//    @RequestMapping(value = "/document/queryList", method = RequestMethod.POST)
//    public Object getDocumentList() {
//        ModelMap modelMap = new ModelMap();
//        modelMap.put("httpCode", 200);
//        return ResponseEntity.ok(modelMap);
//    }
//
//    /**
//     * 按照前端js文件数据格式,获取省市县列表
//     *
//     * @return
//     */
//    @ApiOperation(value = "按照前端js文件数据格式,获取省市县列表")
//    @PostMapping("/region/getProvinceAndCityAndCountryList")
//    @Cacheable(cacheNames = "regionCache", key = "'regionDataForFrontSelect'")
//    public Object getProvinceAndCityAndCountryList() {
//        Object data = sysRegionService.getProvinceAndCityAndCountryList();
//        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();
//        resultMap.put("data", data);
//        resultMap.put("httpCode", 200);
//        return resultMap;
//    }
//
//
//    /**
//     * 根据ID获取单个用户信息         需要补充用到缓存登录用户数据
//     *
//     * @param
//     * @return
//     */
//    @ApiOperation(value = "获取当前登录用户的机构信息")
//    @RequestMapping(value = "/user/findSysUserOrganization", method = RequestMethod.POST)
//    public Object findSysUserOrganization(ModelMap modelMap,
//                                          @RequestHeader(value = "token", defaultValue = "") String token) {
//        String orgId = (String) redisHelper.hget(token, Constants.UserSession.userOrganizationId);
//        SysOrganization org = sysOrganizationService.getSysOrganizationById(orgId);
//        org.setOrgType("ASMS");
//
//        String userId = (String) redisHelper.hget(token, Constants.UserSession.userId);
//        SysUser user = sysUserService.getById(userId);
//        modelMap.addAttribute("org", org);
//        modelMap.addAttribute("user", user);
//        modelMap.addAttribute("httpCode", 200);
//        return ResponseEntity.ok(modelMap);
//    }
//
//    /**
//     * 获取用户权限菜单集合
//     *
//     * @param
//     * @return
//     */
//    @ApiOperation(value = "获取用户权限菜单集合")
//    @RequestMapping(value = "/ssoMenu/getMenusByUserId", method = RequestMethod.POST)
//    public Object getMenusByUserId(ModelMap modelMap, @RequestParam(name = "type", required = false) String type, @RequestHeader(value = "token", defaultValue = "") String token, @RequestParam(name = "excludeMenusNames", required = false) String excludeMenusNames) {
//        //从登录缓存中获取当前用户信息
//        String userId = (String) redisHelper.hget(token, Constants.UserSession.userId);
//        SysUser user = sysUserService.getById(userId);
//        //未登录
//        if (user == null) {
//            modelMap.put("httpCode", 401);
//            return ResponseEntity.ok(modelMap);
//        }
//
//        //获取用户下的所有菜单
//       /* List<SysUserRole> sysUserRoles = sysUserRoleService.getRoleIdsByUserId(userId);
//        List<String> roleIds = new ArrayList<>();
//        if(!CollectionUtils.isEmpty(sysUserRoles)){
//            sysUserRoles.forEach((sysUserRole -> {
//                if(sysUserRole != null) {
//                    roleIds.add(sysUserRole.getRoleId());
//                }
//            }));
//        }*/
//        List<String> roleIds=sysUserRoleService.getRoleIdsByUserId(userId);
//        //通过UserGroup获取的roleId集合
//        List<SysUserGroup> ugList=sysUserGroupService.getUserGroupsByUserId(userId);
//        List<String> idsList2=new ArrayList<>();
//        if(ugList.size()>0){
//            for (SysUserGroup sug:ugList) {
//                SysRoleGroup rg=sysGroupService.getById(sug.getGroupId());
//                List<SysRoleRoleGroup> rrpList= sysRoleRoleGroupService.getListByGroupId(sug.getGroupId());
//                if(rrpList.size()>0){
//                    for (SysRoleRoleGroup sysRoleRoleGroup: rrpList) {
//                        idsList2.add(sysRoleRoleGroup.getRoleId());
//                    }
//                }
//            }
//        }
//        // List<String> roleIdList2=IdUtil.getIdsByStr(sysRoleGroupMapper.selectById(sysUserForm.getGroupId()).getRoleIds());
//        roleIds.remove(idsList2);
//        roleIds.addAll(idsList2);
//   /*     String roleIds = sysGroupService.getById(user.getGroupId()).getRoleIds();*/
//       List<SysResourceForm> list = new ArrayList<>();
//        for(String roleId:roleIds){
//            list.addAll(sysResourceService.getAllResourceByRoleId(roleId));
//        }
//        //去重
//        HashSet h = new HashSet(list);
//        list.clear();
//        list.addAll(h);
//
//        List<DgapResourceForm> data =  formatDgap(list);
//        modelMap.put("data", data);
//        modelMap.put("httpCode", 200);
//        return ResponseEntity.ok(modelMap);
//    }
//
//
//    @SofnLog("登录")
//    @PostMapping("/login")
//    @ApiOperation(value = "登录")
//    public Object login(
//            @ApiParam(value = "用户名") @RequestParam String account,
//            @ApiParam(value = "密码") @RequestParam String password,
//            @ApiParam(value = "验证码id") @RequestParam String uuid,
//            @ApiParam(value = "验证码") @RequestParam String code,
//            @ApiParam(value = "记住密码,Y:记住 N:不记住") @RequestParam(required = false, defaultValue = "N") String rememberMe) {
//
//        if (StringUtils.isBlank(account)) {
//            throw new SofnException("参数account未找到");
//        }
//
//        if (StringUtils.isBlank(password)) {
//            throw new SofnException("password");
//        }
//
//        if (StringUtils.isBlank(uuid)) {
//            throw new SofnException("captchaId");
//        }
//
//        if (StringUtils.isBlank(code)) {
//            throw new SofnException("captcha");
//        }
//
//        if (StringUtils.isBlank(uuid) || StringUtils.isBlank(code)) {
//            throw new SofnException(ExceptionType.CAPTCHA_NOT_INPUT);
//        }
//
//        // 获取redis中缓存的验证码
//        String cacheCaptcha = (String) redisHelper.get(uuid);
//
//        // 比较验证码是否相同
//        if (!StringUtils.equalsIgnoreCase(code, cacheCaptcha)) {
//            // 默认是sofn 这里上线的时候需要取消 如果不相等
//            cacheCaptcha = "sofn";
//            if (!StringUtils.equalsIgnoreCase(code, cacheCaptcha)) {
//                throw new SofnException(ExceptionType.CAPTCHA_CORRECT);
//            }
//        }
//
//        SysUser user = sysUserService.findByUserName(account);
//        if (user == null) {
//            throw new SofnException(ExceptionType.USER_NOT_EXIST);
//        }
//
//        String saltPassword = ShiroUtils.sha256(password, user.getSalt());
//        if (!StringUtils.equals(saltPassword, user.getPassword())) {
//            throw new SofnException(ExceptionType.PASSWORD_ERROR);
//        }
//
//        SysUserForm sysUserForm = new SysUserForm();
//        BeanUtils.copyProperties(user, sysUserForm);
//
//        Set<String> allPermissionList = new HashSet<>();
//        Set<String> roleList = new HashSet<>();
//
//        // 查询用户角色
//        List<SysRole> roles = sysUserService.loadRolesByUserId(user.getId());
//        roles.forEach(sysRole -> {
//            roleList.add(sysRole.getRoleName());
//        });
//
//        sysUserForm.setRoleNames(StringUtils.join(roleList, ","));
//        sysUserForm.setResourceList(allPermissionList);
//        // 生成token
//        String token = JwtUtils.generateToken(account);
//        cacheUserInfo(token, user, roles, rememberMe);
//
////        return Result.ok(new Auth(token, sysUserForm));
//        ModelMap modelMap = new ModelMap();
//        modelMap.addAttribute("token", token);
//        modelMap.addAttribute("userType", "NORMAL");
//        modelMap.addAttribute("user", user);
//        modelMap.addAttribute("httpCode", 200);
//        return ResponseEntity.ok(modelMap);
//    }
//
//    /**
//     * 缓存用户登录信息
//     *
//     * @param token
//     * @param user       登录用户
//     * @param roleList   用户角色名称列表
//     * @param rememberMe 是否记住密码
//     */
//    private void cacheUserInfo(String token, SysUser user, List<SysRole> roleList, String rememberMe) {
//        String lastToken = (String) redisHelper.hget(Constants.UserSession.userNameToken, user.getUsername());
//        if (StringUtils.isNotBlank(lastToken)) {
//            //删除缓存
//            String dgapLoginToken = String.format("LOGIN:login_key#token=%s", lastToken);
//            redisHelper.del(dgapLoginToken);
//            redisHelper.del(lastToken);
//        }
//        // 缓存登录信息
//        if (!redisHelper.hset(Constants.UserSession.userNameToken, user.getUsername(), token) ||
//                !redisHelper.hset(token, Constants.UserSession.userId, user.getId()) ||
//                !redisHelper.hset(token, Constants.UserSession.user, JsonUtils.obj2json(user)) ||
//                !redisHelper.hset(token, Constants.UserSession.userName, user.getUsername()) ||
//                !redisHelper.hset(token, Constants.UserSession.userOrganizationId, user.getOrganizationId()) ||
//                !redisHelper.hset(token, Constants.UserSession.userRoles, JsonUtils.obj2json(roleList))) {
//            redisHelper.del(token);
//            throw new SofnException(ExceptionType.CACHE_ERROR);
//        }
//
//        // 设置过期时间
//        if (BoolUtils.isTrue(rememberMe)) {
//            redisHelper.hset(token, Constants.UserSession.rememberMe, rememberMe);
//            redisHelper.expire(token, ymlConfig.rememberExpireTime/1000);
//            redisHelper.expire(user.getUsername(), ymlConfig.rememberExpireTime/1000);
//        } else {
//            redisHelper.expire(token, ymlConfig.tokenExpireTime/1000);
//            redisHelper.expire(user.getUsername(), ymlConfig.tokenExpireTime/1000);
//        }
//
//        String loginKey = String.format("LOGIN:login_key#token=%s", token);
//
//        //将转换后的user信息缓存到redis
//        CurrentUser dgapUser = new CurrentUser();
//        SysOrganization sysOrganization = sysOrganizationService.getSysOrganizationById(user.getOrganizationId());
//        BeanFormatUtil.DgapUserBeanTrans(user,dgapUser, sysOrganization);
//        redisHelper.set(loginKey, JsonUtils.obj2json(dgapUser), 30 * 60);
//    }
//
//
//    private List<DgapResourceForm> formatDgap(List<SysResourceForm> list){
//        List<DgapResourceForm> result = new ArrayList<>();
//        for(SysResourceForm resourceForm: list){
//            DgapResourceForm dgapResourceForm = new DgapResourceForm();
//            BeanFormatUtil.DgapResourceBeanTrans(resourceForm, dgapResourceForm);
//            result.add(dgapResourceForm);
//        }
//        result.sort(new Comparator<DgapResourceForm>(){
//            public int compare(DgapResourceForm o1,DgapResourceForm o2){
//                return o1.getNumbers().compareTo(o2.getNumbers());
//            }
//        });
//
//        return result;
//    }
//}
