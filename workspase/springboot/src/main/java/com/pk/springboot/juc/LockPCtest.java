package com.pk.springboot.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock实现生产者消费者
 */
public class LockPCtest {
    private int number;

    //声明锁
    private Lock lock = new ReentrantLock();

    //声明钥匙
    private Condition condition = lock.newCondition();

    /**
     * 加
     */
    public void add(){
        try{
            lock.lock();
            while (number>0){
                condition.await();
            }
            number++;
            System.out.println(number);
            condition.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    /**
     * 减
     */
    public void dec(){
        try{
            lock.lock();
            while (number==0){
                condition.await();
            }
            number--;
            System.out.println(number);
            condition.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {

        LockPCtest lockPCtest = new LockPCtest();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                lockPCtest.add();
            }
        }).start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                lockPCtest.dec();
            }
        }).start();
    }
}
