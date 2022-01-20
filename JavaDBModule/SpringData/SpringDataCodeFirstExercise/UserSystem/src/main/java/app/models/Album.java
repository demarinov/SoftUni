package app.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="albums")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="background_color")
    @Enumerated
    private BackgroundColor backgroundColor;

    @Column(name="is_public")
    private boolean isPublic;

    @ManyToMany
    @JoinTable(name="albums_pictures",
    joinColumns = @JoinColumn(name="album_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name="picture_id", referencedColumnName = "id"))
    private Set<Picture> pictures;

    @ManyToOne
//    @JoinColumn(name="user_id", referencedColumnName = "id")
    private User user;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BackgroundColor getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(BackgroundColor backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public Set<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(Set<Picture> pictures) {
        this.pictures = pictures;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
