package com.sofn.fdpi.web;

import com.google.common.collect.Maps;
import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.fdpi.service.TbUsersService;
import com.sofn.fdpi.sysapi.bean.SysUser;
import com.sofn.fdpi.util.SysOwnOrgUtil;
import com.sofn.fdpi.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-01-02 9:45
 */
@Slf4j
@Api(value = "APP_企业用户相关接口",tags = "APP_企业用户相关接口")
@RestController
@RequestMapping("app/user")
public class TbUsersAppController {
    @Autowired
    private TbUsersService tbUsersService;

    @SofnLog("修改当前用户密码")
    @ApiOperation(value = "修改当前用户密码")
    @GetMapping(value = "/updatePassWord")
    public Result updatePassWord(@ApiParam("旧密码") @RequestParam(value = "oldPassWord") String oldPassWord,
                                 @ApiParam("新密码") @RequestParam(value = "newPassWord") String newPassWord){
        UpdatePasswordVo vo =new UpdatePasswordVo();
        vo.setId(UserUtil.getLoginUserId());
        vo.setNewPassword(newPassWord);
        vo.setOldPassword(oldPassWord);
        String s = tbUsersService.changePassWordByUserId(vo);
        return  Result.ok(s);
    }

    @SofnLog("查询个人信息")
    @ApiOperation(value = "获取当前用户的个人信息")
    @GetMapping(value = "/getUserInfo")
    public Result<SysUser> getLogininfo(){
        return    tbUsersService.getLoginUserInfo();
    }
    /*已测*/
    @SofnLog("查询当前直属下的企业信息")
    @ApiOperation(value = "查询当前直属下的企业信息")
    @GetMapping(value = "/getCompanyUserInfo")
    public Result<CompanyUserInfo> getCompanyUserInfo(@ApiParam(value = "公司名字",required = false) @RequestParam(value = "compName",required = false)String compName ,
                                                      @ApiParam(value = "账号状态",required = false) @RequestParam(value = "userStatus",required = false)String userStatus,
                                                      @RequestParam(value = "pageNo") Integer pageNo,
                                                      @RequestParam(value = "pageSize") Integer pageSize){
        Map<String,Object> map= Maps.newHashMap();
        map.put("compName",compName);
        map.put("userStatus",userStatus);
        PageUtils companyUserInfo = tbUsersService.getCompanyUserInfo(map, pageNo, pageSize);
        return    Result.ok(companyUserInfo);
    }

    /*已测*/

    @SofnLog("查询当前直属下的企业信息")
    @ApiOperation(value = "查询当前直属下的企业信息")
    @GetMapping(value = "/getCompanyUserInfoByid")
    public Result<UserCompanyInfoVo> getCompanyUserInfoByid(@ApiParam(value = "用户id",required = true)
                                                            @RequestParam("id")String id ){
        UserCompanyInfoVo userCompanyInfo = tbUsersService.getUserCompanyInfo(id);
        return    Result.ok(userCompanyInfo);
    }
    @ApiOperation(value = "查询当前机构的级别")
    @GetMapping(value = "/getLevel")
    public Result<String> getCompanyUserInfoByid(){
        Result<SysOrgAndRegionVo> sysOrgInfoForApproveAndQuery = SysOwnOrgUtil.getSysOrgInfoForApproveAndQuery();
        String sysOrgLevelId = sysOrgInfoForApproveAndQuery.getData().getSysOrgLevelId();
         return Result.ok(sysOrgLevelId);
    }
    @ApiOperation(value = "获取机构信息")
    @GetMapping(value = "/getL")
    public Result<SysRegionInfoVo> getCompan(){

        return Result.ok(tbUsersService.get());
    }


}
