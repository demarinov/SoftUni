package softuni.exam.models.dtos.binding.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="car")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarIdXmlDto {

    @XmlElement(name="id")
    private Long id;

    public CarIdXmlDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
