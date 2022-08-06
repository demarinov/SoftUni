package com.dido.holidaybay.component;

import com.dido.holidaybay.service.AttractionService;
import com.dido.holidaybay.service.UserService;
import com.dido.holidaybay.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppInit implements CommandLineRunner {

    private final UserService userService;
    private final AttractionService attractionService;
    private final VideoService videoService;

    @Override
    public void run(String... args) {

        userService.init();
        attractionService.init();
        videoService.init();
    }
}