package entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@DiscriminatorValue(value="TE")
public class Teacher extends Person{

    private static final String type = "TE";

    private String email;

    private double salaryPerHour;

    @OneToMany(mappedBy = "teacher", targetEntity = Course.class,
        fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Course> teacherCourses;

    public Teacher() {
        super(type);
    }

    public Teacher(double salaryPerHour) {
        super(type);
        this.salaryPerHour = salaryPerHour;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getSalaryPerHour() {
        return salaryPerHour;
    }

    public void setSalaryPerHour(double salaryPerHour) {
        this.salaryPerHour = salaryPerHour;
    }
}
