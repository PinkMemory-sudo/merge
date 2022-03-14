？

* 解决了传统IO的什么问题
* NIO是怎么工作的





三个核心组件：

Channel

Buffer

Selector

其他组件：

Pipe

FileLock



# **概述**：



[参考](https://tech.meituan.com/2016/11/04/nio.html)



解决高并发与大量连接、I/O处理问题的有效方式 。

IO阻塞，CPU空闲时，可以利用多线程使用CPU资源

线程池本身就是一个天然的漏斗，

但是线程资源是很宝贵的：

* 线程的创建和销毁都是重量级的系统函数， 在Linux这样的操作系统中，线程本质上就是一个进程。
* 线程本身占用较大内存，像Java的线程栈，一般至少分配512K～1M的空间，如果系统中的线程数过千，恐怕整个JVM的内存都会被吃掉一半
*  线程的切换成本是很高的。操作系统发生线程切换的时候，需要保留线程的上下文，然后执行系统调用。如果线程数过高，可能执行线程切换的时间甚至会大于线程执行的时间 



**阻塞和非阻塞**

发送IO请求，没有请求到时会一直等待



**同步和非同步**

进行IO操作，没有操作完时会一直等待



 **Reactor**

注册时间，轮询查询



**I/O多路复用**



**与传统IO的对比**

IO分成两个阶段：等待就绪和操作。

传统IO在等待就绪阶段不知道什么时候可以读，就会一直阻塞，而等待就绪这个阶段是不需要CPU的，而操作阶段(读写)才会使用到CPU，基于memorycopy是非常快的

NIO进行读写时会立马返回，





**为什么人使用NIO**

当面对十万甚至百万级连接的时候，传统的BIO模型是无能为力的 



**Chanel,Buffer与Select的关系**

将Chanel中的数据读到Buffer，将Buffer中的数据写到Chanel

一个 selector 允许一个线程处理多个 channel。





**特点**：



# Channel



# Buffer



# Selector

Selector是阻塞的









* NIO与传统的IO的区别



NoBlocking

分组IO

更加高效？

1. 传统IO面向流，NIO面向缓冲区

建立目标文件与应用程序之间用于传输数据的数据流，IO直接面向字节数组的流动，**单向**的，分输入流和输出流。

建立目标文件与应用程序之间用于传输数据的通道，通道本身不进行数据传输，数据的传输依赖于缓冲区，缓冲区

是**双向**的

2. 针对于网络IO，IO是阻塞的，NIO是非阻塞的







缓冲区，根据数据类型的不同，提供了不同类型的缓冲区(boolean除外)

byte,short,int,long,float,double，都集成buffered

通过allocath获得缓冲区，最常用的树byte，分配一个指定大小的缓冲区来存储数据。put,get。

**缓冲区的4个核心属性**

capacity，容量，缓存区本质就是数组，所以容量声明后不能改变

limit，缓冲区中可以操作数据的大小，limit后面的数据不能进行读写。

如果limit小于0或者大于capacity则抛异常，小于position则limit置为position，小于mark则mark失效

position，缓冲区中正在操作的位置，

position <= limit <= capatity







**Buffer**



flip

反转读写模式，buffer初始的默认模式为写







get获得position的byte并且position自增一

重载的方法：



put将byte写入缓冲区的position



compact

将position和limit之间的byte拷贝到缓冲区的最前面。

从缓冲区写完数据后调用compact



wrap变为阻塞的

position变为0，limit/capacity成为数组的长度，mark失效



slice

```
Creates a new byte buffer whose content is a shared subsequence of this buffer's content.
```

创建一个新缓冲区共享此缓冲区，缓冲区的改变对新缓冲区可见





























# IO与NIO

NIO，异步非阻塞





# 使用





# Channel

源节点与目标节点的连接，但是不能操作数据，

调用操作系统的IO接口



**Channel的主要实现类**

| 实现类              | 描述                                                         |
| ------------------- | ------------------------------------------------------------ |
| FileChannel         | 对本地文件进行操作                                           |
| SocketChannel       | TCP网络数据的传输                                            |
| ServerSocketChannel | 可以监听新进来的 TCP 连接，像 Web 服务器那样。对每一个新进来的连接都会创建一个 SocketChanne |
| DataGramChannel     | UDP网络数据传输                                              |



## 通道与流的区别



**应用程序与磁盘之间的数据写入或者读出，都需要由用户地址空间和内存地址空间之间来回复制数据。**

内存地址空间中的数据通过操作系统层面的IO接口，完成与磁盘的数据存取



通道的主要实现类  

* FileChannel 用于本地文件数据传输  
* SocketChannel 用于网络，TCP 
* ServerSocketChannel 用于网络，TCP
* DatagramChannel 用于网络，UDP



通道之间的数据传输 

* transferForm() 
* transferTo()

## 获得Channel的三种方式：



1. 支持Channel的类中getChannel方法获得对应的Channel(底层调用的是FileChannelImpl.open,可读不可写)

**本地IO中**

* FileOutputStream/FileInputStream
* RandomAccessFile



**网络IO中**

* Socket
* ServerSocker
* DatagramSocker



2. JDK1.7NIO2中的静态方法Open



2. JDK1.7中NIO2中工具类Files里的newByteChannel





# Buffer







mark

记录当前position的位置



reset

将position置为上一个(即mark标记的)



rewind，重复读

clear，清空缓冲区，三个指针变了，但是缓冲区中的数据还存在。



isDirect

判断是不是直接缓冲区



直接缓冲区与非直接缓冲区







**弊端**

写入到物理内存中的数据Java就操作不了的，什么时候写入到磁盘中，完全由操作系统控制。

**直接建立到物理内存中消耗大？为什么？什么时候用直接缓冲区？**



## MappedByteBuffer



直接缓冲区与非直接缓冲区

内存映射



直接缓冲区与非直接缓冲区

直接缓冲区：

应用程序直接操作物理映射文件，IO操作不用在操作系统和虚拟机之间赋值，提高了读写速度，但是增加内存的消耗，内存的释放只能通过Java的垃圾回收机制来释放，并不是一不使用就回收。

* 通过allocateDirect获得的缓冲区
* 建立在物理内存上



非直接缓冲区：

* 通过allocate分配的缓冲区
* 建立在JVM上



**直接缓冲区会提高效率？**

应用程序和物理磁盘间的数据没有办法进行直接传输，应用程序面向操作系统的IO接口

一般情况下,最好仅在直接缓神区能在程序性能方面带来明显好处时分配它们。



**直接缓冲区效率高的原因**(与间接缓冲区的区别)

磁盘文件->OS->JVM->应用程序

首先会读取到内核的地址空间，然后拷贝到JVM，所以传统的IO和非直接缓冲区浪费了一步拷贝。



**内存映射文件**

直接缓冲区，直接操作OS中的物理内存，磁盘文件读入内存，应用程序直接操作内存。



直接缓冲区通道之间的数据传输



**使用直接缓冲区完成文件复制**





**RandomAccessFile**

随机访问，我们之前创建的文件都是从开头开始的，而RandomAccessFile可以指定读写的位置

既可以读也可以写。

常用方法：

获得RandomAccessFile

`RandomAccessFile(String name, String mode)`

`RandomAccessFile(File file, String mode)`

mode的取值：r、rw、rws、rwd



| 方法                | 描述                                     |
| ------------------- | ---------------------------------------- |
| seek(int index)     | 将指针移动到某个位置开始读写             |
| setLength(long len) | 给写入文件预留空间                       |
| wirteXxx            | 会覆盖掉指针后面的数据                   |
| readLine            | 读一行,意识位置不是0，而是指针所在的位置 |



## 分散(Scatter)和聚集(Gather)



**分散读取与聚集写入**，就是在读写时可以传进去一个Buffer数组

scatter/gather

分散读取:将Channel中的数据分散到各个Buffer中，按照缓冲区的顺序，一次将缓冲区填满

聚集写入:将多个Buffer的数据聚集到Channel中，将缓冲区中的数据依次写入缓冲区



将缓冲区转换为字节数组。







## charBuffer















## 







































# Selector

多用复路器

用于监听多个Channel的事件(连接打开，数据到达)



字符集Charset



**NIO的核心在于网络IO**



传统IO是阻塞的：客户端向服务端发送请求，服务端会一直等待接收客户端数据，服务端的该线程会一直阻塞。

客户端在想服务器发送数据时，数据会首先到达系统中的内核地址空间，线程会阻塞到内核地址空间中有数据，才会将数据拷贝到用户地址空间，读取到程序中。



**NIO的非阻塞式**

关键：选择器

会将通道注册到选择器上，选择器会监控通道的IO事件。IO准备就绪时，选择器才给通道分配线程。



获得网络通道

通道分为本地通道和网络通道





异步读取文件

**Java NIO AsynchronousFileChannel**



使用直接缓冲区完成文件的复制(内存映射文件)



常见错误

`java.nio.channels.NonWritableChannelException`





















