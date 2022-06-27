package com.mobile.repositories;

import com.mobile.models.enums.RoleEnum;
import com.mobile.models.entities.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

    List<UserRoleEntity> getAllBy();
    List<UserRoleEntity> getAllByRole(RoleEnum role);
}
