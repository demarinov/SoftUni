package com.mobile.controllers;

import com.mobile.models.dtos.UserDto;
import com.mobile.models.dtos.UserLoginDto;
import com.mobile.models.enums.RoleEnum;
import com.mobile.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private static final String REDIRECT_HOME = "redirect:/";
    private static final String USER_ROLES_MODEL = "userRolesModel";

    @ModelAttribute("userModel")
    public UserDto initUserModel(Model model) {
        model.addAttribute(USER_ROLES_MODEL, new String[]{"DEFAULT"});
        return UserDto.builder()
                .build();
    }

    @ModelAttribute("userLoginDto")
    public UserLoginDto initUserLoginDto() {

        return UserLoginDto.builder().build();
    }

    @GetMapping("/register")
    public String showRegistration(HttpSession session, Model model) {
        session.setAttribute("user", null);

        return "auth-register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("userModel") UserDto userDto,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes,
                           @RequestParam(value = "roles", required = false) String[] userRoles,
                           HttpSession session) {

        if (bindingResult.hasErrors()) {

            if (userRoles== null || userRoles.length == 0) {
                userRoles = new String[1];
                userRoles[0] = "NONE";
                userDto.normalizeUserRole(userRoles);
            }
            redirectAttributes.addFlashAttribute("userModel", userDto);
            redirectAttributes.addFlashAttribute(USER_ROLES_MODEL, userRoles);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userModel", bindingResult);
            return "redirect:/users/register";
        }

        if (userRoles== null || userRoles.length == 0) {
            userRoles = new String[1];
            userRoles[0] = "NONE";
            redirectAttributes.addFlashAttribute(USER_ROLES_MODEL, userRoles);
        }

        userService.register(userDto, userRoles, session);
        return REDIRECT_HOME;
    }

    @GetMapping("/login")
    public String showLogin(Model model) {
        return "auth-login";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("userLoginDto") UserLoginDto userDto,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes,
                        HttpSession session) {

        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userLoginDto", userDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginDto", bindingResult);
            return "redirect:/users/login";
        }

        boolean result = userService.login(userDto, session);

        return result ? REDIRECT_HOME : "redirect:/users/login";
    }

    @GetMapping("logout")
    public String logout(HttpSession session) {

        session.setAttribute("user",null);
        userService.logout();

        return REDIRECT_HOME;
    }
}
