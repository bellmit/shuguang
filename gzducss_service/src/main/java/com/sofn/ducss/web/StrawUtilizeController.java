package com.sofn.ducss.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sofn.ducss.exception.SofnException;
import com.sofn.ducss.log.SofnLog;
import com.sofn.ducss.model.SysOrganization;
import com.sofn.ducss.model.basemodel.Result;
import com.sofn.ducss.util.IdUtil;
import com.sofn.ducss.util.JsonUtils;
import com.sofn.ducss.util.PageUtils;
import com.sofn.ducss.util.UserUtil;
import com.sofn.ducss.enums.RegionLevel;
import com.sofn.ducss.model.Popup;
import com.sofn.ducss.model.StrawUtilize;
import com.sofn.ducss.model.StrawUtilizeDetail;
import com.sofn.ducss.service.PopupService;
import com.sofn.ducss.service.StrawUtilizeDetailService;
import com.sofn.ducss.service.StrawUtilizeService;
import com.sofn.ducss.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

@Slf4j
@Api(value = "规模化秸秆利用量填报", tags = "规模化秸秆利用量填报")
@RestController
@RequestMapping("/strawUtilize")
public class StrawUtilizeController {

    @Autowired
    private StrawUtilizeService strawUtilizeService;

    @Autowired
    private StrawUtilizeDetailService strawUtilizeDetailService;

    @Autowired
    private PopupService popupService;

    @SofnLog("填报查询")
    @ApiOperation(value = "填报查询")
    @PostMapping("/getStrawUtilize")
    public Result<PageUtils<StrawUtilize>> getStrawUtilize(QueryPageVo queryVo, HttpServletRequest request) {
        //根据token获取登录用户Id
        String userId = UserUtil.getLoginUserId(request.getHeader("Authorization"));
        if (StringUtils.isBlank(userId)) {
            throw new SofnException("当前登录用户异常");
        }
        String year = queryVo.getYear();
        if (StringUtils.isBlank(year)) {
            throw new SofnException("请选择年度");
        }
        String countyId = queryVo.getCountyId();
        Integer pageNo = queryVo.getPageNo();
        Integer pageSize = queryVo.getPageSize();
        if (StringUtils.isBlank(countyId)) {
            PageHelper.offsetPage(pageNo, pageSize);
            List<StrawUtilize> strawUtilize = new ArrayList<>();
            PageInfo<StrawUtilize> strawUtilizePageInfo = new PageInfo<>(strawUtilize);
            return Result.ok(PageUtils.getPageUtils(strawUtilizePageInfo));
        } else {
            PageUtils<StrawUtilize> strawUtilize = strawUtilizeService.getStrawUtilize(year, countyId, pageNo, pageSize);
            return Result.ok(strawUtilize);
        }

    }

    @SofnLog("查询规模化秸秆利用量数据")
    @ApiOperation(value = "查询规模化秸秆利用量数据")
    @GetMapping("/getStrawUtilizeList")
    public Result<PageUtils<StrawUtilize>> getStrawUtilizeList(StrawUtilizeQueryVo queryVo
            , HttpServletRequest request) {
        //根据token获取登录用户Id
        String userId = UserUtil.getLoginUserId(request.getHeader("Authorization"));
        if (StringUtils.isBlank(userId)) {
            throw new SofnException("当前登录用户异常");
        }

        String userAreaByLogUser = UserUtil.getLoginUserOrganizationInfo();
        if (org.springframework.util.StringUtils.isEmpty(userAreaByLogUser)) {
            throw new SofnException("未获取到登录用户所属机构信息!");
        }
        SysOrganization sysOrganization = JsonUtils.json2obj(userAreaByLogUser, SysOrganization.class);
        String regioncode = sysOrganization.getRegioncode();
        List<String> areaList = JsonUtils.json2List(regioncode, String.class);
        //判断当前用户机构级别是否是乡镇级
        if (!RegionLevel.COUNTY.getCode().equals(sysOrganization.getOrganizationLevel())) {
            throw new SofnException("当前用户不是乡镇级用户!");
        }
        String countyId = areaList.get(2);
        String year = queryVo.getYear();
        String mainName = queryVo.getMainName();
        String dateBegin = queryVo.getDateBegin();
        String dateEnd = queryVo.getDateEnd();
        Integer pageNo = queryVo.getPageNo();
        Integer pageSize = queryVo.getPageSize();
        PageUtils<StrawUtilize> strawUtilizePageUtils = strawUtilizeService.getStrawUtilizeByPage(pageNo, pageSize, year, mainName, countyId, dateBegin, dateEnd);

        return Result.ok(strawUtilizePageUtils);
    }

    @SofnLog("新增/编辑页面数据展示")
    @ApiOperation(value = "新增/编辑页面数据展示")
    @GetMapping("/getStrawUtilizeDetailList")
    public Result<StrawUtilizeDetailVo> getStrawUtilizeDetailList(HttpServletRequest request, String strawUtilizeId,
                                                                  @RequestParam(value = "mainName", required = false) @ApiParam(value = "市场主体名称", required = false) String mainName) {
        String userAreaByLogUser = UserUtil.getLoginUserOrganizationInfo();
        if (org.springframework.util.StringUtils.isEmpty(userAreaByLogUser)) {
            throw new SofnException("未获取到登录用户所属机构信息!");
        }
        SysOrganization sysOrganization = JsonUtils.json2obj(userAreaByLogUser, SysOrganization.class);
        String regioncode = sysOrganization.getRegioncode();
        List<String> areaList = JsonUtils.json2List(regioncode, String.class);
        //判断当前用户机构级别是否是乡镇级
        if (!RegionLevel.COUNTY.getCode().equals(sysOrganization.getOrganizationLevel())) {
            throw new SofnException("当前用户不是乡镇级用户!");
        }
        String countyId = areaList.get(2);
        String cityId = areaList.get(1);
        String provinceId = areaList.get(0);

        StrawUtilizeDetailVo strawUtilizeDetailVo = new StrawUtilizeDetailVo();
        if (null == strawUtilizeId) {
            strawUtilizeId = "";
        }
        if (!strawUtilizeId.equals("")) {
            StrawUtilize strawUtilize = strawUtilizeService.selectStrawUtilizeById(strawUtilizeId);
            strawUtilizeDetailVo.setStrawUtilizeId(strawUtilizeId);
            strawUtilizeDetailVo.setAddTime(strawUtilize.getCreateDate());
            strawUtilizeDetailVo.setYear(strawUtilize.getYear());
            strawUtilizeDetailVo.setDepartment(strawUtilize.getReportArea());
            strawUtilizeDetailVo.setMainNo(strawUtilize.getMainNo());
            strawUtilizeDetailVo.setMainName(strawUtilize.getMainName());
            strawUtilizeDetailVo.setCorporationName(strawUtilize.getCorporationName());
            strawUtilizeDetailVo.setCompanyPhone(strawUtilize.getCompanyPhone());
            strawUtilizeDetailVo.setMobilePhone(strawUtilize.getMobilePhone());
            strawUtilizeDetailVo.setAddress(strawUtilize.getAddress());
            // 区域信息省市县
            strawUtilizeDetailVo.setCountyId(countyId);
            strawUtilizeDetailVo.setCityId(cityId);
            strawUtilizeDetailVo.setProvinceId(provinceId);
            // 表格信息
            List<StrawUtilizeDetail> list = strawUtilizeDetailService.getStrawUtilizeDetail(strawUtilizeId);
            strawUtilizeDetailVo.setStrawUtilizeDetailList(list);
        } else {

            //判断是否弹出提示框
            Calendar date2 = Calendar.getInstance();
            String year = String.valueOf(date2.get(Calendar.YEAR));//获取当前自然年

            Popup popup = popupService.selectPopupByAreaIdAndYear(countyId, year);
            if (null == popup) {
                Popup popupNew = new Popup();
                popupNew.setId(IdUtil.getUUId());
                popupNew.setStrawUtilizePopup("N");
                popupNew.setAreaId(countyId);
                popupNew.setYear(year);
                popupService.save(popupNew);
                strawUtilizeDetailVo.setPopup("Y");
            } else {
                if (popup.getStrawUtilizePopup().equals("Y")) {
                    strawUtilizeDetailVo.setPopup("Y");
                    popup.setStrawUtilizePopup("N");
                    popupService.updateById(popup);
                } else {
                    strawUtilizeDetailVo.setPopup("N");
                }
            }

            Date date = new Date();
            GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
            gc.setTime(date);
            Integer lastYear = gc.get(1) - 1;
            strawUtilizeDetailVo.setYear(lastYear.toString());
            strawUtilizeDetailVo.setAddTime(new Date());
            strawUtilizeDetailVo.setDepartment("");
            strawUtilizeDetailVo.setStrawUtilizeId("");
            strawUtilizeDetailVo.setMainNo("");
            // 区域信息省市县
            strawUtilizeDetailVo.setCountyId(countyId);
            strawUtilizeDetailVo.setCityId(cityId);
            strawUtilizeDetailVo.setProvinceId(provinceId);
            // 表格信息
            List<StrawUtilizeDetail> list = strawUtilizeDetailService.getStrawUtilizeDetail(strawUtilizeId);
            strawUtilizeDetailVo.setStrawUtilizeDetailList(list);

            if (null == mainName) {
                mainName = "";
            }
            //获取最近一年的表格数据进行快速填报
            String strawUtilizeIdByYear = strawUtilizeService.selectStrawUtilizeDetailIdByYear(countyId, mainName);
            if (null != strawUtilizeIdByYear) {
                StrawUtilizeDetailVo strawUtilizeDetailVo2 = new StrawUtilizeDetailVo();
                StrawUtilize strawUtilize = strawUtilizeService.selectStrawUtilizeById(strawUtilizeIdByYear);
                strawUtilizeDetailVo2.setStrawUtilizeId(strawUtilizeIdByYear);
                strawUtilizeDetailVo2.setAddTime(strawUtilize.getCreateDate());
                strawUtilizeDetailVo2.setYear(strawUtilize.getYear());
                strawUtilizeDetailVo2.setDepartment(strawUtilize.getReportArea());
                strawUtilizeDetailVo2.setMainNo(strawUtilize.getMainNo());
                strawUtilizeDetailVo2.setMainName(strawUtilize.getMainName());
                strawUtilizeDetailVo2.setCorporationName(strawUtilize.getCorporationName());
                strawUtilizeDetailVo2.setCompanyPhone(strawUtilize.getCompanyPhone());
                strawUtilizeDetailVo2.setMobilePhone(strawUtilize.getMobilePhone());
                strawUtilizeDetailVo2.setAddress(strawUtilize.getAddress());
                // 区域信息省市县
                strawUtilizeDetailVo2.setCountyId(countyId);
                strawUtilizeDetailVo2.setCityId(cityId);
                strawUtilizeDetailVo2.setProvinceId(provinceId);
                // 表格信息
                List<StrawUtilizeDetail> list2 = strawUtilizeDetailService.getStrawUtilizeDetail(strawUtilizeIdByYear);

                //调整表格顺序
                List<StrawUtilizeDetail> list3 = new ArrayList<>();
                for (StrawUtilizeDetail strawUtilizeDetail : list) {
                    String name = strawUtilizeDetail.getStrawName();
                    for (StrawUtilizeDetail strawUtilizeDetail12 : list2) {
                        String name2 = strawUtilizeDetail12.getStrawName();
                        if (name.equals(name2)) {
                            list3.add(strawUtilizeDetail12);
                        }
                    }
                }

                strawUtilizeDetailVo2.setStrawUtilizeDetailList(list3);
                strawUtilizeDetailVo.setStrawUtilizeDetailVoByYear(strawUtilizeDetailVo2);
            }
        }
        return Result.ok(strawUtilizeDetailVo);
    }

    @SofnLog("填报查询详情获取")
    @ApiOperation(value = "填报查询详情获取")
    @GetMapping("/getStrawUtilizeDetailListPro")
    public Result<StrawUtilizeDetailVo> getStrawUtilizeDetailListPro(HttpServletRequest request, String strawUtilizeId) {
        if (StringUtils.isBlank(strawUtilizeId)) {
            throw new SofnException("查询参数不能为空");
        }

        StrawUtilizeDetailVo strawUtilizeDetailVo = new StrawUtilizeDetailVo();

        StrawUtilize strawUtilize = strawUtilizeService.selectStrawUtilizeById(strawUtilizeId);
        strawUtilizeDetailVo.setStrawUtilizeId(strawUtilizeId);
        strawUtilizeDetailVo.setAddTime(strawUtilize.getCreateDate());
        strawUtilizeDetailVo.setYear(strawUtilize.getYear());
        strawUtilizeDetailVo.setDepartment(strawUtilize.getReportArea());
        strawUtilizeDetailVo.setMainNo(strawUtilize.getMainNo());
        strawUtilizeDetailVo.setMainName(strawUtilize.getMainName());
        strawUtilizeDetailVo.setCorporationName(strawUtilize.getCorporationName());
        strawUtilizeDetailVo.setCompanyPhone(strawUtilize.getCompanyPhone());
        strawUtilizeDetailVo.setMobilePhone(strawUtilize.getMobilePhone());
        strawUtilizeDetailVo.setAddress(strawUtilize.getAddress());
        // 区域信息省市县
        strawUtilizeDetailVo.setCountyId(strawUtilize.getAreaId());
        strawUtilizeDetailVo.setCityId(strawUtilize.getCityId());
        strawUtilizeDetailVo.setProvinceId(strawUtilize.getProvinceId());
        // 表格信息
        List<StrawUtilizeDetail> list = strawUtilizeDetailService.getStrawUtilizeDetail(strawUtilizeId);
        strawUtilizeDetailVo.setStrawUtilizeDetailList(list);
        return Result.ok(strawUtilizeDetailVo);
    }

    @SofnLog("新增/修改规模化秸秆利用量数据")
    @ApiOperation(value = "新增/修改规模化秸秆利用量数据")
    @PostMapping("/addOrUpdateStrawUtilizeDetail")
    public Result addOrUpdateStrawUtilizeDetail(HttpServletRequest request, @Validated @RequestBody StrawUtilizeVo strawUtilizeVo) {
        //根据token获取登录用户Id
        String userId = UserUtil.getLoginUserId(request.getHeader("Authorization"));
        if (StringUtils.isBlank(userId)) {
            throw new SofnException("当前登录用户异常");
        }
        String result = strawUtilizeDetailService.addOrUpdateStrawUtilizeDetail(strawUtilizeVo, userId);

        return Result.ok(result);
    }

    @SofnLog("删除规模化秸秆利用量数据")
    @ApiOperation(value = "删除规模化秸秆利用量数据")
    @GetMapping("/delStrawUtilizeDetail")
    public Result delStrawUtilizeDetail(HttpServletRequest request, String strawUtilizeId) {
        //根据token获取登录用户Id
        String userId = UserUtil.getLoginUserId(request.getHeader("Authorization"));
        if (StringUtils.isBlank(userId)) {
            throw new SofnException("当前登录用户异常");
        }
        strawUtilizeDetailService.deleteStrawUtilizeById(strawUtilizeId);

        return Result.ok("删除成功");
    }


    @SofnLog("批量删除规模化秸秆利用量数据")
    @ApiOperation(value = "批量删除规模化秸秆利用量数据")
    @PostMapping("/batch/delStrawUtilizeDetail")
    public Result batchDelete(HttpServletRequest request, @RequestBody @Valid BatchIdsVo batchIdsVo) {
        //根据token获取登录用户Id
        String userId = UserUtil.getLoginUserId(request.getHeader("Authorization"));
        if (StringUtils.isBlank(userId)) {
            throw new SofnException("当前登录用户异常");
        }
        strawUtilizeDetailService.batchDeleteByUtilizeIds(batchIdsVo.getIds());
        return Result.ok("删除成功");
    }

    @SofnLog("获取填报过的市场主体名称")
    @ApiOperation(value = "获取填报过的市场主体名称")
    @GetMapping("/getMainName")
    public Result<List<String>> getMainName() {
        String userAreaByLogUser = UserUtil.getLoginUserOrganizationInfo();
        if (org.springframework.util.StringUtils.isEmpty(userAreaByLogUser)) {
            throw new SofnException("未获取到登录用户所属机构信息!");
        }
        SysOrganization sysOrganization = JsonUtils.json2obj(userAreaByLogUser, SysOrganization.class);
        String regioncode = sysOrganization.getRegioncode();
        List<String> areaList = JsonUtils.json2List(regioncode, String.class);
        //判断当前用户机构级别是否是乡镇级
        if (!RegionLevel.COUNTY.getCode().equals(sysOrganization.getOrganizationLevel())) {
            throw new SofnException("当前用户不是乡镇级用户!");
        }
        String countyId = areaList.get(2);
        List<String> mainNames = strawUtilizeService.getMainNames(countyId);
        return Result.ok(mainNames);
    }

}




