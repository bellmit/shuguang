package com.sofn.agzirdd.service;

import com.sofn.agzirdd.vo.WelcomeMapResVo;
import com.sofn.agzirdd.vo.WelcomeSearchVo;
import com.sofn.agzirdd.vo.WelcomeTableVo;

import java.util.List;

public interface WelcomeService {
    /**
     * 监测-发生面积数据
     * @param vo
     * @return
     */
    List<WelcomeTableVo> getFSMJInfo(WelcomeSearchVo vo);

    /**
     * 监测-种群数量数据
     * @param vo
     * @return
     */
    List<WelcomeTableVo> getZQSLInfo(WelcomeSearchVo vo);

    /**
     * 地图调查监测数据
     * @param vo
     * @return
     */
    List<WelcomeMapResVo> getMapInfo(WelcomeSearchVo vo);

    /**
     * 获取发生面积默认有数据的物种，默认调查数据
     * @param year
     * @return
     */
    String getDefaultFSMJSpName(String year);

    /**
     * 种群数量默认有数据的物种，默认调查数据
     * @param year
     * @return
     */
    String getDefaultZQSLSpName(String year);

    /**
     * 调查-外来入侵物种发生面积
     * @param vo
     * @return
     */
    List<WelcomeTableVo> getDCFSMJInfo(WelcomeSearchVo vo);

    /**
     * 调查-外来入侵物种种群数量/平方米
     * @param vo
     * @return
     */
    List<WelcomeTableVo> getDCZQSLInfo(WelcomeSearchVo vo);
}
