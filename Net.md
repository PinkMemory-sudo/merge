**三次握手**

主要是为了确定双方的发送和接受都没有问题。





**https ssl讲一下，讲一下证书签发过程** 



**TCP怎么保证可靠传输的**



**五层模型中都有哪些协议**

物理层：

数据链路层

网络层

传输层：TCP，UDP

应用层：DNS，HTTP



**浏览器输入Url到显示的过程**

1. DNS域名解析获得IP，(浏览器缓存，路由器缓存，DNS缓存)
2. 与服务器建立TCP连接
3. 使用HTTP访问
4. 服务器处理HTTP请求
5. 返回HTTP相应
6. 解析渲染





**HTTP状态码**

| 状态码 | 描述                 |
| ------ | -------------------- |
| 1xx    | 接受的请求正在处理   |
| 2xx    | 成功                 |
| 3xx    |                      |
| 4xx    | 服务器无法处理的请求 |
| 5xx    | 服务器内部处理错误   |



**常见错误码**



400： Bad Request 请求出现错误，比如请求头不对等

401： Unauthorized 无权限

404：请求的内容不存在。

405： Method Not Allowed 请求方式错误

408：请求超时

500：服务器错误

502：网关错误



**HTTP长连接**

在HTTP/1.0中默认使⽤短连接。也就是说，客户端和服务器每进⾏⼀次HTTP操作，就建⽴⼀次
连接，任务结束就中断连接。

⽽从HTTP/1.1起，默认使⽤⻓连接，会在响应头添加`Connection:keep-alive`当⼀个⽹⻚打开完成后，客户端和服务器之间⽤于传输HTTP数据的
TCP连接不会关闭，客户端再次访问这个服务器时，会继续使⽤这⼀条已经建⽴的连接。KeepAlive不会永久保持连接，它有⼀个保持时间，可以在不同的服务器软件（如Apache）中设定这个时间。实现⻓连接需要客户端和服务端都⽀持⻓连接。



**无状态协议**

不保存用户的状态，不记录之前的连接。

当客户端第一次像服务端发送http请求时，服务端向客户端返回一个cookie，当客户端再次发送http请求时携带该cookie，于是服务端便知道该客户端是一个老用户了 



**Cookie和Session的区别**

Cookie 存储在客户端中，⽽Session存储在服务器上，相对来说 Session 安全性更⾼。如果要在
Cookie 中存储⼀些敏感信息，不要直接写⼊ Cookie 中，最好能将 Cookie 信息加密然后使⽤到
的时候再去服务器端解密。



**为什么Cookie ⽆法防⽌CSRF攻击，⽽token可以** 

 跨站请求伪造，就是随便点击网页连接，上当受骗。进⾏Session 认证的时候，我们⼀般使⽤ Cookie 来存储 SessionId,当我们登陆后后端⽣成⼀个SessionId放在Cookie中返回给客户端，服务端通过Redis或者其他存储⼯具记录保存着这个Sessionid，客户端登录以后每次请求都会带上这个SessionId，服务端通过这个SessionId来标示你这个⼈。如果别⼈通过 cookie拿到了 SessionId 后就可以代替你的身份访问系统了。

但是，我们使⽤ token 的话就不会存在这个问题，在我们登录成功获得 token 之后，⼀般会选择
存放在 local storage 中。然后我们在前端通过某些⽅式会给每个发到后端的请求加上这个 token,
这样就不会出现 CSRF 漏洞的问题。



**HTTP与HTTPS的区别**

* HTTP默认端口是80，HTTPS默认端口是443
* HTTP所有传输的内容都是明⽂，HTTPS所有传输的内容都经过加密，加密采⽤对称加密，但对称加密的密钥⽤服务器⽅的证书进⾏了⾮对称加密 