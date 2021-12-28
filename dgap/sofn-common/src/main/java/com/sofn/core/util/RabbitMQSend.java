package com.sofn.core.util;

import com.sofn.core.rabbitmq.RabbitMqProducer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/** MQ消息发送类
 * Created by wuye on 2016/11/21.
 */
@Service
public class RabbitMQSend {

    @Resource
    private RabbitMqProducer rabbitMqProducer;

    /**
     * 发送一对一消息
     * @param key
     * @param message
     * @return
     */
    public boolean DirectSend(String key,Object message){
       // RabbitMqProducer rabbitMqProducer = new RabbitMqProducer();
        return rabbitMqProducer.sendDirectMessage(key,message);
    }


    /**
     * 发送一对多消息
     * @param message
     * @return
     */
    public boolean FountSend(Object message){
       // RabbitMqProducer rabbitMqProducer = new RabbitMqProducer();
        return rabbitMqProducer.sendFountMessage(message);
    }


    /**
     * 发送筛选消息
     * @param key
     * @param message
     * @return
     */
    public boolean TopicSend(String key,Object message){
      //  RabbitMqProducer rabbitMqProducer = new RabbitMqProducer();
        return rabbitMqProducer.sendTopicMessage(key,message);
    }
}
