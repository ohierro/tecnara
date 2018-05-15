package com.hiberus.training.pm.core.provider;

import com.hiberus.training.pm.core.db.AddressRepository;
import com.hiberus.training.pm.core.db.EmployeeRepository;
import com.hiberus.training.pm.core.db.PhoneRepository;
import com.hiberus.training.pm.core.db.entity.Address;
import com.hiberus.training.pm.core.db.entity.Employee;
import com.hiberus.training.pm.core.provider.impl.EmployeeProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TestEmployeeProvider {

    private EmployeeProvider employeeProvider;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private PhoneRepository phoneRepository;

    @Before
    public void setUp() {
        ModelMapper mapper = new ModelMapper();
        employeeProvider = new EmployeeProvider(employeeRepository, phoneRepository, addressRepository,  mapper);
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

    @Test
    public void testSetAddress() {
        // set up data
        AddressDto addressDto = new AddressDto(null, "Fake 123", "Zaragoza", "Zaragoza", "Spain", "50000");
        Address addressEntity = new Address(1L, "Fake 123", "Zaragoza", "Zaragoza", "Spain", "50000");
        Employee employeeEntity = new Employee("Test","test",21.3f,new Date());

        when(addressRepository.save(any())).thenReturn(addressEntity);
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(employeeEntity));
//        when(employeeRepository.save(any(Employee.class)));

        // call
        AddressDto result = employeeProvider.setAddress(1L, addressDto);

        // check
        verify(addressRepository).save(any(Address.class));
        verify(employeeRepository).save(employeeEntity);
    }
}
