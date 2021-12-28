package com.sofn.common.mq;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.util.ParameterizedTypeImpl;
import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.model.Result;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

/**
 *@Description MQ消息接收工具类
 *@Author quzhijie
 *@Date 2020/3/2 9:42
 *@Version 1.0
 **/
public class MqReceiveUtils {

  /**
   * 获取消息队列中的json串
   * @param record
   * @return json字符串
   */
  public static String getValue(ConsumerRecord<?, String> record) {
    Optional<String> kafkaMessage = Optional.ofNullable(record.value());
    return kafkaMessage.orElse(null);
  }

  /**
   * 获取消息队列中的数据对象，即将json串转换成对象,，即MqMessage<T>
   * @param record
   * @param clazz
   * @param <T>
   * @return
   */
  public static <T> MqMessage<T> getValueForObj(ConsumerRecord<?, String> record, Class<T> clazz) {
    String json = getValue(record);
    return JSONObject.parseObject(json, new TypeReference<MqMessage<T>>(clazz) {});
  }

  /**
   * 获取消息队列中的数据对象，即将json串转换成List，即MqMessage<List<T>>
   * @param record
   * @param clazz
   * @param <T>
   * @return
   */
  public static <T> MqMessage<T> getValueForList(ConsumerRecord<?, String> record, Class<T> clazz) {
    String json = getValue(record);
    return JSONObject.parseObject(json, buildType(MqMessage.class, List.class, clazz));
  }

  private static Type buildType(Type... types) {
    ParameterizedTypeImpl beforeType = null;
    if (types != null && types.length > 0) {
      for (int i = types.length - 1; i > 0; i--) {
        beforeType = new ParameterizedTypeImpl(new Type[]{beforeType == null ? types[i] : beforeType}, null, types[i - 1]);
      }
    }

    return beforeType;
  }

}
