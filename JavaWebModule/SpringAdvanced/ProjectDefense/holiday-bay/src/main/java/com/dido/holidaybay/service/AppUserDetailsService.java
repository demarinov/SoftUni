package com.dido.holidaybay.service;

import com.dido.holidaybay.model.entity.UserEntity;
import com.dido.holidaybay.model.entity.UserRoleEntity;
import com.dido.holidaybay.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.stream.Collectors;

@RequiredArgsConstructor
public class AppUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {



        return userRepository.findByUserName(username)
                .map(this::map)
                .orElseThrow(() -> new UsernameNotFoundException("User with email:"+username+" not found!"));
    }

    private UserDetails map(UserEntity userEntity) {
        return User.builder()
                        .username(userEntity.getUserName())
                        .password(userEntity.getPassword())
                        .authorities(userEntity.getUserRoles()
                                .stream().map(this::map).collect(Collectors.toList()))
                        .build();
    }

    private GrantedAuthority map(UserRoleEntity userRoleEntity) {

        return new SimpleGrantedAuthority("ROLE_"+userRoleEntity.getRole().name());
    }
}
