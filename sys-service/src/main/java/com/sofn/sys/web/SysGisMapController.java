package com.sofn.sys.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import com.sofn.sys.mapper.SysRegionMapper;
import com.sofn.sys.model.SysRegion;
import com.sofn.sys.vo.SysGisMapVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Api(tags = "GisMap信息查询")
@RestController
@RequestMapping("/sysGisMap")
public class SysGisMapController {

  @Autowired
  private SysRegionMapper sysRegionMapper;

  @ApiOperation(value = "根据行政区划编码查询地区信息")
  @GetMapping("/getGisMapRegionInfo")
  public Result<SysGisMapVo> getGisMapRegionInfo(@ApiParam(value = "行政区划编码",required = true) @RequestParam String regionCode
  ) {
    ArrayList<SysGisMapVo> sysGisMapVoList = new ArrayList<>();
    List<SysRegion> sysRegions = sysRegionMapper.selectList(
      new QueryWrapper<SysRegion>()
        .eq("REGION_CODE", regionCode)
    );
    if (!sysRegions.isEmpty()) {
      sysRegions.forEach(sysRegion -> {
          SysGisMapVo sysGisMapVo = new SysGisMapVo();
          sysGisMapVo.setLongitude(sysRegion.getLongitude());
          sysGisMapVo.setLatitude(sysRegion.getLatitude());
          sysGisMapVo.setRegionCode(sysRegion.getRegionCode());
          sysGisMapVo.setRegionName(sysRegion.getRegionName());
          if (null != sysRegion.getRemark()) {
            sysGisMapVo.setRemark(sysRegion.getRemark());
          }
          sysGisMapVoList.add(sysGisMapVo);
        }
      );
    }
    return Result.ok(sysGisMapVoList);
  }


  @ApiOperation(value = "根据行政区划编码保存备注")
  @GetMapping("/saveGisMapRemark")
  @SofnLog(value = "根据行政区划编码保存备注", type = "编辑")
  public Result<String> saveGisMapRemark(@ApiParam(value = "行政区划备注",required = false) @RequestParam String remark,
                                         @ApiParam(value = "行政区划编码",required = true) @RequestParam String regionCode
  ) {
    if (null != remark) {
      UpdateWrapper<SysRegion> updateWrapper = new UpdateWrapper<>();
      updateWrapper.eq(
        "REGION_CODE", regionCode
      ).set("REMARK", remark);
      sysRegionMapper.update(null, updateWrapper);
    }
    return Result.ok();
  }

}
