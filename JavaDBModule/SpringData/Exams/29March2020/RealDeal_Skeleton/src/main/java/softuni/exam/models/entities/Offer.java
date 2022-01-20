package softuni.exam.models.entities;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="offers",
uniqueConstraints = {@UniqueConstraint(columnNames = {"description","added_on"})})
public class Offer extends BaseEntity{

    //•	price – a number (must be positive number).
    @Min(value=0)
    @Column(name="price")
    private BigDecimal price;
    //•	description – a very long text with minimum length 5.
    @Column(name="description")
    @Length(min=5)
    private String description;
    //•	hasGoldStatus –  can be true or false.
    @Column(name="has_gold_status")
    private boolean hasGoldStatus;
    //•	addedOn – date and time of adding the offer.
    @Column(name="added_on")
    private LocalDateTime addedOn;
    //The combination of description and addedOn makes an offer unique.
    @ManyToOne
    @JoinColumn(name="car_id", referencedColumnName = "id")
    private Car car;

    @ManyToOne
    @JoinColumn(name="seller_id", referencedColumnName = "id")
    private Seller seller;


    @ManyToMany(mappedBy = "offers")
    private Set<Picture> pictures;


    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isHasGoldStatus() {
        return hasGoldStatus;
    }

    public void setHasGoldStatus(boolean hasGoldStatus) {
        this.hasGoldStatus = hasGoldStatus;
    }

    public LocalDateTime getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(LocalDateTime addedOn) {
        this.addedOn = addedOn;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Set<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(Set<Picture> pictures) {
        this.pictures = pictures;
    }
}
