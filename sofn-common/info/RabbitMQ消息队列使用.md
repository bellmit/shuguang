## 消息发送

#### 配置

以下配置需要根据实际环境调整
```yml
rabbitmq:
    password: guest
    username: guest
    port: 5672
    addresses: 10.0.50.101
    #开启发送失败返回
    publisher-returns: true
    #开启发送确认
    publisher-confirms: true
    listener:
      simple:
        #指定最小的消费者数量
        concurrency: 2
        #指定最大的消费者数量
        max-concurrency: 2
        #开启ack
        acknowledge-mode: auto
      #开启ack
      direct:
        acknowledge-mode: auto
    #支持消息的确认与返回
    template:
      mandatory: true
```

#### 使用RabbitMqSendService<T>发送消息

消息内容放在MqMessage的data属性中，消息会转换成json发送给接收端

示例：
```
@Autowired
private RabbitMqSendService<SysOrg> rabbitMqSendService;

@GetMapping("/mq")
public Result testMq() {
  MqData data = new MqData();
  data.name = "qqzj";
  data.age = 30;
  data.date = new Date();
  rabbitMqSendService.send(MqTopics.SYS_ORG_ADD, AppIds.SYS, MqMessage.ok(sysOrg));
  return Result.ok();
}
```

## 消息接收
 
#### 配置

```
rabbitmq:
    password: guest
    username: guest
    port: 5672
    addresses: 10.0.50.101
    #开启发送失败返回
    publisher-returns: true
    #开启发送确认
    publisher-confirms: true
    listener:
      simple:
        #指定最小的消费者数量
        concurrency: 2
        #指定最大的消费者数量
        max-concurrency: 2
        #开启ack
        acknowledge-mode: auto
      #开启ack
      direct:
        acknowledge-mode: auto
    #支持消息的确认与返回
    template:
      mandatory: true
```

#### 使用

```
@Component
public class MqListener {

  @RabbitListener(bindings = {@QueueBinding(
    value = @Queue(value = AppIds.SYS+"_org_add", durable = "true"),
    exchange = @Exchange(value = MqTopics.SYS_ORG_ADD, type = ExchangeTypes.TOPIC),
    key = AppIds.SYS)
  }) @Component
public class MqListener {

  @RabbitListener(bindings = {@QueueBinding(
    value = @Queue(value = AppIds.SYS, durable = "true"),
    exchange = @Exchange(value = MqTopics.SYS_ORG_ADD, type = ExchangeTypes.TOPIC),
    key = AppIds.SYS)
  })
  public void process1(String msg) {
    //需要自己将json反序列化为对象
    System.out.println("Receiver  : " + msg);
  }

}
  public void process1(String msg) {
    //需要自己将json反序列化为对象
    System.out.println("Receiver  : " + msg);
  }

}
```
上边代码中的

@Queue(value = AppIds.SYS+"_org_add", durable = "true") 其中value值在系统中唯一

AppIds.SYS 替换成自己系统的APPId

MqTopics.SYS_ORG_ADD 替换为需要接收消息的主题




