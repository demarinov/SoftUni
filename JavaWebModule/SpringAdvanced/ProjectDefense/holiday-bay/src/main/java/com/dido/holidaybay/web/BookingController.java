package com.dido.holidaybay.web;

import com.dido.holidaybay.constants.CommonConstants;
import com.dido.holidaybay.model.dto.BookingDto;
import com.dido.holidaybay.model.dto.HotelDto;
import com.dido.holidaybay.model.entity.UserEntity;
import com.dido.holidaybay.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/booking")
public class BookingController {

    private final BookingService bookingService;
    private final HotelService hotelService;
    private final UserService userService;
    private final VoucherService voucherService;

    @ModelAttribute("bookingModel")
    public Model bookingModel(Model bookingModel) {
        bookingModel.addAttribute("bookingModel", new BookingDto());
        return bookingModel;
    }

    @GetMapping("/add")
    public String booking(Model model, Principal principal) {

        if (principal == null) {
            return CommonConstants.LOGIN_REDIRECT;
        }

        List<HotelDto> hotelDtoList = hotelService.getHotelsWithFreeRooms();
        model.addAttribute("hotels", hotelDtoList);
        return "add-booking";
    }

    @PostMapping("/add")
    public String addBooking(@Valid BookingDto bookingDto,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,
                             Principal principal) {

        if (principal == null) {
            return CommonConstants.LOGIN_REDIRECT;
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("bookingModel", bookingDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.bookingModel",
                    bindingResult);
            redirectAttributes.addFlashAttribute("invalid", true);
            return "redirect:/booking/add";
        }

        UserEntity user = userService.getUserByUserName(principal.getName());

        boolean bookingResult = bookingService.addBooking(bookingDto, user);
        if (bookingResult) {
            bookingResult = voucherService.create(bookingDto);
        }

        return bookingResult ? "redirect:/vouchers/all" : "redirect:/banking";
    }

    @GetMapping("/active")
    public String bookings(Principal principal, Model model,
                           @PageableDefault(value = 0, size=5) Pageable pageable) {

        if (principal == null) {
            return CommonConstants.LOGIN_REDIRECT;
        }

        UserEntity user = userService.getUserByUserName(principal.getName());

        Page<BookingDto> bookingDtoList = bookingService.getUserBookings(user, pageable);

        model.addAttribute("bookingList", bookingDtoList);
        model.addAttribute("past", false);

        return "booking";
    }

    @GetMapping("/history")
    public String bookingsHistory(Principal principal, Model model,
                                  @PageableDefault(value = 0, size=5) Pageable pageable) {

        if (principal == null) {
            return CommonConstants.LOGIN_REDIRECT;
        }

        UserEntity user = userService.getUserByUserName(principal.getName());

        Page<BookingDto> bookingDtoList = bookingService.getUserBookingsHistory(user, pageable);

        model.addAttribute("bookingList", bookingDtoList);
        model.addAttribute("past", true);

        return "booking-history";
    }

    @RequestMapping(value = "/cancel/{id}", method={RequestMethod.DELETE, RequestMethod.GET})
    public String bookingCancel(@PathVariable("id") Long bookId, Principal principal) {

        if (principal == null) {
            return CommonConstants.LOGIN_REDIRECT;
        }

        return "redirect:/vouchers/deactivate";
    }
}
