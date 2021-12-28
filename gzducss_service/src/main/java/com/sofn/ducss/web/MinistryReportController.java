/**
 * Copyright (c) 1998-2020 SOFN Corporation
 * All rights reserved.
 * <p>
 * <p>
 * Created On 2020-12-10 9:44
 */
package com.sofn.ducss.web;

import com.deepoove.poi.XWPFTemplate;
import com.sofn.ducss.enums.AuditStatusEnum;
import com.sofn.ducss.enums.Constants;
import com.sofn.ducss.enums.RegionLevel;
import com.sofn.ducss.enums.SixRegionEnum;
import com.sofn.ducss.model.SysOrganization;
import com.sofn.ducss.model.basemodel.Result;
import com.sofn.ducss.sysapi.SysApi;
import com.sofn.ducss.sysapi.SysFileApi;
import com.sofn.ducss.model.SysFileManageForm;
import com.sofn.ducss.vo.SysFileVo;
import com.sofn.ducss.vo.SysRegionTreeVo;
import com.sofn.ducss.util.JsonUtils;
import com.sofn.ducss.util.UserUtil;
import com.sofn.ducss.config.FormCallbackConfig;
import com.sofn.ducss.exception.SofnException;
import com.sofn.ducss.mapper.CollectFlowMapper;
import com.sofn.ducss.mapper.ReportDataMapper;
import com.sofn.ducss.model.StrawUtilizeSum;
import com.sofn.ducss.service.ProvinceReportService;
import com.sofn.ducss.service.ReportService;
import com.sofn.ducss.vo.ProReportForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;

import static com.xiaoleilu.hutool.util.ClassUtil.getClassLoader;

/**
 * 部级报告导出控制器
 *
 * @author jiangtao
 * @version 1.0
 **/
@Slf4j
@Api(value = "部级报告生成", tags = "部级模板生成报告")
@Controller
@RequestMapping("/ministryReportManager")
public class MinistryReportController {

    @Autowired(required = false)
    private SysFileApi sysFileApi;

    @Autowired
    private ProvinceReportService provinceReportService;

    @Autowired
    private FormCallbackConfig formCallbackConfig;

    @Autowired
    private ReportService reportService;

    @Autowired
    private SysApi sysApi;

    @Autowired
    private ReportDataMapper reportDataMapper;

    @Autowired
    private CollectFlowMapper collectFlowMapper;
    //临时文件存放
    private static final String templateFolder = getClassLoader().getResource("static/").getPath();

    /*@ApiOperation(value = "部级报告主要粮食作物秸秆产生与利用情况报告部分",produces = "application/octet-stream")
    @PostMapping(value = "/exportMajorYieldReport", produces = "application/octet-stream")
    public void  exportReport(HttpServletRequest request, HttpServletResponse response, String year) {
        reportService.getMajorYieldReportTotal(request, response, year);
    }*/


    /*@ApiOperation(value = "部级报告生成不同地区秸秆产生与利用情况分析",produces = "application/octet-stream")
    @PostMapping(value = "/exportDifferentRegionReport", produces = "application/octet-stream")
    public void  exportDifferentRegionReport(HttpServletRequest request, HttpServletResponse response, String year) {
        reportService.getDifferentRegionReport(request, response, year);
    }*/

    @ResponseBody
    @ApiOperation(value = "部级报告主要粮食作物秸秆产生与利用情况报告部分(部级报告)")
    @PostMapping(value = "/exportMajorYieldReport")
    public Result exportMajorYieldReport(HttpServletRequest request, @ApiParam(value = "年份") @RequestParam(value = "year", required = false) String year) {
        //根据token获取登录用户Id
        String userId = UserUtil.getLoginUserId(request.getHeader("Authorization"));
        if (StringUtils.isBlank(userId)) {
            throw new SofnException("当前登录用户异常");
        }
        String token = UserUtil.getLoginToken();
        String userAreaByLogUser = UserUtil.getLoginUserOrganizationInfo();
        if (org.springframework.util.StringUtils.isEmpty(userAreaByLogUser)) {
            throw new SofnException("未获取到登录用户所属机构信息!");
        }
        SysOrganization sysOrganization = JsonUtils.json2obj(userAreaByLogUser, SysOrganization.class);
        String regioncode = sysOrganization.getRegioncode();
        //判断当前用户级别
        if (!RegionLevel.MINISTRY.getCode().equals(sysOrganization.getOrganizationLevel())) {
            throw new SofnException("当前用户不是部级用户！");
        }
        //玉米秸秆产生量
        //若不输入年度,则取当前年的上一年度
        if (StringUtils.isEmpty(year) || StringUtils.isNumeric(year) == false) {
            year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR) - 1);
        }
        //获取数据
        List<String> status = new ArrayList<>();
        status.add(AuditStatusEnum.PASSED.getCode());
        status.add(AuditStatusEnum.REPORTED.getCode());
        //获取全国省份
        List<String> areaCodes = new ArrayList<>();
        //获取当前区域下一级areacode
        List<SysRegionTreeVo> regionTree = sysApi.getSysRegionTreeById("100000", "ducss");
        if (regionTree.size() > 0) {
            for (SysRegionTreeVo treeDatum : regionTree) {
                areaCodes.add(treeDatum.getRegionCode());
            }
        } else {
            throw new SofnException("下级区域异常");
        }
        //查询当前产量最高的作物
        List<StrawUtilizeSum> byStrawType = reportDataMapper.getSumGroupByStrawType(year, areaCodes, status, "theory_resource");
        String strawType = "";
        if (byStrawType != null && byStrawType.size() > 0) {
            strawType = byStrawType.get(0).getStrawType();
        } else {
            throw new SofnException("当年未有作物产量");
        }
        Map<String, Object> mapOne = reportService.getMajorYieldReport(year, byStrawType.get(0).getStrawType());
        Map<String, Object> mapTwo = reportService.getMajorYieldReport(year, byStrawType.get(1).getStrawType());
        Map<String, Object> mapThree = reportService.getMajorYieldReport(year, byStrawType.get(2).getStrawType());
        List<Map<String, Object>> documents = new ArrayList<>();
        documents.add(mapOne);
        documents.add(mapTwo);
        documents.add(mapThree);
        try {
            ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            Resource[] resources = resolver.getResources("static/majorYieldTwo.docx");
            Resource resource = resources[0];
            //获得文件流，因为在jar文件中，不能直接通过文件资源路径拿到文件，但是可以在jar包中拿到文件流
            InputStream stream = resource.getInputStream();
            //Linux目录下的/usr/
            String tmpPath = formCallbackConfig.getDir() + File.separator + "majorYieldTemporary" + (int) (Math.random() * 100000) + ".docx";
//            String tmpPath = templateFolder + "majorYieldTemporary" + (int) (Math.random() * 100000)  + ".docx";
            //临时文件保存 linux 下地址
            String targetPath = formCallbackConfig.getDir() + File.separator + year + "_" + "部级报告_第四部分、主要粮食作物秸秆产生与利用情况分析报告" + ".docx";
//            String targetPath = templateFolder  + year + "_"+ "ministry_第四部分、主要粮食作物秸秆产生与利用情况分析" + ".docx";
            File targetFile = new File(tmpPath);
            //将流读到目标文件中
            FileUtils.copyInputStreamToFile(stream, targetFile);
            XWPFTemplate.compile(tmpPath).render(new HashMap<String, Object>() {
                {
                    put("majorYield", documents);
                }
            }).writeToFile(targetPath);
            //将生成的文件上传到文件服务器上
            File file = new File(targetPath);
            DiskFileItem fileItem = (DiskFileItem) new DiskFileItemFactory().createItem("file",
                    MediaType.MULTIPART_FORM_DATA_VALUE, true, file.getName());
            try (
                    InputStream input = new FileInputStream(file);
                    OutputStream os = fileItem.getOutputStream()) {
                IOUtils.copy(input, os);
            }
            MultipartFile multipartFile = new CommonsMultipartFile(fileItem);

            //调用支撑平台接口上传图片
            SysFileVo data = sysFileApi.uploadFile(multipartFile, token);

            if (targetFile.exists()) {
                targetFile.delete();
            }
            //激活
            if (null != data) {
                SysFileManageForm fileManageForm = new SysFileManageForm(data.getFileName(), "ducss", null, null, data.getId());
                //标注为报告文件
                fileManageForm.setBusinessFileType(Constants.REPORT_FILE);
                sysFileApi.activationFile(fileManageForm, token);
                ProReportForm form = new ProReportForm(data.getId(), data.getFilePath());
                if (file.exists()) {
                    file.delete();
                }
                return new Result<>(Result.CODE, form);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.error("部级报告主要粮食作物秸秆产生与利用情况报告部分!");
    }

    @ResponseBody
    @ApiOperation(value = "导出不同地区秸秆产生与利用情况分析报告(部级报告)")
    @PostMapping(value = "/exportDifferentRegionReport")
    public Result exportDifferentRegionReport(HttpServletRequest request, @ApiParam(value = "年份") @RequestParam(value = "year", required = false) String year) {
        //根据token获取登录用户Id
        String userId = UserUtil.getLoginUserId(request.getHeader("Authorization"));
        if (StringUtils.isBlank(userId)) {
            throw new SofnException("当前登录用户异常");
        }
        String token = UserUtil.getLoginToken();
        String userAreaByLogUser = UserUtil.getLoginUserOrganizationInfo();
        if (org.springframework.util.StringUtils.isEmpty(userAreaByLogUser)) {
            throw new SofnException("未获取到登录用户所属机构信息!");
        }
        SysOrganization sysOrganization = JsonUtils.json2obj(userAreaByLogUser, SysOrganization.class);
        String regioncode = sysOrganization.getRegioncode();
        //判断当前用户级别
        if (!RegionLevel.MINISTRY.getCode().equals(sysOrganization.getOrganizationLevel())) {
            throw new SofnException("当前用户不是部级用户！");
        }
        //获取东北区
        Map<String, Object> mapOne = reportService.getDifferentRegionReportMap(year, SixRegionEnum.NORTHEAST_REGION.getCode(), SixRegionEnum.NORTHEAST_REGION.getDescription());
        Map<String, Object> mapTwo = reportService.getDifferentRegionReportMap(year, SixRegionEnum.NORTH_REGION.getCode(), SixRegionEnum.NORTH_REGION.getDescription());
        Map<String, Object> mapThree = reportService.getDifferentRegionReportMap(year, SixRegionEnum.CHANG_JIANG_RIVER_REGION.getCode(), SixRegionEnum.CHANG_JIANG_RIVER_REGION.getDescription());
        Map<String, Object> mapFour = reportService.getDifferentRegionReportMap(year, SixRegionEnum.SOUTH_REGION.getCode(), SixRegionEnum.SOUTH_REGION.getDescription());
        Map<String, Object> mapFive = reportService.getDifferentRegionReportMap(year, SixRegionEnum.NORTHWEST_REGION.getCode(), SixRegionEnum.NORTHWEST_REGION.getDescription());
        Map<String, Object> mapSix = reportService.getDifferentRegionReportMap(year, SixRegionEnum.SOUTHWEST_REGION.getCode(), SixRegionEnum.SOUTHWEST_REGION.getDescription());

        List<Map<String, Object>> documents = new ArrayList<>();
        documents.add(mapOne);
        documents.add(mapTwo);
        documents.add(mapThree);
        documents.add(mapFour);
        documents.add(mapFive);
        documents.add(mapSix);
        try {
            ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            Resource[] resources = resolver.getResources("static/differentRegionTwo.docx");
            Resource resource = resources[0];
            //获得文件流，因为在jar文件中，不能直接通过文件资源路径拿到文件，但是可以在jar包中拿到文件流
            InputStream stream = resource.getInputStream();
            //Linux目录下的/usr/
            String tmpPath = formCallbackConfig.getDir() + File.separator + "differentRegionTemporary" + (int) (Math.random() * 100000) + ".docx";
//            String tmpPath = templateFolder + "differentRegionTemporary" + (int) (Math.random() * 100000)  + ".docx";
            //临时文件保存
            String targetPath = formCallbackConfig.getDir() + File.separator + year + "_" + "部级报告_第三部分、不同地区秸秆产生与利用情况分析" + ".docx";
//            String targetPath = templateFolder+ year + "_" +  "ministry_第三部分、不同地区秸秆产生与利用情况分析"  + ".docx";
            File targetFile = new File(tmpPath);
            //将流读到目标文件中
            FileUtils.copyInputStreamToFile(stream, targetFile);
            XWPFTemplate.compile(tmpPath).render(new HashMap<String, Object>() {
                {
                    put("differentRegion", documents);
                }
            }).writeToFile(targetPath);
            //将生成的文件上传到文件服务器上
            File file = new File(targetPath);
            DiskFileItem fileItem = (DiskFileItem) new DiskFileItemFactory().createItem("file",
                    MediaType.MULTIPART_FORM_DATA_VALUE, true, file.getName());
            try (
                    InputStream input = new FileInputStream(file);
                    OutputStream os = fileItem.getOutputStream()) {
                IOUtils.copy(input, os);
            }
            MultipartFile multipartFile = new CommonsMultipartFile(fileItem);

            //调用支撑平台接口上传图片
            SysFileVo data = sysFileApi.uploadFile(multipartFile, token);

            if (targetFile.exists()) {
                targetFile.delete();
            }
            //激活
            if (null != data) {
                SysFileManageForm fileManageForm = new SysFileManageForm(data.getFileName(), "ducss", null, null, data.getId());
                //标注为报告文件
                fileManageForm.setBusinessFileType(Constants.REPORT_FILE);
                sysFileApi.activationFile(fileManageForm, token);
                ProReportForm form = new ProReportForm(data.getId(), data.getFilePath());
                if (file.exists()) {
                    file.delete();
                }
                return new Result<>(Result.CODE, form);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.error("导出不同地区秸秆产生与利用情况分析报告!");
    }

    /**
     * 生成第一部分的报告
     */
    @ResponseBody
    @ApiOperation(value = "导出不同地区秸秆产生与利用情况分析报告(部级报告)")
    @GetMapping(value = "/createReport")
    public Result createReport(HttpServletRequest request, @ApiParam(value = "年份") @RequestParam(value = "year", required = false) String year) {
        //第五部分
        this.everyProvinceReport(request, year);
        // 第四部分
        this.exportMajorYieldReport(request, year);
        // 第三部分
        this.exportDifferentRegionReport(request, year);
        //根据token获取登录用户Id
        String userId = UserUtil.getLoginUserId(request.getHeader("Authorization"));
        if (StringUtils.isBlank(userId)) {
            throw new SofnException("当前登录用户异常");
        }
        String token = UserUtil.getLoginToken();
        String userAreaByLogUser = UserUtil.getLoginUserOrganizationInfo();
        if (org.springframework.util.StringUtils.isEmpty(userAreaByLogUser)) {
            throw new SofnException("未获取到登录用户所属机构信息!");
        }
        SysOrganization sysOrganization = JsonUtils.json2obj(userAreaByLogUser, SysOrganization.class);
        String regioncode = sysOrganization.getRegioncode();
        //判断当前用户级别
        if (!RegionLevel.MINISTRY.getCode().equals(sysOrganization.getOrganizationLevel())) {
            throw new SofnException("当前用户不是部级用户！");
        }


        try {
            ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            Resource[] resources = resolver.getResources("static/real-template.docx");
            Resource resource = resources[0];
            //获得文件流，因为在jar文件中，不能直接通过文件资源路径拿到文件，但是可以在jar包中拿到文件流
            InputStream stream = resource.getInputStream();
            //Linux目录下的/usr/
            String tmpPath = formCallbackConfig.getDir() + File.separator + "temp2-new" + (int) (Math.random() * 100000) + ".docx";
//            String tmpPath = templateFolder + "differentRegionTemporary" + (int) (Math.random() * 100000)  + ".docx";
            Map<String, Object> documents = reportService.getProductAndUseInfoRort(year);
            //临时文件保存
            String targetPath = formCallbackConfig.getDir() + File.separator + year + "_" + "部级报告_第一二部分、全国农作物秸秆产生与利用情况分析" + ".docx";
//            String targetPath = templateFolder + year + "_"+   "ministry_第一二部分、全国农作物秸秆产生与利用情况分析"  + ".docx";
            File targetFile = new File(tmpPath);
            //将流读到目标文件中
            FileUtils.copyInputStreamToFile(stream, targetFile);
            XWPFTemplate.compile(tmpPath).render(documents).writeToFile(targetPath);

            //将生成的文件上传到文件服务器上
            File file = new File(targetPath);
            DiskFileItem fileItem = (DiskFileItem) new DiskFileItemFactory().createItem("file",
                    MediaType.MULTIPART_FORM_DATA_VALUE, true, file.getName());
            try (
                    InputStream input = new FileInputStream(file);
                    OutputStream os = fileItem.getOutputStream()) {
                IOUtils.copy(input, os);
            }
            MultipartFile multipartFile = new CommonsMultipartFile(fileItem);

            //调用支撑平台接口上传图片
            SysFileVo data = sysFileApi.uploadFile(multipartFile, token);

            if (targetFile.exists()) {
                targetFile.delete();
            }
            //激活
            if (null != data) {
                SysFileManageForm fileManageForm = new SysFileManageForm(data.getFileName(), "ducss", null, null, data.getId());
                //标注为报告文件
                fileManageForm.setBusinessFileType(Constants.REPORT_FILE);
                sysFileApi.activationFile(fileManageForm, token);
                ProReportForm form = new ProReportForm(data.getId(), data.getFilePath());
                if (file.exists()) {
                    file.delete();
                }
                return new Result<>(Result.CODE, form);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.error("导出不同地区秸秆产生与利用情况分析报告!");
    }


    @ResponseBody
    @ApiOperation(value = "分省区农作物秸秆产生与利用情况分析部分(部级报告)")
    @PostMapping(value = "/everyProvinceReport")
    public Result everyProvinceReport(HttpServletRequest request, @ApiParam(value = "年份") @RequestParam(value = "year", required = false) String year) {
        //根据token获取登录用户Id
        String userId = UserUtil.getLoginUserId(request.getHeader("Authorization"));
        if (StringUtils.isBlank(userId)) {
            throw new SofnException("当前登录用户异常");
        }
        String token = UserUtil.getLoginToken();
        String userAreaByLogUser = UserUtil.getLoginUserOrganizationInfo();
        if (org.springframework.util.StringUtils.isEmpty(userAreaByLogUser)) {
            throw new SofnException("未获取到登录用户所属机构信息!");
        }
        SysOrganization sysOrganization = JsonUtils.json2obj(userAreaByLogUser, SysOrganization.class);
        String regioncode = sysOrganization.getRegioncode();
        //判断当前用户级别
        if (!RegionLevel.MINISTRY.getCode().equals(sysOrganization.getOrganizationLevel())) {
            throw new SofnException("当前用户不是部级用户！");
        }

        //若不输入年度,则取当前年的上一年度
        if (StringUtils.isEmpty(year) || StringUtils.isNumeric(year) == false) {
            year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR) - 1);
        }
        //获取数据
        List<String> status = new ArrayList<>();
        status.add(AuditStatusEnum.PASSED.getCode());
        status.add(AuditStatusEnum.REPORTED.getCode());
        //获取全国省份
        List<String> areaCodes = new ArrayList<>();
        //获取全国各省areacode
        //Result<List<SysRegionTreeVo>> regionTree = sysApi.getSysRegionTreeById("100000", "ducss");
        //获取已被审核通过的省级id
        areaCodes = collectFlowMapper.getProvinceListByCondition(Constants.ExamineState.PASSED.toString(), "5", year);
        List<Map<String, Object>> documents = new ArrayList<>();
        if (areaCodes != null) {
            Map<String, Object>[] maps = new HashMap[areaCodes.size()];
            for (int i = 0; i < areaCodes.size(); i++) {
                if (!areaCodes.get(i).equals(Constants.XINJIANG_CONSTRUCTION_CORPS)) {
                    maps[i] = provinceReportService.getProvinceReport(areaCodes.get(i), year);
                    documents.add(maps[i]);
                }
            }

        } else {
            throw new SofnException("当前无省级数据，请检查");
        }

        try {
            ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            Resource[] resources = resolver.getResources("static/everyProvince.docx");
            Resource resource = resources[0];
            //获得文件流，因为在jar文件中，不能直接通过文件资源路径拿到文件，但是可以在jar包中拿到文件流
            InputStream stream = resource.getInputStream();
            //Linux目录下的/usr/
            String tmpPath = formCallbackConfig.getDir() + File.separator + "部级报告_第五部分、分省区农作物秸秆产生与利用情况分析报告" + (int) (Math.random() * 100000) + ".docx";
//            String tmpPath = templateFolder + "everyPro" + (int) (Math.random() * 100000)  + ".docx";
            //临时文件保存 linux 下地址
            String targetPath = formCallbackConfig.getDir() + File.separator + year + "_" + "部级报告_第五部分、分省区农作物秸秆产生与利用情况分析报告" + ".docx";
//           String targetPath = templateFolder  + year + "_"+ "ministry_第五部分、分省区农作物秸秆产生与利用情况分析" + ".docx";
            File targetFile = new File(tmpPath);
            //将流读到目标文件中
            FileUtils.copyInputStreamToFile(stream, targetFile);
            XWPFTemplate.compile(tmpPath).render(new HashMap<String, Object>() {
                {
                    put("everyProvince", documents);
                }
            }).writeToFile(targetPath);
            //将生成的文件上传到文件服务器上
            File file = new File(targetPath);
            DiskFileItem fileItem = (DiskFileItem) new DiskFileItemFactory().createItem("file",
                    MediaType.MULTIPART_FORM_DATA_VALUE, true, file.getName());
            try (
                    InputStream input = new FileInputStream(file);
                    OutputStream os = fileItem.getOutputStream()) {
                IOUtils.copy(input, os);
            }
            MultipartFile multipartFile = new CommonsMultipartFile(fileItem);
            //调用支撑平台接口上传图片
            SysFileVo data = sysFileApi.uploadFile(multipartFile, token);
            if (targetFile.exists()) {
                targetFile.delete();
            }
            //激活
            if (null != data) {
                SysFileManageForm fileManageForm = new SysFileManageForm(data.getFileName(), "ducss", null, null, data.getId());
                //标注为报告文件
                fileManageForm.setBusinessFileType(Constants.REPORT_FILE);
                sysFileApi.activationFile(fileManageForm, token);
                ProReportForm form = new ProReportForm(data.getId(), data.getFilePath());
                if (file.exists()) {
                    file.delete();
                }
                return new Result<>(Result.CODE, form);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.error("部级报告分省区农作物秸秆产生与利用情况分析生成过程出现错误!");
    }


}
