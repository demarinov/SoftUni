package com.mobile.services;

import com.mobile.components.CurrentUser;
import com.mobile.models.dtos.OfferDto;
import com.mobile.models.dtos.UserDto;
import com.mobile.models.entities.OfferEntity;
import com.mobile.models.entities.UserEntity;
import com.mobile.repositories.OfferRepository;
import com.mobile.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OfferServiceImpl implements OfferService{

    private final OfferRepository offerRepository;
    private final ModelMapper mapper;
    private final CurrentUser currentUser;
    private final UserRepository userRepository;

    @Override
    public List<OfferDto> getAllOffers() {

        return offerRepository.getAllBy().stream()
                .map(offerEntity -> mapper.map(offerEntity, OfferDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean addOffer(OfferDto offerDto) {

        return uploadOrUpdateOfferInDb(offerDto, true);
    }

    private boolean uploadOrUpdateOfferInDb(OfferDto offerDto, boolean adding) {

        if (offerDto != null) {
            UserDto userDto = new UserDto();
            userDto.setId(currentUser.getId());
            userDto.setUserName(currentUser.getUsername());
            UserEntity userEntity = userRepository.findById(currentUser.getId()).orElse(null);
            if (userEntity == null) {
                log.debug("User with id {} not present",currentUser.getId());
                return false;
            }
            offerDto.setSeller(userDto);
            if (adding) {
                offerDto.setCreated(LocalDateTime.now());
                offerDto.setModified(LocalDateTime.now());
            } else {

                LocalDateTime created = offerRepository.findById(offerDto.getId())
                        .get().getCreated();
                offerDto.setModified(LocalDateTime.now());
                offerDto.setCreated(created);
            }

            OfferEntity offerEntity = mapper.map(offerDto, OfferEntity.class);
            offerEntity.setSeller(userEntity);
            // auto find the model entity based on model id from dto
            offerRepository.save(offerEntity);
            return true;
        }

        return false;
    }

    @Override
    public OfferDto getOfferDetails(UUID id) {

        OfferEntity offer = offerRepository.findById(id).orElse(null);
        if (offer != null) {
            log.debug(offer.toString());
            return mapper.map(offer, OfferDto.class);
        }

        return null;
    }

    @Override
    public boolean updateOffer(OfferDto offerDto) {

        return uploadOrUpdateOfferInDb(offerDto, false);
    }

    @Override
    public boolean deleteOffer(UUID id) {
        offerRepository.deleteById(id);
        return true;
    }
}
