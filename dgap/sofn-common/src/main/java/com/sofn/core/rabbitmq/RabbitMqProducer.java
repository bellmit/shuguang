package com.sofn.core.rabbitmq;

import com.sofn.core.util.HessianCodeFactoryUtil;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

/** RabbitMQ
 * Created by wuye on 2016/11/21.
 */
@Service
public class RabbitMqProducer {
    @Resource
    private AmqpTemplate directTemplate;

    @Resource
    private AmqpTemplate topicTemplate;

    @Resource
    private AmqpTemplate fountTemplate;

    /**
     * 发送一对一消息
     * @param key
     * @param message
     * @return
     */
    public boolean sendDirectMessage(String key,Object message){
        boolean isFlag = false;
        byte[] msg = null;
        try {
            msg = HessianCodeFactoryUtil.serialize(message);
            directTemplate.convertAndSend(key,msg);
            isFlag = true;
        }catch (IOException e){
            e.printStackTrace();
        }
        return isFlag;
    }


    /**
     * 发送一对多消息
     * @param message
     * @return
     */
    public boolean sendFountMessage(Object message){
        boolean isFlag = false;
        byte[] msg = null;
        try {
            msg = HessianCodeFactoryUtil.serialize(message);
            fountTemplate.convertAndSend(msg);
            isFlag = true;
        }catch (IOException e){
            e.printStackTrace();
        }
        return isFlag;
    }

    /**
     * 发送筛选消息
     * @return
     */
    public boolean sendTopicMessage(String key,Object message){
        boolean isFlag = false;
        byte[] msg = null;
        try {
            msg = HessianCodeFactoryUtil.serialize(message);
            topicTemplate.convertAndSend(key,msg);
            isFlag = true;
        }catch (IOException e){
            e.printStackTrace();
        }
        return isFlag;
    }

}
