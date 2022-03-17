package com.pk.springboot.juc;

import java.util.concurrent.CompletableFuture;

/**
 * wait/notify实现生产者消费知
 */
public class JuT {
    private int number = 0;

    public synchronized void add() {
        try {
            while (number > 0) {
                this.wait();
            }
            System.out.println(number++);
            this.notify();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized void dec() {
        try {
            while (number == 0) {
                this.wait();
            }
            System.out.println(number--);
            this.notify();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        JuT juT = new JuT();
        Runnable add=()->{
            for (int i = 0; i < 10; i++) {
                juT.add();
            }
        };
        Runnable dec=()->{
            for (int i = 0; i < 10; i++) {
                juT.dec();
            }
        };

        new Thread(add).start();
        new Thread(dec).start();
    }

}
