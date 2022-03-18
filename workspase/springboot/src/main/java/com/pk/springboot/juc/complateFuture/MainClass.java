package com.pk.springboot.juc.complateFuture;

import java.util.concurrent.*;

public class MainClass {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(4);
        Runnable task=()->{
        for (int i = 0; i < 10; i++) {
            System.out.println("123");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
        }};
//        new Thread(task).start();
//        countDownLatch.await(10, TimeUnit.SECONDS);
//        System.out.println("321");
//
//        CyclicBarrier cyclicBarrier = new CyclicBarrier(4);
//        task=()->{
//            System.out.println("+++");
//            try {
//                cyclicBarrier.await();
//            } catch (InterruptedException | BrokenBarrierException e) {
//                e.printStackTrace();
//            }
//            System.out.println("---");
//        };
//        for (int i = 0; i < 4; i++) {
//            new Thread(task).start();
//        }

        Semaphore semaphore = new Semaphore(2);
        semaphore.acquire();
        System.out.println("hello");
        semaphore.release();


    }
}
