package com.mobile.repositories;

import com.mobile.models.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    List<UserEntity> getAllBy();
    UserEntity getByUserName(String userName);

    List<UserEntity> findByUserName(String value);
}
