package com.sofn.flow.controller;

import com.sofn.flow.service.ProcessWorkItemService;
import com.sofn.flow.util.BooleanUtil;
import com.sofn.flow.vo.BackWorkItemForm;
import com.sofn.flow.vo.ProcessWorkItemVo;
import com.sofn.flow.vo.Result;
import com.sofn.flow.vo.SubmitInstanceVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author heyongjie
 * @date 2020/5/13 13:59
 */
@Api(tags = "工作项管理")
@RequestMapping("/workItem")
@RestController
public class ProcessWorkItemController extends BasicController {

    @Autowired
    private ProcessWorkItemService processWorkItemService;

    @ApiOperation(value = "获取待办工作项列表")
    @GetMapping(value = "/getWorkItemList")
    public Result<List<ProcessWorkItemVo>> getWorkItemList(@ApiParam(value = "流程定义ID", required = true)
                                                           @RequestParam(value = "defId") String defId) {
        List<ProcessWorkItemVo> workItemList = processWorkItemService.getWorkItemList(null, defId,null);
        return Result.ok(workItemList);
    }

    @ApiOperation(value = "获取待抢工作项列表")
    @GetMapping(value = "/getWaitGrabWorkItemList")
    public Result<List<ProcessWorkItemVo>> getWaitGrabWorkItemList(@ApiParam(value = "流程实例ID", required = true)
                                                                   @RequestParam(value = "defId") String defId) {
        List<ProcessWorkItemVo> workItemList = processWorkItemService.getWaitGrabWorkItemList(null, defId,null);
        return Result.ok(workItemList);
    }

    @ApiOperation(value = "提交工作项")
    @PostMapping(value = "/completeWorkItem")
    public Result<String> completeWorkItem(@Validated @ApiParam(value = "提交流程实体", required = true) @RequestBody SubmitInstanceVo submitInstanceVo, BindingResult br) {
        if (br.hasErrors()) {
            return Result.error(getErrMsg(br));
        }

        return processWorkItemService.completeWorkItem(null, submitInstanceVo);
    }

    @ApiOperation(value = "重新分配工作项用户")
    @GetMapping(value = "/reassignWorkItem")
    public Result<String> reassignWorkItem(@ApiParam(value = "工作项ID", required = true)
                                           @RequestParam(value = "workItemId") String workItemId,
                                           @ApiParam(value = "源用户ID", required = true)
                                           @RequestParam(value = "sourceUserId") String sourceUserId,
                                           @ApiParam(value = "目标用户ID", required = true)
                                           @RequestParam(value = "targetUserId") String targetUserId) {
        return processWorkItemService.reassignWorkItem(null, workItemId, sourceUserId, targetUserId);
    }

    @ApiOperation(value = "获取工作项属性")
    @GetMapping(value = "/getWorkItemAttr")
    public Result<Map<String, Object>> getWorkItemAttr(@ApiParam(value = "工作项ID", required = true)
                                                       @RequestParam(value = "workItemId") String workItemId) {
        return processWorkItemService.getWorkItemAttr(null, workItemId);
    }

    @ApiOperation(value = "接收工作项")
    @GetMapping(value = "/acceptWorkItem")
    public Result<String> acceptWorkItem(@ApiParam(value = "流程实例ID", required = true)
                                         @RequestParam(value = "processInstanceId") String processInstanceId) {
        // 针对抢任务策略，如果是抢任务，需要先获取任务，如果当前活动组内分配策略为抢任务，则必须调用这个方法进行任务抢占
        return Result.ok(processWorkItemService.acceptWorkItem(null, processInstanceId, null)? BooleanUtil.TRUE:BooleanUtil.FALSE);
    }

    @ApiOperation(value = "获取所有可回退列表")
    @GetMapping(value = "/getBackWorkItemList")
    public Result<List<ProcessWorkItemVo>> getBackWorkItemList(@ApiParam(value = "活动实例ID", required = true)
                                                               @RequestParam(value = "actInstanceId") String actInstanceId) {
        // 获取所有可回退列表getBackWorkItemList
        return processWorkItemService.getBackWorkItemList(null, actInstanceId);
    }

    @ApiOperation(value = "回退")
    @PostMapping(value = "/backWorkItem")
    public Result<String> backWorkItem(@ApiParam(value = "回退参数", required = true)
                                       @RequestBody BackWorkItemForm form) {
        // 回退到指定的节点
        // backWorkItemId    需要回退到哪个节点，可以为空，如果为空则返回上一级节点，如果上一级有二个节点就报错
        return processWorkItemService.backWorkItem(null, form);
    }

}
