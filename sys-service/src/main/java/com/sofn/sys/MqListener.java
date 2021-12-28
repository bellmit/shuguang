package com.sofn.sys;

import com.sofn.common.mq.MqTopics;
import com.sofn.common.utils.AppIds;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description: TODO
 * @Author: quzhijie
 * @Date: 2020/4/17 10:18
 **/

@Component
public class MqListener {

  @RabbitListener(bindings = {@QueueBinding(
    value = @Queue(value = AppIds.SYS+"_org_add", durable = "true"),
    exchange = @Exchange(value = MqTopics.SYS_ORG_ADD, type = ExchangeTypes.TOPIC),
    key = AppIds.SYS)
  })
  public void process1(String msg) {
    //需要自己将json反序列化为对象
    System.out.println("Receiver  : " + msg);
  }

}
