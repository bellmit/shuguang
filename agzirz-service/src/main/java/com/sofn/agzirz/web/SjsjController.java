package com.sofn.agzirz.web;


import com.sofn.agzirz.model.Sjsj;
import com.sofn.agzirz.service.ISjsjService;
import com.sofn.agzirz.util.ExportUtil;
import com.sofn.agzirz.vo.SjsjVo;
import com.sofn.common.exception.SofnException;
import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.common.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 事件收集模块 前端控制器
 * </p>
 *
 * @author simon
 * @since 2020-03-04
 */
@Slf4j
@Api(tags = "事件收集接口")
@RestController
@RequestMapping("/sjsj")
public class SjsjController extends BaseController {
    @Autowired
    ISjsjService sjsjService;

    @ApiOperation(value = "事件收集列表信息")
    @GetMapping("/getSjsjPageList")
    @SofnLog("查询事件收集列表")
    @RequiresPermissions("agzirz:sjsj:getSjsjPageList")
    public Result getSjsjPageList(HttpServletRequest request,@RequestParam(value="disposalOrgani",required = false) @ApiParam(value = "处置机构") String disposalOrgani
                            , @RequestParam(value="eventLocation",required = false) @ApiParam(value="发生地点") String eventLocation
                            , @RequestParam(value="startTime",required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") @ApiParam(value="发生-开始时间") Date startTime
                            , @RequestParam(value="endTime",required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") @ApiParam(value="发生-结束时间") Date endTime
                            , @RequestParam(value="pageNo") @ApiParam(value="起始行数") Integer pageNo
                              , @RequestParam(value = "pageSize") @ApiParam(value="每页显示数") Integer pageSize
                              ) {
        checkLoginState(request);

        PageUtils<Sjsj> res = sjsjService.getSjsjPageList(pageNo,pageSize, disposalOrgani, eventLocation, startTime, endTime);

        return Result.ok(res);
    }

    @ApiOperation(value = "导出事件收集列表")
    @GetMapping(value="/exportSjsjList",produces = "application/octet-stream")
    @SofnLog("导出事件收集列表")
    @RequiresPermissions("agzirz:sjsj:exportSjsjList")
    public void exportSjsjList(HttpServletResponse response,HttpServletRequest request, @RequestParam(value="disposalOrgani",required = false) @ApiParam(value = "处置机构") String disposalOrgani
            , @RequestParam(value="eventLocation",required = false) @ApiParam(value="发生地点") String eventLocation
            , @RequestParam(value="startTime",required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") @ApiParam(value="发生-开始时间") Date startTime
            , @RequestParam(value="endTime",required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") @ApiParam(value="发生-结束时间") Date endTime
    ) {
        checkLoginState(request);

        List<Sjsj> data = sjsjService.getSjsjList(disposalOrgani, eventLocation, startTime, endTime);

        log.info("开始时间{}",new Date());
        ExportUtil.createExcel(Sjsj.class,data,response,"入侵物种事件收集.xlsx");
        log.info("结束时间{}",new Date());

    }

    @PostMapping("/add")
    @ApiOperation(value="保存事件收集信息，新增")
    @SofnLog(value="保存事件收集信息")
    @RequiresPermissions("agzirz:sjsj:add")
    public Result add(HttpServletRequest request, @RequestBody @ApiParam(name = "保存事件收集信息数据对象", value = "传入json格式", required = true) @Validated SjsjVo sjsjVo
                       ) {
        String userId = checkLoginState(request);

        Sjsj model = new Sjsj();
        BeanUtils.copyProperties(sjsjVo,model);
        model.setReportUser(userId);

        int rows = sjsjService.saveOrupdateSjsj(model);
        return rows>0?Result.ok():Result.error();
    }

    @PostMapping("/modify")
    @ApiOperation(value="保存事件收集信息，编辑")
    @SofnLog(value="保存事件收集信息")
    @RequiresPermissions("agzirz:sjsj:modify")
    public Result modify(HttpServletRequest request, @RequestBody @ApiParam(name = "保存事件收集信息数据对象", value = "传入json格式", required = true) @Validated SjsjVo sjsjVo
    ) {
        String userId = checkLoginState(request);

        Sjsj model = new Sjsj();
        BeanUtils.copyProperties(sjsjVo,model);
        model.setReportUser(userId);

        int rows = sjsjService.saveOrupdateSjsj(model);
        return rows>0?Result.ok():Result.error();
    }

    @DeleteMapping("/del")
    @ApiOperation(value = "删除单个事件收集信息")
    @SofnLog(value = "删除单个事件收集信息")
    @RequiresPermissions("agzirz:sjsj:del")
    public Result del(HttpServletRequest request,@RequestParam(value = "id", required = true) @ApiParam(value = "id") String id) {
        checkLoginState(request);

        boolean flag = sjsjService.removeSjsjById(id);
        return flag?Result.ok():Result.error();
    }

    @GetMapping("/show")
    @ApiOperation(value = "查询单个事件收集信息")
    @SofnLog(value = "查询单个事件收集信息")
    @RequiresPermissions("agzirz:sjsj:show")
    public Result show(HttpServletRequest request,@RequestParam(value = "id", required = true) @ApiParam(value = "id") String id) {
        checkLoginState(request);

        Sjsj res = sjsjService.getSjsjShowById(id);
        if(res!=null && res.getEnableStatus().equals("N")){
            res = null;
        }
        return res != null ? Result.ok(res) : Result.error("find no data of ID "+id);
    }

    private String checkLoginState(HttpServletRequest request) {
        //从请求头中获取token
        String token = request.getHeader("Authorization");
        //根据token获取登录用户Id
        String userId = UserUtil.getLoginUserId(token);
        if(StringUtils.isBlank(userId)){
            throw new SofnException("当前登录用户异常");
        }

        return userId;
    }

}
