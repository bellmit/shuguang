package com.sofn.ouman.web;

import com.sofn.common.model.Result;
import com.sofn.common.web.BaseController;
import com.sofn.ouman.service.RoleService;
import com.sofn.ouman.vo.SelectVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@Api(tags = "企业接口")
@RestController
@RequestMapping("/comp")
public class CompController extends BaseController {

    @Resource
    private RoleService roleService;

    @ApiOperation(value = "获取企业类型下拉数据")
    @GetMapping("/listCompType")
    public Result<List<SelectVo>> listCompType() {
        return Result.ok(roleService.listCompType());
    }

}
