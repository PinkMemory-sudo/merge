# 概述



面临的问题：

​		分布式系统，各个服务直接相互调用，调用链路太长，一个服务超时出错，可能会引起多个服务阻塞，造成雪崩。一个服务出错时，**不要影响其他的服务。不长时间等待占用资源**,占这资源不放，而消费者由访问这个出错的生产者，资源都堵到这，把tomcat的线程池占满，同一服务中的其他接口得不到线程也会被拖死。



* 熔断
* 服务降级



# 服务降级



调用某个服务异常时，备选方案是什么，不要影响其他服务，避免连锁故障，兜底的解决方案。



**触发服务降级的时机**

* 调用超时
* 程序异常
* 熔断
* 线程池/信号量打满



服务降级客户端和服务端都可以做，但是一般放在客户端



**服务提供者的自身加固**

1. 方法上添加@HystrixCommand注解，指定兜底的方法



**服务消费者**

1. 开启hystrix,设置配置文件

```

```

2. 主启动类上加注解，@EnableHystrix

3. 业务方法添加注解指定兜底方法



问题：

* 每个业务方法对应一个兜底的方法，代码膨胀
* 业务方法和兜底方法耦合在了一起



**代码膨胀**

@DefaultProperties(defalutFallfack=),指定默认的兜底方案，但是在业务方法上需要添加@HystrixCommand，表示它由兜底方案。



**代码耦合**

服务调用时，@FeignClient中的fallback属性指定一个接口的实现类，当调用失败时，就调用接口实现的方法





# 熔断



降级->熔断->恢复链路



熔断器用来快速失败。作用在服务的调用方，如果它调用一个服务在短时间内多次失败，那么下次再调用时，就不再去远程调用了，而是直接失败。当它诊断出服务已经恢复时才打开远程调用。



**断路器机制**

断路器的三种状态：Open，HalfOpen，Closed

当Hystrix Command请求后端服务失败数量超过一定比例(默认50%), 断路器会切换到开路状态(Open). 这时所有请求会直接失败而不会发送到后端服务. 断路器保持在开路状态一段时间后(默认5秒), 自动切换到半开路状态(HALF-OPEN). 这时会判断下一次请求的返回情况, 如果请求成功, 断路器切回闭路状态(CLOSED), 否则重新切换到开路状态(OPEN)。Closed后过一段时间就会切换成半开状态。

熔断后不再调用其他服务，而是直接调用fallback方法。



![1646497784693](..\imgs\服务熔断.png)



# 限流 

sentinal吧



# HystrixDashboard

服务监控WEB界面。

1. 添加依赖



**生成的图表**





# 实时监控

