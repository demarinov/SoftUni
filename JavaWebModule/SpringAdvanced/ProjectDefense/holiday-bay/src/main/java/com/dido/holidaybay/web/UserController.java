package com.dido.holidaybay.web;

import com.dido.holidaybay.model.dto.*;
import com.dido.holidaybay.model.entity.UserEntity;
import com.dido.holidaybay.service.UserService;
import com.dido.holidaybay.service.VoucherService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final VoucherService voucherService;

    @GetMapping("/register")
    public String register(Principal principal, Model model) {

        if (principal != null) {

            return "redirect:/";
        }

        if (model.getAttribute("userRegistrationModel") == null) {
            model.addAttribute("userRegistrationModel",
                    UserRegisterDto.builder().build());
        }

        return "auth-register";
    }

    @PostMapping("/register")
    public String register(@Valid UserRegisterDto userRegisterDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes,
                           Principal principal) {

        if (principal != null) {

            return "redirect:/";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegistrationModel", userRegisterDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegistrationModel", bindingResult);

            return "redirect:/users/register";
        }

        if (!userService.registerAndLogin(userRegisterDTO)) {
            redirectAttributes.addFlashAttribute("userRegistrationModel", userRegisterDTO);
            redirectAttributes.addFlashAttribute("wrong_passwords", true);
            return "redirect:/users/register";
        }
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(Principal principal) {

        if (principal != null) {

            return "redirect:/";
        }

        return "auth-login";
    }

    // NOTE: This should be post mapping!
    @PostMapping("/login-error")
    public String onFailedLogin(
            @ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY) String userName,
            RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY, userName);
        redirectAttributes.addFlashAttribute("bad_credentials",
                true);

        return "redirect:/users/login";
    }

    @GetMapping("/profile")
    public String profile(@AuthenticationPrincipal UserDetails userDetails, Model model) {

        UserEntity user = userService.getUserByUserName(userDetails.getUsername());

        model.addAttribute("user", user);

        return "profile";
    }

    @GetMapping("/admin")
    public String admin(Principal principal, Model model) {

        if (model.getAttribute("adminModel") == null) {
            model.addAttribute("adminModel", AdminDto.builder().build());
        }

        if (model.getAttribute("voucherModel") == null) {
            model.addAttribute("voucherModel", VoucherAdminDto.builder().build());
        }

        List<UserEntity> userDtoList = userService.getAllUsers();
        model.addAttribute("users", userDtoList);

        UserEntity user = userService.getUserByUserName(principal.getName());
        List<VoucherDto> voucherDtoList = voucherService.getVouchers(user);

        model.addAttribute("vouchers", voucherDtoList);

        return "admin";
    }

    @PostMapping("/change-role")
    public String adminUserRoleChange( @Valid
            AdminDto userDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("adminModel", userDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.adminModel", bindingResult);

            return "redirect:/users/admin";
        }

        if (!userService.updateUserRole(userDto.getUserId(), userDto.getRoleType())) {
            redirectAttributes.addFlashAttribute("bad_user_roles", true);
        }

        return "redirect:/users/admin";
    }

    @PostMapping("/voucher-deactivate")
    public String adminDeactivate( @Valid VoucherAdminDto voucherDto,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("voucherModel", VoucherDto.builder()
                        .id(voucherDto.getVoucherId())
                .build());
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("voucherModel", voucherDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.voucherModel", bindingResult);

            return "redirect:/users/admin";
        }

        redirectAttributes.addFlashAttribute("voucherModel", VoucherDto.builder()
                .id(voucherDto.getVoucherId())
                .build());
        // redirect to voucher controller
        return "redirect:/vouchers/deactivate";
    }
}
