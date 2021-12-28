package com.sofn.common.mq.rabbit;

import com.sofn.common.exception.SofnException;
import com.sofn.common.mq.MqMessage;
import com.sofn.common.utils.JsonUtils;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

/**
  * @Description: RabbitMQ消息发送服务
  * @Author: quzhijie
  * @Date: 2020/4/15 16:24
  **/
@Component
public class RabbitMqSendService<T> {

    @Autowired
    private RabbitTemplate rabbitTemplate;

   /**
    * 发送mq消息
    * @param exchange 交换器名称
    * @param routingKey 路由键
    * @param msg 消息
    */
    public void send(String exchange, String routingKey, MqMessage<T> msg) {
        if (msg == null){
          throw new SofnException("rabbitMq: send object is null");
        }
        CorrelationData cId = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(exchange, routingKey, JsonUtils.obj2json(msg), cId);
    }

   /**
    * 发送mq消息
    * @param exchange 交换器名称
    * @param routingKeys 路由键集合
    * @param msg 消息
    */
   public void send(String exchange, List<String> routingKeys, MqMessage<T> msg) {
     if (msg == null){
       throw new SofnException("rabbitMq: send object is null");
     }

     String jsonMsg = JsonUtils.obj2json(msg);
     if (!CollectionUtils.isEmpty(routingKeys)){
       routingKeys.forEach(routingKey -> {
         CorrelationData cId = new CorrelationData(UUID.randomUUID().toString());
         rabbitTemplate.convertAndSend(exchange, routingKey, jsonMsg, cId);
       });
     }
   }

}
