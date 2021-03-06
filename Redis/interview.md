# 数据类型



**Redis常见数据类型和使用场景**

String：计数场景

List：双向链表，消息队列，订阅模式

hash：对象存储

set：存放不重复数据以及获得交集并集

sorted set：与set相比多了score字段，可以根据score字段进行排序



**一个字符串类型的值最大容量**

512M



# 线程



**单线程**

1. 单线程编程容易并且更容易维护;
2. Redis 的性能瓶颈不再 CPU ，主要在内存和网络;
3. 多线程就会存在死锁、线程上下文切换等问题，甚至会影响性能



**为何引入多线程**

为了提高网络IO读写能力

虽然，Redis6.0 引入了多线程，但是 Redis 的多线程只是在网络数据的读写这类耗时操作上使用
了， 执行命令仍然是单线程顺序执行



Redis将并发访问变成了串行进行



# 集群



**redis有几种集群方案**



## 主从复制



**集群之间如何复制**



**什么情况下会导致集群不可用**

hash槽不完整时



**Redis哈希槽的概念**

Redis中有16384个哈希槽，每个key根据算法计算出hash槽的位置，到对应的节点执行操作。



**Redis集群最大节点数**

16384



## Cluster



**涉及多个key的操作该怎么办**



**分布式锁**





# 事务



**Redis中事务执行失败，后面的命令仍然会执行**



**同步机制**

![image-20220124174153500](../img/redis同步机制.png)



**事务相关的命令有哪几个**

MULIT

EXEC

DISCARD

WATCH

通过MULIT开启事务，通过EXEC/DISCARD提交事务。



**实现乐观锁**





# 性能





# 热点



**过期数据的删除策略**

1. 惰性删除 :只会在取出key的时候才对数据进行过期检查。这样对CPU最友好，但是可能会 造成太多过期 key 没有被删除。
2. 定期删除 : 每隔一段时间抽取一批 key 执行删除过期key操作。并且，Redis 底层会通过限 制删除操作执行的时⻓和频率来减少删除操作对CPU时间的影响。



**内存淘汰策略**

1. volatile-lru(least recently used):从已设置过期时间的数据集(server.db[i].expires) 中挑选最近最少使用的数据淘汰
2. volatile-ttl:从已设置过期时间的数据集(server.db[i].expires)中挑选将要过期的数据淘汰
3. volatile-random:从已设置过期时间的数据集(server.db[i].expires)中任意选择数据淘汰
4. allkeys-lru(least recently used):当内存不足以容纳新写入数据时，在键空间中，移除
最近最少使用的 key(这个是最常用的)
5. allkeys-random:从数据集(server.db[i].dict)中任意选择数据淘汰
6. no-eviction:禁止驱逐数据，也就是说当内存不足以容纳新写入数据时，新写入操作会报
    错。这个应该没人使用吧!



**缓存穿透**

访问不存在的key，每次都穿过缓存去查数据库

解决方案：

首先需要参数校验

1. 缓存无效的key：如果数据库和缓存中都没有，就将这个无效的key缓存起来，如果黑客构造了大量无效key会比较栈内存，所以key的有效期应该设置短些
2. 布隆过滤器：布隆过滤器说某个元素存在，某个元素可能小概率不存在，说某个元素不存咋，某个元素就一定不存在。



**缓存击穿**





**缓存雪崩**

同一时间大量的key失效，请求全部打在了数据库上，可能直接导致数据库宕机。



**如何保证Redis中都是日点数据**

淘汰策略？



**集群中Session共享**

![image-20220124175227871](../img/Redis共享Session.png)



# 其他



设计一段程序，一分钟只能获得2次验证码



**利用Redis设计一个消息队列**

![image-20220124174604923](../img/redis实现MQ.png)

**写操作丢失**



**回收进程如何工作**

![image-20220124174927608](../img/Redis回收进程.png)



**回收使用的LRU算法**



**Redis的使用场景**

* session缓存
* 排行版，计数器



**Redis内存模型**

![image-20220124182816566](../img/Redis内存模型.png)



# 分布式锁



# 如何使用Redis做缓存

就是要注意缓存穿透，击穿，雪崩的问题



# 缓存一致性问题



# 布隆过滤器































