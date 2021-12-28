package com.sofn.ducss.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.sofn.common.exception.SofnException;
import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.JsonUtils;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.ducss.enums.RegionLevel;
import com.sofn.ducss.model.*;
import com.sofn.ducss.service.PopupService;
import com.sofn.ducss.service.ProStillDetailService;
import com.sofn.ducss.service.ProStillService;
import com.sofn.ducss.sysapi.SysApi;
import com.sofn.ducss.sysapi.bean.SysOrganization;
import com.sofn.ducss.util.SysRegionUtil;
import com.sofn.ducss.util.vo.AreaRegionCode;
import com.sofn.ducss.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Api(value = "产生量与直接还田量填报", tags = "产生量与直接还田量填报")
@RestController
@RequestMapping("/proStill")
public class ProStillController {

    @Autowired
    private ProStillService proStillService;

    @Autowired
    private ProStillDetailService proStillDetailService;

    @Autowired
    private PopupService popupService;

    @Autowired
    private SysApi sysApi;

    @SofnLog("填报查询")
    @ApiOperation(value = "填报查询")
    @PostMapping("/getProStill")
    public Result<PageUtils<ProStill>> getProStill(QueryPageVo queryVo, HttpServletRequest request) {
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
        if (StringUtils.isNotEmpty(queryVo.getYear())) {
            years = Arrays.asList(queryVo.getYear().split(","));
        }
        String countyId = queryVo.getCountyId();
        Integer pageNo = queryVo.getPageNo();
        Integer pageSize = queryVo.getPageSize();
        if (StringUtils.isBlank(countyId)) {
            PageHelper.offsetPage(pageNo, pageSize);
            List<ProStill> proStillList = new ArrayList<>();
            PageInfo<ProStill> pageInfo = new PageInfo<>(proStillList);
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
                PageUtils<ProStill> proStillPageUtils = proStillService.listGroupByYearAndAreaId(queryMap);
                List<ProStill> stillList = proStillPageUtils.getList();
                if (CollectionUtils.isNotEmpty(stillList)) {
                    Set<String> codeIds = Sets.newHashSet();
                    stillList.forEach(item -> {
                        codeIds.add(item.getAreaId());
                        codeIds.add(item.getCityId());
                        codeIds.add(item.getProvinceId());
                    });
                    codeIds.remove(null);
                    Map<String, String> regionNameMapsByCodes = SysRegionUtil.getRegionNameMapsByCodes(IdUtil.getStrIdsByList(Lists.newArrayList(codeIds)), null);
                    stillList.forEach(item -> {
                        String areaName = SysRegionUtil.getAreaName(regionNameMapsByCodes, item.getProvinceId(), item.getCityId(), item.getAreaId());
                        item.setAreaName(areaName);
                        String userName = UserUtil.getUsernameById(item.getCreateUserId());
                        item.setCreateUserName(userName);
                        item.setId(item.getId() == null ? IdUtil.getUUId() : item.getId());
                    });
                }
                return Result.ok(proStillPageUtils);
            }
            PageUtils<ProStill> proStill = proStillService.getProStillByPage(pageNo, pageSize, years, countyIds, null, null);
            return Result.ok(proStill);
        }
    }

    @SofnLog("查询产生量与直接还田量数据")
    @ApiOperation(value = "查询产生量与直接还田量数据")
    @GetMapping("/getProStillList")
    public Result<PageUtils<ProStill>> getProStillList(QueryVo queryVo
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
        List<String> countyIds = Lists.newArrayList(countyId);
        List<String> years = Lists.newArrayList();
        if (StringUtils.isNotEmpty(queryVo.getYear())) {
            years = Arrays.asList(queryVo.getYear().split(","));
        }
        // String year = queryVo.getYear();
        String dateBegin = queryVo.getDateBegin();
        String dateEnd = queryVo.getDateEnd();
        Integer pageNo = queryVo.getPageNo();
        Integer pageSize = queryVo.getPageSize();
        PageUtils<ProStill> countryTasks = proStillService.getProStillByPage(pageNo, pageSize, years, countyIds, dateBegin, dateEnd);

        return Result.ok(countryTasks);
    }

    @SofnLog("新增/编辑页面数据展示")
    @ApiOperation(value = "新增/编辑页面数据展示")
    @GetMapping("/getProStillDetailList")
    public Result<ProStillDetailVo> getProStillDetailList(HttpServletRequest request, String proStillId) {
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

        ProStillDetailVo proStillDetailVo = new ProStillDetailVo();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (null == proStillId) {
            proStillId = "";
        }
        if (!proStillId.equals("")) {
            ProStill proStill = proStillService.selectProStillById(proStillId);
            proStillDetailVo.setProStillId(proStillId);
            proStillDetailVo.setAddTime(proStill.getCreateDate());
            proStillDetailVo.setYear(proStill.getYear());
            proStillDetailVo.setDepartment(proStill.getReportArea());
            // 区域信息省市县
            proStillDetailVo.setCountyId(countyId);
            proStillDetailVo.setCityId(cityId);
            proStillDetailVo.setProvinceId(provinceId);
            // 表格信息
            List<ProStillDetail> list = proStillDetailService.getProStillDetail(proStillId);
            proStillDetailVo.setProStillDetails(list);
        } else {

            //判断是否弹出提示框
            Calendar date = Calendar.getInstance();
            String year = String.valueOf(date.get(Calendar.YEAR));//获取当前自然年

            Popup popup = popupService.selectPopupByAreaIdAndYear(countyId, year);
            if (null == popup) {
                Popup popupNew = new Popup();
                popupNew.setId(IdUtil.getUUId());
                popupNew.setProStillPopup("N");
                popupNew.setAreaId(countyId);
                popupNew.setYear(year);
                popupService.save(popupNew);
                proStillDetailVo.setPopup("Y");
            } else {
                if (popup.getProStillPopup().equals("Y")) {
                    proStillDetailVo.setPopup("Y");
                    popup.setProStillPopup("N");
                    popupService.updateById(popup);
                } else {
                    proStillDetailVo.setPopup("N");
                }
            }

            String createTime = sdf.format(new Date());
            proStillDetailVo.setProStillId("");
            proStillDetailVo.setAddTime(new Date());
            Calendar cal = Calendar.getInstance();
            proStillDetailVo.setYear(cal.get(Calendar.YEAR) + "");
            proStillDetailVo.setDepartment("");
            // 区域信息省市县
            proStillDetailVo.setCountyId(countyId);
            proStillDetailVo.setCityId(cityId);
            proStillDetailVo.setProvinceId(provinceId);
            // 表格信息
            List<ProStillDetail> list = proStillDetailService.getProStillDetail(proStillId);
            proStillDetailVo.setProStillDetails(list);

            //获取最近一年的表格数据进行快速填报
            String proStillIdByYear = proStillService.selectProStillIdByYear(countyId);
            if (null != proStillIdByYear) {
                ProStillDetailVo proStillDetailVo2 = new ProStillDetailVo();
                ProStill proStill = proStillService.selectProStillById(proStillIdByYear);
                proStillDetailVo2.setProStillId(proStillIdByYear);
                proStillDetailVo2.setAddTime(proStill.getCreateDate());
                proStillDetailVo2.setYear(proStill.getYear());
                proStillDetailVo2.setDepartment(proStill.getReportArea());
                // 区域信息省市县
                proStillDetailVo2.setCountyId(countyId);
                proStillDetailVo2.setCityId(cityId);
                proStillDetailVo2.setProvinceId(provinceId);
                // 表格信息
                List<ProStillDetail> list2 = proStillDetailService.getProStillDetail(proStillIdByYear);

                //调整表格顺序
                List<ProStillDetail> list3 = new ArrayList<>();
                for (ProStillDetail proStillDetail : list) {
                    String name = proStillDetail.getStrawName();
                    for (ProStillDetail proStillDetail12 : list2) {
                        String name2 = proStillDetail12.getStrawName();
                        if (name.equals(name2)) {
                            list3.add(proStillDetail12);
                        }
                    }
                }
                proStillDetailVo2.setProStillDetails(list3);
                proStillDetailVo.setProStillDetailVoByYear(proStillDetailVo2);
            }
        }
        return Result.ok(proStillDetailVo);
    }

    @SofnLog("填报查询详情获取")
    @ApiOperation(value = "填报查询详情获取")
    @GetMapping("/getProStillDetailListPro")
    public Result<ProStillDetailVo> getProStillDetailListPro(HttpServletRequest request, String proStillId) {
        if (StringUtils.isBlank(proStillId)) {
            throw new SofnException("查询参数不能为空");
        }
        ProStillDetailVo proStillDetailVo = new ProStillDetailVo();

        ProStill proStill = proStillService.selectProStillById(proStillId);
        proStillDetailVo.setProStillId(proStillId);
        proStillDetailVo.setAddTime(proStill.getCreateDate());
        proStillDetailVo.setYear(proStill.getYear());
        proStillDetailVo.setDepartment(proStill.getReportArea());
        // 区域信息省市县
        proStillDetailVo.setCountyId(proStill.getAreaId());
        proStillDetailVo.setCityId(proStill.getCityId());
        proStillDetailVo.setProvinceId(proStill.getProvinceId());
        // 表格信息
        List<ProStillDetail> list = proStillDetailService.getProStillDetail(proStillId);
        proStillDetailVo.setProStillDetails(list);

        return Result.ok(proStillDetailVo);
    }

    @SofnLog("新增/修改产生量与直接还田量数据")
    @ApiOperation(value = "新增/修改产生量与直接还田量数据")
    @PostMapping("/addOrUpdateProStillDetails")
    public Result addOrUpdateProStillDetails(HttpServletRequest request, @Validated @RequestBody ProStillVo proStillVo) {
        //根据token获取登录用户Id
        String userId = UserUtil.getLoginUserId(request.getHeader("Authorization"));
        if (StringUtils.isBlank(userId)) {
            throw new SofnException("当前登录用户异常");
        }
        String result = proStillDetailService.addOrUpdateProStill(proStillVo, userId);

        return Result.ok(result);
    }

    @SofnLog("删除产生量与直接还田量数据")
    @ApiOperation(value = "删除产生量与直接还田量数据")
    @GetMapping("/delProStill")
    public Result delProStill(HttpServletRequest request, String proStillId) {
        //根据token获取登录用户Id
        String userId = UserUtil.getLoginUserId(request.getHeader("Authorization"));
        if (StringUtils.isBlank(userId)) {
            throw new SofnException("当前登录用户异常");
        }
        proStillDetailService.deleteProStillById(proStillId);
        return Result.ok("删除成功");
    }

}




