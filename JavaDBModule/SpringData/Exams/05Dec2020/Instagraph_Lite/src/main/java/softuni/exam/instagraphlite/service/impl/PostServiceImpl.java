package softuni.exam.instagraphlite.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.models.dtos.xml.PostXmlDto;
import softuni.exam.instagraphlite.models.dtos.xml.PostsXmlDto;
import softuni.exam.instagraphlite.models.entities.Post;
import softuni.exam.instagraphlite.repository.PostRepository;
import softuni.exam.instagraphlite.service.PictureService;
import softuni.exam.instagraphlite.service.PostService;
import softuni.exam.instagraphlite.service.UserService;
import softuni.exam.instagraphlite.util.ValidationUtils;
import softuni.exam.instagraphlite.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    private ModelMapper modelMapper;

    private ValidationUtils validationUtils;

    private XmlParser xmlParser;

    private PictureService pictureService;

    private UserService userService;

    private static final String POST_PATH = "src/main/resources/files/posts.xml";

    @Autowired
    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper,
                           ValidationUtils validationUtils, XmlParser xmlParser,
    PictureService pictureService, UserService userService) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
        this.validationUtils = validationUtils;
        this.xmlParser = xmlParser;
        this.pictureService = pictureService;
        this.userService = userService;
    }

    @Override
    public boolean areImported() {
        return postRepository.count() > 0;
    }

    @Override
    public String readFromFileContent() throws IOException {
        return new String (Files.readAllBytes(Paths.get(POST_PATH)));
    }

    @Override
    public String importPosts() throws IOException, JAXBException {

        PostsXmlDto postsXmlDto = xmlParser.fromFile(POST_PATH, PostsXmlDto.class);

        StringBuilder sb = new StringBuilder();

        postsXmlDto.getPostXmlDtos()
                .stream()
                .filter(postXmlDto ->
                {
                    boolean isValid = validationUtils.isValid(postXmlDto)
                            && entityPictureExists(postXmlDto.getPicture().getPath())
                            && entityUserExists(postXmlDto.getUser().getUserName());

                    sb.append(isValid ? String.format("Successfully imported Post, made by user %s%n",
                            postXmlDto.getUser().getUserName()) : "Invalid Post\n");

                    return isValid;
                })
                .map(postXmlDto -> {
                    Post post = modelMapper.map(postXmlDto, Post.class);

                    post.setPicture(pictureService.
                            getPictureByPath(postXmlDto.getPicture().getPath()));

                    post.setUser(userService.findUserByUserName(post.getUser().getUserName()));

                    return post;
                })
                .forEach(post -> postRepository.save(post));

        return sb.toString();
    }

    private boolean entityUserExists(String userName) {
        return userService.existsByUserName(userName);
    }

    private boolean entityPictureExists(String path) {

        return pictureService.isEntityExist(path);
    }
}
