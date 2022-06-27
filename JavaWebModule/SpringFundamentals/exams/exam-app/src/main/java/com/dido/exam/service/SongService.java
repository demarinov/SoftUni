package com.dido.exam.service;

import com.dido.exam.model.dto.SongDto;
import com.dido.exam.model.entity.SongEntity;
import com.dido.exam.model.entity.StyleEntity;
import com.dido.exam.model.entity.UserEntity;
import com.dido.exam.repository.SongRepository;
import java.util.Collections;

import com.dido.exam.util.SecondsUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SongService {

    private final SongRepository songRepository;
    private final UserService userService;
    private final StyleService styleService;
    private final ModelMapper mapper;

    public List<SongDto> getALlSongs() {

        return songRepository.findAll().stream()
                .map(songEntity -> {
                   SongDto song = mapper.map(songEntity, SongDto.class);
                    song.setDurationStr(SecondsUtils.convertToMinutes(songEntity.getDuration()));
                    return song;
                })
                .collect(Collectors.toList());
    }

    public List<SongDto> getSongsOfCurrentUser() {

        UserEntity userEntity = userService.findCurrentUser();

        if (userEntity != null) {

            return userEntity.getPlaylist().stream()
                    .map(songEntity -> {

                        SongDto song = mapper.map(songEntity, SongDto.class);
                        song.setDurationStr(SecondsUtils.convertToMinutes(songEntity.getDuration()));
                        return song;

                    })
                    .collect(Collectors.toList());
        }

        return Collections.emptyList();
    }

    public boolean addSong(SongDto songDto) {

        if (songDto != null) {
            SongEntity songEntity = mapper.map(songDto, SongEntity.class);
            StyleEntity styleEntity = styleService.findByName(songDto.getStyle().name());
            songEntity.setStyle(styleEntity);
            songRepository.save(songEntity);
            return true;
        }

        return false;
    }

    public boolean addSongToPlaylist(Long songId) {

        if (songId != null) {

            UserEntity userEntity = userService.findCurrentUser();

            SongEntity songEntity = songRepository.findById(songId).orElse(null);

            if (songEntity != null) {

                SongEntity foundSong = userEntity.getPlaylist().stream()
                                .filter(song -> song.getId().equals(songEntity.getId()))
                                        .findFirst().orElse(null);

                if (foundSong == null) {
                    userEntity.getPlaylist().add(songEntity);
                    userService.updateUser(userEntity);
                }
                return true;
            }
        }

        return false;
    }
}
