package com.sofn.fyem.web;

import com.google.common.collect.Maps;
import com.sofn.common.exception.SofnException;
import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import com.sofn.common.utils.JsonUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.common.web.BaseController;
import com.sofn.fyem.excelmodel.BasicProliferationReleaseExcel;
import com.sofn.fyem.excelmodel.ReleaseSiteAndChoiceTimeExcel;
import com.sofn.fyem.model.BasicProliferationRelease;
import com.sofn.fyem.service.BasicProliferationReleaseService;
import com.sofn.fyem.sysapi.SysRegionApi;
import com.sofn.fyem.sysapi.bean.SysOrganization;
import com.sofn.fyem.util.ExportUtil;
import com.sofn.fyem.vo.BasicProliferationReleaseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 放流地点/时间选择-接口
 * @author Administrator
 */
@Api(value = "放流地点/时间选择-接口", tags = "放流地点/时间选择-接口")
@RestController
@RequestMapping("/releaseSiteAndChoiceTime")
public class ReleaseSiteAndChoiceTimeController extends BaseController {

    /**
     * 水生物放流Service
     */
    @Autowired
    private BasicProliferationReleaseService basicProliferationReleaseService;

    @Autowired
    private SysRegionApi sysRegionApi;
    @SofnLog("获取放流地点/时间选择-信息(不分页)")
    @ApiOperation(value="获取放流地点/时间选择-信息(不分页)")
    @GetMapping("/releaseSiteAndChoiceTimeList")
    public Result<List<BasicProliferationReleaseVO>> releaseSiteAndChoiceTimeList(
            @ApiParam(name = "belongYear",value = "belongYear(必传)")@RequestParam(value = "belongYear")String belongYear,
            @ApiParam(name = "provinceId",value = "省()")@RequestParam(value = "provinceId",required = false)String provinceId,
            @ApiParam(name = "cityId",value = "市()")@RequestParam(value = "cityId",required = false)String cityId,
            @ApiParam(name = "countyId",value = "区县()")@RequestParam(value = "countyId",required = false)String countyId,
            HttpServletRequest request){
        //根据token获取登录用户Id
        String userId = UserUtil.getLoginUserId(request.getHeader("Authorization"));
        if(StringUtils.isBlank(userId)){
            throw new SofnException("当前登录用户异常");
        }
        Map<String, Object> params = Maps.newHashMap();
        List<String> roleColde = UserUtil.getLoginUserRoleCodeList();
        params.put("belongYear",belongYear);
        params.put("provinceId",provinceId);
        params.put("countyId",countyId);
        params.put("cityId",cityId);

        SysOrganization org = JsonUtils.json2obj(UserUtil.getLoginUserOrganizationInfo(), SysOrganization.class);
        Result<List<SysOrganization>> fyem = sysRegionApi.getOrgListByAgentOrgId("fyem", org.getId(), "");
        List<SysOrganization> fyemOrg = fyem.getData();
        SysOrganization orgDaiLi = new SysOrganization();
        if(fyemOrg.size()>0){
            orgDaiLi = fyemOrg.get(0);
        }
        //填报单位
        if(roleColde.contains("fyem_county") || roleColde.contains("fyem_city_add") || roleColde.contains("fyem_prov_add")  ){
            params.put("organizationId",org.getId());
        }
        if(roleColde.contains("fyem_city")){
            //判断当前机构是否为行政机构
            if("Y".equals(org.getThirdOrg())){
                //行政机构
                params.put("cityId",org.getRegionLastCode());
            }else{
                //非行政机构
                params.put("cityId",orgDaiLi.getRegionLastCode());
            }
            params.put("countyId",countyId);
        }
        if(roleColde.contains("fyem_prov")){
            //判断当前机构是否为行政机构
            if("Y".equals(org.getThirdOrg())){
                //行政机构
                params.put("provinceId",org.getRegionLastCode());
            }else{
                //非行政机构
                params.put("provinceId",orgDaiLi.getRegionLastCode());
            }
            params.put("cityId",cityId);
            params.put("countyId",countyId);
        }
        List<BasicProliferationReleaseVO> basicProliferationReleaseList = basicProliferationReleaseService.getBasicProliferationReleaseListByQuery(params);

        return Result.ok(basicProliferationReleaseList);
    }

    @SofnLog("放流地点/时间选择-信息导出")
    @ApiOperation(value="放流地点/时间选择-信息导出")
    @GetMapping(value = "/exportReleaseSiteAndChoiceTime", produces = "application/octet-stream")
    public void exportPhysiologicalParameters(
            @ApiParam(name = "belongYear",value = "belongYear(必传)")@RequestParam(value = "belongYear")Integer belongYear,
            @ApiParam(name = "provinceId",value = "省()")@RequestParam(value = "provinceId",required = false)Integer provinceId,
            @ApiParam(name = "cityId",value = "市()")@RequestParam(value = "cityId",required = false)Integer cityId,
            @ApiParam(name = "countyId",value = "区县()")@RequestParam(value = "countyId",required = false)Integer countyId,
            HttpServletRequest request, HttpServletResponse response){

        //根据token获取登录用户Id
        String userId = UserUtil.getLoginUserId(request.getHeader("Authorization"));
        Map<String, Object> params = Maps.newHashMap();

        List<String> roleColde = UserUtil.getLoginUserRoleCodeList();
        params.put("belongYear",belongYear);
        params.put("provinceId",provinceId);
        params.put("countyId",countyId);
        params.put("cityId",cityId);
        SysOrganization org = JsonUtils.json2obj(UserUtil.getLoginUserOrganizationInfo(), SysOrganization.class);
        Result<List<SysOrganization>> fyem = sysRegionApi.getOrgListByAgentOrgId("fyem", org.getId(), "");
        List<SysOrganization> fyemOrg = fyem.getData();
        SysOrganization orgDaiLi = new SysOrganization();
        if(fyemOrg.size()>0){
            orgDaiLi = fyemOrg.get(0);
        }
        //填报单位
        if(roleColde.contains("fyem_county") || roleColde.contains("fyem_city_add") || roleColde.contains("fyem_prov_add")  ){
            params.put("organizationId",org.getId());
        }
        if(roleColde.contains("fyem_city")){
            //判断当前机构是否为行政机构
            if("Y".equals(org.getThirdOrg())){
                //行政机构
                params.put("cityId",org.getRegionLastCode());
            }else{
                //非行政机构
                params.put("cityId",orgDaiLi.getRegionLastCode());
            }
            params.put("countyId",countyId);
        }
        if(roleColde.contains("fyem_prov")){
            //判断当前机构是否为行政机构
            if("Y".equals(org.getThirdOrg())){
                //行政机构
                params.put("provinceId",org.getRegionLastCode());
            }else{
                //非行政机构
                params.put("provinceId",orgDaiLi.getRegionLastCode());
            }
            params.put("cityId",cityId);
            params.put("countyId",countyId);
        }
        List<BasicProliferationReleaseExcel> basicProliferationReleaseExcel = basicProliferationReleaseService.getBasicProliferationReleaseExcel(params);
        List<ReleaseSiteAndChoiceTimeExcel> releaseSiteAndChoiceTimeExcels = new ArrayList<>();
        basicProliferationReleaseExcel.forEach(x->{
            ReleaseSiteAndChoiceTimeExcel releaseSiteAndChoiceTimeExcel = new ReleaseSiteAndChoiceTimeExcel();
            BeanUtils.copyProperties(x,releaseSiteAndChoiceTimeExcel);
            releaseSiteAndChoiceTimeExcels.add(releaseSiteAndChoiceTimeExcel);
        });
        ExportUtil.createExcel(ReleaseSiteAndChoiceTimeExcel.class,releaseSiteAndChoiceTimeExcels,response,
                "放流地点、时间选择-信息导出.xlsx");
    }

}
