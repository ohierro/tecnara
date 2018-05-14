package com.hiberus.training.pm.core.db;

import com.hiberus.training.pm.core.db.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query ("select e from Employee e where e.firstName = :query or e.lastName = :query")
    List<Employee> findByFullName(String query);
}
