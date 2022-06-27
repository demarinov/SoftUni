package com.mobile.models.dtos;

import com.mobile.models.enums.RoleEnum;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserRoleDto {

    private Long id;

    @NotNull
    private RoleEnum role;
}
