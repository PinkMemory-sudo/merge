# 索引



**什么是索引**

就像字典的目录，可以根据拼音和笔画进行查询。索引就是按照数据中的几个字段进行排序，查询时可以根据这个字段缩小范围。



**索引的数据结构**

高度可控的多路搜索树，IO的次数等于树的高度

***B树***



***B+树***

真实的数据存在与叶子节点，叶子结点本身依关键字的大小连接。

INnoDB数据文件的本身就是一个索引，数据文件是按照主键和B+Tree组织的，这种索引就称为聚簇索引。



**B+树的叶子节点存放什么数据(聚簇索引和非聚簇索引)**



**为什么是B+树**

单一节点可以存储更多节点，减少IO次数

所有查询都需要查询到叶子节点，查询性能稳定

所有叶子节点相连，便于范围查询



**索引的分类**

唯一索引：指定的某(些)列的值不能重复

主键索引：指定的某(些)列的值不重复且非空

普通索引：允许重复和空

全文索引：

1.普通索引index :加速查找
2.唯一索引
    主键索引：primary key ：加速查找+约束（不为空且唯一）
    唯一索引：unique：加速查找+约束 （唯一）
3.联合索引
    -primary key(id,name):联合主键索引
    -unique(id,name):联合唯一索引
    -index(id,name):联合普通索引
4.全文索引fulltext :用于搜索很长一篇文章的时候，效果最好。

多个字段组合创建的索引，当查询条件中使用了这些字段最左边的字段时才会生效，也遵循最左匹配原则

全文索引

只有MyISAM支持，只能作用在char，varchar和text。通过一句话中的某个单词找到这条记录



**主键索引和唯一索引的区别**

* 主键索引是非空且唯一的，唯一索引可以有空的



**什么是聚簇索引**

聚簇索引不是索引的一种种类，而是一种存储方式，数据和索引放在一起。聚簇表示数据行和相邻的键值紧凑的存储在一起。因为无法同时将数据行存储在两个位置，所以一个表只能有一个聚簇索引。



**聚簇索引的优势**

减少IO

* 对范围查询
* 适合排序



**聚簇索引和非聚簇索引的区别**

* 聚簇索引的树的叶子节点存放的是整个记录
* 非聚簇索引的叶子节点存放的是主键，当查询的字段不是索引时，就需要根据主键再去聚簇索引中取值(回表)



**非聚簇索引一定会回表查询吗，非覆盖索引**

非聚簇索引叶子节点不存出数据，如果向获得其他数据，就要再去聚簇索引中拿，而如果查询的字段是索引中的，就可以直接返回。(索引覆盖)



组合索引(k1,k2,k3)相当于创建了三个索引(k1),(k1,k2),(k1,k2,k3)



**覆盖索引是什么**

要查询的字段，都在当前使用的索引中，走完索引就不需要回表了。即要查询的字段索引已经覆盖了，不需要回表。



**索引下推**

MySQL5.6引入了索引下推。

索引下推的作用就是减少了回表的操作。

没有ICP的情况

- 存储引擎读取索引记录；
- 根据索引中的主键值，定位并读取完整的行记录；
- 存储引擎把记录交给`Server`层去检测该记录是否满足`WHERE`条件。

使用ICP的情况下，查询过程如下：

- 读取索引记录（不是完整的行记录）；
- 判断`WHERE`条件部分能否用索引中的列来做检查，条件不满足，则处理下一行索引记录；
- 条件满足，使用索引中的主键去定位并读取完整的行记录（就是所谓的回表）；
- 存储引擎把记录交给`Server`层，`Server`层检测该记录是否满足`WHERE`条件的其余部分。



**怎么创建索引**

创建普通索引：`CREATE INDEX (自定义)索引名 ON 数据表(字段);`

创建符合索引：`CREATE INDEX (自定义)索引名 ON 数据表(字段，字段，...);`



**可以使用多少列创建索引**



**怎么删除索引**

`删除索引：DROP INDEX 索引名;`



**查看表上定义的所有索引**



**使用索引一定能提高性能吗**



**索引的弊端**

* 增加了数据库的存储空间
* 在插入和修改数据时要花费较多的时间(因为索引也要随之变动)



索引创建的原则



最左匹配原则



**什么情况索引会失效**

* like以%开头
* or语句前后没有同时使用索引
* 在索引列上使用 IS NULL 或 IS NOT NULL操作。索引是不索引空值的，所以这样的操作不能使用索引
* 在索引字段上使用not，<>，!=。会扫描全表
* 对索引字段进行计算操作、字段上使用函数
* 字符串不加引号，如varchar不加单引号的话可能会自动转换为int型
* 没有使用组合索引的第一列索引
* 当全表扫描速度比索引速度快时，mysql会使用全表扫描，此时索引失效



MySQL5.6和5.7对索引做了哪些优化



怎么查看SQL使用的索引



值为NULL会使用索引吗



优化器执行的过程



Explain



索引的缺点





# 事务



**什么是事务**

事务就是将多个操作看成是一个原子性的操作，要么成功，要么所有事务都失败。



**事务的四大特性(ACID)**

* 原子性 将多个操作看成一个操作，要么都成功，要么都失败，失败后回滚，不能对数据库造成影响。
* 一致性 事务前后数据库的完整性约束不会收到破坏
* 隔离性 多个事务间不能相互影响，同一时间只允许一个事务操作同一个数据
* 持久性  事务提交后，接下来的操作或故障不会对持久化的数据造成影响



**并发事务带来的问题**

* 赃读，读取到其他事务中还没提交的数据，其他数据回滚时读到的数据就是脏数据
* 不可重读，一个事务多次读取数据，期间另一个事务修改了数据，导致读取的数据前后不一样
* 幻读，一个事务读取多行，另一个事务期间插入了几行，导致前后读取到的行数不一致



**事务的隔离级别，MySQL的默认隔离级别是什么**

***读取未提交：***一个事务读取到了另一个事务还没提交的数据，造成赃读，不可重读，和幻读

***读取已提交：***一个事务可以读取已提交的事务，造成不可重读，和幻读

***可重读：***一个事务中多次读取到的结果都是一样的，造成、幻读

***串行读：***完全符合ACID，事务一个一个执行

MySQL InnoDB 存储引擎的默认支持的隔离级别是 **REPEATABLE-READ**(可重读)

InnoDB虽然采用的是可重读的隔离级别，下使用的是Next-Key Lock 锁算法，因此可以避免幻读的产生。

InnoDB 存储引擎在 分布式事务 的情况下一般会用到 **SERIALIZABLE(**可串行化**)** 隔离级别。



MVCC底层实现原理



InnoDB事务与日志实现的原理



有多少种日志



binlog录入方式



日志的存放形式



事务如何通过日志实现的



事务回滚机制概述



# 存储引擎



**存储引擎的功能**



**MyISAM和InnoDB的区别**

|        | 事务                     | 外键                     | 行级锁                   | MVCC                     | 全文索引                 |
| ------ | ------------------------ | ------------------------ | ------------------------ | ------------------------ | ------------------------ |
| MyISAM | :heavy_multiplication_x: | :heavy_multiplication_x: | :heavy_multiplication_x: | :heavy_multiplication_x: | :heavy_check_mark:       |
| InnoDB | :heavy_check_mark:       | :heavy_check_mark:       | :heavy_check_mark:       | :heavy_check_mark:       | :heavy_multiplication_x: |

* 5.5版本后默认的存储引擎为InnoDB，之前是MyISAM
* 事务：MyISAM不支持事务
* 锁： MyISAM 只有表级锁(table-level locking)，而InnoDB 支持行级锁(row- level locking)和表级锁,默认为行级锁。
* 外键：MyISAM不支持外键
* MVCC：仅InnoBD支持，应对高并发事务, MVCC比单纯的加锁更高效;
* 清空整个表时，InnoDB是一行一行的删除，效率非常慢。MyISAM则会重建表
* MyISAM最大的缺陷就是崩溃 后无法安全恢复

MyISAM强调性能，但是也不一定比InnoDB快，尤其是使用到聚簇索引的时候



**使用场景**

* MyISAM适合查询以及插入为主的应用，
* InnoDB适合频繁修改以及涉及到安全性较高的应用

* InnoDB：需要事务和外键约束时，可以冲灾难中恢复





InnoDB的四大特性





# 优化



小表驱动大表？



优化长难句有什么实战



优化特定类型的查询



优化关联查询



优化子查询



优化LIMIT分页



优化UNION



优化where



优化distinct



数据库结构的优化



MySQL作为发布系统的存储，一天五万的增量，要维持三年，怎么优化



CPU飙升应该怎么处理



慢查询日志



数据库连接池为什么



数据库性能分析show status



# 读写分离



读写分离常见方案



复制的原理和流程



主从一致校验



主从延迟怎么解决



数据库中间价mycat



# 分库分表



**垂直分区**

优点：可以使得列数据变小，在查询时减少读取的Block数，减少I/O次数

缺点：主键会出现冗余，并会引起Join操作，此外，垂直分区会让事务变得更加复杂。



**水平分区**

表的行数超过200万行时，就会变慢，这时可以把一张的表的数 据拆成多张表来存放。表结构不变，根据某种算法将数据保存到不同的数据库或表中，达到分布式存储。

缺点：分片事务难以解决；跨节点Join 性能􏰁差，逻辑复杂

尽量不要对数据进行分片，因为拆分 会带来逻辑、部署、运维的各种复杂度 ，一般的数据表在优化得当的情况下支撑千万以下的数据 量是没有太大问题的。如果实在要分片，尽量选择客户端分片架构，这样可以减少一次和中间件 的网络I/O。



**分库分表后主键ID怎么设置**





# 增删改查



SQL分类



六种关联查询的使用场景



**数值类型**

| 类型         | 描述                                                         |
| ------------ | ------------------------------------------------------------ |
| INT或INTEGER | int(m)里的m并不影响实际的取值范围，它代表的是数据在显示时显示的最小长度 |
| BIGINT       |                                                              |
| FLOAT        |                                                              |
| DOUBLE       |                                                              |
| DECIMAL      | DECIMAL(M,D)，m是总长度，d是小数位个数,decimal(10, 0)以字符串形式进行保存的 |
| TINYINT      |                                                              |
| SMALLINT     |                                                              |
| MEDIUMINT    |                                                              |

可以在后面加 unsigned，表示无符号



**日期和时间类型**

| 类型      | 描述 |
| --------- | ---- |
| DATE      |      |
| TIME      |      |
| DATETIME  |      |
| TIMESTAMP |      |
| YEAR      |      |



**MySQL的创建时间和修改时间怎么自动更新**

方案1：创建表的时候指定

```mysql
...
`create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
`update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
...
```

方案2：修改字段

```mysql
ALTER TABLE `course`
MODIFY COLUMN `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ;
ALTER TABLE `course`
ADD COLUMN `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ;
```



**字符串类型**

| 类型       | 描述                            |
| ---------- | ------------------------------- |
| char       |                                 |
| varchar    |                                 |
| TIMYBLOB   | 不超过 255 个字符的二进制字符串 |
| TINYTEXT   |                                 |
| BLOB       | 二进制形式的长文本数据          |
| TEXT       |                                 |
| MEDIUMBLOB |                                 |
| MEDIUMTEXT |                                 |
| LONGBLOB   |                                 |
| LONGTEXT   |                                 |

char(n) 和 varchar(n) 中括号中 n 代表字符的个数，并不代表字节个数，比如 CHAR(30) 就可以存储 30 个字符。



int(20),char(20)和varchar(20)的区别

* 无论n等于多少，int永远占4个字节，n表示的是显示宽度，不足的用0补足，但这要整型设置了unsigned zerofill才有效。超过的无视长度而直接显示整个数字，所以准确来说n表示的是显示的最小的位数



为什么要定义一个主键



分布式主键设计方案



**DATETIME和TIMESTAMP的区别**

* datetime的默认值是null，timestamp的默认值是当前时间
* datetime存储的时间与时区无关，timestamp存储的时间及显示的时间都依赖于当前时区 
* datetime占8个字节，timestamp占4个字节 所以存储的范围不一样



TEXT类型的最大长度



BLOB和TEXT的区别



货币使用什么数据类型



什么是非标准字符串类型



emoji表情使用什么类型存储



怎么获得当前日期



NOW()和CURRENT_DATE的区别



如果有一个字段定义为TIMESTAMP，将会发生什么



时间存储



UNIX和MySQL之间时间戳的转换



日期如何考虑时区转换的问题



字段类型考虑的优先级



确保表格中的样式只接受指定范围的值



字段为什么要定义成NOT NULL



主键使用自增ID还是UUID



MySQL自增ID用完怎么办



使用什么类型存储用户的密码散列



MySQL中有哪些表格



百万级别以上的数据如何删除



LIKE中%和_的含义



IN和EXISTS的区别



**内连接，外链接什么意思**

内连接：只连接匹配的行

外连接分为左外连接，右外连接和全连接



找到最后一次插入是分配的自增ID



SQL执行过程



**TRUNCATE与DELETE的区别**

* TRUNCATE用来清空整张表，效率高
* 自增约束，TRUNCATE从1开始，DELETE继续往后
* TRUNCATE不支持回滚
* TRUNCATE后没有返回值，DELETE后返回受影响的函数





UNION和UNIONALL的区别



一个6亿数据的表A和3亿数据的表B，怎么快速查询处第50000到50200的数据



大表查询怎么优化



超大分页怎么处理



SQL的执行顺序



**count(*)、count(1)、count(column)的区别**

执行结果：count(*)和count(1)没有区别，都不会过滤空值，count(column)不包含空值

执行效率：MyISAM 引擎会把一个表的总行数记录了下来，所以在执行 count(*) 的时候会直接返回数量，执行效率很高，5.5后MySQL的默认存储引擎是InnoDB，因为增加了版本控制(MVCC)的原因，同时有多个事务访问数据并且有更新操作的时候，每个事务需要维护自己的可见性，那么每个事务查询到的行数也是不同的，所以不能缓存具体的行数，他每次都需要 count 一下所有的行数。`InnoDB handles SELECT COUNT(*) and SELECT COUNT(1) operations in the same way There is no performance difference`

效率问题：

MYISAM，内部有计数器，COUNT(*)可以直接获得表的列数

INNODA，COUNT(*)和COUNT(1)相差不大，但比COUNT(colunm_name)效率高(多了IS NULL判断)



什么是游标



外连接内连接与自连接的区别



## 多表查询





# 存储过程



**什么是存储过程，怎么调用**

一组为了完成特定功能预编译的SQL 语句集，一次编译后永久有效。如果某次操作需要执行多句 SQL，使用存储过程比单纯 SQL语句执行要快。
调用：

* 可以用一个命令对象来调用存储过程。

* 可以供外部程序调用，比如：java 程序。



**存储过程的优点**

* 存储过程是预编译过的，执行快。
* 存储过程的代码直接存放于数据库中，通过存储过程名直接调用，减少网络通讯。
* 安全性高，执行存储过程需要有一定权限的用户。
* 存储过程可以重复使用，可减少数据库开发人员的工作量。缺点：移植性差



# 触发器



什么是触发器，触发器的使用场景



**什么是触发器**

触发器是特殊的存储过程，通过事件触发执行。用来维护数据的完整性和一致性。



MySQL中都有什么触发器



# 视图



**什么是视图**

通俗的讲，视图就是一条SELECT语句执行后返回的结果集，不存储具体的数据，而是存储视图的定义。所以我们在创建视图的时候，主要的工作就落在创建这条SQL查询语句上。视图是一张虚拟的表，具有和物理表相同的功能，对视图的修改会影响基本表。



**视图的优点**

方便操作，特别是查询操作，减少复杂的SQL语句，增强可读性，视图可以隐藏表表间的复杂关系。

更加安全，数据库授权命令不能限定到特定行和特定列，但是通过合理创建视图，可以把权限限定到行列级别；



**使用场景**

* 授权：客户想要直接查询数据库，不希望用户访问表中某些含敏感信息的列，可以创建视图和用户，将视图授权给用户。

* 表连接：关键信息来源于多个复杂关联表，可以创建视图提取我们需要的信息或者有的时候，由于表中的数据量太大，需要对表进行拆分，这样会导致表的结构发生变化，导致用户的应用程序受到影响，这时我们就可以使用视图来屏蔽实体表间的逻辑关系，去构建应用程序所需要的原始表关系




**视图的分类**

* MERGE：将视图的sql语句和引用视图的sql语句合并在一起，最后一起执行。

* TEMPTABLE： 将视图的结果集存放在临时表中，每次执行时从临时表中操作。

* UNDEFINED： 默认的视图类型，DBMS倾向于选择而不是必定选择MERGE，因为MERGE的效率更高，更重要的是临时表视图不能更新。

所以，这里推荐使用MERGE算法类型视图。



**怎么创建视图**

```mysql
CREATE ALGORITHM = UNDEFINED 
DEFINER = `root`@`localhost` 
SQL SECURITY DEFINER
VIEW `视图名` AS (
    select ...
);
```

ALGORITHM=UNDEFINED：指定视图的处理算法；

DEFINER=`root`@`localhost`：指定视图创建者；

SQL SECURITY DEFINER：指定视图查询数据时的安全验证方式；



**怎么使用视图**

将视图名当成表名

```mysql
select
视图名.属性
from 视图
```





# 范式



**三大范式**

终极目标是为了减少数据的冗余，一般情况下只有前三种范式需要满足

1NF:所有字段都是不可分割的

2NF:属性完全依赖于主键，没有部分依赖组件的属性

3NF:解决依赖传递问题



超键和候选键是什么



主键和候选见的关系



MySQL中有什么约束



完整性约束包含哪些





# 锁



**表级锁和行级锁对比**

* 表级锁: MySQL中锁定 粒度最大 的一种锁，对当前操作的整张表加锁，实现简单，资源消 耗也比􏰁少，加锁快，不会出现死锁。其锁定粒度最大，触发锁冲突的概率最高，并发度最 低，MyISAM和 InnoDB引擎都支持表级锁。
* 行级锁: MySQL中锁定 粒度最小 的一种锁，只针对当前操作的行进行加锁。 行级锁能大 大减少数据库操作的冲突。其加锁粒度最小，并发度高，但加锁的开销也最大，加锁慢，会 出现死锁。



**乐观锁和悲观锁**

悲观锁如行锁，表锁，读锁，写锁等，都是在做操作之前先上锁。

乐观锁每次去拿数据的时候都认为别人不会修改数据，所以不会上锁，但是在更新的时候会判断一下在此期间别人有没有去更新这个数据

乐观锁适合在多读的应用类型，这样可以提高吞吐量，如果经常插入，产生冲突的可能性就比较大，上层应用会不断的进行 retry，这样反倒是降低了性能，所以这种情况下用悲观锁就比较合适。



**InnoDB存储引擎的三种锁的算法**

* Record lock:单个行记录上的锁

* Gap lock:间隙锁，锁定一个范围，不包括记录本身 
* Next-key lock:record+gap 锁定一个范围，包含记录本身



**行锁和表锁的使用场景**



**MySQL的死锁**





**什么是MVVC**



**隔离级别与锁的关系**



**MySQL的乐观锁悲观锁怎么实现**



高并发情况下修改同一行数据



MySQL中有哪几种锁

按锁的粒度



乐观锁和悲观锁



select for update



MySQL一条SQL加锁的分析



锁的优化策略



MySQl死锁问题遇到过吗？怎么解决的











# 其他



MySQL有关权限的表都有哪些



**SQL注入产生的原因，如何预防**

将执行语句作为参数传进去，可以使用预编译，预编译不会执行参数中的语句



**什么是SQL预编译**

 很多情况下，一条SQL语句可能会反复执行，或者每次执行的时候只有个别的值不同。指的是数据库驱动在发送 sql 语句和参数给 DBMS 之前对 sql 语句进行编译，这样 DBMS 执行 sql 时，就不需要重新编译。 



 **PreparedStatement为什么能在一定程度上防止SQL注入？** 

 在使用参数化查询的情况下，数据库不会将参数的内容视为SQL执行的一部分，而是作为一个字段的属性值来处理，这样就算参数中包含破环性语句（or ‘1=1’）也不会被执行。



MySQL的基础架构图



500台DB怎么快速重启



如何监控数据库



备份计划MySQLdump和xtranbackup的实现原理



数据表损坏有什么修复方案







MyISAMchk是干什么的



MyISAM Static和MySIAM Dynamic的区别



**备份和恢复**













































































































































































































分库分表

**索引的数据结构**



每个节点中存的是什么数据



B树

多路平衡查找树，与平衡二叉树的区别是B树可以有多个子节点

节点大小为一页



B+树

非叶子节点不存储数据，

叶子节点相连，增加区间访问效率



为什么使用B+树而不使用B树



每一次IO读取的数据我们称之为一页(page)，具体一页有多大数据跟操作系统有关，一般为4k或8k





























































































# MVCC




