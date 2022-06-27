package com.mobile.controllers;

import com.mobile.models.dtos.BrandDto;
import com.mobile.services.BrandService;
import com.mobile.services.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/brands")
@RequiredArgsConstructor
public class BrandsController {

    private final BrandService brandService;
    private final ModelService modelService;

    @GetMapping("/all")
    public ModelAndView all(ModelAndView modelAndView, HttpSession session) {
        modelAndView.setViewName("brands");
        session.setAttribute("user", null);

        List<BrandDto> brandList = brandService.getAllBrands();

        modelAndView.addObject("brandList", brandList);

        return modelAndView;
    }
}
