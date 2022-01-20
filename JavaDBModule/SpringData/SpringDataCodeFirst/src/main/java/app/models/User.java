package app.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="user")
public class User {

    // -	Id – long value, primary key
    //-	Username – unique for each user
    //-	Age – integer value
    //-	Accounts – each user can have many accounts, which will be identified by their id

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="user_name")
    private String userName;

    @Column(name="age")
    private int age;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Account> accounts;

    public User() {
    }

    public long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }
}
