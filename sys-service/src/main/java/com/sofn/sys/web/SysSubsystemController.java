package com.sofn.sys.web;

import com.google.common.collect.Maps;
import com.sofn.common.exception.SofnException;
import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.SqlEscapeUtils;
import com.sofn.common.utils.shiro.JWTToken;
import com.sofn.common.web.BaseController;
import com.sofn.sys.enums.SysManageEnum;
import com.sofn.sys.model.SysSubsystem;
import com.sofn.sys.service.SysSubsystemService;
import com.sofn.sys.service.permission.PermissionSubSystemService;
import com.sofn.sys.util.UserHaveAppIdUtil;
import com.sofn.sys.vo.SysSubsystemForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 子系统接口
 * Created by sofn 2019/5/29 16:27
 * @author sofn
 */
@Api(tags = "子系统接口")
@RestController
@RequestMapping("/sysSubsystem")
@Slf4j
public class SysSubsystemController extends BaseController {

    @Autowired
    private SysSubsystemService sysSubsystemService;

    @Autowired
    private PermissionSubSystemService permissionSubSystemService;

    @SofnLog("获取子系统信息列表")
    @ApiOperation(value = "按条件分页获取子系统信息", notes="权限码(sys:subsystem:index)")
    @GetMapping("/getSysSubsystemByPage")
    public Result<PageUtils<SysSubsystemForm>> getSysSubsystemByPage(@RequestParam(required = false) @ApiParam(value = "子系统名称") String subsystemName,
                                        @RequestParam(value="pageNo") @ApiParam(name = "pageNo",value = "分页序号",required = true) Integer pageNo,
                                        @RequestParam(value = "pageSize") @ApiParam(name = "pageSize",value = "每页数量",required = true) Integer pageSize) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("subsystemName", SqlEscapeUtils.escape(subsystemName, SqlEscapeUtils.DB.ORACLE));
        params.put("delFlag", SysManageEnum.DEL_FLAG_N.getCode());
        // 获取分页内容
        PageUtils<SysSubsystemForm> sysSubsystemByContion = sysSubsystemService.getSysSubsystemByContion(params,pageNo,pageSize);
        return Result.ok(sysSubsystemByContion);
    }
    @SofnLog(value = "添加子系统信息",type = "新增")
    @ApiOperation(value = "添加子系统信息" , notes="权限码:(sys:subsystem:create)")
    @PostMapping(value = "/saveSysSubsystem")
    public Result<String> saveSysSubsystem(@Validated @RequestBody @ApiParam(name = "子系统对象", value = "传入json格式", required = true)
                                                             SysSubsystemForm sysSubsystemForm, BindingResult result) {
        // 校验参数
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        // 转化为MODEL对象
        SysSubsystem sysSubsystem =  SysSubsystemForm.getSysSubsystem(sysSubsystemForm);
        synchronized (this){
            sysSubsystemService.addSysSubsystem(sysSubsystem);
        }
        return Result.ok();
    }
    @SofnLog(value = "修改子系统信息",type = "修改")
    @ApiOperation(value = "修改子系统信息" , notes="权限码:(sys:subsystem:update)")
    @PostMapping(value = "/updateSysSubsystem")
    public Result<String> updateSysSubsystem(@Validated @RequestBody  @ApiParam(name = "子系统对象", value = "传入json格式", required = true)
                                                 SysSubsystemForm sysSubsystemForm, BindingResult result) {
        // 校验参数
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        // 转化为MODEL对象
        SysSubsystem sysSubsystem =  SysSubsystemForm.getSysSubsystem(sysSubsystemForm);
        sysSubsystemService.updateSysSubsystem(sysSubsystem);
        return Result.ok();
    }
    @SofnLog(value = "删除子系统信息",type = "删除")
    @ApiOperation(value = "删除子系统信息" , notes="权限码:(sys:subsystem:delete)")
    @PostMapping(value = "/deleteSysSubsystem")
    public Result<String> deleteSysSubsystem(@ApiParam(value = "子系统ID",required = true) @RequestParam String id){
        if(id == null  || "".equals(id)){
            return Result.error("ID不能为空");
        }
        sysSubsystemService.deleteSysSubsystem(id);
        return Result.ok();
    }

    @ApiOperation(value = "获取子系统树结构", notes="权限码:(sys:subsystem:getSysSubsystemTree)")
    @GetMapping(value = "/getSysSubsystemTree")
    public Result<SysSubsystemForm> getSysSubsystemTree(@RequestParam(required = false) @ApiParam(value = "子系统名称") String subsystemName,
                                                        @RequestHeader(value = JWTToken.TOKEN,required = false) String token){
        try {
            if(!StringUtils.isBlank(token)){
                // 1. 根据当前登录用户查询出有的子系统
                List<String> userAppIds = permissionSubSystemService.getUserAppIds(token);
                UserHaveAppIdUtil.setValue(userAppIds);
            }
            // 2. 加载树
            SysSubsystemForm subsystemList= sysSubsystemService.getSysSubsystemTree(subsystemName);
            return Result.ok(subsystemList);
        }catch (Exception e){
            throw new SofnException(e.getMessage());
        }finally {
            UserHaveAppIdUtil.remove();
        }
    }
    @ApiOperation(value = "获取当前用户子系统树结构", notes="权限码:(sys:subsystem:getSysSubsystemTreeByUser)")
    @GetMapping(value = "/getSysSubsystemTreeByUser")
    public Result<SysSubsystemForm> getSysSubsystemTreeByUser(){
        SysSubsystemForm treeSysSubsystemList= sysSubsystemService.getSysSubsystemTreeByUser();
        return Result.ok(treeSysSubsystemList);
    }

    @SofnLog("获取子系统信息")
    @ApiOperation(value = "根据ID获取一个", notes="权限码:(sys:subsystem:getSysSubsystemOne)")
    @GetMapping(value = "/getSysSubsystemOne")
    public Result<SysSubsystemForm> getSysSubsystemOne(@ApiParam(name = "id",value = "子系统ID",required = true) @RequestParam(value = "id") String id){
        SysSubsystem sysSubsystem =  sysSubsystemService.getById(id);
        SysSubsystemForm sysSubsystemForm=new SysSubsystemForm(sysSubsystem);
        String parentName;
        if(sysSubsystem.getParentId().equals(SysManageEnum.SUBSYSTEM_ROOT.getCode())){
            parentName=SysManageEnum.SUBSYSTEM_ROOT.getDescription();
        }else{
            parentName =sysSubsystemService.getById(sysSubsystem.getParentId()).getSubsystemName();
        }

        sysSubsystemForm.setParentName(parentName);
        return Result.ok(sysSubsystemForm);
    }

    @SofnLog("根据APPID获取子系统信息")
    @ApiOperation(value = "根据appId获取子系统信息", notes="权限码:(sys:subsystem:getSysSubsystemOneByAppId)")
    @GetMapping(value = "/getSysSubsystemOneByAppId")
    public Result<SysSubsystemForm> getSysSubsystemOneByAppId(@ApiParam(name = "appId",value = "子系统appId",required = true) @RequestParam(value = "appId") String appId){
      SysSubsystem sysSubsystem =  sysSubsystemService.getSysSubsystemAppId(appId);
      SysSubsystemForm sysSubsystemForm=new SysSubsystemForm(sysSubsystem);
      String parentName;
      if(sysSubsystem.getParentId().equals(SysManageEnum.SUBSYSTEM_ROOT.getCode())){
        parentName=SysManageEnum.SUBSYSTEM_ROOT.getDescription();
      }else{
        parentName =sysSubsystemService.getById(sysSubsystem.getParentId()).getSubsystemName();
      }

      sysSubsystemForm.setParentName(parentName);
      return Result.ok(sysSubsystemForm);
    }

    @ApiOperation(value = "根据IDS批量删除子系统" , notes="权限码:(sys:subsystem:batchDelete)")
    @PostMapping("/batchDeleteSubsystem/{ids}")
    public Result<String> batchDeleteSubsystem(@ApiParam(value = "待删除IDS，ID用英文,号分隔") @PathVariable(value = "ids") String ids){
        if(StringUtils.isBlank(ids)){
            return Result.ok();
        }
        List<String> idList = IdUtil.getIdsByStr(ids);
        sysSubsystemService.batchDeleteSubsystem(idList);
        return Result.ok();
    }
    @ApiOperation(value = "根据ID获取当前ID下面的一层树结构", notes="权限码:(sys:subsystem:getSysSubsystemTreeById)")
    @GetMapping("/getSysSubsystemTreeById")
    public Result<List<SysSubsystemForm>> getSysSubsystemTreeById(@ApiParam(name = "id",value = "子系统ID",required = true) @RequestParam("id") String id){
        List<SysSubsystem> sysSubsystemTreeVos = sysSubsystemService.getSysSubsystemTreeById(id);
        List<SysSubsystemForm> sysSubsystemFormTreeVos=new ArrayList<>();
        for (SysSubsystem sysSubsystemTreeVo : sysSubsystemTreeVos) {
            //获取父菜单名
            SysSubsystemForm sysResourceForm = new SysSubsystemForm(sysSubsystemTreeVo);
            SysSubsystem sysResourceParent = sysSubsystemService.getById(sysSubsystemTreeVo.getParentId());
            //如果为一级菜单,父菜单名称为子系统名称
            if (sysSubsystemTreeVo.getParentId().equals(SysManageEnum.SUBSYSTEM_ROOT.getCode())) {
                sysResourceForm.setParentName(SysManageEnum.SUBSYSTEM_ROOT.getDescription());
            } else {
                sysResourceForm.setParentName(sysResourceParent.getSubsystemName());
            }
            sysSubsystemFormTreeVos.add(sysResourceForm);
        }
       return Result.ok(sysSubsystemFormTreeVos);
    }


    @GetMapping("/getSubsystemByType/{type}")
    @ApiOperation(value = "根据类型获取子系统。系统类型，1 平台 2 分系统 3 子系统", notes="权限码:(sys:subsystem:getSubsystemByType)")
    public Result<List<SysSubsystemForm>> getSubsystemByType(@PathVariable(value = "type") String type){
        List<SysSubsystemForm> subsystemByType = sysSubsystemService.getSubsystemByType(type);
        return Result.ok(subsystemByType);
    }




}
