package com.nlt.app.repository;

import com.nlt.app.model.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findAllById(Long id);

    List<Employee> getEmployeesByAgeGreaterThan(int i);
}
