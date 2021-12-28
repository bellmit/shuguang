package com.sofn.fdpi.web;

import com.sofn.common.model.Result;
import com.sofn.common.utils.*;
import com.sofn.fdpi.constants.Constants;
import com.sofn.fdpi.model.ChangeRecord;
import com.sofn.fdpi.model.ChangeRecordProcess;
import com.sofn.fdpi.model.ChangeType;
import com.sofn.fdpi.service.ChangeRecordService;
import com.sofn.fdpi.util.MapUtil;
import com.sofn.fdpi.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: wenjunyun
 * @Date: 2019/12/17 14:52
 */
@Slf4j
@Api(value = "APP_物种变更相关接口", tags = "APP_物种变更相关接口")
@RestController
@RequestMapping("/app/changeRecord")
public class ChangeRecordAppController {


    @Autowired
    private ChangeRecordService changeRecordService;


    @ApiOperation("保存物种变更,第一次保存调用，无对象ID的时候调用")
    @RequiresPermissions("fdpi:speciesChangeApply:create")
    @PostMapping("saveChangeRecord")
    public Result<String> saveChangeRec(@ApiParam(value = "变更记录各个字段", required = true) @RequestBody ChangeRecordVO changeRecordVO) {
        if (changeRecordVO.getSpeciesId() == null || changeRecordVO.getSpeciesId().equals("")) {
            return Result.error("物种是必填内容！");
        }
        if (Integer.parseInt(changeRecordVO.getChangeNum()) > 100000000) {
            return Result.error("单次变更数目不能大于100000000！");
        }
        try {
            Integer.parseInt(changeRecordVO.getChangeReason());
        } catch (Exception e) {
            return Result.error("请传入变更原因代码！");
        }
        ChangeRecord changeRecord = new ChangeRecord();
        //将vo转到databean，并且完善创建人，时间，等等信息

        String id = IdUtil.getUUId();
        changeRecord.setSource(changeRecordVO.getSource());
        changeRecord.setMode(changeRecordVO.getMode());
        changeRecord.setChangeCompany(UserUtil.getLoginUserOrganizationId());
        changeRecord.setChangeNum(changeRecordVO.getChangeNum());
        changeRecord.setCreateUserId(UserUtil.getLoginUserId());
        changeRecord.setCreateTime(new Date());
        changeRecord.setChangeReason(changeRecordVO.getChangeReason());
        changeRecord.setChangeStatus("0");
        changeRecord.setFileId(changeRecordVO.getFileId());
        changeRecord.setFileName(changeRecordVO.getFileName());
        changeRecord.setFilePath(changeRecordVO.getFilePath());
        changeRecord.setRemark(changeRecordVO.getRemark());
        changeRecord.setSpeciesId(changeRecordVO.getSpeciesId());
        changeRecord.setDelFlag("N");
        changeRecord.setId(id);
        changeRecord.setFirstOpnion("");
        changeRecord.setSecondOpnion("");
        changeRecord.setChangeDate(changeRecordVO.getChangeDate());
        changeRecord.setRequestReport(changeRecordVO.getRequestReport());
//        ----update by xiaobo  新加字段历史物种数量
        changeRecord.setHisSpeNum(changeRecordVO.getHisSpeNum());
//
        ChangeRecordProcess changeRecordProcess = new ChangeRecordProcess();
        changeRecordProcess.setAdvice("保存");
        changeRecordProcess.setChangeRecordId(changeRecord.getId());
        changeRecordProcess.setContent("用户保存!");
        changeRecordProcess.setConTime(new Date());
        changeRecordProcess.setId(IdUtil.getUUId());
        changeRecordProcess.setOpUserId(UserUtil.getLoginUser().getNickname());
        changeRecordProcess.setStatus("0");

        changeRecordService.saveChangeRec(changeRecord, changeRecordProcess);

        return Result.ok(id);
    }


    @ApiOperation("修改物种变更，第二次或者第二次以后的保存操作调用，已经存在ID的时候调用")
    @PostMapping("updateChangeRecord")
    @RequiresPermissions("fdpi:speciesChangeApply:update")
    public Result<String> updateChangeRecord(@ApiParam(value = "变更记录各个字段", required = true) @RequestBody ChangeRecordVO changeRecordVO) {
        if (changeRecordVO.getSpeciesId() == null || changeRecordVO.getSpeciesId().equals("")) {
            return Result.error("物种是必填内容！");
        }
        if (Integer.parseInt(changeRecordVO.getChangeNum()) > 100000000) {
            return Result.error("单次变更数目不能大于100000000！");
        }
        try {
            Integer.parseInt(changeRecordVO.getChangeReason());
        } catch (Exception e) {
            return Result.error("请传入变更原因代码！");
        }
        ChangeRecord changeRecord = new ChangeRecord();
        //将vo转到databean，并且完善创建人，时间，等等信息
        //changeRecord.setChangeCompany(UserUtil.getLoginUserOrganizationId());
        changeRecord.setSource(changeRecordVO.getSource());
        changeRecord.setMode(changeRecordVO.getMode());
        changeRecord.setChangeReason(changeRecordVO.getChangeReason());
        changeRecord.setChangeNum(changeRecordVO.getChangeNum());
        changeRecord.setUpdateUserId(UserUtil.getLoginUserId());
        changeRecord.setUpdateTime(new Date());
        changeRecord.setChangeReason(changeRecordVO.getChangeReason());
        changeRecord.setChangeStatus("0");
        changeRecord.setFileId(changeRecordVO.getFileId());
        changeRecord.setFileName(changeRecordVO.getFileName());
        changeRecord.setFilePath(changeRecordVO.getFilePath());
        changeRecord.setRemark(changeRecordVO.getRemark());
        changeRecord.setSpeciesId(changeRecordVO.getSpeciesId());
        changeRecord.setDelFlag("N");
        changeRecord.setId(changeRecordVO.getId());
        changeRecord.setFirstOpnion("");
        changeRecord.setSecondOpnion("");
        changeRecord.setChangeDate(changeRecordVO.getChangeDate());
        changeRecord.setRequestReport(changeRecordVO.getRequestReport());
//        ----update by xiaobo  新加字段历史物种数量
        changeRecord.setHisSpeNum(changeRecordVO.getHisSpeNum());
//
        ChangeRecordProcess changeRecordProcess = new ChangeRecordProcess();
        changeRecordProcess.setAdvice("修改");
        changeRecordProcess.setChangeRecordId(changeRecord.getId());
        changeRecordProcess.setContent("用户修改!");
        changeRecordProcess.setConTime(new Date());
        changeRecordProcess.setId(IdUtil.getUUId());
        changeRecordProcess.setOpUserId(UserUtil.getLoginUser().getNickname());
        changeRecordProcess.setStatus("0");

        changeRecordService.updateChangeRecord(changeRecord, changeRecordProcess);

        return Result.ok("修改成功!");
    }


    @ApiOperation("获取当前用户所在企业的物种下拉列表")
    @PostMapping("listSpeciesSelect")
    //@RequiresPermissions("changeRecord:listSpeciesSelect")
    public Result<List<SpeciesSelect>> listSpeciesSelect() {
        Map map = new HashMap<>();
        map.put("companyId", UserUtil.getLoginUserOrganizationId());
        map.put("type", "1");
        List<SpeciesSelect> list = changeRecordService.listSpeciesSelect(map);
        return Result.ok(list);
    }


    @ApiOperation("获取变更原因下拉列表")
    @PostMapping("getChangeType")
    //@RequiresPermissions("changeRecord:getChangeType")
    public Result<List<ChangeType>> getChangeType() {
        List<ChangeType> list = changeRecordService.listChangeType();
        return Result.ok(list);
    }

    @ApiOperation("获取物种变更填写时自动带出的企业信息")
    @PostMapping("getCompany")
    //@RequiresPermissions("changeRecord:getCompany")
    public Result<ChangeRecordCompanyVO> getCompany() {
        Map map = new HashMap();
        map.put("companyId", UserUtil.getLoginUserOrganizationId());
        ChangeRecordCompanyVO changeRecordCompanyVO = changeRecordService.getCompanyByIdOrName(map);
        return Result.ok(changeRecordCompanyVO);
    }


    @ApiOperation("获取审核时物种变更填写详情信息")
    @PostMapping("getChangeRecordDetailById")
    @RequiresPermissions(value = {"fdpi:speciesChangeApply:view", "fdpi:speciesChangeApprove:view"}, logical = Logical.OR)
    public Result<ChangeRecordDetailVO> getChangeRecordDetailById(@RequestParam(required = true) @ApiParam(value = "编辑时传入变更记录ID，带出保存数据", required = true) String changeRecordId) {
        Map map = new HashMap();
        map.put("id", changeRecordId);
        ChangeRecordDetailVO changeRecordDetailVO = changeRecordService.getChangeRecordDetailById(map);
        return Result.ok(changeRecordDetailVO);
    }


    @ApiOperation("上报物种变更,未保存过直接上报，除ID外的全部属性都需要填充，已保存未编辑只是上报，只需要传对象的ID")
    @PostMapping("saveOrUpdateChangeRec")
    @RequiresPermissions("fdpi:speciesChangeApply:report")
    public Result<String> saveOrUpdateChangeRec(@ApiParam(value = "变更记录各个字段数据。备注：变更记录ID可能为空，即用户跳过保存直接上报", required = true) @RequestBody ChangeRecordVO changeRecordVO) {
        if (changeRecordVO.getId() == null) {
            if (changeRecordVO.getSpeciesId() == null || changeRecordVO.getSpeciesId().equals("")) {
                return Result.error("物种是必填内容！");
            }
            if (Integer.parseInt(changeRecordVO.getChangeNum()) > 100000000) {
                return Result.error("单次变更数目不能大于100000000！");
            }
            try {
                Integer.parseInt(changeRecordVO.getChangeReason());
            } catch (Exception e) {
                return Result.error("请传入变更原因代码！");
            }
            String id = IdUtil.getUUId();
            ChangeRecord changeRecord = new ChangeRecord();
            changeRecord.setSource(changeRecordVO.getSource());
            changeRecord.setMode(changeRecordVO.getMode());
            changeRecord.setChangeCompany(UserUtil.getLoginUserOrganizationId());
            changeRecord.setChangeNum(changeRecordVO.getChangeNum());
            changeRecord.setCreateUserId(UserUtil.getLoginUserId());
            changeRecord.setCreateTime(new Date());
            changeRecord.setChangeReason(changeRecordVO.getChangeReason());
            changeRecord.setChangeStatus("1");
            changeRecord.setIsReport("Y");
            changeRecord.setFileId(changeRecordVO.getFileId());
            changeRecord.setFileName(changeRecordVO.getFileName());
            changeRecord.setFilePath(changeRecordVO.getFilePath());
            changeRecord.setRemark(changeRecordVO.getRemark());
            changeRecord.setSpeciesId(changeRecordVO.getSpeciesId());
            changeRecord.setDelFlag("N");
            changeRecord.setId(IdUtil.getUUId());
            changeRecord.setFirstOpnion("");
            changeRecord.setSecondOpnion("");
            changeRecord.setChangeDate(changeRecordVO.getChangeDate());
            changeRecord.setRequestReport(changeRecordVO.getRequestReport());
            //        ----update by xiaobo  新加字段历史物种数量
            changeRecord.setHisSpeNum(changeRecordVO.getHisSpeNum());
            //
            ChangeRecordProcess changeRecordProcess = new ChangeRecordProcess();
            changeRecordProcess.setAdvice("上报");
            changeRecordProcess.setChangeRecordId(changeRecord.getId());
            changeRecordProcess.setContent("用户未保存直接上报!");
            changeRecordProcess.setConTime(new Date());
            changeRecordProcess.setId(IdUtil.getUUId());

            changeRecordProcess.setOpUserId(UserUtil.getLoginUser().getNickname());
            changeRecordProcess.setStatus("1");

            changeRecordService.saveChangeRec(changeRecord, changeRecordProcess);
            return Result.ok(id);
        } else {
//            ChangeRecord changeRecord=new ChangeRecord();
//            changeRecord.setId(changeRecordVO.getId());
//            changeRecord.setChangeStatus("1");
//
//            changeRecord.setUpdateUserId(UserUtil.getLoginUserId());
//            changeRecord.setUpdateTime(new Date());
//            changeRecord.setDelFlag("N");
//            changeRecord.setFirstOpnion("");
//            changeRecord.setSecondOpnion("");
//            //        ----update by xiaobo  新加字段历史物种数量
//            changeRecord.setHisSpeNum(changeRecordVO.getHisSpeNum());
            ChangeRecord changeRecord = new ChangeRecord();
            //将vo转到databean，并且完善创建人，时间，等等信息
            //changeRecord.setChangeCompany(UserUtil.getLoginUserOrganizationId());
            changeRecord.setChangeReason(changeRecordVO.getChangeReason());
            changeRecord.setChangeNum(changeRecordVO.getChangeNum());
            changeRecord.setUpdateUserId(UserUtil.getLoginUserId());
            changeRecord.setUpdateTime(new Date());
            changeRecord.setChangeReason(changeRecordVO.getChangeReason());
            changeRecord.setChangeStatus("1");
            changeRecord.setIsReport("Y");
            changeRecord.setFileId(changeRecordVO.getFileId());
            changeRecord.setFileName(changeRecordVO.getFileName());
            changeRecord.setFilePath(changeRecordVO.getFilePath());
            changeRecord.setRemark(changeRecordVO.getRemark());
            changeRecord.setSpeciesId(changeRecordVO.getSpeciesId());
            changeRecord.setDelFlag("N");
            changeRecord.setId(changeRecordVO.getId());
            changeRecord.setFirstOpnion("");
            changeRecord.setSecondOpnion("");
            changeRecord.setChangeDate(changeRecordVO.getChangeDate());
            changeRecord.setRequestReport(changeRecordVO.getRequestReport());
//        ----update by xiaobo  新加字段历史物种数量
            changeRecord.setHisSpeNum(changeRecordVO.getHisSpeNum());
            //


            ChangeRecordProcess changeRecordProcess = new ChangeRecordProcess();
            changeRecordProcess.setAdvice("上报");
            changeRecordProcess.setChangeRecordId(changeRecord.getId());
            changeRecordProcess.setContent("用户保存后再上报!");
            changeRecordProcess.setConTime(new Date());
            changeRecordProcess.setId(IdUtil.getUUId());
            changeRecordProcess.setOpUserId(UserUtil.getLoginUser().getNickname());
            changeRecordProcess.setStatus("1");

            changeRecordService.updateChangeRecord(changeRecord, changeRecordProcess);

            return Result.ok("上报成功!");
        }


    }


    @ApiOperation("初审通过,包含参数：变更记录ID")
    @PostMapping("firstPass")
    @RequiresPermissions("fdpi:speciesChangeApprove:approve")
    public Result<String> firstPass(@RequestParam(required = true) @ApiParam(value = "包含参数：变更记录ID", required = true) String changeRecordId
    ) {
        ChangeRecord changeRecord = new ChangeRecord();
        ChangeRecordProcess changeRecordProcess = new ChangeRecordProcess();
//        Map map=new HashMap();
//        map.put("id",changeRecordId);
//        //获取记录ID，以便得到企业ID
//        ChangeRecordDetailVO changeRecordDetailVO=changeRecordService.getChangeRecordDetailById(map);
//        map.put("companyId",changeRecordDetailVO.getCompanyId());
//        //根据记录的企业ID ，得到企业信息，包括直属机构以及省级机构
//        ChangeRecordCompanyVO changeRecordCompanyVO=changeRecordService.getCompanyByIdOrName(map);
//        //如果直属跟省级机构相同，则初审即复审
//        String orgInfoJosn = UserUtil.getLoginUserOrganizationInfo();
//        OrganizationInfo orgInfo = JsonUtils.json2obj(orgInfoJosn, OrganizationInfo.class);

        changeRecord.setId(changeRecordId);
        changeRecord.setChangeStatus("3");
        changeRecord.setFirstOpnion("通过!");
        changeRecord.setUpdateTime(new Date());

        changeRecordProcess.setAdvice("审核通过！");
        changeRecordProcess.setChangeRecordId(changeRecordId);
        changeRecordProcess.setContent("审核通过!");
        changeRecordProcess.setConTime(new Date());
        changeRecordProcess.setId(IdUtil.getUUId());
        changeRecordProcess.setOpUserId(UserUtil.getLoginUser().getNickname());
        changeRecordProcess.setStatus("3");


        changeRecordService.updateChangeRecord(changeRecord, changeRecordProcess);

        return Result.ok("操作成功!");
    }

    @ApiOperation("初审退回,包含参数：变更记录ID；初审退回意见")
    @PostMapping("firstBack")
    @RequiresPermissions("fdpi:speciesChangeApprove:back")
    public Result<String> firstBack(@ApiParam(value = "包含参数：变更记录ID；初审退回意见", required = true) @RequestBody ChangeRecordVO changeRecordVO) {
        ChangeRecord changeRecord = new ChangeRecord();
        changeRecord.setId(changeRecordVO.getId());
        changeRecord.setChangeStatus("2");
        changeRecord.setFirstOpnion(changeRecordVO.getFirstOpnion());

        ChangeRecordProcess changeRecordProcess = new ChangeRecordProcess();
        changeRecordProcess.setAdvice(changeRecord.getFirstOpnion());
        changeRecordProcess.setChangeRecordId(changeRecord.getId());
        changeRecordProcess.setContent("初审退回!");
        changeRecordProcess.setConTime(new Date());
        changeRecordProcess.setId(IdUtil.getUUId());
        changeRecordProcess.setOpUserId(UserUtil.getLoginUser().getNickname());
        changeRecordProcess.setStatus("2");


        changeRecordService.updateChangeRecord(changeRecord, changeRecordProcess);

        return Result.ok("操作成功!");
    }


//    @ApiOperation("复审通过,包含参数:变更记录ID")
//    @PostMapping("secondPass")
//    //@RequiresPermissions("changeRecord:saveChangeRec")
//    public Result<String> secondPass(@RequestParam(required = true) @ApiParam(value="包含参数:变更记录ID",required = true)  String changeRecordId){
//        ChangeRecord changeRecord=new ChangeRecord();
//        changeRecord.setId(changeRecordId);
//        changeRecord.setChangeStatus("5");
//        changeRecord.setChangeDate(new Date());
//        changeRecord.setFirstOpnion("通过!");
//
//        ChangeRecordProcess changeRecordProcess=new ChangeRecordProcess();
//        changeRecordProcess.setAdvice("复审通过!");
//        changeRecordProcess.setChangeRecordId(changeRecord.getId());
//        changeRecordProcess.setContent("复审通过!");
//        changeRecordProcess.setConTime(new Date());
//        changeRecordProcess.setId(IdUtil.getUUId());
//        changeRecordProcess.setOpUserId(UserUtil.getLoginUser().getNickname());
//        changeRecordProcess.setStatus("5");
//
//        changeRecordService.updateChangeRecord(changeRecord,changeRecordProcess);
//        return Result.ok("操作成功!");
//    }
//
//    @ApiOperation("复审退回,包含参数:变更记录ID；复审退回意见")
//    @PostMapping("secondBack")
//    //@RequiresPermissions("changeRecord:saveChangeRec")
//    public Result<String> secondBack(@ApiParam(value="包含参数:变更记录ID；复审退回意见" ,required = true) @RequestBody ChangeRecordVO changeRecordVO){
//        ChangeRecord changeRecord=new ChangeRecord();
//        changeRecord.setId(changeRecordVO.getId());
//        changeRecord.setChangeStatus("4");
//        changeRecord.setSecondOpnion(changeRecordVO.getSecondOpnion());
//
//        ChangeRecordProcess changeRecordProcess=new ChangeRecordProcess();
//        changeRecordProcess.setAdvice(changeRecord.getSecondOpnion());
//        changeRecordProcess.setChangeRecordId(changeRecord.getId());
//        changeRecordProcess.setContent("复审退回!");
//        changeRecordProcess.setConTime(new Date());
//        changeRecordProcess.setId(IdUtil.getUUId());
//        changeRecordProcess.setOpUserId(UserUtil.getLoginUser().getNickname());
//        changeRecordProcess.setStatus("4");
//
//        changeRecordService.updateChangeRecord(changeRecord,changeRecordProcess);
//        return Result.ok("操作成功!");
//    }

    @ApiOperation("物种变更管理列表")
    @PostMapping("changeManager")
    @RequiresPermissions(value = {"fdpi:speciesChangeApply:query", "fdpi:speciesChangeApprove:query"}, logical = Logical.OR)
    public Result<ChangeRecordDetailVO> changeManager(
            @RequestParam(required = true) @ApiParam(value = "列表类型，1：企业变更列表；2：审核列表", required = true) String queryType,
            @RequestParam(required = false) @ApiParam("单位名称") String companyName,
            @RequestParam(required = false) @ApiParam("物种ID") String speciesId,
            @RequestParam(required = false) @ApiParam("审核状态:0:保存；1：上报；2：审核退回；3审核通过") String changeStatus,
            @RequestParam(required = false) @ApiParam("变更开始时间") String changeDateStart,
            @RequestParam(required = false) @ApiParam("变更结束时间") String changeDateEnd,
            @RequestParam(value = "pageNo") Integer pageNo,
            @RequestParam(value = "pageSize") Integer pageSize) {
        String[] keys = {"changeDateStart", "changeDateEnd", "queryType", "companyName", "speciesId", "changeStatus"};
        String[] vals = {changeDateStart, changeDateEnd, queryType, companyName, speciesId, changeStatus};
        if (StringUtils.hasText(changeDateStart))
            vals[0] = changeDateStart + " 00:00:00";
        if (StringUtils.hasText(changeDateEnd))
            vals[1] = changeDateEnd + " 23:59:59";
        return Result.ok(changeRecordService.listChangeRecordDetailVO(MapUtil.getParams(keys, vals), pageNo, pageSize));
    }


    @ApiOperation("软删除某条变更记录")
    @PostMapping("deleteChangeRecord")
    @RequiresPermissions("fdpi:speciesChangeApply:delete")
    public Result<String> deleteChangeRecord(@RequestParam(required = true) @ApiParam(value = "传入需要删除的变更记录ID", required = true) String changeRecordId) {
        ChangeRecord changeRecord = new ChangeRecord();
        changeRecord.setId(changeRecordId);
        changeRecord.setDelFlag("Y");

        ChangeRecordProcess changeRecordProcess = new ChangeRecordProcess();
        changeRecordProcess.setAdvice("删除");
        changeRecordProcess.setChangeRecordId(changeRecord.getId());
        changeRecordProcess.setContent("删除!");
        changeRecordProcess.setConTime(new Date());
        changeRecordProcess.setId(IdUtil.getUUId());
        changeRecordProcess.setOpUserId(UserUtil.getLoginUser().getNickname());
        changeRecordProcess.setStatus("6");

        changeRecordService.deleteChangeRecord(changeRecord, changeRecordProcess);
        return Result.ok("删除成功!");
    }


    @ApiOperation("物种变更审核进度查询")
    @PostMapping("listChangeRecordProcess")
    @RequiresPermissions("fdpi:speciesChangeSpeed:query")
    public Result<PageUtils<ChangeRecordProcessVo>> listChangeRecordProcess(@RequestParam(required = false) @ApiParam("审核状态:0:保存；1：上报；2：审核退回；3初审通过") String changeStatus,
                                                                            @RequestParam(required = false) @ApiParam("变更开始时间") String changeDateStart,
                                                                            @RequestParam(required = false) @ApiParam("变更结束时间") String changeDateEnd,
                                                                            @RequestParam(value = "pageNo") Integer pageNo,
                                                                            @RequestParam(value = "pageSize") Integer pageSize) {
        Boolean a = Constants.WORKFLOW.equals(BoolUtils.N);

        Map map = new HashMap();
        if (a) {
            map.put("curCompanyId", UserUtil.getLoginUserOrganizationId());
            if (changeStatus != null && !changeStatus.trim().equals("")) {
                map.put("changeStatus", changeStatus);
            }
            if (changeDateStart != null && !changeDateStart.trim().equals("")) {
                map.put("changeDateStart", changeDateStart);
            }
            if (changeDateEnd != null && !changeDateEnd.trim().equals("")) {
                map.put("changeDateEnd", changeDateEnd);
            }
        } else {
            map.put("curCompanyId", UserUtil.getLoginUserOrganizationId());
            if (changeStatus != null && !changeStatus.trim().equals("")) {
                map.put("status", changeStatus);
            }
            if (changeDateStart != null && !changeDateStart.trim().equals("")) {
                map.put("changeDateStart", changeDateStart);
            }
            if (changeDateEnd != null && !changeDateEnd.trim().equals("")) {
                map.put("changeDateEnd", changeDateEnd);
            }
        }

        PageUtils<ChangeRecordProcessVo> pu = Constants.WORKFLOW.equals(BoolUtils.N)
                ? changeRecordService.listProcess(map, pageNo, pageSize)
                : changeRecordService.listProcessByassembly(map, pageNo, pageSize);
        return Result.ok(pu);
    }


    @ApiOperation("物种变更审核流水查询")
    @GetMapping("getChangeRecordProcess")
    public Result<List<ChangeRecordProcessVo>> listChangeRecordProcess(@RequestParam(required = true) @ApiParam("changeRecordProcess") String changeRecordId) {
        Map map = new HashMap();
        map.put("changeRecordId", changeRecordId);
        List<ChangeRecordProcessVo> pu = Constants.WORKFLOW.equals(BoolUtils.N)
                ? changeRecordService.getProcess(map)
                : changeRecordService.getProcessByassembly(map);
        return Result.ok(pu);
    }

}
