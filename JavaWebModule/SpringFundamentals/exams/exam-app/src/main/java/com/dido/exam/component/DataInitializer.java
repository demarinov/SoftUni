package com.dido.exam.component;

import com.dido.exam.model.dto.StyleDto;
import com.dido.exam.model.enums.StyleEnum;
import com.dido.exam.service.StyleService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final StyleService styleService;

    @Override
    public void run(String... args) throws Exception {

        List<StyleDto> styles = styleService.getAll();

        if (!styles.isEmpty()) {
            return;
        }

        StyleDto styleDtoPop = StyleDto.builder()
                .name(StyleEnum.POP.name())
                .option(StyleEnum.POP)
                .build();

        StyleDto styleDtoRock = StyleDto.builder()
                .name(StyleEnum.ROCK.name())
                .option(StyleEnum.ROCK)
                .build();

        StyleDto styleDtoJazz = StyleDto.builder()
                .name(StyleEnum.JAZZ.name())
                .option(StyleEnum.JAZZ)
                .build();

        styles.add(styleDtoPop);
        styles.add(styleDtoRock);
        styles.add(styleDtoJazz);

        styles.forEach(styleService::addStyle);

    }
}
