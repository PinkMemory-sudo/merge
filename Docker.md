**Docker网络**





 **Docker与虚拟机有何不同** 

* 虚拟机虚拟出一套完成的硬件资源，而Docker直接使用宿主机的内核，没有其他开销，接近本机的性能



**什么是Docker镜像**

就像Java中的类用来创建对象，Docker镜像用来创建Docker容器，是Docker容器的源代码



**什么是容器**





**DockerFile中常用的命令**

FROM指定基础镜像

RUN运行指定的命令

CMD容器启动时运行的命令



**COPY和ADD的区别**

 COPY与ADD的区别COPY的SRC只能是本地文件，其他用法一致