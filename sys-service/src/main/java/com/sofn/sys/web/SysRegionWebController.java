package com.sofn.sys.web;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sofn.common.db.DruidUtil;
import com.sofn.common.model.Result;
import com.sofn.common.utils.BoolUtils;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.shiro.JWTToken;
import com.sofn.common.web.BaseController;
import com.sofn.sys.service.SysRegionService;
import com.sofn.sys.service.SysRegionToTreeService;
import com.sofn.sys.vo.SysRegionTreeVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.sql.*;
import java.util.*;

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
  @Transactional
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
      versionYear = String.valueOf(Calendar.getInstance().get(Calendar.YEAR)-1);
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

//  public static void main(String[] args) throws SQLException, ClassNotFoundException {
//    String url = "jdbc:uxdb://192.168.21.62:5432/sys_dev?stringtype=unspecified";
//    String username = "uxdb";
//    String password = "sofn@123";
//
//
//    Class.forName("com.uxsino.uxdb.Driver");
//    Connection connection = DriverManager.getConnection(url, username, password);
//    //查询出sys_region的除了2021年的所有数据
//
//    Statement st = null;
//    st = connection.createStatement();
//    String sql = "select parent_id,parent_ids,id from sys_region where version_year<2021";
//    ResultSet resultSet = st.executeQuery(sql);
//    List<Map<String,String>> result = Lists.newArrayList();
//    int j = 0;
//    while (resultSet.next()) {
//      j ++;
//      String uPparentIds = "";
//      String parentId = resultSet.getString("parent_id");
//      String parentIds = resultSet.getString("parent_ids");
//      String id = resultSet.getString("id");
//      String[] split = parentIds.split("/");
//      if (split.length == 2) {
//        split[0] = "0";
//        uPparentIds = "";
////        while (1==1){
////          return;
////        }
//      } else {
//        for (int i = 1; i < split.length - 1; i++) {
//          uPparentIds += "/" + split[i];
//        }
//      }
//      Map<String,String> map = Maps.newHashMap();
//
//      map.put("id",id);
//      map.put("parentId",uPparentIds);
//      result.add(map);
//
//
//
//    }
//
//
//    for (Map<String, String> map : result) {
//      String id = map.get("id");
//      String parentId = map.get("parentId");
//      String upsql = "update sys_region set parent_ids ='" + parentId + "' WHERE id= '" + id + "'";
//      st.executeUpdate(upsql);
//    }
////    把parent_ids的最后一位截取掉
//  }

//  public static void main(String[] args) throws Exception {
//    String url = "jdbc:uxdb://192.168.21.62:5432/sys_dev?stringtype=unspecified";
//    String username = "uxdb";
//    String password = "sofn@123";
//
//
//    Class.forName("com.uxsino.uxdb.Driver");
//    Connection connection = DriverManager.getConnection(url, username, password);
//    //查询出sys_region的除了2021年的所有数据
//
//    Statement st = null;
//    st = connection.createStatement();
//    String sql = "select * from sys_region where version_year=2021";
//    ResultSet resultSet = st.executeQuery(sql);
//    List<Map<String,String>> result = Lists.newArrayList();
//    while (resultSet.next()) {
//      String parentId = resultSet.getString("parent_id");
//      String parentIds = resultSet.getString("parent_ids");
//      String id = resultSet.getString("id");
//      String regionName = resultSet.getString("region_name");
//      String regionCode = resultSet.getString("region_code");
//      String versionYear = resultSet.getString("version_year");
//      Map<String,String> map = Maps.newHashMap();
//      map.put()
//    }
//  }
}
