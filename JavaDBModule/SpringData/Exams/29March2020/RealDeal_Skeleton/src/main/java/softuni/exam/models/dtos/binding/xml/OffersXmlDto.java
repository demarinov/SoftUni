package softuni.exam.models.dtos.binding.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name="offers")
@XmlAccessorType(XmlAccessType.FIELD)
public class OffersXmlDto implements Serializable {

    @XmlElement(name="offer")
    List<OfferXmlDto> offerDtoList;

    public List<OfferXmlDto> getOfferDtoList() {
        return offerDtoList;
    }

    public void setOfferDtoList(List<OfferXmlDto> offerDtoList) {
        this.offerDtoList = offerDtoList;
    }
}
