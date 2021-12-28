package com.sofn.fdpi.web;

import com.sofn.common.model.Result;
import com.sofn.common.web.BaseController;
import com.sofn.fdpi.util.SysOwnOrgUtil;
import com.sofn.fdpi.vo.SysOrgAndRegionVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "系统审核级别管理", tags = "系统审核级别管理")
@RestController
@RequestMapping("/sys")
public class SysApproveLevelController extends BaseController {

    @ApiOperation("获取当前账号的审核级别：'0':非审核级别(提示不能审核);'1':一级审核;'2':二级审核；'3':'三级审核'")
    @GetMapping("/getSysApproveLevel")
    public Result getSysApproveLevel() {
        Result<SysOrgAndRegionVo> sysResult = SysOwnOrgUtil.getSysApproveLevelForApprove();
        if (Result.CODE.equals(sysResult.getCode())) {
            SysOrgAndRegionVo data = sysResult.getData();
            return Result.ok((Object) data == null ? "null" : data.getApproveLevel(), "成功！");
        } else {
            return Result.error(sysResult.getMsg());
        }

    }
}
