package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dtos.binding.json.CarDto;
import softuni.exam.models.entities.Car;
import softuni.exam.repository.CarRepository;
import softuni.exam.service.CarService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.ValidationUtilImpl;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    private Gson gson;

    private ModelMapper modelMapper;

    private CarRepository carRepository;

    private ValidationUtil validationUtil;
    
    private static final String CARS_PATH = "src/main/resources/files/json/cars.json";

    @Autowired
    public CarServiceImpl(Gson gson, ModelMapper modelMapper, CarRepository carRepository, ValidationUtil validationUtil) {
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.carRepository = carRepository;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return carRepository.count() > 0;
    }

    @Override
    public String readCarsFileContent() throws IOException {
        return new String(Files.readAllBytes(Paths.get(CARS_PATH)));
    }


    @Override
    public String importCars() throws IOException {
        String carJson = readCarsFileContent();

        CarDto[] carDtos = gson.fromJson(carJson, CarDto[].class);


//        PropertyMap<CarDto, Car> propertyMap = new PropertyMap<CarDto, Car>() {
//            @Override
//            protected void configure() {
//
//                map(source.getRegisteredOn()).setRegisteredOn(null);
//            }
//        };
//
//        TypeMap<CarDto, Car> typeMap = modelMapper.getTypeMap(CarDto.class, Car.class);
//        if (typeMap == null) {
//            typeMap = modelMapper.addMappings(propertyMap);
//        }

        StringBuilder builder = new StringBuilder();

        List<Car> cars = Arrays.stream(carDtos)
                .filter(carDto -> {
                    boolean isValid = validationUtil.isValid(carDto);

                    builder.append(isValid ? String.format("Successfully imported car - %s - %s%n",
                            carDto.getMake(), carDto.getModel()) : "Invalid car\n");

                    return isValid;
                    
                        })
                .map(carDto -> modelMapper.map(carDto, Car.class))
                .collect(Collectors.toList());

        carRepository.saveAll(cars);

        return builder.toString();
    }

    @Override
    public String getCarsOrderByPicturesCountThenByMake() {

        StringBuilder sb = new StringBuilder();

        List<Car> cars = carRepository.findAllCountByPictures();

        cars.forEach(c ->
        sb.append(String.format("Car make - %s, model - %s\n" +
                "\tKilometers - %f\n" +
                "\tRegistered on - %s\n" +
                "\tNumber of pictures - %d",c.getMake(), c.getModel(),
                c.getKilometers(), c.getRegisteredOn(), c.getPictures().size()))
        .append(System.lineSeparator())
        );

        return sb.toString();
    }

    @Override
    public Car findById(Long car) {
        return carRepository.findById(car).orElse(null);
    }
}
