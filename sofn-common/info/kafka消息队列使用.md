## 消息发送

#### 配置

```
spring:
  kafka:
    bootstrap-servers: 10.0.50.101:9092
    producer:
      batch-size: 16384
      buffer-memory: 33554432
      key-serializer: org.apache.kafka.common.serialization.StringSerializer
      value-serializer: org.apache.kafka.common.serialization.StringSerializer
```

#### 使用MqSendService发送消息

消息内容放在MqMessage的data属性中，消息会转换成json发送给接收端
```
@Autowired
MqSendService mqSendService;

@GetMapping("/mq")
public Result testMq() {
  MqData data = new MqData();
  data.name = "qqzj";
  data.age = 30;
  data.date = new Date();
  mqSendService.send(MqTopics.SYS_TEST, new MqMessage<MqData>(1, data, new Date()));
  return Result.ok();
}
```

## 消息接收
 
#### 配置

```
spring:
  kafka:
    bootstrap-servers: 10.0.50.101:9092
    consumer:
      group-id: test-consumer-group
      auto-offset-reset: earliest
      enable-auto-commit: true
      auto-commit-interval: 100
      key-deserializer: org.apache.kafka.common.serialization.StringDeserializer
      value-deserializer: org.apache.kafka.common.serialization.StringDeserializer
```

#### 使用

```
@Service
public class RecieveService {

  @KafkaListener(topics = {MqTopics.SYS_TEST})
  public void listen(ConsumerRecord<?, String> record) {
    MqMessage<MqData> message = MqReceiveUtils.getValueForObj(record, MqData.class);
    System.out.println(message.toString());
  }
}
```

#### MqReceiveUtils工具

MqReceiveUtils工具提供了将接收消息反序列化的几个方法，具体参考注释。
