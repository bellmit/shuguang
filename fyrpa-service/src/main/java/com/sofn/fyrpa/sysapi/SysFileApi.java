package com.sofn.fyrpa.sysapi;


import com.sofn.common.config.FeignConfiguration;
import com.sofn.common.model.Result;
import com.sofn.common.utils.shiro.JWTToken;
import com.sofn.fyrpa.sysapi.bean.SysFileManageForm;
import com.sofn.fyrpa.sysapi.bean.SysFileManageVo;
import com.sofn.fyrpa.sysapi.bean.SysFileVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Description: 用于文件激活相关
 * @Author: mcc
 * @Date: 2020\2\26 0026
 */
@FeignClient(
        value = "sys-service",
        configuration = FeignConfiguration.class
)
public interface SysFileApi {


    /**
     * 激活文件
     * @param sysFileManageForm
     * @return
     */
    @PostMapping(value ="/fileManage/activationFile", consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<List<SysFileVo>> activationFile(
            @RequestBody @ApiParam(name = "添加文件对象", value = "传入json格式", required = true)
                    SysFileManageForm sysFileManageForm);


    /**
     * 批量删除支撑平台中的文件
     * @param ids
     * @return
     */
    @GetMapping(value = "/fileManage/batchDeleteFile/{ids}")
    Result batchDeleteFile(@PathVariable("ids") @ApiParam(value = "多个ID使用,隔开") String ids);

    /**
     * 批量获取文件信息
     * @param ids
     * @return
     */
    @GetMapping(value = "/fileManage/batchGetFileInfo/{ids}")
    Result<List<SysFileManageVo>> batchGetFileInfo(@PathVariable("ids") @ApiParam(value = "多个ID使用,隔开") String ids);

    @GetMapping(value = "/fileManage/downloadFile/{id}", produces = "application/octet-stream")
    @ApiOperation(value = "根据ID下载文件", produces = "application/octet-stream")
    void downloadFile(@PathVariable(value = "id") String id, HttpServletResponse response,
                      @RequestHeader(value = JWTToken.TOKEN, required = false) @ApiParam(required = false) String token) throws IOException;


    /**
     * 通过ID获取文件
     * @param id
     * @return
     */
    @GetMapping(value = "/fileManage/getOne/{id}")
    Result<SysFileManageVo> getOne(@PathVariable("id") String id);
}



