package softuni.exam.instagraphlite.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.models.dtos.json.UserJsonDto;
import softuni.exam.instagraphlite.models.entities.Picture;
import softuni.exam.instagraphlite.models.entities.Post;
import softuni.exam.instagraphlite.models.entities.User;
import softuni.exam.instagraphlite.repository.UserRepository;
import softuni.exam.instagraphlite.service.PictureService;
import softuni.exam.instagraphlite.service.UserService;
import softuni.exam.instagraphlite.util.ValidationUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private ModelMapper modelMapper;

    private ValidationUtils validationUtils;

    private Gson gson;

    private PictureService pictureService;

    private static final String USER_PATH = "src/main/resources/files/users.json";

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper,
                           ValidationUtils validationUtils, Gson gson,
                           PictureService pictureService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validationUtils = validationUtils;
        this.gson = gson;
        this.pictureService = pictureService;
    }


    @Override
    public boolean areImported() {
        return userRepository.count() > 0;
    }

    @Override
    public String readFromFileContent() throws IOException {
        return new String(Files.readAllBytes(Paths.get(USER_PATH)));
    }

    @Override
    public String importUsers() throws IOException {

        UserJsonDto[] userJsonDtos = gson.fromJson(readFromFileContent(), UserJsonDto[].class);

        StringBuilder sb = new StringBuilder();
        Arrays.stream(userJsonDtos)
                .filter(userJsonDto -> {
                    boolean isValid = validationUtils.isValid(userJsonDto)
                            && !existsByUserName(userJsonDto.getUsername())
                            && pictureService.isEntityExist(userJsonDto.getProfilePicture());


                    sb.append(isValid ? String.format("Successfully imported User: %s%n",
                            userJsonDto.getUsername()) : "Invalid User\n");

                    return isValid;
                })
                .map(userJsonDto -> {
                    User user = modelMapper.map(userJsonDto, User.class);
                    Picture profilePicture =
                            pictureService.getPictureByPath(userJsonDto.getProfilePicture());

                    if (profilePicture != null) {
                        user.setProfilePicture(profilePicture);
                    }
                    return user;
                })
                .forEach(u -> userRepository.save(u));

        return sb.toString();
    }

    public boolean existsByUserName(String userName) {

        return userRepository.existsByUserName(userName);
    }

    @Override
    public String exportUsersWithTheirPosts() {

        StringBuilder sb = new StringBuilder();

        // "User: {username}
        //Post count: {count of posts}
        //==Post Details:
        //----Caption: {caption}
        //----Picture Size: {size}

        List<User> usersOrdered = userRepository.getUsersOrderedByCountOfPosts();

        for (User user : usersOrdered) {
            sb.append(String.format("User: %s%n", user.getUserName()));
            sb.append(String.format("Post count: %d%n",user.getPosts().size()));
            sb.append(String.format("==Post Details:%n"));
            for (Post post : user.getPosts().stream().
                    sorted(Comparator.comparing(p -> p.getPicture().getSize()))
                    .collect(Collectors.toList())) {
                sb.append(String.format("----Caption: %s%n",post.getCaption()));
                sb.append(String.format("----Picture Size: %.2f%n",post.getPicture().getSize()));
            }
        }

        return sb.toString();
    }

    @Override
    public User findUserByUserName(String userName) {
        return userRepository.findUserByUserName(userName).get(0);
    }
}
