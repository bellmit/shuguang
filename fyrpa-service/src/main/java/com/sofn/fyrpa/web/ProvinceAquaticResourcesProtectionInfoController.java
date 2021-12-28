package com.sofn.fyrpa.web;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.Result;
import com.sofn.common.model.User;
import com.sofn.common.utils.UserUtil;
import com.sofn.fyrpa.service.ProvinceAquaticResourcesProtectionInfoService;
import com.sofn.fyrpa.vo.AquaticResourcesProtectionInfoVoList;
import com.sofn.fyrpa.vo.InformationAuditVo;
import com.sofn.fyrpa.vo.ListVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api(value = "待审信息(省级)模块", tags = "待审信息(省级)相关接口")
@RestController
@Slf4j
@RequestMapping(value = "/provinceAquaticResources")
public class ProvinceAquaticResourcesProtectionInfoController {
    @Autowired
    private ProvinceAquaticResourcesProtectionInfoService provinceAquaticResourcesProtectionInfoService;

    @PostMapping("/isNotPass")
    @ApiOperation("驳回(未经专家审核)")
    //@RequiresPermissions("fyrpa:provinceAquaticResources:isNotPass")
    public Result isNotPass(@RequestBody @Validated InformationAuditVo informationAuditVo,
                            @RequestParam(required = true)@ApiParam(value = "id", required = true)String id){
        Result result = this.provinceAquaticResourcesProtectionInfoService.isNotPass(informationAuditVo, id);
        return result;
    }

    @PostMapping("/isPass")
    @ApiOperation("省级通过(未经专家审核)")
    //@RequiresPermissions("fyrpa:provinceAquaticResources:isPass")
    public Result isPass(@RequestBody @Validated InformationAuditVo informationAuditVo,
                            @RequestParam(required = true) @ApiParam(value = "id", required = true) String id){
        Result result = this.provinceAquaticResourcesProtectionInfoService.isPass(informationAuditVo, id);
        return result;
    }

    @ApiOperation("分页查询")
    @GetMapping("/selectPageList")
    //@RequiresPermissions("fyrpa:provinceAquaticResources:selectPageList")
    @ApiImplicitParam(name="regionCodeArr", value="区域数组集", required=false, paramType="query" ,allowMultiple=true, dataType = "String")
    public Result<IPage<AquaticResourcesProtectionInfoVoList>> selectPageList(@ApiParam(value = "当前页数")@RequestParam(value = "pageNo", required = false) Integer pageNo,
                                                                              @ApiParam(value = "每页显示条数")@RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                                              @ApiParam(value = "报送时间")@RequestParam(value = "submitTime", required = false) String submitTime,
                                                                              @ApiParam(value = "全文查询")@RequestParam(value = "keyword", required = false) String keyword,
                                                                              @RequestParam(value = "regionCodeArr", required = false) String[] regionCodeArr, HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        User user = UserUtil.getLoginUser();

        if(StringUtils.isBlank(authorization)){
            throw new SofnException("当前登录用户异常");
        }

        Result<IPage<AquaticResourcesProtectionInfoVoList>> result= this.provinceAquaticResourcesProtectionInfoService.selectPageList(pageNo, pageSize, submitTime, keyword,regionCodeArr,user);
        return result;
    }

    @ApiOperation("详情页查询")
    @GetMapping("/selectDetailsById")
    //@RequiresPermissions("fyrpa:provinceAquaticResources:selectDetailsById")
    public Result selectDetailsById(@ApiParam(value = "id")@RequestParam(value = "id", required = false) String id){
        Result<ListVo> result = this.provinceAquaticResourcesProtectionInfoService.selectDetailsById(id);
        return  result;
    }

    @ApiOperation("审批意见查询")
    @GetMapping("/selectAuditList")
    //@RequiresPermissions("fyrpa:provinceAquaticResources:selectAuditList")
    public Result selectInfoAuditList(@ApiParam(value = "id")@RequestParam(value = "id", required = false) String id){
        Result result = this.provinceAquaticResourcesProtectionInfoService.selectInfoAuditList(id);
        return result;
    }

    @ApiOperation("省级通过的数据查询")
    @GetMapping("/selectListIsPass")
    //@RequiresPermissions("fyrpa:provinceAquaticResources:selectListIsPass")
    @ApiImplicitParam(name="regionCodeArr", value="区域数组集", required=false, paramType="query" ,allowMultiple=true, dataType = "String")
    public Result selectListIsPass(@ApiParam(value = "当前页数")@RequestParam(value = "pageNo", required = false) Integer pageNo,
                                   @ApiParam(value = "每页显示条数")@RequestParam(value = "pageSize", required = false) Integer pageSize,
                                   @ApiParam(value = "报送时间")@RequestParam(value = "submitTime", required = false) String submitTime,
                                   @ApiParam(value = "全文查询")@RequestParam(value = "keyword", required = false) String keyword,
                                   @RequestParam(value = "regionCodeArr", required = false) String[] regionCodeArr,HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        User user = UserUtil.getLoginUser();

        if(StringUtils.isBlank(authorization)){
            throw new SofnException("当前登录用户异常");
        }

        Result result = this.provinceAquaticResourcesProtectionInfoService.selectListIsPass(pageNo, pageSize, submitTime, keyword,regionCodeArr,user);
        return result;
    }

    @ApiOperation("省级上报保护区数据")
    @PutMapping("/report/{id}")
    public Result<String> report(@ApiParam(value = "保护区id", format = "path", required = true) @PathVariable("id") String id,
                                 @ApiParam(hidden = true) @RequestHeader("Authorization") String authorization) {
        if(StringUtils.isBlank(authorization)){
            throw new SofnException("当前登录用户异常");
        }

        User user = UserUtil.getLoginUser();

        return this.provinceAquaticResourcesProtectionInfoService.report(id, user);
    }
}
