package com.dido.holidaybay.service;

import com.dido.holidaybay.model.dto.BonusDto;
import com.dido.holidaybay.model.entity.BonusEntity;
import com.dido.holidaybay.model.entity.UserEntity;
import com.dido.holidaybay.model.enums.BonusTypeEnum;
import com.dido.holidaybay.repository.BonusRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class BonusService {

    private final BonusRepository bonusRepository;

    public BonusDto getBonusByUserId(Long userId) {

        Optional<BonusEntity> bonusEntity = bonusRepository.findByUserId(userId);
        return bonusEntity.map(this::map).orElse(null);
    }

    private BonusDto map(BonusEntity bonus) {

        return BonusDto.builder()
                .bonusType(bonus.getBonusType().name())
                .id(bonus.getId())
                .build();
    }

    public boolean addBonus(UserEntity user, String bonusType) {

        List<BonusEntity> bonusEntityList = bonusRepository.findAllByNotGiven(true)
                .stream().filter(bonus-> bonus.getBonusType().name().equals(bonusType))
                .collect(Collectors.toList());

        if (!bonusEntityList.isEmpty()) {

            BonusEntity bonusEntity = bonusEntityList.get(0);

            bonusEntity.setUser(user);
            bonusEntity.setNotGiven(false);
            bonusRepository.save(bonusEntity);
            return true;
        }

        return false;

    }

    public boolean removeBonus(UserEntity user, Long bonusId) {
        List<BonusEntity> userBonuses = user.getBonuses();

        if (!userBonuses.isEmpty()) {

            BonusEntity bonusToRemove = userBonuses.stream()
                    .filter(bonus -> bonus.getId().equals(bonusId))
                    .findFirst().orElse(null);
            if (bonusToRemove != null) {
                bonusToRemove.setNotGiven(true);
                bonusToRemove.setUser(null);
                bonusRepository.save(bonusToRemove);
                userBonuses.remove(bonusToRemove);

                return true;
            }

        }

        return false;
    }

    public boolean createBonus(BonusDto bonusDto) {

        if (bonusDto != null) {
            BonusEntity bonusEntity = BonusEntity.builder()
                    .bonusType(BonusTypeEnum.valueOf(bonusDto.getBonusType()))
                    .info(bonusDto.getInfo())
                    .notGiven(true)
                    .created(LocalDateTime.now())
                    .modified(LocalDateTime.now())
                    .build();

            bonusRepository.save(bonusEntity);
            return true;
        }

        return false;
    }
}
