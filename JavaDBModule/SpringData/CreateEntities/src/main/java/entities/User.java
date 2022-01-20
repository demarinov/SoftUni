package entities;

import orm.Column;
import orm.Entity;
import orm.Id;

import java.util.Date;

@Entity(name="users")
public class User {

    @Id
    private int id;
    @Column(name="username")
    private String userName;
    @Column(name="password")
    private String password;
    @Column(name="age")
    private int age;
    @Column(name="registration_date")
    private Date registrationDate;

    public User(){

    }

    public User(String userName, String password, int age, Date registrationDate) {
        this.userName = userName;
        this.password = password;
        this.age = age;
        this.registrationDate = registrationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", registrationDate=" + registrationDate +
                '}';
    }
}
