package com.sofn.agsjsi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.agsjsi.model.WetExamplePointCollect;
import com.sofn.agsjsi.vo.DropDownVo;
import com.sofn.agsjsi.vo.WetExamplePointCollectVo;
import com.sofn.agsjsi.vo.excelBean.WetExamplePointCollectExcel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WetExamplePointCollectMapper extends BaseMapper<WetExamplePointCollect> {
    List<WetExamplePointCollectVo> listForCondition(@Param("wetName")String wetName, @Param("wetCode") String wetCode, @Param("secondBasin") String secondBasin, @Param("status") String status, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("orgProvinceCode") String orgProvinceCode, @Param("orgCityCode") String orgCityCode, @Param("orgAreaCode") String orgAreaCode,@Param("sysUserLevel")String sysUserLevel
            ,@Param("approveFlag") String approveFlag);
    WetExamplePointCollectVo getObj(@Param("id") String id);
    List<WetExamplePointCollectExcel> listForExport(@Param("wetName")String wetName, @Param("wetCode") String wetCode, @Param("secondBasin") String secondBasin, @Param("status") String status, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("orgProvinceCode") String orgProvinceCode, @Param("orgCityCode") String orgCityCode, @Param("orgAreaCode") String orgAreaCode,@Param("sysUserLevel")String sysUserLevel
            ,@Param("approveFlag") String approveFlag);
    List<DropDownVo> listForSelect(@Param("lastRegionCode") String lastRegionCode);
}
