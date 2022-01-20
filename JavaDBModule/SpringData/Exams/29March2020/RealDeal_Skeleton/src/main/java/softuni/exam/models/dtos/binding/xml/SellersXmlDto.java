package softuni.exam.models.dtos.binding.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name="sellers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SellersXmlDto implements Serializable {

    @XmlElement(name="seller")
    private List<SellerXmlDto> sellerDtoList;

    public List<SellerXmlDto> getSellerDtoList() {
        return sellerDtoList;
    }

    public void setSellerDtoList(List<SellerXmlDto> sellerDtoList) {
        this.sellerDtoList = sellerDtoList;
    }
}
