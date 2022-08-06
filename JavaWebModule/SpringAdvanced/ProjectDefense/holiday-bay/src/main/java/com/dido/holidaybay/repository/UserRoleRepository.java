package com.dido.holidaybay.repository;

import com.dido.holidaybay.model.entity.UserRoleEntity;
import com.dido.holidaybay.model.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

    Optional<UserRoleEntity> findByRole(RoleEnum roleEnum);
}