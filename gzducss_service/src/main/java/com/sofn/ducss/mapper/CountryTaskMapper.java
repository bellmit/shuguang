package com.sofn.ducss.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.ducss.model.CountryTask;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CountryTaskMapper extends BaseMapper<CountryTask> {

    List<CountryTask> getTaskByPage(Map<String, Object> params);

    List<CountryTask> getMinistryTaskByPage(Map<String, Object> params);

    int deleteByPrimaryKey(Integer id);

    CountryTask selectByPrimaryKey(String id);

    int updateByPrimaryKey(CountryTask task);

    List<CountryTask> getCountryTaskByCondition(Map<String, Object> params);

    int updateByCondition(CountryTask task);

    int updateMinistryByCondition(CountryTask task);

    void updateTaskFactMainNum(Map<String, Object> params);

    void updateStatus(Map<String, Object> params);

    List<CountryTask> getTasks(Map<String, Object> params);

    Integer updateDynamicNumById(@Param("id") String id, @Param("factChgNum") Integer factChgNum, @Param("mainChgNum") Integer mainChgNum);

    void updateTaskStatus(@Param("year") String year, @Param("areaId") String areaId, @Param("status") Byte status);

    boolean insertCountryTask(CountryTask countryTask);

    boolean insertMinistryTask(CountryTask countryTask);

    CountryTask selectOneByBean(CountryTask queryDct);


    /**
     * 获取已发布的年份数据
     *
     * @param statusList 状态集合
     * @param taskLevel  等级
     * @return 年份数据集合
     */
    List<String> getCountryTaskYearList(@Param("statusList") List<String> statusList, @Param("taskLevel") String taskLevel);
}