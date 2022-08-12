package com.dido.holidaybay.service;

import com.dido.holidaybay.model.dto.UserRegisterDto;
import com.dido.holidaybay.model.entity.BankAccount;
import com.dido.holidaybay.model.entity.UserEntity;
import com.dido.holidaybay.model.entity.UserRoleEntity;
import com.dido.holidaybay.model.enums.RoleEnum;
import com.dido.holidaybay.repository.BankingRepository;
import com.dido.holidaybay.repository.UserRepository;
import com.dido.holidaybay.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    private final BankingRepository bankingRepository;


    public void init() {

        if (userRepository.count() == 0 && userRoleRepository.count() == 0) {

            UserRoleEntity roleAdmin = UserRoleEntity.builder()
                    .role(RoleEnum.ADMIN)
                    .build();

            UserRoleEntity roleUser = UserRoleEntity.builder()
                    .role(RoleEnum.BASIC_USER)
                    .build();

            UserRoleEntity roleHotelOwner = UserRoleEntity.builder()
                    .role(RoleEnum.HOTEL_OWNER)
                    .build();

            userRoleRepository.save(roleAdmin);
            userRoleRepository.save(roleUser);
            userRoleRepository.save(roleHotelOwner);

            initAdmin(Arrays.asList(roleAdmin, roleUser));
            initUser(Arrays.asList(roleUser));

        }
    }

    private void initAdmin(List<UserRoleEntity> roles) {


        UserEntity userEntity = UserEntity.builder()
                .userRoles(roles)
                .firstName("Admin")
                .lastName("Adminov")
                .userName("admin@mail.com")
                .bonusEligible(true)
                .created(LocalDateTime.now())
                .modified(LocalDateTime.now())
                .password(passwordEncoder.encode("topsecret"))
                .build();
        BankAccount bankAccount = BankAccount.builder()
                .user(userEntity)
                .amount(10000)
                .created(LocalDateTime.now())
                .modified(LocalDateTime.now())
                .build();

        userEntity.setBankAccount(bankAccount);

        UserEntity userEntityTwo = UserEntity.builder()
                .userRoles(roles)
                .firstName("Root")
                .lastName("Adminski")
                .userName("root@mail.com")
                .bonusEligible(true)
                .created(LocalDateTime.now())
                .modified(LocalDateTime.now())
                .password(passwordEncoder.encode("topsecret"))
                .build();
        BankAccount bankAccountTwo = BankAccount.builder()
                .user(userEntityTwo)
                .amount(10000)
                .created(LocalDateTime.now())
                .modified(LocalDateTime.now())
                .build();

        userEntityTwo.setBankAccount(bankAccountTwo);

        userRepository.save(userEntity);
        userRepository.save(userEntityTwo);

    }

    private void initUser(List<UserRoleEntity> roles) {

        UserEntity userEntity = UserEntity.builder()
                .userRoles(roles)
                .firstName("User")
                .lastName("Userov")
                .userName("user@mail.com")
                .bonusEligible(true)
                .created(LocalDateTime.now())
                .modified(LocalDateTime.now())
                .password(passwordEncoder.encode("topsecret"))
                .build();

        BankAccount bankAccount = BankAccount.builder()
                .user(userEntity)
                .amount(10000)
                .created(LocalDateTime.now())
                .modified(LocalDateTime.now())
                .build();

        userEntity.setBankAccount(bankAccount);
        userRepository.save(userEntity);

    }

    public boolean registerAndLogin(UserRegisterDto userRegisterDto) {

        if (!userRegisterDto.getPassword().equals(userRegisterDto.getConfirmPassword())) {
            return false;
        }

        UserRoleEntity userRoleEntity = userRoleRepository.findByRole(RoleEnum.BASIC_USER).orElse(null);

        BankAccount bankAccount = BankAccount.builder()
                .amount(0d)
                .created(LocalDateTime.now())
                .modified(LocalDateTime.now())
                .build();
        UserEntity userEntity = UserEntity.builder()
                .userName(userRegisterDto.getEmail())
                .firstName(userRegisterDto.getFirstName())
                .lastName(userRegisterDto.getLastName())
                .age(userRegisterDto.getAge())
                .bonusEligible(true)
                .bankAccount(bankAccount)
                .password(passwordEncoder.encode(userRegisterDto.getPassword()))
                .userRoles(Arrays.asList(userRoleEntity))
                .build();

        bankAccount.setUser(userEntity);

        userRepository.save(userEntity);

        UserDetails userDetails = userDetailsService.loadUserByUsername(userEntity.getUserName());

        Authentication auth = new UsernamePasswordAuthenticationToken(
                userDetails,
                userDetails.getPassword(),
                userDetails.getAuthorities()

        );

        SecurityContextHolder.getContext().setAuthentication(auth);

        return true;
    }


    public UserEntity getUserByUserName(String userName) {
        return userRepository.findByUserName(userName).orElse(null);
    }

    public List<UserEntity> getAllUsers() {
        return new ArrayList<>(userRepository.findAll());
    }

    public boolean updateUserRole(Long userId, String roleType) {

        UserEntity user = userRepository.findById(userId).orElse(null);

        if (user != null) {

            UserRoleEntity userRoleEntity =
                    userRoleRepository.findByRole(RoleEnum.valueOf(roleType)).orElse(null);
            user.setUserRoles(new ArrayList<>());
            user.getUserRoles().add(userRoleEntity);
            userRepository.save(user);

            return true;
        }

        return false;
    }

    public UserEntity getUserById(Long userId) {

        return userRepository.findById(userId).orElse(null);
    }

    public boolean updateUser(UserEntity userEntity) {

        if (userEntity != null) {
            userRepository.save(userEntity);

            return true;
        }

        return false;
    }
}
