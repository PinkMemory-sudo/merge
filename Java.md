# Java



## 基础



**String为什么要使用final**

String不叫常用，被final修饰的类不能被继承



**编码问题gbk、utf-8 不说了最简单的也最容易乱码的问题**



**泛型擦除**





## 容器





**十大排序算法**



**堆排序概念**



**HashMap1.7死循环**

死循环发生在HashMap的扩容函数中，在进行扩容的时候，从新表赋值到旧表采用的是头插法，原因是扩容转移后前后链表顺序倒置.



**HashMap1.8**

在jdk1.8中对HashMap进行了优化，在发生hash碰撞，不再采用头插法方式，而是直接插入链表尾部，因此不会出现环形链表的情况。



**ConcurrentHashMap为什么是线程安全的**

jdk1.7采用Segment + HashEntry + ReentrantLock的方式进行实现的，1.8中放弃了Segment臃肿的设计，取而代之的是采用Node + CAS + Synchronized来保证并发安全进行实现。

JDK1.7

分段锁技术，那我把整张表分成 N 个部分，并使元素尽量均匀的分布到每个部分中，分别给他们加锁，互相之间并不影响，每个部分就是一个 Segment。

HashMap由多个Segment组成，每个Segment可以看成是一个个小HashMao，是HashEntry数组+链表。当对某个Segment上锁时，不会影响其他的Segment。

put时通过key的hash找到segment的下标在通过hash找到它在这个Segment中数组的位置。如果当前Segment被占用，就会使用scanAndLockForPut方法自旋。

remove 方法和 put 方法类似。

size()

先采用乐观的方式，认为在统计 size 的过程中，并没有发生 put， remove 等会改变 Segment 结构的操作。但是，如果发生了，就需要重试。如果重试2次都不成功(执行三次，第一次不能叫做重试)，就只能强制把所有 Segment 都加锁之后，再统计了，以此来得到准确的结果。



JDK1.8

采用synchronized+CAS(此时的 Synchronized 已经升级了，效率得到了很大提升，锁升级可以了解一下)，把锁的粒度进一步降低，而放弃了 Segment 分段，而是给数组中的每一个头节点（为了方便，以后都叫桶）都加锁，锁的粒度降低了。并且，用的是 Synchronized 锁。

底层数据结构与HashMap相同。Key和Value不能为空

**迭代器**

Collection继承了迭代器接口，所以所有Collection的子类都可以使用迭代器进行遍历。迭代器是的遍历与底层的数据结构隔离。

使用迭代器进行遍历时，不能使用集合自己的remove方法去删除元素，而应该使用iterator的remove方法删除元素。

原因：在生成iterator的时候会生成一个*expectedModCount*，而我们在进行add,remove时会改变ModCount，遍历时检测到两者不相同就会抛异常(*ConcurrentModificationException*)。

使用for循环时可以使用集合自己的remove方法，但是要注意删除后元素前移了，而下次遍历i就++了，就会导致删除的元素之后的一个元素没有遍历到，解决的办法是删除后i--。倒序删除的话就没问题，因为下一个遍历的元素的位置没有改变。



**集合的排序**

collections有多个sort方法，可以传入comparator或者不传入，按找类的comparable排序

也可以使用list的sort(comparator)



**ConcurrentHashMap**

jdk1.7中是采用Segment + HashEntry + ReentrantLock的方式进行实现的，而1.8中放弃了Segment臃肿的设计，取而代之的是采用Node + CAS + Synchronized来保证并发安全进行实现。



**排序的倒序**



**迭代器删除元素**



## 多线程



### 线程池



**线程池参数**



**ThreadLocal的变量，父子线程之间是可以共享的吗**

不可以



**怎么停止一个线程**



**什么时候会触发InterruptException**

打断wait时



Java内存模型



**Java如何实现线程同步**



**ThreadLocal底层原理，内存泄漏问题**



**四种进程间通信方式**



**手写单例**



**高并发场景接口优化思路**

首先是服务要经受的住，然后是数据库经受的住，最后实在不行的话加入MQ进行削峰，异步处理。

1. 添加负载均衡层，将请求均匀打到系统层。
2. 系统层采用集群化部署多台机器，扛住初步的并发压力。
3. 数据库分库分表 + 读写分离或微服务
4. 缓存集群引入
5. 数据库其实本身不是用来承载高并发请求的
6. 比如说消息中间件技术，也就是MQ集群，是非常好的做写请求异步化处理，实现削峰填谷的效果。



**Synchronized静态方法和实例方法的区别**



** ReentrantLock  与Synchronized的区别**

* Synchronized会自动释放锁， ReentrantLock  需要手动释放

* 临界区发生异常时，synchronized会释放锁。 ReentrantLock  不会
* synchronized是公平的锁，ReentrantLock  可以实现非公平锁







**CAS存在什么问题**

Java中专门针对版本号的东西



**AtomicInteger的原理**



**ThreadLocal的内部原理**

是一个map，key是什么





### 线程同步





## JVM



四种引用

弱引用的使用场景

虚引用的使用场景

 类加载机制、双亲委派



## GC



**Java的动态代理**



**内存泄露怎么定位**

jmap，jstack 的使用等等





可达性分析算法中，可作为根节点的类型

**垃圾回收算法**







## **JDK1.8新特性**







