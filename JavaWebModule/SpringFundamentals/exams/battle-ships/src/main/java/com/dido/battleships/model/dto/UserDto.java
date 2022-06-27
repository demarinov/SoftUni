package com.dido.battleships.model.dto;

import com.dido.battleships.model.validation.FieldMatch;
import com.dido.battleships.model.validation.UniqueUserName;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@Builder
@UniqueUserName(
        field="username",
        secondaryField = "uniqueUserName",
        message = "Username already occupied."
)
public class UserDto implements Serializable {

    @NotEmpty
    @Size(min = 3, max=10)
    private String username;

    @NotEmpty
    @Size(min=4)
    private String password;

    @NotEmpty
    @Size(min=4)
    private String confirmPassword;

    @NotEmpty
    @Size(min=5, max=20)
    private String fullName;

    @NotEmpty
    @Email
    private String email;

    private String uniqueUserName;
}

