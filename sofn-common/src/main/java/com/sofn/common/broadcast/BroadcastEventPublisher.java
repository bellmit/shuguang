package com.sofn.common.broadcast;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * 广播消息发送器
 *@Author quzhijie
 *@Date 2020/8/3 14:10
 *@Version 1.0
 **/
@Service
public class BroadcastEventPublisher {

  @Autowired
  private ApplicationEventPublisher applicationEventPublisher;

  public void send(Object object) {
    applicationEventPublisher.publishEvent(object);
  }
}
