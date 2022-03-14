package com.pk.springboot.entity;

public class Test {



    public static int a = getNum();
    static {
        System.out.println("父类静态代码块");
    }
    public Test(){
        System.out.println("父类构造函数");
    }

    {
        System.out.println("父类构造代码块");
    }
    public static void main(String[] args) {
        Test test = new Test();
    }

    public static int getNum() {
        System.out.println("父类静态");
        return 1;
    }
}
