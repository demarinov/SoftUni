package com.dido.holidaybay.web;

import com.dido.holidaybay.model.dto.BookingDto;
import com.dido.holidaybay.model.dto.HotelDto;
import com.dido.holidaybay.model.entity.UserEntity;
import com.dido.holidaybay.service.BookingService;
import com.dido.holidaybay.service.HotelService;
import com.dido.holidaybay.service.UserService;
import com.dido.holidaybay.service.VoucherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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


        List<HotelDto> hotelDtoList = hotelService.getHotelsWithFreeRooms();
        model.addAttribute("hotels", hotelDtoList);
        return "add-booking";
    }

    @PostMapping("/add")
    public String addBooking(@Valid BookingDto bookingDto,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,
                             Principal principal) {


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


        UserEntity user = userService.getUserByUserName(principal.getName());

        Page<BookingDto> bookingDtoList = bookingService.getUserBookings(user, pageable);

        model.addAttribute("bookingList", bookingDtoList);
        model.addAttribute("past", false);

        return "booking";
    }

    @GetMapping("/history")
    public String bookingsHistory(Principal principal, Model model,
                                  @PageableDefault(value = 0, size=5) Pageable pageable) {

        UserEntity user = userService.getUserByUserName(principal.getName());

        Page<BookingDto> bookingDtoList = bookingService.getUserBookingsHistory(user, pageable);

        model.addAttribute("bookingList", bookingDtoList);
        model.addAttribute("past", true);

        return "booking-history";
    }

}
