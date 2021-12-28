package com.sofn.ducss.web;

import com.deepoove.poi.XWPFTemplate;
import com.google.common.collect.Lists;
import com.sofn.common.exception.SofnException;
import com.sofn.common.fileutil.SysFileManageVo;
import com.sofn.common.model.Result;
import com.sofn.common.utils.JsonUtils;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.ducss.config.FormCallbackConfig;
import com.sofn.ducss.enums.Constants;
import com.sofn.ducss.enums.RegionLevel;
import com.sofn.ducss.service.ProvinceReportService;
import com.sofn.ducss.sysapi.SysFileApi;
import com.sofn.ducss.sysapi.bean.SysFileManageForm;
import com.sofn.ducss.sysapi.bean.SysFileVo;
import com.sofn.ducss.sysapi.bean.SysOrganization;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.xiaoleilu.hutool.util.ClassUtil.getClassLoader;

/**
 * @ClassName ProvinceReportController
 * @Description
 * @Author Chlf
 * @Date 2020/11/30 14:52
 * Version1.0
 **/
@Slf4j
@Api(value = "省级报告生成", tags = "根据省级用户模板生成报告")
@RestController
@RequestMapping("/provinceReportManager")
public class ProvinceReportController {
    @Autowired
    private SysFileApi sysFileApi;
    @Autowired
    private ProvinceReportService provinceReportService;
    //临时文件存放,本地调试使用
    private static final String templateFolder = getClassLoader().getResource("static/").getPath();
    @Autowired
    private FormCallbackConfig formCallbackConfig;
//    @Autowired
//    private SysApi sysApi;

    @ApiOperation(value = "省级报告生成【省级用户使用】")
    @PostMapping(value = "/exportReport")
    public Result<ProReportForm> exportReport(HttpServletRequest request, @ApiParam(value = "年份") @RequestParam(value = "year", required = false) String year,
                                              @ApiParam(value = "省级名称", required = true) @RequestParam(value = "regionNames") String regionNames) {
        //根据token获取登录用户Id
        String userId = UserUtil.getLoginUserId(request.getHeader("Authorization"));
        if (StringUtils.isBlank(userId)) {
            throw new SofnException("当前登录用户异常");
        }
        String userAreaByLogUser = UserUtil.getLoginUserOrganizationInfo();
        if (org.springframework.util.StringUtils.isEmpty(userAreaByLogUser)) {
            throw new SofnException("未获取到登录用户所属机构信息!");
        }
        String token = UserUtil.getLoginToken();
        SysOrganization sysOrganization = JsonUtils.json2obj(userAreaByLogUser, SysOrganization.class);
        String regioncode = sysOrganization.getRegioncode();
        List<String> areaList = JsonUtils.json2List(regioncode, String.class);
        String provinceId = "";
        //判断当前用户级别
        if (RegionLevel.PROVINCE.getCode().equals(sysOrganization.getOrganizationLevel())) {
            provinceId = areaList.get(0);
        }
        Map<String, Object> map = provinceReportService.getProvinceReport(provinceId, year);

        try {
            //URL url = this.getClass().getClassLoader().getResource("static/ducss_pro.docx");
            //ClassPathResource classPathResource = new ClassPathResource("static/ducss_pro.docx");
            //log.error("pr", classPathResource);
            //InputStream initialStream = this.getClass().getResourceAsStream("static/ducss_pro.docx");
            ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            Resource[] resources = resolver.getResources("static/ducss_pro.docx");
            Resource resource = resources[0];
            //获得文件流，因为在jar文件中，不能直接通过文件资源路径拿到文件，但是可以在jar包中拿到文件流
            InputStream stream = resource.getInputStream();
            //Linux目录下的/usr/
            String tmpPath = formCallbackConfig.getDir() + File.separator + "ProReport_" + provinceId + ".docx";
            //String tmpPath = templateFolder + File.separator + "temporary" +provinceId+".docx";
            //临时文件保存
            //String targetPath = templateFolder + File.separator + "ProReport_" +provinceId+".docx";
            //获取省名称
            //String provinceName = sysApi.getSysRegionName(provinceId).getData();
            String targetPath = formCallbackConfig.getDir() + File.separator + year + "_" + regionNames + "_" + provinceId + ".docx";
            File targetFile = new File(tmpPath);
            //将流读到目标文件中
            FileUtils.copyInputStreamToFile(stream, targetFile);
            //此时获取到的即为存在的文档
            //XWPFDocument doc = WordExportUtil.exportWord07(tmpPath, map);
            //FileOutputStream fos = new FileOutputStream(tmpPath);
            //doc.write(fos);
            XWPFTemplate.compile(tmpPath).render(map).writeToFile(targetPath);
            //fos.close();

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
            SysFileVo data = sysFileApi.uploadFile(multipartFile, UserUtil.getLoginToken()).getData();

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
                //删除原来系统中存在的同一年度的报告
                //provinceReportService.deleteOldProReport(provinceId, year);
                return new Result<>(Result.CODE, form);
            }

            return Result.error("省级报告生成有误!");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }

    @GetMapping("/list")
    @ApiOperation(value = "省级报告列表(支撑平台觉得改的多,手动分页)")
    @ResponseBody
    public Result<PageUtils<SysFileManageVo>> getSysFileByPage(@ApiParam(value = "从哪开始", required = true) @RequestParam(value = "pageNo") Integer pageNo,
                                                               @ApiParam(value = "显示多少条", required = true) @RequestParam(value = "pageSize") Integer pageSize,
                                                               @ApiParam(value = "文件名，模糊查询") @RequestParam(required = false) String fileName) {
        // 以下代码并非自己想写，手动分页
        List<String> fileNames = Lists.newArrayList();
        if (StringUtils.isNotEmpty(fileName)) {
            fileNames = Arrays.asList(fileName.split(","));
        }
        // 查询所有
        List<SysFileManageVo> all = Lists.newArrayList();
        for (String name : fileNames) {
            Result<PageUtils<SysFileManageVo>> resultInfo = sysFileApi.getSysFileByPage(0, 999, name, "ducss", null, null, null, "Y", null);
            if (resultInfo != null && resultInfo.getData() != null) {
                all.addAll(resultInfo.getData().getList());
            }
        }
        // 手动分页
        int startIndex = pageNo;
        int endIndex = startIndex + pageSize;
        if (endIndex > all.size()) {
            endIndex = all.size();
        }
        List<SysFileManageVo> result = Lists.newArrayList();
        if (all.size() - 1 >= startIndex) {
            result = all.subList(startIndex, endIndex);
        }
        PageUtils<SysFileManageVo> utils = new PageUtils<>(result, all.size(), pageSize, pageNo + 1);
        return Result.ok(utils);
    }

}
