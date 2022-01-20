package softuni.exam.models.dtos.binding.xml;


import softuni.exam.models.dtos.binding.xml.CarIdXmlDto;
import softuni.exam.models.dtos.binding.xml.SellerIdXmlDto;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


@XmlRootElement(name="offer")
@XmlAccessorType(XmlAccessType.FIELD)
public class OfferXmlDto implements Serializable {

    //•	price – a number (must be positive number).
    @XmlElement(name="price")
    @Min(value=0)
    private BigDecimal price;
    //•	description – a very long text with minimum length 5.
    @XmlElement(name="description")
    @Size(min=5)
    private String description;
    //•	hasGoldStatus –  can be true or false.
    @XmlElement(name="has-gold-status")
    private boolean hasGoldStatus;
    //•	addedOn – date and time of adding the offer.
    @XmlElement(name="added-on")
    private String addedOn;
    //The combination of description and addedOn makes an offer unique.

    @XmlElement(name="car")
    private CarIdXmlDto carXmlDto;

    @XmlElement(name="seller")
    private SellerIdXmlDto sellerXmlDto;

    public CarIdXmlDto getCarXmlDto() {
        return carXmlDto;
    }

    public void setCarXmlDto(CarIdXmlDto carXmlDto) {
        this.carXmlDto = carXmlDto;
    }

    public SellerIdXmlDto getSellerXmlDto() {
        return sellerXmlDto;
    }

    public void setSellerXmlDto(SellerIdXmlDto sellerXmlDto) {
        this.sellerXmlDto = sellerXmlDto;
    }

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

    public String getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(String addedOn) {
        this.addedOn = addedOn;
    }
}
