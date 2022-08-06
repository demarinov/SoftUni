package com.dido.holidaybay.web;

import com.dido.holidaybay.service.AttractionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/attraction")
public class AttractionController {

    private final AttractionService attractionService;

    @GetMapping("/all")
    public String attractions(Model model) {

        model.addAttribute("attractionModel", attractionService.getAttractions().get(0));

        return "attraction";
    }
}
