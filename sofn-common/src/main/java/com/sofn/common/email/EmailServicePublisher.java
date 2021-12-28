package com.sofn.common.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * 邮件发送事件
 *@Author quzhijie
 *@Date 2020/5/28 17:10
 *@Version 1.0
 **/
@Service
public class EmailServicePublisher {

  @Autowired
  private ApplicationEventPublisher applicationEventPublisher;

  public void sendEmail(EmailVo emailVo) {
    applicationEventPublisher.publishEvent(emailVo);
  }
}
