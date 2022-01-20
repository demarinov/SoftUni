package softuni.exam.instagraphlite.models.dtos.xml;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name="post")
@XmlAccessorType(XmlAccessType.FIELD)
public class PostXmlDto implements Serializable {

    @XmlElement(name="caption")
    @Size(min=21)
    @NotNull
    private String caption;
    //•	user – a User. Cannot be null
    @XmlElement(name="user")
    @NotNull
    private UserXmlDto user;
    //•	picture – a Picture. Cannot be null.
    @XmlElement(name="picture")
    @NotNull
    private PictureXmlDto picture;

    public PostXmlDto() {
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public UserXmlDto getUser() {
        return user;
    }

    public void setUser(UserXmlDto user) {
        this.user = user;
    }

    public PictureXmlDto getPicture() {
        return picture;
    }

    public void setPicture(PictureXmlDto picture) {
        this.picture = picture;
    }
}
