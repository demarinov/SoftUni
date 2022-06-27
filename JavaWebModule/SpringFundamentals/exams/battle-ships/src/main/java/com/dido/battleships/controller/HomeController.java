package com.dido.battleships.controller;

import com.dido.battleships.model.dto.ShipDto;
import com.dido.battleships.service.ShipService;
import com.dido.battleships.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    private final UserService userService;
    private final ShipService shipService;

    @GetMapping({"","/home"})
    public String home(Model model) {

        if (!userService.isLoggedIn()) {
            return "redirect:/";
        }

        model.addAttribute("userShips", shipService.getShipsOfCurrentUser());
        model.addAttribute("otherShips", shipService.getNonCurrentUserShips());
        model.addAttribute("allShips", shipService.getALlShips());

        return "home";
    }

    @PostMapping("/home")
    public String fire(@RequestParam("userShip") String userShipId,
                       @RequestParam("otherShip") String otherShipId) {

        log.debug(userShipId);
        log.debug(otherShipId);

        ShipDto userShipDto = shipService.findShipById(userShipId);
        ShipDto otherShipDto = shipService.findShipById(otherShipId);

        if (userShipDto != null && otherShipDto != null) {
            log.debug("Attacker and defender can fight");

            int otherHealth = otherShipDto.getHealth() - userShipDto.getPower();

            if (otherHealth <= 0) {
                log.debug("Defender is dead.RIP!");

                // remove the defender ship from db
                shipService.removeShip(otherShipDto);
            } else {
                otherShipDto.setHealth(otherHealth);
                shipService.updateShipHealth(otherShipDto);
            }
        }

        return "redirect:/home";
    }

    @GetMapping("/")
    public String index(Model model) {

        if (userService.isLoggedIn()) {
            return "redirect:/home";
        }

        return "index";
    }
}
