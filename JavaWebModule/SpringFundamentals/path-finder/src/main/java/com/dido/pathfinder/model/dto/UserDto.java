package com.dido.pathfinder.model.dto;

import com.dido.pathfinder.model.validation.FieldMatch;
import com.dido.pathfinder.model.validation.UniqueUserName;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.io.Serializable;

@Getter
@Setter
@Builder
@UniqueUserName(
        field="username",
        secondaryField = "uniqueUserName",
        message = "Username already occupied."
)
@FieldMatch(
        first = "password",
        second = "confirmPassword",
        message = "Passwords do not match."
)
public class UserDto implements Serializable {

    @NotEmpty
    @Size(min = 3)
    private String username;

    @NotEmpty
    @Size(min=5, max=20)
    private String password;
    private String confirmPassword;

    @NotEmpty
    @Size(min=3)
    private String fullName;

    @Email
    private String email;

    @Min(0)
    @Max(90)
    private Integer age;

    private String uniqueUserName;
}

