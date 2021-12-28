package com.sofn.agsjdm.web;

import com.sofn.agsjdm.service.InformationManagementService;
import com.sofn.agsjdm.util.MapUtil;
import com.sofn.agsjdm.vo.BasicsCollectionForm;
import com.sofn.agsjdm.vo.InformationManagementFrom;
import com.sofn.agsjdm.vo.InformationManagementTableVo;
import com.sofn.common.exception.SofnException;
import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * @Description
 * @Author wg
 * @Date 2021/7/14 9:29
 **/
@Slf4j
@Api(tags = "农业湿地信息管理模块接口")
@RestController
@RequestMapping("/im")
public class InformationManagementController extends BaseController {
    @Resource
    private InformationManagementService informationManagementService;

    @ApiOperation(value = "新增")
    @PostMapping(value = "/save")
    @RequiresPermissions("agsjdm:point:create")
    public Result insert(@Validated @RequestBody @ApiParam(name = "基础信息收集对象", value = "传入json格式", required = true) InformationManagementFrom im, BindingResult result) {
        //校验参数
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        //湿地至少有一条完整的数据
        if (!((StringUtils.isNotBlank(im.getWetlandClass1())
                && Objects.nonNull(im.getWetlandClassArea1())
                && StringUtils.isNotBlank(im.getMainWetlandType1())
                && Objects.nonNull(im.getMainWetlandTypeArea1()))
                ||
                (StringUtils.isNotBlank(im.getWetlandClass2())
                        && Objects.nonNull(im.getWetlandClassArea2())
                        && StringUtils.isNotBlank(im.getMainWetlandType2())
                        && Objects.nonNull(im.getMainWetlandTypeArea2())))
        ) {
            throw new SofnException("湿地应至少有一条完整的数据");
        }
        //插入数据
        informationManagementService.insertIm(im);
        return Result.ok();
    }

    @ApiOperation(value = "详情")
    @GetMapping(value = "/searchByid")
    @RequiresPermissions("agsjdm:point:view")
    public Result<InformationManagementFrom> searchByid(@ApiParam(value = "主键", required = true) @RequestParam(value = "id", required = true) String id) {
        InformationManagementFrom informationManagementFrom = informationManagementService.searchByid(id);
        return Result.ok(informationManagementFrom);
    }

    @ApiOperation(value = "修改")
    @PutMapping(value = "/update")
    @RequiresPermissions("agsjdm:point:update")
    public Result update(@Validated @RequestBody @ApiParam(name = "基础信息收集对象", value = "传入json格式", required = true) InformationManagementFrom im, BindingResult result) {
        //校验参数
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        //插入数据
        informationManagementService.updateIm(im);
        return Result.ok();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("/delete")
    @RequiresPermissions("agsjdm:point:delete")
    public Result delete(@ApiParam(value = "主键", required = true) @RequestParam(value = "id", required = true) String id) {
        informationManagementService.delete(id);
        return Result.ok();
    }

    @ApiOperation(value = "列表")
    @GetMapping("/list")
    public Result<PageUtils<InformationManagementTableVo>> list(@RequestParam(value = "wetName", required = false) @ApiParam(name = "wetName", value = "湿地区名称") String wetName
            , @RequestParam(value = "wetCode", required = false) @ApiParam(name = "wetCode", value = "湿地区编码") String wetCode
            , @RequestParam(value = "secondBasin", required = false) @ApiParam(name = "secondBasin", value = "所属二级流域") String secondBasin
            , @RequestParam(value = "startTime", required = false) @ApiParam(name = "startTime", value = "调查开始时间") String startTime
            , @RequestParam(value = "endTime", required = false) @ApiParam(name = "endTime", value = "调查结束时间") String endTime
            , @RequestParam(value = "pageNo", required = false, defaultValue = "0") @ApiParam(name = "pageNo", value = "分页页数") Integer pageNo
            , @RequestParam(value = "pageSize", required = false, defaultValue = "20") @ApiParam(name = "pageSize", value = "每页条数") Integer pageSize) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startTimeDate = null;
        Date endTimeDate = null;
        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
            startTimeDate = simpleDateFormat.parse(startTime);
            endTimeDate = simpleDateFormat.parse(endTime);
        }
        String[] keys = {"wetName", "wetCode", "secondBasin", "startTime", "endTime"};
        Object[] vals = {wetName, wetCode, secondBasin, startTimeDate, endTimeDate};
        PageUtils<InformationManagementTableVo> informationManagementTableVoPageUtils = informationManagementService.listPage(MapUtil.getParams(keys, vals), pageNo, pageSize);
        return Result.ok(informationManagementTableVoPageUtils);
    }
}
