package com.dido.holidaybay.service;

import com.dido.holidaybay.model.dto.AttractionDto;
import com.dido.holidaybay.model.entity.AttractionEntity;
import com.dido.holidaybay.repository.AttractionRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class AttractionServiceTests {

    @Autowired
    private AttractionService attractionService;

    @Autowired
    private AttractionRepository attractionRepository;

    @BeforeEach
    void init() {
        attractionRepository.deleteAll();

    }

    @AfterEach
    void end() {

        if (attractionRepository.count() == 0) {
            AttractionEntity attractionEntity = createAttractionEntity();
            attractionRepository.save(attractionEntity);
        }

    }

    private AttractionEntity createAttractionEntity() {
        AttractionEntity attractionEntity = AttractionEntity.builder()
                .name("Best Things To Do in Nessebar, Bulgaria")
                .content("Things to see in Nessebar are plenty- most of them are historic and a treat for tourists.\n" +
                        "\n" +
                        "Nessebar Archaeological Museum: The perfect place to get intimate with Nessebar’s rich history, the museum showcases artefacts dating back to Roman, Greek and Ottoman rulers.\n" +
                        "Church of Christ Pantocrator: A beautiful church accommodating a museum, the Church of Christ Pantocrator is a must if you love architecture, design and history.\n" +
                        "Church of St. John Aliturgetos: A non-consecrated church, the Church of St. John Aliturgetos still holds its beauty. Perhaps the most beautiful church in the city had it been preserved well, take a walk along here to take a peek in 14th century architecture.\n" +
                        "Cape Emine: The stormiest cape in Bulgaria with ruins of a fortress, the place is perfect to hike to if you love to explore places off the beaten path.\n" +
                        "Church of the Holy Saviour: Built in 1609, the church of Holy Saviour is a non-consecrated building. The building is famous for its wall paintings depicting the life of Christ and Virgin Mary.")
                .additionalInfo("While Nessebar is pre-dominantly a sightseeing capital, there are a few activities that you can do while in the city.\n" +
                        "\n" +
                        "Diving: Diving the waters of the Black sea is a treat in itself. Dive to see jellyfishes, seahorses among other fishes.\n" +
                        "Shopping: Explore the old town flooded with specialty shops which include beautiful handicrafts and jewellery made out of semi precious stones.\n" +
                        "Below we have a list of things to do in Nessebar and the places where you want to spend the best moments of your trip. At the same time, the city offers you the best neighborhoods to explore and new foods to try.\n" +
                        "\n" +
                        "This list should help you in deciding and prioritizing what you should plan on your Nessebar getaway. Find a mix of must-see tourist spots, underrated local hangouts, and maybe even a few new experiences you can try out.\n" +
                        "\n" +
                        "Make the most of your trip to Nessebar with confidence.")
                .build();

        return attractionEntity;
    }

    @Test
    void testInit() {
        attractionService.init();

        AttractionEntity expectedAttractionEntity = createAttractionEntity();

        AttractionEntity attractionEntity = attractionRepository.findAll().get(0);

        assertEquals(expectedAttractionEntity.getName(), attractionEntity.getName());
        assertEquals(expectedAttractionEntity.getContent(), attractionEntity.getContent());
        assertEquals(expectedAttractionEntity.getAdditionalInfo(), attractionEntity.getAdditionalInfo());
    }

    @Test
    void testGetAttractionsOk() {

        attractionService.init();
        List<AttractionDto> attractionDtos = attractionService.getAttractions();

        String expectedName = "Best Things To Do in Nessebar, Bulgaria";
        String expectedContent = "Things to see in Nessebar are plenty- most of them are historic and a treat for tourists.\n" +
                "\n" +
                "Nessebar Archaeological Museum: The perfect place to get intimate with Nessebar’s rich history, the museum showcases artefacts dating back to Roman, Greek and Ottoman rulers.\n" +
                "Church of Christ Pantocrator: A beautiful church accommodating a museum, the Church of Christ Pantocrator is a must if you love architecture, design and history.\n" +
                "Church of St. John Aliturgetos: A non-consecrated church, the Church of St. John Aliturgetos still holds its beauty. Perhaps the most beautiful church in the city had it been preserved well, take a walk along here to take a peek in 14th century architecture.\n" +
                "Cape Emine: The stormiest cape in Bulgaria with ruins of a fortress, the place is perfect to hike to if you love to explore places off the beaten path.\n" +
                "Church of the Holy Saviour: Built in 1609, the church of Holy Saviour is a non-consecrated building. The building is famous for its wall paintings depicting the life of Christ and Virgin Mary.";

        String expectedInfo = "While Nessebar is pre-dominantly a sightseeing capital, there are a few activities that you can do while in the city.\n" +
                "\n" +
                "Diving: Diving the waters of the Black sea is a treat in itself. Dive to see jellyfishes, seahorses among other fishes.\n" +
                "Shopping: Explore the old town flooded with specialty shops which include beautiful handicrafts and jewellery made out of semi precious stones.\n" +
                "Below we have a list of things to do in Nessebar and the places where you want to spend the best moments of your trip. At the same time, the city offers you the best neighborhoods to explore and new foods to try.\n" +
                "\n" +
                "This list should help you in deciding and prioritizing what you should plan on your Nessebar getaway. Find a mix of must-see tourist spots, underrated local hangouts, and maybe even a few new experiences you can try out.\n" +
                "\n" +
                "Make the most of your trip to Nessebar with confidence.";
        assertEquals(expectedName, attractionDtos.get(0).getName());
        assertEquals(expectedContent, attractionDtos.get(0).getContent());
        assertEquals(expectedInfo, attractionDtos.get(0).getAdditionalInfo());
    }
}
