package entities;

import javax.persistence.*;

@Entity
@Table(name="billing_details")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="billing_type")
public abstract class BillingDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;

    @Column(name="number")
    private int number;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="owner_id", referencedColumnName = "id")
    private User owner;

    @Column(name="billing_type", insertable = false, updatable = false)
    private String billingType;

    public BillingDetails(String billingType) {
        this.billingType = billingType;
    }

    protected BillingDetails(String billingType, int number, User owner) {
        this.number = number;
        this.owner = owner;
        this.billingType = billingType;
    }

    public int getId() {
        return id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
