package app;

import app.entities.Employee;
import app.entities.EmployeeDto;
import org.modelmapper.ModelMapper;

import java.util.Date;

public class SimpleMappingDemo {

    public static void main(String[] args) {
        Employee employee = new Employee();
        employee.setFirstName("Bono");
        employee.setLastName("Ron");
        employee.setBirthDate(new Date());
        employee.setSalary(2000d);
        employee.setAddress("Brick town, 1900 street");

        ModelMapper modelMapper = new ModelMapper();
        EmployeeDto employeeDto = modelMapper.map(employee,EmployeeDto.class);

        System.out.println(employeeDto.getFirstName() + " "+employeeDto.getLastName());
    }
}
