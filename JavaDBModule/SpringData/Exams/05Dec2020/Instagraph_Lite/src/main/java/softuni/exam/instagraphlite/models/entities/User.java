package softuni.exam.instagraphlite.models.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity{

    // •	id – integer number, primary identification field, auto increment.
    //•	username – a char sequence. Cannot be null. The username is unique.
    @Column(name="username", unique = true)
    @Size(min=2, max = 18)
    @NotNull
    private String userName;
    // Must be between 2 and 18 (both numbers are INCLUSIVE)
    //•	password – a char sequence. Cannot be null. Must be at least 4 characters long, inclusive.
    @Column(name="password", nullable = false)
    @Size(min=4)
    private String password;
    //•	profilePicture – a Picture. Cannot be null.
    @ManyToOne
    @NotNull
    @JoinColumn(name="picture_id", referencedColumnName = "id")
    private Picture picture;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Post> posts;

    public User() {
    }

    public User(@Size(min = 2, max = 18) String userName, @Size(min = 4) String password, @NotNull Picture profilePicture) {
        this.userName = userName;
        this.password = password;
        this.picture = profilePicture;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setProfilePicture(Picture profilePicture) {
        this.picture = profilePicture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }
}
