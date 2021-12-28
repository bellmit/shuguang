package com.sofn.dhhrp.service;

import com.sofn.dhhrp.model.Baseinfo;
import com.sofn.dhhrp.vo.ChartVo;

import java.util.List;


public interface AnalysieService {

    /**
     * 查报表
     */
    List<Baseinfo> listBaseinfo(String pointName, String year, String index, String variety);

    /**
     * 查报表
     */
    List<ChartVo> getAmount(String pointName, String year, String index, String variety);

    /**
     * 查报表
     */
    List<ChartVo> getRatio(String pointName, String year, String index, String variety);
}
