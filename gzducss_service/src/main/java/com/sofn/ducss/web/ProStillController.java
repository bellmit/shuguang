package com.sofn.ducss.web;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sofn.ducss.exception.SofnException;
import com.sofn.ducss.model.SysOrganization;
import com.sofn.ducss.util.IdUtil;
import com.sofn.ducss.log.SofnLog;
import com.sofn.ducss.model.basemodel.Result;
import com.sofn.ducss.util.JsonUtils;
import com.sofn.ducss.util.PageUtils;
import com.sofn.ducss.util.UserUtil;
import com.sofn.ducss.enums.RegionLevel;
import com.sofn.ducss.model.Popup;
import com.sofn.ducss.model.ProStill;
import com.sofn.ducss.model.ProStillDetail;
import com.sofn.ducss.service.PopupService;
import com.sofn.ducss.service.ProStillDetailService;
import com.sofn.ducss.service.ProStillService;
import com.sofn.ducss.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
        String countyId = queryVo.getCountyId();
        Integer pageNo = queryVo.getPageNo();
        Integer pageSize = queryVo.getPageSize();
        if (StringUtils.isBlank(countyId)) {
            PageHelper.offsetPage(pageNo, pageSize);
            List<ProStill> proStillList = new ArrayList<>();
            PageInfo<ProStill> pageInfo = new PageInfo<>(proStillList);
            return Result.ok(PageUtils.getPageUtils(pageInfo));
        } else {
            PageUtils<ProStill> proStill = proStillService.getProStillByPage(pageNo, pageSize, year, countyId, null, null);
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
        //判断当前用户机构级别是否是乡镇级
        if (!RegionLevel.COUNTY.getCode().equals(sysOrganization.getOrganizationLevel())) {
            throw new SofnException("当前用户不是乡镇级用户!");
        }
        String countyId = areaList.get(2);
        String year = queryVo.getYear();
        String dateBegin = queryVo.getDateBegin();
        String dateEnd = queryVo.getDateEnd();
        Integer pageNo = queryVo.getPageNo();
        Integer pageSize = queryVo.getPageSize();
        PageUtils<ProStill> countryTasks = proStillService.getProStillByPage(pageNo, pageSize, year, countyId, dateBegin, dateEnd);

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
        //判断当前用户机构级别是否是乡镇级
        if (!RegionLevel.COUNTY.getCode().equals(sysOrganization.getOrganizationLevel())) {
            throw new SofnException("当前用户不是乡镇级用户!");
        }
        String countyId = areaList.get(2);
        String cityId = areaList.get(1);
        String provinceId = areaList.get(0);

        ProStillDetailVo proStillDetailVo = new ProStillDetailVo();
        proStillDetailVo.setDepartment(sysOrganization.getOrganizationName());
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

            // String createTime = sdf.format(new Date());
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




