package com.dido.more;

import java.util.*;

public class CompanyRoaster {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        if (n <= 0) {
            return;
        }

        List<Department> departments = new ArrayList<>();

        for (int i = 0; i < n; i++) {

            String[] empData = sc.nextLine().split("\\s+");

            String name = empData[0];
            Double salary = Double.parseDouble(empData[1]);
            String position = empData[2];
            String departmentName = empData[3];

            Employee employee = new Employee(name, salary, position, departmentName);

            String email;
            Integer age;
            if (empData.length == 5) {
                if (empData[4].contains("@")) {
                    email = empData[4];
                    employee.setEmail(email);
                } else {
                    age = Integer.parseInt(empData[4]);
                    employee.setAge(age);
                }

            } else if (empData.length == 6) {
                if (empData[4].contains("@")) {
                    email = empData[4];
                    employee.setEmail(email);
                    age = Integer.parseInt(empData[5]);
                    employee.setAge(age);
                } else {
                    age = Integer.parseInt(empData[4]);
                    employee.setAge(age);
                    email = empData[5];
                    employee.setEmail(email);
                }
            }

            Department department = findDepartment(departments, departmentName);

            if (department == null) {
                List<Employee> empList = new ArrayList<Employee>();
                department = new Department(empList, departmentName);
                empList.add(employee);
                departments.add(department);
            } else {
                department.addEmployee(employee);

            }

            Double totalSalaryPerDepartment = department.getTotalSalary() + employee.getSalary();
            department.setTotalSalary(totalSalaryPerDepartment);


        }

        departments.sort(Comparator.comparingDouble(Department::calculateAvgSalary).reversed());
        Department maxSalaryDepartment = departments.get(0);
        // find department with max av salary
//        for (int i = 1; i < departments.size(); i++) {
//
//            Department dep = departments.get(i);
//            double maxAvSalary = 1.0 * maxSalaryDepartment.getTotalSalary()
//                    / maxSalaryDepartment.getEmployees().size();
//            double empAvSalary = 1.0 * dep.getTotalSalary() / dep.getEmployees().size();
//
//            if (maxAvSalary < empAvSalary) {
//                maxSalaryDepartment = dep;
//            }
//
//        }

        // get employees with max av salary
        List<Employee> maxSalaryEmp = maxSalaryDepartment.getEmployees();
        Collections.sort(maxSalaryEmp, (o1,o2) -> o1.getSalary().compareTo(o2.getSalary()));
        Collections.reverse(maxSalaryEmp);

        System.out.println("Highest Average Salary: "+maxSalaryDepartment.getName());
        for (int i = 0; i < maxSalaryEmp.size(); i++) {

            System.out.println(maxSalaryEmp.get(i).toString());
        }


    }

    public static Department findDepartment(List<Department> departments, String department) {

        return departments.stream().filter(d -> d.getName().equals(department))
                .findFirst().orElse(null);
    }
}

class Department {

    private List<Employee> employees;
    private String name;
    private Double totalSalary;
    private Double averageSalary;

    public Department(List<Employee> employees, String name) {
        this.name = name;
        this.employees = employees;
        totalSalary = 0.0d;
        averageSalary = 0.0d;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public void addEmployee(Employee emp) {
        employees.add(emp);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(Double totalSalary) {
        this.totalSalary = totalSalary;
    }

    public Double calculateAvgSalary() {
        averageSalary = employees.stream().mapToDouble(e -> e.getSalary()).average().getAsDouble();
        return averageSalary;
    }

    public Double getAverageSalary() {

        return averageSalary;
    }

    public void setAverageSalary(Double averageSalary) {
        this.averageSalary = averageSalary;
    }
}

class Employee {

    private String name;
    private Double salary;
    private String position;
    private String department;

    private String email;
    private Integer age;

    public Employee(String name, Double salary, String position, String department) {
        this.name = name;
        this.salary = salary;
        this.position = position;
        this.department = department;
        this.email = "n/a";
        this.age = -1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return name + " "+String.format("%.2f",salary) + " "+email+" "+age;
    }
}
