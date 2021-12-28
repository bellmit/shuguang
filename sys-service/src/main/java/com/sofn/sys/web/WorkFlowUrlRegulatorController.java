//package com.sofn.sys.web;
//
//
//import com.sofn.common.model.Result;
//import com.sofn.common.utils.IdUtil;
//import com.sofn.common.utils.PageUtils;
//import com.sofn.sys.mapper.WorkFlowUrlRegulatorMapper;
//import com.sofn.sys.model.WorkFlowUrlRegulator;
//import com.sofn.sys.service.WorkFlowUrlRegulatorService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.util.StringUtils;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Api(tags = "工作流程url信息管理")
//@RequestMapping("/urlRegulator")
//@RestController
//public class WorkFlowUrlRegulatorController {
//  @Autowired
//  private WorkFlowUrlRegulatorService workFlowUrlRegulatorService;
//  @Autowired
//  private WorkFlowUrlRegulatorMapper workFlowUrlRegulatorMapper;
//
//  @GetMapping("/queryList")
//  @ApiOperation(value = "分页查询用户信息")
//  public Result<PageUtils<WorkFlowUrlRegulator>>queryList(@RequestParam(required = false) @ApiParam("url的key") String urlKey,
//                                                          @RequestParam(value = "pageNo") Integer pageNo,
//                                                          @RequestParam(value = "pageSize") Integer pageSize) {
//
//    PageUtils<WorkFlowUrlRegulator> pageUtils = workFlowUrlRegulatorService.findAllWorkFlowUrlList(urlKey, pageNo, pageSize);
//    return Result.ok(pageUtils);
//  }
//
//  @PostMapping("/delete/{ids}")
//  @ApiOperation(value = "删除url信息")
//  public Result delete( @ApiParam(value = "待删除IDS，ID用英文,号分隔") @PathVariable(value = "ids") String ids){
//    if (StringUtils.isEmpty(ids)) {
//      return Result.ok();
//    }
//    List<String> idList = IdUtil.getIdsByStr(ids);
//    workFlowUrlRegulatorService.batchDelete(idList);
//    return Result.ok();
//  }
//
//  @PostMapping("/saveOrUpdate")
//  @ApiOperation(value = "更新或者保存url信息")
//  public Result saveOrUpdate( @RequestBody() WorkFlowUrlRegulator workFlowUrlRegulator){
//    if (null==workFlowUrlRegulator) {
//      return Result.ok();
//    }
//    if (null!=workFlowUrlRegulator.getId()){
//    workFlowUrlRegulatorMapper.updateById(workFlowUrlRegulator);
//    }else {
//      workFlowUrlRegulatorMapper.insert(workFlowUrlRegulator);
//    }
//    return Result.ok();
//  }
//
//  @GetMapping("/info")
//  @ApiOperation(value = "根据id查询url信息")
//  public Result selectById( String id){
//    if (null==id) {
//      return Result.ok();
//    }
//    return Result.ok(workFlowUrlRegulatorMapper.selectById(id));
//  }
//
//}
