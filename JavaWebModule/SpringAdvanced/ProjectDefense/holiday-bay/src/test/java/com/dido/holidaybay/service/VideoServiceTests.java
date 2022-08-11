package com.dido.holidaybay.service;

import com.dido.holidaybay.model.dto.AttractionDto;
import com.dido.holidaybay.model.dto.VideoDto;
import com.dido.holidaybay.model.entity.AttractionEntity;
import com.dido.holidaybay.model.entity.VideoEntity;
import com.dido.holidaybay.repository.VideoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class VideoServiceTests {

    @Autowired
    private VideoService videoService;

    @Autowired
    private VideoRepository videoRepository;

    @BeforeEach
    void init() {
        videoRepository.deleteAll();
        List<VideoEntity> videoEntities = createVideoEntities();
        videoRepository.saveAll(videoEntities);
    }

    @AfterEach
    void end() {

    }

    private List<VideoEntity> createVideoEntities() {
        VideoEntity videoEntityNesebar = VideoEntity.builder()
                .title("Nesebar 4k")
                .videoUrl("https://www.youtube.com/embed/ls_pyhwO2xk")
                .build();

        VideoEntity videoEntitySozopol =
                VideoEntity.builder()
                        .title("Sozopol 4k")
                        .videoUrl("https://www.youtube.com/embed/DCDD2kaPIio")
                        .build();

        VideoEntity videoEntitySunny =
                VideoEntity.builder()
                        .title("Sunny Beach 4k")
                        .videoUrl("https://www.youtube.com/embed/pqqIi3GOTmo")
                        .build();

        return Arrays.asList(videoEntityNesebar, videoEntitySozopol, videoEntitySunny);
    }

    @Test
    void testInit() {
        videoRepository.deleteAll();
        videoService.init();

        List<VideoEntity> expectedVideoEntities = createVideoEntities();

        List<VideoEntity> videoEntities = videoRepository.findAll();

        assertEquals(3, videoEntities.size());
    }

    @Test
    void testGetAttractionsOk() {

        videoService.init();
        List<VideoDto> videoDtos = videoService.getVideos();

        assertEquals(3, videoDtos.size());
    }
}
