package com.hiberus.training.pm.core.provider;

import com.hiberus.training.pm.core.db.entity.Address;
import com.hiberus.training.pm.core.db.entity.Employee;
import com.hiberus.training.pm.core.provider.impl.EmployeeProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;


//@DataJpaTest(includeFilters = {EmployeeProvider.class})
@RunWith(SpringRunner.class)
@SpringBootTest
//@Profile("integration")
@ActiveProfiles(profiles = "integration")
//@SpringBootTest(classes = {EmployeeProvider.class})
//@EnableAutoConfiguration()
public class ITEmployeeProvider {

    @Autowired
    private EmployeeProvider employeeProvider;

     @Autowired
     private EntityManager manager;

//    @Mock
//    private TestEntityManager testEntityManager;
//
//    public void setUp() {
//        LocalDate date = LocalDate.of(2018,1,5);
//        Date startDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
//
//        Employee employee = new Employee("John", "Doe", 21.5f, startDate);
////        employeeRepository.save(employee);
//        testEntityManager.persist(employee);
//
//        employee = new Employee("Foo", "Bar", 23.5f, startDate);
////        employeeRepository.save(employee);
//        testEntityManager.persist(employee);
//
////        testEntityManager.persist()
//    }

    @Test
    public void testSetAddress() {
        AddressDto addressDto = new AddressDto(null, "Fake 123", "Zaragoza", "Zaragoza", "Spain", "50000");

        AddressDto result = employeeProvider.setAddress(1L, addressDto);

        assertThat(result.getId()).isNotNull();

        Address address = (Address) manager.createQuery("select a from Address a").getSingleResult();
        assertThat(address.getId()).isNotNull().isNotNegative();

        Employee employee = (Employee) manager.createQuery("select e from Employee e where e.id = :id")
                .setParameter("id",1L)
                .getSingleResult();

        assertThat(employee.getAddress()).isEqualToComparingFieldByField(address);
    }
}
