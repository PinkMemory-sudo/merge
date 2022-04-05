package com.pk.mybatis;

import com.pk.mybatis.entity.Employee;
import com.pk.mybatis.mapper.EmployeeMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class SeleteTest {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Test
    public void findById() {
        Employee employee = employeeMapper.findById(1);
        log.info("find:{}", employee);
    }

    @Test
    public void findByDpId() {
        List<Employee> employees = employeeMapper.findEmployeesByDpId(3);
        log.info("find:{}", employees);
    }

    @Test
    public void countByDpId() {
        int count = employeeMapper.countByDpId(1);
        log.info("count:{}", count);
    }

    @Test
    public void findByPrefixName(){
//        Employee employee = employeeMapper.findByPrefixName("To");
        System.out.println(employeeMapper.findTen());
//        log.info("find:{}",employee);
    }
}
