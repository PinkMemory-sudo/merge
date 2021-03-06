# Java



## 基础



**String为什么要使用final**

String比较常用，被final修饰的类不能被继承保证String类的不可改变，使得JVM可以实现字符串常量池，节约空间和提高性能，不可变也保证了多线程下的安全性。



**String str="hello world"和String str=new String("hello world")的区别**

使用""创建的字符串存储在堆中的字符串常量池中，会先检查是否存在，相同的内容只会存储一份。

new String()，通过new创建出来的字符串存储是在堆内存中，然后检查常量池中存不存在，堆中保存着常量池中的地址。



**String a = "hello2"; String b = "hello" + 2; System.out.println((a == b));会输出什么**

true， "hello" + 2会在编译时被jvm优化成"hello2"，所以a和b是同一个对象



**String a = "hello2"; 　 String b = "hello";    String c = b + 2;    System.out.println((a == c));的输出结果**

false 因为c是间接相加的，jvm不会对引用变量进行优化



**null转String**

* (String) null 返回null
* String.valueOf(null) 返回字符串"null"
* ""+null 返回字符串null
* null.toString NPE



## 反射



**什么是反射**

对于任意一个类，都能够知道这个类的所有属性和方法；对于任意一个对象，都能够调用它的任意一个方法和属性；这种动态获取的信息以及动态调用对象的方法的功能称为java语言的反射机制。 



**Spring哪些地方用到了反射**

* 对象的实例化是通过反射实现的 

* 注解



**获得Class对象的方法**

* 实例.getClass()
* Class.forName("")
* 类名.class



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

在运行时，Java不会保留范型。 Java的范型只存在于源码里，编译的时候给你静态地检查一下范型类型是否正确，而到了运行时就不检查了 。

```java
LinkedList<Cat> cats = new LinkedList<Cat>();
# 编译后变成了
LinkedList cats = new LinkedList();
cats.add(new Dog());
```



**final、finalize 和 finally 的不同之处** 

finalize 是 Object 类的一个方法，在垃圾收集器执行的时候会调用被回收对象的此方法， 可以覆盖此方法提供垃圾收集时的其他资源回收，例如关闭文件等。 



**finally与return语句**

finally在return之前执行，finally中对返回结果的操作不会影响返回值



**JDK1.8新特性**

* StreamAPI
* TimeAPI
* lambda表达式: 允许把函数作为一个方法的参数 
* 函数式接口
* 接口添加默认方法
* 方法引用
* 取消永久代



**JDK1.8接口的变化**

* Java8之前，接口中的方法被`public abstract`隐式修饰，变量被`public static final`修饰
* Java8引入了默认方法( default修饰符，子类可以重写)，静态方法



**元注解**

* @Target：注解的范围，ElementType枚举常用的TYPE(类,接口,)、FIELD，METHOD
* @ Retention：声明周期， SOURCE源文件中(编译后删除)，CLASS(默认的，保留在字节码中，加载进内存时删除)，RUNTIME( jvm加载class文件之后，仍然存在 )。一般如果需要在运行时去动态获取注解信息，那只能用 RUNTIME 注解；如果要在编译时进行一些预处理操作，比如生成一些辅助代码（如 ButterKnife），就用 CLASS注解；如果只是做一些检查性的操作，
* @Documented：能不能生成Javadoc
*  @Inherited： 是否可以被继承



**注解的定义**

通过@interface





**获得字段的注解**

```
for (Field field : userClass.getDeclaredFields()) {
    if (field.isAnnotationPresent(Autowired.class)) {
    Autowired annotation = field.getAnnotation(Autowired.class);
    boolean required = annotation.required();
    field.get(哪个对象)
    }
}
```



**静态内部类和(成员)内部类的区别**

* 成员内部类可以无条件的访问外部类的属性和方法(包括静态的和私有的，同名时需要使用外部类.this.变量)
* 成员内部类是依赖外部类存在的，也就是说创建内部类必须存在一个外部类`外部类对象.new 内部类()`,而内部类被private修饰时，只能被外部类使用。
* 静态内部类不能访问外部类非静态变量和方法
* 静态内部类不依赖于外部类，与类的静态成员相似



**内部类和外部类的访问方式**

内部类可以随意访问外部类，外部类访问内部类需要用内部类的引用进行访问

静态内部类只能访问外部类静态的方法和变量



**运行时异常有哪些**

* 空指针
* ByZero
* 下标越界



**new创建对象的过程**

1. 检查类是否被加载过(常量池中这个类的引用)
2. 分配内存
3. 初始化零值(默认值)
4. 设置对象头(对象的元信息，如年龄,hash)
5. 执行init方法：



**重写的限制**

* 访问修饰符，不能比父类小
* 参数列表相同
* 返回值类型相同
* 异常，不能抛出比父类大的异常
* 



## NIO



**NIO的原理**



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



**集合和数组之间的转换**

集合的toArray方法

Arrays.asList()方法



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

会先计算对象的 hashcode 值来判断对象加入的位置，同 时也会与其他加入的对象的 hashcode 值作比，如果没有相符的 hashcode ， HashSet 会假设对象没有重复出现。但是如果发现有相同 hashcode 值的对象，这时会调用 equals() 方法来检查hashcode 相等的对象是否真的相同。如果两者相同， HashSet 就不会让加入操作成功。



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

线程安全，

key不能为null

扩容机制



**HashMap1.7死循环**

死循环发生在HashMap的扩容函数中，在进行扩容的时候，从新表赋值到旧表采用的是头插法，原因是扩容转移后前后链表顺序倒置.



**HashMap1.8**

在jdk1.8中对HashMap进行了优化，在发生hash碰撞，不再采用头插法方式，而是直接插入链表尾部，因此不会出现环形链表的情况。



**容量为什么是2的幂次方**

当大是2的幂次方时，求余操作求可以通过(lengh-1)$hash来操作，这样每次根据Hash计算位置时效率就高得多了



### ConcurrentHashMap

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

多线程之间是交替执行的，通常是线程数大于CPU数，一个CPU来回切换来执行多个线程。当一个线程执行完CPU时间段切换到另一个线程前，会先将自己的当前状态先保存起来，等再次切换回这个任务时在把状态加载出来接着执行。



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



**并发三要素**

* 原子性
* 可见性
* 有序性



**线程调度器和时间分片**

线程调度器是操作系统的服务，负责为Runnable的线程根据优先级和等待时间分配CPU时间。不受JVM的限制，所以Java设置的优先级不一定有用。



**sleep方法和wait方法的异同**

* wait是Object的方法，sleep是Thread的静态方法

* wait需要锁，会释放锁，sleep不需要锁，不会释放锁



**sleep和yield方法的区别**

* yield方法会放弃CPU执行权，进入就绪态
* sleep会进入休眠



 **Java 中 interrupted 和 isInterruptedd 方法的区别**  

interrupt

interrupt 方法用于中断线程。调用该方法的线程的状态为将被置为”中断”状态。

注意：线程中断仅仅是置线程的中断状态位，不会停止线程。需要用户自己去监

视线程的状态为并做处理。支持线程中断的方法（也就是线程中断后会抛出

interruptedException 的方法）就是在监视线程的中断状态，一旦线程的中断状

态被置为“中断状态”，就会抛出中断异常。

interrupted

查询当前线程的中断状态，并且清除原状态。如果一个线程被中断了，第一次调

用 interrupted 则返回 true，第二次和后面的就返回 false 了。

isInterrupted

仅仅是查询当前线程的中断状态



**捕获线程的异常**

线程中的异常是不能抛出到调用该线程的外部方法中捕获的。

 JDK5之后有了一个Thread.UncaughtExceptionHandler 新接口，它允许我们在每一个Thread对象上添加一个异常处理器。  会在线程因未捕获的异常而面临死亡时被调用。 

```Java
public class MyUnchecckedExceptionhandler implements UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("捕获异常处理方法：" + e);
    }
}
```

可以在创建线程的时候设置

```Java
Thread t = new Thread(new ExceptionThread());
t.setUncaughtExceptionHandler(new MyUnchecckedExceptionhandler());
t.start();
```

线程池的话还可以在TreadFactory中设置 

```java
ExecutorService exec = Executors.newCachedThreadPool(new ThreadFactory(){
                    @Override
            public Thread newThread(Runnable r) {
                Thread thread = newThread(r);
                thread.setUncaughtExceptionHandler(new MyUnchecckedExceptionhandler());
                return thread;
            }
});
exec.execute(new ExceptionThread());
```

设置默认的处理器

```
// 设置默认的线程异常捕获处理器
Thread.setDefaultUncaughtExceptionHandler(new MyUnchecckedExceptionhandler());
```



**线程异常的回调接口**

UncaughtExceptionHandler



### Volatile



**两个作用**

* 可见性
* 禁止指令重排序



**使用 volatile 关键字的场景**  

synchronized 关键字是防止多个线程同时执行一段代码，那么就会很影响程序执行效率，而volatile 关键字在某些情况下性能要优于 synchronized，但是要注意 volatile 关键字是无法替代synchronized 关键字的，因为volatile 关键字无法保证操作的原子性。通常来说，使用volatile 必须具备以下 2 个条件：

1） 对变量的写操作不依赖于当前值

2） 该变量没有包含在具有其他变量的不变式中



### Synchronized



**对Synchronized的了解**

synchronized用来进行线程同步的，代码块synchronized修饰的方法和代码块在同一时间只能被一个线程执行。synchronized关键字可以用来修饰实例方法，静态方法和代码块。修饰实例方法时，使用的锁对象时this，修饰静态方法时，使用的时当前类的Class对象，修饰代码块时自己指定锁对象。



**Synchronized原理**

每个对象创建时对象头中都会存储hashcode，GC年两，锁状态标志，线程持有的锁，偏向锁的线程ID，偏向锁的时间戳等。



**为什么不建议使用String作为锁对象**

 尽量不要使⽤ synchronized(String a) 因为 JVM 中，字符串常量池具有缓存功能， 两个String的值一致时指向的地址是一致的，其实两个线程锁的是同一个对象。



**synchronized 关键字和 volatile 关键字的区别**

* volatile 关键字只能用于变量而 synchronized 关键字可以修饰方法以及代码块
* volatile 关键字能保证数据的可⻅性，但不能保证数据的原子性。 synchronized 关键字两者都能保证
* volatile 关键字主要用于解决变量在多个线程之间的可⻅性，而 synchronized 关键字解决 的是多个线程之间访问资源的同步性



**为什么Java 早期版本中， synchronized 属于 重量级锁，效率低下**

因为监视器锁(monitor)是依赖于底层的操作系统的 Mutex Lock 来实现的，Java 的线程是映射到操作系统的原生线程之上的。如果要挂起或者唤醒一个线程，都需要操作系统帮忙完成，而操作系统实现线程之间的切换时需要从用户态转换到内核态，这个状态之间的转换需要相对比⻓的时间，时间成本相对高。



**原理**

 通过成对的MonitorEnter和MonitorExit指令来实现。  

1. 如果monitor的进入数为0，则该线程进入monitor，然后将进入数设置为1，该线程即为monitor的所有者；
2. 如果线程已经占有该monitor，只是重新进入，则进入monitor的进入数加1；
3. 如果其他线程已经占用了monitor，则该线程进入阻塞状态，直到monitor的进入数为0，再重新尝试获取monitor的所有权；



**JDK1.6后synchronized的优化**

JDK1.6 对锁的实现引入了大量的优化，如自旋锁、适应性自旋锁、锁消除、锁粗化、偏向锁、轻量级锁等技术来减少锁操作的开销。

最开始是无锁状态，有线程使用时变成偏向锁，有线程争强时变成轻量锁，竞争频繁变成重量锁

无状态锁：最开始是无锁状态

偏向锁：存储ThreadID

轻量级锁:CAS+自旋一段时间

重量级锁：完全阻塞



### Lock



**Java有哪些锁机制**

* 乐观锁悲观锁
* 独享和共享
* 可重入和不可重入
* 公平和非公平



**ReentrantLock  与Synchronized的区别**

* 实现原理不同，

* Synchronized会自动释放锁， ReentrantLock需要手动释放
* 临界区发生异常时，synchronized会释放锁。 ReentrantLock  不会
* synchronized是公平的锁，ReentrantLock  可以实现非公平锁



**tryLock解决死锁问题**



**公平锁与非公平锁**

公平锁是指多个线程按照申请锁的顺序来获取锁，线程直接进入队列中排队，队列中的第一个线程才能获得锁 

非公平锁是多个线程加锁时直接尝试获取锁，获取不到才会到等待队列的队尾等待 

默认非公平锁



**读写锁**

 读写锁：既是排他锁，又是共享锁。 读写锁底层是同一把锁（基于同一个AQS），所以会有同一时刻不允许读写锁共存的限制 。



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



**阻塞队列有哪几种**

* ArrayBlockingQueue，数组有界
* LinkedBlockingQueue，链表，不指定大小默认无界(int的最大值)
*  SynchronousQueue  容量为 0 
*  PriorityBlockingQueue  是一个支持优先级的无界阻塞队列(带顺序的)，可以通过自定义类实现 compareTo() 方法来指定元素排序规则 
*  DelayQueue  设定让队列中的任务延迟多久之后执行 



**线程池的种类**

*  newCachedThreadPool无限大的线程池
*  newFixedThreadPool  固定大小的线程池
*  newScheduledThreadPool  创建一个定长线程池，支持定时及周期性任务执行 
*  newSingleThreadExecutor只有一个线程的线程池，保证任务执行的顺序 
*   newWorkStealingPool ForkJoinPool的扩展



**最大线程数设置过大会有什么后果**

* 线程时需要内存空间的，创建的线程过多就会占用过大的内存空间
* 线程上下文切换需要消耗时间，线程数太多而进行频繁切换也会浪费时间
* netty的建议是设置为2倍的cpu核心数。 



**执⾏ execute()⽅法和 submit()⽅法的区别是什么呢？**

* execute() ⽅法⽤于提交不需要返回值的任务
* submit() ⽅法⽤于提交需要返回值的任务， 线程池会返回⼀个 Future 类型的对象 。



### CAS



CAS的实现

比较交换，当前值等于期待值时才进行操作。



**CAS存在什么问题**

Java中专门针对版本号的东西



### AQS



[参考](https://www.cnblogs.com/waterystone/p/4920797.html)

**AQS是什么**

是一个同步器框架， 定义了一套多线程访问共享资源的同步器框架。它维护了一个volatile int state（代表共享资源）和一个FIFO线程等待队列（多线程争用资源被阻塞时会进入此队列）自定义同步器在实现时只需要实现共享资源state的获取与释放方式即可，至于具体线程等待队列的维护（如获取资源失败入队/唤醒出队等），AQS已经在顶层实现好了。

AQS 核心思想是，如果被请求的共享资源空闲，则将当前请求资源的线程设置为有效的工作线 程，并且将共享资源设置为锁定状态。如果被请求的共享资源被占用，那么就需要一套线程阻塞等待以及被唤醒时锁分配的机制，这个机制 AQS 是用 CLH 队列锁实现的，即将暂时获取不到锁 的线程加入到队列中。

AQS 定义两种资源共享方式
Exclusive(独占):只有一个线程能执行，如 ReentrantLock 。又可分为公平锁和非公平锁:
公平锁:按照线程在队列中的排队顺序，先到者先拿到锁
非公平锁:当线程要获取锁时，无视队列顺序直接去抢锁，谁抢到就是谁的 Share(共享):多个线程可同时执行，如
 Semaphore 、 CountDownLatch 、 CyclicBarrier 、 ReadWriteLock 我们 都会在后面讲到。



**自定义同步器**

- isHeldExclusively()：该线程是否正在独占资源。只有用到condition才需要去实现它。
- tryAcquire(int)：独占方式。尝试获取资源，成功则返回true，失败则返回false。
- tryRelease(int)：独占方式。尝试释放资源，成功则返回true，失败则返回false。
- tryAcquireShared(int)：共享方式。尝试获取资源。负数表示失败；0表示成功，但没有剩余可用资源；正数表示成功，且有剩余资源。
- tryReleaseShared(int)：共享方式。尝试释放资源，如果释放后允许唤醒后续等待结点返回true，否则返回false。



以 **ReentrantLock** 为例，state 初始化为 0，表示未锁定状态。A 线程 lock()时，会调用 tryAcquire()独占该锁并将 state+1。此后，其他线程再 tryAcquire()时就会失败，直到 A 线程 unlock()到 state=0(即释放锁)为止，其它线程才有机会获取该锁。当然，释放锁之前，A 线程 自己是可以重复获取此锁的(state 会累加)，这就是可重入的概念。但要注意，获取多少次就 要释放多么次，这样才能保证 state 是能回到零态的。

再以 **CountDownLatch** 以例，任务分为 N 个子线程去执行，state 也初始化为 N(注意 N 要与线 程个数一致)。这 N 个子线程是并行执行的，每个子线程执行完后 countDown() 一次，state 会 CAS(Compare and Swap)减 1。等到所有子线程都执行完后(即 state=0)，会 unpark()主调用线 程，然后主调用线程就会从 await() 函数返回，继续后余动作。



#### **CountDownLatch**

```java
CountDownLatch countDownLatch = new CountDownLatch(4);
        Runnable task=()->{
        for (int i = 0; i < 10; i++) {
            System.out.println("123");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
        }};
        new Thread(task).start();
        countDownLatch.await(10, TimeUnit.SECONDS);
        System.out.println("321");
```



#### **CyclicBarrier** 

```java
CyclicBarrier cyclicBarrier = new CyclicBarrier(4);
        task=()->{
            System.out.println("+++");
            try {
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("---");
        };
        for (int i = 0; i < 4; i++) {
            new Thread(task).start();
        }
```





#### **Semaphore** 



* **Semaphore** **(**信号量**)-**允许多个线程同时访问(相当于有多把钥匙): synchronized 和 ReentrantLock 都是一次只 允许一个线程访问某个资源， Semaphore (信号量)可以指定多个线程同时访问某个资源。
* **CountDownLatch** (倒计时器): CountDownLatch 是一个同步工具类，用来协调多个线 程之间的同步。这个工具通常用来控制线程等待，它可以让某一个线程等待直到倒计时结 束，再开始执行。
* **CyclicBarrier** **(**循环栅栏**)**: CyclicBarrier 和 CountDownLatch 非常类似，它也可以实现线 程间的技术等待，但是它的功能比 CountDownLatch 更加复杂和强大



**CountDownLatch与CyclicBarrier的区别**

- CountDownLatch：一个或者多个线程，等待其他多个线程完成某件事情之后才能执行;
- CyclicBarrier：多个线程互相等待，直到到达同一个同步点，再继续一起执行。



**用过 CountDownLatch么?什么场景下用的?**

实际可以用Java8的CompletableFuture



### 原子操作类

使用原子操作类，减少对Synxhronized和Lock的使用

基本类型：AtomicInteger，AtomicLong，AtomicBoolean

数组类型：AtomicIntegerArray，AtomicLongArray，AtomicReferenceArray

对象的属性修改类型：AtomicIntegerFieldUpdater，AtomicLongFieldUpdater，AtomicReferenceFieldUpdater

引用类型：AtomicReference，AtomicStampedReference，AtomicMarkableReference

AtomicStampedReference可以解决ABA问题





**使用**

*AtomicInteger*，int的原子操作类，常用的方法有自增自减，compareAndSet(expect,update)当前值等于期待值时才进行更新。get获得int值，xxxValue可以获得long，double等值。

```
AtomicInteger num = new AtomicInteger(0);
num.getAndDecrement();
int i = num.intValue();
```

*AtomicIntegerArray* 通过下标对数组中的元素进行原子性操作(自增自减等)，compareAndSet(int i, int expect, int update)

```java
AtomicIntegerArray array = new AtomicIntegerArray(3);
int i = array.addAndGet(0, 1);
int i1 = array.get(0);
array.compareAndSet(0,1,1);
```

*AtomicIntegerFieldUpdater*用来更新对象中的实例变量,让普通变量也可以进行原子性操作

* 更新的变量必须使用volatile修饰

```
AtomicIntegerFieldUpdater<User> updater = AtomicIntegerFieldUpdater.newUpDater(User.class, "age");
updater.getAndIncrement(user);
```

*AtomicReference*， 对普通对象的封装，使普通对象也可以进行原子操作。

 ```java
AtomicReference<User> reference = new AtomicReference<>();
reference.compareAndSet()
 ```

 AtomicStampedReference内部维护了一个时间戳(实际使任意的整形数字)，可以用来解决ABA问题。 当AtomicStampedReference对应的数值被修改时，除了更新数据本身外，还必须要更新时间戳， AtomicStampedReference设置对象值时，对象值以及时间戳都必须满足期望值，写入才会成功 。

```java
compareAndSet(V   expectedReference,
              V   newReference,
              int expectedStamp,
              int newStamp);
```



### ForkJoin



**什么是 FutureTask**  

[ForkJoin](https://www.cnblogs.com/gjmhome/p/14410760.html)



### **CompletableFuture**



### ThreadLocal



**ThreadLocal是什么**

ThreadLocal变量是线程的本地变量，每个线程都可以有自己的副本，每个线程访问到的都是自己的变量，从而避免线程安全问题。



**ThreadLocal实现原理**

 Thread类有一个类型为ThreadLocal.ThreadLocalMap， 即每个线程都有一个属于自己的ThreadLocalMap。



**Thread、ThreadLocal与ThreadLocalMap之间的关系**

Thread 中持有一个ThreadLocalMap，Entry 的key是ThreadLocal 类型的，value 是Object 类型。也就是一个ThreadLocalMap 可以持有多个ThreadLocal。

ThreadLocalMap是Threadlocal的内部类，我们需要通过ThreadLocal来操作变量的值

在第一次调用ThreadLocal的set()/set() 方法的时候开始绑定(给线程创建ThreadLocalMap)， 

之后调用get/set方法时，先取到Thread中ThreadLocalMap，key为当前ThreadLocal的This，然后对Map进行操作

remove()，清空Thread中的ThreadLocalMap



**使用**

```Java
ThreadLocal<String> localVar = new ThreadLocal<>();
localVar.set("hello");
String str = localVal.get();
```



**内存泄漏问题**

原因：

`ThreadLocalMap` 是使用 `ThreadLocal` 的弱引用作为 `Key`的，弱引用的对象在 GC 时会被回收。 这时如果线程还在执行，就会有一条当前线程引用threadLocalMap引用entry引用value，这些值不能被访问页清除不了。

 解决方案：

其实，`ThreadLocalMap`的设计中已经考虑到这种情况，也加上了一些防护措施：在`ThreadLocal`的`get()`,`set()`,`remove()`的时候都会清除线程`ThreadLocalMap`里所有`key`为`null`的`value` 



**ThreadLocal的变量，父子线程之间是可以共享的吗**

 同一个ThreadLocal变量在父线程中被设置值后，在子线程中是获取不到的，因为key值不同



**怎么停止一个线程**

你可以**用** **volatile 布尔变量**来退出 run()方法的循环或者是取消任务来中断线程。

- interrupt()，在一个线程中调用另一个线程的interrupt()方法，即会向那个线程发出信号——线程中断状态已被设置。至于那个线程何去何从，由具体的代码实现决定。
- isInterrupted()，用来判断当前线程的中断状态(true or false)。
- interrupted()是个Thread的static方法，用来恢复中断状态，名字起得额



**什么时候会触发InterruptException**

打断wait时



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



**内存模型**

 JDK1.8 和之前的版本略有不同 

线程共有的：

***堆***

用来存放实例，几乎所有的对象实例都存在堆里，堆时JVM内存管理的核心区域，JVM中堆是最大的一块内存空间，所有线程共享的一块区域，在虚拟机启动的时候创建。

***方法区(即永久代)***

存储已被虚拟机加载的类信息，常量，静态变量等。方法区也称为永久代。常量池是方法区的一部分.方法区内存不足会抛出 OutOfMemoryError 。

*线程私有的*

***程序计数器***

记录着当前线程正在执行的字节码的地址，字节码解释器通过改变程序计数器来依次读取指令，从而实现代码的流程控制，线程切换后根据程序计数器能恢复到正确的执行位置。

***虚拟机栈***

保存着栈帧，描述方法执行的内存模型。每一次函数调用都会有一个对应的栈帧被压入 Java 栈，每一个函数调用结束后，都会有一个栈帧被弹出。 Java 虚拟机栈会出现两种异常：StackOverFlowError 和 OutOfMemoryError 

***本地方法栈***

与虚拟机栈相似，但是本地方法栈执行的是native方法。在hotspot虚拟机中本地方法栈和Java虚拟机栈合而为一。



***方法区，常量池的关系***

运行时常量池时方法区的一部分。 Class ⽂件中除了有类的版本、字段、⽅法、接⼝等描述信息外，还有常量池信息（⽤于存放编译期⽣成的各种字⾯量和符号引⽤） 。 JDK1.7及之后版本的 JVM 已经将运⾏时常量池从⽅法区中移了出来，在 Java 堆（Heap）中开辟了⼀块区域存放运⾏时常量池。



*栈帧*：存放着局部变量表、操作数（operand）栈、动态链接、方法正常退出或者异常退出的定义等 。 

*局部变量*：表存放着编译期可知的各种数据类和对象的引用

*操作数栈*：

*动态连接*：

Java虚拟机栈可能会出现两种错误：StackOverFlowError 和 OutOfMemoryError

***StackOverFlowError*** : 若 Java 虚拟机栈的内存大小不允许动态扩展，那么当线程请求栈 的深度超过当前 Java 虚拟机栈的最大深度的时候，就抛出 StackOverFlowError 错误。

***OutOfMemoryError*** : 若 Java 虚拟机堆中没有空闲内存，并且垃圾回收器也无法提供更多 内存的话。就会抛出 OutOfMemoryError 错误。



**类加载时机**

    （1）使用new实例化对象时，读取和设置类的静态变量、静态非字面值常量（静态字面值常量除外）时，调用静态方法时。
    
    （2）对内进行反射调用时。
    
    （3）当初始化一个类时，如果父类没有进行初始化，需要先初始化父类。
    
    （4）启动程序所使用的main方法所在类



**JVM加载class文件的流程**

类的加载是将class文件中的数据读取到内存，然后进行连接，验证，准备和解析，***生成Class对象***。

验证：  确保Class文件的字节流中包含的信息符合当前虚拟机的要求，并且不会危害虚拟机自身的安全。

准备： 开辟内存，设置默认值

解析：将常量池中的符号引用变为直接引用(内存地址)

初始化：为类的静态变量复初始值。执行clinit方法(执行静态变量初始化和静态代码块的执行，如果父类没有初始化，先初始化父类)



**静态代码块与静态变量，构造代码块，构造函数的执行顺序**

同一个类来说，静态代码块>构造代码块>构造函数

1. 父类静态代码块和静态变量
2. 子类静态代码块
3. 父类的构造代码块和构造函数
4. 子类的构造代码块和构造函数



**创建对象的过程**

1. 类加载检查：检查是否已经被加载过(到方法区中找)，没有的话要执行类加载操作
2. 分配内存：开辟堆内存空间
3. 初始化零值：就是设置默认值
4. 设置对象头：比如这个对象的Hash,GC分代的年龄，是哪个类的实例
5. 执行init方法：父类变量的初始化、语句块，构造器，子类的变量初始化，代码块，构造器



**数组在内存中如何分配**



**Java中常说的堆和栈，分别是什么数据结构；另外，为什么要分为堆和栈来存储数据**



**什么是双亲委派模型**

根据双亲委派模式，在加载类文件的时候，子类加载器首先将加载请求委托给它的父加载器，父加载器会检测自己是否已经加载过类，如果已经加载则加载过程结束，如果没有加载的话则请求继续向上传递直Bootstrap ClassLoader。如果请求向上委托过程中，如果始终没有检测到该类已经加载，则Bootstrap ClassLoader开始尝试从其对应路劲中加载该类文件，如果失败则由子类加载器继续尝试加载，直至发起加载请求的子加载器为止。



**怎么破坏双亲委派模型**



**类加载器的种类**

* 启动类加载器(Bootstrap ClassLoader): 最顶级的加载器， 负责加载jvm的核心类库，比如`java.lang.*`等，从系统属性中的`sun.boot.class.path`所指定的目录中加载类库 
* 扩展类加载器(Extension ClassLoader)：从java.ext.dirs系统属性所指定的目录中加载类库，或者从JDK的安装目录的jre/lib/ext子目录（扩展目录）下加载类库，如果把用户的jar文件放在这个目录下，也会自动由扩展类加载器加载。
* 应用程序类加载器(Application ClassLoader)：从环境变量classpath或者系统属性java.class.path所指定的目录中加载类。
* 自定义加载器(User ClassLoader)





**为什么要使用双亲委派模型**

 免类的重复加载，这样就可以保证任何的类加载器最终得到的都是同样一个Object对象。 



**为什么要自定义类加载器**

有时我们不一定是从类文件中读取类，可能是从网络的输入流中读取类，这就需要做一些*加密和解密操作*，这就需要自己实现加载类的逻辑，当然其他的特殊处理也同样适用 



## GC



**都有哪些垃圾回收算法，都有哪些弊端**

* 标记清除： ⾸先标记出所有不需要回收的对象，在标记完成后统⼀回收掉所 有没有被标记的对象。 造成了内存碎片
* 复制算法： 以将内存分为大小相同的两块，每次使⽤其中 的⼀块。当这⼀块的内存使⽤完后，就将还存活的对象复制到另⼀块去，然后再把使⽤的空间⼀次清理掉。浪费了一半内存。
* 标记整理：标记过程仍然与“标记-清除”算法⼀样，但后续步骤不是直接对可回收对象回收，⽽是让所有存活的对象向⼀端移动，然后直接清理掉端边界以外的内存。移动增加了开销。
* 分代收集：般将 java 堆分为新⽣代和⽼年代，每个代使用不同的算法。



**堆的划分**

总体分为新生代，老年代。具体分为Eden，FromSurcicor，ToSurvivor。对象创建后一般会进入Eden，当 eden 区没有足够空间进行分配时会进行一次MinorGC，Eden区还不行就判断Servicor区，Servivor还不行就判断老年代中行不行，不行进行FullGC，还不行就OOM。



**晋升的流程**

*Minor GC*

触发时机：

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

本地方法栈中引用的对象

方法区中类静态属性引用的对象

方法区中常量引用的对象



**四种引用**

强引用引用：大部分引用实际上都是强引用，垃圾回收器绝不会回收它

软引用：可有可无，如果内存空间足够，垃圾回收器 就不会回收它，如果内存空间不足了，就会回收这些对象的内存。

弱引用：可有可无，但是被辣鸡回收器发现会被回收

虚引用：形同虚设，主要用来跟踪对象被垃圾回收的活动。当垃圾回收器准备回收一个对象时，如果发现它还有虚引用，就会在回收对象的内存之前， 把这个虚引用加入到与之关联的引用队列中。程序可以通过判断引用队列中是 否已经加入了虚引 用，来了解被引用的对象是否将要被垃圾回收。程序如果发现某个虚引用已经被加入到引用队 列，那么就可以在所引用的对象的内存被回收之前采取必要的行动。



**内存泄露怎么定位**

jmap，jstack 的使用等等





# 算法



**进制转换**

Java内置的进制转换

10进制转其他进制：`Integer.toHexString(15)`

其他进制转10进制：`Integer.valueOf("F", 16)`



**去掉字符串中重复的子字符串**



**阶乘**





## **排序算法**



**冒泡排序**



**选择排序**



**插入排序**



**快排**



**有序集合的合并**



**倒序**





**字符串算法**



**KMP**



**最长公共前缀**



**最⻓回文串**



**最⻓回文子串**



**最⻓回文子序列**



**括号匹配深度**



**把字符串转换成整数**



**二叉树的遍历**



**逆波兰计算器**



**阶乘**



**斐波那契函数**



**素数判断**



**水仙花数**



**正整数分解质因数**



**最大公约数和最小公倍数**



**完数**



**完全平方数**



**堆排序概念**







