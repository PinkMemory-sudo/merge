package com.pk.mybatis;

import com.pk.mybatis.mapper.EmployeeMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class DeleteTest {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Test
    public void deleteByName(){
        int result = employeeMapper.deleteByName("Jerry");
        log.info("删除成功！受影响行数：{}",result);
    }
}
