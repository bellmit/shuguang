package com.sofn.agpjyz.service;

import com.sofn.agpjyz.vo.HabitatTypeForm;
import com.sofn.agpjyz.vo.HabitatTypeVo;

import java.util.List;

/**
 * 生境类型服务接口
 *
 * @Author yumao
 * @Date 2020/3/10 15:59
 **/
public interface HabitatTypeService {
    /**
     * 新增
     */
    HabitatTypeVo save(HabitatTypeForm form);

    /**
     * 根据资源ID删除
     */
    void deleteBySourceId(String sourceId);

    /**
     * 根据资源ID查找
     */
    List<HabitatTypeVo> listBySourceId(String sourceId);


}
