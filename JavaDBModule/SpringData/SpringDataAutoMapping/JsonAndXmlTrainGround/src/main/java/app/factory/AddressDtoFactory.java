package app.factory;

import app.entities.AddressDto;
import app.entities.AddressXmlDto;

public class AddressDtoFactory {

    private AddressDtoFactory() {

    }

    public static AddressDto createAddressDto() {

        AddressDto addressDto = new AddressDto();
        addressDto.setCity("London");
        addressDto.setCountry("England");
        addressDto.setStreet("Leydon str.");

        return addressDto;
    }

    public static AddressDto[] createAddressDtoArray() {

        AddressDto addressDto = new AddressDto();
        addressDto.setCity("London");
        addressDto.setCountry("England");
        addressDto.setStreet("Leydon str.");

        AddressDto addressDtoOne = new AddressDto();
        addressDtoOne.setCity("London2");
        addressDtoOne.setCountry("England2");
        addressDtoOne.setStreet("Leydon str. 2");



        return new AddressDto[]{addressDto, addressDtoOne};
    }

    public static AddressXmlDto createAddressXmlDto() {
        AddressXmlDto addressDto = new AddressXmlDto();
        addressDto.setCity("London");
        addressDto.setCountry("England");
        addressDto.setStreet("Leydon str.");

        return addressDto;

    }
}
