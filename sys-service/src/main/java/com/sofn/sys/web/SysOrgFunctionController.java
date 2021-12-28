package com.sofn.sys.web;

import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.web.BaseController;
import com.sofn.sys.model.SysDict;
import com.sofn.sys.model.SysSystemFunction;
import com.sofn.sys.service.SysDictService;
import com.sofn.sys.service.SysOrgSystemFunctionService;
import com.sofn.sys.vo.SysFunctionForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 组织机构代理
 * @Author: quzhijie
 * @Date: 2020/1/3 14:08
 **/
@RestController
@Api(tags = "组织机构职能")
@RequestMapping("/orgFunction")
public class SysOrgFunctionController extends BaseController {

    @Autowired
    private SysOrgSystemFunctionService sysSystemFunctionService;

    @Autowired
    SysDictService sysDictService;

    @ApiOperation(value = "保存机构职能",notes = "sys:orgFunction:save")
    @PostMapping("/save")
    @SofnLog(value = "保存机构职能", type = "修改")
    public Result<String> save(@ApiParam(value = "机构职能分配对象") @Validated @RequestBody SysFunctionForm sysFunctionForm, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }

        List<SysSystemFunction> list = new ArrayList<>();
        if (!CollectionUtils.isEmpty(sysFunctionForm.getFunctionOfSystemForm())) {
          sysFunctionForm.getFunctionOfSystemForm().forEach(functionOfSystemForm -> {
            if (functionOfSystemForm != null && !CollectionUtils.isEmpty(functionOfSystemForm.getFunctionCodes())){
              if (StringUtils.isBlank(functionOfSystemForm.getAppId())){
                return;
              }

              functionOfSystemForm.getFunctionCodes().forEach(s -> {
                if (StringUtils.isBlank(s)){
                  return;
                }

                list.add(new SysSystemFunction(IdUtil.getUUId(),functionOfSystemForm.getAppId(),sysFunctionForm.getOrgId(),s, null));
              });
            }
          });
        }

      sysSystemFunctionService.saveFunction(sysFunctionForm.getOrgId(), list);
      return Result.ok();
    }

  @ApiOperation(value = "根据机构Id查询机构职能",notes = "sys:orgFunction:getByOrgId")
  @GetMapping("/getByOrgId")
  public Result<List<SysSystemFunction>> getByOrgId(@NotBlank @ApiParam(value = "机构ID") @RequestParam("orgId") String orgId) {
    List<SysSystemFunction> list = sysSystemFunctionService.getByOrgId(orgId);
    if (!CollectionUtils.isEmpty(list)) {
      list.forEach(sysSystemFunction -> {
        if (sysSystemFunction != null && StringUtils.isNotBlank(sysSystemFunction.getFunctionCode())) {
          SysDict sysDict = sysDictService.getById(sysSystemFunction.getFunctionCode());
          if (sysDict != null) {
            sysSystemFunction.setFunctionDictCode(sysDict.getDictcode());
          }
        }
      });
    }
    return Result.ok(list);
  }
}
