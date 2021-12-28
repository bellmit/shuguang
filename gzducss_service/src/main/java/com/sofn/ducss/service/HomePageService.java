package com.sofn.ducss.service;

import com.sofn.ducss.vo.homePage.DataArea;
import com.sofn.ducss.vo.homePage.ReportProgressVo;

import java.util.List;

/**
 * 首页数据接口
 *
 * @author jiangtao
 * @version 1.0
 **/
public interface HomePageService {

    /**
     * 获取填报县,市场主体,抽样分散户数据
     *
     * @param year          年份
     * @param areaCode          区域code
     * @param administrativeLevel          用户等级
     * @param type           类型 为空为首页展示 只管上报和审核之后的, 传 1 则是展示下一级已经审核通过的数据
     * @return boolean 布尔类型
     */
    DataArea getDataArea(String year,String areaCode,String administrativeLevel, String type);


    /**
     * 根据不同行政机构获取不同级别的审核数据
     *
     * @param year          年份
     * @param administrativeLevel          用户等级
     * @return boolean 布尔类型
     */
    List<ReportProgressVo> getReportProgress(String year,String administrativeLevel);

}