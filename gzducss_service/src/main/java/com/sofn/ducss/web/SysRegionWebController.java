package com.sofn.ducss.web;

import com.sofn.ducss.model.basemodel.Result;
import com.sofn.ducss.service.SysRegionService;
import com.sofn.ducss.service.SysRegionToTreeService;
import com.sofn.ducss.util.BoolUtils;
import com.sofn.ducss.util.IdUtil;
import com.sofn.ducss.util.shiro.JWTToken;
import com.sofn.ducss.vo.SysRegionTreeVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

@Api(tags = "行政区划适应前端新增接口")
@RestController
@RequestMapping("/sysRegionWeb")
@Slf4j
public class SysRegionWebController extends BaseController {

    @Autowired
    private SysRegionToTreeService sysRegionToTreeService;

    @Autowired
    private SysRegionService sysRegionService;


    @ApiOperation(value = "获取行政区划树结构，获取整个行政区划树结构,从顶级100000开始，如果有条件将返回符合条件的上下级关系树",
            notes = "权限码(sys:regionLastCode:getSysRegionWebTree)")
    @GetMapping(value = "/getSysRegionWebTree")
    public Result<SysRegionTreeVo> getSysRegionWebTree(@ApiParam(value = "行政区划名称") @RequestParam(required = false) String keyword,
                                                       @ApiParam(value = "父节点ID") @RequestParam(required = false) String parentId,
                                                       @ApiParam(value = "行政区划代码") @RequestParam(required = false) String regionCode,
                                                       @ApiParam(value = "是否鉴权：Y鉴权（和当前的登录用户相关，例如是成都市的用户，就返回四川省-成都市-List<区>），使用鉴权后筛选无效")
                                                       @RequestParam(value = "isAuth", required = false) String isAuth,
                                                       @ApiParam(value = "行政区划级别(province省city市, 例如鉴权情况下双流县用户如果选择行政区域为province,则能查出整个双流上级到省,整个四川省的区域)") @RequestParam(required = false) String level,
                                                       @ApiParam(value = "如果是第三方登录用户，如果用户对应的机构代理了多个行政机构，那么需要传入一个明确的机构以确定机构级别") @RequestParam(required = false) String orgId,
                                                       @ApiParam(value = "系统APPID") @RequestParam(required = false) String appId,
                                                       @ApiParam(value = "版本年份") @RequestParam(required = false) String versionYear,
                                                       @RequestHeader(value = JWTToken.TOKEN, required = false) @ApiParam(required = false) String token
    ) {
        HashMap<String, SysRegionTreeVo> resultMap = new HashMap<>();
        List<String> idsByStr = IdUtil.getIdsByStr(versionYear);

        if (!idsByStr.isEmpty()) {
            for (String versionYear1 : idsByStr) {
                SysRegionTreeVo treeVo;
                //如果传递的年份在数据库中没有数据，则查询最近的一年
                List<Integer> versionYearList = sysRegionService.getVersionYearList();
                if (!versionYearList.contains(Integer.valueOf(versionYear1))) {
                    versionYear1 = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
                }
                if (BoolUtils.Y.equals(isAuth)) {
                    return Result.ok(sysRegionToTreeService.getSysRegionTreeByLoginUser(token, level, orgId, appId, Integer.valueOf(versionYear1)));
                }

                if (StringUtils.isNotBlank(keyword) || StringUtils.isNotBlank(parentId) || StringUtils.isNotBlank(regionCode)) {
                    treeVo = sysRegionToTreeService.getSysRegionTree(appId, keyword, parentId, regionCode, Integer.valueOf(versionYear1));
                } else {
                    treeVo = sysRegionToTreeService.getSysRegionTreeByCache(appId, Integer.valueOf(versionYear1));
                }
                resultMap.put(versionYear1, treeVo);
            }
        } else {
            SysRegionTreeVo treeVo;
            //如果没传年份，默认查询前一年的数据
            versionYear = String.valueOf(Calendar.getInstance().get(Calendar.YEAR) - 1);
            if (BoolUtils.Y.equals(isAuth)) {
                return Result.ok(sysRegionToTreeService.getSysRegionTreeByLoginUser(token, level, orgId, appId, Integer.valueOf(versionYear)));
            }

            if (StringUtils.isNotBlank(keyword) || StringUtils.isNotBlank(parentId) || StringUtils.isNotBlank(regionCode)) {
                treeVo = sysRegionToTreeService.getSysRegionTree(appId, keyword, parentId, regionCode, Integer.valueOf(versionYear));
            } else {
                treeVo = sysRegionToTreeService.getSysRegionTreeByCache(appId, Integer.valueOf(versionYear));
            }
            resultMap.put(versionYear, treeVo);
        }

        return Result.ok(resultMap);
    }
}
