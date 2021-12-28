package com.sofn.agpjpm.sysapi;


import com.sofn.common.config.FeignConfiguration;
import com.sofn.common.fileutil.SysFileManageVo;
import com.sofn.common.model.Result;
import com.sofn.agpjpm.vo.SysFileManageForm;
import com.sofn.agpjpm.vo.SysFileVo;

import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 使用Feign 调用sys服务
 */
@FeignClient(value = "sys-service", configuration = FeignConfiguration.class)
public interface SysFileApi {


    /**
     * 上传文件，调用sys服务接口
     *
     * @return Result
     */
    @PostMapping(value = "/fileManage/uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Result<SysFileVo> uploadFile(
            @ApiParam(value = "文件名") @RequestParam(value = "fileName", required = false) String fileName,
            @ApiParam(value = "备注") @RequestParam(value = "remark", required = false) String remark,
            @ApiParam(value = "所属系统", required = true) @RequestParam("parentId") String parentId,
            @ApiParam(value = "接口号，默认为hidden，请注意所有hidden开头的接口号都不会在文件管理中显示")
            @RequestParam(value = "interfaceNum", defaultValue = "hidden") String interfaceNum,
            @ApiParam(value = "文件") @RequestPart(value = "file") MultipartFile file);


    /**
     * 下载文件，调用sys服务接口
     *
     * @return Result
     */
    @PostMapping("/fileManage/downloadFile/{id}")
    void downloadFile(@ApiParam(value = "要下载的文件ID", required = true) @PathVariable(value = "id") String id, HttpServletResponse response);

    /**
     * 激活文件
     */
    @PostMapping(value = "/fileManage/activationFile", consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<List<SysFileVo>> activationFile(@RequestBody @ApiParam(name = "添加文件对象", value = "传入json格式", required = true)
                                                   SysFileManageForm sysFileManageForm);

    /**
     * 替换文件，不需要调用激活文件，但是替换之前需要先调用上传文件获取newFileId
     */
    @GetMapping("/fileManage/replaceFile")
    Result replaceFile(
            @ApiParam(value = "要替换的文件ID", required = true) @RequestParam("id") String id,
            @ApiParam(value = "备注") @RequestParam(value = "remark", required = false) String remark,
            @ApiParam(value = "新文件Id", required = true) @RequestParam("newFileId") String newFileId
    );

    /**
     * 删除支撑平台中的文件
     *
     * @param id：文件id
     * @return
     */
    @PostMapping("/fileManage/deleteFile")
    Result delFile(@ApiParam("要删除的文件ID") @RequestParam("id") String id);

    /**
     * 批量删除支撑平台中的文件
     *
     * @param ids
     * @return
     */
    @GetMapping(value = "/fileManage/batchDeleteFile/{ids}")
    Result batchDeleteFile(@PathVariable("ids") @ApiParam(value = "多个ID使用,隔开") String ids);
    @GetMapping("/fileManage/getOne/{id}")
    Result<SysFileManageVo> getOne(@PathVariable("id") String id);
    @GetMapping("/sysRegion/getSysRegionName/{id}")
    Result<String> getSysRegionName(@PathVariable(value = "id")String id);
}
