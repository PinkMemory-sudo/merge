相比synchronazed，Lock作用范围更加灵活。



**synchronazed，Lock的区别**

* synchronazed是关键字，Lock是类
* Lock作用范围更加灵活，所以需要手工释放锁，并且在发生异常时也不会释放锁，所以加锁部分的代码要用try-catch，释放锁放在finally中





注意循环或者判断条件中的Lock





















加锁相当于加了个执行条件，不仅要获得CPU执行权，还要有锁才能执行，这样保证了当有锁的线程执行完别的线程才能执行。







什么是可重入锁？synchronized可重入吗

一个线程在获得锁对象后，再次申请锁对象能否获得锁对象。不可重入时，就会产生死锁