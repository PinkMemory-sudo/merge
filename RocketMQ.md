 **为什么使用消息队列**

 你们公司有个什么业务场景，这个业务场景有个什么技术挑战，如果不用MQ可能会很麻烦，但是你现在用了MQ之后带给了你很多的好处 。



**MQ的优点与缺点**

优点：异步处理，应用解耦，削峰

缺点：

* 可用性降低：考虑MQ宕机的情况

* 复杂性提高：要考虑MQ的消息丢失与重复消费等问题
* 一致性问题：



**MQ对比**

1. 使用场景：Kafka适合日志处理；RocketMQ适合业务处理
2. 性能：Kafka吞吐量更高，单机百万/秒；RocketMQ单机10万/秒， Kafka一个topic有很多partition，代表很多目录，每个目录下有很多segment，每个代表一个消息文件，而RocketMQ存储消息只有commitLog文件 
3.  Kafka不支持定时，事务消息等
4.  Kafka超过64个队列（partition）性能下降严重，而RocketMQ最高支持5万个队列   



**MQ怎么选择**





**MQ的协议**

JMS：Java消息服务。它便于消息系统中的Java应用程序进行消息交换，并且通过提供标准的产生、发送、接收消息的接口，简化企业应用的开发。ActiveMQ是该协议的典型实现。

 AMQP： 应用 层标准。。 RabbitMQ是该协议的典型实现。



**怎么保证消息不丢失**

1. 消费者保证投递发送成功

生产者有三种发送方式：同步、异步和OneWay。同步就是发送消息后进入阻塞，等待确认，异步就是异步的，发送后直接返回，消息发送成功后回掉自定义的接口，Oneway就是只管发送。

生产者采用同步的方式确保发送到了MQ，发送失败时可以指定重试次数，最后还不行就存入DB。也可以采用异步回调的方式，将消息存储补偿表中

2. Broker确保持久化

Broker的刷盘策略分为同步和异步。同步是等消息持久化后再返回ACK，异步是指将消息写入PageCache后直接放回。可以采用同步刷盘的策略，确保消息被持久化。另外，MQ应该采用多主多从的方式来保证高可用，采用同步双写(主从都持久化后再返回ACK)。

3. 消费者。 消费完成后，才向服务器返回ack (Rocket默认的)



**怎么保证消息一定被消费了**

消费者怎么告诉Broker消费完了，宕机了怎么办，怎么发送ACK的



**RocketMQ怎么避免消息重复消费**

消息重复消费的原因，如何做幂等



**消息堆积怎么办**

1. 消费者问题。消费者挂了需要重启；Topic中消息队列的数量大于消费者的数量，这时候可以考虑添加消费者(如果有DB等操作，要考虑下游是否支撑得住)
2. MQ问题。Topic中消息队列的数量小于消费者的数量，这时候添加消费者是没有用的。这时应该新建Topic指定消息队列的数量，然后将消息转移到新的Topic中，最后添加消费者，消费新Topic中的消息。



**消息怎么过滤**

1. 通过Tag过滤
2. 通过SQL



**消息是如何发送的**



**消费者如何接收消息**



**如何确保消息被接受了**

生产者保证消息发送到了Broker，Broker保证消息被消费。



**RocketMQ怎么使用顺序消息**

* 全局顺序：需要将Topic的消息队列数量设置成1
* 局部顺序：相同业务的消息需要发送到相同的消息队列中。需要使用MessageQueueSelector来选择要发送的Queue，即对业务编号进行hash，然后根据队列数量对hash值取余，将消息发送到一个queue中。(调用send方法的时候指定MessageQueueSelector)

1. 生产者：相同业务发送到相同的Queue中
2. 消费者：要保证消息顺序消费，同一个queue就只能被一个消费者所消费，因此对broker中消费队列加锁是无法避免的(消费客户端先向broker端发起对messageQueue的加锁请求，只有加锁成功时才创建pullRequest进行消息拉取，下面看下lock加锁请求方法)。使用MessageListenerOrderly监听器



**延迟队列的使用**

发消息时。设置delayLevel等级即可。

rocketmq实现的延时队列只支持特定的延时时间段，1s,5s,10s,...2h，不能支持任意时间段的延时。定时消息会暂存在名为SCHEDULE_TOPIC_XXXX的topic中，定时扫描，时间到后再添加到指定的Topic。



**消息怎么重试**

1. 生产者，可以设置Producer重试的次数，在指定时间内消息没有发送成功时就会重试。还不行就在catch中将消息存入DB的补偿表中
2. 消费者，消费者失败分两种情况：Exception和超时。消费者的重试实际是Broker的机制。消费者返回消息消费失败后Broker会把消息放到一个延时队列中取，Broker重试会放到不同的延时队列中，随着重试次数的增加，它重试的间隔会不断的变长，直至进入死信队列，就不在给消费者发送这条消息。超时是Broker没收到ACK，它认为没有发送就会一直发送消息。



**消费者因某些原因无法解决的消息怎么办**

返回成功，然后将消息存入补偿表中。



**消息基于什么传输的**



**怎么设计一个MQ**

1. 可伸缩
2. 持久化
3. 高可用



**死信队列的原因，使用**

消息重试超过一定次数后（默认16次）就会被放到死信队列中，不会再被消费者正常消费。有效期与正常消息相同，均为 3 天，3 天后会被自动删除。因此，请在死信消息产生后的 3天内及时处理。排查可疑因素并解决问题后，可以在消息队列 RocketMQ 控制台重新发送该消息，让消费者重新消费一次。



**消息补偿机制是什么**

1. 消息发送失败，将消息存在了DB中，定时的将DB中的消息取出来重新发送(或者直接让消费者来查)，发送成功后修改补偿表中消息的状态，失败后等待下一次的补偿。1、如果是同步发送MQ消息，则在try catch代码块中catch部分，执行保存入库操作；2、如果是异步发送MQ消息，则在SendCallback接口的public void onException(Throwable e) {}方法，执行保存入库操作。

表结构设计：

Id,topic,tag,property,status,exception,create_time,update_time



**优先级队列**

由于RocketMQ所有消息都是持久化的，所以如果按照优先级来排序，开销会非常大，因此RocketMQ没有特意支持消息优先级。好像目前要实现严格的优先级也只能用不同topic了。



**事务消息**

保证本地事务和消息的发送都成功

1. 发送Half消息
2. half消息发送成功
3. 执行本地事务
4. commit or rollback
5. 回查事务状态
6. 根据事务状态进行commit or rollback
7. 消息可见/删除

发送half给Broker，Broker存储下来但消费者并不可见(并没有发送到Topic)

消息发送成功后执行本地事务，根据执行结果手动指定状态( COMMIT/ ROLLBACK )给MQ

MQ收到COMMIT后消息变为可见，收到ROLLBACK后删除消息

如果MQ一直没有收到消息，会定时的检查本地事务状态

**实现**

1. 生产者发送事务消息

```
rocketMQTemplate.sendMessageInTransaction
```

2. 监听生产者发送的事务消息

```
@Slf4j
@Component
@RocketMQTransactionListener(txProducerGroup = "spring_boot_producer_group")
public class SyncProducerListener implements RocketMQLocalTransactionListener {
    private AtomicInteger trnner = new AtomicInteger(0);
    private ConcurrentHashMap<String, Object> localTrans = new ConcurrentHashMap<>();
    @Autowired
    private LocalService localService;
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {
        try {
            localService.executeLocalService(message.getPayload().toString());
            log.info("【本地业务执行完毕】 msg:{}, Object:{}", message, o);
            localTrans.put(message.getHeaders().getId()+"", message.getPayload());
            return RocketMQLocalTransactionState.COMMIT;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("【执行本地业务异常】 exception message:{}", e.getMessage());
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        log.info("【执行检查任务】");
        return RocketMQLocalTransactionState.UNKNOWN;
    }
}
```



**怎么做消峰**



**Broker是怎么保存消息的**

 RocketMQ主要的存储文件包括commitlog文件、consumequeue文件、indexfile文件。 

Broker在收到消息之后，会把消息保存到commitlog的文件当中，而同时在分布式的存储当中，每个broker都会保存一部分topic的数据，同时，每个topic对应的messagequeue下都会生成consumequeue文件用于保存commitlog的物理位置偏移量offset，indexfile中会保存key和offset的对应关系。

由于同一个topic的消息并不是连续的存储在commitlog中，消费者如果直接从commitlog获取消息效率非常低，所以通过consumequeue保存commitlog中消息的偏移量的物理地址，这样消费者在消费的时候先从consumequeue中根据偏移量定位到具体的commitlog物理文件，然后根据一定的规则（offset和文件大小取模）在commitlog中快速定位。

**Master和Slave之间是怎么同步数据的呢**



**RocketMQ速度快的原因**

是因为使用了顺序存储、Page Cache和异步刷盘。

1. 我们在写入commitlog的时候是顺序写入的，这样比随机写入的性能就会提高很多
2. 写入commitlog的时候并不是直接写入磁盘，而是先写入操作系统的PageCache
3. 最后由操作系统异步将缓存中的数据刷到磁盘



# 生产者



**发送消息**

打印发送结果和key



**消息发送失败的处理**

- 至多重试2次。
- 如果同步模式发送失败，则轮转到下一个Broker，如果异步模式发送失败，则只会在当前Broker进行重试。这个方法的总耗时时间不超过sendMsgTimeout设置的值，默认10s。
- 如果本身向broker发送消息产生超时异常，就不会再重试。

 调用send同步方法发送失败时，则尝试将消息存储到db，然后由后台线程定时重试，确保消息一定到达Broker。 



**Topic，tag，key**



# 消费者



**消息幂等**

可以借助关系型数据库，使用消息的唯一键(可以是msgId，或者是消息内容如订单号)



**接收消息**



**消息过滤**



**SpringBoot与RocketMQ**

1. 引入starter

*配置文件*

```
rocketmq:
  name-server: 192.168.1.224:9876 # 访问地址,分号隔开
  producer:
    group: Pro_Group # 必须指定group
    send-message-timeout: 3000 # 消息发送超时时长，默认3s
    retry-times-when-send-failed: 3 # 同步发送消息失败重试次数，默认2
    retry-times-when-send-async-failed: 3 # 异步发送消息失败重试次数，默认2
```



生产者发送消息，传入Topic、Tag、Message和回调函数

```java
@Slf4j
@Component
public class MQProducerService {

	@Value("${rocketmq.producer.send-message-timeout}")
    private Integer messageTimeOut;

	// 建议正常规模项目统一用一个TOPIC
    private static final String topic = "RLT_TEST_TOPIC";
    
	// 直接注入使用，用于发送消息到broker服务器
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

	/**
     * 普通发送（这里的参数对象User可以随意定义，可以发送个对象，也可以是字符串等）
     */
    public void send(User user) {
        rocketMQTemplate.convertAndSend(topic + ":tag1", user);
//        rocketMQTemplate.send(topic + ":tag1", MessageBuilder.withPayload(user).build()); // 等价于上面一行
    }

    /**
     * 发送同步消息（阻塞当前线程，等待broker响应发送结果，这样不太容易丢失消息）
     * （msgBody也可以是对象，sendResult为返回的发送结果）
     */
    public SendResult sendMsg(String msgBody) {
        SendResult sendResult = rocketMQTemplate.syncSend(topic, MessageBuilder.withPayload(msgBody).build());
        log.info("【sendMsg】sendResult={}", JSON.toJSONString(sendResult));
        return sendResult;
    }

	/**
     * 发送异步消息（通过线程池执行发送到broker的消息任务，执行完后回调：在SendCallback中可处理相关成功失败时的逻辑）
     * （适合对响应时间敏感的业务场景）
     */
    public void sendAsyncMsg(String msgBody) {
        rocketMQTemplate.asyncSend(topic, MessageBuilder.withPayload(msgBody).build(), new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                // 处理消息发送成功逻辑
            }
            @Override
            public void onException(Throwable throwable) {
                // 处理消息发送异常逻辑
            }
        });
    }
    
	/**
     * 发送延时消息（上面的发送同步消息，delayLevel的值就为0，因为不延时）
     * 在start版本中 延时消息一共分为18个等级分别为：1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h
     */
    public void sendDelayMsg(String msgBody, int delayLevel) {
        rocketMQTemplate.syncSend(topic, MessageBuilder.withPayload(msgBody).build(), messageTimeOut, delayLevel);
    }

    /**
     * 发送单向消息（只负责发送消息，不等待应答，不关心发送结果，如日志）
     */
    public void sendOneWayMsg(String msgBody) {
        rocketMQTemplate.sendOneWay(topic, MessageBuilder.withPayload(msgBody).build());
    }
    
	/**
     * 发送带tag的消息，直接在topic后面加上":tag"
     */
    public SendResult sendTagMsg(String msgBody) {
        return rocketMQTemplate.syncSend(topic + ":tag2", MessageBuilder.withPayload(msgBody).build());
    }
    
}
```



消费者

实现RocketMQListener接口，添加@ RocketMQMessageListener 注解指定监听的Topic，tag和消费者组

```
@Slf4j
@Component
public class MQConsumerService {

    // topic需要和生产者的topic一致，consumerGroup属性是必须指定的，内容可以随意
    // selectorExpression的意思指的就是tag，默认为“*”，不设置的话会监听所有消息
    @Service
    @RocketMQMessageListener(topic = "RLT_TEST_TOPIC", selectorExpression = "tag1", consumerGroup = "Con_Group_One")
    public class ConsumerSend implements RocketMQListener<User> {
        // 监听到消息就会执行此方法
        @Override
        public void onMessage(User user) {
            log.info("监听到消息：user={}", JSON.toJSONString(user));
        }
    }

    // 注意：这个ConsumerSend2和上面ConsumerSend在没有添加tag做区分时，不能共存，
    // 不然生产者发送一条消息，这两个都会去消费，如果类型不同会有一个报错，所以实际运用中最好加上tag，写这只是让你看知道就行
    @Service
    @RocketMQMessageListener(topic = "RLT_TEST_TOPIC", consumerGroup = "Con_Group_Two")
    public class ConsumerSend2 implements RocketMQListener<String> {
        @Override
        public void onMessage(String str) {
            log.info("监听到消息：str={}", str);
        }
    }

	// MessageExt：是一个消息接收通配符，不管发送的是String还是对象，都可接收，当然也可以像上面明确指定类型（我建议还是指定类型较方便）
    @Service
    @RocketMQMessageListener(topic = "RLT_TEST_TOPIC", selectorExpression = "tag2", consumerGroup = "Con_Group_Three")
    public class Consumer implements RocketMQListener<MessageExt> {
        @Override
        public void onMessage(MessageExt messageExt) {
            byte[] body = messageExt.getBody();
            String msg = new String(body);
            log.info("监听到消息：msg={}", msg);
        }
    }

}
```





**发布订阅**



**Broker与Topic，Partition的区别**



**顺序消息**