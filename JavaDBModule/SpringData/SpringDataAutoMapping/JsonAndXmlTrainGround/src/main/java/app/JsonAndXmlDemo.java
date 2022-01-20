package app;

import app.entities.AddressDto;
import app.entities.AddressXmlDto;
import app.factory.AddressDtoFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;

public class JsonAndXmlDemo {

    public static void main(String[] args) throws FileNotFoundException, JAXBException {

        // 1. From Dto to Json
        System.out.println(convertFromDtoToJson());

        // 2. From Json to Dto
        System.out.println(convertFromJsonToDto());

        // 3. From Dto to Json file
        try {
            convertFromDtoToJsonFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // 4. From Json file to Dto
        System.out.println(convertFromJsonFileToDto());

        // 5. From Dto to Xml
        convertFromDtoToXml();

        // 6. From Xml to Dto
        System.out.println(convertFromXmlToDto());

        // 7. From Dto array to json array
        System.out.println(convertFromJsonToDtoArray()[0]);

    }

    public static class Hero {
        boolean rol;

        public boolean isRol() {
            return rol;
        }
    }

    private static void test(boolean nice) {

    }

    private static AddressXmlDto convertFromXmlToDto() throws JAXBException {

        JAXBContext jaxbContext = JAXBContext.newInstance(AddressXmlDto.class);

        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        AddressXmlDto addressXmlDto = (AddressXmlDto) unmarshaller.
                unmarshal(new File("address.xml"));

        return addressXmlDto;
    }

    private static void convertFromDtoToXml() throws JAXBException, JAXBException {

        AddressXmlDto addressDto = AddressDtoFactory.createAddressXmlDto();
        JAXBContext jaxbContext = JAXBContext.newInstance(AddressXmlDto.class);

        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.marshal(addressDto, new File("address.xml"));


    }

    private static AddressDto convertFromJsonFileToDto() throws FileNotFoundException {

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting().create();

        AddressDto addressDto;

        FileReader reader = new FileReader("address.json");

        addressDto = gson.fromJson(reader, AddressDto.class);

        return addressDto;
    }

    private static void convertFromDtoToJsonFile() throws FileNotFoundException {

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting().create();

        Object addressDto = AddressDtoFactory.createAddressDto();

        PrintWriter writer = new PrintWriter("address.json");
        gson.toJson(addressDto, writer);

        writer.close();
    }

    private static AddressDto[] convertFromJsonToDtoArray() {

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting().create();

        String jsonStr = convertFromDtoToJsonArray();

        AddressDto[] addressDto;

        addressDto = gson.fromJson(jsonStr, AddressDto[].class);

        return addressDto;
    }

    private static AddressDto convertFromJsonToDto() {

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting().create();

        String jsonStr = convertFromDtoToJson();

        AddressDto addressDto;

        addressDto = gson.fromJson(jsonStr, AddressDto.class);

        return addressDto;
    }

    private static String convertFromDtoToJson() {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting().create();

        AddressDto addressDto = AddressDtoFactory.createAddressDto();

        String json = gson.toJson(addressDto);

        return json;
    }

    private static String convertFromDtoToJsonArray() {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting().create();

        AddressDto[] addressDto = AddressDtoFactory.createAddressDtoArray();

        String json = gson.toJson(addressDto);

        return json;
    }

}
