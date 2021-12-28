package com.sofn.agzirdd.sysapi;

import com.sofn.agzirdd.sysapi.bean.SysRegionForm;
import com.sofn.agzirdd.sysapi.bean.SysRegionTreeVo;
import com.sofn.common.config.FeignConfiguration;
import com.sofn.common.model.Result;
import com.sofn.common.utils.PageUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


/**
 * 使用Feign 调用sys服务
 *
 * @author chenlf
 * @date 2019/9/16 14:26
 */
@FeignClient(
        value = "sys-service",
//        url = "http://10.0.50.103:7999/sys",
        configuration = FeignConfiguration.class
)
public interface SysRegionApi {

    /**
     * 获取区县信息
     * @param areaId 区域id
     * @return SysRegionForm
     */
    @GetMapping(value = "/sysRegion/getSysRegionOne/{areaId}",produces = MediaType.APPLICATION_JSON_VALUE)
    Result<SysRegionForm> getSysRegionOne(@ApiParam(value = "行政区划ID", required = true) @PathVariable(value = "areaId") String areaId);

    /**
     * 根据行政区划代码获取当前机构的所有上级机构信息
     * @param regionId 行政区划ID
     * @return List<SysRegionTreeVo>
     */
    @GetMapping(value = "/sysRegion/getParentNodeByRegionId/{regionId}", consumes =
            MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    Result<List<SysRegionTreeVo>> getParentNode(@ApiParam(value = "行政区划ID", required = true) @PathVariable(value = "regionId") String regionId);

    /**
     * 根据父ID获取当前ID下面的树结构，只返回一层
     * @param areaId 父级行政区划ID
     * @return List<SysRegionTreeVo>
     */
    @GetMapping(value = "/sysRegion/getSysRegionTreeById/{areaId}",produces = MediaType.APPLICATION_JSON_VALUE)
    Result<List<SysRegionTreeVo>> getSysRegionTreeById(@ApiParam(value = "父级行政区划ID", required = true) @PathVariable(value = "areaId") String areaId);


    /**
     * 按条件获取全国省市区县信息（返回此parentId下所有层级信息）
     * @param keyword 行政区划名称
     * @param parentId 父节点ID(最高100000)
     * @param regionCode 行政区划代码
     * @return List<SysRegionTreeVo>
     */
    @GetMapping(value="/sysRegion/getSysRegionTree",produces = MediaType.APPLICATION_JSON_VALUE)
    Result<List<SysRegionTreeVo>> getSysRegionTree(
            @ApiParam(value = "行政区划名称") @RequestParam(required = false, value = "keyword") String keyword,
            @ApiParam(value = "父节点ID") @RequestParam(required = false, value = "parentId") String parentId,
            @ApiParam(value = "行政区划代码") @RequestParam(required = false, value = "regionCode") String regionCode);


    @ApiOperation(value = "根据父ID获取下面的子区划，只返回一层，如果带了条件后台不进行分页并且不止返回一层，任何一个分页参数未传入就不分页")
    @GetMapping(value="/sysRegion/getSysRegionFormByParentId",produces = MediaType.APPLICATION_JSON_VALUE)
    Result<PageUtils<SysRegionTreeVo>> getSysRegionFormByParentId(@ApiParam(value = "行政区划名称") @RequestParam(value = "keyword",required = false) String keyword,
                                                                  @ApiParam(value = "父节点ID，当没有条件时必传") @RequestParam(value = "parentId",required = false) String parentId,
                                                                  @ApiParam(value = "行政区划代码") @RequestParam(value = "regionCode",required = false)String regionCode,
                                                                  @ApiParam(value = "从哪条记录开始") @RequestParam(value = "pageNo",required = false) Integer pageNo,
                                                                  @ApiParam(value = "每页显示多少条") @RequestParam(value = "pageSize",required = false)Integer pageSize,
                                                                  @ApiParam(value = "系统APPID") @RequestParam(value = "appId",required = false) String appId);



    @ApiOperation(value = "根据区划代码获取代码对应的区划名称")
    @GetMapping(value="/sysRegion/getRegionNamesByCodes/{codes}",produces = MediaType.APPLICATION_JSON_VALUE)
    Result<String> getRegionNamesByCodes(@ApiParam(value = "区划代码，多个用逗号分隔") @PathVariable("codes") String codes);
}
