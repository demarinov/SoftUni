package com.dido.battleships.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@Builder
public class UserLoginDto implements Serializable {

    @NotEmpty
    @Size(min=3, max=10)
    private String username;
    @NotEmpty
    @Size(min=4)
    private String password;
}
