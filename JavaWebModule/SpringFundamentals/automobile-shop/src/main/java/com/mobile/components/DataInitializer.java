package com.mobile.components;

import com.mobile.models.enums.CategoryEnum;
import com.mobile.models.dtos.BrandDto;
import com.mobile.models.dtos.ModelDto;
import com.mobile.services.BrandService;
import com.mobile.services.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final ModelService modelService;
    private final BrandService brandService;

    @Override
    public void run(String... args) throws Exception {

        List<BrandDto> allBrands = brandService.getAllBrands();

        if (!allBrands.isEmpty()) {
            return;
        }

        BrandDto brand = BrandDto.builder().
                name("Toyota").
                created(LocalDateTime.now()).
                modified(LocalDateTime.now()).
                build();
        BrandDto brandBwv = BrandDto.builder()
                .name("BMW")
                .created(LocalDateTime.now())
                .modified(LocalDateTime.now())
                .build();


        List<ModelDto> corollaModels = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            ModelDto model = ModelDto.builder().name("Corolla"+i)
                    .brand(brand)
                    .category(CategoryEnum.CAR)
                    .imageUrl("https://upload.wikimedia.org/wikipedia/commons/a/a4/2019_Toyota_Corolla_Icon_Tech_VVT-i_Hybrid_1.8.jpg")
                    .created(LocalDateTime.now())
                    .modified(LocalDateTime.now())
                    .startYear(1999l)
                    .endYear(2003l)
                    .build();

            corollaModels.add(model);
        }

        List<ModelDto> bmwModels = new ArrayList<>();
        for (int i = 3; i < 6; i++) {
            ModelDto modelBMW = ModelDto.builder().name("M240i"+i)
                    .brand(brandBwv)
                    .category(CategoryEnum.CAR)
                    .imageUrl("https://www.bmw.bg/content/dam/bmw/common/all-models/m-series/bmw-m240i-xdrive-coupe/onepager/bmw-2-series-m240i-xdrive-coupe-onepager-sp-desktop.jpg.asset.1635257425565.jpg")
                    .created(LocalDateTime.now())
                    .modified(LocalDateTime.now())
                    .startYear(1999l)
                    .endYear(2003l)
                    .build();

            bmwModels.add(modelBMW);
        }

        brand.setModels(corollaModels);
        brandBwv.setModels(bmwModels);

        brandService.addBrand(brand);
        brandService.addBrand(brandBwv);
    }
}
