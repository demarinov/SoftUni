package app.services.impl;

import app.entities.Employee;
import app.repositories.EmployeeRepository;
import app.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findEmployeesByBirthdayBefore(int year) {
        return employeeRepository.findEmployeesByBirthdayBefore(year);
    }

    @Override
    public void save(Employee employee) {
        employeeRepository.save(employee);
    }
}
