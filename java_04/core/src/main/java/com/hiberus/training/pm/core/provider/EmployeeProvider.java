package com.hiberus.training.pm.core.provider;

import com.hiberus.training.pm.core.controller.EmployeeDto;
import com.hiberus.training.pm.core.controller.IEmployeeService;
import com.hiberus.training.pm.core.db.EmployeeRepository;
import com.hiberus.training.pm.core.db.PhoneRepository;
import com.hiberus.training.pm.core.db.entity.Employee;
import com.hiberus.training.pm.core.db.entity.Phone;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeProvider implements IEmployeeService {
    private final EmployeeRepository employeeRepository;
    private final PhoneRepository phoneRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public EmployeeProvider(EmployeeRepository employeeRepository,
                            PhoneRepository phoneRepository,
                            ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.phoneRepository = phoneRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<EmployeeDto> listEmployees() {
        List<Employee> employees = employeeRepository.findAll();

        return employees.stream().map(employee -> convertToDto(employee)).collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDto> findEmployee(String query) {
        if (query.isEmpty()) return new ArrayList<>();

        List<Employee> employees = employeeRepository.findByFullName(query);

        return employees.stream().map(employee -> convertToDto(employee)).collect(Collectors.toList());
    }

    public EmployeeDto findByPhone(String phoneNumber) {
        return convertToDto(phoneRepository.findByPhoneNumber(phoneNumber).getOwner());
    }

    public List<PhoneDto> listPhones(Long employeeId) {
        List<Phone> phones = employeeRepository.findById(employeeId).get().getPhoneList();

        return phones.stream().map(phone -> convertToDto(phone)).collect(Collectors.toList());
    }

    public void addPhone(Long employeeId, PhoneDto phone) {
        Phone entity = convertToEntity(phone);
        Employee employee = employeeRepository.findById(employeeId).get();
        entity.setOwner(employee);
        phoneRepository.save(entity);
    }


    private EmployeeDto convertToDto(Employee employee) {
        EmployeeDto dto = new EmployeeDto();
        modelMapper.map(employee,dto);

        return dto;
    }

    private PhoneDto convertToDto(Phone phone) {
        PhoneDto dto = new PhoneDto();
        modelMapper.map(phone, dto);

        return dto;
    }

    private Phone convertToEntity(PhoneDto dto) {
        Phone entity = new Phone();
        modelMapper.map(dto, entity);

        return entity;
    }

}
