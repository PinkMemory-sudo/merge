package com.pk.springboot.juc.complateFuture;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

public class MainClass {
    public static void main(String[] args) throws InterruptedException {

        System.out.println(test(6));
        System.out.println(Math.pow(2, 3));
    }

    public static int test(int n) {
        int first = 1, second = 1, sum = 0;
        if (n <= 0) {
            return 0;
        } else if (n < 3) {
            return 1;
        } else {
            for (int i = 3; i <= n; i++) {
                sum = first + second;
                first = second;
                second = sum;
            }
            return sum;
        }
    }
}
