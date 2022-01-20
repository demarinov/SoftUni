package app.services.impl;

import app.models.Picture;
import app.repositories.PictureRepository;
import app.services.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PictureServiceImpl implements PictureService {

    private PictureRepository pictureRepository;

    @Autowired
    public PictureServiceImpl(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    @Override
    public void persist(Picture picture) {
        pictureRepository.save(picture);
    }

    @Override
    public List<Picture> findPictureByTitle(String title) {
        return pictureRepository.findByTitle(title);
    }
}
