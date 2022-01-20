package entities;


import com.sun.source.tree.UsesTree;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="shampoos")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="shampoo_type", discriminatorType = DiscriminatorType.STRING)
public class BasicShampoo implements Shampoo {

    @Id
    private long id;

    @Basic
    private BigDecimal price;

    @Basic
    private String brand;

    @Enumerated
    private Size size;


    @OneToOne(optional = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "label", referencedColumnName = "id")
    private BasicLabel label;


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name="shampoo_ingredients"
            ,joinColumns = @JoinColumn(name="shampoo_id", referencedColumnName = "id")
            ,inverseJoinColumns = @JoinColumn(name="ingredient_id", referencedColumnName = "id"))
    private Set<BasicIngredient> ingredients;

    public BasicShampoo() {
    }

    public BasicShampoo(String brand,BigDecimal price ,Size size, BasicLabel basicLabel) {
        this.price = price;
        this.brand = brand;
        this.size = size;
        this.label = basicLabel;
        this.ingredients = new HashSet<>();
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(int id) {

    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String getBrand() {
        return brand;
    }

    @Override
    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public Size getSize() {
        return size;
    }

    @Override
    public void setSize(Size size) {
        this.size = size;
    }

    @Override
    public BasicLabel getLabel() {
        return label;
    }

    @Override
    public void setLabel(BasicLabel label) {
        this.label = label;
    }


    @Override
    public Set<BasicIngredient> getIngredients() {
        return ingredients;
    }

    @Override
    public void setIngredients(Set<BasicIngredient> ingredients) {
        this.ingredients = ingredients;
    }
}
