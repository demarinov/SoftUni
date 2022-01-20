package app.model.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="orders")
public class Order extends BaseEntity{

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private User buyer;

    @ManyToMany
    @JoinTable(name="orders_games",
    joinColumns = @JoinColumn(name="order_id",referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name="game_id", referencedColumnName = "id"))
    private Set<Game> products;
}
