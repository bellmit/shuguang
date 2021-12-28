package com.sofn.agpjyz.web;

import com.sofn.agpjyz.model.Halophytes;
import com.sofn.agpjyz.model.KnowledgeBase;
import com.sofn.agpjyz.service.KnowledgeBaseService;
import com.sofn.agpjyz.vo.HalophytesVo;
import com.sofn.agpjyz.vo.KnowledgeVo;
import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-03-09 9:37
 */
@Slf4j
@Api(value = "知识库接口",tags = "知识库相关接口")
@RestController
@RequestMapping("/knowledgebase")
public class KnowledgeBaseController extends BaseController {
    @Autowired
    private  KnowledgeBaseService knowledgeBaseService;

    @RequiresPermissions("agpjyz:knowledgebase:create")
    @SofnLog("新增知识库基础信息")
    @ApiOperation(value = "新增知识库基础信息")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result save(@Validated @RequestBody @ApiParam(name = "知识库基础信息对象", value = "传入json格式", required = true)
                                   KnowledgeVo halophytesVo, BindingResult result) {
//         校验参数
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        int i = knowledgeBaseService.save(halophytesVo);
        if (i == 1) {
            return Result.ok("新增成功");
        } else {
            return Result.ok("不能重读插入记录");
        }
    }

//    @RequiresPermissions("agpjyz:knowledgebase:menu")
    @SofnLog("条件获取知识库基础信息(分页)")
    @ApiOperation(value = "条件获取知识库基础信息(分页)")
    @GetMapping(value = "/listPage")
    public Result<PageUtils<KnowledgeBase>> getList(@ApiParam(name = "knowledge",value = "知识库名称",required = false)@RequestParam(value = "knowledge",required = false)String knowledge,
                                                           @ApiParam(name = "documentId",value = "文档类型 1:知识常识2.工作手册3.样本集素材采集方法，4相关项目的政策、法规 ",required = false)@RequestParam(value = "documentId",required = false)String documentId,
                                                           @ApiParam(name = "pageNo",required = true)@RequestParam("pageNo")int pageNo,
                                                           @ApiParam(name = "pageSize",required = true)@RequestParam("pageSize")int pageSize) {

        Map map= new HashMap<String,Object>(2);
        map.put("knowledge",knowledge);
        map.put("documentId",documentId);
        return  Result.ok(knowledgeBaseService.list(map,pageNo,pageSize));
    }
    @RequiresPermissions("agpjyz:knowledgebase:view")
    @ApiOperation(value = "详情")
    @GetMapping("/get")
    public Result<KnowledgeBase> get(
            @ApiParam(value = "主键id", required = true) @RequestParam("id") String id) {
        return Result.ok(knowledgeBaseService.getOne(id));
    }
    @RequiresPermissions("agpjyz:knowledgebase:delete")
    @SofnLog("删除知识库基础信息")
    @ApiOperation(value = "删除知识库基础信息")
    @DeleteMapping(value = "/delete")
    public Result del(@ApiParam(value = "知识库id",required = true)@RequestParam("id") String id){
        return Result.ok(knowledgeBaseService.del(id));
    }
    @RequiresPermissions("agpjyz:knowledgebase:update")
    @SofnLog("修改知识库基础信息")
    @ApiOperation(value = "修改知识库基础信息")
    @PutMapping(value = "/update")
    public Result updateHal(@Validated @RequestBody @ApiParam(name = "知识库", value = "传入json格式",
            required = true) KnowledgeVo v,BindingResult result){
//        校验参数
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        knowledgeBaseService.update(v);
        return Result.ok();
    }


}
