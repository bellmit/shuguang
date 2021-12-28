package com.sofn.common.mq.rabbit;

import com.sofn.common.mq.MqTopics;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@ConditionalOnProperty(prefix = "sofn.rabbitmq", name = "enable", havingValue = "true")
@Configuration
public class RabbitMqConfig {

    /**
     * JSON序列化
     *//*
    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbitTemplate;
    }

    *//**
     * JSON反序列化
     *//*
    @Bean
    public RabbitListenerContainerFactory<?> rabbitListenerContainerFactory(ConnectionFactory connectionFactory){
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        return factory;
    }*/

    /**
     * topic 交换器
     */
    @Bean
    public TopicExchange topExchange() {
        return new TopicExchange(MqTopics.TOPIC_EXCHANGE,true,false);
    }

    /**
     * fanout 交换器
     */
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(MqTopics.FANOUT_EXCHANGE,true,false);
    }

    /**
     * 新增机构交换器
     */
    @Bean
    public TopicExchange addOrgExchange() {
      return new TopicExchange(MqTopics.SYS_ORG_ADD,true,false);
    }

    /**
     * 修改机构交换器
     */
    @Bean
    public TopicExchange updateOrgExchange() {
      return new TopicExchange(MqTopics.SYS_ORG_UPDATE,true,false);
    }

    /**
     * 删除机构交换器
     */
    @Bean
    public TopicExchange deleteOrgExchange() {
    return new TopicExchange(MqTopics.SYS_ORG_DELETE,true,false);
  }

}
