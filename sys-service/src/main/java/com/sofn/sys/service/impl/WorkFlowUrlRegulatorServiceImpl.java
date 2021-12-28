package com.sofn.sys.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sofn.common.utils.PageUtils;
import com.sofn.sys.mapper.WorkFlowUrlRegulatorMapper;
import com.sofn.sys.model.WorkFlowUrlRegulator;
import com.sofn.sys.service.WorkFlowUrlRegulatorService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class WorkFlowUrlRegulatorServiceImpl extends ServiceImpl<WorkFlowUrlRegulatorMapper, WorkFlowUrlRegulator> implements WorkFlowUrlRegulatorService {



  @Override
  @Transactional(rollbackFor = Exception.class)
  public void batchDelete(List<String> ids) {
    if (!CollectionUtils.isEmpty(ids)) {
      ids.forEach((id) -> {
        this.baseMapper.deleteById(id);
      });
    }
  }

  @Override
  public  String getWorkFlowUrl(String urlKey) {
    //数据库无该key，则返回0
    String urlValue = "0";
    WorkFlowUrlRegulator workFlowUrlRegulator = this.baseMapper.selectOne(
      new QueryWrapper<WorkFlowUrlRegulator>()
        .eq(!StringUtils.isEmpty(urlKey), "URL_KEY", urlKey)
    );
    if (null!=workFlowUrlRegulator) {
     return workFlowUrlRegulator.getUrlValue();
    }
    return urlValue;
  }

  @Override
  public PageUtils<WorkFlowUrlRegulator> findAllWorkFlowUrlList(String urlKey, Integer pageNo, Integer pageSize) {
    PageHelper.offsetPage(pageNo, pageSize);
    List<WorkFlowUrlRegulator> WorkFlowUrlRegulatorList = this.baseMapper.selectList(
      new QueryWrapper<WorkFlowUrlRegulator>()
        .eq(!StringUtils.isEmpty(urlKey), "URL_KEY", urlKey)
    );
    PageInfo<WorkFlowUrlRegulator> WorkFlowUrlRegulatorInfo = new PageInfo<WorkFlowUrlRegulator>(WorkFlowUrlRegulatorList);
    PageInfo<WorkFlowUrlRegulator> pageInfo = new PageInfo<>();
    BeanUtils.copyProperties(WorkFlowUrlRegulatorInfo, pageInfo);


    return PageUtils.getPageUtils(pageInfo);
  }
}
