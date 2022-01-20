package com.dido.exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CompanyInfo {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        List<Department> departmentList = new ArrayList<>();
        for (int i = 0; i < n; i++) {

            String[] empData = sc.nextLine().split("\\s+");

            // name, salary, position, department, email and age
            // mandatory
            String name = empData[0];
            Double salary = Double.parseDouble(empData[1]);
            String position = empData[2];
            String departmentName = empData[3];

            Employee employee = new Employee(name,salary, position, departmentName);
            Department department = findDepartment(departmentList, departmentName);
            if (department == null) {
                department = new Department(departmentName);
            }

            String email = "";
            Integer age = -1;
            if (empData.length == 6){
                email = empData[4];
                age = Integer.parseInt(empData[5]);
                employee.setEmail(email);
                employee.setAge(age);
            } else if (empData.length == 5) {
                if (empData[4].contains("@")) {
                    email = empData[4];
                    employee.setEmail(email);
                } else {
                    // prob age ...
                    age = Integer.parseInt(empData[4]);
                    employee.setAge(age);
                }
            }

            department.addEmployee(employee);

            departmentList.add(department);
        }


        // calculates the department with the highest average salary and prints
        // for each employee in that department
        // his name, salary, email and age - sorted by salary in descending order.

        Department depWithHighestAvgSalary = departmentList.stream()
                .sorted((d1, d2) ->{
                    Double avgSalary1 = d1.getEmployeeList().stream()
                            .mapToDouble(e -> e.getSalary()).average().getAsDouble();
                    Double avgSalary2 = d2.getEmployeeList().stream()
                            .mapToDouble(e -> e.getSalary()).average().getAsDouble();
                    return avgSalary2.compareTo(avgSalary1);
                })
                .findFirst().orElse(null);

        if (depWithHighestAvgSalary != null) {
            System.out.printf("Highest Average Salary: %s%n",depWithHighestAvgSalary.getName());

            depWithHighestAvgSalary.getEmployeeList().stream()
                    .sorted((e1,e2) -> e2.getSalary().compareTo(e1.getSalary()))
                    .forEach(e -> System.out.printf("%s %.2f %s %d%n"
                            ,e.getName(), e.getSalary(), e.getEmail(), e.getAge()));
        }
    }

    public static Department findDepartment(List<Department> departmentList, String depName) {

        Department department = departmentList.stream()
                .filter(d -> d.getName().equals(depName)).findFirst().orElse(null);

        return department;
    }

    static class Employee {

        //Define a class Employee that holds the following information: name, salary, position,
        // department, email and age.
        // The name, salary, position and department are mandatory while the rest are optional.
        private String name;
        private Double salary;
        private String position;
        private String department;

        private String email;
        private Integer age;

        public Employee() {

        }

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
    }

    static class Department {


        private String name;
        List<Employee> employeeList;

        public Department(String name) {
            this.name = name;
            employeeList = new ArrayList<>();
        }

        public void addEmployee(Employee emp) {
            employeeList.add(emp);
        }

        public Employee getEmployee(String name) {

            Employee employee = employeeList.stream().findFirst().orElse(null);

            return employee;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Employee> getEmployeeList() {
            return employeeList;
        }

        public void setEmployeeList(List<Employee> employeeList) {
            this.employeeList = employeeList;
        }
    }
}
