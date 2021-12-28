package com.sofn.common.mq;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;
import java.util.Date;

/**
 *@Description 消息队列消息对象
 *@Author quzhijie
 *@Date 2020/2/28 9:12
 *@Version 1.0
 **/
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MqMessage<T> {
    public static final int CODE = 200;

    public Integer code = CODE;
    public T data;
    public Date timestamp = Calendar.getInstance().getTime();

    public MqMessage(Integer code, T data) {
      this.code = code;
      this.data = data;
    }

    public static <T> MqMessage<T> ok(T t){
      return new MqMessage<T>(CODE, t);
    }
}
