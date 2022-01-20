package softuni.exam.models.entities;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="pictures")
public class Picture extends BaseEntity{


    // Picture
    //•	name – a char sequence (between 2 to 20 exclusive). The name of a picture is unique.
    @Column(name="name", unique = true)
    @Length(min=2, max=20)
    private String name;
    //•	dateAndTime – The date and time of a picture.
    @Column(name="date")
    private LocalDateTime dateAndTime;

    @ManyToOne
    @JoinColumn(name="car_id", referencedColumnName = "id")
    private Car car;

    @ManyToMany
    @JoinTable(name="pictures_offers",
    joinColumns = @JoinColumn(name="picture_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name="offer_id", referencedColumnName = "id"))
    private Set<Offer> offers;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDate() {
        return dateAndTime;
    }

    public void setDate(LocalDateTime date) {
        this.dateAndTime = date;
    }

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Set<Offer> getOffers() {
        return offers;
    }

    public void setOffers(Set<Offer> offers) {
        this.offers = offers;
    }
}
