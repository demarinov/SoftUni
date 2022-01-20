package entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="customer")
public class Customer {


    // •	Customer
    //o	int id
    //o	String name
    //o	String email
    //o	String creditCardNumber
    //o	Set<Sale> sales

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="email")
    private String email;

    @Column(name="credit_card_number")
    private String creditCardNumber;

    @OneToMany(mappedBy = "customer", targetEntity = Sale.class,
        fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Sale> sales;


    public Customer() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public Set<Sale> getSales() {
        return sales;
    }

    public void setSales(Set<Sale> sales) {
        this.sales = sales;
    }
}
