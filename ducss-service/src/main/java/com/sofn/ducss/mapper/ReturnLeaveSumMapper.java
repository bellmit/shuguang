package com.sofn.ducss.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.ducss.model.ReturnLeaveSum;
import com.sofn.ducss.model.StrawUtilizeSum;
import com.sofn.ducss.vo.ReturnLeaveSumVo;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Zhang Yi
 * @Date 2020/11/8 21:13
 * @Version 1.0
 * 还田离田汇总mapper
 */
public interface ReturnLeaveSumMapper extends BaseMapper<ReturnLeaveSum> {

    /**
     * 根据年份和区域id删除
     *
     * @param strawUtilizeSum
     */
    void deleteStrawUtilizeSum(StrawUtilizeSum strawUtilizeSum);

    /**
     * 插入秸秆还田离田汇总
     *
     * @param list
     */
    void insertBatchReturnLeaveSum(List<ReturnLeaveSum> list);

    /**
     * 根据区域id和年份查询14种秸秆离田还田汇总
     *
     * @param lastId
     * @param year
     * @return
     */
    ArrayList<ReturnLeaveSum> getReturnLeaveSumByAreaId(@Param("areaId") String lastId, @Param("year") String year);

    /**
     * 根据子集id和区划查询秸秆离田还田汇总
     *
     * @param childrenIds
     * @param year
     * @param status
     * @return
     */
    ArrayList<ReturnLeaveSum> getReturnLeaveSumByChildrenIdsAndYear(@Param("childrenIds") List<String> childrenIds, @Param("year") String year, @Param("status") List<String> status);
}
