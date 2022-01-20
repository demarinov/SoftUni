package entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="store_location")
public class StoreLocation {

    // •	StoreLocation
    //o	int id
    //o	String locationName
    //o	Set<Sale> sales

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="location_name")
    private String locationName;

    @OneToMany(mappedBy = "storeLocation", targetEntity = Sale.class,
        fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Sale> sales;

    public StoreLocation() {
        this.sales = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Set<Sale> getSales() {
        return sales;
    }

    public void setSales(Set<Sale> sales) {
        this.sales = sales;
    }
}
