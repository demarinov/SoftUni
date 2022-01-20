package app.entities;


import java.util.List;

public class ManagerDto {

    private String firstName;
    private String lastName;
    private List<EmployeeDto> managedEmployees;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<EmployeeDto> getManagedEmployees() {
        return managedEmployees;
    }

    public void setManagedEmployees(List<EmployeeDto> managedEmployees) {
        this.managedEmployees = managedEmployees;
    }
}
