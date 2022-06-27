package com.dido.pathfinder.controller;

import com.dido.pathfinder.model.dto.UserDto;
import com.dido.pathfinder.model.dto.UserLoginDto;
import com.dido.pathfinder.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private static final String HOME_REDIRECT="redirect:/";

    private final UserService userService;

    @ModelAttribute("userRegistrationModel")
    public UserDto initModel() {

        return UserDto.builder().build();
    }

    @GetMapping("/login")
    public String login(Model model) {

        return "login";
    }

    @PostMapping("/login")
    public String login(UserLoginDto userLoginDto, Model model) {
        userService.login(userLoginDto);
        return HOME_REDIRECT;
    }

    @GetMapping("/register")
    public String register(Model model) {

        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid UserDto userDto,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes,
                           Model model){

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("userRegistrationModel", userDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegistrationModel", bindingResult);

            return "redirect:/user/register";
        }

        userService.register(userDto);
        return HOME_REDIRECT;
    }

    @RequestMapping(value="/logout", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String logout() {

        userService.logout();
        return HOME_REDIRECT;
    }

    @GetMapping("/profile/{id}")
    public String profile(@PathVariable("id") Long id) {

        return "profile";
    }
}
