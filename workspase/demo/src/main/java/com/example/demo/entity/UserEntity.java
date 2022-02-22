package com.example.demo.entity;

import java.io.Serializable;

public class UserEntity implements Serializable {
    private String name;

    public UserEntity() {
    }

    public UserEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "name='" + name + '\'' +
                '}';
    }
}
