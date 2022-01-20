package entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="product")
public class Product {

    // o	int id
    //o	String name
    //o	Double quantity
    //o	BigDecimal price
    //o	Set<Sale> sales

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="quantity")
    private Double quantity;

    @Column(name="price")
    private BigDecimal price;

    @OneToMany(mappedBy = "product", targetEntity = Sale.class,
        fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Sale> sales;

    public Product() {

        this.sales = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Set<Sale> getSales() {
        return sales;
    }

    public void setSales(Set<Sale> sales) {
        this.sales = sales;
    }
}
