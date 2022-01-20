package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dtos.binding.xml.OffersXmlDto;
import softuni.exam.models.entities.Offer;
import softuni.exam.repository.OfferRepository;
import softuni.exam.service.CarService;
import softuni.exam.service.OfferService;
import softuni.exam.service.SellerService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {

    private OfferRepository offerRepository;

    private ModelMapper modelMapper;

    private ValidationUtil validationUtil;

    private XmlParser xmlParser;

    private CarService carService;

    private SellerService sellerService;

    private static final String OFFER_PATH = "src/main/resources/files/xml/offers.xml";

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository, ModelMapper modelMapper,
                            ValidationUtil validationUtil, XmlParser xmlParser,
                            CarService carService, SellerService sellerService) {
        this.offerRepository = offerRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
        this.carService = carService;
        this.sellerService = sellerService;
    }

    @Override
    public boolean areImported() {
        return offerRepository.count() > 0;
    }

    @Override
    public String readOffersFileContent() throws IOException {
        return new String(Files.readAllBytes(Paths.get(OFFER_PATH)));
    }

    @Override
    public String importOffers() throws IOException, JAXBException {

        OffersXmlDto offersXmlDto = xmlParser.fromFile(OFFER_PATH, OffersXmlDto.class);

        StringBuilder sb = new StringBuilder();

        List<Offer> offers = offersXmlDto.getOfferDtoList()
                .stream()
                .filter(offerXmlDto -> {
                    boolean isValid = validationUtil.isValid(offerXmlDto);

                    sb.append(isValid ? String.format("Successfully imported seller - %s - %s%n",
                            offerXmlDto.getAddedOn(), offerXmlDto.isHasGoldStatus()) : "Invalid seller\n");

                    return isValid;
                })
                .map(offerXmlDto -> {
                    Offer offer = modelMapper.map(offerXmlDto, Offer.class);

                    offer.setCar(carService.findById(offerXmlDto.getCarXmlDto().getId()));
                    offer.setSeller(sellerService.
                            findById(offerXmlDto.getSellerXmlDto().getId()));

                    return offer;

                })
                .collect(Collectors.toList());


        offerRepository.saveAll(offers);

        return sb.toString();
    }
}
