package com.mobile.services;

import com.mobile.models.dtos.BrandDto;
import com.mobile.models.entities.BrandEntity;

import java.util.List;

public interface BrandService {

    List<BrandDto> getAllBrands();
    BrandEntity addBrand(BrandDto brandDto);
}
