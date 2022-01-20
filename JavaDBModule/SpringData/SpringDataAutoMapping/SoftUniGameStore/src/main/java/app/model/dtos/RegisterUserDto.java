package app.model.dtos;

import app.model.validators.confirm_password.ConfirmPassword;
import app.model.validators.email.Email;
import app.model.validators.password.Password;

import java.io.Serializable;

@ConfirmPassword
public class RegisterUserDto implements Serializable {

    // •	RegisterUser|<email>|<password>|<confirmPassword>|<fullName>

    @Email
    private String email;

    @Password
    private String password;

    @Password
    private String confirmPassword;

    private String fullName;

    public RegisterUserDto(String email, String password, String confirmPassword, String fullName) {
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
