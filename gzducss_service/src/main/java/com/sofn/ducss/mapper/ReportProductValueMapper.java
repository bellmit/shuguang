package com.sofn.ducss.mapper;

import com.sofn.ducss.model.wordmodel.ReportWordProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 报表产生量相关数据
 * @author heyongjie
 * @date 2020/12/31 11:01
 */
public interface ReportProductValueMapper {

    /**
     * 获取产生量情况
     * @param year   年度
     * @param regionYear  区划年度
     * @return List<Map<String,Object>>
     */
    List<ReportWordProduct> getProductInfoGroupByProvince(@Param("year") String year, @Param("regionYear") String regionYear);


    /**
     * 获取各个县的产生量情况
     * @param areaIds  区划ID
     * @param year  年度
     * @param regionYear   区划年度
     * @return   List<ReportWordProduct>
     */
    List<ReportWordProduct> getProductInfoGroupByArea(@Param("areaIds") List<String> areaIds,@Param("year")  String year, @Param("regionYear")String regionYear);

    /**
     * 取出各个作物的产生量情况
     * @param year   年度
     * @return     List<ReportWordProduct>
     */
    List<ReportWordProduct> getProductInfoGroupStraw(@Param("year") String year ,@Param("regionYear")String regionYear);

    /**
     * 根据区划和作物分组获取产生信息
     * @param year  年度
     * @param regionYear   区划年度
     * @return  List<ReportWordProduct>
     */
    List<ReportWordProduct> getProductInfoGroupAreaAndStraw(@Param("year") String year ,@Param("regionYear")String regionYear);

}
