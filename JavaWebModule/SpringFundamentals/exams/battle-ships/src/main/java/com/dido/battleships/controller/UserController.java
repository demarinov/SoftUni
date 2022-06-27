package com.dido.battleships.controller;

import com.dido.battleships.model.dto.UserDto;
import com.dido.battleships.model.dto.UserLoginDto;
import com.dido.battleships.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private static final String HOME_REDIRECT="redirect:/home";
    private static final String LOGIN_REDIRECT="redirect:/user/login";
    private static final String INDEX_REDIRECT="redirect:/";
    private static final String USER_REGISTRATION_MODEL = "userRegistrationModel";
    private static final String USER_LOGIN_MODEL = "userLoginModel";

    private final UserService userService;

    @GetMapping("/login")
    public String login(Model model) {

        if (model.getAttribute(USER_LOGIN_MODEL) == null) {
            model.addAttribute(USER_LOGIN_MODEL, UserLoginDto.builder().build());
        }

        if (userService.isLoggedIn()) {
            return HOME_REDIRECT;
        }

        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {

        if (model.getAttribute(USER_REGISTRATION_MODEL) == null) {
            model.addAttribute(USER_REGISTRATION_MODEL, UserDto.builder().build());
        }

        if (userService.isLoggedIn()) {
            return HOME_REDIRECT;
        }

        return "register";
    }



    @PostMapping("/register")
    public String register(@Valid UserDto userDto,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes,
                           Model model){

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute(USER_REGISTRATION_MODEL, userDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegistrationModel", bindingResult);

            return "redirect:/user/register";
        }

        userService.register(userDto);

        return HOME_REDIRECT;
    }

    @PostMapping("/login")
    public String login(@Valid UserLoginDto userLoginDto,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes,
                        Model model) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute(USER_LOGIN_MODEL, userLoginDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginModel", bindingResult);

            return LOGIN_REDIRECT;
        }

        boolean result = userService.login(userLoginDto);

        if (!result) {
            redirectAttributes.addFlashAttribute(USER_LOGIN_MODEL, userLoginDto);
            redirectAttributes.addFlashAttribute("wrongCredentials", true);

            return LOGIN_REDIRECT;
        }

        return HOME_REDIRECT;
    }

    @RequestMapping(value="/logout", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String logout() {

        userService.logout();
        return INDEX_REDIRECT;
    }


}
