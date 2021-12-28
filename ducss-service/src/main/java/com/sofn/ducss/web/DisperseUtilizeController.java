package com.sofn.ducss.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.sofn.common.exception.SofnException;
import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import com.sofn.common.utils.*;
import com.sofn.ducss.enums.Constants;
import com.sofn.ducss.enums.LogEnum;
import com.sofn.ducss.enums.RegionLevel;
import com.sofn.ducss.model.*;
import com.sofn.ducss.service.*;
import com.sofn.ducss.sysapi.bean.SysOrganization;
import com.sofn.ducss.util.LogUtil;
import com.sofn.ducss.util.SysRegionUtil;
import com.sofn.ducss.util.vo.AreaRegionCode;
import com.sofn.ducss.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Api(value = "分散利用量填报", tags = "分散利用量填报")
@RestController
@RequestMapping("/disperseUtilize")
public class DisperseUtilizeController {

    @Autowired
    private DisperseUtilizeService disperseUtilizeService;

    @Autowired
    private DisperseUtilizeDetailService disperseUtilizeDetailService;

    @Autowired
    private PopupService popupService;

    @SofnLog("填报查询")
    @ApiOperation(value = "填报查询")
    @PostMapping("/getDisperseUtilize")
    public Result<PageUtils<DisperseUtilize>> getDisperseUtilize(QueryPageVo queryVo, HttpServletRequest request) {
        //根据token获取登录用户Id
        String userId = UserUtil.getLoginUserId(request.getHeader("Authorization"));
        if (StringUtils.isBlank(userId)) {
            throw new SofnException("当前登录用户异常");
        }
        String year = queryVo.getYear();
        if (StringUtils.isBlank(year)) {
            throw new SofnException("请选择年度");
        }
        List<String> years = Lists.newArrayList();
        if (StringUtils.isNotEmpty(year)) {
            years = Arrays.asList(year.split(","));
        }
        String countyId = queryVo.getCountyId();
        Integer pageNo = queryVo.getPageNo();
        Integer pageSize = queryVo.getPageSize();
        if (StringUtils.isBlank(countyId)) {
            PageHelper.offsetPage(pageNo, pageSize);
            List<DisperseUtilize> disperseUtilize = new ArrayList<>();
            PageInfo<DisperseUtilize> pageInfo = new PageInfo<>(disperseUtilize);
            return Result.ok(PageUtils.getPageUtils(pageInfo));
        } else {
            List<String> countyIds = Arrays.asList(countyId.split(","));
            String firstCode = countyIds.get(0);
            AreaRegionCode firstArea = SysRegionUtil.getRegionCodeByLastCode(firstCode, null);
            if (!RegionLevel.COUNTY.getCode().equals(firstArea.getRegionLevel())) {
                Map<String, Object> queryMap = Maps.newHashMap();
                queryMap.put("pageNo", pageNo);
                queryMap.put("pageSize", pageSize);
                queryMap.put("years", years);
                if (RegionLevel.CITY.getCode().equals(firstArea.getRegionLevel())) {
                    queryMap.put("cityIds", countyIds);
                    queryMap.put("groupBy", "area_id");
                } else if (RegionLevel.PROVINCE.getCode().equals(firstArea.getRegionLevel())) {
                    queryMap.put("provinceIds", countyIds);
                    queryMap.put("groupBy", "city_id");
                }
                PageUtils<DisperseUtilize> disperseUtilizePageUtils = disperseUtilizeService.listGroupByYearAndAreaId(queryMap);
                List<DisperseUtilize> utilizeList = disperseUtilizePageUtils.getList();
                if (CollectionUtils.isNotEmpty(utilizeList)) {
                    Set<String> codeIds = Sets.newHashSet();
                    utilizeList.forEach(item -> {
                        codeIds.add(item.getAreaId());
                        codeIds.add(item.getCityId());
                        codeIds.add(item.getProvinceId());
                    });
                    codeIds.remove(null);
                    Map<String, String> regionNameMapsByCodes = SysRegionUtil.getRegionNameMapsByCodes(IdUtil.getStrIdsByList(Lists.newArrayList(codeIds)), null);
                    utilizeList.forEach(item -> {
                        String areaName = SysRegionUtil.getAreaName(regionNameMapsByCodes, item.getProvinceId(), item.getCityId(), item.getAreaId());
                        item.setAreaName(areaName);
                        item.setId(item.getId() == null ? IdUtil.getUUId() : item.getId());
                    });
                }
                return Result.ok(disperseUtilizePageUtils);
            }
            PageUtils<DisperseUtilize> disperseUtilize = disperseUtilizeService.getDisperseUtilize(years, countyIds, pageNo, pageSize);
            return Result.ok(disperseUtilize);
        }
    }

    @SofnLog("查询分散利用量数据")
    @ApiOperation(value = "查询分散利用量量数据")
    @GetMapping("/getDisperseUtilizeList")
    public Result<PageUtils<DisperseUtilize>> getDisperseUtilizeList(DisperseUtilizeQueryVo queryVo
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
        //判断当前用户机构级别是否是县级
        if (!RegionLevel.COUNTY.getCode().equals(sysOrganization.getOrganizationLevel())) {
            throw new SofnException("当前用户不是县级用户!");
        }
        String countyId = areaList.get(2);
        // String year = queryVo.getYear();
        List<String> years = Lists.newArrayList();
        if (StringUtils.isNotEmpty(queryVo.getYear())) {
            years = Arrays.asList(queryVo.getYear().split(","));
        }
        String userName = queryVo.getUserName();
        String dateBegin = queryVo.getDateBegin();
        String dateEnd = queryVo.getDateEnd();
        Integer pageNo = queryVo.getPageNo();
        Integer pageSize = queryVo.getPageSize();
        PageUtils<DisperseUtilize> disperseUtilizeByPage = disperseUtilizeService.getDisperseUtilizeByPage(pageNo, pageSize, years, userName, countyId, dateBegin, dateEnd);

        return Result.ok(disperseUtilizeByPage);
    }

    @SofnLog("新增/编辑页面数据展示")
    @ApiOperation(value = "新增/编辑页面数据展示")
    @GetMapping("/getDisperseUtilizeDetailList")
    public Result<DisperseUtilizeDetailVo> getDisperseUtilizeDetailList(HttpServletRequest request, String disperseUtilizeId,
                                                                        @RequestParam(value = "farmerName", required = false) @ApiParam(value = "户主姓名", required = false) String farmerName) {
        String userAreaByLogUser = UserUtil.getLoginUserOrganizationInfo();
        if (org.springframework.util.StringUtils.isEmpty(userAreaByLogUser)) {
            throw new SofnException("未获取到登录用户所属机构信息!");
        }
        SysOrganization sysOrganization = JsonUtils.json2obj(userAreaByLogUser, SysOrganization.class);
        String regioncode = sysOrganization.getRegioncode();
        List<String> areaList = JsonUtils.json2List(regioncode, String.class);
        //判断当前用户机构级别是否是县级
        if (!RegionLevel.COUNTY.getCode().equals(sysOrganization.getOrganizationLevel())) {
            throw new SofnException("当前用户不是县级用户!");
        }
        String countyId = areaList.get(2);
        String cityId = areaList.get(1);
        String provinceId = areaList.get(0);

        DisperseUtilizeDetailVo disperseUtilizeDetailVo = new DisperseUtilizeDetailVo();
        if (null == disperseUtilizeId) {
            disperseUtilizeId = "";
        }
        if (!disperseUtilizeId.equals("")) {
            DisperseUtilize disperseUtilize = disperseUtilizeService.selectDisperseUtilizeById(disperseUtilizeId);
            disperseUtilizeDetailVo.setDisperseUtilizeId(disperseUtilizeId);
            disperseUtilizeDetailVo.setAddTime(disperseUtilize.getCreateDate());
            disperseUtilizeDetailVo.setYear(disperseUtilize.getYear());
            disperseUtilizeDetailVo.setDepartment(disperseUtilize.getReportArea());
            disperseUtilizeDetailVo.setFarmerNo(disperseUtilize.getFarmerNo());
            disperseUtilizeDetailVo.setFarmerName(disperseUtilize.getFarmerName());
            disperseUtilizeDetailVo.setFarmerPhone(disperseUtilize.getFarmerPhone());
            disperseUtilizeDetailVo.setAddress(disperseUtilize.getAddress());
            // 区域信息省市县
            disperseUtilizeDetailVo.setCountyId(countyId);
            disperseUtilizeDetailVo.setCityId(cityId);
            disperseUtilizeDetailVo.setProvinceId(provinceId);
            // 表格信息
            List<DisperseUtilizeDetail> list = disperseUtilizeDetailService.getDisperseUtilizeDetail(disperseUtilizeId);
            disperseUtilizeDetailVo.setDisperseUtilizeDetailList(list);
        } else {

            //判断是否弹出提示框
            Calendar date2 = Calendar.getInstance();
            String year = String.valueOf(date2.get(Calendar.YEAR));//获取当前自然年

            Popup popup = popupService.selectPopupByAreaIdAndYear(countyId, year);
            if (null == popup) {
                Popup popupNew = new Popup();
                popupNew.setId(IdUtil.getUUId());
                popupNew.setDisperseUtilizePopup("N");
                popupNew.setAreaId(countyId);
                popupNew.setYear(year);
                popupService.save(popupNew);
                disperseUtilizeDetailVo.setPopup("Y");
            } else {
                if (popup.getDisperseUtilizePopup().equals("Y")) {
                    disperseUtilizeDetailVo.setPopup("Y");
                    popup.setDisperseUtilizePopup("N");
                    popupService.updateById(popup);
                } else {
                    disperseUtilizeDetailVo.setPopup("N");
                }
            }


            Date date = new Date();
            GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
            gc.setTime(date);
            Integer lastYear = gc.get(1) - 1;
            disperseUtilizeDetailVo.setYear(lastYear.toString());
            disperseUtilizeDetailVo.setAddTime(new Date());
            disperseUtilizeDetailVo.setDepartment("");
            disperseUtilizeDetailVo.setDisperseUtilizeId("");
            disperseUtilizeDetailVo.setFarmerNo("");
            // 区域信息省市县
            disperseUtilizeDetailVo.setCountyId(countyId);
            disperseUtilizeDetailVo.setCityId(cityId);
            disperseUtilizeDetailVo.setProvinceId(provinceId);
            // 表格信息
            List<DisperseUtilizeDetail> list = disperseUtilizeDetailService.getDisperseUtilizeDetail(disperseUtilizeId);
            for (DisperseUtilizeDetail disperseUtilizeDetail : list) {
                disperseUtilizeDetail.setApplication("");
            }
            disperseUtilizeDetailVo.setDisperseUtilizeDetailList(list);

            if (null == farmerName) {
                farmerName = "";
            }
            //获取最近一年的表格数据进行快速填报
            String disperseUtilizeIdByYear = disperseUtilizeService.selectDisperseUtilizeIdByYear(countyId, farmerName);
            if (null != disperseUtilizeIdByYear) {
                DisperseUtilizeDetailVo disperseUtilizeDetailVo2 = new DisperseUtilizeDetailVo();
                DisperseUtilize disperseUtilize = disperseUtilizeService.selectDisperseUtilizeById(disperseUtilizeIdByYear);
                disperseUtilizeDetailVo2.setDisperseUtilizeId(disperseUtilizeIdByYear);
                disperseUtilizeDetailVo2.setAddTime(disperseUtilize.getCreateDate());
                disperseUtilizeDetailVo2.setYear(disperseUtilize.getYear());
                disperseUtilizeDetailVo2.setDepartment(disperseUtilize.getReportArea());
                disperseUtilizeDetailVo2.setFarmerNo(disperseUtilize.getFarmerNo());
                disperseUtilizeDetailVo2.setFarmerName(disperseUtilize.getFarmerName());
                disperseUtilizeDetailVo2.setFarmerPhone(disperseUtilize.getFarmerPhone());
                disperseUtilizeDetailVo2.setAddress(disperseUtilize.getAddress());
                // 区域信息省市县
                disperseUtilizeDetailVo2.setCountyId(countyId);
                disperseUtilizeDetailVo2.setCityId(cityId);
                disperseUtilizeDetailVo2.setProvinceId(provinceId);
                // 表格信息
                List<DisperseUtilizeDetail> list2 = disperseUtilizeDetailService.getDisperseUtilizeDetail(disperseUtilizeIdByYear);

                //调整表格顺序
                List<DisperseUtilizeDetail> list3 = new ArrayList<>();
                for (DisperseUtilizeDetail disperseUtilizeDetail : list) {
                    String name = disperseUtilizeDetail.getStrawName();
                    for (DisperseUtilizeDetail disperseUtilizeDetail12 : list2) {
                        String name2 = disperseUtilizeDetail12.getStrawName();
                        if (name.equals(name2)) {
                            list3.add(disperseUtilizeDetail12);
                        }
                    }
                }

                disperseUtilizeDetailVo2.setDisperseUtilizeDetailList(list3);
                disperseUtilizeDetailVo.setDisperseUtilizeDetailVoByYear(disperseUtilizeDetailVo2);
            }
        }
        return Result.ok(disperseUtilizeDetailVo);
    }

    @SofnLog("填报查询详情获取")
    @ApiOperation(value = "填报查询详情获取")
    @GetMapping("/getDisperseUtilizeDetailListPro")
    public Result<DisperseUtilizeDetailVo> getDisperseUtilizeDetailListPro(HttpServletRequest request, String disperseUtilizeId) {
        if (StringUtils.isBlank(disperseUtilizeId)) {
            throw new SofnException("查询参数不能为空");
        }

        DisperseUtilizeDetailVo disperseUtilizeDetailVo = new DisperseUtilizeDetailVo();

        DisperseUtilize disperseUtilize = disperseUtilizeService.selectDisperseUtilizeById(disperseUtilizeId);
        disperseUtilizeDetailVo.setDisperseUtilizeId(disperseUtilizeId);
        disperseUtilizeDetailVo.setAddTime(disperseUtilize.getCreateDate());
        disperseUtilizeDetailVo.setYear(disperseUtilize.getYear());
        disperseUtilizeDetailVo.setDepartment(disperseUtilize.getReportArea());
        disperseUtilizeDetailVo.setFarmerNo(disperseUtilize.getFarmerNo());
        disperseUtilizeDetailVo.setFarmerName(disperseUtilize.getFarmerName());
        disperseUtilizeDetailVo.setFarmerPhone(disperseUtilize.getFarmerPhone());
        disperseUtilizeDetailVo.setAddress(disperseUtilize.getAddress());
        // 区域信息省市县
        disperseUtilizeDetailVo.setCountyId(disperseUtilize.getAreaId());
        disperseUtilizeDetailVo.setCityId(disperseUtilize.getCityId());
        disperseUtilizeDetailVo.setProvinceId(disperseUtilize.getProvinceId());
        // 表格信息
        List<DisperseUtilizeDetail> list = disperseUtilizeDetailService.getDisperseUtilizeDetail(disperseUtilizeId);
        disperseUtilizeDetailVo.setDisperseUtilizeDetailList(list);
        return Result.ok(disperseUtilizeDetailVo);
    }

    @SofnLog("新增/修改分散利用量数据")
    @ApiOperation(value = "新增/修改分散利用量数据")
    @PostMapping("/addOrUpdateDisperseUtilizeDetail")
    public Result addOrUpdateDisperseUtilizeDetail(HttpServletRequest request, @Validated @RequestBody DisperseUtilizeVo disperseUtilizeVo) {
        //根据token获取登录用户Id
        String userId = UserUtil.getLoginUserId(request.getHeader("Authorization"));
        if (StringUtils.isBlank(userId)) {
            throw new SofnException("当前登录用户异常");
        }
        String result = disperseUtilizeDetailService.addOrUpdateDisperseUtilizeDetail(disperseUtilizeVo, userId);

        return Result.ok(result);
    }

    @SofnLog("删除分散利用量数据")
    @ApiOperation(value = "删除分散利用量数据")
    @GetMapping("/delUtilizeDetail")
    public Result delUtilizeDetail(HttpServletRequest request, String disperseUtilizeId) {
        //根据token获取登录用户Id
        String userId = UserUtil.getLoginUserId(request.getHeader("Authorization"));
        if (StringUtils.isBlank(userId)) {
            throw new SofnException("当前登录用户异常");
        }
        disperseUtilizeDetailService.deleteDisperseUtilizeById(disperseUtilizeId);
        return Result.ok("删除成功");
    }

    @SofnLog("批量删除分散利用量数据")
    @ApiOperation(value = "批量删除分散利用量数据")
    @PostMapping("/batch/delUtilizeDetail")
    public Result batchDelete(HttpServletRequest request, @RequestBody @Valid BatchIdsVo batchIdsVo) {
        //根据token获取登录用户Id
        String userId = UserUtil.getLoginUserId(request.getHeader("Authorization"));
        if (StringUtils.isBlank(userId)) {
            throw new SofnException("当前登录用户异常");
        }
        disperseUtilizeDetailService.batchDeleteByUtilizeIds(batchIdsVo.getIds());
        return Result.ok("删除成功");
    }


    @SofnLog("统计赋值")
    @ApiOperation(value = "统计赋值")
    @GetMapping("/statisticalAssignment")
    public Result statisticalAssignment() {
        disperseUtilizeDetailService.statisticalAssignment();
        return Result.ok("成功");
    }

    @SofnLog("获取填报过的户主姓名")
    @ApiOperation(value = "获取填报过的户主姓名")
    @GetMapping("/getFarmerName")
    public Result<List<String>> getFarmerName() {
        String userAreaByLogUser = UserUtil.getLoginUserOrganizationInfo();
        if (org.springframework.util.StringUtils.isEmpty(userAreaByLogUser)) {
            throw new SofnException("未获取到登录用户所属机构信息!");
        }
        SysOrganization sysOrganization = JsonUtils.json2obj(userAreaByLogUser, SysOrganization.class);
        String regioncode = sysOrganization.getRegioncode();
        List<String> areaList = JsonUtils.json2List(regioncode, String.class);
        //判断当前用户机构级别是否是县级
        if (!RegionLevel.COUNTY.getCode().equals(sysOrganization.getOrganizationLevel())) {
            throw new SofnException("当前用户不是县级用户!");
        }
        String countyId = areaList.get(2);
        List<String> farmerNames = disperseUtilizeService.getFarmerNames(countyId);
        return Result.ok(farmerNames);
    }

}




