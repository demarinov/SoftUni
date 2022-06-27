package com.dido.battleships.component;

import com.dido.battleships.model.dto.CategoryDto;
import com.dido.battleships.model.enums.ShipEnum;
import com.dido.battleships.repository.CategoryRepository;
import com.dido.battleships.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final CategoryService categoryService;

    @Override
    public void run(String... args) throws Exception {

        List<CategoryDto> categories = categoryService.getAll();

        if (!categories.isEmpty()) {
            return;
        }

        CategoryDto categoryDtoBattle = CategoryDto.builder()
                .name(ShipEnum.BATTLE)
                .build();

        CategoryDto categoryDtoCargo = CategoryDto.builder()
                .name(ShipEnum.CARGO)
                .build();

        CategoryDto categoryDtoPatrol = CategoryDto.builder()
                .name(ShipEnum.PATROL)
                .build();

        categories.add(categoryDtoBattle);
        categories.add(categoryDtoCargo);
        categories.add(categoryDtoPatrol);

        categories.forEach(categoryService::addCategory);
    }
}
