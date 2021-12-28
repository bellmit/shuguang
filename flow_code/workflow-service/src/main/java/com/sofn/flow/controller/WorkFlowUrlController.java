package com.sofn.flow.controller;

import com.sofn.flow.enums.WorkFlowEnum;
import com.sofn.flow.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


/**
 * @ClassName WorkFlowUrlController
 * @Description: TODO
 * @Author liling
 * @Date 2020/12/29
 * @Version V1.0
 **/

@Api(tags = "工作流程url")
@RequestMapping("/url")
@RestController
public class WorkFlowUrlController {

    @Value("${workflow.model-url}")
    private String workFlowUrl;

    @Value("${workflow.other-url}")
    private String workOtherUrl;

    @ApiOperation(value = "获取流程URL")
    @GetMapping(value = "/getOtherUrl")
    public Result<String> getOtherUrl(@ApiParam(value = "展示流程定义图key") @RequestParam(name = "key", required = false) String key) {
        String displayWithModelId = WorkFlowEnum.HTML_01.getCode();
        String displayWithProcId = WorkFlowEnum.HTML_02.getCode();
        String displayWithInstId = WorkFlowEnum.HTML_03.getCode();
        String noParamHistory = WorkFlowEnum.HTML_04.getCode();
        String historyWithOperatorId = WorkFlowEnum.HTML_05.getCode();
        String displayWithPIAndOI = WorkFlowEnum.HTML_06.getCode();
        String displayWithIIAndOI = WorkFlowEnum.HTML_07.getCode();
        String displayWithMoreParam = WorkFlowEnum.HTML_08.getCode();

        if(StringUtils.isEmpty(key)){
            Map<String,String> map = new HashMap<>();
            map.put(displayWithModelId,workOtherUrl+WorkFlowEnum.HTML_01.getDescription());
            map.put(displayWithProcId,workOtherUrl+WorkFlowEnum.HTML_02.getDescription());
            map.put(displayWithInstId,workOtherUrl+WorkFlowEnum.HTML_03.getDescription());
            map.put(noParamHistory,workOtherUrl+WorkFlowEnum.HTML_04.getDescription());
            map.put(historyWithOperatorId,workOtherUrl+WorkFlowEnum.HTML_05.getDescription());
            map.put(displayWithPIAndOI,workOtherUrl+WorkFlowEnum.HTML_06.getDescription());
            map.put(displayWithIIAndOI,workOtherUrl+WorkFlowEnum.HTML_07.getDescription());
            map.put(displayWithMoreParam,workOtherUrl+WorkFlowEnum.HTML_08.getDescription());
            return Result.ok(map);
        }else {
            if(key.equals(displayWithModelId)){
                return Result.ok(workOtherUrl+WorkFlowEnum.HTML_01.getDescription());
            }else if (key.equals(displayWithProcId)){
                return Result.ok(workOtherUrl+WorkFlowEnum.HTML_02.getDescription());
            }
            else if (key.equals(displayWithInstId)){
                return Result.ok(workOtherUrl+WorkFlowEnum.HTML_03.getDescription());
            }
            else if (key.equals(displayWithPIAndOI)){
                return Result.ok(workOtherUrl+WorkFlowEnum.HTML_06.getDescription());
            }
            else if (key.equals(displayWithIIAndOI)){
                return Result.ok(workOtherUrl+WorkFlowEnum.HTML_07.getDescription());
            }
            else if (key.equals(displayWithMoreParam)){
                return Result.ok(workOtherUrl+WorkFlowEnum.HTML_08.getDescription());
            }
            else if (key.equals(noParamHistory)){
                return Result.ok(workOtherUrl+WorkFlowEnum.HTML_04.getDescription());
            }
            else if (key.equals(historyWithOperatorId)){
                return Result.ok(workOtherUrl+WorkFlowEnum.HTML_05.getDescription());
            }else {
                return Result.ok(null);
            }

        }
    }

    @ApiOperation(value = "获取ProcessModelerURL")
    @GetMapping(value = "/getProcessModelerUrl")
    public Result<String> getProcessModelerUrl() {
        return Result.ok(workFlowUrl);
    }
}
