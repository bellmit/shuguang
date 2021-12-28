package com.sofn.agzirz.web;


import com.google.common.collect.Maps;
import com.sofn.agzirz.model.Xgxxsj;
import com.sofn.agzirz.service.XgxxsjService;
import com.sofn.agzirz.util.ExportUtil;
import com.sofn.agzirz.vo.XgxxsjExcelVo;
import com.sofn.agzirz.vo.XgxxsjQueryExcelVo;
import com.sofn.agzirz.vo.XgxxsjQueryVo;
import com.sofn.agzirz.vo.XgxxsjVo;
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

@Api(value = "相关信息收集接口", tags = "相关信息收集接口")
@RestController
@RequestMapping("/xgxxsj")
public class XgxxsjController extends BaseController {

    @Autowired
    private XgxxsjService xgxxsjService;

    @SofnLog("获取相关信息收集信息(分页)")
    @ApiOperation(value="获取相关信息收集信息(分页)")
    @PostMapping("/getXgxxsjByPage")
    @RequiresPermissions("agzirz:xgxxsj:getXgxxsjByPage")
    public Result<PageUtils<XgxxsjVo>> getXgxxsjByPage(
            @RequestBody @ApiParam(name = "查询参数对象", value = "传入json格式", required = true)XgxxsjQueryVo xgxxsjQueryVo,
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
        params.put("cnName", xgxxsjQueryVo.getCnName());
        params.put("schoName", xgxxsjQueryVo.getSchoName());
        params.put("haveMeasure", xgxxsjQueryVo.getHaveMeasure());
        params.put("dateBegin", xgxxsjQueryVo.getDateBegin());
        params.put("dateEnd", xgxxsjQueryVo.getDateEnd());
        //获取分页需要的相关索引和每页条数
        Integer pageNo = xgxxsjQueryVo.getPageNo();
        Integer pageSize = xgxxsjQueryVo.getPageSize();
        PageUtils<XgxxsjVo> xgxxsjPage = xgxxsjService.getXgxxsjByPage(params, pageNo, pageSize);
        return Result.ok(xgxxsjPage);
    }



    @SofnLog("获取指定的相关信息收集信息")
    @ApiOperation(value="获取指定的相关信息收集信息")
    @GetMapping("/getXgxxsjById")
    @RequiresPermissions("agzirz:xgxxsj:getXgxxsjById")
    public Result<XgxxsjVo> getXgxxsjById(
            @ApiParam(name = "id",value = "相关信息收集id",required = true)@RequestParam(value = "id")String id){

        XgxxsjVo xgxxsjVo = xgxxsjService.getXgxxsjById(id);

        return Result.ok(xgxxsjVo);
    }


    @SofnLog("新增相关信息收集信息")
    @ApiOperation(value="新增相关信息收集信息")
    @PostMapping("/addXgxxsj")
    @RequiresPermissions("agzirz:xgxxsj:addXgxxsj")
    public Result addXgxxsj(
            @RequestBody @ApiParam(name = "相关信息收集对象", value = "传入json格式", required = true)@Validated Xgxxsj xgxxsj,
            HttpServletRequest request){

        //从请求头中获取token
        String token = request.getHeader("Authorization");
        //根据token获取登录用户Id
        String userId = UserUtil.getLoginUserId(token);
        if(StringUtils.isBlank(userId)){
            throw new SofnException("当前登录用户异常");
        }

        xgxxsj.setReportUser(userId);
        xgxxsj.setReportTime(new Date());
        xgxxsj.setXgxxsjNo(IdUtil.getUUId());
        xgxxsjService.addXgxxsj(xgxxsj);
        return Result.ok("新增相关信息收集成功");
    }


    @SofnLog("编辑相关信息收集")
    @ApiOperation(value="编辑相关信息收集")
    @PostMapping("/updateXgxxsj")
    @RequiresPermissions("agzirz:xgxxsj:updateXgxxsj")
    public Result updateXgxxsj(
            @RequestBody @ApiParam(name = "相关信息收集对象", value = "传入json格式", required = true)@Validated Xgxxsj xgxxsj,
            HttpServletRequest request){

        //从请求头中获取token
        String token = request.getHeader("Authorization");
        //根据token获取登录用户Id
        String userId = UserUtil.getLoginUserId(token);
        if(StringUtils.isBlank(userId)){
            throw new SofnException("当前登录用户异常");
        }

        xgxxsjService.updateXgxxsj(xgxxsj,userId);
        return Result.ok("编辑相关信息收集成功!");
    }

    @SofnLog("删除相关信息收集")
    @ApiOperation(value="删除相关信息收集")
    @GetMapping("/removeFkyjzh")
    @RequiresPermissions("agzirz:xgxxsj:removeFkyjzh")
    public Result removeFkyjzh(
            @ApiParam(name = "id",value = "相关信息收集id",required = true)@RequestParam(value = "id")String id,
            HttpServletRequest request){
        //从请求头中获取token
        String token = request.getHeader("Authorization");
        //根据token获取登录用户Id
        String userId = UserUtil.getLoginUserId(token);
        if(StringUtils.isBlank(userId)){
            throw new SofnException("当前登录用户异常");
        }
        xgxxsjService.removeXgxxsj(id,userId);
        return Result.ok("删除相关信息收集成功!");
    }

    @SofnLog(value = "相关信息收集导出")
    @ApiOperation(value = "相关信息收集导出")
    @PostMapping(value = "/exportFkyjzhInfo", produces = "application/octet-stream")
    @RequiresPermissions("agzirz:xgxxsj:exportFkyjzhInfo")
    public void exportFkyjzhInfo(@RequestBody @ApiParam(name = "查询参数对象", value = "传入json格式", required = true)XgxxsjQueryExcelVo xgxxsjQueryExcelVo,
                                HttpServletResponse response){
        Map<String, Object> params = Maps.newHashMap();
        params.put("cnName", xgxxsjQueryExcelVo.getCnName());
        params.put("schoName", xgxxsjQueryExcelVo.getSchoName());
        params.put("haveMeasure", xgxxsjQueryExcelVo.getHaveMeasure());
        params.put("dateBegin", xgxxsjQueryExcelVo.getDateBegin());
        params.put("dateEnd", xgxxsjQueryExcelVo.getDateEnd());
        List<Xgxxsj> xgxxsjs = xgxxsjService.getXgxxsjList(params);
        List<XgxxsjExcelVo> list = new ArrayList<>();
        for(Xgxxsj xgxxsj : xgxxsjs){
            XgxxsjExcelVo xgxxsjExcelVo = XgxxsjExcelVo.getXgxxsjVo(xgxxsj);
            String name = UserUtil.getUsernameById(xgxxsj.getReportUser());
            xgxxsjExcelVo.setReportUser(name);
            if("1".equals(xgxxsj.getHaveMeasure())){
                xgxxsjExcelVo.setHaveMeasure("是");
            }else {
                xgxxsjExcelVo.setHaveMeasure("否");
            }
            list.add(xgxxsjExcelVo);
        }
        ExportUtil.createExcel(XgxxsjExcelVo.class,list,response,"相关信息收集列表.xlsx");

    }

}
