package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dtos.binding.xml.SellersXmlDto;
import softuni.exam.models.entities.Seller;
import softuni.exam.repository.SellerRepository;
import softuni.exam.service.SellerService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SellerServiceImpl implements SellerService {

    private XmlParser xmlParser;

    private SellerRepository sellerRepository;

    private ModelMapper modelMapper;

    private ValidationUtil validationUtil;

    private static final String SELLER_PATH = "src/main/resources/files/xml/sellers.xml";

    @Autowired
    public SellerServiceImpl(XmlParser xmlParser, SellerRepository sellerRepository,
                             ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.xmlParser = xmlParser;
        this.sellerRepository = sellerRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return sellerRepository.count() > 0;
    }

    @Override
    public String readSellersFromFile() throws IOException {
        return new String(Files.readAllBytes(Paths.get(SELLER_PATH)));
    }

    @Override
    public String importSellers() throws IOException, JAXBException {

        SellersXmlDto sellersXmlDto = xmlParser.fromFile(SELLER_PATH, SellersXmlDto.class);

        StringBuilder sb = new StringBuilder();

        List<Seller> sellers = sellersXmlDto.getSellerDtoList()
                .stream()
                .filter(sellerXmlDto -> {
                    boolean isValid = validationUtil.isValid(sellerXmlDto);

                    sb.append(isValid ? String.format("Successfully imported seller - %s - %s%n",
                            sellerXmlDto.getLastName(), sellerXmlDto.getEmail()) : "Invalid seller\n");

                    return isValid;
                })
                .map(sellerXmlDto -> {
                    Seller seller = modelMapper.map(sellerXmlDto, Seller.class);

                    return seller;
                })
                .collect(Collectors.toList());

        sellerRepository.saveAll(sellers);

        return sb.toString();
    }

    @Override
    public Seller findById(Long id) {
        return sellerRepository.findById(id).orElse(null);
    }
}
