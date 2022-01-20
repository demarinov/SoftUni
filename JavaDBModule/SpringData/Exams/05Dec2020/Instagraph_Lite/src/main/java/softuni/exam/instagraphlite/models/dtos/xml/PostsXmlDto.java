package softuni.exam.instagraphlite.models.dtos.xml;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name="posts")
@XmlAccessorType(XmlAccessType.FIELD)
public class PostsXmlDto implements Serializable {

    @XmlElement(name="post")
    private List<PostXmlDto> postXmlDtos;

    public PostsXmlDto() {
    }

    public List<PostXmlDto> getPostXmlDtos() {
        return postXmlDtos;
    }

    public void setPostXmlDtos(List<PostXmlDto> postXmlDto) {
        this.postXmlDtos = postXmlDto;
    }
}
