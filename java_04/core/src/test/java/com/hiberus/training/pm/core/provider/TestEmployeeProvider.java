package com.hiberus.training.pm.core.provider;

import com.hiberus.training.pm.core.controller.EmployeeDto;
import com.hiberus.training.pm.core.db.EmployeeRepository;
import com.hiberus.training.pm.core.db.entity.Employee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TestEmployeeProvider {

    private EmployeeProvider employeeProvider;

    @Mock
    private EmployeeRepository employeeRepository;

    @Before
    public void setUp() {
        ModelMapper mapper = new ModelMapper();
        employeeProvider = new EmployeeProvider(employeeRepository, null, mapper);
    }

    @Test
    public void testListEmployee() {
        // set up data
        List<Employee> employees = Arrays.asList(
                new Employee[]{
                        new Employee("John", "Doe", 23.5f, null),
                        new Employee("Foo", "Bar", 21.5f, null)
                }
        );

        when(employeeRepository.findAll()).thenReturn(employees);

        // call
        List<EmployeeDto> result = employeeProvider.listEmployees();

        // check
        assertEquals((Long)1L, result.get(0).getId());
        assertEquals("John", result.get(0).getFirstName());
        assertEquals("Doe", result.get(0).getLastName());
        assertEquals(23.5f, result.get(0).getSalary().floatValue(), 0);
    }

    @Test
    public void testFindEmployee() {
        // set up data
        List<Employee> employees = Arrays.asList(
                new Employee[] {
                        new Employee("John", "Doe", 23.5f, null)
                });
        when(employeeRepository.findByFullName(anyString())).thenReturn(employees);

        // call
        List<EmployeeDto> result = employeeProvider.findEmployee("John");

        // check
        assertEquals(1, result.size());
        assertEquals("John", result.get(0).getFirstName());
        verify(employeeRepository).findByFullName("John");
    }

    @Test
    public void testFindNonExistentEmployee() {
        // set up data
        when(employeeRepository.findByFullName(anyString())).thenReturn(new ArrayList<Employee>());

        // call
        List<EmployeeDto> result = employeeProvider.findEmployee("John");

        // check
        verify(employeeRepository, times(0)).findByFullName(anyString());
        assertEquals(0, result.size());
    }

    @Test
    public void testFindEmptyEmployee() {
        // set up data

        // call
        List<EmployeeDto> result = employeeProvider.findEmployee("");

        // check
        verify(employeeRepository, times(0)).findByFullName(anyString());
    }
}
