package app.entites.dtos;

import app.entites.Product;
import app.entites.validators.NameLimit;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.Set;

public class UserDto implements Serializable {

    private String firstName;

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

    @Length(min = 3)
    private String lastName;

    @NameLimit(nullable = true)
    private int age;

    private Set<Product> products;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", products=" + products +
                '}';
    }
}
