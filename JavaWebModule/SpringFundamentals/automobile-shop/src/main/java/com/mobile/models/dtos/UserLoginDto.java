package com.mobile.models.dtos;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserLoginDto implements Serializable {

    @NotEmpty
    @Size(min=5, max=20)
    private String userName;

    @NotEmpty
    @Size(min=5)
    private String password;
}
