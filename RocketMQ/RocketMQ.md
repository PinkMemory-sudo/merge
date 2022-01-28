# 概述



**MQ的用途：**



**常见MQ产品对比：**



**MQ常见协议：**



## 基本概念





**消息（Message）**



**主题（Topic）**



**标签（Tag）**

消息的二级分类



**队列（Queue）**



**消息标识（MessageId/Key）**



**Name Server**

注册中心，路由



**Broker Server**

消息的存储和转发



## 基本架构



**Producer Cluster**



**NameServer Cluster**



**Broker Cluster**



**Consumer Cluster**



**负载均衡**



gong



Topic与Beoker的关系？

Topic绑定到多个Broker上？

自动创建Topic



Queue与Broker？

一个Broker中可以有多个Queue



消费者从NameServer获得路由，直接像Broker发送消息？



修改Broker中Queue的数量

写队列和读队列的数量可以不一致，借此来解决修改Queue数量时的一些问题







```
nohup sh mqnamesrv & 
tail -f ~/logs/rocketmqlogs/namesrv.log
```



```
nohup sh 
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











































































