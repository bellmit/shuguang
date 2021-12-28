package com.sofn.agpjpm.service;

import com.sofn.agpjpm.vo.PictureAttVo;
import com.sofn.agpjpm.vo.SpeciesMonitorForm;
import com.sofn.agpjpm.vo.SpeciesMonitorVo;

import java.util.List;

public interface SpeciesMonitorService {

    /**
     * 新增
     */
    SpeciesMonitorVo save(SpeciesMonitorForm form, String sourceId, Integer sort);

    /**
     * 根据资源ID删除
     */
    void deleteBySourceId(String sourceId);

    /**
     * 根据资源ID删除
     */
    void deleteByIds(List<String> ids);

    /**
     * 新增
     */
    void update(List<SpeciesMonitorForm> forms, String sourceId);

    /**
     * 根据资源ID查找
     */
    List<SpeciesMonitorVo> listBySourceId(String sourceId);

    /**
     * 根据资源ID查找
     */
    List<SpeciesMonitorVo> listWithPicBySourceId(String sourceId);

}
