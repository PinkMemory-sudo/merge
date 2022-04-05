* 组成
* 流程
* 编码规范，Mapper，Reduce，Driver的编写，实现单词统计·

分布式运算程序框架

MapReduce 核心功能是将用户编写的业务逻辑代码和自带默认组件整合成一个完整的 分布式运算程序，并发运行在一个 Hadoop 集群上。  负责海量数据的计算，分为Map阶段和Reduce阶段。先分任务，在汇总结果



必须指定输入路径和输出路径，输出路径不能存在，存在的话抛异常



优点：

* 易于编程
* 良好的扩展性
* 高容错

缺点：

* 不擅长实时计算
* 不擅长流式计算
* 不擅长DAG(有向无环图)计算



# **核心思想**





# MapReduce进程

* MrAppMaster：负责整个程序的过程调度及状态协调
* MapTask：负责 Map 阶段的整个数据处理流程
* ReduceTask：负责 Reduce 阶段的整个数据处理流程





序列化

Java的类型对应到Hadoop的Writeable类型



用户编写的程序分成三个部分：Mapper、Reducer 和 Driver。 



# Mapper接口





# Reduce接口



# 序列化

Java 的序列化是一个重量级序列化框架（Serializable），一个对象被序列化后，会附带很多额外的信息（各种校验信息，Header，继承体系等），不便于在网络中高效传输。所以，Hadoop 自己开发了一套序列化机制（Writable）。



# 工作流程



# ETL