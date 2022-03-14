package com.pk.springboot.entity;

public class TestC extends Test{


    static {
        System.out.println("子类静态代码块");
    }
    public TestC(){
        System.out.println("子类构造函数");
    }
    {
        System.out.println("子类构造代码块");
    }

    public static void main(String[] args) {
        TestC testC = new TestC();
    }
}
