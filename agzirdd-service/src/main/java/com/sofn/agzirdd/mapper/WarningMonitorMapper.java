package com.sofn.agzirdd.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.agzirdd.model.WarningMonitor;
import com.sofn.agzirdd.vo.WelcomeTableDBVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface WarningMonitorMapper extends BaseMapper<WarningMonitor> {
    int deleteByPrimaryKey(String id);

    WarningMonitor selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(WarningMonitor record);

    int updateByPrimaryKey(WarningMonitor record);

    List<WarningMonitor> selectByCondition(Map<String, String> params);

    int deleteByCondition(Map<String, String> params);

    /**
     * 查询欢迎页图表数据，发生面积
     * @param params
     * @return
     */
    List<WelcomeTableDBVo> selectListYearData(Map<String,Object> params);

    /**
     * 获取一个有数据的物种名
     * @param year
     * @return
     */
    @Deprecated
    String selectOneEffectiveSpeciesName(@Param("year") String year, @Param("clName") String clName);
}