package com.sofn.ducss.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.ducss.model.StrawProduce;
import com.sofn.ducss.vo.StrawProduceResVo2;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StrawProduceMapper extends BaseMapper<StrawProduce> {

    int deleteByPrimaryKey(Integer id);

    StrawProduce selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(StrawProduce record);

    void deleteStrawProduce(StrawProduce strawProduce);

    void insertStrawProduce(StrawProduce strawProduce);

    /**
     * @param childrenIds 要汇总在一起的区划id
     * @param year
     * @param status
     * @return
     */
    List<StrawProduce> sumStrawProduce(@Param("childrenIds") List<String> childrenIds, @Param("year") String year, @Param("status") String status);

    List<StrawProduce> sumStrawProduce2(@Param("parentAreaId") String parentAreaId, @Param("year") String year,
                                        @Param("status") List<String> status, @Param("ids") List<String> ids);

    void insertBatchStrawProduce(List<StrawProduce> proList);

    /**
     * 要汇总在一起的区划id 2
     *
     * @param childrenIds
     * @param year
     * @param status
     * @return
     */
    List<StrawProduceResVo2> sumStrawProduceTwo(@Param("childrenIds") List<String> childrenIds, @Param("year") String year, @Param("status") List<String> status);
}