package app;

import app.entities.Employee;
import app.entities.EmployeeDto;
import app.entities.ManagerDto;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Date;

public class AdvancedMappingDemo {

    public static void main(String[] args) {
        Employee employeeM = new Employee();
        employeeM.setFirstName("Berth");
        employeeM.setLastName("York");
        employeeM.setSalary(2000d);
        employeeM.setBirthday(new Date());
        employeeM.setManager(null);

        Employee employeeA = new Employee();
        employeeA.setFirstName("Roni");
        employeeA.setLastName("Foward");
        employeeA.setSalary(3000d);
        employeeA.setOnHoliday(true);
        employeeA.setManager(employeeM);
        employeeA.setBirthday(new Date());

        Employee employeeB = new Employee();
        employeeB.setFirstName("Lory");
        employeeB.setLastName("Matthews");
        employeeB.setSalary(3000d);
        employeeB.setManager(employeeM);

        employeeM.setManagedEmployees(new ArrayList(){{add(employeeA);add(employeeB);}});

        ModelMapper modelMapper = new ModelMapper();

        ManagerDto managerDto = modelMapper.map(employeeM, ManagerDto.class);

        System.out.printf("%s %s | Employees: %d%n", managerDto.getFirstName(),
                managerDto.getLastName(), managerDto.getManagedEmployees().size());
        for (EmployeeDto emp : managerDto.getManagedEmployees()) {

            System.out.printf("    - %s %s %.2f%n",emp.getFirstName(),
                    emp.getLastName(), emp.getSalary());
        }
    }
}
