package com.sofn.fyem.web;

import com.google.common.collect.Maps;
import com.sofn.common.exception.SofnException;
import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import com.sofn.common.utils.JsonUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.common.web.BaseController;
import com.sofn.fyem.sysapi.SysRegionApi;
import com.sofn.fyem.sysapi.bean.SysOrganization;
import com.sofn.fyem.vo.ReleaseStatisticsForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
@Api(value = "获取相关区划接口", tags = "获取相关区划接口")
@RestController
@RequestMapping("/fyemRegion")
public class RegionController extends BaseController {


    @Autowired
    private SysRegionApi sysRegionApi;

    @SofnLog("获取代理机构所代理的区划")
    @ApiOperation(value="获取代理机构所代理的区划")
    @GetMapping("/getRegionByLogin")
    public Result<List<String>> getRegionByLogin(
            HttpServletRequest request){

        List<String> regionStr = new ArrayList<>();

        //获取组织相关信息
        String loginUserOrganizationInfo = UserUtil.getLoginUserOrganizationInfo();
        SysOrganization sysOrganization = JsonUtils.json2obj(loginUserOrganizationInfo, SysOrganization.class);
        Result<List<SysOrganization>> fyem = sysRegionApi.getOrgListByAgentOrgId("fyem", sysOrganization.getId(),"");
        SysOrganization fyemOrg = fyem.getData().get(0);
        List<String>  regionCode = JsonUtils.json2List(fyemOrg.getRegioncode(), String.class);
        regionStr.addAll(regionCode);

        return Result.ok(regionStr);
    }
}
