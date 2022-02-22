package com.pk.springboot.mapper;

import com.pk.springboot.entity.UserEntity;
import com.pk.springboot.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface UserMapper {
    int count();

    List<UserEntity> getAll();

    int add(UserEntity userEntity);

    UserInfo getUserInfoById(int id);

    UserInfo getUserInfoById2(int id);
}
