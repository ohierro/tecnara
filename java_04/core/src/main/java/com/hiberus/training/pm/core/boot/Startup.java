package com.hiberus.training.pm.core.boot;

import com.hiberus.training.pm.core.db.EmployeeRepository;
import com.hiberus.training.pm.core.db.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

@Component
public class Startup implements ApplicationRunner {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public Startup(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        LocalDate date = LocalDate.of(2018,1,5);
        Date startDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());

        Employee employee = new Employee("John", "Doe", 21.5f, startDate);
        employeeRepository.save(employee);

        employee = new Employee("Foo", "Bar", 23.5f, startDate);
        employeeRepository.save(employee);
    }
}
