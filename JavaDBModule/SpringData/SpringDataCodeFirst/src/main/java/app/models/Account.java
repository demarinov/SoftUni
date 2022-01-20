package app.models;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="account")
public class Account {

    // -	Id – long value, primary key
    //-	Balance – BigDecimal, cannot be negative
    //-	User – an account can be owned by a single user

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="balance")
    private BigDecimal balance;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private User user;

    public Account() {
    }

    public long getId() {
        return id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
