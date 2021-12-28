package com.sofn.fyem.web;

import com.sofn.common.exception.SofnException;
import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import com.sofn.common.model.User;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.common.web.BaseController;
import com.sofn.fyem.enums.ReleaseLevelEnum;
import com.sofn.fyem.service.BasicProliferationReleaseService;
import com.sofn.fyem.sysapi.SysRegionApi;
import com.sofn.fyem.util.FyemAreaUtil;
import com.sofn.fyem.vo.BasicProliferationReleaseForm;
import com.sofn.fyem.vo.BasicProliferationReleaseVO;
import com.sofn.fyem.vo.FyemArea;
import com.sofn.fyem.vo.ReleaseLevelDropDownVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 水生生物增殖放流基础数据
 * @Author: DJH
 * @Date: 2020.4.26 10.06
 */
@Slf4j
@Api(value = "水生生物增殖放流基础数据", tags = "水生生物增殖放流基础数据模块接口")
@RestController
@RequestMapping(value = "/basicProliferationRelease")
public class BasicProliferationReleaseController extends BaseController {

    @Autowired
    private BasicProliferationReleaseService basicProliferationReleaseService;
    @Autowired
    private SysRegionApi sysRegionApi;

    @SofnLog("水生生物增殖放流基础数据展示")
    @ApiOperation(value = "水生生物增殖放流基础数据展示")
//    @RequiresPermissions(value = "")
    @GetMapping("/listBPRByBelongYear")
    public Result<List<BasicProliferationReleaseVO>>  listBPRByBelongYear(@RequestParam(value = "belongYear", required = false, defaultValue = "0") @ApiParam(name = "belongYear", value = "所属年度,（非必传）", required = false) String belongYear,
                                                                          HttpServletRequest request){
        //根据token获取登录用户Id
        String loginUserId = UserUtil.getLoginUserId(request.getHeader("Authorization"));
        if(StringUtils.isBlank(loginUserId)){
            throw new SofnException("当前登录用户异常");
        }
        Map<String, Object> params = new HashMap();
        params.put("belongYear", belongYear);
        //params.put("createUserId", loginUserId);
        User loginUser = UserUtil.getLoginUser();
        if(loginUser == null || loginUser.getOrganizationId().isEmpty()){
            throw new SofnException("获取信息失败，用户信息异常！组织id为空");
        }
        params.put("organizationId", loginUser.getOrganizationId());
        List<BasicProliferationReleaseVO> list = basicProliferationReleaseService.listBPRByBelongYear(params);
        return Result.ok(list);
    }

    @SofnLog("水生生物增殖放流基础数据展示（分页）")
    @ApiOperation(value = "水生生物增殖放流基础数据展示（分页）")
//    @RequiresPermissions(value = "")
    @GetMapping("/getBasicProliferationReleaseListByPage")
    public Result<PageUtils<BasicProliferationReleaseVO>>  getBasicProliferationReleaseListByPage(@RequestParam(value = "belongYear", required = false, defaultValue = "0") @ApiParam(name = "belongYear", value = "所属年度,（非必传）", required = false) String belongYear,
                                                                                             @RequestParam(value = "pageNo", defaultValue = "1") @ApiParam(name = "pageNo", value = "起始位置,（必传）", required = true) int pageNo,
                                                                                             @RequestParam(value = "pageSize", defaultValue = "10") @ApiParam(name = "pageSize", value = "分页大小,（必传）", required = true) int pageSize,
                                                                                             HttpServletRequest request){
        //根据token获取登录用户Id
        String loginUserId = UserUtil.getLoginUserId(request.getHeader("Authorization"));
        if(StringUtils.isBlank(loginUserId)){
            throw new SofnException("当前登录用户异常");
        }
        Map<String, Object> params = new HashMap();
        params.put("belongYear", belongYear);
        //params.put("createUserId", loginUserId);
        User loginUser = UserUtil.getLoginUser();
        if(loginUser == null || loginUser.getOrganizationId().isEmpty()){
            throw new SofnException("获取信息失败，用户信息异常！组织id为空");
        }
        params.put("organizationId", loginUser.getOrganizationId());
        PageUtils<BasicProliferationReleaseVO> list = basicProliferationReleaseService.getBasicProliferationReleaseListByPage(params, pageNo, pageSize);
        return Result.ok(list);
    }

    @SofnLog("水生生物增殖放流基础数据获取")
    @ApiOperation(value = "水生生物增殖放流基础数据获取")
//    @RequiresPermissions(value = "")
    @GetMapping("/getBPRById")
    public Result<BasicProliferationReleaseForm> getBPRById(@RequestParam(value = "id", required = true, defaultValue = "0") @ApiParam(name = "id", value = "水生生物增殖放流基础数据id,（必传）", required = true) String id){
        BasicProliferationReleaseForm form = basicProliferationReleaseService.getBPRById(id);
        return Result.ok(form);
    }

    @SofnLog("水生生物增殖放流基础数据新增")
    @ApiOperation(value = "水生生物增殖放流基础数据新增")
//    @RequiresPermissions(value = "")
    @PostMapping("/insert")
    public Result insert(@Validated @RequestBody @ApiParam(name = "form", value = "水生生物增殖放流表单json对象,（必传）,所有字段都为必填项", required = true) BasicProliferationReleaseForm form,
                         HttpServletRequest request, BindingResult bind){
        if (bind.hasErrors()){
            return Result.error(getErrMsg(bind));
        }
        // 判断当前登录用户是否异常
        String token = request.getHeader("Authorization");
        String loginUserId = UserUtil.getLoginUserId(token);
        if(StringUtils.isBlank(loginUserId)){
            throw new SofnException("当前登录用户异常");
        }
        String result = basicProliferationReleaseService.insert(form, token);
        if ("1".equals(result)){
            return Result.ok("新增成功！");
        }else {
            return Result.error("新增失败:"+result);
        }
    }

    @SofnLog("水生生物增殖放流基础数据修改")
    @ApiOperation(value = "水生生物增殖放流基础数据修改")
//    @RequiresPermissions(value = "")
    @PutMapping("/update")
    public Result update(@Validated @RequestBody @ApiParam(name = "form", value = "水生生物增殖放流表单json对象,（必传）", required = true) BasicProliferationReleaseForm form,
                         HttpServletRequest request, BindingResult bind){
        if (bind.hasErrors()){
            return Result.error(getErrMsg(bind));
        }
        // 判断当前登录用户是否异常
        String token = request.getHeader("Authorization");
        String loginUserId = UserUtil.getLoginUserId(token);
        if(StringUtils.isBlank(loginUserId)){
            throw new SofnException("当前登录用户异常");
        }
        String result = basicProliferationReleaseService.update(form, token);
        if ("1".equals(result)){
            return Result.ok("修改成功！");
        }else {
            return Result.error("修改失败:"+result);
        }
    }

    @SofnLog("水生生物增殖放流基础数据删除")
    @ApiOperation(value = "水生生物增殖放流基础数据删除")
//    @RequiresPermissions(value = "")
    @DeleteMapping("/delete")
    public Result delete(@RequestParam(value = "id", required = true, defaultValue = "")@ApiParam(name="id",value = "主键id,（必传）",required = true) String id){
        String result = basicProliferationReleaseService.delete(id);
        if ("1".equals(result)){
            return Result.ok("删除成功！");
        }else {
            return Result.error("删除失败:"+result);
        }
    }

    @ApiOperation(value = "获取放流活动级别下拉列表")
    @GetMapping("/listForLevels")
    public Result<List<ReleaseLevelDropDownVo>> listForLevels() {
        return Result.ok(ReleaseLevelEnum.listForLevels());
    }

    /**
     * 获取当前登录用户所在地区信息
     * @param params
     * @return
     */
    private Map<String,Object> getLoginUserArea(Map<String,Object> params){
        //获取当前登录用户所属省市县id
        List<FyemArea> fyemAreaList = FyemAreaUtil.getUserAreaIdByLogUser(sysRegionApi);
        FyemArea area = fyemAreaList.size() != 0 ? fyemAreaList.get(0): new FyemArea() ;
        params.put("provinceId",area.getProvinceId());
        params.put("cityId",area.getCityId());
        params.put("countyId",area.getCountyId());
        return params;
    }
}
