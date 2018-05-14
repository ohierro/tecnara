package com.hiberus.training.pm.core.controller.impl;

import com.hiberus.training.pm.core.controller.IEmployeeService;
import com.hiberus.training.pm.core.controller.EmployeeDto;
import com.hiberus.training.pm.core.db.EmployeeRepository;
import com.hiberus.training.pm.core.db.entity.Employee;
import com.hiberus.training.pm.core.provider.EmployeeProvider;
import com.hiberus.training.pm.core.provider.PhoneDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EmployeeService {
    private final EmployeeProvider employeeProvider;

    @Autowired
    public EmployeeService(EmployeeProvider employeeProvider) {
        this.employeeProvider = employeeProvider;
    }

    @RequestMapping(path = "/employees", method = RequestMethod.GET)
    public ResponseEntity<?> listEmployees() {
        List<EmployeeDto> dtos = employeeProvider.listEmployees();

        return new ResponseEntity<Object>(dtos, HttpStatus.OK);
    }

    @GetMapping(path = "/employees/{id}/phones")
    public List<PhoneDto> listPhones(@PathVariable Long id) {
        return employeeProvider.listPhones(id);
    }

    @PostMapping(path = "/employees/{id}/phones")
    public void addPhone(@PathVariable Long id, @RequestBody PhoneDto phone) {
        employeeProvider.addPhone(id, phone);
    }

    @GetMapping(path = "/employees/byPhone")
    public EmployeeDto findByPhone(@RequestParam  String phoneNumber) {
        return employeeProvider.findByPhone(phoneNumber);
    }
}