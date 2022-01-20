package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dtos.binding.json.PictureDto;
import softuni.exam.models.entities.Picture;
import softuni.exam.repository.CarRepository;
import softuni.exam.repository.PictureRepository;
import softuni.exam.service.CarService;
import softuni.exam.service.PictureService;
import softuni.exam.util.ValidationUtil;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PictureServiceImpl implements PictureService {

    private Gson gson;

    private PictureRepository pictureRepository;

    private CarService carService;

    private ModelMapper modelMapper;

    private ValidationUtil validationUtil;
    
    private static final String PICTURES_PATH = "src/main/resources/files/json/pictures.json";

    @Autowired
    public PictureServiceImpl(Gson gson, PictureRepository pictureRepository, CarService carService,
                              ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.gson = gson;
        this.pictureRepository = pictureRepository;
        this.carService = carService;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }


    @Override
    public boolean areImported() {
        return pictureRepository.count() > 0;
    }

    @Override
    public String readPicturesFromFile() throws IOException {

        return new String(Files.readAllBytes(Paths.get(PICTURES_PATH)));
    }

    @Override
    public String importPictures() throws IOException {

        PictureDto[] pictureDtos = gson.fromJson(readPicturesFromFile(),PictureDto[].class);


        StringBuilder sb = new StringBuilder();
        List<Picture> pictures = Arrays.stream(pictureDtos)
                .filter(pictureDto -> {
                    boolean isValid = validationUtil.isValid(pictureDto);

                    sb.append(isValid ? String.format("Successfully imported picture - %s%n",
                            pictureDto.getName()) : "Invalid picture\n");

                    return isValid;
                }).map(pictureDto -> {
                    Picture picture = modelMapper.map(pictureDto, Picture.class);

                    picture.setCar(carService.findById(pictureDto.getCar()));

                    return picture;


                })
                .collect(Collectors.toList());

        pictureRepository.saveAll(pictures);

        return sb.toString();
    }
}
