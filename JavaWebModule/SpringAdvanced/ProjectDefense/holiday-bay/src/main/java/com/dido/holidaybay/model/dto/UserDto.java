package com.dido.holidaybay.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private String userName;
    private String firstName;
    private String lastName;
    private Integer age;
}
