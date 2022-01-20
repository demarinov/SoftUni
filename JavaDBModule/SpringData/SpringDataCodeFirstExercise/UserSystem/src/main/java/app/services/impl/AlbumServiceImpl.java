package app.services.impl;

import app.models.Album;
import app.repositories.AlbumRepository;
import app.services.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService{

    private AlbumRepository albumRepository;

    @Autowired
    public AlbumServiceImpl(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    public List<Album> findAlbumByName(String name) {
        return albumRepository.findByName(name);
    }

    @Override
    public void persist(Album album) {
        albumRepository.save(album);
    }
}
