package app.entites;

import app.entites.validators.NameLimit;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="users")
public class User extends BaseEntity{

    @Column(name="first_name", nullable = true)
    private String firstName;

    @Column(name="last_name")
    @Length(min = 3)
    private String lastName;

    @Column(name = "age", nullable = true)
    private int age;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="users_products",
    joinColumns = @JoinColumn(name="user_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name="product_id", referencedColumnName = "id"))
    private Set<Product> products;

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
