package com.sofn.fdpi.web;

import com.google.common.collect.Maps;
import com.sofn.common.exception.SofnException;
import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.web.BaseController;
import com.sofn.fdpi.model.ManageRes;
import com.sofn.fdpi.service.ManageResService;
import com.sofn.fdpi.vo.ForfeiProcessFrom;
import com.sofn.fdpi.vo.ManageResInfoVo;
import com.sofn.fdpi.vo.ResProcessFrom;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @Description:
 * @Auther: Bay
 * @Date: 2020/1/3 16:16
 */
@Slf4j
@Api(value = "收容救护",tags = "收容救护接口")
@RestController
@RequestMapping("ManageRes")
public class ManageResController extends BaseController {
    @Autowired
    ManageResService manageResService;
    @RequiresPermissions(value = {"fdpi:helpApply:query","fdpi:helpAprove:query"},logical = Logical.OR)
    @SofnLog("获取收容信息(分页)")
    @ApiOperation("获取收容信息(分页)")
    @GetMapping(value = "/list")
    public Result<List<ManageRes>> getManageResList(@ApiParam(name = "rescueSpe",value = "救护物种")@RequestParam(value = "rescueSpe",required = false)String rescueSpe,
                                                    @ApiParam(name = "rescueSite",value = "救护地点")@RequestParam(value = "rescueSite",required = false)String rescueSite,
                                                    @ApiParam(name = "status",value = "状态 1未上报2上报3复审退回4复审通过5终审退回6终审通过")@RequestParam(value = "status",required = false)String status,
                                                    @ApiParam(name = "pageNo",required = true)@RequestParam("pageNo")int pageNo,
                                                    @ApiParam(name = "pageSize",required = true)@RequestParam("pageSize")int pageSize){
        HashMap<String, Object> map = Maps.newHashMap();
        map.put("rescueSite",rescueSite);
        map.put("rescueSpe",rescueSpe);
        map.put("status",status);
        PageUtils<ManageRes> manageResList = manageResService.getManageResList(map,pageNo,pageSize);
        return Result.ok(manageResList);
    }
    @RequiresPermissions(value = {"fdpi:helpApply:view","fdpi:helpAprove:view"},logical = Logical.OR)
    @SofnLog("根据id获取罚没详细信息")
    @ApiOperation(value = "根据id获取罚没详细信息")
    @GetMapping(value = "/get")
    public Result getManageResInfo(@RequestParam(value = "id") String id){
        ManageRes manageResInfo = manageResService.getManageResInfo(id);
        return Result.ok(manageResInfo);
    }
    @RequiresPermissions("fdpi:helpApply:create")
    @SofnLog("增加收容信息")
    @ApiOperation("增加收容信息")
    @PostMapping("/save")
    public Result saveManageResInfo(@Validated @RequestBody @ApiParam(name = "收容信息对象", value = "传入json格式", required = true)
            ManageResInfoVo manageResInfoVo,BindingResult result){
        // 校验参数
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        String s = null;
        try {
            s = manageResService.saveManageRes(manageResInfoVo);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SofnException(e.getMessage());
        }
        if ("1".equals(s)){
            return Result.ok("增加收容信息成功");
        }
        return Result.error(s);
    }
    @RequiresPermissions("fdpi:helpApply:update")
    @SofnLog("修改收容信息")
    @ApiOperation("修改收容信息")
    @PutMapping(value = "/update")
    public Result updateManageResInfo(@Validated @RequestBody @ApiParam(name = "罚没对象",
            value = "传入json格式", required = true) ManageResInfoVo manageResInfoVo){
        String s = manageResService.updateManageResInfo(manageResInfoVo);
        if ("1".equals(s)) {
            return Result.ok("修改收容信息成功");
        }
        return Result.error(s);
    }
    @RequiresPermissions("fdpi:helpApply:delete")
    @ApiOperation("删除收容信息")
    @SofnLog("删除收容信息")
    @DeleteMapping("/delete")
    public Result deleteManageResById(@RequestParam(value = "id")@ApiParam(name = "id",value = "收容id",required = true)String id){
        String s = manageResService.deleteManageResInfo(id);
        if ("1".equals(s)) {
            return Result.ok("删除收容信息成功");
        }
        return Result.error(s);
    }
    @RequiresPermissions("fdpi:helpApply:report")
    @SofnLog("收容信息上报")
    @ApiOperation(value = "收容信息上报")
    @PostMapping("/audit")
    public Result insertManageResProcess(@Validated @RequestBody ResProcessFrom resProcessFrom, BindingResult result){
        if (result.hasErrors()){
            return Result.error(getErrMsg(result));
        }
        manageResService.insertManageResProcess(resProcessFrom);
        return Result.ok();
    }

//
//    @ApiOperation(value = " 审核通过")
//    @PostMapping("/pass")
//    @SofnLog("罚没信息审核")
//    public Result pass(@Validated @RequestBody ResProcessFrom resProcessFrom, BindingResult result){
//        if (result.hasErrors()){
//            return Result.error(getErrMsg(result));
//        }
//        manageResService.pass(resProcessFrom);
//        return Result.ok();
//    }
//    @ApiOperation(value = " 审核退回")
//    @PostMapping("/back")
//    @SofnLog("罚没信息审核")
//    public Result back(@Validated @RequestBody ResProcessFrom resProcessFrom,BindingResult result){
//        if (result.hasErrors()){
//            return Result.error(getErrMsg(result));
//        }
//        manageResService.pass(resProcessFrom);
//        return Result.ok();
//    }

}
