package app.services;

import app.models.Picture;

import java.util.List;

public interface PictureService {

    void persist(Picture picture);

    List<Picture> findPictureByTitle(String title);
}
