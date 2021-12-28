package com.sofn.fdpi.web;

import com.google.common.collect.Maps;
import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.web.BaseController;
import com.sofn.fdpi.model.Forfei;
import com.sofn.fdpi.service.ForfeiService;
import com.sofn.fdpi.vo.ForfeiInfoVo;
import com.sofn.fdpi.vo.ForfeiProcessFrom;
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
 * @Date: 2020/1/2 16:52
 */
@Api(value = "APP_罚没相关接口",tags = "APP_罚没相关接口")
@RestController
@Slf4j
@RequestMapping(value = "/app/Forfei")
public class ForfeiAppController extends BaseController {
    @Autowired
    ForfeiService forfeiService;

    @RequiresPermissions(value = {"fdpi:finesApply:query","fdpi:finesAprove:query"},logical = Logical.OR)
    @ApiOperation(value = " 获取罚没信息(分页)")
    @GetMapping("/list")
    @SofnLog("获取罚没信息(分页)")
    public Result<List<Forfei>> getForfeiListByPage(@ApiParam(name = "ffType",value = "罚没类型 0：制品：1：活品")@RequestParam(value = "ffType",required = false)String ffType,
                                                    @ApiParam(name = "speName",value = "物种名")@RequestParam(value = "speName",required = false)String speName,
                                                    @ApiParam(name = "status",value = "状态 1未上报2上报3复审退回4复审通过5终审退回6终审通过")@RequestParam(value = "status",required = false)String status,
                                                    @ApiParam(name = "pageNo",required = true)@RequestParam("pageNo")int pageNo,
                                                    @ApiParam(name = "pageSize",required = true)@RequestParam("pageSize")int pageSize){
        HashMap<String, Object> map = Maps.newHashMap();
        map.put("ffType",ffType);
        map.put("speName",speName);
        map.put("status",status);
        PageUtils<Forfei> listByPage = forfeiService.getForfeiListByPage(map, pageNo, pageSize);
        return Result.ok(listByPage);
    }
    @RequiresPermissions("fdpi:finesApply:create")
    @ApiOperation(value = " 新增罚没信息")
    @PostMapping("/save")
    @SofnLog("新增罚没信息")
    public Result<Forfei> saveForfei(@Validated @RequestBody @ApiParam(name = "罚没信息对象", value = "传入json格式", required = true)
                                             ForfeiInfoVo forfeiInfoVo, BindingResult result){
        if (result.hasErrors()){
            return Result.error(getErrMsg(result));
        }
        return Result.ok(forfeiService.saveForfeiInfo(forfeiInfoVo));

    }
    @RequiresPermissions("fdpi:finesApply:delete")
    @ApiOperation(value = " 删除罚没信息")
    @DeleteMapping("/delete")
    @SofnLog("删除罚没信息")
    public Result deleteById(@RequestParam(value = "id") @ApiParam(name = "id", value = "罚没id",required = true)String id){
        String info = forfeiService.deleteForFeiInfo(id);
        if (!"1".equals(info)){
            return Result.ok(info);
        }
        return Result.ok("删除罚没信息失败");
    }
    @RequiresPermissions("fdpi:finesApply:update")
    @ApiOperation(value = " 修改罚没信息")
    @PutMapping("/update")
    @SofnLog("修改罚没信息")
    public Result updateForFeiInfo(@Validated @RequestBody @ApiParam(name = "罚没对象",
            value = "传入json格式", required = true)ForfeiInfoVo forfeiInfoVo,BindingResult result){
        if (result.hasErrors()){
            return Result.error(getErrMsg(result));
        }
        String info = forfeiService.updateForFeiInfo(forfeiInfoVo);
        if ("1".equals(info)) {
            return Result.ok("修改罚没信息成功");
        }
        return Result.error(info);
    }
    @RequiresPermissions(value = {"fdpi:finesApply:view","fdpi:finesAprove:view"},logical = Logical.OR)
    @ApiOperation(value = " 根据id查询罚没详细信息")
    @GetMapping("/get")
    @SofnLog("根据id查询罚没详细信息")
    public Result<Forfei> getForfeiInfo(@RequestParam(value = "id") String id){
        Forfei forfeiInfo = forfeiService.getForfeiInfo(id);
        return Result.ok(forfeiInfo);
    }
    @RequiresPermissions("fdpi:finesApply:report")
    @ApiOperation(value = " 罚没信息上报")
    @PostMapping("/audit")
    @SofnLog("罚没信息上报")
    public Result insertForfeiProcess(@Validated @RequestBody ForfeiProcessFrom forfeiProcessFrom,BindingResult result){
        if (result.hasErrors()){
            return Result.error(getErrMsg(result));
        }
        forfeiService.insertForfeiProcess(forfeiProcessFrom);
        return Result.ok();
    }

//
//    @ApiOperation(value = " 审核通过")
//    @PostMapping("/pass")
//    @SofnLog("罚没信息审核")
//    public Result pass(@Validated @RequestBody ForfeiProcessFrom forfeiProcessFrom,BindingResult result){
//        if (result.hasErrors()){
//            return Result.error(getErrMsg(result));
//        }
//        forfeiService.pass(forfeiProcessFrom);
//        return Result.ok();
//    }
//    @ApiOperation(value = " 审核退回")
//    @PostMapping("/back")
//    @SofnLog("罚没信息审核")
//    public Result back(@Validated @RequestBody ForfeiProcessFrom forfeiProcessFrom,BindingResult result){
//        if (result.hasErrors()){
//            return Result.error(getErrMsg(result));
//        }
//        forfeiService.back(forfeiProcessFrom);
//        return Result.ok();
//    }


}
