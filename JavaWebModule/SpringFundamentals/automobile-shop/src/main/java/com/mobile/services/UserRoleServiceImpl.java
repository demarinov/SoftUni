package com.mobile.services;

import com.mobile.models.entities.UserRoleEntity;
import com.mobile.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService{

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public List<UserRoleEntity> getAllRoles() {
        return userRoleRepository.getAllBy();
    }
}
