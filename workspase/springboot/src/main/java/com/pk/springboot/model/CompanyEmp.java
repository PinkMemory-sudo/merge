package com.pk.springboot.model;

import com.pk.springboot.entity.CompanyEntity;
import com.pk.springboot.entity.UserEntity;
import lombok.Data;

import java.util.List;

@Data
public class CompanyEmp extends CompanyEntity {
    private List<UserEntity> userEntityList;
}
