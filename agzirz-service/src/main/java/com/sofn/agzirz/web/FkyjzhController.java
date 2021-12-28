package com.sofn.agzirz.web;


import com.google.common.collect.Maps;
import com.sofn.agzirz.model.FKYJZH;
import com.sofn.agzirz.service.FkyjzhService;
import com.sofn.agzirz.sysapi.SysFileApi;
import com.sofn.agzirz.util.ExportUtil;
import com.sofn.agzirz.vo.FkyjzhExcelVo;
import com.sofn.agzirz.vo.FkyjzhQueryExcelVo;
import com.sofn.agzirz.vo.FkyjzhQueryVo;
import com.sofn.agzirz.vo.FkyjzhVo;
import com.sofn.common.exception.SofnException;
import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.common.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Api(value = "防控应急指挥接口", tags = "防控应急指挥接口")
@RestController
@RequestMapping("/fkyjzh")
public class FkyjzhController extends BaseController {

    @Autowired
    private FkyjzhService fkyjzhService;

    @Autowired
    private SysFileApi sysRegionApi;

    @SofnLog("获取防控应急指挥信息(分页)")
    @ApiOperation(value="获取防控应急指挥信息(分页)")
    @PostMapping("/getFkyjzhByPage")
    @RequiresPermissions("agzirz:fkyjzh:getFkyjzhByPage")
    public Result<PageUtils<FkyjzhVo>> getFkyjzhByPage(
            @RequestBody @ApiParam(name = "查询参数对象", value = "传入json格式", required = true)FkyjzhQueryVo fkyjzhQueryVo,
            HttpServletRequest request){

        //从请求头中获取token
        String token = request.getHeader("Authorization");
        //根据token获取登录用户Id
        String userId = UserUtil.getLoginUserId(token);
        if(StringUtils.isBlank(userId)){
            throw new SofnException("当前登录用户异常");
        }


        //创建map用于存放查询条件
        Map<String, Object> params = Maps.newHashMap();
        params.put("eventCause", fkyjzhQueryVo.getEventCause());
        params.put("importance", fkyjzhQueryVo.getImportance());
        params.put("dateBegin", fkyjzhQueryVo.getDateBegin());
        params.put("dateEnd", fkyjzhQueryVo.getDateEnd());
        //获取分页需要的相关索引和每页条数
        Integer pageNo = fkyjzhQueryVo.getPageNo();
        Integer pageSize = fkyjzhQueryVo.getPageSize();
        PageUtils<FkyjzhVo> fkyjzhByPage = fkyjzhService.getFkyjzhByPage(params, pageNo, pageSize);
        return Result.ok(fkyjzhByPage);
    }



    @SofnLog("获取指定的防控应急指挥信息")
    @ApiOperation(value="获取指定的防控应急指挥信息")
    @GetMapping("/getFkyjzhById")
    @RequiresPermissions("agzirz:fkyjzh:getFkyjzhById")
    public Result<FkyjzhVo> getFkyjzhById(
            @ApiParam(name = "id",value = "防控应急指挥信息id",required = true)@RequestParam(value = "id")String id){

        FkyjzhVo fkyjzhVo = fkyjzhService.getFkyjzhById(id);

        return Result.ok(fkyjzhVo);
    }


    @SofnLog("新增防控应急指挥信息")
    @ApiOperation(value="新增防控应急指挥信息")
    @PostMapping("/addFkyjzh")
    @RequiresPermissions("agzirz:fkyjzh:addFkyjzh")
    public Result addFkyjzh(
            @RequestBody @ApiParam(name = "防控应急指挥信息对象", value = "传入json格式", required = true)@Validated FKYJZH fkyjzh,
            HttpServletRequest request){

        //从请求头中获取token
        String token = request.getHeader("Authorization");
        //根据token获取登录用户Id
        String userId = UserUtil.getLoginUserId(token);
        if(StringUtils.isBlank(userId)){
            throw new SofnException("当前登录用户异常");
        }

        fkyjzh.setReportUser(userId);
        fkyjzh.setReportTime(new Date());
        fkyjzh.setFkyjzhNo(IdUtil.getUUId());
        fkyjzhService.addFkyjzh(fkyjzh);
        return Result.ok("新增防控应急指挥信息成功");
    }


    @SofnLog("编辑防控应急指挥信息")
    @ApiOperation(value="编辑防控应急指挥信息")
    @PostMapping("/updateFkyjzh")
    @RequiresPermissions("agzirz:fkyjzh:updateFkyjzh")
    public Result updateFkyjzh(
            @RequestBody @ApiParam(name = "防控应急指挥信息对象", value = "传入json格式", required = true)@Validated FKYJZH fkyjzh,
            HttpServletRequest request){

        //从请求头中获取token
        String token = request.getHeader("Authorization");
        //根据token获取登录用户Id
        String userId = UserUtil.getLoginUserId(token);
        if(StringUtils.isBlank(userId)){
            throw new SofnException("当前登录用户异常");
        }

        fkyjzhService.updateFkyjzh(fkyjzh,userId);
        return Result.ok("编辑防控应急指挥信息成功!");
    }

    @SofnLog("删除防控应急指挥信息")
    @ApiOperation(value="删除防控应急指挥信息")
    @GetMapping("/removeFkyjzh")
    @RequiresPermissions("agzirz:fkyjzh:removeFkyjzh")
    public Result removeFkyjzh(
            @ApiParam(name = "id",value = "防控应急指挥信息id",required = true)@RequestParam(value = "id")String id,
            HttpServletRequest request){
        //从请求头中获取token
        String token = request.getHeader("Authorization");
        //根据token获取登录用户Id
        String userId = UserUtil.getLoginUserId(token);
        if(StringUtils.isBlank(userId)){
            throw new SofnException("当前登录用户异常");
        }
        fkyjzhService.removeFkyjzh(id,userId);
        return Result.ok("删除防控应急指挥信息成功!");
    }

    @SofnLog(value = "防控应急指挥信息导出")
    @ApiOperation(value = "防控应急指挥信息导出")
    @PostMapping(value = "/exportFkyjzhInfo", produces = "application/octet-stream")
    @RequiresPermissions("agzirz:fkyjzh:exportFkyjzhInfo")
    public void exportFkyjzhInfo(@RequestBody @ApiParam(name = "查询参数对象", value = "传入json格式", required = true)FkyjzhQueryExcelVo fkyjzhQueryVo,
                                HttpServletResponse response){
        Map<String, Object> params = Maps.newHashMap();
        params.put("eventCause", fkyjzhQueryVo.getEventCause());
        params.put("importance", fkyjzhQueryVo.getImportance());
        params.put("dateBegin", fkyjzhQueryVo.getDateBegin());
        params.put("dateEnd", fkyjzhQueryVo.getDateEnd());
        List<FKYJZH> fkyjzhs = fkyjzhService.getFkyjzhList(params);
        List<FkyjzhExcelVo> list = new ArrayList<>();
        for(FKYJZH fkyjzh : fkyjzhs){
            FkyjzhExcelVo fkyjzhExcelVo = FkyjzhExcelVo.getFkyjzhVo(fkyjzh);
            String name2 = UserUtil.getUsernameById(fkyjzhExcelVo.getReportUser());
            fkyjzhExcelVo.setReportUser(name2);
            String eventLocation = fkyjzhExcelVo.getEventLocation();
            if(StringUtils.isNotBlank(eventLocation)){
                String regionName = "";
                List<String> regionCodeList = new ArrayList<>(IdUtil.getIdsByStr(eventLocation));
                for(String id : regionCodeList){
                    Result<String> result = sysRegionApi.getSysRegionName(id);
                    if(null!=result){
                        String name = result.getData();
                        regionName = regionName+name;
                    }
                }
                fkyjzhExcelVo.setEventLocation(regionName);
            }
            list.add(fkyjzhExcelVo);
        }
        ExportUtil.createExcel(FkyjzhExcelVo.class,list,response,"防控应急指挥信息列表.xlsx");
    }

}
