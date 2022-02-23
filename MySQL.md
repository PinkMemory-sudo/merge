# MySQL



## 索引



**B树和B+树的区别**



**为什么使用B+树**



**MySQL索引原理，有索引的情况下怎么插入数据**



**MySQL中创建索引的原则**



**MySQL有哪些索引类型**

* 普通索引：
* 主键索引：
* 唯一索引：
* 组合索引：多列组成一个索引
* 全文索引：只有MyISAM支持



**什么是聚簇索引**

聚簇索引存数据，其他索引存ID



**InNoDB中主键索引和非主键索引有什么区别**



**什么是覆盖索引**



**索引下推**



**什么是回表查询**



**索引失效**



为什么使用B+树



## 事务



**事务失效场景**



**ACID**

***原子性*** ：不可分割

***一致性*** ： 事务前后数据的完整性必须保持一致 

***隔离性*** : 各个事务之间不能互相干扰 

***持久性*** :  持久性是指事务一旦提交，它对数据库的改变就应该是永久性的。接下来的其他操作或故障不应该对其有任何影响。 



**隔离级别**

| 隔离界别        | 描述                                 |
| --------------- | ------------------------------------ |
| read uncommited | 读未提交，会导致脏读，不可重读和幻读 |
| read commited   | 读已提交，会导致不可重读和幻读       |
| (默认)          | 可重复度，会导致幻读                 |
|                 | 串行读                               |

- 脏读：指一个事务读取到了另外一个事务未提交的数据。
- 不可重复读：指一个事务读取到了另外一个事务提交的update的数据 ，导致前后两次读取的不一致
- 幻读：一个事务读取到了另一个事务insert的



### undo/redo



## 优化





**limit做分页有什么问题**

后面的性能会比较差



**MySQL调优的思路和步骤**

* 机器内存，buffer pool
* 调用链，索引没交或者没有命中索引，或者说有回表操作



**sql优化从哪些方面考虑**

* SQL语句优化



**Sql查询慢怎么定位**



数据库的分页机制，以及一个场景分析( limit 0,5 为什么比 limit 5000,5005快)



## **存储引擎**



* MyISAM：较高的插入查询性能，支持全文索引，但不支持事务
* InnoBD：支持事务。支持行级锁(默认的存储引擎)
* Memory：所有数据存在内存中极高的插入和查询性能



## 触发器



## 存储过程



**存储过程怎么调试**



## 主从复制



MySQL的日志



## 分库分表



**什么是垂直分表，什么是水平分表**



**分布式ID是怎么生成的**



**分表情况业务主键的生成情况**

一个系统中有非常多业务表，生成业务主键ID一般都有一个单独模块



## 锁



**行锁表锁**





**乐观锁和悲观锁的对比**

***概念***

乐观锁：不上锁，提交数据的时候在去判断是否产生冲突，宽进严出。

悲观锁：读数据时先上锁，共享资源每次只给一个线程使用，其它线程阻塞，用完后再把资源转让给其它线程

***实现***

乐观锁CAS(Compare and Swap 比较并交换)

表中添加version字段，乐观锁需要用到这个字段。开启事务后添加where version=version，执行操作后如果影响的行数为0，则征兵version发生了改变，就应该失败回滚，重新提交。

悲观锁有MySQL实现

***使用场景***

乐观锁

冲突很少，或冲突后果不会很严重，那么通常应该选择乐观锁，因为它能得到更好并发性，而且更容易实现

悲观锁



**行锁，表锁，读锁，写锁**



## MVCC









# MyBatis