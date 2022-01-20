package app.entites.dtos;

import app.entites.Product;
import app.entites.validators.NameLimit;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.Set;

public class CategoryDto implements Serializable {

    @Length(min= 3, max = 15)
    private String name;

    private Set<Product> products;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "CategoryDto{" +
                "name='" + name + '\'' +
                ", products=" + products +
                '}';
    }
}
