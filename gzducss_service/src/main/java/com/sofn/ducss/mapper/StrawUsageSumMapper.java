package com.sofn.ducss.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.ducss.model.StrawUsageSum;
import com.sofn.ducss.model.StrawUtilizeSum;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author Zhang Yi
 * @Date 2020/11/6 10:02
 * @Version 1.0
 * 新秸秆利用汇总
 */
public interface StrawUsageSumMapper extends BaseMapper<StrawUsageSum> {
    /**
     * 插入新的汇总秸秆利用数据
     *
     * @param list
     */
    void insertBatchStrawUsageSum(List<StrawUsageSum> list);


    /**
     * 根据年份和区域id删除
     *
     * @param strawUtilizeSum
     */
    void deleteStrawUtilizeSum(StrawUtilizeSum strawUtilizeSum);

    /**
     * 根据区域id和年份查找秸秆利用汇总
     *
     * @param childrenIds
     * @param year
     * @return
     */
    List<StrawUsageSum> selectStrawUtilizeByAreaIds(@Param("childrenIds") List<String> childrenIds, @Param("year") String year, @Param("status") List<String> status);

}
