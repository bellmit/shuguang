package com.sofn.fyrpa.web;

import com.sofn.common.model.BaseData;
import com.sofn.common.model.Result;
import com.sofn.common.utils.BaseDataUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Api(value = "其它模块", tags = "其它接口")
@RestController
@Slf4j
@RequestMapping(value = "/queryInfo")
public class QueryInfoController {

      @GetMapping("/getWaterAreas")
      @ApiOperation("获取水系或海域列表")
      public Result getWaterAreas(){
          List<BaseData> baseDataList = BaseDataUtils.getByType("fyrpa", "water_area");
          List<String> list = new ArrayList<>();
          for (int i = 0; i <baseDataList.size() ; i++) {
               list.add(baseDataList.get(i).getBaseDataName());
          }
          return Result.ok(list);
      }

      @GetMapping("/getDeptList")
      @ApiOperation("获取主管部门列表")
      public Result getDeptList(){
          List<String>list = new ArrayList<>();
          list.add("渔业");
          list.add("农业");
          list.add("林业");
          list.add("海洋");
          list.add("水利");
          list.add("环保");
          list.add("国土");
          list.add("住建");
          list.add("其它");
         return Result.ok(list);
      }

    @GetMapping("/getManagerOrgLevelList")
    @ApiOperation("获取保护区管理机构级别列表")
    public Result getManagerOrgLevelList(){
        List<String>list = new ArrayList<>();
        list.add("正处级");
        list.add("正处级(代管)");
        list.add("副处级");
        list.add("副处级(代管)");
        list.add("正科级");
        list.add("正科级(代管)");
        list.add("副科级");
        list.add("副科级(代管)");
       return Result.ok(list);
    }

    @GetMapping("/getManagerOrgQualityList")
    @ApiOperation("获取保护区管理机构性质列表")
    public Result getManagerOrgQualityList(){
        List<String>list = new ArrayList<>();
        list.add("机关");
        list.add("事业单位");
        list.add("其它");
        list.add("代管");
        return Result.ok(list);
    }

}
