[文档](https://github.com/apache/rocketmq/tree/master/docs/cn)



* 不同Broker中相同Topic中的消息是否相同
* Topic与Message Queue的区别



# 概述



**优点：**

* 单机吞吐量：十万级
* 高可用：分布式架构
* 高可靠：消息可以做到0丢失



**MQ常见协议：**



## 基本概念

RocketMQ主要由 Producer、Broker、Consumer 三部分组成，其中Producer 负责生产消息，Consumer 负责消费消息，Broker 负责存储消息。

**Producer**

RocketMQ提供多种发送方式，同步发送、异步发送、顺序发送、单向发送。

**Consumer**

两种消费形式：拉取式消费、推动式消费。应用主动拉取或Broker主动推送，实时性高。

**Broker**

消息中转角色，负责存储消息、转发消息。

**Name Server**

Broker和Topic的映射

**主题（Topic）**

消息的分类，每个主题包含若干条消息，每条消息只能属于一个主题。

**标签（Tag）**

消息的二级分类

**队列（Queue）**

在Kafka中叫Partition，每个Queue内部是有序的，在RocketMQ中分为读和写两种队列，一般来说读写队列数量一致，如果不一致就会出现很多问题

Message Queue 用于存储消息的物理地址

**Producer Group**

**Consumer Group**

阅完全相同的Topic，实现负载均衡和容错。

**Message**

RocketMQ中每个消息拥有唯一的Message ID，且可以携带具有业务标识的Key。系统提供了通过Message ID和Key查询消息的功能。

**Clustering**

集群消费模式下,相同Consumer Group的每个Consumer实例平均分摊消息。

**Broadcasting**

广播消费模式下，相同Consumer Group的每个Consumer实例都接收全量的消息。

**Normal Ordered Message**

普通顺序消费模式下，消费者通过同一个消息队列（ Topic 分区，称作 Message Queue） 收到的消息是有顺序的，不同消息队列收到的消息则可能是无顺序的。

**Strictly Ordered Message**

严格顺序消息模式下，消费者收到的所有消息均是有顺序的。



## 特性



### 订阅与发布



### **消息顺序**

表示消费者按照生产者发送的顺序来消费消息。

*全局顺序*：某个Topic下都是顺序消费。适用场景：性能要求不高，所有的消息严格按照 FIFO 原则进行消息发布和消费的场景

*局部顺序*：所有消息根据 sharding key 进行区块分区，同一个分区内的消息按照严格的 FIFO 顺序进行发布和消费。 适用场景：性能要求高，以 sharding key 作为分区字段，在同一个区块中严格的按照 FIFO 原则进行消息发布和消费的场景

如：一个订单产生了三条消息分别是订单创建、订单付款、订单完成。消费时要按照这个顺序消费才能有意义，但是同时订单之间是可以并行消费的。



### **消息过滤**

消费者可以根据Tag进行消息过滤，也可以自定义属性过滤。消息过滤在Broker中进行，优点是减少了网络传输负载，缺点是增加了Broker的负载。



### **消息可靠性**

影响消息可靠性的场景：

对于单点故障(机器坏了不能马上恢复)一旦发生，此节点上的消息全部丢失。通过异步复制，RocketMQ可以保证99%的消息不丢失。通过同步双写技术可以完全避免单点，同步双写势必会影响性能，适合对消息可靠性要求极高的场合。



### **至少一次**

消费者消费完成后才会向服务器返回ACK，没有消费的一定不会收到ACK，所以保证了消息至少投递一次。	



### **回溯消息**

消费完的数据，由于消费者出现问题等，需要将之前的消息重复消费一次，Rocket支持按照时间回溯消费，时间维度精确到毫秒。



### **事务消息**

应用本地事务和发送消息操作可以被定义到全局事务中



### **定时消息**

延迟队列，消息到达Broker不会立即被消费，发消息时。设置delayLevel等级即可。

定时消息会暂存在名为SCHEDULE_TOPIC_XXXX的topic中，并根据delayTimeLevel存入特定的queue，queueId = delayTimeLevel – 1，即一个queue只存相同延迟的消息，保证具有相同发送延迟的消息能够顺序消费。等待特定时间投递给真正的Topic。

需要注意的是，定时消息会在第一次写入和调度写入真实topic时都会计数，因此发送数量、tps都会变高。



### **消息重试**

消费者消息消费失败后，消息应该能重新消费

RocketMQ中，每个消费组都有一个重试队列，用于保存暂时无法消费的消息。重试队列设置了多个重试级别，后台定时任务按照对应的时间进行Delay后重新保存至重试队列中。



### **消息重投**

生产者在投递消息时，同步和异步方式均需要Broker返回确认信息，单向发送不需要。所以单向的投递没有任何保障。消息重投保证了消息尽可能不丢失，同时还可能造成消息重复(消息量大、网络抖动等)。



### **流量控制**

生产者流控，因为broker处理能力达到瓶颈；消费者流控，因为消费能力达到瓶颈。





### **死信队列**

用来处理无法被消费的消息。当一条消息初次消费失败，消息队列会自动进行消息重试；达到最大重试次数后，若消费依然失败，则表明消费者在正常情况下无法正确地消费该消息，此时，消息队列 不会立刻将消息丢弃，而是将其发送到该消费者对应死信队列。



**流程**



消息的发布是消费者将消息发送到Topic，消息的订阅是消费者订阅Topic下的Tag。





## 基本架构



***由四部分组成***

![image-20220129134446362](/Users/chenguanlin/Documents/workspace/merge/img/RocketMQ架构图.png)



**NameServer**

管理Broker，向客户端提供路由查询。

NameServer集群中各个实例之间不进行通信，Broker需要向每个NameServer注册自己。



**工作流程**

1. 启动NameServer，等待Broker、Producer、Consumer连接
2. Broker启动，跟所有的NameServer保持长连接，NameServer集群中有Topic跟Broker的映射关系
3. 创建Topic，指定该Topic要存储到哪些Broker，也可以发送消息时自动创建
4. Producer先跟一个NameServer建立长连接，获取当前发送的Topic存在哪些Broker上，轮询从队列列表中选择一个队列，然后与队列所在的Broker建立长连接从而向Broker发消息。
5. Consumer跟其中一台NameServer建立长连接，获取当前订阅Topic存在哪些Broker上，然后直接跟Broker建立连接通道，开始消费消息。



**Broker**

用来存储和转发消息，



**Consumer**

* 支持PUSH和PULL两种消费模式(消费者主动取拉取或Broker去push)
* 集群消费和广播消费。一个队列只会被一个消费者消费



**负载均衡**



## 一次通信的完整流程

1. Producer 与 NameServer集群中的其中一个节点（随机选择）建立长连接，定期从 NameServer 获取 Topic 路由信息，并向提供 Topic 服务的 Broker Master 建立长连接，且定时向 Broker 发送心跳。
2. Producer轮训某个Topic下面的所有队列实现发送方的负载均衡。



## 部署



1. 启动NameServer



2. Broker部署

Master与Slave 的对应关系通过指定相同的BrokerName，不同的BrokerId 来定义，BrokerId为0表示Master，非0表示Slave。





## 设计



[参考](https://github.com/apache/rocketmq/blob/master/docs/cn/design.md)



# 使用



**添加依赖**

```xml
<dependency>
    <groupId>org.apache.rocketmq</groupId>
    <artifactId>rocketmq-client</artifactId>
    <version>4.9.1</version>
</dependency>
```



```
Error:java: 读取/Users/chenguanlin/.m2/repository/org/springframework/spring-core/5.3.15/spring-core-5.3.15.jar时出错; error in opening zip file
```





## 同步消息



## 异步消息



## 单项消息









Topic与Beoker的关系？

Topic绑定到多个Broker上？

自动创建Topic



Queue与Broker？

一个Broker中可以有多个Queue



消费者从NameServer获得路由，直接像Broker发送消息？



修改Broker中Queue的数量

写队列和读队列的数量可以不一致，借此来解决修改Queue数量时的一些问题







```
nohup sh bin/mqnamesrv & 
tail -f ~/logs/rocketmqlogs/namesrv.log
```



```
nohup sh bin/mqbroker -n localhost:9876 -c conf/broker.conf &
tail -f ~/logs/rocketmqlogs/broker.log
```



```
export NAMESRV_ADDR=localhost:9876
sh bin/tools.sh org.apache.rocketmq.example.quickstart.Producer
sh bin/tools.sh org.apache.rocketmq.example.quickstart.Consumer
```



```
bin/mqshutdown
```



```
<dependency>
     <groupId>javax.xml.bind</groupId>
     <artifactId>jaxb-api</artifactId>
     <version>2.3.0</version>
 </dependency>
 <dependency>
     <groupId>com.sun.xml.bind</groupId>
     <artifactId>jaxb-impl</artifactId>
     <version>2.3.0</version>
 </dependency>
 <dependency>
     <groupId>com.sun.xml.bind</groupId>
     <artifactId>jaxb-core</artifactId>
     <version>2.3.0</version>
 </dependency>
 <dependency>
     <groupId>javax.activation</groupId>
     <artifactId>activation</artifactId>
     <version>1.1.1</version>
 </dependency>
```



































































# 高可用





































