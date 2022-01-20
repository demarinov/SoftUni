package softuni.exam.models.entities;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Set;

@Entity
@Table(name="sellers")
public class Seller extends BaseEntity{

    //•	firstName – a char sequence (between 2 to 20 exclusive).
    @Column(name="first_name")
    @Length(min=2, max=20)
    private String firstName;
    //•	lastName – a char sequence (between 2 to 20 exclusive).
    @Column(name="last_name")
    @Length(min=2, max=20)
    private String lastName;
    //•	email – an email – (must contains ‘@’ and ‘.’ – dot). The email of a seller is unique.
    @Email
    @Column(name="email", unique = true)
    private String email;
    //•	rating – enumerated value must be one of these GOOD, BAD or UNKNOWN. Cannot be null.
    @Column(name="rating", nullable = false)
    @Enumerated
    private RatingEnum rating;
    //•	town – a char sequence – the name of a town. Cannot be null.
    @Column(nullable = false)
    private String town;

    @OneToMany(mappedBy = "seller")
    private Set<Offer> offers;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RatingEnum getRating() {
        return rating;
    }

    public void setRating(RatingEnum rating) {
        this.rating = rating;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }
}
