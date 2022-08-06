package com.dido.holidaybay.web;

import com.dido.holidaybay.model.dto.HotelDto;
import com.dido.holidaybay.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/hotels")
public class HotelController {

    private final HotelService hotelService;

    @GetMapping("/all")
    public String hotels(Model model) {

        List<HotelDto> hotelDtoList = hotelService.getHotels();

        model.addAttribute("hotels", hotelDtoList);

        return "hotels";
    }

    @GetMapping("/details")
    public String hotelById(@RequestParam("id") Long id, Model model) {

        HotelDto hotelDto = hotelService.getHotelById(id);

        model.addAttribute("hotel", hotelDto);

        return "hotel-details";
    }

}
