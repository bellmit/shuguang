package com.sofn.ahhrm.service;

import com.sofn.ahhrm.model.Baseinfo;
import com.sofn.ahhrm.vo.ChartVo;

import java.util.List;


public interface AnalysieService {

    /**
     * 查报表
     */
    List<Baseinfo> listBaseinfo(String pointName, String year, String index, String variety);

    /**
     * 查报表
     */
    List<ChartVo> getEffective(String pointName, String year, String index, String variety);

    /**
     * 查报表
     */
    List<ChartVo> getRatio(String pointName, String year, String index, String variety);
}
