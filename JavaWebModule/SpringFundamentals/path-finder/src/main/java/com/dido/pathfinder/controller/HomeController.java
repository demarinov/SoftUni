package com.dido.pathfinder.controller;

import com.dido.pathfinder.model.entity.RouteEntity;
import com.dido.pathfinder.model.entity.UserEntity;
import com.dido.pathfinder.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final RouteService routeService;

    @GetMapping({"","/home"})
    public ModelAndView home(ModelAndView modelAndView) {
        modelAndView.setViewName("index");

        UserEntity user = new UserEntity();

        List<RouteEntity> routes = routeService.getMostCommented();

        modelAndView.addObject("mostCommented", routes.get(0));

        modelAndView.addObject("currentUser", user);



        return modelAndView;
    }
}
