package com.sofn.web.dgap;

import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.authorization.annotation.Authorization;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.HttpCode;
import com.sofn.model.dgap.AetlTransformation;
import com.sofn.model.generator.TDgapResource;
import com.sofn.service.dgap.TDgapEtlService;
import com.sofn.service.dgap.TDgapResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/** */
@RestController
@Api(value = "ETL任务", description = "ETL任务")
@RequestMapping(value = "/etl-transform")
public class TDgapEtlTransformController extends BaseController {

    @Autowired
    private TDgapEtlService tDgapEtlService;

    @Autowired
    private TDgapResourceService tDgapResourceService;

    @ApiOperation(value = "ETL分页列表")
    @RequestMapping(value = "/list")
    @SystemControllerLog(description = "(共享系统)查询ETL列表", operationType = "查询")
    @Authorization
    public Object listTransform(String resourceName, String transName, int start, int length)
            throws Exception {
        if(resourceName.length()>60){
            return  setModelMap(new ModelMap(),HttpCode.BAD_REQUEST);
        }
        if(transName.length()>60){
            return  setModelMap(new ModelMap(),HttpCode.BAD_REQUEST);
        }
        PageInfo<AetlTransformation> pageInfo;
        // 根据resourceName查询出所有的resourceList
        List<TDgapResource> resources = tDgapResourceService.getResourceByName(resourceName);
        if (resources.size() > 0) {
            pageInfo = tDgapEtlService.listTrans(transName, start, length, resources);
            return setSuccessModelMap(new ModelMap(), pageInfo);
        }
        pageInfo = new PageInfo<>();
        pageInfo.setPageNum(0);
        pageInfo.setPageSize(length);
        pageInfo.setTotal(0);
        pageInfo.setList(new ArrayList(0));
        return setSuccessModelMap(new ModelMap(), pageInfo);
    }

    @ApiOperation(value = "添加etl转换")
    @RequestMapping(value = "/add")
    @SystemControllerLog(description = "(共享系统)添加转换", operationType = "添加")
    @Authorization
    public Object addTransform(HttpServletRequest request) throws Exception {
        String resourceId = request.getParameter("resourceId");
        if (!isLegal(resourceId)) {
            return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);
        }
        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            MultipartFile file = multipartRequest.getFile("file");
            File tmpFile = File.createTempFile("tmp", null);
            file.transferTo(tmpFile);
            String transConetnt = FileUtils.readFileToString(tmpFile, "UTF-8");
            String fileName = file.getOriginalFilename();
            String transName = fileName.substring(0, fileName.lastIndexOf("."));
            boolean isSucc = tDgapEtlService.addTrans(resourceId, transConetnt, transName);
            if (isSucc) {
                return setModelMap(new ModelMap(), HttpCode.OK, "保存成功");
            }
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return setFailModelMap(new ModelMap(), e.getMessage());
        }
    }

    @ApiOperation(value = "删除etl转换")
    @RequestMapping(value = "/delete")
    @SystemControllerLog(description = "(共享系统)删除转换", operationType = "删除")
    @Authorization
    public Object deleteTransform(String resourceId, String transName) throws Exception {
        if (!isLegal(resourceId, transName)) {
            return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);
        }
        boolean isSucc = tDgapEtlService.deleteTrans(resourceId, transName);
        if (!isSucc) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
        return setModelMap(new ModelMap(), HttpCode.OK, "删除成功");
    }

    @ApiOperation(value = "批量删除etl转换")
    @RequestMapping(value = "/deleteAll")
    @SystemControllerLog(description = "(共享系统)批量删除转换", operationType = "删除")
    @Authorization
    public Object batchDeleteTransform(String resourceId) throws Exception {
        if (!isLegal(resourceId)) {
            return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);
        }
        String[] split = resourceId.split("\\|");  //这里包含resourceId和transName
        boolean deleteTransform = tDgapEtlService.batchDeleteTransform(split);
        if (true == deleteTransform) {
            return setModelMap(new ModelMap(), HttpCode.OK, "删除成功");
        } else return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR, "删除失败");
    }

    @ApiOperation(value = "更新etl转换")
    @RequestMapping(value = "/update")
    @SystemControllerLog(description = "(共享系统)更新", operationType = "更新")
    @Authorization
    public Object updateTransform(HttpServletRequest request) throws Exception {
        // 直接调用部署即可
        return addTransform(request);
    }

    @ApiOperation(value = "部署etl转换任务")
    @RequestMapping(value = "/deploy")
    @SystemControllerLog(description = "(共享系统)部署转换", operationType = "部署")
    @Authorization
    public Object deployTransform(String resourceId, String transName) throws Exception {
        if (!isLegal(resourceId, transName)) {
            return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);
        }
        boolean isSucc = tDgapEtlService.deployTrans(resourceId, transName);
        if (!isSucc) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
        return setModelMap(new ModelMap(), HttpCode.OK, "部署成功");
    }

    @ApiOperation(value = "卸载etl转换任务")
    @RequestMapping(value = "/undeploy")
    @SystemControllerLog(description = "(共享系统)卸载", operationType = "卸载")
    @Authorization
    public Object undeployTransform(String resourceId, String transName) throws Exception {
        if (!isLegal(resourceId, transName)) {
            return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);
        }
        boolean isSucc = tDgapEtlService.unDeployTrans(resourceId, transName);
        if (!isSucc) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
        return setModelMap(new ModelMap(), HttpCode.OK, "卸载成功");
    }

    @ApiOperation(value = "设置是否调度")
    @RequestMapping(value = "/setScheduleStatus")
    @SystemControllerLog(description = "(共享系统)调度", operationType = "调度")
    @Authorization
    public Object setScheduleStatus(String resourceId, String transName, String enable) throws Exception {
        boolean isSucc = tDgapEtlService.setScheduleStatus(resourceId, transName, enable);
        if (isSucc) {
            return setModelMap(new ModelMap(), HttpCode.OK, "设置成功");
        }
        return setModelMap(new ModelMap(), HttpCode.OK, "设置失败");
    }


    @ApiOperation(value = "立即执行调度")
    @RequestMapping(value = "/runSchedule")
    @SystemControllerLog(description = "(共享系统)立即执行调度", operationType = "调度")
    @Authorization
    public Object runSchedule(String resourceId, String transName, String resourceName) throws Exception {
        boolean result = tDgapEtlService.execTrans(resourceId, transName, resourceName);

        if (result) {
            return setModelMap(new ModelMap(), HttpCode.OK, "操作成功");
        } else
            return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST, "操作失败");

    }


    @ApiOperation(value = "按名字级联查询资源目录列表")
    @RequestMapping(value = "/relist")
    @SystemControllerLog(description = "(共享系统)按名字查询资源列表", operationType = "查询")
    public Object lists() {
        List<TDgapResource> list = tDgapResourceService.getResourceList();
        return setSuccessModelMap(new ModelMap(), list);
    }


    @ApiOperation(value = "按名字级联查询模板列表")
    @RequestMapping(value = "/readlist")
    @SystemControllerLog(description = "(共享系统)按名字级联查询模板列表", operationType = "查询")
    public Object readlist() {

        File file = tDgapEtlService.readlist("ktr-templates");

        List<TDgapResource> list = new ArrayList<>();

        if (file.exists()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File childFile : files) {
                    TDgapResource tDgapResource = new TDgapResource();
                    tDgapResource.setId(childFile.getName());
                    tDgapResource.setName(childFile.getName());
                    list.add(tDgapResource);
                }
            }
        }

        return setSuccessModelMap(new ModelMap(), list);
    }

    @ApiOperation(value = "根据模板添加etl转换")
    @RequestMapping(value = "/addRead")
    @SystemControllerLog(description = "(共享系统)根据模板添加etl转换", operationType = "添加")
    @Authorization
    public Object addReadTransform(HttpServletRequest request) throws Exception {
        String resourceId = request.getParameter("resourceId");
        if (!isLegal(resourceId)) {
            return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);
        }

        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(4096);
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setFileSizeMax(4191304);

        File file = tDgapEtlService.readlist("ktr-templates");
        InputStream inputStream = null;
        String readName = request.getParameter("readName");
        if (file.exists()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File childFile : files) {
                    if (readName.equals(childFile.getName())) {
                        inputStream = new FileInputStream(childFile);
                    }
                }
            }
        }

        try {
            // 根据inputStream读取里面的内容
            String transformXml = getFileContent(inputStream);
            String transName = readName.substring(0, readName.lastIndexOf("."));
            boolean isSucc = tDgapEtlService.addTrans(resourceId, transformXml, transName);
            if (!isSucc) {
                return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
            }
            return setModelMap(new ModelMap(), HttpCode.OK, "保存成功");
        } catch (Exception e) {
            return setFailModelMap(new ModelMap(), e.getMessage());
        }
    }


    /**
     * 判断输入参数是否异常
     *
     * @param args
     * @return
     */
    private boolean isLegal(String... args) {
        for (String arg : args) {
            if (null == arg || "".equals(arg)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 根据上传的文件流读取里面的内容
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    private String getFileContent(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuffer sb = new StringBuffer();
        String line = "";
        while (null != (line = reader.readLine())) {
            sb.append(line);
        }
        return sb.toString();
    }
}
