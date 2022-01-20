package softuni.exam.instagraphlite.models.dtos.json;

import com.google.gson.annotations.Expose;
import softuni.exam.instagraphlite.models.entities.Picture;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class UserJsonDto implements Serializable {

    @Expose
    @Size(min=2,max = 18)
    @NotNull
    private String username;
    // Must be between 2 and 18 (both numbers are INCLUSIVE)
    //•	password – a char sequence. Cannot be null. Must be at least 4 characters long, inclusive.
    @Expose
    @Size(min=4)
    @NotNull
    private String password;
    //•	profilePicture – a Picture. Cannot be null.
    @Expose
    @NotNull
    private String profilePicture;

    public UserJsonDto() {
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
}
