package com.pk.mybatis;

import com.pk.mybatis.mapper.EmployeeMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class UpdateTest {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Test
    public void updateDpIdByName() {
        int result = employeeMapper.updateDpIdByName("TOM", 3);
        log.info("updateL: {}", result);
    }
}
