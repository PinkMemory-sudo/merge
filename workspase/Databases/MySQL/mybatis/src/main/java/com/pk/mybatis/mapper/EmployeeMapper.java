package com.pk.mybatis.mapper;

import com.pk.mybatis.entity.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {

    int addEmployee(Employee employee);

    int deleteByName(String name);

    int updateDpIdByName(@Param("name") String name, @Param("dpId") int dpId);

    Employee findById(int id);

    List<Employee> findEmployeesByDpId(int dpId);

    int countByDpId(int dpId);

    Employee findByPrefixName(String prefixName);

    List<String> findTen();
}
