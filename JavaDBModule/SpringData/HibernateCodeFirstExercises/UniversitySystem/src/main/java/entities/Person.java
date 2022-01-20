package entities;

import javax.persistence.*;
import java.lang.annotation.Inherited;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="person")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type",discriminatorType = DiscriminatorType.STRING)
public abstract class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="phone_number")
    private String phoneNumber;

    @Basic
    @Column(insertable = false, updatable = false)
    private String type;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name="person_course",
            joinColumns = @JoinColumn(name="person_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="course_id", referencedColumnName = "id")
    )
    private Set<Course> courses;

    public Person() {

        this.courses = new HashSet<>();
    }

    public Person(String type) {
        this.type = type;
        this.courses = new HashSet<>();
    }

    public int getId() {
        return id;
    }

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
