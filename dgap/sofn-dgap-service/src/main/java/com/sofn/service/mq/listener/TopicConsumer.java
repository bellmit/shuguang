package com.sofn.service.mq.listener;

import com.sofn.core.util.HessianCodeFactoryUtil;
import java.io.IOException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/** Created by wuye on 2016/11/21. */
public class TopicConsumer implements MessageListener {
  @Override
  public void onMessage(Message message) {
    Object obj = null;
    try {
      obj = HessianCodeFactoryUtil.deSerialize(message.getBody());
    } catch (IOException e) {
      e.printStackTrace();
    }
    System.out.print("dgap 接收到消息:" + obj.toString());
  }
}
