相比synchronazed，Lock作用范围更加灵活。



**synchronazed，Lock的区别**

* synchronazed是关键字，Lock是类
* Lock作用范围更加灵活，所以需要手工释放锁，并且在发生异常时也不会释放锁，所以加锁部分的代码要用try-catch，释放锁放在finally中





注意循环或者判断条件中的Lock



**Lock接口**

```
# 用来获取锁。如果锁已被其他 线程获取，则进行等待
void lock();
void lockInterruptibly() throws InterruptedException;
# 尝试获取锁：成功获取则返回true；获取失败则返回false
boolean tryLock();
boolean tryLock(long time, TimeUnit unit) throws InterruptedException; void unlock();
Condition newCondition();
```



## Condition

wait()/notify()这两个方法一起使用可以实现等待/通 知模式，使用 Condition 类可以 进行选择性通知。

Condition,加强版的wait/nodity，只唤醒相同Condition对象。

- await()会使当前线程等待,同时会释放锁,当其他线程调用 signal()时,线程会重 新获得锁并继续执行。
- signal()用于唤醒一个等待的线程。



# 使用



**格式**

```
Lock lock = ...; 
lock.lock(); 
try{
//处理任务
}catch(Exception ex){ 
}finally{
lock.unlock();
}
```























加锁相当于加了个执行条件，不仅要获得CPU执行权，还要有锁才能执行，这样保证了当有锁的线程执行完别的线程才能执行。



## **ReentrantLock**



什么是可重入锁？synchronized可重入吗

一个线程在获得锁对象后，再次申请锁对象能否获得锁对象。不可重入时，就会产生死锁





## **ReadWriteLock**



接口

```
Lock readLock();
Lock writeLock();
```

好处：

* 如果当前写锁没有被占用，则多线程去读时相当于不加锁，如果写锁会占用，会阻塞
* 如果当前读锁被占用，申请写锁时会阻塞



## **ReentrantReadWriteLock**



# 线程通信

两种方式：

* 共享内存
* 消息传递



wait/notify总结：

* 在执行方法前，先判断条件是否满足，不满足需要wait，满足再想下执行。
* 执行完之后需要notify，通知其他线程































## CopyOnWriteArrayList



* 线程安全
* 它最适合于具有以下特征的应用程序:List大小通常保持很小，只读操作远多 于可变操作，需要在遍历期间防止线程间的冲突。