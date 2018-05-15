package com.hiberus.training.pm.core.provider;

import com.hiberus.training.pm.core.provider.EmployeeDto;

import java.util.List;

public interface IEmployeeProvider {

    List<EmployeeDto> listEmployees();

    List<EmployeeDto> findEmployee(String query);

//    List<EmployeeDto> search(String john);
//
//    List<EmployeeDto> filterBySalary(Float min, Float max);
//
//    EmployeeDto addEmployee(EmployeeDto dto);
//
//    EmployeeDto disableEmployee(Long employeeId);
}
