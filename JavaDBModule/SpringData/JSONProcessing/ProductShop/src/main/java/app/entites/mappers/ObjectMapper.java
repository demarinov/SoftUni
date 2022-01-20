package app.entites.mappers;

import org.modelmapper.ModelMapper;

public class ObjectMapper {

    private static ModelMapper mapper;

    private ObjectMapper() {}

    public static ModelMapper getInstance() {

        if (mapper == null) {
            mapper = new ModelMapper();
        }

        return mapper;
    }
}
