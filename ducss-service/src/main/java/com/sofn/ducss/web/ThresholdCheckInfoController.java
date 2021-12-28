package com.sofn.ducss.web;

import com.sofn.common.exception.SofnException;
import com.sofn.common.model.Result;
import com.sofn.ducss.model.CheckInfo;
import com.sofn.ducss.service.CheckInfoService;
import com.sofn.ducss.sysapi.bean.SysOrganization;
import com.sofn.ducss.util.SysOrgUtil;
import com.sofn.ducss.util.ThreadLocalUserLevelUtil;
import com.sofn.ducss.vo.checkcolor.CheckStrawLevelingVo;
import com.sofn.ducss.vo.checkcolor.CheckStrawProduceVo;
import com.sofn.ducss.vo.checkcolor.CheckStrawUtilzeVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "阈值计算相关接口")
@RestController
@RequestMapping("/thresholdCheckInfo")
public class ThresholdCheckInfoController {

    @Autowired
    private CheckInfoService checkInfoService;

    @GetMapping("/getCheckInfo")
    @ApiOperation(value = "计算审核列表的颜色", notes = "ducss:thresholdCheckInfo:getCheckInfo")
    public Result<List<CheckInfo>> getCheckInfo(@RequestParam("year") @ApiParam(value = "年度", required = true) String year,
                                                @RequestParam(value = "areaId", required = false) @ApiParam(value = "区划") String areaId,
                                                @RequestParam(value = "status", required = false) @ApiParam(value = "审核状态") String status) {

        try {
            setUserLevel();
            List<CheckInfo> checkInfo = checkInfoService.getCheckInfo(year, areaId, status);
            ThreadLocalUserLevelUtil.clear();
            return Result.ok(checkInfo);
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof SofnException) {
                throw new SofnException(((SofnException) e).getMsg());
            }
            throw new SofnException("计算失败");
        } finally {
            ThreadLocalUserLevelUtil.clear();
        }

    }

    @GetMapping("/getLevelingCheckInfo")
    @ApiOperation(value = "获取使用阈值计算好的离田还田量", notes = "ducss:thresholdCheckInfo:getLevelingCheckInfo")
    public Result<List<CheckStrawLevelingVo>> getLevelingCheckInfo(@RequestParam("year") @ApiParam(value = "年度") String year,
                                                                   @RequestParam("areaId") @ApiParam(value = "区划ID") String areaId) {
        try {
            setUserLevel();
            List<CheckStrawLevelingVo> levelingVo = checkInfoService.getLevelingVo(year, areaId);
            return Result.ok(levelingVo);
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof SofnException) {
                throw new SofnException(((SofnException) e).getMsg());
            }
            throw new SofnException("计算失败");
        } finally {
            ThreadLocalUserLevelUtil.clear();
        }
    }


    @GetMapping("/getUtilzeCheckInfo")
    @ApiOperation(value = "获取使用阈值计算好的秸秆利用量", notes = "ducss:thresholdCheckInfo:getUtilzeCheckInfo")
    public Result<List<CheckStrawUtilzeVo>> getUtilzeCheckInfo(@RequestParam("year") @ApiParam(value = "年度") String year,
                                                               @RequestParam("areaId") @ApiParam(value = "区划ID") String areaId) {

        try {
            setUserLevel();
            List<CheckStrawUtilzeVo> checkStrawUtilzeVoList = checkInfoService.getUtilzeCheckInfo(year, areaId);
            return Result.ok(checkStrawUtilzeVoList);
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof SofnException) {
                throw new SofnException(((SofnException) e).getMsg());
            }
            throw new SofnException("计算失败");
        } finally {
            ThreadLocalUserLevelUtil.clear();
        }
    }

    @GetMapping("/getStrawProduceCheckInfo")
    @ApiOperation(value = "获取使用阈值计算好的秸秆产生量", notes = "ducss:thresholdCheckInfo:getStrawProduceCheckInfo")
    public Result<List<CheckStrawProduceVo>> getStrawProduceCheckInfo(@RequestParam("year") @ApiParam(value = "年度") String year,
                                                                      @RequestParam("areaId") @ApiParam(value = "区划ID") String areaId) {

        try {
            setUserLevel();
            List<CheckStrawProduceVo> checkStrawProduceVos = checkInfoService.getStrawProduceCheckInfo(year, areaId);
            return Result.ok(checkStrawProduceVos);
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof SofnException) {
                throw new SofnException(((SofnException) e).getMsg());
            }
            throw new SofnException("计算失败");
        } finally {
            ThreadLocalUserLevelUtil.clear();
        }
    }

    /**
     * 设置用户级别
     */
    private void setUserLevel() {
        SysOrganization sysOrgInfo = SysOrgUtil.getSysOrgInfo();
        String organizationLevel = sysOrgInfo.getOrganizationLevel();
        ThreadLocalUserLevelUtil.setValue(organizationLevel);
    }


}
