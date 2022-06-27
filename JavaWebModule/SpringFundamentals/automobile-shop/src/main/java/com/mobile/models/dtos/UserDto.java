package com.mobile.models.dtos;

import com.mobile.models.enums.RoleEnum;
import com.mobile.models.validation.FieldMatch;
import com.mobile.models.validation.UniqueUserName;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldMatch(
        first = "password",
        second = "confirmPassword",
        message = "Passwords do not match."
)
public class UserDto implements Serializable {

    private Long id;

    @NotEmpty
    @Size(min=5, max=20)
    @UniqueUserName(message="User should be unique.")
    private String userName;

    @NotEmpty
    @Size(min=5)
    private String password;

    private String confirmPassword;

    @NotEmpty
    @Size(min=2, max=20)
    private String firstName;

    @NotEmpty
    @Size(min=2, max=20)
    private String lastName;

    private Boolean isActive;

    private String imageUrl;

    private LocalDateTime created;

    private LocalDateTime modified;

    // it is best to have the dto same as view namely string array
    private List<UserRoleDto> userRoles;

    private String[] roles;

    public void normalizeUserRole(String[] userRoles) {
        this.setUserRoles(new ArrayList<>());

        for (String userRole : userRoles) {
            String role = userRole.equals("A") ? "ADMIN" : "USER";
            this.getUserRoles().add(UserRoleDto.builder().role(RoleEnum.valueOf(role)).build());
        }
    }
}
