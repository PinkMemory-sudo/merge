package com.pk.springboot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pk.springboot.entity.UserEntity;
import com.pk.springboot.mapper.UserMapper;
import com.pk.springboot.model.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@SpringBootTest
public class UserMapperTest {

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private UserMapper userMapper;

    @Test
    public void count() {
        System.out.println(userMapper.count());
    }

    @Test
    public void add() {
        UserEntity tom = UserEntity.builder().name("TOM").age(18).build();
        userMapper.add(tom);
    }

    @Test
    public void userInfo() throws JsonProcessingException {
        UserInfo userInfo = userMapper.getUserInfoById(2);
        System.out.println(mapper.writeValueAsString(userInfo));
    }

    @Test
    public void findAll() throws JsonProcessingException {
        PageHelper.startPage(0,1);
        List<UserEntity> all = userMapper.getAll();
        PageInfo<UserEntity> info = new PageInfo<>(all);
        System.out.println(mapper.writeValueAsString(info));
    }
}
