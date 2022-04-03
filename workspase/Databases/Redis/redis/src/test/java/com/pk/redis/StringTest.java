package com.pk.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
public class StringTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void get() {

//        stringRedisTemplate.opsForValue().set("JERRY","99");
        String tom = stringRedisTemplate.opsForValue().get("JERRY");
        System.out.println(tom);
    }
}
