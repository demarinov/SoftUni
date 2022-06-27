package com.dido.battleships.controller;

import com.dido.battleships.model.dto.CategoryDto;
import com.dido.battleships.model.dto.ShipDto;
import com.dido.battleships.model.enums.ShipEnum;
import com.dido.battleships.service.ShipService;
import com.dido.battleships.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Arrays;

@Controller
@RequiredArgsConstructor
@RequestMapping("/ship")
@Slf4j
public class ShipController {

    private final UserService userService;
    private final ShipService shipService;
    private static final String SHIP_MODEL="shipModel";

    @GetMapping("/add")
    public String add(Model model) {

        if (!userService.isLoggedIn()) {
            return "redirect:/";
        }

        if(model.getAttribute(SHIP_MODEL) == null) {
            CategoryDto categoryDto = CategoryDto.builder()
                    .build();
            ShipDto shipDto = ShipDto.builder()
                    .category(categoryDto)
                    .build();
            model.addAttribute(SHIP_MODEL, shipDto);
            model.addAttribute("categoryModel", "");
            Arrays.stream(ShipEnum.values()).forEach(val -> log.info(val.toString()));
        }

        return "ship-add";
    }

    @PostMapping("/add")
    public String add(@Valid ShipDto shipDto,
                      BindingResult bindingResult,
                      RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            if (shipDto.getCategory() == null) {
                shipDto.setCategory(CategoryDto.builder().build());
            }
            redirectAttributes.addFlashAttribute(SHIP_MODEL, shipDto);
            String categoryModel = shipDto.getCategory().getName() == null ? "" : shipDto
                    .getCategory()
                    .getName().name();
            redirectAttributes.addFlashAttribute("categoryModel", categoryModel);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.shipModel",
                            bindingResult);

            return "redirect:/ship/add";
        }

        shipService.addShip(shipDto);
        return "redirect:/home";
    }
}
