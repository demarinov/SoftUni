package softuni.exam.models.entities;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="cars",
        uniqueConstraints = { @UniqueConstraint(columnNames = { "make", "model", "kilometers"})})
public class Car extends BaseEntity {

    //•	make – a char sequence (between 2 to 20 exclusive).
    @Column(name="make")
    @Length(min = 2, max=20)
    private String make;
    //•	model – a char sequence (between 2 to 20 exclusive).
    @Column(name="model")
    @Length(min=2, max=20)
    private String model;
    //•	kilometers – a number (must be positive).
    @Column(name="kilometers")
    @Min(value=0l, message="Value must be positive.")
    private Double kilometers;
    //•	registeredOn – a date.
    @Column(name="registered_on")
    private Date registeredOn;
    //The combination of make, model and kilometers makes a car unique.

    @OneToMany(mappedBy = "car", fetch = FetchType.EAGER)
    private Set<Picture> pictures;

    @OneToMany(mappedBy = "car")
    private Set<Offer> offers;


    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getKilometers() {
        return kilometers;
    }

    public void setKilometers(Double kilometers) {
        this.kilometers = kilometers;
    }

    public Date getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(Date registeredOn) {
        this.registeredOn = registeredOn;
    }

    public Set<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(Set<Picture> pictures) {
        this.pictures = pictures;
    }

    public Set<Offer> getOffers() {
        return offers;
    }

    public void setOffers(Set<Offer> offers) {
        this.offers = offers;
    }
}
