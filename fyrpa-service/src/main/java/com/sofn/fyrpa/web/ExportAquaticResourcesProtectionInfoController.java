package com.sofn.fyrpa.web;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.Result;
import com.sofn.common.model.User;
import com.sofn.common.utils.UserUtil;
import com.sofn.fyrpa.service.ExportAquaticResourcesProtectionInfoService;
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
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api(value = "待审信息(专家)模块", tags = "待审信息(专家)相关接口")
@RestController
@Slf4j
@RequestMapping(value = "/exportAquaticResources")
public class ExportAquaticResourcesProtectionInfoController {
    @Autowired
    private ExportAquaticResourcesProtectionInfoService exportAquaticResourcesProtectionInfoService;

    @PostMapping("/isNotPass")
    @ApiOperation("驳回")
    //@RequiresPermissions("fyrpa:exportAquaticResources:isNotPass")
    public Result isNotPass(@RequestBody @Validated InformationAuditVo informationAuditVo,
                            @RequestParam(required = true)@ApiParam(value = "id")String id){
        Result result = this.exportAquaticResourcesProtectionInfoService.isNotPass(informationAuditVo, id);
        return result;
    }

    @PostMapping("/isPass")
    @ApiOperation("专家通过")
    //@RequiresPermissions("fyrpa:exportAquaticResources:isPass")
    public Result isPass(@RequestBody @Validated InformationAuditVo informationAuditVo,
                            @RequestParam(required = true)@ApiParam(value = "id")String id){
        Result result = this.exportAquaticResourcesProtectionInfoService.isPass(informationAuditVo, id);
        return result;
    }

    @ApiOperation("分页查询")
    @GetMapping("/selectPageList")
    //@RequiresPermissions("fyrpa:exportAquaticResources:selectPageList")
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

        Result<IPage<AquaticResourcesProtectionInfoVoList>> result= this.exportAquaticResourcesProtectionInfoService.selectPageList(pageNo, pageSize, submitTime, keyword,regionCodeArr,user);
        return result;
    }

    @ApiOperation("详情页查询")
    @GetMapping("/selectDetailsById")
   // @RequiresPermissions("fyrpa:exportAquaticResources:selectDetailsById")
    public Result selectDetailsById(@ApiParam(value = "id")@RequestParam(value = "id", required = false) String id){
        Result<ListVo> result = this.exportAquaticResourcesProtectionInfoService.selectDetailsById(id);
        return  result;
    }

    @ApiOperation("审批意见查询")
    @GetMapping("/selectAuditList")
    //@RequiresPermissions("fyrpa:exportAquaticResources:selectAuditList")
    public Result selectInfoAuditList(@ApiParam(value = "id")@RequestParam(value = "id", required = false) String id, HttpServletRequest request){

        String authorization = request.getHeader("Authorization");
        User user = UserUtil.getLoginUser();

        if(StringUtils.isBlank(authorization)){
            throw new SofnException("当前登录用户异常");
        }
        Result result = this.exportAquaticResourcesProtectionInfoService.selectInfoAuditList(id);
        return result;
    }
}
