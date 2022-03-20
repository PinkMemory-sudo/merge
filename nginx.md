

* 什么是Nginx
* 配置
* Nginx高可用？





# 概述



**常用命令**

sbin目录下执行`./nginx`启动nginx

`./nginx -s stop` 停止Nginx

`./nginx -s quit`平缓停止，可以将当前正在处理的请求执行完

`./nginx -s reload`重启Nginx

` nginx -t -c /usr/local/nginx/conf/nginx.conf `检测配置文件语法是否正确



**正向代理和反向代理的区别**

* 正向代理代理的是客户端，反向代理代理的是服务端
* 反向代理
* 负载均衡



**nginx的负载均衡策略**

* 轮询
* 设置权重
* 一致性hash，ipHash与url_hash



# 配置文件



**主体结构**

![1647787954421](E:\note\2\merge\img\Nginx配置文件结构.png)



## 全局块

 `woker_processes 2` ,工作进程数可以设置成CPU核心数



## events

`worker_connections  1024;` 表示每个 work process 支持的最大连接数为 1024. 



## http



### server

可以看成是一台独立的硬件主机 



#### location

 指定模式来与客户端请求的URI相匹配 

**指定前缀**  

必须以abc开始

```
location /abc {

}
```

**=**

表示必须请求匹配

uri必须是/abc

```
location /abc {

}
```

**location遵循最大前缀匹配结果** 





# 反向代理

正向代理代理客户端，反向代理代理服务器。

反向代理：

多个服务对外表现为一个服务器，客户端直接访问代理服务器，反向代理决定请求哪个服务器。



**配置**

```
server{
	listen 监听的端口号;
	server_name 监听的主机名;
	
	location /mail {
		proxy_pass http://127.0.0.1:8080
		index 
	}
	
	location /phone {
		proxy_pass http://127.0.0.1:8081
		index 默认首页
	}
}
```





# 负载均衡

就是配置一个upstream，location中的proxy_pass中使用upstream的名字

**配置upstream** 

```
upstream OrdinaryPolling {
	server 127.0.0.1:8080 weight=5;
	server 127.0.0.1:8081 weight=2;
}
server {
        listen       80;
        server_name  localhost;

        location / {
            proxy_pass http://OrdinaryPolling;
            index  index.html index.htm index.jsp;

        }
    }
```

可以进行加权



**负载均衡策略**

* 轮询(能够自动提出故障的服务器)
* 权重(weigth越大，访问概率越高)
* ip_hash,相同请求地址会分到同一个后端服务，保证session共享



# 动静分离

静态资源就是HTML，IMG等这些可以直接访问的文件

动态资源则是需要后端进行计算的资源



```
location ~* \.(png|jpg|mp4)${
     #指定图片路径
    root /code/wordpress/images;
    #压缩
    gzip on;
    .....
}
```





# 虚拟主机



**虚拟主机**

 把一台运行在互联网上的物理服务器划分成多个“虚拟”服务器 



# 跨域问题

 

跨域问题由浏览器的同源策略造成的，同源是指协议，host和port都需要相同

使用Nginx转发请求。把跨域的接口写成调本域的接口，然后将这些接口转发到真正的请求地址。 



# 高可用

 **keepalived** 







**为什么Nginx性能这么高？**

 异步非阻塞事件处理机制：运用了epoll模型，提供了一个队列，排队解决 



**Nginx是怎么处理请求的**

先根据请求的server_name和port定位到一个server，然后匹配location



**Nginx做限流**

Nginx中使用ngx_http_limit_req_module模块来限制的访问频率，限制的原理实质是基于漏桶算法原理来实现的。在nginx.conf配置文件中可以使用limit_req_zone命令及limit_req命令限制单个IP的请求处理频率。











