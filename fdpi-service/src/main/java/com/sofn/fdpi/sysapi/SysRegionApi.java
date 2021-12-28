package com.sofn.fdpi.sysapi;

import com.sofn.common.config.FeignConfiguration;
import com.sofn.common.fileutil.SysFileManageVo;
import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import com.sofn.common.utils.shiro.JWTToken;
import com.sofn.fdpi.sysapi.bean.*;
import com.sofn.fdpi.sysapi.bean.SysThirdOrgForm;
import com.sofn.fdpi.vo.*;
import com.sofn.fdpi.vo.SysRegionInfoVo;
import com.sofn.fdpi.vo.SysUserForm;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 使用Feign 调用sys服务
 *
 * @author chenlf
 * @date 2019/9/16 14:26
 */
@FeignClient(value = "sys-service", configuration = FeignConfiguration.class
)
public interface SysRegionApi {
    /**
     * 通过组织id获取用户区域信息
     *
     * @param orgId
     * @return
     */

    @GetMapping("/sysOrganization/getSysRegionInfoByOrgId/{orgId}")
    Result<SysRegionInfoVo> getSysRegionInfoByOrgId(@PathVariable("orgId") @ApiParam(value = "机构IDS", required = true) String orgId);

    /**
     * 根据条件获取组织机构信息
     *
     * @param ids
     * @param orgName
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping("/sysOrganization/getInfoByCondition")
    Result<List<Map<String, String>>> getInfoByCondition(@RequestParam(name = "ids", required = false) @ApiParam(value = "机构IDS") String ids,
                                                         @RequestParam(name = "orgName", required = false) @ApiParam(value = "机构名称模糊查询") String orgName,
                                                         @RequestParam(name = "pageNo", required = false) @ApiParam(value = "从哪开始") Integer pageNo,
                                                         @RequestParam(name = "pageSize", required = false) @ApiParam(value = "显示多少条，如果为0或者空不分页") Integer pageSize);

    /**
     * 获取区县信息
     *
     * @return Result
     */
    @GetMapping(value = "/sysRegion/getSysRegionOne/{areaId}")
    Result<SysRegionForm> getSysRegionOne(@ApiParam(value = "行政区划ID", required = true) @PathVariable(value = "areaId") String areaId);

    /**
     * 根据areaId获取
     *
     * @return Result
     */
    @GetMapping("/sysRegion/getParentNodeByRegionCode/{regionCode}")
    Result<List<SysRegionTreeVo>> getParentNode(@ApiParam(value = "行政区划ID", required = true) @PathVariable(value = "regionCode") String regionCode);

    /**
     * 根据userId获取用户角色
     *
     * @return Result
     */
    @PostMapping("/user/getUserOne")
    Result<SysUserForm> getOne(@ApiParam(name = "id", value = "用户ID", required = true) @RequestParam("id") String id);

    /**
     * @param parentId
     * @return
     */
    @GetMapping("/sysRegion/getListByParentId/{parentId}")
    Result<List<SysRegionForm>> getListByParentId(@PathVariable("parentId") String parentId);

    /**
     * 注册接口
     *
     * @param sysUserForm
     * @return
     */
    @PostMapping("/user/createUser")
    Result<String> create(@RequestBody SysUserForm sysUserForm);

    /**
     * 上传文件，调用sys服务接口
     *
     * @return Result
     */
    @PostMapping(value = "/fileManage/uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Result<SysFileVo> uploadFile(
            @ApiParam(value = "文件名") @RequestParam(value = "fileName", required = false) String fileName,
            @ApiParam(value = "备注") @RequestParam(value = "remark", required = false) String remark,
            @ApiParam(value = "所属系统", required = true) @RequestParam("parentId") String parentId,
            @ApiParam(value = "接口号，默认为hidden，请注意所有hidden开头的接口号都不会在文件管理中显示")
            @RequestParam(value = "interfaceNum", defaultValue = "hidden") String interfaceNum,
            @ApiParam(value = "文件") @RequestPart(value = "file") MultipartFile file);


    /**
     * 下载文件，调用sys服务接口
     *
     * @return Result
     */
    @PostMapping("/fileManage/downloadFile/{id}")
    void downloadFile(@ApiParam(value = "要下载的文件ID", required = true) @PathVariable(value = "id") String id, HttpServletResponse response);

    /**
     * 激活文件
     */
    @PostMapping(value = "/fileManage/activationFile", consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<List<SysFileVo>> activationFile(@RequestBody @ApiParam(name = "添加文件对象", value = "传入json格式", required = true)
                                                   SysFileManageForm sysFileManageForm);

    /**
     * 替换文件，不需要调用激活文件，但是替换之前需要先调用上传文件获取newFileId
     */
    @GetMapping("/fileManage/replaceFile")
    Result replaceFile(
            @ApiParam(value = "要替换的文件ID", required = true) @RequestParam("id") String id,
            @ApiParam(value = "备注") @RequestParam(value = "remark", required = false) String remark,
            @ApiParam(value = "新文件Id", required = true) @RequestParam("newFileId") String newFileId
    );


    /**
     * 删除支撑平台中的文件
     *
     * @param id：文件id
     * @return
     */
    @PostMapping("/fileManage/deleteFile")
    Result delFile(@ApiParam("要删除的文件ID") @RequestParam("id") String id);

    /**
     * 批量删除支撑平台中的文件
     *
     * @param ids
     * @return
     */
    @GetMapping(value = "/fileManage/batchDeleteFile/{ids}")
    Result batchDeleteFile(@PathVariable("ids") @ApiParam(value = "多个ID使用,隔开") String ids);

    /**
     * 通过机构id获取机构信息
     *
     * @param id 机构id
     * @return 机构信息
     */
    @PostMapping("/sysOrganization/getOrgInfoById")
    Result<SysOrganizationForm> getOrgInfoById(@ApiParam(value = "机构ID", required = true) @NotNull @RequestParam("id") String id);

    /**
     * 修改当前用户的密码
     *
     * @param updatePasswordVo
     * @return
     */
    @PostMapping(value = "/user/changePwd", consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<String> changePassword(@RequestBody @ApiParam(name = "修改密码对象", value = "传入json格式", required = true) UpdatePasswordVo updatePasswordVo);

    @PostMapping("/user/getUserOne")
    Result<SysUserForm> getUserInfo(@ApiParam(name = "id", value = "用户ID", required = true) @RequestParam("id") String id);

    @GetMapping("/user/getLoginUser")
    Result<SysUser> getLoginUser();

    @GetMapping("/user/getLoginUser")
    Result<SysUserForm> getLoginUserForm();


    /**
     * 根据角色码创建用户:权限:(sys:user:createbyrolecode)
     *
     * @param sysUserForm 用户对象
     * @return Result<String> 支撑平台用户id
     */
    @ApiOperation(value = "根据角色码创建用户")
    @PostMapping(value = "/user/createByRoleCode", consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<String> createByRoleCode(@RequestBody SysUserRoleCodeForm sysUserForm);

    /**
     * 批量删除用户
     *
     * @param ids
     * @return
     */
    @PostMapping("/user/batchDeleteUser/{ids}")
    Result<String> deleteBatchByIds(@ApiParam(value = "待删除IDS，ID用英文,号分隔") @PathVariable(value = "ids") String ids);

    /**
     * 创建非行政机构 权限点:(sys:organization:createthird)
     *
     * @param sysThirdOrgForm 第三方机构json对象
     * @return
     */
    @PostMapping(value = "/sysOrganization/createThirdOrganization", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    Result<String> createThirdOrganization(@RequestBody SysThirdOrgForm sysThirdOrgForm);

    /**
     * 删除机构
     */
    @PostMapping("/sysOrganization/delete")
    Result<String> deleteOrg(@NotBlank @RequestParam("id") String id);

    /**
     * 批量查看用户信息
     *
     * @param ids
     * @return
     */
    @GetMapping(value = "/user/getUserInfoByIds")
    Result<List<SysUserForm>> getUserInfoByIds(@RequestParam @ApiParam(value = "ids，多个使用,隔开", required = true) String ids);

    /**
     * 用户账号重复性校验
     *
     * @param id       userId
     * @param username 用户名称
     * @return true or false
     */
    @GetMapping(value = "/user/checkUserNameExist")
    Result<String> checkUserNameExist(@ApiParam(value = "用户ID,非必传，不为空此条记录不会包括在检测范围") @RequestParam(value = "id", required = false) String id,
                                      @ApiParam(value = "用户名") @RequestParam(value = "username") String username);

    @ApiOperation(value = "根据行政区划名称获取区划", notes = "权限码(sys:region:regionbyname)")
    @GetMapping(value = "/sysRegion/getSysRegionByName")
    Result<SysRegionForm> getSysRegionByName(@ApiParam(value = "行政区划名称", required = true) @RequestParam(value = "regionName") String regionName);

    @GetMapping("/sysRegion/getSysRegionName/{id}")
    Result<String> getSysRegionName(@PathVariable(value = "id") String id);

    /**
     * 检查组织机构名称是否重复,返回值 Y:重复 N:不重复
     *
     * @param id      机构id
     * @param orgName 机构名称
     * @return true or false
     */
    @GetMapping("/sysOrganization/checkOrgNameIsExits")
    Result<String> checkOrgNameIsExits(@RequestParam(name = "id", required = false) @ApiParam(value = "组织机构ID，用于校验编辑时的名称是否重复") String id,
                                       @RequestParam(name = "orgName") @ApiParam(value = "机构名称") String orgName);

    @GetMapping("/fileManage/getOne/{id}")
    @ApiOperation(value = "获取某一个文件信息")
    @ResponseBody
    Result<SysFileManageVo> getOneFile(@PathVariable("id") String id);

    @GetMapping(value = "/dict/getDictListByType")
    Result<List<SysDict>> getDictListByType(@ApiParam(required = true, value = "字典类型值") @RequestParam(value = "typevalue") String typevalue);

    @GetMapping(value = "/dict/getDictNameByValueAndType")
    Result<List<SysDict>> getDictNameByValueAndType(@ApiParam(required = true, value = "字典类型名称") @RequestParam(value = "typename") String typename,
                                                    @ApiParam(value = "字典值") @RequestParam(value = "dictcode", required = false) String dictcode);

    @SofnLog(type = "登录", value = "web用户登录")
    @RequestMapping(value = "/user/login", consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "登录")
    Result<Auth> login(@Validated @RequestBody @ApiParam(name = "登录信息对象", value = "传入json格式", required = true) LoginVo loginVo);


    @ApiOperation(value = "获取行政区划树结构，获取整个行政区划树结构,从顶级100000开始，如果有条件将返回符合条件的上下级关系树",
            notes = "权限码(sys:regionLastCode:getSysRegionTree)")
    @GetMapping(value = "/sysRegion/getSysRegionTree")
    Result<SysRegionTreeVo> getSysRegionTree(@ApiParam(value = "行政区划名称") @RequestParam(required = false) String keyword,
                                             @ApiParam(value = "父节点ID") @RequestParam(required = false) String parentId,
                                             @ApiParam(value = "行政区划代码") @RequestParam(required = false) String regionCode,
                                             @ApiParam(value = "是否鉴权：Y鉴权（和当前的登录用户相关，例如是成都市的用户，就返回四川省-成都市-List<区>），使用鉴权后筛选无效")
                                             @RequestParam(value = "isAuth", required = false) String isAuth,
                                             @ApiParam(value = "行政区划级别(province省city市, 例如鉴权情况下双流县用户如果选择行政区域为province,则能查出整个双流上级到省,整个四川省的区域)") @RequestParam(required = false) String level,
                                             @ApiParam(value = "如果是第三方登录用户，如果用户对应的机构代理了多个行政机构，那么需要传入一个明确的机构以确定机构级别") @RequestParam(required = false) String orgId,
                                             @ApiParam(value = "系统APPID") @RequestParam(required = false) String appId,
                                             @ApiParam(value = "版本年份") @RequestParam(required = false) Integer versionYear,
                                             @RequestHeader(value = JWTToken.TOKEN, required = false) @ApiParam(required = false) String token);

    @ApiOperation(value = "根据appId获取子系统信息", notes = "权限码:(sys:subsystem:getSysSubsystemOneByAppId)")
    @GetMapping(value = "/sysSubsystem/getSysSubsystemOneByAppId")
    Result<SysSubsystemForm> getSysSubsystemOneByAppId(@ApiParam(name = "appId", value = "子系统appId", required = true) @RequestParam(value = "appId") String appId);

    @ApiOperation(value = "根据appId获取用户有的权限", notes = "权限码:(sys:user:getUserHavePermission)")
    @GetMapping("/user/getUserHavePermission/{appId}")
    Result<Map<String, Set<String>>> getUserHavePermission(@ApiParam(value = "子系统标识") @PathVariable("appId") String appId);

}
