package com.sofn.core.rabbitmq;

import java.io.Serializable;

/** RabbitMQ消息中间件实体类
 * Created by wuye on 2016/11/18.
 */
public class RabbitMQinfo implements Serializable {
    private String mqId;
    private String form;
    private String title;
    private String content;
    private String sendingTime;
}
