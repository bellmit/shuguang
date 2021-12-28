package com.sofn.sys.web;

import com.google.common.collect.Maps;
import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import com.sofn.common.web.BaseController;
import com.sofn.sys.model.SysOrg;
import com.sofn.sys.service.SysOrgAgentService;
import com.sofn.sys.service.SysOrgService;
import com.sofn.sys.vo.OrgAndProOrgInfoVo;
import com.sofn.sys.vo.SysOrgAgentForm;
import com.sofn.sys.vo.SysOrganizationForm;
import com.sofn.sys.vo.SysRegionInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description: 组织机构代理
 * @Author: quzhijie
 * @Date: 2020/1/3 14:08
 **/
@RestController
@Api(tags = "组织机构代理接口-NEW")
@RequestMapping("/sysOrgAgent")
public class SysOrgAgentController extends BaseController {

    @Autowired
    private SysOrgAgentService sysOrgAgentService;

    @Autowired
    private SysOrgService orgService;


    @ApiOperation(value = "分配机构代理",notes = "sys:sysOrgAgent:allotOrgAgent")
    @PostMapping("/allotOrgAgent")
    @SofnLog(value = "分配机构代理", type = "修改")
    public Result<String> allotOrgAgent(@ApiParam(value = "代理机构分配对象") @Validated @RequestBody SysOrgAgentForm sysOrgAgentForm,
                                        BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }

        sysOrgAgentService.allotOrgAgent(sysOrgAgentForm);
        return Result.ok();
    }

    @ApiOperation(value = "根据行政机构ID查询系统下分配的代理机构",notes = "sys:sysOrgAgent:getOrgAgentByOrgId")
    @GetMapping("/getOrgAgentByOrgId")
    public Result<List<SysOrg>> getOrgAgentByOrgId(@RequestParam @ApiParam(name = "subsystemId", value = "系统ID",required = true) String subsystemId,
                                                   @RequestParam @ApiParam(name = "orgId", value = "行政机构ID",required = true) String orgId,
                                                   @ApiParam(name = "agentCode", value = "代理机构标识") @RequestParam(required = false) String agentCode) {
        List<SysOrg> list = sysOrgAgentService.selectOrgAgentByOrgId(subsystemId, orgId, agentCode);
        return Result.ok(list);
    }

    @ApiOperation(value = "根据代理机构ID查询代理的行政机构",notes = "sys:sysOrgAgent:getOrgListByAgentOrgId")
    @GetMapping("/getOrgListByAgentOrgId")
    public Result<List<SysOrg>> getOrgListByAgentOrgId(@RequestParam @ApiParam(name = "subsystemId", value = "系统ID",required = true) String subsystemId,
                                                       @RequestParam @ApiParam(name = "agentOrgId", value = "代理机构ID",required = true) String agentOrgId,
                                                       @ApiParam(name = "agentCode", value = "代理机构标识") @RequestParam(required = false) String agentCode) {
        List<SysOrg> list = sysOrgAgentService.selectOrgListByAgentOrgId(subsystemId, agentOrgId, agentCode);
        return Result.ok(list);
    }

    @ApiOperation(value = "按条件查询代理机构行政机构信息",notes = "sys:sysOrgAgent:getOrgAndProOrgInfo")
    @GetMapping("/getOrgAndProOrgInfo")
    public Result<List<OrgAndProOrgInfoVo>> getOrgAndProOrgInfo(@ApiParam(name = "organizationLevel",defaultValue = "province", value = "机构级别,ministry:部级，province：省级，city：市级，county：县级，country：乡级")
                                                                @RequestParam(required = false,defaultValue = "province") String orgLevel,
                                                                @ApiParam(name = "appId", value = "子系统ID或者AppId",required = true) @RequestParam String appId,
                                                                @RequestParam(required = false) @ApiParam(name = "proxyId",value = "代理机构ID") String proxyId,
                                                                @RequestParam(required = false) @ApiParam(name = "orgId",value = "行政机构ID")  String orgId,
                                                                @RequestParam(required = false) @ApiParam(name = "regionId",value = "区划ID")   String regionId,
                                                                @RequestParam(required = false) @ApiParam(name = "agentCode",value = "代理标识")  String agentCode) {
        Map<String,String> params = Maps.newHashMap();
        if(StringUtils.isBlank(orgLevel)){
            params.put("orgLevel","province");
        }else{
            params.put("orgLevel",orgLevel);
        }
        params.put("appId",appId);
        params.put("proxyId",proxyId);
        params.put("orgId",orgId);
        params.put("regionId",regionId);
        params.put("agentCode",agentCode);
        List<OrgAndProOrgInfoVo> orgAndProOrgInfo = sysOrgAgentService.getOrgAndProOrgInfo(params);
        return Result.ok(orgAndProOrgInfo);
    }

    @ApiOperation(value = "获取行政机构的代理关系",notes = "sys:sysOrgAgent:getOrgProxyRelationship")
    @GetMapping("/getOrgProxyRelationship")
    public Result<List<SysOrgAgentForm>> getOrgProxyRelationship(@ApiParam(name="orgId",required = true) @RequestParam String orgId){
        List<SysOrgAgentForm> orgProxyRelationship = sysOrgAgentService.getOrgProxyRelationship(orgId);
        return Result.ok(orgProxyRelationship);
    }

    @ApiOperation(value = "根据代理机构ID和子系统ID获取所有的授权码",notes = "sys:sysOrgAgent:getAllAgentCodeBySubsystemIdAndProxyOrgId")
    @GetMapping("/getAllAgentCodeBySubsystemIdAndProxyOrgId")
    public Result<List<String>> getAllAgentCodeBySubsystemIdAndProxyOrgId(@ApiParam(name="subsystemId",value = "子系统ID",required = true) @RequestParam String subsystemId,
                                                                          @ApiParam(name="proxyOrgId",value = "代理机构ID",required = true) @RequestParam String proxyOrgId){
        List<String> allAgentCodeBySubsystemIdAndProxyOrgId = sysOrgAgentService.getAllAgentCodeBySubsystemIdAndProxyOrgId(subsystemId, proxyOrgId);
        return Result.ok(allAgentCodeBySubsystemIdAndProxyOrgId);
    }

    @ApiOperation(value = "根据代理机构ID和子系统ID删除代理关系",notes = "sys:sysOrgAgent:delProxyRelationshipByProxyIdAndSubsystemId")
    @GetMapping("/delProxyRelationshipByProxyIdAndSubsystemId")
    @SofnLog(value = "根据代理机构ID和子系统ID删除代理关系", type = "删除")
    public Result<String> delProxyRelationshipByProxyIdAndSubsystemId(@ApiParam(name="proxyOrgId",value = "代理机构ID",required = true) @RequestParam String proxyOrgId,
                                                                      @ApiParam(name="subsystemId",value = "子系统ID",required = true) @RequestParam String subsystemId,
                                                                      @ApiParam(name="orgId",value = "要删除的行政机构ID，删除后只有行政机构再次授权才能被恢复",required = true) @RequestParam String orgId){
        sysOrgAgentService.delProxyRelationshipByProxyIdAndSubsystemId(proxyOrgId, subsystemId, orgId);
        return Result.ok("删除成功");
    }

    @ApiOperation(value = "根据系统APPID获取有代理机构的行政机构的列表",notes = "sys:sysOrgAgent:getOrgListHasAgent")
    @GetMapping("/getOrgListHasAgent")
    public Result<List<SysOrganizationForm>> getOrgListHasAgent(@ApiParam(name="appId",required = true) @RequestParam String appId){
      List<SysOrg> list = sysOrgAgentService.getOrgListHasAgent(appId);
      List<SysOrganizationForm> formList = new ArrayList<>();
      for (SysOrg so : list) {
        if (so == null) {
          continue;
        }

        SysRegionInfoVo regionInfoVo = orgService.getSysRegionInfoByOrgId(so.getId());
        SysOrganizationForm form = new SysOrganizationForm();
        BeanUtils.copyProperties(so, form);
        form.setRegionInfoVo(regionInfoVo);

        formList.add(form);
      }
      return Result.ok(formList);
    }


    @ApiOperation(value = "根据代理机构ID获取当前机构已经维护了哪些系统的关系",notes = "sys:sysOrgAgent:getSubsystemByProxyOrgId")
    @GetMapping("/getSubsystemByProxyOrgId")
    public Result<List<String>> getSubsystemByProxyOrgId(@ApiParam(name="proxyOrgId",required = true) @RequestParam String proxyOrgId){
        List<String> subsystemByProxyOrgId = sysOrgAgentService.getSubsystemByProxyOrgId(proxyOrgId);
        return Result.ok(subsystemByProxyOrgId);
    }

}
