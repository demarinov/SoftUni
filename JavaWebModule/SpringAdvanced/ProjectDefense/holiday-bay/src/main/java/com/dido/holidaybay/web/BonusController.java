package com.dido.holidaybay.web;

import com.dido.holidaybay.model.dto.BonusDto;
import com.dido.holidaybay.model.entity.UserEntity;
import com.dido.holidaybay.service.BonusService;
import com.dido.holidaybay.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bonus")
@RequiredArgsConstructor
public class BonusController {

    private final BonusService bonusService;
    private final UserService userService;

    @GetMapping("/user")
    public ResponseEntity<BonusDto> getUserBonus(@AuthenticationPrincipal UserDetails userDetails) {

        UserEntity user = userService.getUserByUserName(userDetails.getUsername());

        if (user == null) {
            return null;
        }

        BonusDto bonusDto = bonusService.getBonusByUserId(user.getId());

        return bonusDto != null ? ResponseEntity.ok(bonusDto) : ResponseEntity.notFound().build();
    }

    @PostMapping("/user/add")
    @CrossOrigin
    public boolean addBonus(@RequestParam("user") String userName,
                            @RequestParam("bonusType") String bonusType) {


        UserEntity user = userService.getUserByUserName(userName);

        if (user == null) {
            return false;
        }

        return bonusService.addBonus(user, bonusType);
    }

    @PostMapping("/create")
    public boolean createBonus(@RequestBody BonusDto bonusDto) {
        return bonusService.createBonus(bonusDto);
    }

    @RequestMapping(value="/user/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
    public boolean deleteBonus(@RequestParam("user") String userName, @RequestParam("bonusId") Long bonusId) {
        UserEntity user = userService.getUserByUserName(userName);

        if (user == null) {
            return false;
        }
        return bonusService.removeBonus(user, bonusId);
    }
}
