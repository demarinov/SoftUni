package app.services;

import app.models.Album;

import java.util.List;

public interface AlbumService {

    List<Album> findAlbumByName(String name);

    void persist(Album album);
}
