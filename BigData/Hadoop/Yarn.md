Yarn：YetAnnother Resource Negitiator，资源协调者，是Hadoop的资源管理器，主要就是用来管理CPU和内存。



**构成**



**ResourceManager**

整个集群资源的老大



**NameMenager**

单个节点上的资源老大



**ApplicationMaster**

单个任务运行的老大。提交一个作业就会创建一个ApplicationMaster，放进Contatiner中



**Container**

容器，相当于一台独立的服务器，里面封装了CPU，内存，磁盘，网络等资源。