package app.entites.dtos;

import app.entites.Category;
import app.entites.User;
import app.entites.validators.NameLimit;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

public class ProductDto implements Serializable {

    @Length(min = 3)
    private String name;

    private BigDecimal price;

    private Set<Category> categories;

    private Set<User> users;

    private int buyerId;

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

    @Override
    public String toString() {
        return "ProductDto{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", categories=" + categories +
                ", users=" + users +
                ", buyerId=" + buyerId +
                ", sellerId=" + sellerId +
                '}';
    }
}
