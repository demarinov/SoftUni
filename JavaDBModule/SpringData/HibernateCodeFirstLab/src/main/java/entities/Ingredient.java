package entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface Ingredient extends Serializable {

    String getName();

    void setName(String name);

    int getId();

    void setId(int id);

    BigDecimal getPrice();

    void setPrice(BigDecimal price);

    Set<BasicShampoo> getShampoos();

    void setShampoos(Set<BasicShampoo> shampoos);

}
