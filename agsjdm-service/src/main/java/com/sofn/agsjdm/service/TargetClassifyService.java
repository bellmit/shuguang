package com.sofn.agsjdm.service;


import com.sofn.agsjdm.model.TargetClassify;
import com.sofn.common.utils.PageUtils;

import java.util.List;

/**
 * 指标分类服务接口
 */
public interface TargetClassifyService {

    /**
     * 新增
     */
    TargetClassify save(TargetClassify entity);

    /**
     * 删除
     */
    void delete(String id);

    /**
     * 更新
     */
    void update(TargetClassify entity);

    /**
     * 详情
     */
    TargetClassify get(String id);

    /**
     * 列表查询
     * @return
     */
    List<TargetClassify> list(String targetVal);

    /**
     * 分页查询
     */
    PageUtils<TargetClassify> listPage(String targetVal, Integer pageNo, Integer pageSize);

}
