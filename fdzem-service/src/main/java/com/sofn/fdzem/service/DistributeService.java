package com.sofn.fdzem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.common.utils.PageUtils;
import com.sofn.fdzem.model.Distribute;
import com.sofn.fdzem.vo.DistributeVo;
import com.sofn.fdzem.vo.EvaluateVo;

import java.util.List;

public interface DistributeService {
    /**
     * 查看区域监测中心列表
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageUtils<Distribute> listPage(Integer pageNum, Integer pageSize);

    /**
     * 监测站分配查询
     *
     * @param id
     * @return
     */
    DistributeVo getById(String id);

    /**
     * 保存分配监测站
     *
     * @param id
     * @param monitroIds
     */
    void updateDistribute(String id, List<Long> monitroIds);

    /**
     * 保存监测中心数据
     *
     * @param distribute
     */
   /* void insert(Distribute distribute);*/
}
