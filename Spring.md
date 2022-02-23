# Spring



## Bean



**Spring 中 beanFactory 和 ApplicationContext 的联系和区别**  

两者都可作为容器

BeanFactory：

是Spring里面最低层的接口，提供了最简单的容器的功能，只提供了实例化对象和拿对象的功能；

ApplicationContext：

应用上下文，继承BeanFactory接口，它是Spring的一各更高级的容器，提供了更多的有用的功能

总体区别如下：

1） 使用 ApplicationContext，配置  bean 默认配置是 singleton，无论是否使用，都会被实例化。优点是预先加载，缺点是浪费内存；

2） 使用 BeanFactory 实例化对象时，配置的 bean 等到使用的时候才会被实例化。优点是节约内存，缺点是速度比较慢，多用于移动设备的开发；

3） 没有特殊要求的情况下，应该使用 ApplicationContext 完成，ApplicationContext 可以实现

BeanFactory 所有可实现的功能，还具备其他更多的功能。



**Spring中的Bean为什么用单例**



BeanFactory创建Bean过程



BeanFactory 和FactoryBean的区别



## AOP



springIOC、AOP、静态代理和动态代理的区别



**项目中SpringAOP的使用**



拦截器和过滤器底层原理



**拦截器使用**



实现方式：

- 预编译：AspectJ
- 运行期动态代理（JDK动态代理、CGLib动态代理）：SpringAOP

**名词解释**



## 事务



Spring事务配置，隔离级别



AOP的原理



**Spring事务失效**



**Spring的事务传播机制**



## SpringMVC



**SpringMVC的原理**



## 其他



**Spring中注解失效的场景**



**怎么防止定时任务重复值行多次**



Oauth2协议授权流程简单介绍

Oauth2中后台token是存在服务JVM内存中，如果服务崩了的话，token失效了怎么处理(可以用Redis去实现持久化)



**Spring线程池**



