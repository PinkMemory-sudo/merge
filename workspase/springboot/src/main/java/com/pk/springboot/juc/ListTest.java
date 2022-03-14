package com.pk.springboot.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ListTest {

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
//        List<String> list = Collections.synchronizedList(new ArrayList<String>());
        for (int i = 0; i < 100; i++) {
            new Thread(() ->{ list.add(UUID.randomUUID().toString());
                System.out.println(list); }, "线程" + i).start();
        }
    }
}
