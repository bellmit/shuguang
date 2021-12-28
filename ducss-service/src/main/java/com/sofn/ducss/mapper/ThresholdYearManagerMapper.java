package com.sofn.ducss.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.ducss.model.ThresholdYearManager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ThresholdYearManagerMapper  extends BaseMapper<ThresholdYearManager> {

    /**
     * 获取年份是否重复
     * @param year  年份
     * @param id   ID  如果传入则不包含
     * @return   当前年份的数量
     */
    Integer getCountByYear(@Param("year") String year,@Param("id") String id);

    /**
     * 获取数据库有的年度
     * @return  List<String>
     */
    List<String> getHaveYear();

}
