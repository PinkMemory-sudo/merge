**MQ对比**

1. 使用场景：Kafka适合日志处理；RocketMQ适合业务处理
2. 性能：Kafka吞吐量更高，单机百万/秒；RocketMQ单机10万/秒， Kafka一个topic有很多partition，代表很多目录，每个目录下有很多segment，每个代表一个消息文件，而RocketMQ存储消息只有commitLog文件 
3.  Kafka不支持定时，事务消息等
4.  Kafka超过64个队列（partition）性能下降严重，而RocketMQ最高支持5万个队列   



 **为什么使用消息队列**

 你们公司有个什么业务场景，这个业务场景有个什么技术挑战，如果不用MQ可能会很麻烦，但是你现在用了MQ之后带给了你很多的好处 。



**MQ的协议**

JMS：Java消息服务。它便于消息系统中的Java应用程序进行消息交换，并且通过提供标准的产生、发送、接收消息的接口，简化企业应用的开发。ActiveMQ是该协议的典型实现。

 AMQP： 应用 层标准。。 RabbitMQ是该协议的典型实现。