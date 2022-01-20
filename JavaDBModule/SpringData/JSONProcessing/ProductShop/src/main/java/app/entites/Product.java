package app.entites;

import app.entites.validators.NameLimit;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name="products")
public class Product extends BaseEntity{

    @Column(name = "name")
    @Length(min = 3)
    private String name;

    @Column(name="price")
    private BigDecimal price;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="products_categories",
    joinColumns = @JoinColumn(name="product_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name="category_id", referencedColumnName = "id"))
    private Set<Category> categories;

    @ManyToMany(mappedBy = "products")
    private Set<User> users;

    @Column(name="buyer_id", nullable = true)
    private int buyerId;

    @Column(name="seller_id")
    private int sellerId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }
}
