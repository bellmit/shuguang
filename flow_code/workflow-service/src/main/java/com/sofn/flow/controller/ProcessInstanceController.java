package com.sofn.flow.controller;

import com.cvicse.workflow.api.WfFilter;
import com.cvicse.workflow.api.query.entity.RunningEntity;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.sofn.flow.model.Act;
import com.sofn.flow.service.ActService;
import com.sofn.flow.service.ProcessDefinitionService;
import com.sofn.flow.service.ProcessInstanceService;
import com.sofn.flow.util.BooleanUtil;
import com.sofn.flow.util.PageUtils;
import com.sofn.flow.util.RedisUtil;
import com.sofn.flow.vo.*;
import com.sofn.infroflow.util.IdUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 流程实例相关接口
 *
 * @author heyongjie
 * @date 2020/5/8 17:29
 */
@Api(tags = "流程实例管理")
@RequestMapping("/instance")
@RestController
public class ProcessInstanceController extends BasicController {

    @Autowired
    private ProcessInstanceService processInstanceServiceImpl;

    @Autowired
    private ProcessDefinitionService processDefinitionService;

    @Autowired
    RedisUtil redisUtil;

    @ApiOperation(value = "启动流程[包括完成第一个节点]")
    @PostMapping(value = "/startChainProcess")
    public Result<String> startChainProcess(@Validated
                                            @ApiParam(name = "启动流程实体", required = true)
                                            @RequestBody SubmitInstanceVo vo, BindingResult br) {
        if (br.hasErrors()) {
            return Result.error(getErrMsg(br));
        }

        // 根据流程定义启动流程
        return processInstanceServiceImpl.startProcess(null, vo);
    }

    @ApiOperation(value = "获取全部流程实例")
    @GetMapping(value = "/getAllProcessInstance")
    public Result<List<ProcessInstanceVo>> getAllProcessInstance(@ApiParam(value = "是否包括历史实例，[Y/N]")
                                                                 @RequestParam(value = "isHistory", required = false) String isHistory) {
        // 获取所有的流程定义
        if (StringUtils.isBlank(isHistory)) {
            isHistory = BooleanUtil.FALSE;
        }

        Result<List<ProcessInstanceVo>> allProcessInstance = processInstanceServiceImpl.getAllProcessInstance(null, isHistory);
        // 这里为了避免Service层循环引入 在controller层设置一下流程定义名称
        if (allProcessInstance != null && !CollectionUtils.isEmpty(allProcessInstance.getData())) {
            List<ProcessDefinitionVo> list = processDefinitionService.getProcessDefinition(null, null);
            if (!CollectionUtils.isEmpty(list)) {
                List<ProcessInstanceVo> processInstanceVos = allProcessInstance.getData();
                for (ProcessInstanceVo processInstanceVo : processInstanceVos) {
                    for (ProcessDefinitionVo vo : list) {
                        if (processInstanceVo.getProcessDefinitionId().equals(vo.getId())) {
                            processInstanceVo.setProcessDefName(vo.getName());
                            break;
                        }
                    }
                }
            }
        }

        return allProcessInstance;
    }

    @ApiOperation(value = "根据流程属性获取流程实例")
    @GetMapping(value = "/getProcessInstanceByAttr")
    public Result<List<ProcessInstanceVo>> getProcessInstanceByAttr(@ApiParam(value = "流程定义ID", required = true)
                                                                    @RequestParam(value = "defId") String defId,
                                                                    @ApiParam(value = "属性名称", required = true)
                                                                    @RequestParam(value = "idAttrName") String idAttrName,
                                                                    @ApiParam(value = "属性值", required = true)
                                                                    @RequestParam(value = "idAttrValue") String idAttrValue) {
        List<ProcessInstanceVo> list = processInstanceServiceImpl.getProcessInstanceByAttr(null, defId, idAttrName, idAttrValue);
        return Result.ok(list);
    }

    @ApiOperation(value = "开启流程实例")
    @GetMapping(value = "/startProcessInstance")
    public Result<String> startProcessInstance(@ApiParam(value = "流程实例ID", required = true)
                                               @RequestParam(value = "instId") String instId) {
        // 将之前创建流程实例启动，流程的第一个活动将被启动
        String loginUserName = redisUtil.getLoginUserName();
        if (StringUtils.isBlank(loginUserName)) {
            return Result.noLogin();
        }

        return processInstanceServiceImpl.startProcessInstance(null, loginUserName, instId);
    }

    @ApiOperation(value = "停止流程实例")
    @GetMapping(value = "/stopProcessInstance")
    public Result<String> stopProcessInstance(@ApiParam(value = "流程实例ID", required = true)
                                              @RequestParam(value = "instId") String instId) {
        //  终止流程，将未结束的流程结束掉，各活动的相关数据被拷贝到流程实例对象中
        processInstanceServiceImpl.stopProcessInstance(null, instId);
        return Result.ok("停止流程实例成功");
    }

    @ApiOperation(value = "停止流程实例[根据业务ID]")
    @PostMapping(value = "/stopProcessInstanceByAttr")
    public Result<String> stopProcessInstanceByAttr(@Validated
                                                    @ApiParam(name = "启动流程实体", required = true)
                                                    @RequestBody UpdateInstVo vo, BindingResult br) {
        if (br.hasErrors()) {
            return Result.error(getErrMsg(br));
        }

        // 根据流程定义启动流程
        processInstanceServiceImpl.stopProcessInstance(null, vo.getDefId(), vo.getIdAttrName(), vo.getIdAttrValue());
        return Result.ok("停止流程实例成功");
    }

    @ApiOperation(value = "取消流程实例")
    @GetMapping(value = "/cancelProcessInstance")
    public Result<String> cancelProcessInstance(@ApiParam(value = "流程实例ID", required = true)
                                                @RequestParam(value = "instId") String instId) {
        // 取消流程，各活动的相关数据不会被拷贝到流程实例对象中
        processInstanceServiceImpl.cancelProcessInstance(null, instId);
        return Result.ok("取消流程实例成功");
    }

    @ApiOperation(value = "取消流程实例[根据业务ID]")
    @PostMapping(value = "/cancelProcessInstanceByAttr")
    public Result<String> cancelProcessInstanceByAttr(@Validated
                                                      @ApiParam(name = "启动流程实体", required = true)
                                                      @RequestBody UpdateInstVo vo, BindingResult br) {
        if (br.hasErrors()) {
            return Result.error(getErrMsg(br));
        }

        // 根据流程定义启动流程
        processInstanceServiceImpl.cancelProcessInstance(null, vo.getDefId(), vo.getIdAttrName(), vo.getIdAttrValue());
        return Result.ok("取消流程实例成功");
    }

    @ApiOperation(value = "获取流程实例状态")
    @GetMapping(value = "/getProcessInstanceState")
    public Result<String> getProcessInstanceState(@ApiParam(value = "流程实例ID", required = true)
                                                  @RequestParam(value = "instId") String instId) {
        // 获取流程实例状态
        return processInstanceServiceImpl.getProcessInstanceState(null, instId);
    }

    @ApiOperation(value = "获取流程实例属性")
    @GetMapping(value = "/getProcessInstanceAttr")
    public Result<Map<String, Object>> getProcessInstanceAttr(@ApiParam(value = "流程实例ID", required = true)
                                                              @RequestParam(value = "instId") String instId) {
        // 获取流程实例的属性  可动态生成表单用于提交流程
        // 这个列表是流程实例在运行过程中所产生的应用上的业务数据。
        return processInstanceServiceImpl.getProcessInstanceAttr(null, instId);
    }

    @ApiOperation(value = "恢复流程实例")
    @GetMapping(value = "/reviveProcess")
    public Result<String> reviveProcess(@ApiParam(value = "流程实例ID", required = true)
                                        @RequestParam(value = "instId") String instId) {
        //  复活流程实例,将正常结束、终止和取消的流程实例恢复为流程结束前一样的状态
        processInstanceServiceImpl.reviveProcessInstance(null, instId);
        return Result.ok("恢复流程实例成功");
    }

    @ApiOperation(value = "恢复流程实例[根据业务ID]")
    @PostMapping(value = "/reviveProcessInstanceByAttr")
    public Result<String> reviveProcessInstanceByAttr(@Validated
                                                      @ApiParam(name = "启动流程实体", required = true)
                                                      @RequestBody UpdateInstVo vo, BindingResult br) {
        if (br.hasErrors()) {
            return Result.error(getErrMsg(br));
        }

        processInstanceServiceImpl.reviveProcessInstance(null, vo.getDefId(), vo.getIdAttrName(), vo.getIdAttrValue());
        return Result.ok("恢复流程实例成功");
    }

    @ApiOperation(value = "删除流程实例")
    @Delete(value = "/delProcessInstance")
    public Result<String> delProcessInstance(@ApiParam(value = "流程实例ID", required = true)
                                             @RequestParam(value = "instId") String instId) {
        // 刪除流程实例
        processInstanceServiceImpl.delProcessInstance(null, instId);
        return Result.ok("删除流程实例成功");
    }

    @ApiOperation(value = "删除流程实例[根据业务ID]")
    @PostMapping(value = "/delProcessInstanceByAttr")
    public Result<String> delProcessInstanceByAttr(@Validated
                                                   @ApiParam(name = "启动流程实体", required = true)
                                                   @RequestBody UpdateInstVo vo, BindingResult br) {
        if (br.hasErrors()) {
            return Result.error(getErrMsg(br));
        }

        processInstanceServiceImpl.delProcessInstance(null, vo.getDefId(), vo.getIdAttrName(), vo.getIdAttrValue());
        return Result.ok("删除流程实例成功");
    }

    @ApiOperation(value = "设置流程实例属性")
    @PostMapping(value = "/setValues")
    public Result<String> setValues(@ApiParam(value = "流程实例属性", required = true)
                                    @RequestParam(value = "values") Map<String, Object> values,
                                    @ApiParam(value = "流程实例ID", required = true)
                                    @RequestParam(value = "instId") String instId) {
        return processInstanceServiceImpl.setValues(null, values, instId);
    }

    @ApiOperation(value = "获取流程实例历史对象[根据业务ID]")
    @PostMapping(value = "/getProcessInstHisByIdAttr")
    public Result<HisProcInstVo> getProcessInstHisByIdAttr(@Validated
                                                           @ApiParam(name = "更新流程实体", required = true)
                                                           @RequestBody UpdateInstVo vo, BindingResult br) {
        if (br.hasErrors()) {
            return Result.error(getErrMsg(br));
        }

        HisProcInstVo instVo = processInstanceServiceImpl.getProcessInstHisByIdAttr(null, vo.getDefId(), vo.getIdAttrName(), vo.getIdAttrValue(), true);
        return Result.ok(instVo);
    }

//    @ApiOperation(value = "获取流程实例历史对象列表[根据属性]")
//    @PostMapping(value = "/getProcessInstHisByAttr")
//    public Result<PageUtils<Act>> getProcessInstHisByAttr(@ApiParam(name = "更新流程实体", required = true)
//                                                              @RequestBody HisActInstForm form, BindingResult br) {
//        if (br.hasErrors()) {
//            return Result.error(getErrMsg(br));
//        }
//
//        if (StringUtils.isNotBlank(form.getIdAttrName()) && StringUtils.isNotBlank(form.getIdAttrValue())){
//            if (CollectionUtils.isEmpty(form.getAttrs())){
//                form.setAttrs(new HashMap<>());
//            }
//
//            form.getAttrs().put(form.getIdAttrName(),form.getIdAttrValue());
//        }
//
//        PageUtils<Act> pageInfo = processInstanceServiceImpl.getProcessInstHisByAttr(form.getDefId(), form.getAttrs(), form.getPageNo(), form.getPageSize());
//        return Result.ok(pageInfo);
//    }

    @ApiOperation(value = "获取流程实例全部对象列表[根据属性]")
    @PostMapping(value = "/getProcessInstAllByAttr")
    public Result<List<ProcessVo>> getProcessInstAllByAttr(@RequestBody ProcessInstAllByAttrParam processInstAllByAttrParam) {


        List<InstanceParamVo> instanceParamVos = processInstAllByAttrParam.getInstanceParamVos();
        if(CollectionUtils.isEmpty(instanceParamVos)){
            instanceParamVos = Lists.newArrayList();
        }else {
            // 处理值
            for (InstanceParamVo instanceParamVo : instanceParamVos) {
                if(instanceParamVo != null){
                    String valueIsIn = instanceParamVo.getValueIsIn();
                    String value = instanceParamVo.getValue();
                    if(!StringUtils.isBlank(value)) {
                        // 如果使用in那么就需要按照逗号拆分
                        if(BooleanUtil.TRUE.equals(valueIsIn)){
                            Set<String> valueSet = Sets.newHashSet(value.split(","));
                            String sqlId = IdUtil.getSqlId(valueSet);
                            instanceParamVo.setValue(sqlId);
                        }else {
                            instanceParamVo.setValue("'" + value + "'");
                        }
                    }
                }
            }
        }

        InstanceParamVo instanceParamVo = new InstanceParamVo();
        List<String> idAttrValues = processInstAllByAttrParam.getIdAttrValues();
        if(!CollectionUtils.isEmpty(idAttrValues)){
            instanceParamVo.setValue(IdUtil.getSqlId(Sets.newHashSet(idAttrValues)));
            instanceParamVo.setValueIsIn("Y");
        }
        instanceParamVo.setKey(processInstAllByAttrParam.getIdAttrName());
        instanceParamVos.add(instanceParamVo);




        List<ProcessVo> pageInfo = processInstanceServiceImpl.getProcessInstAllByAttr(processInstAllByAttrParam.getDefIds(),

                processInstAllByAttrParam.getIdAttrName(),
                processInstAllByAttrParam.getIdAttrValues(),
                instanceParamVos, processInstAllByAttrParam.getStartTime(), processInstAllByAttrParam.getEndTime(),
                processInstAllByAttrParam.getPageNo(), processInstAllByAttrParam.getPageSize());
        return Result.ok(pageInfo);
    }


    @ApiOperation(value = "获取流程活动对象列表[根据名称]")
    @PostMapping(value = "/getActivityAllDataByName")
    public Result<PageUtils<ActivityDataVo>> getActivityAllDataByName(@RequestBody ActivityDataParamsVo activityDataParamsVo) {

        List<InstanceParamTwoVo> instanceParamVos = null;
        if(!CollectionUtils.isEmpty(activityDataParamsVo.getInstanceParamVos())){
            instanceParamVos = activityDataParamsVo.getInstanceParamVos();
            instanceParamVos.forEach((e)->{
                if(e.getValues().size() > 1){
                    e.setValueIsIn("N");
                }else {
                    e.setValueIsIn("Y");
                }
            });
        }
        PageUtils<ActivityDataVo> pageInfo = processInstanceServiceImpl.getActivityAllDataByName(activityDataParamsVo.getDefId(),
                                                                                                activityDataParamsVo.getIdAttrName(),
                                                                                                activityDataParamsVo.getIdAttrValues(),
                                                                                                activityDataParamsVo.getActivityMaker(),
                                                                                                instanceParamVos,
                                                                                                activityDataParamsVo.getStartTime(),
                                                                                                activityDataParamsVo.getEndTime(),
                                                                                                activityDataParamsVo.getPageNo(),
                                                                                                activityDataParamsVo.getPageSize());
        return Result.ok(pageInfo);
    }
}
