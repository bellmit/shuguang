package com.sofn.fdpi.sysapi;

import com.sofn.common.config.FeignConfiguration;
import com.sofn.common.model.Result;
import com.sofn.common.utils.PageUtils;
import com.sofn.fdpi.sysapi.bean.*;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * @Description:
 * @author: xiaobo
 * @Date: 2020-12-28 16:25
 */
@FeignClient(
        value = "workflow-service",
        configuration = FeignConfiguration.class
)
public interface WorkApi {
    /**
     * 启动流程
     *
     * @param vo
     * @return
     */
    @RequestMapping(value = "/instance/startChainProcess", consumes = {MediaType.APPLICATION_JSON_VALUE})
    Result<String> startChainProcess(@RequestBody SubmitInstanceVo vo);


    /**
     * 提交工作项
     *
     * @param vo
     * @return
     */
    @RequestMapping(value = "/workItem/completeWorkItem", consumes = {MediaType.APPLICATION_JSON_VALUE})
    Result<String> completeWorkItem(@RequestBody SubmitInstanceVo vo);

    /**
     * 回退
     */
    @PostMapping(value = "/workItem/backWorkItem", consumes = {MediaType.APPLICATION_JSON_VALUE})
    Result<String> backWorkItem(@ApiParam(value = "回退参数", required = true) @RequestBody BackWorkItemForm form);

    /**
     * 删除缓存
     *
     * @param defId
     * @return
     */
    @GetMapping(value = "/definition/removeProDefAttrCache")
    Result<String> removeProDefAttrCache(@ApiParam(value = "流程定义ID", required = true)
                                         @RequestParam(name = "defId") String defId);

    /**
     * 获取流程实例历史对象[根据业务ID]
     *
     * @return
     */
    @PostMapping(value = "/instance/getProcessInstHisByIdAttr", consumes = {MediaType.APPLICATION_JSON_VALUE})
    Result<HisProcInstVo> getProcessInstHisByIdAttr(@Validated @ApiParam(name = "更新流程实体", required = true)
                                                    @RequestBody UpdateInstVo vo);


    /**
     * 获取流程活动对象列表[根据名称]
     */
    @PostMapping(value = "/instance/getActivityAllDataByName", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Result<PageUtils<ActivityDataVo>> getActivityAllDataByName(@RequestBody ActivityDataParamsVo activityDataParamsVo);
}
