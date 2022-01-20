package com.nlt.app.model.entities;

import javax.persistence.*;

@Entity
@Table(name="employees")
public class Employee extends BaseEntity{

    // •	id – integer number, primary identification field.
    //•	first name – a string (required).
    @Column(name="first_name", nullable = false)
    private String firstName;
    //•	last name – a string (required).
    @Column(name="last_name", nullable=false)
    private String lastName;
    //•	age – a Integer(required).
    @Column(name="age", nullable= false)
    private Integer age;
    //•	project – a Project entity(required).
    @ManyToOne
    @JoinColumn(name="project_id", referencedColumnName = "id")
    private Project project;

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("Name: %s %s%n",firstName, lastName));
        sb.append(String.format("\tAge: %d%n",age));
        sb.append(String.format("\tProject name: %s",project.getName()));
        sb.append(System.lineSeparator());
        return sb.toString();
    }
}
