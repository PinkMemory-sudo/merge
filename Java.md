# Java



## 基础



**String为什么要使用final**

String不叫常用，被final修饰的类不能被继承



**String str="hello world"和String str=new String("hello world")的区别**

使用""创建的字符串存储在堆中的字符串常量池中，相同的内容只会存储一份。new String()，通过new创建出来的字符串存储是在堆内存中，不会去检查是否存在。



**String a = "hello2"; String b = "hello" + 2; System.out.println((a == b));会输出什么**

true， "hello" + 2会在编译时被jvm优化成"hello2"，所以a和b是同一个对象



**String a = "hello2"; 　 String b = "hello";    String c = b + 2;    System.out.println((a == c));的输出结果**

false 因为c是间接相加的，jvm不会对引用变量进行优化



**null转String**

* (String) null 返回null
* String.valueOf(null) 返回字符串"null"
* ""+null 返回字符串null
* null.toString NPE



**获得Class对象的方法**



**BIO,NIO,AIO的区别**

BIO： 同步阻塞 I/O 模式，就是我们平常使用的IO，使用简单，并发低。

NIO： 同步非阻塞I/O模式，通过channel传输，实现了多路复用

AIP： NIO2，异步非阻塞的 IO 模型，基于事件和回调机制，应用不广

同步与异步，阻塞与非阻塞的区别：IO包括IO的请求以及实际IO，阻塞和非阻塞发生在IO的请求，同步和异步发生在IO的操作。



**Log4j对程序有影响吗**

将日志记录到本地或者数据库需要消耗IO，频繁操作，会对系统性能产生影响



**哪些方法不能被重写**

子类不能继承的：被final修饰的方法，static修饰的方法，或private修饰的方法，跨包的话因为不可见所以也不能重写，构造方法不会被继承，也就不会被重写。



**编码问题gbk、utf-8 不说了最简单的也最容易乱码的问题**



**泛型擦除**



**final、finalize 和 finally 的不同之处** 

finalize 是 Object 类的一个方法，在垃圾收集器执行的时候会调用被回收对象的此方法， 可以覆盖此方法提供垃圾收集时的其他资源回收，例如关闭文件等。 





**JDK1.8新特性**

* StreamAPI
* TimeAPI

* lambda表达式
* 函数式接口

* 方法引用
* 取消永久代



**JDK1.8接口的变化**

* Java8之前，接口中的方法被`public abstract`隐式修饰，变量被`public static final`修饰
* Java8引入了默认方法( default修饰符，子类可以重写)，静态方法



## 容器





**List,Set,Map三者的区别**

* List : 存储的元素是有序的、可重复的。 
* Set : 存储的元素是无序的、不可重复的。
* Map: 使用键值对(kye-value)存储，可以不能重复，value可以重复



**迭代器**

Collection继承了迭代器接口，所以所有Collection的子类都可以使用迭代器进行遍历。迭代器是的遍历与底层的数据结构隔离。

使用迭代器进行遍历时，不能使用集合自己的remove方法去删除元素，而应该使用iterator的remove方法删除元素。

原因：在生成iterator的时候会生成一个*expectedModCount*，而我们在进行add,remove时会改变ModCount，遍历时检测到两者不相同就会抛异常(*ConcurrentModificationException*)。

使用for循环时可以使用集合自己的remove方法，但是要注意删除后元素前移了，而下次遍历i就++了，就会导致删除的元素之后的一个元素没有遍历到，解决的办法是删除后i--。倒序删除的话就没问题，因为下一个遍历的元素的位置没有改变。



**Iterator 和 ListIterator 的区别是什么？**

* Iterator 可用来遍历 Set 和 List 集合，但是 ListIterator 只能用来遍历 List
* Iterator 对集合只能是前向遍历，ListIterator 既可以前向也可以后向。
* ListIterator 实现了 Iterator 接口，并包含其他的功能，比如：增加元素，替换元素，获取前一个和后一个元素的索引，等等。



**集合的排序**

collections有多个sort方法，可以传入comparator或者不传入，按找类的comparable排序

也可以使用list的sort(comparator)



**Comparable与Comparator的区别**

常见的 String、Byte、Char、Date等都实现了Comparable接口可以直接进行比较。实现Comparable接口的comparTo方法，定义了该该类的实例怎么进行比较，可以使用Collections.sort方法排序。而没有实现Comparable接口的类要想排序，需要定义一个比较器。通过list.sort,传入一个Comparator来排序。

升序：当前对象小就返回-1.当前对象大就返回1，相等返回0；



### List



**Arraylist 与 LinkedList 区别?**

ArrayList的***数据结构***是数组，LinkedList的数组结构是链表，所以两者的主要区别就是数组和链表的区别。

ArrayList适合通过下标进行***随机访问***，LinkedList适合频繁插入删除的情况。

ArrayList需要连续的空间，不够时需要扩容，重新分配空间，LinkedList不用。

内存***空间***占用：ArrayList 的空间浪费主要体现在在 list 列表的结尾会预留一定的容量空 间，而 LinkedList 的空间花费则体现在它的每一个元素都需要存放直接后继和直接前驱以及数据



 **ArrayList 的扩容机制**

默认的初始容量是10(最小)

第一次调用add方法添加元素，先取指定的容量和默认容量中最大的那个进行初始化。之后调用add方法，当容量不够时，调用grow方法进行扩容，新容量等于`oldCapacity + (oldCapacity >> 1)`,也就是就容量的1.5倍，然后判断新容量与需要的容量的大小。



### Set



**set怎么保证不重复的**

会先计算对象的 hashcode 值来判断对象加入的位置，同 时也会与其他加入的对象的 hashcode 值作比􏰀，如果没有相符的 hashcode ， HashSet 会假设对象没有重复出现。但是如果发现有相同 hashcode 值的对象，这时会调用 equals() 方法来检查hashcode 相等的对象是否真的相同。如果两者相同， HashSet 就不会让加入操作成功。



**HashSet 和 TreeSet 有什么区别**

HashSet通过HashMap实现，TreeSet通过TreeSet实现

存入HashSet需要重新HashCode和equals

存入TreeSet需要实现Comparable



### Map



**为什么重写 equals 时必须重写 hashCode 方法**

默认情况下，hashCode是对象在堆中的一个特殊值，而equals比较的则是在堆中的地址，所以无论如何，每个对象的hashcode和equals都不会相同。对象的hashcode主要用来计算出该对象在hash表中的索引，然后equals来比较两个对象是否相同这样就减少了比较的次数；在set中，会先根据hashcode计算出索引比较是否相同，有的还在调用equals比较对象是否相同来保证元素不重复，如果equals相同但是hashcode不同，可能计算出来的索引不同，就不能保证元素不重复。

所以必须保证：

equals相同时，hashCode也一定要相同。所以重写了equals方法，则生成hashcode的条件也要发生改变。



**Hashtable 和 Hashmap 的区别**

线程安全，key不能为null



**HashMap1.7死循环**

死循环发生在HashMap的扩容函数中，在进行扩容的时候，从新表赋值到旧表采用的是头插法，原因是扩容转移后前后链表顺序倒置.



**HashMap1.8**

在jdk1.8中对HashMap进行了优化，在发生hash碰撞，不再采用头插法方式，而是直接插入链表尾部，因此不会出现环形链表的情况。



**容量为什么是2的幂次方**

当大是2的幂次方时，求余操作求可以通过(lengh-1)$hash来操作，这样每次根据Hash计算位置时效率就高得多了



**ConcurrentHashMap为什么是线程安全的**

*Jdk1.7*

是分成多个segment，每个segment配一把锁

*Jdk1.8*

ConcurrentHashMap 取消了 Segment 分段锁，采用 CAS 和 synchronized 来保证并发安全。synchronized 只锁定当前链表或红黑二叉树的首节点，锁的粒度更小，这样只要 hash 不冲突，就不会产生并 发，效率又提升 N 倍。



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



## 多线程



**进程和线程的区别**

进程是资源分配(内存，文件等)的最小单位，进程间的资源是独享的

线程是CPU调度的基本单位，一个进程中可以有多个线程，它们之间共享该进程的资源

线程切换消耗的资源要比进程小得多，线程也被成为轻量级进程



**什么是上下文切换**

多线程通常是线程数大于CPU数，一个CPU来回切换来执行多个线程。当一个线程执行完CPU时间段切换到另一个线程前，会先将自己的当前状态先保存起来，等再次切换回这个任务时在把状态加载出来接着执行。



**死锁**

互斥条件：该资源任一时刻只能有一个线程占用

请求与保持条件：一个进程因请求资源而阻塞时，不释放自己一获得的资源

不剥夺条件：线程已经获得的资源不可剥夺，只有自己使用完毕后才释放资源

循环等待条件：若干进程间形成一种头围相连的循环等待条件



**如何避免死锁**

请求与保持条件：一次申请全部资源，这样就不会因某个资源被其他线程占用而阻塞

不可剥夺条件：如果没有申请到其他线程占用的资源时，把自己占用的那部分释放掉



**线程的状态**

| 状态          | 描述                                                         |
| ------------- | ------------------------------------------------------------ |
| NEW           | 新建状态                                                     |
| RUNNABLE      | 可运行状态，包含READY和RUNNING                               |
| BLOCKED       | 阻塞状态，线程发起阻塞的I/O操作或者申请其他线程占用的资源时。阻塞状态不会占用CPU |
| WAITING       | 等待状态，线程执行了wait或者join状态，执行notify或者加入的线程执行完后进入RUNNABLE |
| TIMED_WAITING | 与WAITING状态类似，但是指定的有时间，超时自动转RUNNABLE      |
| TERMINATED    | 线程运行完之后                                               |



**线程调度器和时间分片**

线程调度器是操作系统的服务，负责为Runnable的线程根据优先级和等待时间分配CPU时间。不受JVM的限制，所以Java设置的优先级不一定有用。



**sleep方法和wait方法的异同**

* 都会是线程进入阻塞状态，sleep不会释放锁，wait会释放锁。
* wait使用前提是先获得锁，通过锁调用wait方法

* sleep到时间后继续执行wait() 方法被调用后，线程不会自动苏醒，需要别的线程调用同一个对象上的 notify() 或 者 notinotify动苏醒。



**sleep和yield方法的区别**

* yield方法会放弃CPU执行权，进入就绪态
* sleep会进入休眠



怎么唤醒一个阻塞的线程



**Java如何实现线程同步**



**什么是 FutureTask**  

[ForkJoin](https://www.cnblogs.com/gjmhome/p/14410760.html)



 **Java 中 interrupted 和 isInterruptedd 方法的区别**  



### Volatile



**两个作用**

* 可见性
* 禁止指令重排序



**使用 volatile 关键字的场景**  

synchronized 关键字是防止多个线程同时执行一段代码，那么就会很影响程序执行效率，而volatile 关键字在某些情况下性能要优于 synchronized，但是要注意 volatile 关键字是无法替代synchronized 关键字的，因为volatile 关键字无法保证操作的原子性。通常来说，使用volatile 必须具备以下 2 个条件：

1） 对变量的写操作不依赖于当前值

2） 该变量没有包含在具有其他变量的不变式中



### Synchronized



synchronized关键字用来解决线程间的同步问题，synchronized修饰的方法和代码块在同一时间只能被一个线程执行。

synchronized关键字可以用来修饰实例方法，静态方法和代码块。修饰实例方法时，使用的锁对象时this，修饰静态方法时，使用的时当前类的Class对象，修饰代码块时自己指定锁对象。



**为什么不建议使用String作为锁对象**

 尽量不要使⽤ synchronized(String a) 因为 JVM 中，字符串常量池具有缓存功能， 两个String的值一致时指向的地址是一致的，其实两个线程锁的是同一个对象。



**synchronized 关键字和 volatile 关键字的区别**

* volatile 关键字只能用于变量而 synchronized 关键字可以修饰方法以及代码块
* volatile 关键字能保证数据的可⻅性，但不能保证数据的原子性。 synchronized 关键字两者都能保证
* volatile 关键字主要用于解决变量在多个线程之间的可⻅性，而 synchronized 关键字解决 的是多个线程之间访问资源的同步性



**为什么Java 早期版本中， synchronized 属于 重量级锁，效率低下**

因为监视器锁(monitor)是依赖于底层的操作系统的 Mutex Lock 来实现的，Java 的线程是映射到操作系统的原生线程之上的。如果要挂起或者唤醒一个线程，都需要操作系统帮忙完成，而操作系统实现线程之间的切换时需要从用户态转换到内核态，这个状态之间的转换需要相对比⻓的时间，时间成本相对高。



**JDK1.6后synchronized的优化**

JDK1.6 对锁的实现引入了大量的优化，如自旋锁、适应性自旋锁、锁消除、锁粗化、偏向锁、轻量级锁等技术来减少锁操作的开销。

最开始是无锁状态，有线程使用时变成偏向锁，有线程争强时变成轻量锁，竞争频繁变成重量锁

无状态锁：最开始是无锁状态

偏向锁：存储ThreadID

轻量级锁:CAS+自旋一段时间

重量级锁：完全阻塞



### Lock



**ReentrantLock  与Synchronized的区别**

* Synchronized会自动释放锁， ReentrantLock  需要手动释放
* 临界区发生异常时，synchronized会释放锁。 ReentrantLock  不会
* synchronized是公平的锁，ReentrantLock  可以实现非公平锁



### 线程池



**线程池的优点，为什么要使用线程池**

* 重复利用，减少线程创建和销毁的开销
* 提高响应速度
* 可以控制并发量，防止无限创建线程





**线程池的五种状态**

* 线程池处在RUNNING状态时，能够接收新任务，以及对已添加的任务进行处理
* 线程池处在SHUTDOWN状态时，不接收新任务，但能处理已添加的任务
* 线程池处在STOP状态时，不接收新任务，不处理已添加的任务，并且会中断正在处理的任务
* 当所有的任务已终止，ctl记录的”任务数量”为0，线程池会变为TIDYING状态。
* 线程池彻底终止，就变成TERMINATED状态。



**线程池的7个参数**

| 参数                     | 说明                                                         |
| ------------------------ | ------------------------------------------------------------ |
| corePoolSize             | 线程池要保持的线程的数量                                     |
| BlockingQueue            | 在线程数达到最大时，就会存放在队列中(runnable通过execute提交的) |
| maximumPoolSize          | 线程池中最多可以保存的线程数                                 |
| RejectedExecutionHandler | 线程数已经达到最大，并且队列也满了，对新提交的任务怎么拒绝   |
| keepAliveTime            | 大于核心线程数时，如果执行时间内没有分配到任务就会清掉       |
| TimeUnit                 | keepAliveTime的单位                                          |
| ThreadFactory            | 线程池用它来创建线程                                         |



**线程池的4种拒绝策略**

| 拒绝策略             | 说明                                                         |
| -------------------- | ------------------------------------------------------------ |
| AbortPolicy          | 抛弃任务,直接抛出 RejectedExecutionException 异常阻止系统正常运行 |
| DiscardPolicy        | 直接抛弃任务，不抛出异常                                     |
| DiscardOledestPolicy | 丢弃掉最先入队的，重新尝试添加到队列中                       |
| CallerRunsPolicy     | 调用者自己去执行任务                                         |

   

**线程池工作原理**





**最大线程数设置过大会有什么后果**

* 线程时需要内存空间的，创建的线程过多就会占用过大的内存空间
* 线程上下文切换需要消耗时间，线程数太多而进行频繁切换也会浪费时间
* netty的建议是设置为2倍的cpu核心数。 





**执⾏ execute()⽅法和 submit()⽅法的区别是什么呢？**

* execute() ⽅法⽤于提交不需要返回值的任务
* submit() ⽅法⽤于提交需要返回值的任务， 线程池会返回⼀个 Future 类型的对象 。



### CAS



**CAS存在什么问题**

Java中专门针对版本号的东西



### AQS



### 原子操作类



基本类型：AtomicInteger，AtomicLong，AtomicBoolean

数组类型：AtomicIntegerArray，AtomicLongArray，AtomicReferenceArray

引用类型：AtomicReference，AtomicStampedReference，AtomicMarkableReference

AtomicStampedReference可以解决ABA问题

对象的属性修改类型：AtomicIntegerFieldUpdater，AtomicLongFieldUpdater，AtomicReferenceFieldUpdater



### 其他



**ThreadLocal底层原理，内存泄漏问题**

 每⼀个线程都有自己的专属本地变量。主要是将线程和线程的私有变成做一个映射，各个线程之间互不影响。高并发的情况下可以实现无状态调用。特别适合各个线程依赖不同变量完成操作的情况。就是空间换时间，各自用各自的变量，自然就不用处理线程安全的问题。内部类，Map

 ThrealLocal 类中可以通过 Thread.currentThread() 获取到当前线程对象后，直接通过 getMap(Thread t) 可以访问到该线程的 ThreadLocalMap 对象。



**内存泄漏问题**

 `ThreadLocalMap` 是使用 `ThreadLocal` 的弱引用作为 `Key`的，弱引用的对象在 GC 时会被回收。 这时如果线程还在执行，就会有一条当前线程引用threadLocalMap引用entry引用value，这些值不能被访问页清除不了。

 其实，`ThreadLocalMap`的设计中已经考虑到这种情况，也加上了一些防护措施：在`ThreadLocal`的`get()`,`set()`,`remove()`的时候都会清除线程`ThreadLocalMap`里所有`key`为`null`的`value` 



**ThreadLocal的变量，父子线程之间是可以共享的吗**

不可以



**怎么停止一个线程**

你可以**用** **volatile 布尔变量**来退出 run()方法的循环或者是取消任务来中断线程。

- interrupt()，在一个线程中调用另一个线程的interrupt()方法，即会向那个线程发出信号——线程中断状态已被设置。至于那个线程何去何从，由具体的代码实现决定。
- isInterrupted()，用来判断当前线程的中断状态(true or false)。
- interrupted()是个Thread的static方法，用来恢复中断状态，名字起得额



**什么时候会触发InterruptException**

打断wait时



Java内存模型



**四种进程间通信方式**



**什么是自旋**

很多synchronized中都是简单的代码，执行非常快，不妨不让线程进去阻塞状态，而是在synchronized外循环，循环多次还没获得锁再进行阻塞。



**手写单例**

```java
public class Singleton {
    private volatile static Singleton uniqueInstance;
    private Singleton() {}
		public static Singleton getUniqueInstance() { 
      //先判断对象是否已经实例过，没有实例化过才进入加锁代码
			if (uniqueInstance == null) { //类对象加锁
            synchronized (Singleton.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new Singleton();
                }
			} }
        return uniqueInstance;
    }
}
```



**高并发场景接口优化思路**

首先是服务要经受的住，然后是数据库经受的住，最后实在不行的话加入MQ进行削峰，异步处理。

1. 添加负载均衡层，将请求均匀打到系统层。
2. 系统层采用集群化部署多台机器，扛住初步的并发压力。
3. 数据库分库分表 + 读写分离或微服务
4. 缓存集群引入
5. 数据库其实本身不是用来承载高并发请求的
6. 比如说消息中间件技术，也就是MQ集群，是非常好的做写请求异步化处理，实现削峰填谷的效果。





## JVM



**内存划分**

*线程共有的*：

**堆**

用来存放实例，几乎所有的对象实例都存在堆里，堆时JVM内存管理的核心区域，

JVM中堆是最大的一块内存空间，所有线程共享的一块区域，在虚拟机启动的时候创建。堆还可以细分，详情看GC部分。随着JIT编译期的发展与逃逸分析技术逐渐 成熟，栈上分配、标量替换优化技术将会导致一些微妙的变化，所有的对象都分配到堆上也渐渐 变得不那么“绝对”了。

从jdk 1.7开始已经默认开启逃逸分析，如果某些方法中的对象引用没有被 返回或者未被外面使用(也就是未逃逸出去)，那么对象可以直接在栈上分配内存。

**方法区**

存储已被虚拟机加载的类信息，常量，静态变量等。方法区也称为永久代。常量池是方法区的一部分

jdk1.8取消了方法区，用元空间代替。

*线程私有的*

**程序计数器**

记录着当前线程正在执行的字节码的地址，线程切换后根据程序计数器能恢复到正确的执行位置。

**虚拟机栈**

保存着栈帧，描述方法执行的内存模型。每一次函数调用都会有一个对应的栈帧被压入 Java 栈，每一个函数调用结束后，都会有一个栈帧被弹出。

*栈帧*：存放着局部变量表、操作数（operand）栈、动态链接、方法正常退出或者异常退出的定义等 。 

*局部变量*：表存放着编译期可知的各种数据类和对象的引用

*操作数栈*：

*动态连接*：

Java虚拟机栈可能会出现两种错误：StackOverFlowError 和 OutOfMemoryError

***StackOverFlowError*** : 若 Java 虚拟机栈的内存大小不允许动态扩展，那么当线程请求栈 的深度超过当前 Java 虚拟机栈的最大深度的时候，就抛出 StackOverFlowError 错误。

***OutOfMemoryError*** : 若 Java 虚拟机堆中没有空闲内存，并且垃圾回收器也无法提供更多 内存的话。就会抛出 OutOfMemoryError 错误。

**本地方法栈**

与虚拟机栈相似，但是本地方法栈执行的是native方法。在hotspot虚拟机中本地方法栈和Java虚拟机栈合而为一。



堆和方法区是所有线程共享的资源，其中堆是进程中最大的一块内存，主要用于存放新创建的对 象 (所有对象都在这里分配内存)，方法区主要用于存放已被加载的类信息、常量、静态变量、即 时编译器编译后的代码等数据

PC：程序计数器。字节码解释器通过改变程序计数器来依次读取指令，从而实现代码的流程控制；在多线程的情况下，程序计数器用于记录当前线程执行的位置，从而当线程被切换回来的时 候能够知道该线程上次运行到哪儿了。如果执行的是 native 方法，那么程序计数器记录的是 undefined 地址，只有执行 的是 Java 代码时程序计数器记录的才是下一条指令的地址。

Java8取消了方法区，而将常量放在了元空间(直接内存)



**常量池，方法区，永久代？**





**JVM加载class文件的原理**

类的加载是将class文件中的数据读取到内存，然后进行连接，验证，准备和解析。类的装载有类加载起(ClassLoad)和它的子类完成。

验证：  确保Class文件的字节流中包含的信息符合当前虚拟机的要求，并且不会危害虚拟机自身的安全。

准备：分配内存，并将其赋默认值

解析：将常量池中的符号引用变为直接引用(内存地址)

初始化：执行xxx方法，设置初始值



**创建对象的过程**

1. 类加载检查：检查是否已经被加载过(到方法区中找)，没有的话要执行类加载操作
2. 分配内存：开辟堆内存空间
3. 初始化零值：就是设置默认值
4. 设置对象头：比如这个对象的Hash,GC分代的年龄，是哪个类的实例
5. 执行init方法：父类变量的初始化、语句块，构造器，子类的变量初始化，代码块，构造器

* 父类>子类，静态>非静态，代码块>构造方法



**什么是双亲委派模型**

根据双亲委派模式，在加载类文件的时候，子类加载器首先将加载请求委托给它的父加载器，父加载器会检测自己是否已经加载过类，如果已经加载则加载过程结束，如果没有加载的话则请求继续向上传递直Bootstrap ClassLoader。如果请求向上委托过程中，如果始终没有检测到该类已经加载，则Bootstrap ClassLoader开始尝试从其对应路劲中加载该类文件，如果失败则由子类加载器继续尝试加载，直至发起加载请求的子加载器为止。



**类加载器的种类**

* 启动类加载器(Bootstrap ClassLoader): 最顶级的加载器， 负责加载jvm的核心类库，比如`java.lang.*`等，从系统属性中的`sun.boot.class.path`所指定的目录中加载类库 
* 扩展类加载器(Extension ClassLoader)：从java.ext.dirs系统属性所指定的目录中加载类库，或者从JDK的安装目录的jre/lib/ext子目录（扩展目录）下加载类库，如果把用户的jar文件放在这个目录下，也会自动由扩展类加载器加载。
* 应用程序类加载器(Application ClassLoader)：从环境变量classpath或者系统属性java.class.path所指定的目录中加载类。
* 自定义加载器(User ClassLoader)





**为什么要使用双亲委派模型**

 免类的重复加载，这样就可以保证任何的类加载器最终得到的都是同样一个Object对象。 



## GC



**都有哪些垃圾回收算法，都有哪些弊端**

* 标记清除： ⾸先标记出所有不需要回收的对象，在标记完成后统⼀回收掉所 有没有被标记的对象。 造成了内存碎片
* 复制算法： 以将内存分为大小相同的两块，每次使⽤其中 的⼀块。当这⼀块的内存使⽤完后，就将还存活的对象复制到另⼀块去，然后再把使⽤的空间⼀次清理掉。浪费了一半内存。
* 标记整理：标记过程仍然与“标记-清除”算法⼀样，但后续步骤不是直接对可回收对象回收，⽽是让所有存活的对象向⼀端移动，然后直接清理掉端边界以外的内存。移动增加了开销。
* 分代收集：般将 java 堆分为新⽣代和⽼年代，每个代使用不同的算法。



**晋升的流程**

*Minor GC*

触发时机：当 eden 区没有足够空间进行分配时

大部分情况，对象都会首先在 Eden 区域分配，在一次新生代垃圾回收后，如果对象还存活，则 会进入 s0 或者 s1，并且对象的年龄还会加 1(Eden 区->Survivor 区后对象的初始年龄变为 1)， 当它的年龄增加到一定程度(默认为 15 岁)，就会被晋升到老年代中。经过这次 GC 后，Eden 区和"From"区已经被清空。这个时候，"From"和"To"会交换他们的⻆色。Minor GC 会一直重复这样的过程，直到“To”区被填 满，"To"区被填满之后，会将所有对象移动到老年代中。

* 对象优先分配到Eden，大对象如果进行一次Minor GC后还存不下，直接进入老年代

* 动态对象年龄判定：“Hotspot 遍历所有对象时，按照年龄从小到大对其所占用的大小进行累 积，当累积的某个年龄大小超过了 survivor 区的一半时，取这个年龄和 MaxTenuringThreshold 中更小的一个值，作为新的晋升年龄阈值”

*Full GC*



**老年代为什么使用标记整理和标记清除，新生代为什么要使用复制算法**

触发Full GC的频率要低，里面保存的对象被GC的可能性小，大对象也保存在老年代中，老年代还需要自己兜底内存，标记清楚和标记整理不会浪费内存，所以使用这两个方法。

新生代中的对象大部分都会被GC，所以使用复制算法效率高，而且空间也并不是1:1等分而是8:1:1,只浪费了10%内存。



**如何判断一个对象应该被回收**  

1. 引用计数法：给对象中添加一个引用计数器，每当有一个地方引用它，计数器就加1;当引用失效，计数器就
   减1;任何时候计数器为0的对象就是不可能再被使用的。
2. 可达性分析算法：这个算法的基本思想就是通过一系列的称为 “GC Roots” 的对象作为起点，从这些节点开始向下 搜索，节点所走过的路径称为引用链，当一个对象到 GC Roots 没有任何引用链相连的话，则证 明此对象是不可用的。



**可以作为 GCRoots 的对象有哪些**  

虚拟机栈中引用的对象

方法区中类静态属性引用的对象

方法区中常量引用的对象

本地方法栈中引用的对象



**四种引用**

强引用引用：大部分引用实际上都是强引用，垃圾回收器绝不会回收它

软引用：可有可无，如果内存空间足够，垃圾回收器 就不会回收它，如果内存空间不足了，就会回收这些对象的内存。

弱引用：可有可无，但是被辣鸡回收器发现会被回收

虚引用：形同虚设，主要用来跟踪对象被垃圾回收的活动。当垃圾回收器准备回收一个对象时，如果发现它还有虚引用，就会在回收对象的内存之前， 把这个虚引用加入到与之关联的引用队列中。程序可以通过判断引用队列中是 否已经加入了虚引 用，来了解被引用的对象是否将要被垃圾回收。程序如果发现某个虚引用已经被加入到引用队 列，那么就可以在所引用的对象的内存被回收之前采取必要的行动。



**内存泄露怎么定位**

jmap，jstack 的使用等等





可达性分析算法中，可作为根节点的类型



# 算法

**十大排序算法**



**堆排序概念**



