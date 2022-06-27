package com.dido.battleships.service;

import com.dido.battleships.component.CurrentUser;
import com.dido.battleships.model.dto.ShipDto;
import com.dido.battleships.model.entity.CategoryEntity;
import com.dido.battleships.model.entity.ShipEntity;
import com.dido.battleships.model.entity.UserEntity;
import com.dido.battleships.repository.ShipRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShipService {

    private final ShipRepository shipRepository;
    private final CategoryService categoryService;
    private final UserService userService;
    private final CurrentUser currentUser;
    private final ModelMapper mapper;

    public List<ShipDto> getALlShips() {

        return shipRepository.findAll().stream()
                .map(shipEntity -> mapper.map(shipEntity, ShipDto.class))
                .sorted((shipOne, shipTwo) -> {

                    int resultName = shipOne.getName().compareTo(shipTwo.getName());

                    int resultHealth = resultName != 0 ? resultName : shipOne.getHealth()
                            .compareTo(shipTwo.getHealth());

                    return resultHealth != 0 ? resultHealth : shipOne.getPower()
                            .compareTo(shipTwo.getPower());
                })
                .toList();
    }

    public List<ShipDto> getShipsOfCurrentUser() {

        return shipRepository.findAll()
                .stream().filter(ship -> ship.getUser().getUsername().equals(currentUser.getUsername()))
                .map(ship -> mapper.map(ship, ShipDto.class))
                .toList();
    }

    public List<ShipDto> getNonCurrentUserShips() {
        return shipRepository.findAll()
                .stream().filter(ship -> !ship.getUser().getUsername().equals(currentUser.getUsername()))
                .map(ship -> mapper.map(ship, ShipDto.class))
                .toList();
    }

    public boolean addShip(ShipDto shipDto) {

        if (shipDto != null) {
            ShipEntity shipEntity = mapper.map(shipDto, ShipEntity.class);
            CategoryEntity categoryEntity = categoryService.findByName(shipDto.getCategory().getName());
            shipEntity.setCategory(categoryEntity);
            UserEntity userEntity = userService.findCurrentUser();
            shipEntity.setUser(userEntity);
            shipRepository.save(shipEntity);
            return true;
        }

        return false;
    }

    public ShipDto findShipById(String id) {

        if (id != null && !id.isEmpty()) {

            return mapper.map(shipRepository.findById(Long.valueOf(id)).orElse(null),
                    ShipDto.class);
        }

        log.debug("Ship id cannot be null or empty");
        return null;

    }

    public boolean removeShip(ShipDto otherShipDto) {

        ShipEntity shipToRemove =  shipRepository.findById(otherShipDto.getId())
                .orElse(null);

        if (shipToRemove != null) {

            shipRepository.delete(shipToRemove);
            return true;
        }

        log.debug("No ship to remove: null");
        return false;
    }

    public boolean updateShipHealth(ShipDto shipDto) {

        if (shipDto != null) {
            ShipEntity updateShipEntity = shipRepository.findById(shipDto.getId()).orElse(null);

            if (updateShipEntity != null) {
                updateShipEntity.setHealth(shipDto.getHealth());
                shipRepository.save(updateShipEntity);

                return true;
            }
        }

        return false;
    }
}
