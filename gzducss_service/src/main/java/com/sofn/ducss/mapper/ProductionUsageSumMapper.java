package com.sofn.ducss.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.ducss.model.ProductionUsageSum;
import com.sofn.ducss.model.StrawUtilizeSum;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author Zhang Yi
 * @Date 2020/11/5 11:06
 * @Version 1.0
 * 新-产生量与利用量汇总
 */
public interface ProductionUsageSumMapper extends BaseMapper<ProductionUsageSum> {

    void insertProductionUsageSum(ProductionUsageSum setProductionUsageSum);

    /**
     * 根据区域id和年份删除
     *
     * @param strawUtilizeSum
     */
    void deleteByYearandArea(StrawUtilizeSum strawUtilizeSum);

    /**
     * 根据年份区域地查出一个新的汇总
     *
     * @param areaIds
     * @param year
     * @param status
     */
    List<ProductionUsageSum> selectProductionUsageSum(@Param("childrenIds") List<String> areaIds, @Param("year") String year, @Param("statusList") List<String> statusList);
}
