package com.sofn.sys.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.common.utils.PageUtils;
import com.sofn.sys.model.WorkFlowUrlRegulator;

import java.util.List;

public interface WorkFlowUrlRegulatorService extends IService<WorkFlowUrlRegulator> {

  /**
   * 批量删除url信息
   *
   * @param ids ID
   */
  void batchDelete(List<String> ids);

  /**
   * 根据key查找value
   * @param urlKey
   * @return
   */
  String getWorkFlowUrl(String urlKey);

  /**
   * @param urlKey   条件
   * @param pageNo   页数
   * @param pageSize 每页显示多少条
   * @return
   */
  PageUtils<WorkFlowUrlRegulator> findAllWorkFlowUrlList(String urlKey, Integer pageNo, Integer pageSize);
}
