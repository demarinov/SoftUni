package com.dido.pathfinder.controller;

import com.dido.pathfinder.model.dto.RouteDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/route")
public class RouteController {


    @PostMapping("/add")
    public String add(RouteDto routeDto) {



        return "redirect:/";
    }
}
