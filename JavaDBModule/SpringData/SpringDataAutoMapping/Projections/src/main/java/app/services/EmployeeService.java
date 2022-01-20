package app.services;

import app.entities.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findEmployeesByBirthdayBefore(int year);

    void save(Employee employee);
}
