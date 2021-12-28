package com.sofn.fyem.sysapi;

import com.sofn.common.config.FeignConfiguration;
import com.sofn.common.model.Result;
import com.sofn.common.utils.shiro.JWTToken;
import com.sofn.fyem.sysapi.bean.SysFileManageForm;
import com.sofn.fyem.sysapi.bean.SysFileManageVo;
import com.sofn.fyem.sysapi.bean.SysFileVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Description: 用于文件上传、下载和激活
 * @Author: DJH
 * @Date: 2020\4\29
 */
@FeignClient(
        value = "sys-service",
        configuration = FeignConfiguration.class
)
public interface SysFileApi {


    /**
     * 激活文件
     * @param sysFileManageForm sysFileManageForm
     * @return List<SysFileVo>
     */
    @PostMapping(value ="/fileManage/activationFile", consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<List<SysFileVo>> activationFile(
            @RequestBody @ApiParam(name = "添加文件对象", value = "传入json格式", required = true)
                    SysFileManageForm sysFileManageForm,
            @RequestHeader(value = JWTToken.TOKEN,required = false) @ApiParam(required = false) String token);

    /**
     * 上传文件，上传成功后需要调用激活文件接口才有效，否则文件会定期清理
     * @param file
     * @param token
     * @return
     */
    @PostMapping(value = "/fileManage/uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Result<SysFileVo> uploadFile(@ApiParam(value = "文件") @RequestPart(value = "file") MultipartFile file,
                                 @RequestHeader(value = JWTToken.TOKEN,required = false) @ApiParam(required = false) String token);

    /**
     * 根据ID下载文件
     * @param id
     * @param response
     * @param token
     * @throws IOException
     */
    @GetMapping(value = "/fileManage/downloadFile/{id}", produces = "application/octet-stream")
    public void downloadFile(@PathVariable(value = "id") String id, HttpServletResponse response,
                             @RequestHeader(value = JWTToken.TOKEN,required = false) @ApiParam(required = false) String token) throws IOException;

    /**
     * 获取某一个文件信息
     * @param id
     * @return
     */
    @GetMapping("/fileManage/getOne/{id}")
    public Result<SysFileManageVo> getOne(@PathVariable("id") String id);

}
