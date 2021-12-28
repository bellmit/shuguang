package com.sofn.fdpi.web;

import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import com.sofn.common.web.BaseController;
import com.sofn.fdpi.model.Essaycolumn;
import com.sofn.fdpi.service.EssaycolumnService;
import com.sofn.fdpi.vo.EssaycolumnVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description:
 * @Auther: Bay
 * @Date: 2020/1/7 13:46
 */
@RestController
@Slf4j
@RequestMapping("Essaycolumn")
public class EssaycolumnController extends BaseController {
    @Autowired
    EssaycolumnService esService;
    @ApiOperation("根据状态获取栏目名")
    @GetMapping("/get")
    @SofnLog("根据状态获取栏目名")
    public Result getcolumnName(@RequestParam("status")@ApiParam(name = "status", value = "栏目status",required = true)String status){
        List<Essaycolumn> essaycolumns = esService.getcolumnName(status);
        return Result.ok(essaycolumns);
    }
    @ApiOperation("获取栏目详细信息")
    @SofnLog("获取栏目详细信息")
    @GetMapping("/getInfo")
    public Result getEssaycolumnInfo(@RequestParam("id")@ApiParam(name = "id", value = "id",required = true)String id){
        Essaycolumn essaycolumnInfo = esService.getEssaycolumnInfo(id);
        return Result.ok(essaycolumnInfo);
    }
    @ApiOperation("删除栏目信息")
    @SofnLog("删除栏目信息")
    @GetMapping("/delete")
    public Result deleteEssaycolumnInfo(@RequestParam("id")@ApiParam(name = "id", value = "id",required = true) String id){
        String s = esService.deleteEssaycolumnInfo(id);
        if ("1".equals(s)){
            return Result.ok("删除栏目信息成功");
        }
        return Result.ok("删除栏目信息失败");
    }
    @ApiOperation("修改栏目信息")
    @SofnLog("修改栏目信息")
    @PostMapping("/update")
    public Result updateEssaycolumnInfo(@Validated @RequestBody
                                         @ApiParam(name = "栏目对象",value = "传入json对象",required = true)
                                         EssaycolumnVo essaycolumnVo,BindingResult result){
        if (result.hasErrors()){
            return Result.error(getErrMsg(result));
        }
        String s = esService.updateEssaycolumnInfo(essaycolumnVo);
        if ("1".equals(s)) {
            return Result.ok("修改栏目信息成功");
        }
        return Result.error(s);
    }
    @ApiOperation("新增栏目信息")
    @SofnLog("新增栏目信息")
    @PostMapping("/save")
    public Result saveEssaycolumnInfo(@Validated @RequestBody
                                          @ApiParam(name = "栏目对象",value = "传入json对象",required = true)
                                                  EssaycolumnVo essaycolumnVo,BindingResult result){
        if (result.hasErrors()){
            return Result.error(getErrMsg(result));
        }
        String s = esService.saveEssaycolumn(essaycolumnVo);
        if ("1".equals(s)) {
            return Result.ok("新增栏目信息成功");
        }else {
            return Result.error(s);
        }
    }


}
