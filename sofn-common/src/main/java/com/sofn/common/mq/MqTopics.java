package com.sofn.common.mq;

/**
 *@Description 主题
 *@Author quzhijie
 *@Date 2020/3/2 15:15
 *@Version 1.0
 **/
public interface MqTopics {
  public final static String FANOUT_EXCHANGE = "fanout_exchange";
  public final static String TOPIC_EXCHANGE = "topic_exchange";

  /**组织机构新增**/
  public final static String SYS_ORG_ADD = "SYS_ORG_ADD";
  /**组织机构修改**/
  public final static String SYS_ORG_UPDATE = "SYS_ORG_UPDATE";
  /**组织机构删除**/
  public final static String SYS_ORG_DELETE = "SYS_ORG_DELETE";
}
