HDFS：Hadoop DIstributed File System，是一个分布式文件系统，将文件存在多台机器上



**组成**



**NameNode**

数据都存在什么位置。存储文件的元数据，如文件名，文件目录结构，文件属性(生成时间，副本数，文件权限)，以及每个文件的块列表和块所在的DataNode等。



**DataNode**

具体存储数据的位置。在本地文件系统存储文件块数据，以及块数据的校验和。



**2NN**

辅助NameNode工作。每隔一段时间对NameNode元数据备份。