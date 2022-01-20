package softuni.exam.instagraphlite.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.models.dtos.json.PictureJsonDto;
import softuni.exam.instagraphlite.models.entities.Picture;
import softuni.exam.instagraphlite.repository.PictureRepository;
import softuni.exam.instagraphlite.repository.UserRepository;
import softuni.exam.instagraphlite.service.PictureService;
import softuni.exam.instagraphlite.util.ValidationUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PictureServiceImpl implements PictureService {

    private PictureRepository pictureRepository;

    private ModelMapper modelMapper;

    private ValidationUtils validationUtils;

    private Gson gson;

    private static final String PICTURE_PATH = "src/main/resources/files/pictures.json";

    @Autowired
    public PictureServiceImpl(PictureRepository pictureRepository, ModelMapper modelMapper,
                              ValidationUtils validationUtils, Gson gson) {
        this.pictureRepository = pictureRepository;
        this.modelMapper = modelMapper;
        this.validationUtils = validationUtils;
        this.gson = gson;
    }

    @Override
    public boolean areImported() {
        return pictureRepository.count() > 0;
    }

    @Override
    public String readFromFileContent() throws IOException {
        return new String(Files.readAllBytes(Paths.get(PICTURE_PATH)));
    }

    @Override
    public String importPictures() throws IOException {

        PictureJsonDto[] pictureJsonDtos = gson.fromJson(readFromFileContent(),PictureJsonDto[].class);

        StringBuilder sb = new StringBuilder();
        Arrays.stream(pictureJsonDtos)
                .filter(pictureJsonDto -> {

                    boolean isValid = validationUtils.isValid(pictureJsonDto)
                            && !isEntityExist(pictureJsonDto.getPath());

                    sb.append(isValid ? String.format("Successfully imported Picture, with size %.2f%n",
                            Double.parseDouble(pictureJsonDto.getSize())) : "Invalid Picture\n");



                    return isValid;

                })
                .map(pictureJsonDto -> {
                    Picture picture = modelMapper.map(pictureJsonDto, Picture.class);

                    return picture;
                })
                .forEach(p -> pictureRepository.save(p));

        return sb.toString();
    }

    public boolean isEntityExist(String path) {
        return pictureRepository.existsByPath(path);
    }


    @Override
    public String exportPictures() {

        List<Picture> pictures = pictureRepository.getPicturesBySizeGreaterThanOrderBySizeAsc(30000d);

        // {picSize} – {picPath}
        StringBuilder sb = new StringBuilder();

        for (Picture pic : pictures) {
            sb.append(String.format("%.2f - %s%n",pic.getSize(), pic.getPath()));
        }
        return sb.toString();
    }

    @Override
    public Picture getPictureByPath(String profilePicture) {
        return pictureRepository.getPictureByPath(profilePicture).stream().findFirst().orElse(null);
    }
}
