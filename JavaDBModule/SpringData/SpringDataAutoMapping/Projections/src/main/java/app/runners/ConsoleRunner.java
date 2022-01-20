package app.runners;

import app.entities.Employee;
import app.entities.EmployeeDto;
import app.services.EmployeeService;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@Component
public class ConsoleRunner implements CommandLineRunner {

    private EmployeeService employeeService;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public ConsoleRunner(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public void run(String... args) throws Exception {
        seedEmployees();

        ModelMapper modelMapper = new ModelMapper();

        PropertyMap<Employee, EmployeeDto> employeePropertyMap =
                new PropertyMap<Employee, EmployeeDto>() {
            @Override
            protected void configure() {

                map().setManagerLastName(source.getManager().getLastName());

                map().setFirstName(source.getFirstName());
                map().setLastName(source.getLastName());
                map().setSalary(source.getSalary());
            }
        };

        TypeMap<Employee, EmployeeDto> employeeDtoTypeMap =
                modelMapper.addMappings(employeePropertyMap);


        List<EmployeeDto> employeeDtoList = new ArrayList<>();
        List<Employee> employees = employeeService.findEmployeesByBirthdayBefore(1990);


        for (Employee emp : employees) {
            EmployeeDto employeeDto = new EmployeeDto();
            employeeDtoTypeMap.map(emp, employeeDto);
            employeeDtoList.add(employeeDto);
        }

        employeeDtoList.forEach(e -> {
                String manager = e.getManagerLastName();
                if (manager == null || manager.isEmpty()) {
                    manager = "[no manager]";
                }
                System.out.printf("%s %s %.2f - Manager: %s%n",
                e.getFirstName(), e.getLastName(), e.getSalary(), manager);
        });
    }

    private void seedEmployees() throws ParseException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Employee employeeM = new Employee();
        employeeM.setFirstName("Berth");
        employeeM.setLastName("York");
        employeeM.setSalary(5000d);
        employeeM.setBirthday(simpleDateFormat.parse("1989-12-03"));
        employeeM.setManager(null);

        Employee employeeA = new Employee();
        employeeA.setFirstName("Roni");
        employeeA.setLastName("Foward");
        employeeA.setSalary(3000d);
        employeeA.setOnHoliday(true);
        employeeA.setManager(employeeM);
        employeeA.setBirthday(simpleDateFormat.parse("1991-12-06"));

        Employee employeeB = new Employee();
        employeeB.setFirstName("Lory");
        employeeB.setLastName("Matthews");
        employeeB.setSalary(3000d);
        employeeB.setManager(employeeM);
        employeeB.setBirthday(simpleDateFormat.parse("1980-12-07"));

        employeeM.setManagedEmployees(new ArrayList(){{add(employeeA);add(employeeB);}});

        employeeService.save(employeeM);
    }
}
