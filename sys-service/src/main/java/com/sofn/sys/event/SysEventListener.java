package com.sofn.sys.event;

import com.sofn.common.utils.RedisHelper;
import com.sofn.sys.event.bean.BaseDataAllotEvent;
import com.sofn.sys.util.SysCacheUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 *@Description 广播监听器
 *@Author quzhijie
 *@Date 2020/8/3 14:51
 *@Version 1.0
 **/
@Component
public class SysEventListener {

  @Autowired
  RedisHelper redisHelper;

  /**
   * 基础数据分配监听器
   * @param event
   */
  @Async
  @EventListener
  public void handleBaseDataAllotEvent(BaseDataAllotEvent event) {
    redisHelper.delByPattern("sys_region_cache_*");
  }

}
