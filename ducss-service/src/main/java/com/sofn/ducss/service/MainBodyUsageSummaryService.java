package com.sofn.ducss.service;

import com.sofn.common.utils.PageUtils;
import com.sofn.ducss.vo.MainBodyUsageSummaryVo;
import com.sofn.ducss.vo.StrawUtilizeInfoAndDetailInfoVo;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 市场主体规模化利用相关服务
 */
public interface MainBodyUsageSummaryService {


    /**
     * 从汇总表中获取市场主体规模化利用汇总数据
     * @param level   级别  2 市级  3 省级   4 部级
     * @param year   年度
     * @param areaId  需要查询那个区划的下级数据
     * @param orderBy   排序字段
     * @param isDesc   是否倒序排序  Y 倒序排序
     * @return  List<MainBodyUsageSummaryVo>
     */
    List<MainBodyUsageSummaryVo> getList(String level, String year, String areaId, String orderBy, String isDesc);


    /**
     * 查询县级市场主体规模化利用填报列表
     * 县级无法选择其他县 只能查看当前县
     * @param year   年
     * @param areaId  区划ID
     * @return    List<MainBodyUsageSummaryMapper>
     */
    PageUtils<List<MainBodyUsageSummaryVo>> getListByCounty(String year,
                                                           String areaId, String orderBy, String isDesc, Integer pageNo, Integer pageSize);

    /**
     * 根据市级ID查询当前市的各指标的汇总 个体工商户个数按照地址来做统计
     * @param year  年份
     * @param areaId  市级ID
     * @return   List<MainBodyUsageSummaryVo>
     */
    List<MainBodyUsageSummaryVo> getListByCityCount( String year,
                                                     String areaId);


    /**
     * 根据省级ID查询当前省的各个市的各指标的汇总， 个体工商户个数按照地址来做统计
     * @param year  年份
     * @param areaId 省级ID
     * @return   List<MainBodyUsageSummaryVo>
     */
    List<MainBodyUsageSummaryVo> getListByProvinceCount( String year,
                                                        String areaId);


    /**
     * 查询当前省的各个市的各指标的汇总， 个体工商户个数按照地址来做统计
     * @param year  年份
     * @return  List<MainBodyUsageSummaryVo>
     */
    List<MainBodyUsageSummaryVo> getListByChinaCount( String year);

    /**
     * 根据不同级别查询不同的数据
     * @param level    级别   2 市级  3 省级   4 部级
     * @param year  数据年份
     * @param areaId   区域ID
     * @return   List<MainBodyUsageSummaryVo>
     */
    List<MainBodyUsageSummaryVo> getListByLevel(String level, String year, String areaId);


    /**
     * 根据主体ID查询主体的填报信息
     * @param utilizeId  填报主体ID
     * @return List<StrawUtilizeInfoAndDetailInfoVo>
     */
    StrawUtilizeInfoAndDetailInfoVo getStrawUtilizeInfoAndDetailInfo(String utilizeId);

    /**
     * 导出秸秆系统市场主体汇总导出表
     * @param year  年度
     * @param areaId   区域
     * @param response   HttpServletResponse
     */
    void exportMainBodyUsageInfo(String year, String areaId , HttpServletResponse response);

}
