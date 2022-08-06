package com.dido.holidaybay.service;

import com.dido.holidaybay.model.dto.VideoDto;
import com.dido.holidaybay.model.entity.VideoEntity;
import com.dido.holidaybay.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class VideoService {

    private final VideoRepository videoRepository;
    private final ModelMapper modelMapper;

    public void init() {


        if (videoRepository.count() == 0) {

            VideoEntity videoEntityNesebar =
                    VideoEntity.builder()
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

            videoRepository.save(videoEntityNesebar);
            videoRepository.save(videoEntitySozopol);
            videoRepository.save(videoEntitySunny);

        }
    }

    public List<VideoDto> getVideos() {

        return videoRepository.findAll().stream()
                .map(video -> modelMapper.map(video, VideoDto.class))
                .collect(Collectors.toList());
    }
}
