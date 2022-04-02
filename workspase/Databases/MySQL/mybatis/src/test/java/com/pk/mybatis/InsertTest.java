package com.pk.mybatis;

import com.pk.mybatis.entity.Employee;
import com.pk.mybatis.mapper.EmployeeMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class InsertTest {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Test
    public void addEmployee() {
        Employee employee = Employee.builder().name("Jerry")
                .age(19)
                .dpId(1).build();
        int id = employeeMapper.addEmployee(employee);
        log.info("插入成功！id={},employee:{}", id,employee);
    }
}
