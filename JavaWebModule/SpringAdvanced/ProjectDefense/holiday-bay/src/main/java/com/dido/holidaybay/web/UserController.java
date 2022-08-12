package com.dido.holidaybay.web;

import com.dido.holidaybay.model.dto.AdminDto;
import com.dido.holidaybay.model.dto.UserRegisterDto;
import com.dido.holidaybay.model.dto.VoucherAdminDto;
import com.dido.holidaybay.model.dto.VoucherDto;
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

    private static final String USER_REGISTRATION_MODEL = "userRegistrationModel";
    private static final String HOME_REDIRECT = "redirect:/";
    private static final String ADMIN_REDIRECT = "redirect:/users/admin";
    private static final String ADMIN_MODEL = "adminModel";
    private static final String VOUCHER_MODEL = "voucherModel";

    @GetMapping("/register")
    public String register(Principal principal, Model model) {

        if (principal != null) {

            return HOME_REDIRECT;
        }

        if (model.getAttribute(USER_REGISTRATION_MODEL) == null) {
            model.addAttribute(USER_REGISTRATION_MODEL,
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

            return HOME_REDIRECT;
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute(USER_REGISTRATION_MODEL, userRegisterDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegistrationModel", bindingResult);

            return "redirect:/users/register";
        }

        if (!userService.registerAndLogin(userRegisterDTO)) {
            redirectAttributes.addFlashAttribute(USER_REGISTRATION_MODEL, userRegisterDTO);
            redirectAttributes.addFlashAttribute("wrong_passwords", true);
            return "redirect:/users/register";
        }
        return HOME_REDIRECT;
    }

    @GetMapping("/login")
    public String login(Principal principal) {

        if (principal != null) {

            return HOME_REDIRECT;
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

        if (model.getAttribute(ADMIN_MODEL) == null) {
            model.addAttribute(ADMIN_MODEL, AdminDto.builder().build());
        }

        if (model.getAttribute(VOUCHER_MODEL) == null) {
            model.addAttribute(VOUCHER_MODEL, VoucherAdminDto.builder().build());
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

            redirectAttributes.addFlashAttribute(ADMIN_MODEL, userDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.adminModel", bindingResult);

            return ADMIN_REDIRECT;
        }

        if (!userService.updateUserRole(userDto.getUserId(), userDto.getRoleType())) {
            redirectAttributes.addFlashAttribute("bad_user_roles", true);
        }

        return ADMIN_REDIRECT;
    }

    @PostMapping("/voucher-deactivate")
    public String adminDeactivate( @Valid VoucherAdminDto voucherDto,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute(VOUCHER_MODEL, VoucherDto.builder()
                        .id(voucherDto.getVoucherId())
                .build());
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute(VOUCHER_MODEL, voucherDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.voucherModel", bindingResult);

            return ADMIN_REDIRECT;
        }

        redirectAttributes.addFlashAttribute(VOUCHER_MODEL, VoucherDto.builder()
                .id(voucherDto.getVoucherId())
                .build());
        // redirect to voucher controller
        return "redirect:/vouchers/deactivate";
    }
}
