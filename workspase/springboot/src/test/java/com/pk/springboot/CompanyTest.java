package com.pk.springboot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pk.springboot.entity.CompanyEntity;
import com.pk.springboot.mapper.CompanyMapper;
import com.pk.springboot.model.CompanyEmp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class CompanyTest {

    @Resource
    private CompanyMapper companyMapper;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void add() {
        CompanyEntity companyEntity = CompanyEntity.builder().name("A").build();
        System.out.println(companyMapper.add(companyEntity));
    }

    @Test
    public void findEmp() throws JsonProcessingException {
        CompanyEmp emp = companyMapper.findEmp(1);
        System.out.println(mapper.writeValueAsString(emp));
    }
}
