package com.sofn.agzirdd.sysapi;

import com.sofn.agzirdd.sysapi.bean.SysFileManageForm;
import com.sofn.agzirdd.sysapi.bean.SysFileManageVo;
import com.sofn.agzirdd.sysapi.bean.SysFileVo;
import com.sofn.common.config.FeignConfiguration;
import com.sofn.common.model.Result;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @Description: 用于文件激活相关
 * @Author: mcc
 * @Date: 2020\2\26 0026
 */
@FeignClient(
        value = "sys-service",
//        url = "http://10.0.50.103:7999/sys",
        configuration = FeignConfiguration.class
)
public interface SysFileApi {


    /**
     * 激活文件
     * @param sysFileManageForm sysFileManageForm
     * @return List<SysFileVo>
     */
    @PostMapping(value ="/fileManage/activationFile", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    Result<List<SysFileVo>> activationFile(
            @RequestBody @ApiParam(name = "添加文件对象", value = "传入json格式", required = true)
                    SysFileManageForm sysFileManageForm);


    /**
     * 批量删除支撑平台中的文件
     * @param ids ids
     * @return batchDeleteFile
     */
    @GetMapping(value = "/fileManage/batchDeleteFile/{ids}",produces = MediaType.APPLICATION_JSON_VALUE)
    Result batchDeleteFile(@PathVariable("ids") @ApiParam(value = "多个ID使用,隔开") String ids);

    /**
     * 批量获取文件信息
     * @param ids ids
     * @return List<SysFileManageVo>
     */
    @GetMapping(value = "/fileManage/batchGetFileInfo/{ids}",produces = MediaType.APPLICATION_JSON_VALUE)
    Result<List<SysFileManageVo>> batchGetFileInfo(@PathVariable("ids") @ApiParam(value = "多个ID使用,隔开") String ids);

    }
