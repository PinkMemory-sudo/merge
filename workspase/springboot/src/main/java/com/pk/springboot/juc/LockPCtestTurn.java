package com.pk.springboot.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 交替打印ABC
 */
public class LockPCtestTurn {

    // 规定打印顺序
    private int num=1;
    //声明锁
    private Lock lock = new ReentrantLock();

    //声明钥匙，实现交替打印
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void a() {
        try {
            lock.lock();
            while (num != 1) {
                condition1.await();
            }
            System.out.println("A");
            condition2.signal();
            num = 2;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void b() {
        try {
            lock.lock();
            while (num != 2) {
                condition2.await();
            }
            System.out.println("B");
            num = 3;
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void c() {
        try {
            lock.lock();
            while (num != 3) {
                condition3.await();
            }
            System.out.println("C");
            num = 1;
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) {

        LockPCtestTurn lockPCtest = new LockPCtestTurn();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                lockPCtest.a();
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                lockPCtest.b();
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                lockPCtest.c();
            }
        }).start();
    }
}
