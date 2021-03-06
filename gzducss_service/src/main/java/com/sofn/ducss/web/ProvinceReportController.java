package com.sofn.ducss.web;

import com.deepoove.poi.XWPFTemplate;
import com.sofn.ducss.exception.SofnException;
import com.sofn.ducss.model.SysOrganization;
import com.sofn.ducss.model.basemodel.Result;
import com.sofn.ducss.sysapi.SysFileApi;
import com.sofn.ducss.model.SysFileManageForm;
import com.sofn.ducss.vo.SysFileVo;
import com.sofn.ducss.util.JsonUtils;
import com.sofn.ducss.util.UserUtil;
import com.sofn.ducss.config.FormCallbackConfig;
import com.sofn.ducss.enums.Constants;
import com.sofn.ducss.enums.RegionLevel;
import com.sofn.ducss.service.ProvinceReportService;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
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
@Api(value = "??????????????????", tags = "????????????????????????????????????")
@RestController
@RequestMapping("/provinceReportManager")
public class ProvinceReportController {
    @Autowired(required = false)
    private SysFileApi sysFileApi;
    @Autowired
    private ProvinceReportService provinceReportService;
    //??????????????????,??????????????????
    private static final String templateFolder = getClassLoader().getResource("static/").getPath();
    @Autowired
    private FormCallbackConfig formCallbackConfig;
//    @Autowired
//    private SysApi sysApi;

    @ApiOperation(value = "??????????????????????????????????????????")
    @PostMapping(value = "/exportReport")
    public Result<ProReportForm> exportReport(HttpServletRequest request, @ApiParam(value = "??????") @RequestParam(value = "year", required = false) String year,
                                              @ApiParam(value = "????????????", required = true) @RequestParam(value = "regionNames") String regionNames) {
        //??????token??????????????????Id
        String userId = UserUtil.getLoginUserId(request.getHeader("Authorization"));
        if (StringUtils.isBlank(userId)) {
            throw new SofnException("????????????????????????");
        }
        String userAreaByLogUser = UserUtil.getLoginUserOrganizationInfo();
        if (org.springframework.util.StringUtils.isEmpty(userAreaByLogUser)) {
            throw new SofnException("??????????????????????????????????????????!");
        }
        String token = UserUtil.getLoginToken();
        SysOrganization sysOrganization = JsonUtils.json2obj(userAreaByLogUser, SysOrganization.class);
        String regioncode = sysOrganization.getRegioncode();
        List<String> areaList = JsonUtils.json2List(regioncode, String.class);
        String provinceId = "";
        //????????????????????????
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
            //???????????????????????????jar??????????????????????????????????????????????????????????????????????????????jar?????????????????????
            InputStream stream = resource.getInputStream();
            //Linux????????????/usr/
            String tmpPath = formCallbackConfig.getDir() + File.separator + "ProReport_" + provinceId + ".docx";
            //String tmpPath = templateFolder + File.separator + "temporary" +provinceId+".docx";
            //??????????????????
            //String targetPath = templateFolder + File.separator + "ProReport_" +provinceId+".docx";
            //???????????????
            //String provinceName = sysApi.getSysRegionName(provinceId).getData();
            String targetPath = formCallbackConfig.getDir() + File.separator + year + "_" + regionNames + "_" + provinceId + ".docx";
            File targetFile = new File(tmpPath);
            //???????????????????????????
            FileUtils.copyInputStreamToFile(stream, targetFile);
            //???????????????????????????????????????
            //XWPFDocument doc = WordExportUtil.exportWord07(tmpPath, map);
            //FileOutputStream fos = new FileOutputStream(tmpPath);
            //doc.write(fos);
            XWPFTemplate.compile(tmpPath).render(map).writeToFile(targetPath);
            //fos.close();

            //?????????????????????????????????????????????
            File file = new File(targetPath);
            DiskFileItem fileItem = (DiskFileItem) new DiskFileItemFactory().createItem("file",
                    MediaType.MULTIPART_FORM_DATA_VALUE, true, file.getName());
            try (
                    InputStream input = new FileInputStream(file);
                    OutputStream os = fileItem.getOutputStream()) {
                IOUtils.copy(input, os);
            }
            MultipartFile multipartFile = new CommonsMultipartFile(fileItem);

            //????????????????????????????????????
            SysFileVo data = sysFileApi.uploadFile(multipartFile, token);

            if (targetFile.exists()) {
                targetFile.delete();
            }
            //??????
            if (null != data) {
                SysFileManageForm fileManageForm = new SysFileManageForm(data.getFileName(), "ducss", null, null, data.getId());
                //?????????????????????
                fileManageForm.setBusinessFileType(Constants.REPORT_FILE);
                sysFileApi.activationFile(fileManageForm, token);
                ProReportForm form = new ProReportForm(data.getId(), data.getFilePath());
                //???????????????????????????????????????????????????
                //provinceReportService.deleteOldProReport(provinceId, year);
                return new Result<>(Result.CODE, form);
            }

            return Result.error("????????????????????????!");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }
}
