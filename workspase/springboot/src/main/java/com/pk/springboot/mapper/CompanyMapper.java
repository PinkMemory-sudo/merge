package com.pk.springboot.mapper;

import com.pk.springboot.entity.CompanyEntity;
import com.pk.springboot.model.CompanyEmp;

public interface CompanyMapper {
    int add(CompanyEntity companyEntity);

    CompanyEmp findEmp(int companyId);
}
