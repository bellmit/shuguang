package com.sofn.flow.controller;

import com.sofn.flow.service.ProcessDefinitionService;
import com.sofn.flow.util.JsonUtils;
import com.sofn.flow.util.RedisUtil;
import com.sofn.flow.util.ReturnJsonUtil;
import com.sofn.flow.vo.ProcessDefinitionAttrVo;
import com.sofn.flow.vo.ProcessDefinitionVo;
import com.sofn.flow.vo.Result;
import com.sofn.flow.vo.SubmitInstanceVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 获取所有的流程定义
 * @author heyongjie
 * @date 2020/5/7 11:42
 */
@Api(tags = "流程定义管理")
@RequestMapping("/definition")
@RestController
public class ProcessDefinitionController extends BasicController {

    @Autowired
    private ProcessDefinitionService processDefinitionServiceImpl;

    @Autowired
    RedisUtil redisUtil;

    @ApiOperation(value = "获取流程定义列表")
    @GetMapping(value = "/getProcessDefinition")
    public Result<List<ProcessDefinitionVo>> getProcessDefinition(@ApiParam(value = "流程定义ID")
                                                                  @RequestParam(name = "defId", required = false) String defId) {
        return Result.ok(processDefinitionServiceImpl.getProcessDefinition(null,defId));
    }

    @ApiOperation(value = "根据流程定义id获取流程定义属性")
    @GetMapping(value = "/getProcessDefinitionAttr")
    public Result<List<ProcessDefinitionAttrVo>> getProcessDefinitionAttr(@ApiParam(value = "流程定义ID", required = true)
                                                                          @RequestParam(name = "defId") String defId) {
        return processDefinitionServiceImpl.getProcessDefinitionAttr(defId);
    }

    @ApiOperation(value = "根据流程定义id移除属性缓存")
    @GetMapping(value = "/removeProDefAttrCache")
    public Result<String> removeProDefAttrCache(@ApiParam(value = "流程定义ID", required = true)
                                                @RequestParam(name = "defId") String defId) {
        redisUtil.removeProcessDefAttr(defId);
        return Result.ok("删除缓存成功");
    }

}
