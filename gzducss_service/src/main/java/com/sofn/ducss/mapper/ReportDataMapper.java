/**
 * Copyright (c) 1998-2020 SOFN Corporation
 * All rights reserved.
 * <p>
 * <p>
 * Created On 2020-12-10 10:41
 */
package com.sofn.ducss.mapper;

import com.sofn.ducss.model.StrawUtilizeSum;
import com.sofn.ducss.vo.StrawUtilizeSumResVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 报告生成相关Mapper
 *
 * @author jiangtao
 * @version 1.0
 **/
@Component
public interface ReportDataMapper {

    /**
     * 所选区域内利用情况获取数据并根据秸秆类型分组
     *
     * @param   year 年份
     * @param   areaCodes  区域集合
     * @param   status 状态集合
     * @param   strawType 指定秸秆类型
     * @param   dataType 排序类型
     *
     * @return  boolean 布尔类型
     */
    List<StrawUtilizeSum> getTheoryResourceByStrawType(@Param("year") String year, @Param("areaCodes") List<String> areaCodes, @Param("status") List<String> status,@Param("strawType") String strawType,@Param("dataType") String dataType);

    /**
     * 所选区域内利用情况汇总数据
     *
     * @param   year 年份
     * @param   areaCodes  区域集合
     * @param   status 状态集合
     * @param   strawType 指定秸秆类型
     * @param   dataType 排序类型
     *
     * @return  boolean 布尔类型
     */
    StrawUtilizeSum getSumTheoryResourceByStrawType(@Param("year") String year, @Param("areaCodes") List<String> areaCodes, @Param("status") List<String> status,@Param("strawType") String strawType,@Param("dataType") String dataType);

    /**
     * 所选区域内产生量,可收集量,利用量,利用率等数据根据区域分组
     *
     * @param   year 年份
     * @param   areaCodes  区域集合
     * @param   status 状态集合
     *
     * @return  boolean 布尔类型
     */
    StrawUtilizeSumResVo getSumStrawUtilizeByAreaCode(@Param("year") String year, @Param("areaCodes") List<String> areaCodes, @Param("status") List<String> status);


    /**
     * 所选区域内利用情况获取数据并根据秸秆类型分组
     *
     * @param   year 年份
     * @param   areaCodes  区域集合
     * @param   status 状态集合
     * @param   dataType 排序类型
     *
     * @return  boolean 布尔类型
     */
    List<StrawUtilizeSum> getSumGroupByStrawType(@Param("year") String year, @Param("areaCodes") List<String> areaCodes, @Param("status") List<String> status,@Param("dataType") String dataType);
}
