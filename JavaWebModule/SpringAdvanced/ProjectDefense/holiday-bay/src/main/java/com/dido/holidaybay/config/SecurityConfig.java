package com.dido.holidaybay.config;

import com.dido.holidaybay.model.enums.RoleEnum;
import com.dido.holidaybay.repository.UserRepository;
import com.dido.holidaybay.service.AppUserDetailsService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                // which requests are allowed or not
                .authorizeRequests()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                .permitAll()
                // everyone can login and register
                .antMatchers("/","/users/login","/users/register",
                        "/img/**","/hotels/**",
                        "/bonus/**", "/actuator/**",
                        "/attraction/**","/video/**")
                .permitAll()
//                .antMatchers("/hotels/**").hasRole(RoleEnum.BASIC_USER.name())
                // all other requests require user to be authenticated
                .anyRequest().authenticated()
                .and()
                // config form login
                .formLogin()
                .loginPage("/users/login")
                // username form field
                .usernameParameter("username")
                // password form field
                .passwordParameter("password")
                // if success where to go
                .defaultSuccessUrl("/")
                // if failure where to go
                .failureForwardUrl("/users/login-error")
                .and()
                .logout().logoutUrl("/users/logout")
                // invalidate session and delete cookies
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .and()
                .csrf().disable()
                .build();
    }

    @Bean
    public PasswordEncoder createPasswordEncoder() {

        return new Pbkdf2PasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new AppUserDetailsService(userRepository);
    }
}
