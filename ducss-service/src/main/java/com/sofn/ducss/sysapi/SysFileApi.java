package com.sofn.ducss.sysapi;

import com.sofn.common.config.FeignConfiguration;
import com.sofn.common.fileutil.SysFileManageVo;
import com.sofn.common.model.Result;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.shiro.JWTToken;
import com.sofn.ducss.sysapi.bean.SysFileManageForm;
import com.sofn.ducss.sysapi.bean.SysFileVo;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 使用Feign 调用sys服务
 *
 * @author simon
 */
@FeignClient(
        value = "sys-service",
        configuration = FeignConfiguration.class
)
public interface SysFileApi {

    /**
     * 上传文件
     *
     * @return Result
     */
    @PostMapping(value = "/fileManage/uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = "application/json; charset=utf-8")
    Result<SysFileVo> uploadFile(@ApiParam(value = "文件") @RequestPart(value = "file") MultipartFile file,
                                 @RequestHeader(value = JWTToken.TOKEN, required = false) @ApiParam(required = false) String token);


    /**
     * 激活文件
     *
     * @param sysFileManageForm
     * @return
     */
    @PostMapping(value = "/fileManage/activationFile", consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<List<SysFileVo>> activationFile(
            @RequestBody @ApiParam(name = "添加文件对象", value = "传入json格式", required = true) SysFileManageForm sysFileManageForm,
            @RequestHeader(value = JWTToken.TOKEN, required = false) @ApiParam(required = false) String token);

    /**
     * 删除文件
     *
     * @param id
     * @return
     */
    @PostMapping("/fileManage/deleteFile")
    Result<String> delFile(@ApiParam("要删除的文件ID") @RequestParam("id") String id);


    @GetMapping("/fileManage/getOne/{id}")
    Result<SysFileManageVo> getOne(@PathVariable("id") String id);

    @GetMapping("/fileManage/list")
    Result<PageUtils<SysFileManageVo>> getSysFileByPage(@ApiParam(value = "从哪开始", required = true) @RequestParam(value = "pageNo") Integer pageNo,
                                                        @ApiParam(value = "显示多少条", required = true) @RequestParam(value = "pageSize") Integer pageSize,
                                                        @ApiParam(value = "文件名，模糊查询") @RequestParam(required = false) String fileName,
                                                        @ApiParam(value = "所属系统") @RequestParam(required = false) String systemId,
                                                        @ApiParam(value = "文件编号") @RequestParam(required = false) String fileNumber,
                                                        @ApiParam(value = "文件状态") @RequestParam(required = false) String fileState,
                                                        @ApiParam(value = "文件业务类型") @RequestParam(required = false) String businessFileType,
                                                        @ApiParam(value = "该参数无效，为不影响子系统，暂时保留") @RequestParam(required = false) String isSys,
                                                        @ApiParam(value = "操作用户名称") @RequestParam(required = false) String operatorName);

    /**
     * 批量获取文件信息
     */
    @GetMapping(value = "/batchGetFileInfo/{ids}")
    Result<List<SysFileManageVo>> batchGetFileInfo(@PathVariable("ids") @ApiParam(value = "多个ID使用,隔开") String ids);

}
