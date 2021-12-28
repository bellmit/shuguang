package com.sofn.ducss.web;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sofn.ducss.clearlogicaldeletedata.properties.ClearLogicalDeleteDataProperties;
import com.sofn.ducss.fileutil.FileDownloadUtils;
import com.sofn.ducss.vo.SysFileManageVo;
import com.sofn.ducss.model.SysFileManageForm;
import com.sofn.ducss.model.basemodel.Result;
import com.sofn.ducss.service.SysFileManageService;
import com.sofn.ducss.util.IdUtil;
import com.sofn.ducss.util.PageUtils;
import com.sofn.ducss.util.shiro.JWTToken;
import com.sofn.ducss.vo.SysFileVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

/**
 * 文件管理接口
 *
 * @author heyongjie
 * @date 2019/6/6 11:19
 */
@RequestMapping("/fileManage")
@Api(tags = "文件管理接口")
@Controller
public class SysFileManageController extends BaseController {

    @Autowired
    private SysFileManageService sysFileManageService;

    @Autowired
    ClearLogicalDeleteDataProperties clearLogicalDeleteDataProperties;

    @GetMapping("/list")
    @ApiOperation(value = "按条件分页查询文件")
    @ResponseBody
    public Result<PageUtils<SysFileManageVo>> getSysFileByPage(@ApiParam(value = "从哪开始", required = true) @RequestParam(value = "pageNo") Integer pageNo,
                                                               @ApiParam(value = "显示多少条", required = true) @RequestParam(value = "pageSize") Integer pageSize,
                                                               @ApiParam(value = "文件名，模糊查询") @RequestParam(required = false) String fileName,
                                                               @ApiParam(value = "所属系统") @RequestParam(required = false) String systemId,
                                                               @ApiParam(value = "文件编号") @RequestParam(required = false) String fileNumber,
                                                               @ApiParam(value = "文件状态") @RequestParam(required = false) String fileState,
                                                               @ApiParam(value = "文件业务类型") @RequestParam(required = false) String businessFileType,
                                                               @ApiParam(value = "该参数无效，为不影响子系统，暂时保留") @RequestParam(required = false) String isSys,
                                                               @ApiParam(value = "操作用户名称") @RequestParam(required = false) String operatorName) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("fileName", fileName);
        params.put("systemId", systemId);
        params.put("operatorName", operatorName);
        params.put("fileNumber", fileNumber);
        params.put("fileState", fileState);
        params.put("businessFileType", businessFileType);
        PageUtils<SysFileManageVo> pageUtils = sysFileManageService.getSysFileByConditionAndPage(params, pageNo, pageSize);
        return Result.ok(pageUtils);
    }


    @GetMapping("/replaceFile")
    @ApiOperation(value = "替换文件，不需要调用激活文件，但是替换之前需要先调用上传文件获取newFileId")
    @ResponseBody
    public Result<String> replaceFile(
            @ApiParam(value = "要替换的文件ID", required = true) @RequestParam("id") String id,
            @ApiParam(value = "文件名称") @RequestParam(value = "filename", required = false) String filename,
            @ApiParam(value = "备注") @RequestParam(value = "remark", required = false) String remark,
            @ApiParam(value = "新文件Id", required = true) @RequestParam("newFileId") String newFileId,
            @RequestHeader(value = JWTToken.TOKEN, required = false) @ApiParam(required = false) String token
    ) {
        sysFileManageService.replaceFile(id, filename, remark, newFileId, token);
        return Result.ok();
    }


    @PostMapping("/deleteFile")
    @ApiOperation(value = "根据ID删除文件")
    @ResponseBody
    public Result<String> delFile(@ApiParam("要删除的文件ID") @RequestParam("id") String id,
                                  @RequestHeader(value = JWTToken.TOKEN, required = false) @ApiParam(required = false) String token) {
        sysFileManageService.deleteFile(id, token);
        return Result.ok();
    }

    @PostMapping("/showRecords")
    @ApiOperation(value = "根据ID查看文件变更记录")
    @ResponseBody
    public Result<Object> showRecords(@ApiParam("要查看的文件ID") @RequestParam("id") String id) {
        Object result = sysFileManageService.showRecords(id);
        return Result.ok(result);
    }

    @GetMapping("/getFileRealPath/{id}")
    @ApiOperation(value = "获取文件绝对路径")
    @ResponseBody
    public Result<String> getFileRealPath(@PathVariable("id") String id) {
        String realPath = sysFileManageService.getFileRealPath(id);
        return Result.ok((Object) realPath);
    }

    @PostMapping(value = "/uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "上传文件，上传成功后需要调用激活文件接口才有效，否则文件会定期清理，大文件上传端口后加前缀/zuul/")
    @ResponseBody
    public Result<SysFileVo> uploadFile(@ApiParam(value = "文件") @RequestPart(value = "file") MultipartFile file,
                                        @RequestHeader(value = JWTToken.TOKEN, required = false) @ApiParam(required = false) String token) {
        System.out.println(token);
        SysFileVo sysFileVo = sysFileManageService.uploadFile(file, token);
        return Result.ok(sysFileVo);
    }

    @PostMapping("/activationFile")
    @ApiOperation(value = "激活文件")
    @ResponseBody
    public Result<List<SysFileVo>> activationFile(@Validated @RequestBody @ApiParam(name = "添加文件对象", value = "传入json格式", required = true)
                                                          SysFileManageForm sysFileManageForm, BindingResult result,
                                                  @RequestHeader(value = JWTToken.TOKEN, required = false) @ApiParam(required = false) String token) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        List<SysFileVo> sysFileVos = sysFileManageService.activationFile(sysFileManageForm, token);
        return Result.ok(sysFileVos);
    }


    @PostMapping("/batchUploadFile")
    @ApiOperation(value = "批量上传文件，需要调用激活文件接口")
    @ResponseBody
    public Result<List<SysFileVo>> batchUploadFile(@ApiParam(value = "文件集合") @RequestParam(value = "files") MultipartFile[] files,
                                                   @RequestHeader(value = JWTToken.TOKEN, required = false) @ApiParam(required = false) String token) {
        List<SysFileVo> sysFileVoList = Lists.newArrayList();
        if (files != null && files.length != 0) {
            for (MultipartFile file : files) {
                SysFileVo sysFileVo = sysFileManageService.uploadFile(file, token);
                sysFileVoList.add(sysFileVo);
            }
        }
        return Result.ok(sysFileVoList);
    }

    @GetMapping("/getOne/{id}")
    @ApiOperation(value = "获取某一个文件信息")
    @ResponseBody
    public Result<SysFileManageVo> getOne(@PathVariable("id") String id) {
        return Result.ok(sysFileManageService.getOne(id));
    }


    @GetMapping(value = "/downloadFile/{id}", produces = "application/octet-stream")
    @ApiOperation(value = "根据ID下载文件", produces = "application/octet-stream")
    public void downloadFile(@PathVariable(value = "id") String id, HttpServletResponse response, HttpServletRequest request,
                             @RequestHeader(value = JWTToken.TOKEN, required = false) @ApiParam(required = false) String token) throws IOException {
        try {
            Map<String, String> resultInfo = sysFileManageService.downloadFile(id, token);
            String fileInfo = resultInfo.get("fileInfo");
            String fileName = resultInfo.get("fileName");
            FileDownloadUtils.downloadFile(fileName, Base64.decodeBase64(fileInfo), response);
        } catch (Exception e) {
            response.setStatus(500);
            PrintWriter writer = response.getWriter();
            writer.write("文件下载失败");
        }
    }


    @GetMapping(value = "/batchDownloadFile/{ids}", produces = "application/octet-stream")
    @ApiOperation(value = "批量下载文件，得到的是一个压缩包", produces = "application/octet-stream")
    public void batchDownloadFile(@PathVariable("ids") String ids, HttpServletResponse response,
                                  @RequestParam(required = false) @ApiParam(required = false, value = "文件名称，后缀不要穿，默认为.zip，如果为空，默认为（批量下载文件yyyyMMddHHmmss.zip）") String fileName,
                                  @RequestHeader(value = JWTToken.TOKEN, required = false) @ApiParam(required = false) String token) throws IOException {
        try {
            String zipFilePath = sysFileManageService.batchDownloadFileByZip(ids, token, fileName);
            FileDownloadUtils.downloadAndDeleteFile(zipFilePath, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(500);
            PrintWriter writer = response.getWriter();
            writer.write("文件下载失败");
        }
    }

    @GetMapping(value = "/batchDeleteFile/{ids}")
    @ApiOperation(value = "批量删除文件")
    @ResponseBody
    public Result<String> batchDeleteFile(@PathVariable("ids") @ApiParam(value = "多个ID使用,隔开") String ids,
                                          @RequestHeader(value = JWTToken.TOKEN, required = false) @ApiParam(required = false) String token) {
        sysFileManageService.batchDelete(IdUtil.getIdsByStr(ids), token);
        return Result.ok("删除成功");
    }


    @GetMapping(value = "/batchGetFileInfo/{ids}")
    @ApiOperation(value = "批量获取文件信息")
    @ResponseBody
    public Result<List<SysFileManageVo>> batchGetFileInfo(@PathVariable("ids") @ApiParam(value = "多个ID使用,隔开") String ids) {
        return Result.ok(sysFileManageService.getFileInfoByIds(IdUtil.getIdsByStr(ids)));
    }

    @GetMapping(value = "/deleteNotActivationFile")
    @ApiOperation(value = "删除未激活的文件")
    @ResponseBody
    public Result<String> deleteNotActivationFile(@ApiParam(value = "多少天以前的数据") @RequestParam(value = "beforeDays") Integer beforeDays) {
        if (clearLogicalDeleteDataProperties == null && beforeDays == null) {
            Result.error("删除‘以前天数’为空");
        }

        if (beforeDays == null) {
            beforeDays = clearLogicalDeleteDataProperties.getDatAgo();
        }

        sysFileManageService.deleteNotActivationFile(beforeDays);
        return Result.ok("删除成功");
    }

    @GetMapping(value = "/updateFileName")
    @ApiOperation(value = "修改文件名称")
    @ResponseBody
    public Result<String> updateFileName(@ApiParam(value = "文件ID", required = true) @RequestParam("id") String id,
                                         @ApiParam(value = "文件名称", required = true) @RequestParam("newFileName") String newFileName,
                                         @RequestHeader(value = JWTToken.TOKEN, required = false) @ApiParam(required = false) String token) {
        sysFileManageService.updateFileName(id, newFileName, token);
        return Result.ok();
    }

    @PostMapping("/updateFileInfo")
    @ApiOperation(value = "更新文件信息")
    @ResponseBody
    public Result<String> updateFileInfo(@Validated @RequestBody @ApiParam(name = "修改文件对象", value = "传入json格式", required = true) SysFileManageForm sysFileManageForm, BindingResult result,
                                         @RequestHeader(value = JWTToken.TOKEN, required = false) @ApiParam(required = false) String token) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        sysFileManageService.updateFileInfo(sysFileManageForm, token);
        return Result.ok("更新成功！");
    }

}
