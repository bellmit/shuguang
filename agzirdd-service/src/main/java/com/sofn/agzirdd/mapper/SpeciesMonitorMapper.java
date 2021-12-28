package com.sofn.agzirdd.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.agzirdd.model.SpeciesMonitor;
import com.sofn.agzirdd.vo.SpeciesMonitorForm;
import com.sofn.agzirdd.vo.WelcomeMapVo;
import com.sofn.agzirdd.vo.WelcomeTableDBVo;
import com.sofn.agzirdd.vo.WelcomeTableVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Description: 物种监测模块-监测基本信息Mapper
 * @Author: mcc
 * @Date: 2020\3\10 0010
 */
@Mapper
public interface SpeciesMonitorMapper extends BaseMapper<SpeciesMonitor> {

    /**
     * 获取满足条件的物种监测模块-监测基本信息List
     * @param params 监测人,省id,市id,县id,状态,开始时间,结束时间
     * @return List<SpeciesMonitor>
     */
    List<SpeciesMonitor> getSpeciesMonitorByCondition(Map<String,Object> params);

    /**
     * 获取满足条件的物种监测模块-监测基本信息List
     * @param params 监测人,省id,市id,县id,状态,开始时间,结束时间
     * @return List<SpeciesMonitor>
     */
    List<SpeciesMonitor> getSpeciesMonitorByParam(Map<String,Object> params);

    /**
     * 获取满足条件的物种监测模块-监测基本信息FormList
     * @param params 监测人,省id,市id,县id,状态,开始时间,结束时间
     * @return List<SpeciesMonitor>
     */
    List<SpeciesMonitorForm> getSpeciesMonitorForm(Map<String,Object> params);

    /**
     * 获取指定id的物种监测模块-监测基本信息
     * @param id id
     * @return SpeciesMonitor
     */
    SpeciesMonitor getSpeciesMonitorById(String id);

    /**
     * 修改物种监测模块-监测基本信息状态
     * @param params params
     */
    void updateStatus(Map<String,Object> params);

    /**
     *查询欢迎页图表数据，种群数量
     * @param params
     * @return
     */
    List<WelcomeTableDBVo> selectListYearData(Map<String, Object> params);

    /**
     * 查询欢迎页地图物种监测数据
     * @param params
     * @return
     */
    List<WelcomeMapVo> getWelcomeMapInfo(Map<String, Object> params);

    /**
     * 更新之前的数据为旧数据
     * @param provinceId
     * @param cityId
     * @param countyId
     * @param roleCode
     */
    void updateStatusToOld(@Param("provinceId") String provinceId, @Param("cityId") String cityId, @Param("countyId") String countyId
            , @Param("belongYear") String belongYear, @Param("roleCode") String roleCode);

    /**
     * 更新当前数据为新数据
     * @param provinceId
     * @param cityId
     * @param countyId
     * @param roleCode
     */
    void updateStatusToNew(@Param("provinceId") String provinceId, @Param("cityId") String cityId, @Param("countyId") String countyId
            , @Param("belongYear") String belongYear, @Param("roleCode") String roleCode);

}