package com.dido.exam.controller;

import com.dido.exam.model.dto.SongDto;
import com.dido.exam.model.entity.UserEntity;
import com.dido.exam.service.SongService;
import com.dido.exam.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
@RequiredArgsConstructor
@RequestMapping("/song")
public class SongController {

    private final UserService userService;
    private final SongService songService;
    private static final String SONG_MODEL="songModel";

    @GetMapping("/add")
    public String add(Model model) {

        if (!userService.isLoggedIn()) {
            return "redirect:/";
        }

        if(model.getAttribute(SONG_MODEL) == null) {

            SongDto songDto = SongDto.builder()
                    .style(null)
                    .build();
            model.addAttribute(SONG_MODEL, songDto);
            model.addAttribute("styleModel", "");
        }

        return "song-add";
    }

    @PostMapping("/add")
    public String add(@Valid SongDto songDto,
                      BindingResult bindingResult,
                      RedirectAttributes redirectAttributes
                      ) {

        if (!userService.isLoggedIn()) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute(SONG_MODEL, songDto);
            String styleModel = songDto.getStyle() == null ? "" : songDto.getStyle().name();
            redirectAttributes.addFlashAttribute("styleModel", styleModel);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.songModel",
                            bindingResult);

            return "redirect:/song/add";
        }

        songService.addSong(songDto);
        return "redirect:/home";
    }

    @GetMapping("/playlist/add/{id}")
    public String addSongToPlaylist(@PathVariable("id") Long songId) {

        if (!userService.isLoggedIn()) {
            return "redirect:/";
        }

        songService.addSongToPlaylist(songId);
        return "redirect:/home";
    }

    @RequestMapping(value="/remove",method = {RequestMethod.GET, RequestMethod.DELETE})
    public String deleteSongs() {

        if (!userService.isLoggedIn()) {
            return "redirect:/";
        }

        UserEntity userEntity = userService.findCurrentUser();

        userEntity.setPlaylist(new ArrayList<>());

        userService.updateUser(userEntity);

        return "redirect:/";
    }
}
