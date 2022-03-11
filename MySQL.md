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

1. 不满足最左匹配原则，使用左模糊或复合索引没有使用第一列
2. Null没有索引，进行控制判断失效
3. 使用不等于操作
4. OR左右都要使用到索引
5. 数据类型不匹配
6. 对索引字段上进行进行计算或使用函数



为什么使用B+树



## 事务



**事务失效场景**



**ACID**

***原子性*** ：不可分割

***一致性*** ： 事务前后数据的完整性必须保持一致 

***隔离性*** : 各个事务之间不能互相干扰 

***持久性*** :  持久性是指事务一旦提交，它对数据库的改变就应该是永久性的。接下来的其他操作或故障不应该对其有任何影响。 



**隔离级别**

| 隔离界别        | 描述                                 | 实现     |
| --------------- | ------------------------------------ | -------- |
| read uncommited | 读未提交，会导致脏读，不可重读和幻读 |          |
| read commited   | 读已提交，会导致不可重读和幻读       |          |
| (默认)          | 可重复度，会导致幻读                 | ReadView |
| 串行读          | 串行读                               | 加锁     |

- 脏读：指一个事务读取到了另外一个事务未提交的数据。
- 不可重复读：指一个事务读取到了另外一个事务提交的update的数据 ，导致前后两次读取的不一致
- 幻读：一个事务读取到了另一个事务insert的



### undo/redo



## 优化





**limit做分页有什么问题**

后面的性能会比较差



**优化的步骤**

* 索引
* SQL
* 机器
* 主从复制，读写分离





**慢查询定位**

1. 开启慢查询，设置慢查询时间
2. 分析慢查询日志，找到慢查询的语句
3.  利用explain关键字可以模拟优化器执行SQL查询语句，来分析sql慢查询语句 。
4. type： 显示连接使用了何种类型。从最好到最差的连接类型为const、eq_reg、ref、range、indexhe和ALL  
5. key



**sql优化从哪些方面考虑**

* 是否使用到索引
* 选择合适的字段
* 不存储Null，用0代替
* 避免使用select *
*  order by 条件要与where中条件一致，否则order by不会利用索引进行排序



## **存储引擎**



* MyISAM：较高的插入查询性能，支持全文索引，但不支持事务
* InnoBD：支持事务。支持行级锁(默认的存储引擎)
* Memory：所有数据存在内存中极高的插入和查询性能



## 触发器



## 存储过程



**存储过程怎么调试**



## 主从复制



MySQL的日志



**主从复制流程**



```
sharding.jdbc.datasource.names=master,slave0,slave1

sharding.jdbc.datasource.master.type=org.apache.commons.dbcp.BasicDataSource
sharding.jdbc.datasource.master.driver-class-name=com.mysql.jdbc.Driver
sharding.jdbc.datasource.master.url=jdbc:mysql://localhost:3306/master
sharding.jdbc.datasource.master.username=root
sharding.jdbc.datasource.master.password=

sharding.jdbc.datasource.slave0.type=org.apache.commons.dbcp.BasicDataSource
sharding.jdbc.datasource.slave0.driver-class-name=com.mysql.jdbc.Driver
sharding.jdbc.datasource.slave0.url=jdbc:mysql://localhost:3306/slave0
sharding.jdbc.datasource.slave0.username=root
sharding.jdbc.datasource.slave0.password=

sharding.jdbc.datasource.slave1.type=org.apache.commons.dbcp.BasicDataSource
sharding.jdbc.datasource.slave1.driver-class-name=com.mysql.jdbc.Driver
sharding.jdbc.datasource.slave1.url=jdbc:mysql://localhost:3306/slave1
sharding.jdbc.datasource.slave1.username=root
sharding.jdbc.datasource.slave1.password=

sharding.jdbc.config.masterslave.load-balance-algorithm-type=round_robin
sharding.jdbc.config.masterslave.name=ms
sharding.jdbc.config.masterslave.master-data-source-name=master
sharding.jdbc.config.masterslave.slave-data-source-names=slave0,slave1

sharding.jdbc.config.props.sql.show=true
```





## 分库分表



**什么是垂直分表，什么是水平分表**



**分布式ID是怎么生成的**



**分表情况业务主键的生成情况**

一个系统中有非常多业务表，生成业务主键ID一般都有一个单独模块



## 锁



当事务提交时，InnoDB存储引擎会做以下两件事情





InnoDB事务与日志实现的原理



有多少种日志



## bin log



**binlog录入方式**



redo log与binlog的区别

redolog是存储引擎产生的，binlog是数据库产生的，如果一个事务中对十万行记录进行了插入，这个过程中redolog会不断的记录，binlog只在提交的时候才写入binlog





# 锁

作用：用来实现并发事务的隔离级别

数据库中的数据是共享的，因此在并发事务对数据进行操作时需要加锁。



**并发事务的操作**

**读读**

都是读取，不会产生问题，不会加锁。

**写写**

同一时间智能有一个事务进行写，需要加锁。记录和锁结构之间刚开始没有关系，当一个事务需要对记录进行修改时，会先查看有没有锁结构与之关联，没有是就创建一个锁结构与之关联。获得锁成功之后才继续执行事务，获取锁失败会进入等待。

**读写**

一个事务进行读另一个事务进行写时，可能会出现赃读幻读不可重读的情况。

解决方案：读操作进行MVCC，写操作加锁。读已提交和可重读使用到了MVCC。在读已提交中，事务每执行SELECT时会生成一个ReadView，ReadView本省就保证了不会读取未提交的事务。在可重读中，只有在第一次执行SELECT是才会生成一个ReadView，之后的SELECT都会复用这个ReadView



## 行级锁



 ① 记录锁（Record Locks） 

 当一个事务获取了一条记录的S型记录锁后，其他事务也可以继续获取该记录的S型记录锁，但不可 以继续获取X型记录锁； 当一个事务获取了一条记录的X型记录锁后，其他事务既不可以继续获取该记录的S型记录锁，也不 可以继续获取X型记录锁。  

 ② 间隙锁（Gap Locks） 

  不允许别的事务在id值为8的记录前边的间隙插入新记录

 ③ 临键锁（Next-Key Locks） 

 有时候我们既想 锁住某条记录 ，又想 阻止 其他事务在该记录前边的 间隙插入新记录 



## 表级锁



**s锁，x锁**

在对某个表执行SELECT、INSERT、DELETE、UPDATE语句时，InnoDB不会给表加锁，在对某个表执行一些诸如 ALTER TABLE 、 DROP TABLE 这类的 DDL 语句时



**意向锁**

事务有意向对表中的某些行加加锁。意向锁是由存储引擎 ，用户无法手动操作意向锁，在为数据行加共享 / 排他锁之前， InooDB 会先获取该数据行 。



**自增锁**

自增Id



**NDL锁**







## 页级锁



**MySQL都有哪些锁**



**隔离级别与锁的关系**



**MySQL的乐观锁悲观锁怎么实现**



**表级锁和行级锁对比**

* 表级锁: MySQL中锁定 粒度最大 的一种锁，对当前操作的整张表加锁，实现简单，资源消 耗也比􏰁少，加锁快，不会出现死锁。其锁定粒度最大，触发锁冲突的概率最高，并发度最 低，MyISAM和 InnoDB引擎都支持表级锁。
* 行级锁: MySQL中锁定 粒度最小 的一种锁，只针对当前操作的行进行加锁。 行级锁能大 大减少数据库操作的冲突。其加锁粒度最小，并发度高，但加锁的开销也最大，加锁慢，会 出现死锁。





**乐观锁和悲观锁的对比**

***概念***

乐观锁：不上锁，提交数据的时候在去判断是否产生冲突，宽进严出。

悲观锁：读数据时先上锁，共享资源每次只给一个线程使用，其它线程阻塞，用完后再把资源转让给其它线程

***实现***

乐观锁CAS(Compare and Swap 比较并交换)

表中添加version字段，乐观锁需要用到这个字段。开启事务后添加where version=version，执行操作后如果影响的行数为0，则证明version发生了改变，就应该失败回滚，重新提交。

悲观锁有MySQL实现

***使用场景***

乐观锁

冲突很少，或冲突后果不会很严重，那么通常应该选择乐观锁，因为它能得到更好并发性，而且更容易实现

悲观锁



























# MVCC



每行记录都会存储事务版本，事务版本的id是递增的，一个事务只能读到它事务Id之前的数据。







# MyBatis































# 机试题



**建表语句**

```
DROP TABLE IF EXISTS `salary`;
CREATE TABLE `salary` (
  `id`int(11) NOT NULL,
  `name`varchar(10) NOT NULL,
  `sex`varchar(10) NOT NULL,
 `salary` int(11) NOT NULL,
  PRIMARYKEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```



**插入语句**

```
INSERT INTO `salary`(字段...) VALUES ('1', 'A', 'm','2500');
```

与表一致时字段可以省略







**每个部门的前三名**



**将sex字段的值f变成m，将m变成f**

set sex=if(sex="f","m","f")



**第二高的薪水**

```
ORDER BY Salary DESC
limit 1,1
```



**排名，带序号**

```
SELECT Score,(
    SELECT COUNT(DISTINCT s.Score)
    FROM Scores s
    WHERE s.Score >= Scores.Score) AS Rank
FROM Scores
ORDER BY Score DESC;
```



**查询连续出现的数字**

```
SELECT DISTINCT l1.Num AS ConsecutiveNums
FROM Logs l1 
LEFT JOIN Logs l2
ON l1.Id = l2.Id - 1
LEFT JOIN Logs l3
ON l1.Id = l3.Id - 2
WHERE l1.Num = l2.Num AND l1.Num = l3.Num;
```



**收入超过经理收入的员工**

每个员工有他经理的Id

自连接

```

```



**删除重复的电子邮件保留 Id 最小 的那个**

```mysql
DELETE p1
FROM Person p1,Person p2
WHERE (p1.Email = p2.Email) AND (p1.Id > p2.Id);
```



**找出每个部门中工资最高的员工**

先GroupBy获得每个部门最高的工资，再根据部门Id和工资关联部门表和员工表



**部门工资前三高的员工**

会有工资相同的情况，使用count(distinct)





