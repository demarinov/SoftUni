package softuni.exam.models.dtos.binding.xml;

import softuni.exam.models.entities.RatingEnum;

import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name="seller")
@XmlAccessorType(XmlAccessType.FIELD)
public class SellerXmlDto implements Serializable {

    //•	firstName – a char sequence (between 2 to 20 exclusive).
    @XmlElement(name="first-name")
    @Size(min=2, max=20)
    private String firstName;
    //•	lastName – a char sequence (between 2 to 20 exclusive).
    @XmlElement(name="last-name")
    @Size(min=2, max=20)
    private String lastName;
    //•	email – an email – (must contains ‘@’ and ‘.’ – dot). The email of a seller is unique.
    @Email
    @XmlElement(name="email")
    private String email;
    //•	rating – enumerated value must be one of these GOOD, BAD or UNKNOWN. Cannot be null.
    @Enumerated
    @XmlElement(name="rating")
    @NotNull
    private RatingEnum rating;

    @XmlElement(name="town")
    @NotBlank
    private String town;

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
