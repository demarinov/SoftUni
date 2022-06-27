package com.dido.exam.controller;

import com.dido.exam.model.dto.SongDto;
import com.dido.exam.service.SongService;
import com.dido.exam.service.UserService;
import com.dido.exam.util.SecondsUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.thymeleaf.util.NumberUtils;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    private final UserService userService;
    private final SongService songService;

    @GetMapping("/")
    public String index() {

        if (userService.isLoggedIn()) {
            return "redirect:/home";
        }

        return "index";
    }


    @GetMapping({"","/home"})
    public String home(Model model) {

        if (!userService.isLoggedIn()) {
            return "redirect:/";
        }

        List<SongDto> userSongs = songService.getSongsOfCurrentUser();
        model.addAttribute("userSongs", userSongs);
        List<SongDto> allSongs = songService.getALlSongs();

        model.addAttribute("totalDurationOfPlaylist", SecondsUtils.convertToMinutes(userSongs
                .stream()
                .mapToLong(SongDto::getDuration).sum()));
        model.addAttribute("popSongs", allSongs.stream().filter(song ->
                song.getStyle().name().equals("POP")).collect(Collectors.toList()));
        model.addAttribute("rockSongs", allSongs.stream().filter(song ->
                song.getStyle().name().equals("ROCK")).collect(Collectors.toList()));
        model.addAttribute("jazzSongs", allSongs.stream().filter(song ->
                song.getStyle().name().equals("JAZZ")).collect(Collectors.toList()));

        return "home";
    }
}
