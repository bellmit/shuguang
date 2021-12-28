package com.sofn.fyrpa.web;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.Result;
import com.sofn.common.model.User;
import com.sofn.common.utils.UserUtil;
import com.sofn.fyrpa.model.AquaticResourcesProtectionInfo;
import com.sofn.fyrpa.service.AquaticResourcesProtectionInfoService;
import com.sofn.fyrpa.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(value = "保护区基本信息模块", tags = "保护区基本信息的相关接口")
@RestController
@Slf4j
@RequestMapping(value = "/aquaticResources")
public class AquaticResourcesProtectionInfoController {

    @Autowired
    private AquaticResourcesProtectionInfoService aquaticResourcesProtectionInfoService;

    @ApiOperation("新增")
    @PostMapping("/add")
    //@RequiresPermissions("fyrpa:aquaticResources:add")
    public Result add(@Validated @RequestBody(required = false) ListVo listVo, HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        User user = UserUtil.getLoginUser();

        if(StringUtils.isBlank(authorization)){
            throw new SofnException("当前登录用户异常");
        }

        Result result = this.aquaticResourcesProtectionInfoService.addResourcesProtection(listVo.getAquaticResourcesProtectionInfoVo(), listVo.getEnvironmentResourcesVo(), listVo.getManagerOrgVo(),user);
        return result;
    }

    @ApiOperation("保存并上报")
    @PostMapping("/addAndSb")
    //@RequiresPermissions("fyrpa:aquaticResources:addAndSb")
    public Result addAndSb(@Validated @RequestBody(required = false) ListVo listVo,HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        User user = UserUtil.getLoginUser();

        if(StringUtils.isBlank(authorization)){
            throw new SofnException("当前登录用户异常");
        }

        Result result = this.aquaticResourcesProtectionInfoService.addAndSbResourcesProtection(listVo.getAquaticResourcesProtectionInfoVo(), listVo.getEnvironmentResourcesVo(), listVo.getManagerOrgVo(),user);
        return result;
    }

    @ApiOperation("删除")
    @DeleteMapping("/delete")
   // @RequiresPermissions("fyrpa:aquaticResources:delete")
    public Result delete(@ApiParam(value = "id")@RequestParam(value = "id",required = true)String id) {
        Result result = this.aquaticResourcesProtectionInfoService.deleteResourcesProtection(id);
        return result;
    }

    @ApiOperation("修改")
    @PutMapping("/update")
   // @RequiresPermissions("fyrpa:aquaticResources:update")
    public Result update(@ApiParam(value = "id")@RequestParam(value = "id",required = true)String id,@Validated @RequestBody(required = false) ListVo listVo,HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        User user = UserUtil.getLoginUser();

        if(StringUtils.isBlank(authorization)){
            throw new SofnException("当前登录用户异常");
        }

        Result result = this.aquaticResourcesProtectionInfoService.updateResourcesProtection(id,listVo.getAquaticResourcesProtectionInfoVo(), listVo.getEnvironmentResourcesVo(), listVo.getManagerOrgVo(),user);
        return result;
    }

    @ApiOperation("修改并上报")
    @PostMapping("/updateAndSb")
    //@RequiresPermissions("fyrpa:aquaticResources:updateAndSb")
    public Result updateAndSb(@ApiParam(value = "id")@RequestParam(value = "id",required = true)String id,@Validated @RequestBody(required = false) ListVo listVo,HttpServletRequest request) {

        String authorization = request.getHeader("Authorization");
        User user = UserUtil.getLoginUser();

        if(StringUtils.isBlank(authorization)){
            throw new SofnException("当前登录用户异常");
        }
        Result result = this.aquaticResourcesProtectionInfoService.updateAndSbResourcesProtection(id,listVo.getAquaticResourcesProtectionInfoVo(), listVo.getEnvironmentResourcesVo(), listVo.getManagerOrgVo(),user);
        return result;
    }


    @ApiOperation("上报")
    @PutMapping("/sbResourcesProtection")
    //@RequiresPermissions("fyrpa:aquaticResources:sbResourcesProtection")
    public Result sbResourcesProtection(@ApiParam(value = "id")@RequestParam(value = "id",required = true)String id) {
        Result result = this.aquaticResourcesProtectionInfoService.sbResourcesProtection(id);
        return result;
    }

    @ApiOperation("分页查询")
    @GetMapping("/selectPageList")
    //@RequiresPermissions("fyrpa:aquaticResources:selectPageList")
    public Result<IPage<AquaticResourcesProtectionInfoVoList>> selectPageList(@ApiParam(value = "当前页数")@RequestParam(value = "pageNo", required = false) Integer pageNo,
                                                                              @ApiParam(value = "每页显示条数")@RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                                              @ApiParam(value = "报送时间")@RequestParam(value = "submitTime", required = false) String submitTime,
                                                                              @ApiParam(value = "全文查询")@RequestParam(value = "keyword", required = false) String keyword,HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        User user = UserUtil.getLoginUser();

        if(StringUtils.isBlank(authorization)){
            throw new SofnException("当前登录用户异常");
        }

        Result<IPage<AquaticResourcesProtectionInfoVoList>> result = this.aquaticResourcesProtectionInfoService.selectPageList(pageNo, pageSize, submitTime, keyword,user);
        return result;
    }

    @ApiOperation("详情页查询")
    @GetMapping("/selectDetailsById")
    //@RequiresPermissions("fyrpa:aquaticResources:selectDetailsById")
    public  Result<ListVo>  selectDetailsById(@ApiParam(value = "id")@RequestParam(value = "id", required = false) String id){
        Result<ListVo> result = this.aquaticResourcesProtectionInfoService.selectDetailsById(id);
        return  result;
    }

    @ApiOperation("审批意见查询")
    @GetMapping("/selectInfoAuditList")
    //@RequiresPermissions("fyrpa:aquaticResources:selectInfoAuditList")
    public Result selectInfoAuditList(@ApiParam(value = "id")@RequestParam(value = "id", required = false) String id){
        Result result = this.aquaticResourcesProtectionInfoService.selectInfoAuditList(id);
        return result;
    }
}
