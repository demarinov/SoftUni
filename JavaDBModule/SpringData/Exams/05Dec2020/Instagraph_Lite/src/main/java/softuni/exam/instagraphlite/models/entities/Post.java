package softuni.exam.instagraphlite.models.entities;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "posts")
public class Post extends BaseEntity {

    // •	id – integer number, primary identification field, auto increment.
    //•	caption – a char sequence. Cannot be null. Must be at least 21 characters, inclusive.
    @Column(name="caption")
    @Size(min=21)
    private String caption;
    //•	user – a User. Cannot be null
    @ManyToOne
    @NotNull
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private User user;
    //•	picture – a Picture. Cannot be null.
    @ManyToOne
    @JoinColumn(name="picture_id", referencedColumnName = "id")
    private Picture picture;

    public Post() {
    }

    public Post(String caption, @NotNull User user, Picture picture) {
        this.caption = caption;
        this.user = user;
        this.picture = picture;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }
}
