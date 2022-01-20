package app.entities;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

@XmlRootElement(name="address")
@XmlAccessorType(XmlAccessType.FIELD)
public class AddressXmlDto implements Serializable {

    @XmlAttribute(name="country")
    private String country;

    @XmlElement(name="city")
    private String city;

    @XmlElement(name="street")
    private String street;

    public AddressXmlDto() {
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return "AddressXmlDto{" +
                "country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                '}';
    }
}
