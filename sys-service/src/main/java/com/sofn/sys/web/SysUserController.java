package com.sofn.sys.web;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sofn.common.excel.ExcelExportUtil;
import com.sofn.common.excel.ExcelImportUtil;
import com.sofn.common.excel.properties.ExcelPropertiesUtils;
import com.sofn.common.exception.SofnException;
import com.sofn.common.fileutil.FileDownloadUtils;
import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import com.sofn.common.utils.*;
import com.sofn.common.utils.shiro.ShiroUtils;
import com.sofn.common.web.BaseController;
import com.sofn.sys.enums.SysConstant;
import com.sofn.sys.model.*;
import com.sofn.sys.service.SysRoleService;
import com.sofn.sys.service.SysSubsystemService;
import com.sofn.sys.service.SysUserRolePermissionService;
import com.sofn.sys.service.SysUserService;
import com.sofn.sys.vo.*;
import com.sofn.sys.vo.Excel.ImportSysUserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
@Api(tags = "用户相关接口")
@Slf4j
public class SysUserController extends BaseController {

    @Autowired
    private SysUserService userService;

    @Autowired
    private SysUserRolePermissionService sysUserRolePermissionService;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    SysSubsystemService sysSubsystemService;

    //    @RequiresPermissions("sys:user:list")
    @GetMapping("/userList")
    @ApiOperation(value = "分页查询用户信息", notes = "权限:(sys:user:index)")
    @SofnLog(value = "按条件分页获取用户信息")
    public Result<PageUtils<SysUserForm>> queryList(@RequestParam(required = false) @ApiParam("状态") String status,
                                                    @RequestParam(required = false) @ApiParam("关键字(用户名/昵称/邮箱/手机号)") String keyword,
                                                    @RequestParam(value = "pageNo") Integer pageNo,
                                                    @RequestParam(value = "pageSize") Integer pageSize) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("status", status);
        params.put("keyword", keyword);
        PageUtils<SysUserForm> pageUtils = userService.findAllUserList(params, pageNo, pageSize);
        return Result.ok(pageUtils);
    }


    //    @RequiresPermissions("sys:user:getOne")
    @PostMapping("/getUserOne")
    @ApiOperation(value = "根据ID查询用户", notes = "权限:(sys:user:detail)")
    @SofnLog(value = "按ID获取用户信息")
    public Result<SysUserForm> getOne(@ApiParam(name = "id", value = "用户ID", required = true) @RequestParam("id") String id) {
        List<SysUserForm> userInfoByIds = userService.getUserInfoByIds(Lists.newArrayList(id));
        if (!CollectionUtils.isEmpty(userInfoByIds)) {
            return Result.ok(userInfoByIds.get(0));
        }
        return Result.ok(null);
    }

    //    @RequiresPermissions("sys:user:create")
    @ApiOperation(value = "创建用户" , notes = "权限:(sys:user:create)")
    @PostMapping("/createUser")
    @SofnLog(value = "创建用户",type = "新增")
    public Result<String> create(@Validated @RequestBody SysUserForm sysUserForm, BindingResult result) {
        String errMsg = this.getErrMsg(result);
        if (!StringUtils.isEmpty(errMsg)) {
            return Result.error(errMsg);
        }
        SysUser sysUser = SysUserForm.getSysUser(sysUserForm);

        String userId= userService.saveSysUser(sysUser);
        //将Id放入ok
        return Result.ok(userId);
    }

    @ApiOperation(value = "根据角色码创建用户", notes = "权限:(sys:user:createbyrolecode)")
    @PostMapping("/createByRoleCode")
    @SofnLog(value = "根据角色码创建用户", type = "新增")
    public Result<String> createByRoleCode(@Validated @RequestBody SysUserRoleCodeForm sysUserForm, BindingResult result) {
        String errMsg = this.getErrMsg(result);
        if (!StringUtils.isEmpty(errMsg)) {
            return Result.error(errMsg);
        }
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(sysUserForm, sysUser);
        sysUser.setSex(sysUserForm.getSex());
        sysUser.setInitPassword(sysUserForm.getInitPassword());
        if (!StringUtils.isEmpty(sysUserForm.getRoleCodes())) {
            String[] roleCodes = sysUserForm.getRoleCodes().split(",");
            List<SysRole> roleList = new ArrayList<>();
            for (String roleCode : roleCodes) {
                SysRole sysRole = sysRoleService.getSysRoleByAppIdAndRoleCode(sysUserForm.getAppId(), roleCode);
                roleList.add(sysRole);
            }
            sysUser.setRoleList(roleList);
        }

        String id = userService.saveSysUser(sysUser);
        return Result.ok(id, "");
    }

    /**
     * 修改用户的基本信息(不含密码)
     *
     * @param result 用户信息
     * @return
     */
//    @RequiresPermissions("sys:user:update")
    @ApiOperation(value = "修改用户基本信息(不含密码)", notes = "权限:(sys:user:update)")
    @PostMapping("/updateUser")
    @SofnLog(value = "修改用户基本信息", type = "修改")
    public Result<String> update(@Validated @RequestBody SysUserForm sysUserForm, BindingResult result) {
        String errMsg = this.getErrMsg(result);
        if (!StringUtils.isEmpty(errMsg)) {
            return Result.error(errMsg);
        }

        SysUser storeUser = userService.getById(sysUserForm.getId());
        if (storeUser == null) {
            throw new SofnException("用户不存在");
        }

        SysUser sysUser = SysUserForm.getSysUserforEdit(sysUserForm);
        //更新初始化密码
       /* if (StringUtils.isNotBlank(sysUserForm.getInitPassword())) {
            String pdMd5 = DigestUtils.md5DigestAsHex(sysUserForm.getInitPassword().getBytes());
            String saltPwd = ShiroUtils.sha256(pdMd5, storeUser.getSalt());
            sysUser.setPassword(saltPwd);
        }*/
        userService.updateSysUser(sysUser);
        return Result.ok();
    }

    /**
     * 批量删除用户
     *
     * @param ids
     * @return
     */
    @ApiOperation(value = "批量删除用户", notes = "权限:(sys:user:batchDelete)")
    @PostMapping("/batchDeleteUser/{ids}")
    @SofnLog(value = "批量删除用户",type = "删除")
    public Result<String> deleteBatchByIds(
            @ApiParam(value = "待删除IDS，ID用英文,号分隔") @PathVariable(value = "ids") String ids) {

        if (StringUtils.isEmpty(ids)) {
            return Result.ok();
        }
        List<String> idList = IdUtil.getIdsByStr(ids);
        userService.batchDelete(idList);
        return Result.ok();
    }

    /**
     * 根据系统appid批量删除用户
     */
    @ApiOperation(value = "根据系统APPID批量删除用户", notes = "权限:(sys:user:batchdeletebyappid)")
    @PostMapping("/delUserByAppIdAndIds")
    @SofnLog(value = "批量删除用户", type = "批量删除")
    public Result<String> deleteBatchByIds(@Validated @RequestBody SysDelUserForm delUserForm, BindingResult result) {
      if (result.hasErrors()) {
        return Result.error(getErrMsg(result));
      }

      String appId = delUserForm.getAppId();
      SysSubsystem sysSubsystem = sysSubsystemService.getSysSubsystemAppId(appId);
      if (sysSubsystem == null) {
        throw new SofnException("未找到子系统");
      }

      userService.batchDelete(delUserForm.getIds());
      return Result.ok();
    }

    //    @RequiresPermissions("sys:user:changepwd")
    @ApiOperation(value = "更新密码", notes = "权限:(sys:user:changepwd)")
    @PostMapping("/changePwd")
    @SofnLog(value = "修改密码",type = "修改")
    public Result<String> changePassword(@Validated @RequestBody @ApiParam(name = "修改密码对象", value = "传入json格式", required = true) UpdatePasswordVo updatePasswordVo, BindingResult result) {
        // 校验参数
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }

        userService.updatePassword(updatePasswordVo.getId(), updatePasswordVo.getOldPassword(), updatePasswordVo.getNewPassword());
        return Result.ok("修改成功！");
    }

    @ApiOperation(value = "重置密码", notes = "权限:(sys:user:resetPwd)")
    @PostMapping("/resetPwd")
    @SofnLog(value = "重置密码",type = "修改")
    public Result<String> resetPwd(@ApiParam(name = "id", value = "用户ID", required = true) @RequestParam("id") String id) {
        userService.resetPwd(id);
        return Result.ok();
    }

    @ApiOperation(value = "根据appId获取用户有的权限", notes = "权限码:(sys:user:getUserHavePermission)")
    @GetMapping("/getUserHavePermission/{appId}")
    @SofnLog(value = "根据appId获取用户有的权限")
    public Result<Map<String, Set<String>>> getUserHavePermission(
            @ApiParam(value = "子系统标识") @PathVariable("appId") String appId) {
        String userId = UserUtil.getLoginUserId();
        if (org.apache.commons.lang.StringUtils.isBlank(userId)) {
            return Result.error("用户未登录");
        }
        Map<String, Set<String>> maps = sysUserRolePermissionService.getUserHasPermission(userId, appId);
        return Result.ok(maps);
    }

    @ApiOperation(value = "查询当前用户信息的接口")
    @GetMapping("/getLoginUser")
    @SofnLog(value = "查询当前用户信息的接口")
    public Result<SysUserForm> getLoginUser() {
        SysUserForm user = null;
        String loginUserJson = UserUtil.getLoginUserJson();
        if (StringUtils.isNotBlank(loginUserJson)) {
          user = JsonUtils.json2obj(loginUserJson, SysUserForm.class);
        }

        if (user != null) {
            return Result.ok(user);
        } else {
            throw new UnauthenticatedException();
        }
    }

    @ApiOperation(value = "批量导出用户", notes = "权限码(sys:user:exportSysUser)", produces = "application/octet-stream")
    @GetMapping(value = "/exportSysUser", produces = "application/octet-stream")
    public void exportSysUser(@RequestParam(required = false) @ApiParam(value = "用户名称") String userName,
                              @RequestParam(required = false) @ApiParam("关键字(用户名/昵称/邮箱/手机号)") String keyword,
                              @RequestParam(required = false) @ApiParam("机构ID")  String organizationId,
                              HttpServletResponse response) {
        Map<String, String> params = Maps.newHashMap();
        params.put("userName", userName);
        params.put("keyword", keyword);
        params.put("organizationId", organizationId);

        List<ImportSysUserVo> userList = userService.getExportUserInfo(params);
        String filePath = ExcelPropertiesUtils.getExcelFilePath();
        filePath += "/用户信息.xlsx";
        ExcelExportUtil.createExcel(filePath, ImportSysUserVo.class, userList);
        FileDownloadUtils.downloadAndDeleteFile(filePath, response);
    }

    @ApiOperation(value = "导出用户模板", notes = "权限码(sys:user:export)", produces = "application/octet-stream")
    @GetMapping(value = "/exportUserTemplate", produces = "application/octet-stream")
    public void exportUserTemplate(HttpServletResponse response) {
        String filePath = ExcelPropertiesUtils.getExcelFilePath();
        filePath += "/用户导入模板.xlsx";
        File file = new File(filePath);
        if (file.exists()) {
            FileDownloadUtils.downloadFile(filePath, response);
            log.info("磁盘中存在用户导入模板文件，不在重新生成");
        } else {
            ExcelExportUtil.createTemplate(filePath, ImportSysUserVo.class);
            FileDownloadUtils.downloadFile(filePath, response);
        }
    }


    @ApiOperation(value = "批量导入用户", notes = "权限码(sys:user:import)")
    @PostMapping(value = "/importSysUser")
    public Result<String> importSysUser(@ApiParam(value = "Excel文件") @RequestPart(value = "file") MultipartFile file) {
        try {
            List<ImportSysUserVo> importSysUserVoList = ExcelImportUtil.getDataByExcel(file, ImportSysUserVo.class);
            if (!CollectionUtils.isEmpty(importSysUserVoList)) {
                userService.batchInsert(importSysUserVoList);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
        return Result.ok("导入成功");
    }

    @ApiOperation(value = "通过子系统id和行政机构级别分页查寻用户列表", notes = "权限码(sys:user:getUserListByCondition)")
    @PostMapping(value = "/getUserListByCondition")
    @SofnLog(value = "通过子系统id和行政机构级别分页查寻用户列表")
    public Result<PageUtils<SysUserForm>> getUserListByCondition(@RequestParam @ApiParam(value = "子系统id", required = true) String subsystemId,
                                                                 @RequestParam @ApiParam(value = "机构级别", required = true) String organizationLevel,
                                                                 @RequestParam(required = false, defaultValue = "N") @ApiParam(value = "是否包含代理机构用户，Y包含，N不包含", defaultValue = "N") String proxyUser,
                                                                 @RequestParam(value = "pageNo") Integer pageNo,
                                                                 @RequestParam(value = "pageSize") Integer pageSize) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("subsystemId", subsystemId);
        params.put("organizationLevel", organizationLevel);
        params.put("proxyUser", proxyUser);
        PageUtils<SysUserForm> pageUtils = userService.findUserListBySubsystem(params, pageNo, pageSize);
        return Result.ok(pageUtils);
    }

    @ApiOperation(value = "通过组织机构id获取机构下的所有用户列表", notes = "权限码(sys:user:getUserListByOrgId)")
    @GetMapping(value = "/getUserListByOrgId")
    @SofnLog(value = "通过组织机构id获取机构下的所有用户列表")
    public Result<List<SysUserForm>> getUserListByOrgId(@RequestParam @ApiParam(value = "组织机构ID", required = true) String orgId,
                                                        @RequestParam(required = false, defaultValue = "N")
                                                        @ApiParam(value = "是否包含代理机构用户，Y包含，N不包含", defaultValue = "N") String proxyUser
    ) {
        List<SysUser> userListByOrgId = userService.getUserListByOrgId(orgId, proxyUser);
        List<SysUserForm> userInfoByIds = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(userListByOrgId)) {
            List<String> userId = userListByOrgId.stream().map(SysUser::getId).collect(Collectors.toList());
            userInfoByIds = userService.getUserInfoByIds(userId);
        }
        return Result.ok(userInfoByIds);
    }

    @ApiOperation(value = "通过IDS批量查询用户信息", notes = "权限码(sys:user:getUserInfoByIds)")
    @GetMapping(value = "/getUserInfoByIds")
    @SofnLog(value = "通过IDS批量查询用户信息")
    public Result<List<SysUserForm>> getUserInfoByIds(@RequestParam @ApiParam(value = "ids，多个使用,隔开", required = true) String ids) {
        if (!StringUtils.isBlank(ids)) {
            List<SysUserForm> userInfoByIds = userService.getUserInfoByIds(IdUtil.getIdsByStr(ids));
            return Result.ok(userInfoByIds);
        }
        return Result.ok(Lists.newArrayList());
    }

    @ApiOperation(value = "只能更改必要的属性，如果属性为空，不更改属性，备注除外", notes = "权限码(sys:user:updateUserInfo)")
    @PostMapping(value = "/updateUserInfo")
    @SofnLog(value = "只能更改必要的属性，如果属性为空，不更改属性，备注除外", type = "修改")
    public Result<String> updateUserInfo(@Validated @RequestBody @ApiParam(name = "修改用户信息接口", value = "传入json格式", required = true) UpdateUserInfoVo updateUserInfoVo, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        userService.updateUserInfo(updateUserInfoVo);
        return Result.ok("修改成功");
    }

    @ApiOperation(value = "获取基本用户信息", notes = "权限码(sys:user:getBasicUserInfo)")
    @PostMapping(value = "/getBasicUserInfo")
    @SofnLog(value = "获取基本用户信息")
    public Result<PageUtils<BasicUserInfo>> getBasicUserInfo(@RequestBody @ApiParam(name = "查询用户基本信息对象", value = "传入json格式", required = true)
                                                                     SelectUserConditionVo selectUserConditionVo
    ) {
        return Result.ok(userService.getBasicUserInfo(selectUserConditionVo, selectUserConditionVo.getPageNo(), selectUserConditionVo.getPageSize()));

    }

    @ApiOperation(value = "获取基本用户信息", notes = "权限码(sys:user:getBasicUserInfo)")
    @PostMapping(value = "/getBasicUserInfoNew")
    @SofnLog(value = "获取基本用户信息")
    public Result<PageUtils<BasicUserInfo>> getBasicUserInfoNew(@RequestBody @ApiParam(name = "查询用户基本信息对象", value = "传入json格式", required = true)
                                                                     SelectUserConditionVo selectUserConditionVo
    ) {
        return Result.ok(userService.getBasicUserInfoNew(selectUserConditionVo, selectUserConditionVo.getPageNo(), selectUserConditionVo.getPageSize()));

    }

    @ApiOperation(value = "用户账号重复性校验", notes = "权限码(sys:user:checkUserNameExist)")
    @GetMapping(value = "/checkUserNameExist")
    @SofnLog(value = "用户账号重复性校验")
    public Result<String> checkUserNameExist(@ApiParam(value = "用户ID,非必传，不为空此条记录不会包括在检测范围") @RequestParam(value = "id", required = false) String id,
                                             @ApiParam(value = "用户名") @RequestParam(value = "username") String username) {
        boolean isExist = userService.checkUsernameExist(id, username);
        return Result.ok(isExist ? BoolUtils.Y : BoolUtils.N, "");
    }

    @GetMapping(value = "/getRoleHaveUserInfo")
    @ApiOperation(value = "根据角色查询用户", notes = "权限码(sys:user:getRoleHaveUserInfo)")
    @SofnLog(value = "根据角色查询用户")
    public Result<BasicUserInfo> getRoleHaveUserInfo(@ApiParam(value = "roleId", required = true) @RequestParam String roleId,
                                                           @ApiParam(value = "username") @RequestParam(required = false) String username,
                                                           @ApiParam(value = "nickname") @RequestParam(required = false) String nickname,
                                                           @RequestParam(value = "pageNo") @ApiParam(value = "pageNo") Integer pageNo,
                                                           @RequestParam(value = "pageSize")  @ApiParam(value = "pageSize")Integer pageSize) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("roleId",roleId);
        params.put("username",username);
        params.put("nickname",nickname);
        PageUtils<BasicUserInfo> roleHaveUserInfo = userService.getRoleHaveUserInfo(params, pageNo, pageSize);
        return Result.ok(roleHaveUserInfo);
    }

    @GetMapping(value = "/getRoleDontHaveUserInfo")
    @ApiOperation(value = "根据角色查询未分配的用户", notes = "权限码(sys:user:getRoleDontHaveUserInfo)")
    @SofnLog(value = "根据角色查询未分配的用户")
    public Result<List<BasicUserInfo>> getRoleDontHaveUserInfo(@ApiParam(value = "roleId", required = true) @RequestParam String roleId,
                                                               @ApiParam(value = "username") @RequestParam(required = false) String username,
                                                               @ApiParam(value = "nickname") @RequestParam(required = false) String nickname,
                                                               @ApiParam(value = "pageNo") @RequestParam Integer pageNo,
                                                               @ApiParam(value = "pageSize") @RequestParam Integer pageSize) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("roleId",roleId);
        params.put("username",username);
        params.put("nickname",nickname);

        PageUtils<List<BasicUserInfo>> roleDontHaveUserInfo = userService.getRoleDontHaveUserInfo(params, pageNo, pageSize);
        return Result.ok(roleDontHaveUserInfo);
    }

    @GetMapping(value = "/getUserInfoByUsername")
    @ApiOperation(value = "根据用户名称查询用户信息，未返回角色资源等信息", notes = "权限码(sys:user:getUserInfoByUsername)")
    @SofnLog(value = "根据用户名称查询用户信息，未返回角色资源等信息")
    public  Result<SysUserForm> getUserInfoByUsername(@ApiParam(value = "username", required = true) @RequestParam String username){
        return Result.ok(userService.getUserInfoByUsername(username));
    }



    @GetMapping(value = "/getUserInfoByRegionCodeAndAppId")
    @ApiOperation(value = "根据区划代码和系统代码获取用户信息", notes = "权限码(sys:user:getUserInfoByRegionCodeAndAppId)")
    @SofnLog(value = "根据区划代码和系统代码获取用户信息")
    public Result<List<SysUserForm>> getUserInfoByRegionCodeAndAppId(@ApiParam(value = "区划代码", required = true) @RequestParam String regionCode,
                                                                     @ApiParam(value = "系统AppId", required = true) @RequestParam String appId){
        List<SysUserForm> userInfoByRegionCodeAndAppId = userService.getUserInfoByRegionCodeAndAppId(regionCode, appId);
        return Result.ok(userInfoByRegionCodeAndAppId);
    }

    @GetMapping(value = "/getUserByOrgInfoAndAppId")
    @ApiOperation(value = "根据机构信息和系统代码获取用户信息", notes = "权限码(sys:user:getUserInfoByRegionCodeAndAppId)")
    @SofnLog(value = "根据区划代码和系统代码获取用户信息")
    public Result<List<SysUserForm>> getUserByOrgInfoAndAppId(@ApiParam(value = "区划代码集合") @RequestParam(required = false) List<String> regionCodes,
                                                                     @ApiParam(value = "机构级别") @RequestParam(required = false) String orgLevel,
                                                                     @ApiParam(value = "系统AppId", required = true) @RequestParam String appId){
        List<SysUserForm> userFormList = userService.getUserByOrgInfoAndAppId(regionCodes, appId, orgLevel, false);
        return Result.ok(userFormList);
    }

    @GetMapping(value = "/getCurrentUserCanShowAllUser")
    @ApiOperation(value = "获取当前用户能够查看的所有用户信息，只返回了ID和username和nickname", notes = "权限码(sys:user:getCurrentUserCanShowAllUser)")
    @SofnLog(value = "获取当前用户能够查看的所有用户信息，只返回了ID和username和nickname")
    public Result<List<BasicUserInfo>> getCurrentUserCanShowAllUser(){
        List<BasicUserInfo> allUser = userService.getAllUser();
        return Result.ok(allUser);
    }
    @GetMapping(value = "/getUserSystemList")
    @ApiOperation(value = "获取当前用户常用的子系统信息", notes = "权限码(sys:user:getUserSystemList)")
    @SofnLog(value = "获取当前用户常用的子系统信息")
    public Result<List<SysUserSystem>> getUserSystemList(HttpServletRequest request){
        //根据token获取登录用户Id
        String userId = UserUtil.getLoginUserId(request.getHeader("Authorization"));

        if(StringUtils.isBlank(userId)){
            throw new SofnException("当前登录用户异常");
        }
        List<SysUserSystem> userSystemList = userService.getUserUseSystemList(userId);
        return Result.ok(userSystemList);
    }

    @GetMapping(value = "/addUserSystem")
    @ApiOperation(value = "新增一个用户常用的子系统信息", notes = "权限码(sys:user:addUserSystem)")
    @SofnLog(value = "新增一个用户常用的子系统信息")
    public Result<String> addUserSystem(HttpServletRequest request,String systemId){
        //根据token获取登录用户Id
        String userId = UserUtil.getLoginUserId(request.getHeader("Authorization"));

        if(StringUtils.isBlank(userId)){
            throw new SofnException("当前登录用户异常");
        }
        String resultString= userService.addUserSystem(userId,systemId);
        return Result.ok(resultString);
    }

}

