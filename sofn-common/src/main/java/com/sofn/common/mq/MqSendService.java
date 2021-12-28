package com.sofn.common.mq;

import com.sofn.common.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.UUID;

/**
 *@Description 消息队列发送
 *@Author quzhijie
 *@Date 2020/2/28 17:11
 *@Version 1.0
 **/
@Component
public class MqSendService {

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  //发送消息方法
  public void send(String topic,MqMessage msg) {
    if (msg == null) {
      return;
    }

    String msgJson = JsonUtils.obj2json(msg);
    kafkaTemplate.send(topic, msgJson);
  }
}
