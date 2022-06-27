package com.dido.exam.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
public class UserLoginDto {

    @Size(min=3, max=20)
    private String username;
    @Size(min=3, max=20)
    private String password;
}
