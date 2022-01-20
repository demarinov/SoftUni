package app.entites;

import app.entites.validators.NameLimit;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="categories")
public class Category extends BaseEntity{

    @Column(name = "name")
    @Length(min = 3, max = 15)
    private String name;

    @ManyToMany(mappedBy = "categories")
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
}
