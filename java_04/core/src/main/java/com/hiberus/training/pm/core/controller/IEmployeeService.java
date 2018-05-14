package com.hiberus.training.pm.core.controller;

import java.util.List;

public interface IEmployeeService {

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
