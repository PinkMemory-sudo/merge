package com.pk.springboot.model;

import com.pk.springboot.entity.CompanyEntity;
import com.pk.springboot.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo extends UserEntity {
    private CompanyEntity companyEntity;
}
