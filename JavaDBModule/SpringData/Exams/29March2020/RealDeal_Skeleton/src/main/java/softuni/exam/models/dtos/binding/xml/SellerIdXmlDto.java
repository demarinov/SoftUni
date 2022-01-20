package softuni.exam.models.dtos.binding.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name="seller")
@XmlAccessorType(XmlAccessType.FIELD)
public class SellerIdXmlDto implements Serializable {

    @XmlElement(name="id")
    private Long id;

    public SellerIdXmlDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
