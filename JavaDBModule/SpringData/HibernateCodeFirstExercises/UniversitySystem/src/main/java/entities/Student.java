package entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@DiscriminatorValue(value = "ST")
public class Student extends Person{

    private static final String type = "ST";

    private double averageGrade;

    private int attendance;


    public Student() {
        super(type);
    }

    public Student(double averageGrade) {
        super(type);
        this.averageGrade = averageGrade;
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(double averageGrade) {
        this.averageGrade = averageGrade;
    }

    public int getAttendance() {
        return attendance;
    }

    public void setAttendance(int attendance) {
        this.attendance = attendance;
    }
}
