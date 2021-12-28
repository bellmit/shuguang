package com.sofn.agzirz.sysapi;

import com.sofn.agzirz.sysapi.bean.*;
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
 */
@FeignClient(
        value = "sys-service",
        configuration = FeignConfiguration.class
)
public interface SysFileApi {


    /**
     * 激活文件
     *
     * @param sysFileManageForm
     * @return
     */
    @PostMapping(value = "/fileManage/activationFile", consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<List<SysFileVo>> activationFile(
            @RequestBody @ApiParam(name = "添加文件对象", value = "传入json格式", required = true)
                    SysFileManageForm sysFileManageForm);


    /**
     * 批量删除支撑平台中的文件
     *
     * @param ids
     * @return
     */
    @GetMapping(value = "/fileManage/batchDeleteFile/{ids}")
    Result batchDeleteFile(@PathVariable("ids") @ApiParam(value = "多个ID使用,隔开") String ids);

    /**
     * 批量获取文件信息
     *
     * @param ids
     * @return
     */
    @GetMapping(value = "/fileManage/batchGetFileInfo/{ids}")
    Result<List<SysFileManageVo>> batchGetFileInfo(@PathVariable("ids") @ApiParam(value = "多个ID使用,隔开") String ids);

    /**
     * 根据区划id获取省市区名称
     *
     * @return Result<String>
     */
    @GetMapping(value = "/sysRegion/getSysRegionName/{id}")
    Result<String> getSysRegionName(@ApiParam(value = "区划代码") @PathVariable(value = "id") String id);


    /**
     * 根据areaId获取
     *
     * @return Result
     */
    @GetMapping("/sysRegion/getParentNodeByRegionCode/{regionCode}")
    Result<List<SysRegionTreeVo>> getParentNode(@ApiParam(value = "行政区划ID", required = true) @PathVariable(value = "regionCode") String regionCode);


    /**
     * 获取区县信息
     *
     * @return Result
     */
    @GetMapping(value = "/sysRegion/getSysRegionOne/{areaId}")
    Result<SysRegionForm> getSysRegionOne(@ApiParam(value = "行政区划ID", required = true) @PathVariable(value = "areaId") String areaId);


}

