package com.example.demo;

import com.example.demo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class UserDaoTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void count(){
        System.out.println(userMapper.findAll());
    }
}
