package com.mobile.services;


import com.mobile.models.dtos.BrandDto;
import com.mobile.models.entities.BrandEntity;
import com.mobile.repositories.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService{

    private final BrandRepository brandRepository;
    private final ModelMapper mapper;


    @Override
    public List<BrandDto> getAllBrands() {

        List<BrandEntity> brands = brandRepository.getAllBy();
        return brands.stream().map(brand -> mapper.map(brand, BrandDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public BrandEntity addBrand(BrandDto brandDto) {
        if (brandDto != null) {
            BrandEntity brand = mapper.map(brandDto, BrandEntity.class);
            return brandRepository.save(brand);
        }

        return null;
    }
}
