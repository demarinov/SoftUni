package com.dido.holidaybay.web;

import com.dido.holidaybay.constants.CommonConstants;
import com.dido.holidaybay.model.dto.BookingDto;
import com.dido.holidaybay.model.dto.VoucherDto;
import com.dido.holidaybay.model.entity.UserEntity;
import com.dido.holidaybay.service.UserService;
import com.dido.holidaybay.service.VoucherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/vouchers")
public class VoucherController {

    private final VoucherService voucherService;
    private final UserService userService;

    @GetMapping("/all")
    public String vouchers(Principal principal, Model model, @PageableDefault(value = 0, size=1) Pageable pageable) {

        if (principal == null) {

            return CommonConstants.LOGIN_REDIRECT;
        }

        UserEntity user = userService.getUserByUserName(principal.getName());
        Page<VoucherDto> voucherDtoList = voucherService.getVouchers(user,pageable);

        model.addAttribute("voucherList", voucherDtoList);

        return "vouchers";
    }

    @PostMapping("/create")
    public String create(Principal principal, BookingDto bookingDto) {

        if (principal == null) {

            return CommonConstants.LOGIN_REDIRECT;
        }

        voucherService.create(bookingDto);

        return "redirect:/vouchers/all";
    }

    @RequestMapping(value="/deactivate", method={RequestMethod.DELETE, RequestMethod.GET})
    public String deactivate(Principal principal, @ModelAttribute("voucherModel") VoucherDto voucherDto) {

        if (principal == null) {

            return CommonConstants.LOGIN_REDIRECT;
        }

        voucherService.deactivateVoucher(voucherDto);
        return "redirect:/vouchers/all";
    }
}
